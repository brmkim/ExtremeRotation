/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtRotation;

import java.io.*;
import ExtRotation.Rotate2D;
/**
 *
 * @author boram
 */
public class ReadFile 
{
    public static String filename = "";
    public ReadFile(String fn) 
    {
        filename = fn;
    }
    public static void main(String[] args)
    {
        ObjectInputStream inputStream = null;
        try
        {
            inputStream = new ObjectInputStream( new FileInputStream(filename));
        }
        catch (IOException e)
        {
            System.out.println("Error opening input file " + filename + ".");
            System.exit(0);
        }
        // destroy old objects
        Rotate2D readOne = null;
        try
        {
            readOne = (Rotate2D) inputStream.readObject();
            inputStream.close();
        }
        catch(Exception e)
        {
            System.out.println("Error reading from file " + filename + ".");
            System.exit(0);
        }
        System.out.println(readOne);
    }

   
}
