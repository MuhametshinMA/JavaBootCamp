package ex04;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.UUID;

import ex02.UserNotFoundException;
import ex03.TransactionNotFoundException;

public class TransactionService {
  private UsersList users;

  public TransactionService() {
    users = new UsersArrayList();
  }

  public void addUser(User user) {
    users.addUser(user);
  }

  public void printBalanceUser(long id) {
    try {
      System.out.println(users.getUserById(id).getBalance());
    } catch (UserNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public void executeTransferTransaction(long id1, long id2, double amount) throws IllegalTransactionException {
    try {
      User user1 = users.getUserById(id1);
      User user2 = users.getUserById(id2);
      if (user1.getBalance() < amount) {
        throw new IllegalTransactionException("Insufficient balance for user " + user1.getId());
      }
      UUID id = UUID.randomUUID();
      Transaction debitTransaction = new Transaction(id, user2, user1, TransferCategory.DEBIT, amount);
      Transaction creditTransaction = new Transaction(id, user1, user2, TransferCategory.CREDIT, amount);
      debitTransaction.performTransfer();
      user2.addTransaction(debitTransaction);
      user1.addTransaction(creditTransaction);
    } catch (UserNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public Transaction[] getTransferTransactions(long id) {
    try {
      return users.getUserById(id).getTransferTransactions();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return null;
  }

  public void deleteTransaction(UUID id, long userId) {
    try {
      users.getUserById(userId).removeTransaction(id);
    } catch (UserNotFoundException | TransactionNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public Transaction[] getNotAuthenticTransactions() {
    User[] usersArr = this.users.getUsers();

    LinkedList<Transaction> transactions = new LinkedList<Transaction>();
    for (int i = 0; i < users.getSize(); ++i) {
      transactions.addAll(usersArr[i].getTransactionsList());
    }

    Collections.sort(transactions, new Comparator<Transaction>() {
      public int compare(Transaction t1, Transaction t2) {
        return t1.getId().compareTo(t2.getId());
      }
    });

    for (int i = 0; i < transactions.size() - 1; ++i) {
      if (transactions.get(i).getId().equals(transactions.get(i + 1).getId())) {
        transactions.remove(i + 1);
        transactions.remove(i);
        --i;
      }
    }

    return transactions.toArray(new Transaction[transactions.size()]);
  }

}
