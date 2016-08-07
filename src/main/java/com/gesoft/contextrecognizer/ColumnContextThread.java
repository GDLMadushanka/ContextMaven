/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gesoft.contextrecognizer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import com.joestelmach.natty.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author lahiru
 */
public class ColumnContextThread implements Runnable{
Thread t;    
Column col;
String[] DataToProcess;
Set<Float> set_float = new HashSet<Float>();
Set<String> set_string = new HashSet<String>();
ArrayList<Integer> date_List = new ArrayList<Integer>();
int intCount =0,floatCount=0,StringCount=0,dateCount=0;
SysProperties res = new SysProperties();
boolean lessThan = false;


public ColumnContextThread(Column col,String[] data)
{
    this.col = col;
    this.DataToProcess=data;
    t = new Thread(this);
    t.start();
    
}

    @Override
    public void run() {
        col.IsContinous = true;
        String temp = RecognizeType();
        if(temp.equals("Precentage") && lessThan==true)
        {
            if(!Accumulate()){temp="Numerical";}
        }
        col.Context = temp;
        
    }
  
    public String RecognizeType() // select first 100 rows and deduce the data type
    {   
        int iterations = 100;
        Parser parser = new Parser();
        if(DataToProcess.length<iterations){iterations = DataToProcess.length;lessThan = true;}
        for (int i = 1; i < iterations; i++) { // i=1 without header
            String temp = DataToProcess[i];
            if(this.tryParseFloat(temp)){set_float.add(Float.parseFloat(temp));floatCount++;}
            else {set_string.add(temp.toLowerCase());StringCount++;} // getting all strings to a common case
          
            List<DateGroup> groups = parser.parse(temp);
            int x=0;
            for(DateGroup group:groups)  {
            Date dates = group.getDates().get(0);
            x =dates.getDate(); }
            if(x!=0){date_List.add(x);}
        }
        String returnValue=null;
        // if any type contains more than half of the iterations that type is selected
        //if(intCount>iterations/2 && floatCount>iterations*.4){returnValue=2;}
        
        if(floatCount>iterations/2)
        {
            returnValue="Numerical";
            if(set_float.size()<floatCount*.6){col.IsContinous=false;}
            col.NoOfDiscreteValues = set_float.size();
        }
        else if(date_List.size()>iterations/2)
        {
            returnValue="DateTime";
            col.NoOfDiscreteValues = date_List.size();
        }
        else if(StringCount>iterations/2)
        {
            returnValue="Nominal";
            if(set_string.size()<StringCount*.6){col.IsContinous=false;}
            col.NoOfDiscreteValues = set_string.size();
        }
        
        //Secondary recognizions
        if(returnValue.equals("Numerical")){
            int last = set_float.size();
            float[] arr = new float[last];
            int i = 0;

            for (Float f : set_float) {
                arr[i++] = (f != null ? f : Float.NaN); // Or whatever default you want.
            }
            Arrays.sort(arr);
            float max = arr[last-1];
            if(max<=1f || max<=100f){returnValue="Precentage";}
        }
        //calling python scripts
        return returnValue;
    }
    
    public boolean Accumulate()
    {
        float total=0f;
        int i = 0;
        for (Float f : set_float) {
               total += f; // Or whatever default you want.
        }
        if((total>.9f && total<1.1f) || (total>99f && total<101f)){return true;}
        return false;
    }
    
    boolean tryParseInt(String value) {  
     try {  
         Integer.parseInt(value);  
         return true;  
        } catch (NumberFormatException e) {  
         return false;  
        }  
    }    
    
    boolean tryParseFloat(String value) {  
     try {  
         Float.parseFloat(value);  
         return true;  
        } catch (NumberFormatException e) {  
         return false;  
        }  
    } 
        
        
}
