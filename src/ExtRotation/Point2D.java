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
   
   public String toString(double x, double y)
   {
       String xPoint = String.valueOf(x);
       String yPoint = String.valueOf(y);
       String pointsSet = xPoint + ", " + yPoint;
       
       return pointsSet;
   }
    
}
