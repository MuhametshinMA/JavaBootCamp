package ex01;

import java.util.LinkedList;
import java.util.List;

public class Consumer implements Runnable {

    private List<Integer> buffer;
    int delay = 10;

    int count;

    public Consumer(List<Integer> buffer, int delay, int count) {
        this.buffer = buffer;
        this.delay = delay;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void consume() throws InterruptedException {
        synchronized (buffer) {
            while (buffer.isEmpty()) {
                buffer.wait();
            }

            int value = 0;
            if (buffer instanceof LinkedList) {
                value = ((LinkedList<Integer>) buffer).removeFirst();
            }
            System.out.println("Hen");
            buffer.notifyAll();
            Thread.sleep(delay);
        }
    }
}
