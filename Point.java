/**
 * Represents a point on a grid 
 *
 * @author Alexander Hammond
 * @version 1.0
 */
public class Point {
    private int x;
    private int y;
    private int boundary;
    public Point(int xCor, int yCor, int boundaryIndex) {
        boundary = boundaryIndex;
        if (xCor < 0 || yCor < 0 || xCor > boundary || yCor > boundary) {
            throw new InvalidPointException(xCor, yCor);
        }
        x = xCor;
        y = yCor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object o) {
        if (o != null && o instanceof Point) {
            if (o == this) {
                return true;
            }
            Point other = (Point) o;
            if (other.x == this.x && other.y == this.y) {
                return true;
            }
        }
        return false;
    }
        
    public int hashCode() {
        int hash = 3;
        hash = (53 * hash * x) + y;
        return hash;
    }

    public void printPoint() {
        System.out.println("X: " + x + "  Y: " + y);
    }
}

