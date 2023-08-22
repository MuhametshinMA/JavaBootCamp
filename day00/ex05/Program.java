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
          // System.out.println("list students:");
          students = addStudent(students, line);
        } else if (countPoints == 1) {
          // System.out.println("shedule lessons:");
          lessons = addLesson(lessons, line);
        } else if (countPoints == 2) {
          // System.out.println("attendance:");
          attendance = addAttendance(attendance, line);
        }
      }
    }
    sc.close();

    String[][] sheduleDates = makeSheduleDates(students, lessons);

    String[][] shedule = makeShedule(sheduleDates, attendance, students);

    printShedule(shedule);
  }

  public static String[][] makeShedule(String[][] sheduleDates, String[][] attendance, String[] students) {
    String[][] shedule = new String[students.length + 1][sheduleDates.length + 1];
    shedule[0][0] = "          ";
    for (int i = 0; i < sheduleDates.length; ++i) {
      shedule[0][i + 1] = sheduleDates[i][0] + ":00 " + sheduleDates[i][1] + getAlignedStr(sheduleDates[i][2], 3);
    }

    for (int i = 0; i < students.length; ++i) {
      shedule[i + 1][0] = getAlignedStr(students[i], 10);
    }

    for (int i = 0; i < students.length; ++i) {
      for (int j = 0; j < sheduleDates.length; ++j) {
        shedule[i + 1][j + 1] = fillVisit(students[i], sheduleDates[j], attendance);
      }
    }

    return shedule;
  }

  public static String fillVisit(String student, String[] sheduleDate, String[][] attendance) {
    for (int i = 0; i < attendance.length; ++i) {
      if (attendance[i][0].equals(student) && sheduleDate[0].equals(attendance[i][1])
          && sheduleDate[2].equals(attendance[i][2])) {
        if (attendance[i][3].equals("HERE")) {
          return getAlignedStr("1", 10);
        } else if (attendance[i][3].equals("NOT_HERE")) {
          return getAlignedStr("-1", 10);
        }
      }
    }
    return getAlignedStr("", 10);
  }

  public static String[][] makeSheduleDates(String[] students, String[][] lessons) {
    String[] dayMonth = { "TU", "WE", "TH", "FR", "SA", "SU", "MO",
        "TU", "WE", "TH", "FR", "SA", "SU", "MO",
        "TU", "WE", "TH", "FR", "SA", "SU", "MO",
        "TU", "WE", "TH", "FR", "SA", "SU", "MO",
        "TU", "WE" };

    String[][] sheduleDates = new String[0][3];
    for (int i = 0; i < dayMonth.length; ++i) {
      for (int j = 0; j < lessons.length; ++j) {

        if (dayMonth[i].equals(lessons[j][1])) {
          sheduleDates = addSheduleItem(sheduleDates, lessons[j], i + 1);
        }
      }
    }
    return sheduleDates;
  }

  public static String getAlignedStr(String str, int desiredLength) {
    int numSpaces = desiredLength - str.length();
    if (numSpaces <= 0) {
      return str;
    } else {
      String spaces = String.format("%" + numSpaces + "s", "");
      return spaces + str;
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

  public static String[][] addSheduleItem(String[][] shedule, String[] lesson, int dayMonth) {
    String[] sheduleItem = new String[3];
    sheduleItem[0] = lesson[0];
    sheduleItem[2] = String.valueOf(dayMonth);
    sheduleItem[1] = lesson[1];
    shedule = Arrays.copyOf(shedule, shedule.length + 1);
    shedule[shedule.length - 1] = sheduleItem;
    return shedule;
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
    if (intTime < 1 || intTime > 4) {
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

  public static void printShedule(String[][] shedule) {
    for (int s = 0; s < shedule.length; ++s) {
      for (int i = 0; i < shedule[0].length; ++i) {
        if (i == 0) {
          System.out.print(shedule[s][i] + "");
        } else {
          System.out.print(shedule[s][i] + "|");
        }
      }
      System.out.println();
    }
  }

  // public static void printSheduleDate(String[][] shedule) {
  // for (int i = 0; i < shedule.length; ++i) {
  // System.out.print(shedule[i][0] + ":00 " + shedule[i][1]);
  // printNumSpace(Integer.parseInt(shedule[i][2]));
  // System.out.print("|");
  // }
  // System.out.println();
  // }

  // public static void printAttendance(String[][] attendance) {
  // for (int i = 0; i < attendance.length; ++i) {
  // System.out
  // .println("name: " + attendance[i][0] + " time: " + attendance[i][1] + " date:
  // " + attendance[i][2]
  // + " visit: " + attendance[i][3]);
  // }
  // }

  // public static void printStudents(String[] students) {
  // for (String string : students) {
  // System.out.println(string);
  // }
  // }

  // public static void printLessons(String[][] lessons) {
  // for (int i = 0; i < lessons.length; ++i) {
  // System.out.println("day: " + lessons[i][1] + " time: " + lessons[i][0]);
  // }
  // }
}
