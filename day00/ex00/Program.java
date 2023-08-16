public class Program {
  public static void main(String[] args) {
    int num = 479598;
    int divided = num;
    int sum = 0;
    while (divided > 0) {
      sum += divided % 10;
      divided = divided / 10;
    }
    System.out.println(sum);
  }
}