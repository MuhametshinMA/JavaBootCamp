package ex05;

import ex04.TransactionService;

public class Program {

  public static void main(String[] args) {
    try {
    String key = validateParameters(args);

    TransactionService ts = new TransactionService();
    IMenu menu;
    if (key.equals("user")) {
    menu = new StandartMenu(ts);
    menu.performActions();
    } else if (key.equals("dev")) {
    menu = new DevelopMenu(ts);
    menu.performActions();
    }
    } catch (Exception e) {
    System.out.println(e.getMessage());
    }

  }

  public static String validateParameters(String[] args) throws InvalidArgumentException {
    String[] arg1 = args[0].split("=");

    if (arg1.length != 2 || !arg1[0].equals("--profile")) {
      throw new InvalidArgumentException("invalid argument 1");
    }

    if (!(arg1[1].equals("dev") || arg1[1].equals("user"))) {
      throw new InvalidArgumentException("invalid argument 2");
    }
    return arg1[1];
  }
}
