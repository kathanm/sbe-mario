package com.sbe.mario.util;

import com.sbe.mario.constant.Constants;
import com.sbe.mario.model.Move;
import com.sbe.mario.model.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Util class for mario application
 */
public class Utils {

    /**
     * Find the location of a string in the grid
     * @param s string to find
     * @param grid list of strings representing the grid, with the first string in the list being row 0
     * @return x-y coordinate of first occurrence found of string
     */
    public static Point findString(String s, List<String> grid) {
        for (int y = 0; y < grid.size(); y++) {
            String row = grid.get(y);
            int x = row.indexOf(s);
            // if string is not present in the row, continue to next row
            if (x == -1) {
                continue;
            } else {
                return new Point(x, y);
            }
        }
        return null;
    }

    /**
     * Find a path from one point to another while avoiding hazards using BFS
     * @param src source point
     * @param dst destinaton point
     * @param grid grid
     * @return list of moves representing path from src to dst
     */
    public static List<Move> findPath(Point src, Point dst, List<String> grid) {
        // initialize visited and queue
        Set<Point> visited = new HashSet<>();
        Queue<List<Point>> queue = new LinkedList<>();

        // add initial source node to queue
        queue.add(List.of(src));

        while (!queue.isEmpty()) {
            // pop from queue
            List<Point> path = queue.remove();
            Point head = path.get(path.size() - 1);

            // find adjacent nodes
            Point above = new Point(head.getX(), head.getY() + 1);
            Point below = new Point(head.getX(), head.getY() - 1);
            Point left = new Point(head.getX() - 1, head.getY());
            Point right = new Point(head.getX() + 1, head.getY());

            // check if any points are destination or else add to queue
            if (checkPath(dst, grid, visited, queue, path, List.of(above, below, left, right))) {
                return getPath(path);
            }
        }

        return Collections.emptyList();

    }

    private static boolean checkPath (Point dst, List<String> grid, Set<Point> visited, Queue<List<Point>> queue, List<Point> path, List<Point> points) {
        for (Point point: points) {
            char val = findVal(point, grid);
            if (!visited.contains(point)) {
                if (point.equals(dst)) {
                    path.add(point);
                    return true;
                } else if (val != '0' && val != Constants.HAZARD) {
                    List<Point> clonedPath = new ArrayList<>(path);
                    clonedPath.add(point);
                    queue.add(clonedPath);
                    visited.add(point);
                }
            }
        }
        return false;
    }

    /**
     * Get the value of a point on a grid
     * @param point point to get value of
     * @param grid the grid
     * @return value of point in the grid
     */
    public static char findVal(Point point, List<String> grid) {
        return findVal(point.getX(), point.getY(), grid);
    }

    /**
     * Get the valur of a point on a grid
     * @param x x-coordinate of point
     * @param y y-coordinate of point
     * @param grid the grid
     * @return value of point in the grid
     */
    public static char findVal(int x, int y, List<String> grid) {
        if (grid.isEmpty()) {
            return '0';
        }
        if (y < 0 || y > grid.size() - 1 || x < 0 || x > grid.get(0).length() - 1) {
            return '0';
        }
        return grid.get(y).charAt(x);
    }

    private static List<Move> getPath(List<Point> path) {
        List<Move> moves = new ArrayList<>();

        if (path == null ||  path.size() < 2) {
            return Collections.emptyList();
        }

        for (int i = 0; i < path.size() - 1; i++) {
            moves.add(calculateDirection(path.get(i), path.get(i+1)));
        }

        return moves;
    }

    private static Move calculateDirection(Point p1, Point p2) {
        // assumes input is correct and points are adjacent and 1 unit away from each other
        if (p1.getX() == p2.getX()) {
            return p1.getY() + 1 == p2.getY() ? Move.UP : Move.DOWN;
        }

        return p1.getX() + 1 == p2.getX() ? Move.RIGHT : Move.LEFT;
    }
}
