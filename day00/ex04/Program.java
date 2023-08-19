import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    // Ввод текста с консоли
    Scanner scanner = new Scanner(System.in);
    System.out.print("-> ");
    String charSequence = scanner.nextLine();
    scanner.close();

    int[] charArray = getCharRating(charSequence);
    // int[]
    printCharRating(charArray);
    drawGraphRating(charArray);
  }

  public static void drawGraphRating(int[] charArray) {

  }

  public static void printCharRating(int[] charArray) {
    for (int i = 0; i < charArray.length; ++i) {
      System.out.println("code: " + i + " char: " + (char) (i + 32) + ": " + charArray[i]);
    }
  }

  public static int[] getCharRating(String chars) {
    int[] charArray = new int[95];
    int charLength = chars.length();
    for (int i = 0; i < charLength; ++i) {
      ++charArray[(int) chars.charAt(i) - 32];
    }
    return charArray;
  }
}
