package ex03;

import java.util.UUID;

public interface TransactionsList<T> {
  public void addTransaction(T transaction);

  public void removeTransaction(UUID id) throws TransactionNotFoundException;

  public T[] toArray();
}
