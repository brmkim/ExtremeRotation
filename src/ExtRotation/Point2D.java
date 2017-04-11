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
    private double point = 0.0;
    private double radian = 0.0;

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
    public Point2D(double point) 
    {
        this.point = point;
        // some more code
    }
    // accessor - access x & y points in the ArrayList?
    // mutator - ?
    
    // Rotator
    public ArrayList rotator(ArrayList<Point2D> al, double theta)
    {
        // if statement: angle > 0 -- rotate counter clockwise 
        // else if angle < 0 -- rotate clockwise
        double[][] clockwise = { 
            {Math.cos(theta), -Math.sin(theta)},
            {Math.sin(theta), Math.cos(theta)} };
        double[][] counterCW = {
            {Math.cos(theta), Math.sin(theta)},
            {-Math.sin(theta), Math.cos(theta)} };
        
        // more code
        return al;
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
