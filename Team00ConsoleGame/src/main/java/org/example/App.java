package org.example;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        char[][] matrix = {
//                {'#', ' ', '#', '#', '#'},
//                {' ', '#', ' ', ' ', ' '},
//                {'o', ' ', '0', ' ', '#'},
//                {'X', ' ', ' ', '#', ' '},
//                {'X', ' ', ' ', ' ', ' '},
//                {' ', 'X', ' ', '#', ' '},
//                {'#', ' ', ' ', '#', '#'},
//                {' ', '#', ' ', ' ', ' '},
//                {'#', '#', ' ', '#', ' '}
//        };
//
//        int startX = 1; // Начальные координаты X
//        int startY = 3; // Начальные координаты Y
//
//        int endX = 2; // Конечные координаты X
//        int endY = 8; // Конечные координаты Y
//
//        List<int[]> shortestPath = findShortestPath(matrix, startX, startY, endX, endY);
//
//        if (shortestPath != null) {
//            System.out.println("Кратчайший путь:");
//            for (int[] point : shortestPath) {
//                System.out.println("(" + point[0] + ", " + point[1] + ")");
//            }
//        } else {
//            System.out.println("Невозможно найти путь.");
//        }

        MapCreator mapCreator = new MapCreator(5, 30, 9);
        mapCreator.createMap();

        mapCreator.printMap();

        PathFinder pathFinder = new PathFinder(mapCreator);
        pathFinder.getPath();
    }

//    private static List<int[]> findShortestPath(char[][] matrix, int startX, int startY, int endX, int endY) {
//        int rows = matrix.length;
//        int columns = matrix[0].length;
//
//        Queue<int[]> queue = new LinkedList<>();
//        boolean[][] visited = new boolean[rows][columns];
//        int[][] parent = new int[rows][columns];
//
//        queue.offer(new int[]{startX, startY});
//        visited[startX][startY] = true;
//
//        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Возможные направления перемещения (вверх, вниз, влево, вправо)
//
//        while (!queue.isEmpty()) {
//            int[] current = queue.poll();
//            int currentX = current[0];
//            int currentY = current[1];
//
//            if (currentX == endX && currentY == endY) {
//                break; // Достигнута конечная точка
//            }
//
//            for (int[] direction : directions) {
//                int nextX = currentX + direction[0];
//                int nextY = currentY + direction[1];
//
//                if (nextX >= 0 && nextX < rows && nextY >= 0 && nextY < columns && matrix[nextX][nextY] == ' ' && !visited[nextX][nextY]) {
//                    queue.offer(new int[]{nextX, nextY});
//                    visited[nextX][nextY] = true;
//                    parent[nextX][nextY] = currentX * columns + currentY; // Запоминаем родительскую точку для восстановления пути
//                }
//            }
//        }
//
//        if (!visited[endX][endY]) {
//            return null; // Невозможно достичь конечную точку
//        }
//
//        // Восстановление кратчайшего пути
//        List<int[]> shortestPath = new ArrayList<>();
//        int currentX = endX;
//        int currentY = endY;
//
//        while (currentX != startX || currentY != startY) {
//            shortestPath.add(0, new int[]{currentX, currentY});
//            int parentX = parent[currentX][currentY] / columns;
//            int parentY = parent[currentX][currentY] % columns;
//            currentX = parentX;
//            currentY = parentY;
//        }
//
//        shortestPath.add(0, new int[]{startX, startY});
//
//        return shortestPath;
//    }
}
