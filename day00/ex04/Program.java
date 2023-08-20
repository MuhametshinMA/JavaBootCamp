import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    // Ввод текста с консоли
    Scanner scanner = new Scanner(System.in);
    System.out.print("-> ");
    String charSequence = scanner.nextLine();
    scanner.close();

    int[] charArray = getCharRating(charSequence);
    int[][] charRatingMatrix = getNotNullChar(charArray);
    sortCharMatrix(charRatingMatrix);
    int[][] graphMatrix = createGraphMatrix(charRatingMatrix);

    // printMatrixCharsRating(charRatingMatrix);
    grawGraph(graphMatrix);
  }

  public static void grawGraph(int[][] matrix) {
    for (int r = 0; r < matrix.length; ++r) {
      for (int c = 0; c < matrix[r].length; ++c) {
        System.out.println(matrix[r][c]);
      }
    }
  }

  public static int[][] createGraphMatrix(int[][] matrix) {
    int lengthMatrix = 0;
    if (matrix.length > 10) {
      lengthMatrix = matrix.length;
    } else {
      lengthMatrix = 10;
    }
    int[][] graphMatrix = new int[lengthMatrix][12];
    for (int r = 0; r < graphMatrix.length; ++r) {
      for (int c = 0; c < graphMatrix[r].length; ++c) {
        graphMatrix[r][c] = 32;
      }
    }

    double sharpQuotient = matrix[0][1] / 10;
    for (int r = 0; r < matrix.length; ++r) {
      int countSharp = matrix[r][1] / (int) sharpQuotient;
      graphMatrix[r][0] = matrix[r][0];
      graphMatrix[r][countSharp] = matrix[r][1];
      // fillSharps()
    }
    return graphMatrix;
  }

  public static void sortCharMatrix(int[][] matrix) {
    Arrays.sort(matrix, Comparator.comparingInt((int[] a) -> a[1]).reversed());
  }

  public static void printMatrixCharsRating(int[][] matrix) {
    for (int i = 0; i < matrix.length; ++i) {
      System.out.println("i: " + i + " char: " + (char) matrix[i][0] + " rating: " + matrix[i][1]);
    }
  }

  public static int[][] getNotNullChar(int[] arr) {
    int notNullCountChar = 0;
    for (int i = 0; i < arr.length; ++i) {
      if (arr[i] != 0) {
        ++notNullCountChar;
      }
    }
    int[][] charMatrix = new int[notNullCountChar][2];

    int index = 0;
    for (int i = 0; i < arr.length; ++i) {
      if (arr[i] != 0) {
        charMatrix[index][0] = i + 32;
        charMatrix[index][1] = arr[i];
        ++index;
      }
    }

    return charMatrix;
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
