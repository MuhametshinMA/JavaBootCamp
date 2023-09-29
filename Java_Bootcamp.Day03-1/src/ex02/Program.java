package ex02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        String[] parameters = parseParameters(args);

        int arraySize = Integer.parseInt(parameters[0]);
        int threadsCount = Integer.parseInt(parameters[1]);

        int[] array = new int[arraySize];
        Arrays.fill(array, 1);

        int sumInMainThread = 0;
        for (int item : array) {
            sumInMainThread += item;
        }

        System.out.println("Sun: " + sumInMainThread);

        List<Thread> threads = new ArrayList<>();
        try {
            threads = setIndexes(array, arraySize, threadsCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (threads.size() != threadsCount) {
            throw new IllegalArgumentException();
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int sum = 0;
        for (Thread thread : threads) {
            if (thread instanceof ThreadArrSumCalc) {
                ThreadArrSumCalc threadArrSumCalc = (ThreadArrSumCalc) thread;
                sum += threadArrSumCalc.getSum();
            }
        }
        System.out.println("Sum by threads: " + sum);
    }

    public static List<Thread> setIndexes(int[] array, int arraySize, int threadsCount) throws IllegalArgumentException {
        if (threadsCount > arraySize) {
            throw new IllegalArgumentException();
        }

        int indexInterval = arraySize / threadsCount;
        int remainingIndexes = arraySize % threadsCount;

        int startIndex = 0;
        int endIndex;

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadsCount; i++) {
            endIndex = startIndex + indexInterval - 1;

            if (remainingIndexes > 0) {
                endIndex++;
                remainingIndexes--;
            }

            threads.add(new ThreadArrSumCalc(i + 1, array, startIndex, endIndex));
            startIndex = endIndex + 1;
        }
        return threads;
    }

    private static String[] parseParameters(String[] args) {
        String[] parameters = new String[2];
        parameters[0] = args[0].split("=")[1];
        int arraySize = Integer.parseInt(parameters[0]);
        if (arraySize <= 0 || arraySize > 2000000) {
            throw new IllegalArgumentException();
        }

        parameters[1] = args[1].split("=")[1];
        int threadsCount = Integer.parseInt(parameters[1]);
        if (threadsCount <= 0 || threadsCount > arraySize) {
            throw new IllegalArgumentException();
        }
        return parameters;
    }
}
