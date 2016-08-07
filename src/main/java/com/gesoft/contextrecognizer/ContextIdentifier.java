/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gesoft.contextrecognizer;

import java.util.ArrayList;

/**
 *
 * @author lahiru
 */
public class ContextIdentifier{
    public ArrayList<String[]> data;
    int ThreadCount=0; // equals to num columns
    int NumRows =0;
    private Table tbl = new Table();
   
    
    public void Initialize(ArrayList<String[]> data) throws InterruptedException
    {
        this.data = data;
        NumRows = data.get(0).length;
        ThreadCount = data.size();
        tbl.columnCount = ThreadCount;
        tbl.NumOfRows = NumRows;
        tbl.Data = new Column[ThreadCount];
        ColumnContextThread[] threadArr = new ColumnContextThread[ThreadCount];
       
        System.out.println("All threads creted");
        for (int i = 0; i < ThreadCount; i++) {
            tbl.Data[i] = new Column();
            tbl.Data[i].ColumnId = i;
            tbl.Data[i].Heading=data.get(i)[0];
            threadArr[i]= new ColumnContextThread(tbl.Data[i],data.get(i));
        }
        for (int i = 0; i < ThreadCount; i++) {
            threadArr[i].t.join();
        }
        this.printTable(tbl);
    }
    
    public void printTable(Table tbl)
    {
        System.out.println("No of columns : "+tbl.columnCount);
        System.out.println("No of rows : "+tbl.NumOfRows);
        for (int i = 0; i < tbl.columnCount; i++) {
            System.out.println("_____________________________");
            Column col = tbl.Data[i];
            System.out.println("Column"+i+" : "+col.Heading);
            System.out.println("Context : "+col.Context);
            if(col.IsContinous){System.out.println("Continous");}
            else System.out.println("Discrete");
            System.out.println("No of discrete values : "+col.NoOfDiscreteValues);
        }
    }
    
}
