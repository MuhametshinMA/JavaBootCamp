package ex00;

public class Program {
    public static void main(String[] args) {
        String parameters = parseParameters(args);
        int count = Integer.parseInt(parameters);
        Thread eggThread = new Ex00Thread("Egg", count, 10);
        Thread henThread = new Thread(new Ex00Thread("Hen", count, 10));

        henThread.start();
        eggThread.start();

        try {
            henThread.join();
            eggThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executeMainThread(count);
    }

    private static String parseParameters(String[] args) {
        String[] parameters = args[0].split("=");
        return parameters[1];
    }

    private static void executeMainThread(int countMessages) {
        for (int i = 0; i < countMessages; i++) {
            System.out.println("Human");
        }
    }
}
