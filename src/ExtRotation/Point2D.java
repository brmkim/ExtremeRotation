/*
 * This class should contain -- 3 constructores (1: default (0, 0), 2: correctly
 * read input (ex: (2, 3)), 3: one that sets non input to 0 (ex: (12, 0)),
 * accessors, mutators, toString method, and a rotation method.
 */
package ExtRotation;

import java.util.ArrayList;

/**
 *
 * @author boram
 */
public class Point2D 
{
    double yPoint = 0.0;
    double xPoint = 0.0;
    double point = 0.0;
    double xPrime = 0.0;
    double yPrime = 0.0;
    // default constructor
    public Point2D()
    {
        this(0, 0);
    }
    // constructor that takes x & y points
    public Point2D(double xPoint, double yPoint) // not using it yet
    {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
    }
    // constructor that takes only one point (and set the other to 0)
    public Point2D(double xPoint) 
    {
        this.xPoint = xPoint;  // not working as I imagined
        this.yPoint = 0.0;
    }
    // accessor - access x & y points in the ArrayList?
    // mutator - ?
    
    // Rotator
    public ArrayList rotator(ArrayList<Point2D> pointsList, double theta)
    {
        ArrayList<Point2D> rotatedPoints = new ArrayList<>();
        double[][] clockwise = { 
            {Math.cos(theta), -Math.sin(theta)},
            {Math.sin(theta), Math.cos(theta)} };
       
        
        for (Point2D pt: pointsList) 
        {   // some problem here that needs to be fixed
            xPrime = (pt.xPoint *  clockwise[0][0]) 
                    + (pt.yPoint * clockwise[0][1]);
            yPrime = (pt.xPoint * clockwise[1][0]) 
                    + (pt.yPoint * clockwise[1][1]);
            Point2D sets = new Point2D(xPrime, yPrime); 
            rotatedPoints.add(sets);
        }
        
        return rotatedPoints;
    }
     
//   public String toString()
//   {
//       String xPointStr = String.valueOf(xPoint);
//       String yPointStr = String.valueOf(yPoint);
//       String pointsSetStr = xPointStr + ", " + yPointStr;
//       
//       return pointsSetStr;
//   }
    
}
