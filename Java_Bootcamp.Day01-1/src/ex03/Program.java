package ex03;

import java.util.UUID;

public class Program {
  public static void main(String[] args) {
    User user1 = new User("Jo", 1000);
    User user2 = new User("Mi", 500);
    Transaction tr = new Transaction(UUID.randomUUID(), user1, user2, TransferCategory.DEBIT, 500);
    tr.performTransfer();
    user1.addTransaction(tr);
    user2.addTransaction(tr);
    System.out.println(tr);
    System.out.println(user1);
    user1.printTransactions();
    System.out.println(user2);
    user2.printTransactions();

  }
}
