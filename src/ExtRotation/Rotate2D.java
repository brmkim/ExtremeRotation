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
        
        Point2D p2d = null;
        if (args.length < 2)  // else read input file
        {
            System.out.print("Would you like to read from a text file? (y/n) ");
            Scanner input = new Scanner(System.in);
            String textQn = input.next();
            // prompt until correct character is entered
            while (!(textQn.equals("Y") || textQn.equals("y") || textQn.equals("N") 
                    || textQn.equals("n")) )
            {
                System.out.print("Would you like to read from a text file? (y/n) ");
                textQn = input.next();
            }
            
            if (textQn.equals("Y") || textQn.equals("y"))
            {// ask file input
                System.out.print("Enter the file name: ");
                String filename = input.next();
                ReadFile newRead = new ReadFile(filename);
                
             }
                        
            else if (textQn.equals("N") || textQn.equals("n"))
            {
                System.out.println("No points are entered.");
                System.out.println("Enter your list of points.");
                System.out.println("Enter a non-numeric value to stop.");
               
                double xPoint = 0.0, yPoint = 0.0; 
                ArrayList<Point2D> pointsList = new ArrayList<>();
                
                boolean repeat = true;
                do 
                {
                    for (int i = 1; i <= 30 ; i++ ) // the purpose of this for loop is to keep track of point sets entered
                    {
                        System.out.print("Enter x coordinate of point " + i + ": ");
                        if (input.hasNextDouble())  
                            xPoint = input.nextDouble();
                        else
                        {
                            repeat = false;
                            break;
                        }
                        System.out.print("Enter y cocordinate of point " + i + ": ");
                        yPoint = 0.0; // reinitializing to 0 in case this value is not entered
                        if (input.hasNextDouble())
                            yPoint = input.nextDouble();
                        else
                        {
                            repeat = false;
                            
                        }
                        p2d = new Point2D(xPoint, yPoint);                   
                        pointsList.add(p2d);
                    }
                }while(repeat);
                
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
                    System.out.println("(" + p.xPoint + ", " + p.yPoint + ") ");
                Point2D[] arr = new Point2D[pointsList.size()]; 
                Point2D[] convertedArr = new Point2D[pointsList.size()]; 
                arr = pointsList.toArray(arr);
               
                System.out.println("points rotated " + angle + " degrees: ");
                for (int i = 0; i < pointsList.size(); i++)
                {
                    Point2D point = arr[i];
                    Point2D convteredPoint = Point2D.rotator(point, radian);
                    convertedArr[i] = convteredPoint;
                }
                // show rotated points
                DecimalFormat twoDP = new DecimalFormat("###0.0##");
                for (Point2D p: convertedArr)
                    System.out.println("(" + twoDP.format(p.xPoint) + ", " 
                            + twoDP.format(p.yPoint) + ")");   
            }
            // save, write file
        }
   
    }  
}
