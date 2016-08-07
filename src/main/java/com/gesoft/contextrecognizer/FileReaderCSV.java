/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gesoft.contextrecognizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lahiru
 */
public class FileReaderCSV {
  
    public String filePath;
    ArrayList<String[]> data = new ArrayList();
    ArrayList<String[]> ProcessedData = new ArrayList();
    
    public void readCSV() 
    {
        if(!filePath.isEmpty())
        {
            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";

            try {
                    br = new BufferedReader(new FileReader(filePath));
                    while ((line = br.readLine()) != null) 
                    {
                        String[] temp = line.split(cvsSplitBy);
                        data.add(temp);
                    }
                } 
            catch (Exception e) {}
        }
    }
    
    public ArrayList<String[]> GetCSVData()
    {
        readCSV();
        int NumCols = data.get(0).length;
        int NumRows = data.size();
        
        for (int i = 0; i < NumCols; i++) {
            String[] t = new String[NumRows];
            for (int j = 0; j < NumRows; j++) {
                    t[j] = data.get(j)[i];
                
            }
            ProcessedData.add(t);
        }
        
        return ProcessedData;
    }
}


