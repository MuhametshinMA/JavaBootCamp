package ex02;

public class ThreadArrSumCalc extends Thread {

    private int id;
    private int[] array;
    private int startIndex;
    private int endIndex;

    private int sum;

    public ThreadArrSumCalc(int id, int[] array, int startIndex, int endIndex) {
        this.id = id;
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        for (int i = startIndex; i <= endIndex; i++) {
            sum += array[i];
        }
        System.out.println("Thread " + id + ": from " + startIndex + " to " + endIndex + " sum is " + sum);
    }

    public int getSum() {
        return sum;
    }
}
