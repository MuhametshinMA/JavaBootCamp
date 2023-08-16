import java.util.Scanner;

public class Program {
  public static boolean checkPrimalNumber(int number) {
    if (number < 2) {
      System.out.println("IllegalArgument");
      System.exit(1);
    }
    for (int i = 2; i < number / 2; i++) {
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int number = input.nextInt();

    if (number < 2) {
      System.out.println("-> " + number);
      System.out.println("   IllegalArgument");
      System.exit(1);
    }

    int countLoop = 0;
    boolean isPrimal = true;

    for (int i = 2; i < Math.sqrt(number) + 1; i++) {
      ++countLoop;
      if (number % i == 0) {
        isPrimal = false;
        break;
      }
    }

    System.out.println("-> " + number);
    System.err.println("   " + isPrimal + " " + countLoop);

  }
}