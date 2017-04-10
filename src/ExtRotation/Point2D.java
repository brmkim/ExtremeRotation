/*
 * This class should contain -- 3 constructores (1: default (0, 0), 2: correctly
 * read input (ex: (2, 3)), 3: one that sets non input to 0 (ex: (12, 0)),
 * accessors, mutators, toString method, and a rotation method.
 */
package ExtRotation;

/**
 *
 * @author boram
 */
public class Point2D 
{
    private double yPoint = 0.0;
    private double xPoint = 0.0;
    private double point = 0.0;

    public Point2D(double xPoint, double yPoint) // not using it yet
    {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
    }

    public Point2D(double point) 
    {
        this.point = point;
    }

     
   public String toString()
   {
       String xPointStr = String.valueOf(xPoint);
       String yPointStr = String.valueOf(yPoint);
       String pointsSetStr = xPointStr + ", " + yPointStr;
       
       return pointsSetStr;
   }
    
}
