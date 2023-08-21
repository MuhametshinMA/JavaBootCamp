import java.util.Scanner;
import java.util.Arrays;

public class Program {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String[] students = new String[0];
    String[][] lessons = new String[0][2];
    String[][] attendance = new String[0][4];

    int countPoints = 0;
    while (countPoints < 3) {
      String line = sc.nextLine();
      if (line.equals(".")) {
        ++countPoints;
      } else {
        if (countPoints == 0) {
          System.out.println("list students:");
          students = addStudent(students, line);
        } else if (countPoints == 1) {
          System.out.println("shedule lessons:");
          lessons = addLesson(lessons, line);
        } else if (countPoints == 2) {
          System.out.println("attendance:");
          attendance = addAttendance(attendance, line);
        }
      }
    }
    sc.close();

    printAttendance(attendance);
    printLessons(lessons);
    // printStudents(students);
  }

  public static String[][] addAttendance(String[][] attendance, String newAttendance) {
    attendance = Arrays.copyOf(attendance, attendance.length + 1);
    Scanner sc = new Scanner(newAttendance);

    int colsAttendance = 4;
    String[] attendanceCol = new String[4];
    for (int i = 0; i < colsAttendance; ++i) {
      if (sc.hasNext()) {
        attendanceCol[i] = sc.next();
      } else {
        sc.close();
        System.err.println("invalid argument: not enough arguments");
        System.exit(1);
      }
    }

    attendance[attendance.length - 1] = attendanceCol;

    sc.close();

    return attendance;
  }

  public static String[][] addLesson(String[][] lessons, String newLesson) {

    if (lessons.length >= 10) {
      System.err.println("invalid argument: lessons more than 10");
      System.exit(1);
    }

    Scanner sc = new Scanner(newLesson);
    if (!sc.hasNextInt()) {
      System.err.println("invalid argument: incorrect time lesson");
      System.exit(1);
    }

    lessons = Arrays.copyOf(lessons, lessons.length + 1);
    lessons[lessons.length - 1] = new String[2];
    String time = sc.next();
    checkTimeInterval(time);
    lessons[lessons.length - 1][0] = time;
    String day = sc.next();

    if (checkCorrectDay(day) == -1) {
      System.err.println("invalid argument: incorrect week day");
      sc.close();
      System.exit(1);
    }

    lessons[lessons.length - 1][1] = day;

    if (sc.hasNext()) {
      System.err.println("invalid argument: too many parameters");
      sc.close();
      System.exit(1);
    }

    sc.close();

    if (lessons.length > 1) {
      checkLessonSequence(lessons[lessons.length - 1], lessons[lessons.length - 2]);
    }
    return lessons;
  }

  public static void checkLessonSequence(String[] current, String[] prev) {
    int currentDay = checkCorrectDay(current[1]);
    int prevDay = checkCorrectDay(prev[1]);
    if (currentDay < prevDay) {
      System.err.println("invalid argument: incorrect day lesson sequence");
      System.exit(1);
    } else if (currentDay == prevDay) {
      int currentTime = Integer.parseInt(current[0]);
      int prevTime = Integer.parseInt(prev[0]);
      if (currentTime < prevTime + 2) {
        System.err.println("invalid argument: incorrect time lesson sequence");
        System.exit(1);
      }
    }
  }

  public static void checkTimeInterval(String time) {
    int intTime = Integer.parseInt(time);
    if (intTime < 13 || intTime > 16) {
      System.err.println("invalid argument: incorrect time interval");
      System.exit(1);
    }
  }

  public static int checkCorrectDay(String lesson) {
    String[] weekDays = { "MO", "TU", "WE", "TH", "FR", "SA", "SU" };
    for (int i = 0; i < weekDays.length; ++i) {
      if (lesson.equals(weekDays[i])) {
        return i;
      }
    }

    return -1;
  }

  public static String[] addStudent(String[] students, String newStudent) {
    if (students.length >= 10) {
      System.err.println("invalid argument: students more than 10");
      System.exit(1);
    }

    students = Arrays.copyOf(students, students.length + 1);
    students[students.length - 1] = newStudent;
    return students;
  }

  public static void printAttendance(String[][] attendance) {
    for (int i = 0; i < attendance.length; ++i) {
      System.out
          .println("name: " + attendance[i][0] + " time: " + attendance[i][1] + " date: " + attendance[i][2]
              + " visit: " + attendance[i][3]);
    }
  }

  public static void printStudents(String[] students) {
    for (String string : students) {
      System.out.println(string);
    }
  }

  public static void printLessons(String[][] lessons) {
    for (int i = 0; i < lessons.length; ++i) {
      System.out.println("day: " + lessons[i][1] + " time: " + lessons[i][0]);
    }
  }
}
