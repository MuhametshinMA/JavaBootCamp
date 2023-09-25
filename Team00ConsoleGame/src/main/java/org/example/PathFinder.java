package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PathFinder {
    private MapCreator mapCreator;

    public PathFinder(MapCreator mapCreator) {
        this.mapCreator = mapCreator;
    }

    public void getPath() throws IllegalArgumentException {
        int[][] matrix = mapCreator.getMap();
        Pair<Integer, Integer> startPosPair = findCharacterPosition(matrix, 'o');
        Pair<Integer, Integer> endPosPair = findCharacterPosition(matrix, '0');
        findShortestPath(matrix, startPosPair, endPosPair);
    }

    private void findShortestPath(int[][] matrix, Pair<Integer, Integer> startPosPair, Pair<Integer, Integer> endPosPair) {

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        queue.offer(startPosPair);
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> current = queue.poll();
            System.out.println(current.getLeft() + " " + current.getRight());
            List<Pair<Integer, Integer>> listWays = findPossibleWays(matrix, current);
            for (Pair<Integer, Integer> way : listWays) {
                System.out.println("possible way: " + way.getLeft() + " " + way.getRight());
            }
        }
    }

    private List<Pair<Integer, Integer>> findPossibleWays(int[][] matrix, Pair<Integer, Integer> current) {
        List<Pair<Integer, Integer>> result = new LinkedList<>();
        if (current.getLeft() > 0 && matrix[current.getLeft() - 1][current.getRight()] == 32) {
            result.add(new Pair<>(current.getLeft() - 1, current.getRight()));
        }
        if (current.getLeft() < matrix.length - 1 && matrix[current.getLeft() + 1][current.getRight()] == 32) {
            result.add(new Pair<>(current.getLeft() + 1, current.getRight()));
        }
        if (current.getRight() > 0 && matrix[current.getLeft()][current.getRight() - 1] == 32) {
            result.add(new Pair<>(current.getLeft(), current.getRight() - 1));
        }
        if (current.getRight() < matrix[0].length - 1 && matrix[current.getLeft()][current.getRight() + 1] == 32) {
            result.add(new Pair<>(current.getLeft(), current.getRight() + 1));
        }
        return result;
    }

    private Pair<Integer, Integer> findCharacterPosition(int[][] matrix, char character) throws IllegalArgumentException {
        Pair<Integer, Integer> result = null;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == character) {
                    result = new Pair<>(i, j);
                    return result;
                }
            }
        }
        throw new IllegalArgumentException("Character not found");
    }

    public void printMap() {
        int[][] map = mapCreator.getMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print((char) map[i][j]);
            }
            System.out.println();
        }
    }
}
