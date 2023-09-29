package ex01;

import java.util.List;

public class Producer implements Runnable {
    private List<Integer> buffer;
    private int capacity;
    private int delay = 10;

    private int count;

    public Producer(List<Integer> buffer, int capacity, int delay, int count) {
        this.buffer = buffer;
        this.capacity = capacity;
        this.delay = delay;
        this.count = count;
    }

    @Override
    public void run() {
        int value = 1;

        for (int i = 0; i < count; i++) {
            try {
                produce(value++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void produce(int value) throws InterruptedException {
        synchronized (buffer) {
            while (buffer.size() == capacity) {
                buffer.wait();
            }

            buffer.add(value);
            System.out.println("Egg");
            buffer.notifyAll();
            Thread.sleep(delay);
        }
    }
}
