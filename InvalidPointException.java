/**
 * Error thrown when trying to create an invalid point or
 * add a point a path has already been to 
 *
 * @author Alexander Hammond
 * @version 1.0
 */
public class InvalidPointException extends RuntimeException {
    public InvalidPointException(String msg) {
        super(msg);
    }
    
    public InvalidPointException(int xCor, int yCor) {
        super(xCor + ", " + yCor);
    }
}
