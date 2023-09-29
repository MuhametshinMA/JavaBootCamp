package ex04;

public class Program {
  public static void main(String[] args) {
    // do transfers from user1 to user2
    TransactionService ts = new TransactionService();
    User user1 = new User("Jo", 1000);

    ts.addUser(user1);

    User user2 = new User("Mi", 500);
    ts.addUser(user2);

    try {
      ts.executeTransferTransaction(1, 2, 90);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    try {
      ts.executeTransferTransaction(1, 2, 30);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    try {
      ts.executeTransferTransaction(2, 1, 500);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    try {
      ts.executeTransferTransaction(1, 2, 50);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    Transaction[] transactionsUser1 = ts.getTransferTransactions(1);

    for (Transaction t : transactionsUser1) {
      System.out.println(t);
    }

    Transaction[] transactionsUser2 = ts.getTransferTransactions(2);

    for (Transaction t : transactionsUser2) {
      System.out.println(t);
    }

    // delete transaction
    transactionsUser1 = user1.getTransferTransactions();
    Transaction trUser1 = transactionsUser1[0];
    System.out.println(trUser1.getId());
    ts.deleteTransaction(trUser1.getId(), 1);
    ts.deleteTransaction(user1.getTransferTransactions()[1].getId(), 1);

    transactionsUser1 = ts.getTransferTransactions(1);
    for (Transaction t : transactionsUser1) {
      System.out.println(t);
    }
    transactionsUser2 = ts.getTransferTransactions(2);
    for (Transaction t : transactionsUser2) {
      System.out.println(t);
    }

    Transaction[] transactionsNotAuthentic = ts.getNotAuthenticTransactions();
    System.out.println("Non pair transactions: ");
    for (Transaction t : transactionsNotAuthentic) {
      System.out.println(t);
    }
  }
}
