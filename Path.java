import java.util.ArrayList;

/**
 * Represents a path along a grid 
 *
 * @author Alexander Hammond
 * @version 1.0
 */
public class Path {
    private ArrayList<Point> history;
    private Point location;
    private int boundary;
        
    //only used for creating the initial node
    public Path(int startX, int startY, int boundary) {
        this.boundary = boundary;
        location = new Point(startX, startY, boundary);
        history = new ArrayList<Point>();
    }
    
    public Path(Point location, ArrayList<Point> history, int boundary) {
        if (history.contains(location)) {
            throw new InvalidPointException("point in history");
        }
        this.boundary = boundary;
        this.history = history;
        this.location = location;
    }

    public boolean equals(Object o) {
        if (o != null && o instanceof Path) {
            if (o == this) {
                return true;
        }
        Path other = (Path) o;
            if (other.getLocation().equals(this.location)) {
                return true;
            }
        }
        return false;
    }

    public Point getLocation() {
        return location;
    }

    public int distanceFromPoint(int xCor, int yCor) {
        int xDistance;
        int yDistance;
        if (xCor > location.getX()) {
            xDistance = xCor - location.getX();
        }
        else {
            xDistance = location.getX() - xCor;
        }
        if (yCor > location.getY()) {
            yDistance = yCor - location.getY();
        }
        else {
            yDistance = location.getY() - yCor;
        }
        return xDistance + yDistance;
    }

    public ArrayList<Point> getHistory() {
        return history;
    }

    public boolean contains(Point point) {
        return history.contains(point);
    }
    
    public ArrayList<Path> getSurroundingPath() {
        ArrayList<Path> list = new ArrayList<Path>();
        int x = location.getX();
        int y = location.getY();
        ArrayList<Point> newHistory = new ArrayList<Point>();
        newHistory.addAll(history);
        newHistory.add(location);
        try {
        Path one = new Path(new Point(x, y - 1, boundary),
               newHistory, boundary);
        list.add(one);
        }
        catch (InvalidPointException e) {
        }
        try {
        Path two = new Path(new Point(x - 1, y, boundary),
                newHistory, boundary);
        list.add(two);
        }
        catch (InvalidPointException e) {
        }
        try {
        Path three = new Path(new Point(x + 1, y, boundary),
                newHistory, boundary);
        list.add(three);
        }
        catch (InvalidPointException e) {
        }
        try {
        Path four = new Path(new Point(x, y + 1, boundary),
                newHistory, boundary);
        list.add(four);
        }
        catch (InvalidPointException e) {
        }
        return list;
    }

    public void printPath(int[][] map) {
        for (Point step : history) {
            map[step.getY()][step.getX()] = 4;
            step.printPoint();
        }
        map[location.getY()][location.getX()] = 4;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(" " + map[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("Path Length: " + history.size());
    }

    public void printLocation() {
        location.printPoint();
    }
}
