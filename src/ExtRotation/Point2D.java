/*
 * This class should contain -- 3 constructores (1: default (0, 0), 2: correctly
 * read input (ex: (2, 3)), 3: one that sets non input to 0 (ex: (12, 0)),
 * accessors, mutators, toString method, and a rotation method.
 */
package ExtRotation;

import java.io.Serializable;

/**
 *
 * @author boram, Phuong
 */
public class Point2D implements Serializable 
{ // serializable imprementation is needed for reading/writing to a file

    double yPoint = 0.0;
    double xPoint = 0.0;
    double point = 0.0;

    // default constructor
    public Point2D() {
        xPoint = yPoint = 0.0;
    }

    // constructor that takes x & y points
    public Point2D(Double x, Double y) {
        xPoint = x;  // not using 'this.'
        yPoint = y;
    }

    // constructor that takes only one point (and set the other to 0)
    public Point2D(Double x) // not working as I expected
    {
        xPoint = x;
        yPoint = 0.0;
    }

    // accessors
    public double getX() {
        return xPoint;
    }

    public double getY() {
        return yPoint;
    }

// Putting the rotated points in the ArrayList was more efficient but i had a 
// small problem with it. So not using it. 
//    public ArrayList<Point2D> rotator(ArrayList<Point2D> pointsList,  
//            double theta)   
//    {
//        ArrayList<Point2D> rotatedPoints = new ArrayList<>();
//
//        for (Point2D pt: pointsList) 
//        {   
//            xPrime = (pt.xPoint *  Math.cos(theta) 
//                    - pt.yPoint * Math.sin(theta));
//            yPrime = (pt.xPoint *  Math.sin(theta) ) 
//                    + (pt.yPoint * Math.cos(theta));
//            Point2D sets = new Point2D(xPrime, yPrime); 
//            rotatedPoints.add(sets);
//        }
//        
//        return rotatedPoints;
//    }
    public  Point2D rotator(Point2D point, double theta)
    {
        double newX = point.xPoint * Math.cos(theta) 
                - point.yPoint * Math.sin(theta);
        double newY = point.xPoint * Math.sin(theta)
                + point.yPoint * Math.cos(theta);

        return new Point2D(newX, newY);
    }

    @Override
   public String toString()
   {
       String xPointStr = String.valueOf(xPoint);
       String yPointStr = String.valueOf(yPoint);
       String pointsSetStr = "(" + xPointStr + ", " + yPointStr + ")";
       
       return pointsSetStr;
   }
}
