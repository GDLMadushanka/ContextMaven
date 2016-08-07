/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gesoft.contextrecognizer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
/**
 *
 * @author lahiru
 */
public class SysProperties 
{
    public String getProp(String fileName,String propName)
    {
        Properties props = new Properties();
        InputStream is = null;
        try {
            File f = new File(fileName);
            is = new FileInputStream( f );
            props.load( is );
            String temp = props.getProperty(propName);
            is.close();
            return temp;
        }
        catch ( Exception e ) { System.out.println(e.getMessage());}
        return null;
    }
    public void setProp(String fileName,String propName,String value)
    {
        Properties props = new Properties();
        InputStream is = null;
        OutputStream out = null;
        try {
            File f = new File(fileName);
            is = new FileInputStream( f );
            props.load( is );
            is.close();
            out = new FileOutputStream( f );
            props.setProperty(propName, value);
            props.store(out, null);
            out.close();
        }
        catch ( Exception e ) { System.out.println(e.getMessage());}
    }
    public void IncrementProp(String fileName,String propName)
    {
        Properties props = new Properties();
        InputStream is = null;
        OutputStream out = null;
        try {
            File f = new File(fileName);
            is = new FileInputStream( f );
            props.load( is );
            String tem = props.getProperty(propName);
            is.close();
            out = new FileOutputStream( f );
            if(tem==null)
            {
                props.setProperty(propName, "1");
                props.store(out, null);
                out.close();
            }
            else
            {
                Integer temp = Integer.parseInt(tem);
                temp++;
                props.setProperty(propName,String.valueOf(temp));
                props.store(out, null);
                out.close();
            }
        }
        catch ( Exception e ) { System.out.println("error");}
    }
}
    