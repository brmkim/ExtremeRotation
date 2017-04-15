/* User interface class -- contains main and other necessary methods
 * - Add -b options for reading binary files
 * - No command arg || file does not exist || is not readable then read input
 *   and put into an ArrayList<point2D>. Input stops when no-numeric input is 
 *    entered
 * - File exits && readable then read the contents of the file into an array
 * - Ask an angle input in degrees. Convert it into radians
 * - Create second array that contain all of the points rotated. 
 * - Display both input and output points
 */
package ExtRotation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author boram, Phuong 
 */
public class Rotate2D 
{
    
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
        if (args.length == 2)
        {
            filename = args[1]; // take filename as an array
            System.out.println("Opening file: " + filename);
            Point2D[] points = readFile(filename);
            pointsList = new ArrayList<>(Arrays.asList(points)); 
          
        }
        else if (args.length < 1)  // else read input file
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
                Point2D[] points = readFile(filename);
                pointsList = new ArrayList<>(Arrays.asList(points)); 
                // Above) what's inside the bin file is an array of Point2D, 
                // so convert it into array then convert that array ArrayList 
                // of Pont2D
            }
            else if (response.equals("N") || response.equals("n"))
            {
                System.out.println("No points are entered.");
                System.out.println("Enter your list of points.");
                System.out.println("Enter a non-numeric value to stop.");

                double xPoint = 0.0, yPoint = 0.0; 
                pointsList = new ArrayList<>();

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
            }
            
        }
        // When not reading from a file         
        // the codes inside this else block is same as the ones in the block 
        // above. I haven't yet found a way to avoid the redundancy.
        else
        {
            System.out.println("No points are entered.");
            System.out.println("Enter your list of points.");
            System.out.println("Enter a non-numeric value to stop.");

            double xPoint = 0.0, yPoint = 0.0; 
            pointsList = new ArrayList<>();

            boolean repeat = true;
            do 
            {
                for (int i = 1; i <= 30 ; i++ ) 
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
                    yPoint = 0.0;
                }
            }while(repeat);
        }
            System.out.print("Please enter the angle of rotation ");
            System.out.print("(-360 <= angle <= 360): ");
            Scanner angleInput = new Scanner(System.in);
            double angle = 0.0;
            if (angleInput.hasNextDouble())
                angle = angleInput.nextDouble();
            Double radian = angle * (Math.PI / 180);

            // show entered points
            System.out.println("entered points: ");  
            for (Point2D p: pointsList) // printing 
                System.out.println("(" + twoDP.format(p.xPoint) + ", " 
                        + twoDP.format(p.yPoint) + ") ");
            arr = new Point2D[pointsList.size()]; 
            convertedArr = new Point2D[pointsList.size()]; 
            arr = pointsList.toArray(arr);

            // show rotated points
            System.out.println("points rotated " + angle + " degrees: ");
            for (int i = 0; i < pointsList.size(); i++)
            {
                Point2D point = arr[i];
                Point2D convteredPoint = (new Point2D()).rotator(point, 
                        radian); 
                // Above) The method is not to be a static (if it's static 
                // it won't be serializable) so needed to create an object 
                // to call the method
                convertedArr[i] = convteredPoint;
            }
            // show rotated points
            
            for (Point2D p: convertedArr)
                System.out.println("(" + twoDP.format(p.xPoint) + ", " 
                        + twoDP.format(p.yPoint) + ")");   

            
            // ask if the user wants to save the original and/or 
            // rotated points into a binary file
            System.out.println("Save original points as binary file?");
            Scanner newInput = new Scanner(System.in); // made a new scanner 
            //because the previous scanner retains the non-numeric value used to
            //get out of the x/y coordinate question
            String response2 = newInput.next();
            if (response2.equals("Y") || response2.equals("y")) 
            {
                saveFile(arr);
                response2 = null;  // erase what's written to recycle the var       
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
    public static Point2D[] readFile(String fname) 
    {
        FileInputStream readFileStream = null;
        ObjectInputStream inputStream = null;
 
        // read file
        try
        {
            readFileStream = new FileInputStream(fname);
            inputStream = new ObjectInputStream(readFileStream);
        }
        catch (IOException e)
        {
            System.out.println("Error opening input file " 
                    + fname + ".");
            System.exit(0);
        }
        Point2D[] readOne = null; // destroy old reaOne object
        try
        {
            readOne = (Point2D[]) inputStream.readObject();
            inputStream.close();
        }
        catch(Exception e)
        {
            System.out.println("Error reading from file " 
                    + fname + ".");
            System.exit(0);
        }
        return readOne;
    }
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
        Point2D[] points = obj;  //
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
