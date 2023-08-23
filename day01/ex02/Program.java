package ex02;

public class Program {
  public static void main(String[] args) {
    UsersArrayList ua = new UsersArrayList();
    System.out.println("count users: " + ua.getCountUsers());

    User userJo = new User("Jo", 11110);

    ua.addUser(userJo);
    User userJo1 = new User("Jo", 11110);

    ua.addUser(userJo1);
    User userJo2 = new User("Jo", 11110);

    ua.addUser(userJo2);
    User userJo3 = new User("Jo", 11110);

    ua.addUser(userJo3);
    User userJo30 = new User("Jo", 11110);

    ua.addUser(userJo30);
    User userJo4 = new User("Jo", 11110);

    ua.addUser(userJo4);
    User userJo5 = new User("Jo", 11110);

    ua.addUser(userJo5);
    User userJo6 = new User("Jo", 11110);

    ua.addUser(userJo6);
    User userJo7 = new User("Jo", 11110);

    ua.addUser(userJo7);
    User userJo8 = new User("Jo", 11110);

    ua.addUser(userJo8);
    User userJo9 = new User("Jo", 11110);

    ua.addUser(userJo9);

    User userJo11 = new User("Jo", 11110);

    ua.addUser(userJo11);

    System.out.println("count users: " + ua.getCountUsers());
    System.out.println("capacity users: " + ua.getCapacity());

    User findedUser;
    try {
      findedUser = ua.getUserById(100);
    } catch (Exception e) {
      System.out.println("User not found: " + e.getMessage());
    }
    // System.out.println(findedUser);

    for (int i = 0; i < ua.getCountUsers(); ++i) {
      System.out.println(ua.getUserByIndex(i));
    }

  }
}
