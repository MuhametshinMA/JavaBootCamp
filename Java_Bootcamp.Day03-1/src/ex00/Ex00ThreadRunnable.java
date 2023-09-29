package ex00;

public class Ex00ThreadRunnable implements Runnable {

    String name;
    int countMessages = 0;
    int delay = 10;

    Ex00ThreadRunnable(String name, int countMessages, int delay) {
        this.name = name;
        this.countMessages = countMessages;
        this.delay = delay;
    }

    @Override
    public void run() {
        for (int i = 0; i < countMessages; i++) {
            System.out.println("Hen");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
