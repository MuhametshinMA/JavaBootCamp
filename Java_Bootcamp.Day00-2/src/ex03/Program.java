import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int prevWeek = 0;
    long scoreByWeeks = 0;
    int countWeeks = 0;
    while (countWeeks < 18) {
      System.out.print("-> ");
      String str = in.nextLine();
      if (str.equals("42")) {
        break;
      } else {
        prevWeek = getWeek(str, prevWeek);
        System.out.print("-> ");
        String scores = in.nextLine();
        int minScore = getMinScore(scores);
        scoreByWeeks = collectScoreToLong(scoreByWeeks, minScore, countWeeks);
      }
      ++countWeeks;
    }
    drawGraph(scoreByWeeks);

    in.close();
  }

  public static void drawGraph(long scoreByWeeks) {
    int currentweek = 1;
    while (scoreByWeeks > 0) {
      long score = scoreByWeeks % 10;
      drawScore(score, currentweek);
      scoreByWeeks /= 10;
      ++currentweek;
    }
  }

  public static void drawScore(long score, int currentWeek) {
    System.out.print("Week " + currentWeek);
    for (int i = 0; i < score; ++i) {
      System.out.print("=");
    }
    System.out.println(">");
  }

  public static long collectScoreToLong(long score, long minScore, int countWeeks) {
    for (int i = 0; i < countWeeks; ++i) {
      minScore *= 10;
    }

    return (long) minScore + score;
  }

  public static void checkNum(int num) {
    if (!((num > 0) && (num < 10))) {
      System.err.println("IllegalArgument");
      System.exit(1);
    }
  }

  public static int getMinScore(String scores) {
    Scanner scScore = new Scanner(scores);
    int countScores = 1;
    int minScore = 0;

    if (scScore.hasNextInt()) {
      minScore = scScore.nextInt();
      checkNum(minScore);
      while (true) {
        if (scScore.hasNext()) {
          if (scScore.hasNextInt()) {
            ++countScores;
            int currentValue = scScore.nextInt();
            checkNum(currentValue);
            if (minScore > currentValue) {
              minScore = currentValue;
            }
          } else {
            System.err.println("IllegalArgument");
            System.exit(1);
          }
        } else {
          if (countScores < 5) {
            System.err.println("IllegalArgument");
            System.exit(1);
          }
          scScore.close();
          return minScore;
        }
      }
    } else {
      System.err.println("IllegalArgument");
      System.exit(1);
    }
    scScore.close();

    return -1;
  }

  public static int getWeek(String str, int prevWeek) {
    Scanner sc = new Scanner(str);
    if (sc.hasNext() && sc.next().equals("Week")) {
      if (sc.hasNextInt()) {
        int currentWeek = sc.nextInt();
        if (((currentWeek - prevWeek) == 1) && !sc.hasNext()) {
          sc.close();
          return currentWeek;
        } else {
          System.err.println("IllegalArgument");
          System.exit(1);
        }
      } else {
        System.err.println("IllegalArgument");
        System.exit(1);
      }
    } else {
      System.err.println("IllegalArgument");
      System.exit(1);
    }
    sc.close();
    return -1;
  }
}
