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

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author boram
 */
public class Rotate2D 
{
    public static void main(String[] args)
    {
        if (args.length < 2)
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
            
            //if (textQn.equals('Y') || textQn.equals('y'))
            // ask file input
            if (textQn.equals("N") || textQn.equals("n"))
            {
                System.out.println("No points are entered.");
                System.out.println("Enter your list of points.");
                System.out.println("Enter a non-numeric value to stop.");
                double xPoint = 0;
                double yPoint = 0;
                ArrayList<Double> pointsList = new ArrayList<>();
                //while (xPoint is not non-nemeric str || yPoint is not...)
                //{    
                //  for (int i = 1; i >= 1 && is numeric str; i++ )
                //  {
                //  System.out.print("Enter x coordinate of point " + i + ": ");
                //  xPoint = input.nextDouble();
                // 
                //  System.out.print("Enter y cocordinate of point " + i + ": ");
                //  yPoint = input.nextDouble();
                //  Point2D p = new Point2D();
                //  pointsList.add(p.toString(xPoint, yPoint)); // would it work?
                //  }
                //}
                
                // transfer the ArrayList to Print2D class
            }
            
               
        }
        
    }
    
}
