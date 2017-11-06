import java.util.ArrayList;

/**
 * A PathFinder that when created with start and end coordinates
 * is able to determine the fastest path assuming no obstacles and
 * equal movement cost for every step
 *
 * @author Alexander Hammond
 * @version 1.0
 */
public class PathFinder {
    public static ArrayList<Path> accessedPath= new ArrayList<Path>();
    private int[][] map;
    private ArrayList<Path> paths;
    private int beginX;
    private int beginY;
    private int goalX;
    private int goalY;

    public static void main(String[] args) {
        int startX = Integer.parseInt(args[0]);
        int startY = Integer.parseInt(args[1]);
        int endX = Integer.parseInt(args[2]);
        int endY = Integer.parseInt(args[3]);
        PathFinder path = new PathFinder (startX, startY, endX, endY);
        Path result = path.run();
        result.printPath(path.getMap());
        System.out.println(path.paths.size());
    }

    public PathFinder (int startX, int startY, int endX, int endY) {
        if (!(startX >= 0 && startY >= 0 && endX >= 0 && endY >= 0)) {
            throw new RuntimeException("Please use valid indexes (>=0)");
        }
        
        beginX = startX;
        beginY = startY;
        goalX = endX;
        goalY = endY;
        int largest = startX;
        if (startY > largest) {
            largest = startY;
        }
        if (endY > largest) {
            largest = endY;
        }
        if (endX > largest) {
            largest = endX;
        }
        map = makeMap(startX, startY, endX, endY);
        Path firstPath = new Path(startX, startY, largest);
        paths = new ArrayList<Path>();
        paths.add(firstPath);
    }

    //returns an array of points that represents the fastest path
    public Point[] getPath() {
        Path fastestPath = run();
        Point[] path = new Point[fastestPath.getHistory().size() + 1];
        int count = 0;
        for (Point point : fastestPath.getHistory()) {
            path[count++] = point;
        }
        return path;
    }

    public int[][] makeMap(int startX, int startY, int endX, int endY) {
        int largest = startX;
        if (startY > largest) {
            largest = startY;
        }
        if (endX > largest) {
            largest = endX;
        }
        if (endY > largest) {
            largest = endY;
        }
        largest++;
        int[][] theMap = new int[largest][largest];
        for (int i = 0; i < largest; i++) {
            for (int j = 0; j < largest; j++) {
                if (i == startY && j == startX) {
                   theMap[i][j] = 1; 
                }
                else if (i == endY && j == endX) {
                    theMap[i][j] = 2;
                }
                else {
                    theMap[i][j] = 0;
                }
            }
        }
        return theMap;
    }

    public int[][] getMap() {
        return this.map;
    }

    /**
     * The breadth first search algorithm that powers the path planner
     *
     * @return a Path that will contain the fastest path in its history
     */
    public Path run() {
        ArrayList<Path> result = new ArrayList<Path>();
        for (Path path : paths) {
            ArrayList<Path> pathsToAdd = path.getSurroundingPath();
            for (Path newPath: pathsToAdd) {
                if (newPath.distanceFromPoint(goalX, goalY) == 0) {
                    result.add(newPath);
                    paths = result;
                    return newPath;
                }
                if (!accessedPath.contains(newPath)) {
                    accessedPath.add(newPath);
                    result.add(newPath);
                }
            }
        }
        paths = result;
        printPath();
        return run();
    }

    public void printPath() {
        int[][] tmpMap = makeMap(beginX, beginY, goalX, goalY);
        for (Path path : paths) {
            tmpMap[path.getLocation().getY()][path.getLocation().getX()] = 4;
        }
        for (int i = 0; i < tmpMap.length; i++) {
            for (int j = 0; j < tmpMap[i].length; j++) {
                System.out.print(" " + tmpMap[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
