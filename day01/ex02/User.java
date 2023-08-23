package ex02;

public class User {

  private long identifier;
  private String name;
  private double balance;

  public User(String name, double balance) {
    this.identifier = UserIdsGenerator.getInstance().generateId();
    this.name = name;
    if (balance < 0) {
      throw new IllegalArgumentException("Balance can't be negative");
    }

    this.balance = balance;
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
