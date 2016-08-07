/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gesoft.contextrecognizer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author lahiru
 */
public class API {

   
InputStream inputStream;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException{
        // TODO code application logic here
        SysProperties res = new SysProperties();
        /*
        String path ="/home/lahiru/Desktop/timesData.csv";
        FileReaderCSV reader = new FileReaderCSV();
        ContextIdentifier con = new ContextIdentifier();
        
        reader.filePath = path;
        ArrayList<String[]> temp = reader.GetCSVData();
        con.Initialize(temp);*/
        //System.out.println(res.getProp("DataTypes.properties","Numerical"));
        res.IncrementProp("DataTypes.properties","Numerical");
        res.IncrementProp("DataTypes.properties","Num");
    }
}

