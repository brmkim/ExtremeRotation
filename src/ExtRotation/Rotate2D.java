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
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author boram
 */
public class Rotate2D 
{
    public static void main(String[] args)
    {
        Point2D p2d = new Point2D();
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
            
            //if (textQn.equals('Y') || textQn.equals('y'))
            // ask file input
            if (textQn.equals("N") || textQn.equals("n"))
            {
                System.out.println("No points are entered.");
                System.out.println("Enter your list of points.");
                System.out.println("Enter a non-numeric value to stop.");
                double xPoint = 0.0;
                double yPoint = 0.0;
                double point = 0.0;
                double angle = 0.0;
                ArrayList<Point2D> pointsList = new ArrayList<>();
                boolean repeat = true;
                do 
                {
                    for (int i = 1; i >= 1 ; i++ )
                    {
                        System.out.print("Enter x coordinate of point " + i + ": ");
                        Scanner xInput = new Scanner(System.in);
                        if (xInput.hasNextDouble())
                            xPoint = xInput.nextDouble();
                        else
                        {
                            repeat = false;
                            break;
                        }
                        
                        System.out.print("Enter y cocordinate of point " + i + ": ");
                        Scanner yInput = new Scanner(System.in);
                        if (yInput.hasNextDouble())
                        {
                            yPoint = yInput.nextDouble();
                            p2d = new Point2D(xPoint, yPoint);  
                        }
                        else
                        {
                            p2d = new Point2D(xPoint);
                            repeat = false;
                            break;
                        }
                                              
                        pointsList.add(p2d);
                        

                    }
                }while(repeat);
                
                System.out.print("Please enter the angle of rotation ");
                System.out.print("(-360 <= angle <= 360): ");
                Scanner angleInput = new Scanner(System.in);
                if (angleInput.hasNextDouble())
                    angle = angleInput.nextDouble();
                double radian = angle * (Math.PI / 180);
                // show entered points
                System.out.println("entered points: ");  
                for (Point2D p: pointsList) // printing 
                    System.out.print("<" + p.xPoint + ", " + p.yPoint + "> ");
                System.out.println();
                // show rotated points
                ArrayList<Point2D> rotated = p2d.rotator(pointsList, radian);
                System.out.println("points rotated " + angle + " degrees: ");
                 for (Point2D r: rotated)
                    System.out.print("<" + r.xPoint + ", " + r.yPoint + "> ");
                        
               
        }
       
    }
   
    }  
}
