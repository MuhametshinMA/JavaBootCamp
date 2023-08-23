package ex00;

public class Program {
  public static void main(String[] args) {
    User user1 = new User(1, "Jo", 1000);
    User user2 = new User(2, "Mi", 500);
    System.out.println(user1);
    System.out.println(user2);
    Transaction tr = new Transaction(user1, user2, TransferCategory.DEBIT, 500);
    tr.performTransfer();
    System.out.println(tr);
    System.out.println(user1);
    System.out.println(user2);
    Transaction tr1 = new Transaction(user1, user2, TransferCategory.CREDIT, 100);
    tr1.performTransfer();
    System.out.println(tr1);
    System.out.println(user1);
    System.out.println(user2);
  }
}