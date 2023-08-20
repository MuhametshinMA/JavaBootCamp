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
    // for (int i = 0; i < charSequence.length(); ++i) {
    // System.out.println((int)charSequence.charAt(i) - 32);
    // }

    int[] charArray = getCharRating(charSequence);
    int[][] charRatingMatrix = getNotNullChar(charArray);
    sortCharMatrix(charRatingMatrix);
    int[][] graphMatrix = createGraphMatrix(charRatingMatrix);

    grawGraph(graphMatrix);
    System.out.println();
    grawGraph1(graphMatrix);
    grawGraph2(graphMatrix);
    // printMatrixCharsRating(graphMatrix);
    // jro p2ru 9-0tu8 q30[94ty 308uyt 08342[ yf8043 yt0q3 w4gy[08vwf83ytg08 qherg
    // vs;odfhi ;`owiyf '9uy24t[ 2ut [ 0wygoiq wher
  }

  public static void grawGraph(int[][] matrix) {
    for (int r = 0; r < matrix.length; ++r) {
      for (int c = 0; c < matrix[r].length; ++c) {
        System.out.print(matrix[r][c] + " ");
      }
      System.out.println();
    }
  }

  public static void grawGraph1(int[][] matrix) {
    for (int r = 0; r < matrix.length; ++r) {
      for (int c = 0; c < matrix[r].length; ++c) {
        if (c == (matrix[r][12] + 1)) {
          System.out.print(matrix[r][c] + " ");
        } else {
          System.out.print((char) matrix[r][c] + " ");
        }
      }
      System.out.println();
    }
  }

  public static void grawGraph2(int[][] matrix) {
    for (int c = matrix[0].length - 1; c >= 0; --c) {
      for (int r = 0; r < matrix.length; ++r) {
        if ((matrix[r][12] + 1) == c) {
          System.out.print("  " + matrix[r][c]);
        } else {
          System.out.print("  " + (char) matrix[r][c]);
        }
      }
      System.out.println();
    }
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
