public class Program {
  public static void main(String[] args) {
    int num = 479598;
    int reminder;
    int divided = num;
    int sum = 0;
    while (divided > 10) {
      reminder = divided % 10;
      sum += reminder;
      divided = divided / 10;
    }
    sum += divided;
    System.out.println(sum);
  }
}