import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int countCoffieRequests = 0;
    while (true) {
      System.out.print("-> ");

      int num = in.nextInt();
      if (num == 42) {
        break;
      }
      int sumNum = calcSumNum(num);

      if (checkPrimalNum(sumNum)) {
        ++countCoffieRequests;
      }
    }
    System.out.println("   Count of coffee-request - " + countCoffieRequests);
    in.close();
  }

  public static boolean checkPrimalNum(int num) {
    if (num < 2) {
      return false;
    }

    if (num == 2 || num == 3) {
      return true;
    }

    if (num % 2 == 0) {
      return false;
    } else {
      for (int i = 3; i < Math.sqrt(num); i += 2) {
        if (num % i == 0) {
          return false;
        }
      }
    }

    return true;
  }

  public static int calcSumNum(int val) {
    int sum = 0;
    while (val > 0) {
      sum += val % 10;
      val /= 10;
    }
    return sum;
  }
}
