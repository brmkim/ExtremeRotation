/*
 * ExtRotation package contains Point2D class and Rotate2D class
 */
package ExtRotation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * filename: Rotate2D.java
 * class: Rotate2D
 * User-interface class that contains main and other methods for the program
 * The program takes type double values for x and y coordinates from reading 
 * a text or binary file, or from the keyboard; It asks an angle input in 
 * degrees and convert it into radians; displays the rotated points up to 
 * 4 decimal points on the screen and save the original and changed points 
 * into a binary file if needed
 *  
 * @author Boram Kim, Phuong Tran
 * @version 1.6
 * 
 * Compiler: Java 1.8 <br>
 * OS: Windows 10 <br>
 * Hardware: HP Laptop <br>
 * @author Boram Kim, Phuong Tran 
 * 
 * Log:
 * 04/10/2017 BK version 1 completed
 * 04/11/2017 PT added code for making ArrayList to Point2D array
 * 04/13/2017 BK added DecimalFormatter
 * 04/14/2017 BK discarded using ArrayList for rotated points; added readFile
 *               and saveFIle methods
 * 04/15/2017 BK added -b option for reading bin file; changed the flow a bit
 * 04/18/2017 BK added readTxtFile() and readPoints() method
 * 04/19/2017 BK did some testing; added more comments
 */
public class Rotate2D 
{
    /**
     * Inputs numbers for x and y coordinates and an angle to rotate them
     * Displays the results (both original and changed) of up to 4 decimal 
     * points on the screen; prompt if the user wants to save them in binary
     * file   
     * @param args arguments from command line
     * Calls readTxtFile, readFile, readPoints, saveFile
     */
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Point2D p2d = null;  // objects inside main method needs to be 
                            // initialized to null. Not sure why.
        ArrayList<Point2D> pointsList = null;
        Point2D[] arr = null;
        Point2D[] convertedArr = null; 
        String filename = null;
        DecimalFormat twoDP = new DecimalFormat("###0.0###");
        if (args.length == 1 && args[0].contains(".txt"))
        { // if cmd arguments has txt file
            filename = args[0]; // take filename as a string
            pointsList = readTxtFile(filename);
        }
        else if (args.length == 2 && (args[0].contentEquals("-b") || 
                args[1].contentEquals("-b")))
        { // if cmd line arguments has -b option and the file name 
            // the -b argument could be before or after the file name
            if (args[0].contentEquals("-b"))
                filename = args[1];
            else if (args[1].contentEquals("-b"))
                filename = args[0];
            pointsList = readFile(filename);
        }
        else if (args.length < 1)  // else ask whether to read an input file 
        {
            System.out.print("Would you like to read from a text file? (y/n) ");
            
            String response = input.next();
            // prompt until correct character is entered
            while (!(response.equals("Y") || response.equals("y") 
                    || response.equals("N") || response.equals("n")) )
            {
                System.out.print("Would you like to read from a text file? "
                        + "(y/n) ");
                response = input.next();
            }
            // When reading from a file    
            if (response.equals("Y") || response.equals("y"))
            {   
                System.out.print("Enter the file name: ");
                filename = input.next();
                pointsList = readFile(filename);
                
                // Above) what's inside the bin file is an array of Point2D, 
                // so convert it into array then convert that array ArrayList 
                // of Pont2D
            }
            else if (response.equals("N") || response.equals("n"))
            {
                pointsList = readPoints();
            }
        }
        // When extra cmd line argument is given but fail to read the file name
        // or path provided 
        else
        {
           pointsList = readPoints(); 
        }
        
        // proceed to the next step
        System.out.print("Please enter the angle of rotation ");
        System.out.print("(-360 <= angle <= 360): ");
        Scanner angleInput = new Scanner(System.in);
        double angle = 0.0;
        if (angleInput.hasNextDouble())
            angle = angleInput.nextDouble();
        // adjusting the angle within the range. It still works without 
        // doing this, but it seems like Paul did this way. So I'm adjusting it. 
        if (angle > 360)
        {
            while (angle > 360)
                angle -= 360;
        }
        else if (angle < -360)
        {
            while (angle < -360)
                angle += 360;
        }
        Double radian = angle * (Math.PI / 180);

        // show entered points
        System.out.println("\nentered points: ");  
        for (Point2D p: pointsList) // printing 
            System.out.println("(" + twoDP.format(p.xPoint) + ", " 
                    + twoDP.format(p.yPoint) + ") ");
        // declare two Point2D object array variables
        arr = new Point2D[pointsList.size()]; 
        convertedArr = new Point2D[pointsList.size()]; 
        // make the pointsList (an ArrayList) into the object array
        arr = pointsList.toArray(arr);

        System.out.println("\npoints rotated " + angle + " degrees: ");
        for (int i = 0; i < pointsList.size(); i++)
        {
            // make each array member (each point) into an Point2D object 
            // then use Point2D class's rotator method on it 
            Point2D point = arr[i];
            Point2D convteredPoint = (new Point2D()).rotator(point, 
                    radian); 
            // Above) The method is not to be a static (if it's static 
            // it won't be serializable) so needed to create an object 
            // to call the method

            // now put those convertedPoint objects into the object array
            convertedArr[i] = convteredPoint;
        }

        // show rotated points
        for (Point2D p: convertedArr)
            System.out.println("(" + twoDP.format(p.xPoint) + ", " 
                    + twoDP.format(p.yPoint) + ")"); 

        // ask if the user wants to save the original and/or 
        // rotated points into a binary file
        System.out.println("Save original points as binary file?");
        Scanner newInput = new Scanner(System.in); // made a new scanner, 
        //because the previous scanner retains the non-numeric value used to
        //get out of the x/y coordinate question
        String response2 = newInput.next();
        if (response2.equals("Y") || response2.equals("y")) 
        {
            saveFile(arr);
            response2 = null;  // erase what was written to recycle the var       
        }
        System.out.println("Save rotate points as binary file?");
        response2 = newInput.next();
        if (response2.equals("Y") || response2.equals("y")) 
        {
            convertedArr = pointsList.toArray(arr); // converting it into
                            //pointList here only for a formatting reason
            saveFile(convertedArr);   
        }
    }
    /**
     * reads x and y points from the keyboard, make them Point2D objects,  and
     * then add them to an ArrayList
     * Input: type double x and y coordinates from keyboard
     * Output: ArrayList of Point2D objects to System
     * @return pointsList -- an ArrayList of sets of x and y points
     */
    public static ArrayList<Point2D> readPoints()
    {
        ArrayList<Point2D> pointsList = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        Point2D p2d = null; 
        System.out.println("No points are entered.");
        System.out.println("Enter your list of points.");
        System.out.println("Enter a non-numeric value to stop.");
        double xPoint = 0.0, yPoint = 0.0; 

        boolean repeat = true;
        do 
        {
            for (int i = 1; i <= 30 ; i++ ) // the purpose of this for 
                // this loop is to keep track of the point sets entered
            {
                System.out.print("Enter x coordinate of point " 
                        + i + ": ");
                if (input.hasNextDouble())  
                    xPoint = input.nextDouble();
                else
                {
                    repeat = false;
                    break;
                }
                System.out.print("Enter y cocordinate of point " 
                        + i + ": ");
                if (input.hasNextDouble())
                    yPoint = input.nextDouble();
                else
                {
                    repeat = false;
                    break;
                }
                p2d = new Point2D(xPoint, yPoint);                   
                pointsList.add(p2d);
                xPoint = 0.0;
                yPoint = 0.0; // reinitializing to 0
            }
        }while(repeat);
        
        return pointsList;
    }
    /**
     * reads in a text file for input; if the file does not exist or readable
     * calls readPoints method that prompt input from System.in
     * Input: a text file of x and y coordinates
     * Output: ArrayList of Point2D objects to System
     * @param fname txt file name specified in command line
     * @return pointsList -- an ArrayList of sets of x and y points 
     */
    public static ArrayList<Point2D>  readTxtFile(String fname)
    { // this method read txt file and return an ArrayList of the points                                                      
        File fileObject = new File(fname); 
        Scanner fileInputScanner;
        double xPoint = 0.0, yPoint = 0.0;
        Point2D p2d = null;
        ArrayList<Point2D> pointsList = new ArrayList<>();
        
        // if the file exists and is readable
        if  (fileObject.exists() && fileObject.canRead())
        {
            try
            {
                fileInputScanner = new Scanner(fileObject);
                boolean quit = false;
                int numberOfSets = 0;
                
                // read the first element in the file (which tells the number
                // of point sets in the file
                if (fileInputScanner.hasNextInt())
                {
                    numberOfSets = fileInputScanner.nextInt();
                    for (int i = 0; i < numberOfSets; i++)
                    {
                        if (fileInputScanner.hasNextDouble())
                            xPoint = fileInputScanner.nextDouble();
                        else
                        {
                            quit = true;
                            break;
                        }
                        if (fileInputScanner.hasNextDouble())
                            yPoint = fileInputScanner.nextDouble();
                        else
                        {
                            quit = true;
                            break;
                        }
                        p2d = new Point2D(xPoint, yPoint);
                        pointsList.add(p2d);
                        xPoint = 0.0;
                        yPoint = 0.0; // reinitializing to 0
                    }
                }
                else
                {
                    System.out.println("The first number in the file should "
                    + " be an int that indicates the number of point sets.");
                    pointsList = readPoints();
                }
                fileInputScanner.close();
            }
            catch (IOException e)
            {
                System.out.println("Problem reading from file.");
            }
        }
        else if (!fileObject.exists()) // if file doesn't exit 
        {
            System.out.println("File doesn't exist.");
            pointsList = readPoints();
        }
        else // if file is not readable
        {
            System.out.println("File is not readable.");
            pointsList = readPoints();
        }
        
        return pointsList;
    }
    /**
     * reads in a binary file for input; if the file does not exist or readable
     * calls readPoints method that prompt input from System.in
     * Input: a binary file of Point2D object
     * Output: ArrayList of Point2D objects to System
     * @param fname bin file name specified in command line
     * @return pointsList -- an ArrayList of sets of x and y points 
     */
    public static ArrayList<Point2D> readFile(String fname) // read bin file
    {
        FileInputStream readFileStream = null;
        ObjectInputStream inputStream = null;
        File fileObject = new File(fname);
        Point2D pts;
        Point2D[] objArray = null;
        ArrayList<Point2D> pointsList = null;
        // read file
        if (fileObject.exists() && fileObject.canRead())
        {
            try
            {
                readFileStream = new FileInputStream(fname);
                inputStream = new ObjectInputStream(readFileStream);
            }
            catch (IOException e)
            {
                System.out.println("Error opening input file " 
                        + fname + ".");
                pointsList = readPoints();
                return pointsList;
            }
        }
         else if (!fileObject.exists()) // if file doesn't exit 
        {
            System.out.println("File doesn't exist.");
            pointsList = readPoints();
            return pointsList;
        }
        else // if file is not readable
        {
            System.out.println("File is not readable.");
            pointsList = readPoints();
            return pointsList;       
         }
        try
        {
            objArray = (Point2D[]) inputStream.readObject();
            pointsList = new ArrayList<>(Arrays.asList(objArray));
        }
        catch(Exception e)
        {
            System.out.println("Error reading from file " 
                    + fname + e.getMessage() + ".");
            pointsList = readPoints();
        }
        return pointsList;
    }
    /**
     * saves the coordinate values into a binary file
     * Input: Point2D object that contains values for x and y coordinates
     * Output: bin file of the object
     * @param obj Point2D object to be saved
     */
    public static void saveFile(Point2D[] obj)
    {
        ObjectOutputStream outputStream = null;
        System.out.print("Enter the file name: ");
        Scanner input = new Scanner(System.in);
        String filename = input.next();
        
        try
        {
            outputStream = new ObjectOutputStream(
                           new FileOutputStream(filename));  
        }
        catch(IOException e) // if the file is not there
        {
            System.out.println("Error opening output file " +
                                filename + ".");
            System.exit(0);
        }
        Point2D[] points = obj;  // redundant?
        try
        {
            outputStream.writeObject(points);
            outputStream.close( );
        }
        catch(IOException e)
        {
            System.out.println("Error writing to file " +
                                filename + ".");
            System.exit(0);
        }
        System.out.println("Records sent to file " +
                            filename + ".");
    }
}
