package org.example;

import java.util.Random;

import org.fusesource.jansi.AnsiConsole;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
//        AnsiConsole.systemInstall();

        Random randomBoolean = new Random();

        for (; ; ) {
//            System.out.print("\u001b[2J");
            System.out.print("\033[H\033[J");
            System.out.flush();

            try {

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        System.out.print(randomBoolean.nextBoolean() + "\t");
                    }
                    System.out.println();

                }
                System.out.println();

                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }
}
