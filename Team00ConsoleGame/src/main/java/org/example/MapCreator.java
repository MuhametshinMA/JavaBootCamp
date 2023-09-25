package org.example;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.*;

public class MapCreator {
    private int enemiesCount;
    private int wallsCount;
    private int mapSize;
    private int[][] map;

    public MapCreator(int enemiesCount, int wallsCount, int mapSize) {
        this.enemiesCount = enemiesCount;
        this.wallsCount = wallsCount;
        this.mapSize = mapSize;
        map = new int[mapSize][mapSize];
        initMap();
    }

    private void initMap() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = 32;
            }
        }
    }

    public int[][] getMap() {
        return map;
    }

    private int addPair(Set<Pair<Integer, Integer>> setCell, int x, int y, char character) {
        Pair<Integer, Integer> pair = new Pair<>(x, y);
        if (setCell.contains(pair)) {
//            System.out.println("Pair (" + x + ", " + y + ") already exists in the map.");
            return 0;
        }
        setCell.add(pair);
        map[x][y] = character;
        return 1;
    }

    public void printMap() {
        ColoredPrinter cp = new ColoredPrinter.Builder(1, false).build();
//        AnsiConsole.systemInstall();
        System.out.print("\033[H\033[J");
        System.out.flush();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map[i][j] == ' ') {
                    cp.setBackgroundColor(Ansi.BColor.YELLOW);
                    cp.print((char) map[i][j]);
                } else if (map[i][j] == '#') {
                    cp.setBackgroundColor(Ansi.BColor.MAGENTA);
                    cp.print((char) map[i][j]);
                } else if (map[i][j] == 'X') {
                    cp.setBackgroundColor(Ansi.BColor.RED);
                    cp.print((char) map[i][j]);
                } else if (map[i][j] == '0') {
                    cp.setBackgroundColor(Ansi.BColor.BLUE);
                    cp.print((char) map[i][j]);
                } else if (map[i][j] == 'o') {
                    cp.setBackgroundColor(Ansi.BColor.GREEN);
                    cp.print((char) map[i][j]);
                }
            }
            System.out.println();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    public void createMap() {
        initMap();
        Set<Pair<Integer, Integer>> setCell = new HashSet<>();

        addObjectToMap(setCell, wallsCount, '#');
        addObjectToMap(setCell, enemiesCount, 'X');
        addObjectToMap(setCell, 1, 'o');
        addObjectToMap(setCell, 1, '0');
    }

    public void addObjectToMap(Set<Pair<Integer, Integer>> setCell, int wallsCount, char character) {
        int countAdded = 0;

        while (countAdded < wallsCount) {
            Random random = new Random();
            int x = random.nextInt(mapSize);
            int y = random.nextInt(mapSize);
            countAdded += addPair(setCell, x, y, character);
        }
    }
}
