package ex03;

import java.util.LinkedList;
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList<Transaction> {

  private LinkedList<Transaction> transactionsList;

  public TransactionsLinkedList() {
    transactionsList = new LinkedList<Transaction>();
  }

  @Override
  public void addTransaction(Transaction transaction) {
    transactionsList.add(transaction);
  }

  @Override
  public void removeTransaction(UUID id) throws TransactionNotFoundException {
    boolean removed = transactionsList.removeIf(transaction -> transaction.getId().equals(id));
    if (!removed) {
      throw new TransactionNotFoundException("Transaction not found with id: " + id);
    }
  }

  @Override
  public Transaction[] toArray() {
    return transactionsList.toArray(new Transaction[transactionsList.size()]);
  }
}
