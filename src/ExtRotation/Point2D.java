/*
 * ExtRotation package contains Point2D class and Rotate2D class
 */
package ExtRotation;

import java.io.Serializable;

/**
 * Point2D.java
 * Point2D class
 * Takes type double values for x and y coordinates as parameters and
 * rotate them in the angle specified by the user
 * 
 * @author Boram Kim, Phuong Tran
 * @version 1.3
 * Compiler: Java 1.8 <br>
 * OS: Windows 10 <br>
 * Hardware: HP Laptop <br>
 * 
 * Log:
 * 04/10/2017 BK version 1 completed
 * 04/11/2017 PT fixed rotator method (using array)
 * 04/13/2017 BK fixed accessor and mutator
 * 04/14/2017 BK discarded using ArrayList for rotated points; use Phuong's
 *               rotator method that uses a conventional array
 */
public class Point2D implements Serializable 
{ // serializable imprementation is needed for reading/writing to a file

    double yPoint = 0.0;
    double xPoint = 0.0;
   
    
    /**
     * default constructor
     */
    public Point2D() 
    {
        xPoint = yPoint = 0.0;
    }
    /**
     * Constructor that takes x & y points
     * @param x type double value for x coordinates
     * @param y type double value for y coordinates
     */
    public Point2D(Double x, Double y) {
        xPoint = x;  // not using 'this.'
        yPoint = y;
    }
    /**
     * constructor that takes only x point (and set y to 0)
     * not used
     * @param x type double of x point value
     */
    public Point2D(Double x) // not working as I expected
    {
        xPoint = x;
        yPoint = 0.0;
    }
    /**
     * accessors for xPoint variable
     * @return type double value of x point
     */
    public double getX() {
        return xPoint;
    }
    /**
     * accessors for yPoint variable
     * @return type double value of y point
     */
    public double getY() {
        return yPoint;
    }
// Discarted rotator method that returns ArrayList
//    public ArrayList<Point2D> rotator(ArrayList<Point2D> pointsList,  
//            double theta)   
//    {
//        ArrayList<Point2D> rotatedPoints = new ArrayList<>();
//        for (Point2D pt: pointsList) 
//        {   
//            xPrime = (pt.xPoint *  Math.cos(theta) 
//                    - pt.yPoint * Math.sin(theta));
//            yPrime = (pt.xPoint *  Math.sin(theta) ) 
//                    + (pt.yPoint * Math.cos(theta));
//            Point2D sets = new Point2D(xPrime, yPrime); 
//            rotatedPoints.add(sets);
//        }
//        return rotatedPoints;
//    }
    /**
     * rotator method takes Point2D object that contains x and y coordinates
     * and radian angle; it computes and returns rotated x and y coordinates
     * in a form of Point2D object
     * @param point x,y coordinates taken from files or keyboard
     * @param theta angle in radian
     * @return Point2D object of rotated x and y coordinates
     */
    public  Point2D rotator(Point2D point, double theta)
    {
        double newX = point.xPoint * Math.cos(theta) 
                - point.yPoint * Math.sin(theta);
        double newY = point.xPoint * Math.sin(theta)
                + point.yPoint * Math.cos(theta);

        return new Point2D(newX, newY);
    }
    /**
     * override toString method; it takes in type double xPoint and yPoint, 
     * convert them to String and puts parentheses and a comma
     * @return string object of x and y points 
     */
   @Override
   public String toString()
   {
       String xPointStr = String.valueOf(xPoint);
       String yPointStr = String.valueOf(yPoint);
       String pointsSetStr = "(" + xPointStr + ", " + yPointStr + ")";
       
       return pointsSetStr;
   }
}
