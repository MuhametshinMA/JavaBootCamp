package ex04;

import ex02.UserIdsGenerator;

import java.util.LinkedList;
import java.util.UUID;
import ex03.TransactionNotFoundException;

public class User {

  private long identifier;
  private String name;
  private double balance;
  private TransactionsList<Transaction> transactionsList;

  public User(String name, double balance) throws IllegalArgumentException {
    this.identifier = UserIdsGenerator.getInstance().generateId();
    this.name = name;
    if (balance < 0) {
      throw new IllegalArgumentException("Balance can't be negative");
    }

    this.balance = balance;
    transactionsList = new TransactionsLinkedList();
  }

  public LinkedList<Transaction> getTransactionsList() {
    return transactionsList.getTransactionsList();
  }

  public Transaction[] getTransferTransactions() {
    return transactionsList.toArray();
  }

  public void addTransaction(Transaction transaction) {
    transactionsList.addTransaction(transaction);
  }

  public void removeTransaction(UUID id) throws TransactionNotFoundException {
    transactionsList.removeTransaction(id);
  }

  public long getId() {
    return identifier;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    if ((this.balance + balance) < 0)
      throw new IllegalArgumentException("Insufficient funds");
    this.balance += balance;
  }

  @Override
  public String toString() {
    return "User [id=" + identifier + ", name=" + name + ", balance=" + balance + "]";
  }
}
