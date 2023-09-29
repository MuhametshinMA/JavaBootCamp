package ex01;

public class UserIdsGenerator {
  private static UserIdsGenerator instance;
  private long counter;

  private UserIdsGenerator() {
    counter = 1;
  }

  public static synchronized UserIdsGenerator getInstance() {
    if (instance == null) {
      instance = new UserIdsGenerator();
    }
    return instance;
  }

  public long generateId() {
    return counter++;
  }
}
