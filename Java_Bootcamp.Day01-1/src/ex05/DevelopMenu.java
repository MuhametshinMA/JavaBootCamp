package ex05;

import java.util.Scanner;
import java.util.UUID;

import ex04.Transaction;
import ex04.TransactionService;
import ex04.User;

public class DevelopMenu extends StandartMenu {

  public DevelopMenu(TransactionService transactionService) {
    super(transactionService);
  }

  @Override
  public void showMenu() {
    System.out.println("1. Add a user");
    System.out.println("2. View user balances");
    System.out.println("3. Perform a transfer");
    System.out.println("4. View all transactions for a specific user");
    System.out.println("5. DEV - remove a transfer by ID");
    System.out.println("6. DEV - check transfer validity");
    System.out.println("7. Finish execution");
  }

  @Override
  public void performActions() {
    Scanner in = new Scanner(System.in);
    while (true) {
      showMenu();
      System.out.print("-> ");
      String str = in.nextLine();
      if (str.equals("7")) {
        break;
      } else if (str.equals("1")) {

        System.out.println("Enter a user name and a balance");
        System.out.print("-> ");
        String userStr = in.nextLine();
        Scanner userScanner = new Scanner(userStr);
        String name = userScanner.next();
        double balance = userScanner.nextDouble();
        userScanner.close();
        User user = new User(name, balance);
        transactionService.addUser(user);
        System.out.println("User with id = " + user.getId() + " is added");
      } else if (str.equals("2")) {
        System.out.println("Enter a user ID");
        System.out.print("-> ");
        String idStr = in.nextLine();
        transactionService.printBalanceUser(Long.parseLong(idStr));
      } else if (str.equals("3")) {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        System.out.print("-> ");
        String transferStr = in.nextLine();
        Scanner transferScanner = new Scanner(transferStr);
        long senderId = transferScanner.nextLong();
        long recipientId = transferScanner.nextLong();
        double amount = transferScanner.nextDouble();
        transferScanner.close();
        try {
          transactionService.executeTransferTransaction(senderId, recipientId, amount);
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
        System.out.println("The transfer is completed");
      } else if (str.equals("4")) {
        System.out.println("Enter a user ID");
        System.out.print("-> ");
        String idStr = in.nextLine();
        try {
          ex04.Transaction[] transactions = transactionService.getTransferTransactions(Long.parseLong(idStr));
          for (ex04.Transaction t : transactions) {
            if (t.getAmount() > 0) {
              System.out.println(
                  "From " + t.getSender().getName() + "(id = " + t.getSender().getId() + ") " + t.getAmount()
                      + " with id = " + t.getId());
            } else {
              System.out.println(
                  "To " + t.getSender().getName() + "(id = " + t.getSender().getId() + ") " + t.getAmount()
                      + " with id = " + t.getId());
            }
          }
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      } else if (str.equals("5")) {
        System.out.println("Enter a user ID and a transfer ID");
        System.out.print("-> ");
        String idStr = in.nextLine();
        Scanner scanTrans = new Scanner(idStr);
        long userId = Long.parseLong(scanTrans.next());
        UUID transId = UUID.fromString(scanTrans.next());

        scanTrans.close();
        try {
          Transaction[] tr = transactionService.getTransferTransactions(userId);
          for (Transaction t : tr) {
            if (transId.equals(t.getId())) {
              System.out
                  .println("Transfer To " + t.getRecipient().getName() + "(id = " + userId + ") " + t.getAmount() + " removed");
              break;
            }
          }
          transactionService.deleteTransaction(transId, userId);
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      } else if (str.equals("6")) {
        System.out.println("Check results:");
        Transaction[] tr = transactionService.getNotAuthenticTransactions();
        for (Transaction t : tr) {
          System.out.println(t.getRecipient() + "(id = " + t.getRecipient().getId()
              + ") has an unacknowledged transfer id = " + t.getId());
        }
      }
      System.out.println("---------------------------------------------------------");
    }

    in.close();
  }
}
