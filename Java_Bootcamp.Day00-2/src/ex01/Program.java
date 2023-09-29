import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("-> ");
    int number = input.nextInt();
    input.close();
    if (number < 2) {

      System.err.println("   IllegalArgument");
      System.exit(-1);
    }

    int countLoop = 0;
    boolean isPrimal = true;

    if (number % 2 == 0) {
      ++countLoop;
      isPrimal = false;
    } else {
      for (int i = 3; i < Math.sqrt(number) + 1; i += 2) {
        ++countLoop;
        if (number % i == 0) {
          isPrimal = false;
          break;
        }
      }
    }

    System.err.println("   " + isPrimal + " " + countLoop);

  }
}
