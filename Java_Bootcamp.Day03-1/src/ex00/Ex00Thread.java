package ex00;

public class Ex00Thread extends Thread {

    String name;
    int countMessages = 0;
    int delay = 10;

    Ex00Thread(String name, int countMessages, int delay) {
        this.name = name;
        this.countMessages = countMessages;
        this.delay = delay;
    }

    @Override
    public void run() {
        for (int i = 0; i < countMessages; i++) {
            System.out.println(name);
            try {
                sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
