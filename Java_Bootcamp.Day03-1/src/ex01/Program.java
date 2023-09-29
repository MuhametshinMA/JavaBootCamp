package ex01;

import java.util.LinkedList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        List<Integer> buffer = new LinkedList<>();
        String parameters = parseParameters(args);
        int count = Integer.parseInt(parameters);

        Thread producerThread = new Thread(new Producer(buffer, 1, 10, count));
        Thread consumerThread = new Thread(new Consumer(buffer, 10, count));

        producerThread.start();
        consumerThread.start();
    }

    private static String parseParameters(String[] args) {
        String[] parameters = args[0].split("=");
        return parameters[1];
    }

}
