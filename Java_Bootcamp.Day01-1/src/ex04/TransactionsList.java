package ex04;

import java.util.UUID;
import ex03.TransactionNotFoundException;
import java.util.LinkedList;

public interface TransactionsList<T> {
  public LinkedList<T> getTransactionsList();

  public void addTransaction(T transaction);

  public void removeTransaction(UUID id) throws TransactionNotFoundException;

  public T[] toArray();
}
