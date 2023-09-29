package ex01;

public class Program {
  public static void main(String[] args) {
    UserIdsGenerator userIdsGenerator = UserIdsGenerator.getInstance();
    System.out.println(userIdsGenerator);
    UserIdsGenerator userIdsGenerator1 = UserIdsGenerator.getInstance();
    System.out.println(userIdsGenerator1);

    User user = new User("Iohan", 1000000);
    System.out.println(user.getId());
    try {
      User user1 = new User("Isaac", -10000000);
      System.out.println(user1.getId());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }
}