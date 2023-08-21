import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("-> ");

    String charSequence = scanner.nextLine();
    scanner.close();

    int[] charArray = getCharRating(charSequence);
    int[][] charRatingMatrix = getNotNullChar(charArray);
    sortCharMatrix(charRatingMatrix);
    int[][] graphMatrix = createGraphMatrix(charRatingMatrix);
    System.out.println();
    grawGraph(graphMatrix);
  }

  public static void grawGraph(int[][] matrix) {
    for (int c = matrix[0].length - 2; c >= 0; --c) {
      for (int r = 0; r < matrix.length; ++r) {
        if ((matrix[r][12] + 1) == c) {
          printNumSpace(matrix[r][c]);
        } else {
          System.out.print("  " + (char) matrix[r][c]);
        }
      }
      System.out.println();
    }
  }

  public static void printNumSpace(int num) {
    int orderNum = (int) Math.log10(num) + 1;
    int countSpace = 3 - orderNum;
    for (int i = 0; i < countSpace; ++i) {
      System.out.print(" ");
    }
    System.out.print(num);
  }

  public static int[][] createGraphMatrix(int[][] matrix) {
    int lengthMatrix = 0;
    if (matrix.length < 10) {
      lengthMatrix = matrix.length;
    } else {
      lengthMatrix = 10;
    }
    int[][] graphMatrix = new int[lengthMatrix][13];
    for (int r = 0; r < graphMatrix.length; ++r) {
      for (int c = 0; c < graphMatrix[r].length; ++c) {
        graphMatrix[r][c] = 32;
      }
    }

    double sharpQuotient = (double) matrix[0][1] / 10;
    for (int r = 0; r < graphMatrix.length; ++r) {
      double countSharp = (double) matrix[r][1] / sharpQuotient;
      int c = (int) countSharp;
      graphMatrix[r][0] = matrix[r][0];
      graphMatrix[r][c + 1] = matrix[r][1];
      graphMatrix[r][graphMatrix[r].length - 1] = c;
      fillSharps(graphMatrix, r, c);
    }
    return graphMatrix;
  }

  public static void fillSharps(int[][] matrix, int index, int sharpLimit) {
    for (int c = 1; c <= sharpLimit; ++c) {
      matrix[index][c] = '#';
    }
  }

  public static void sortCharMatrix(int[][] matrix) {
    Arrays.sort(matrix, Comparator.comparingInt((int[] a) -> a[1]).reversed());
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

  public static int[] getCharRating(String chars) {
    int[] charArray = new int[95];
    int charLength = chars.length();
    for (int i = 0; i < charLength; ++i) {
      ++charArray[(int) chars.charAt(i) - 32];
    }
    return charArray;
  }
}
