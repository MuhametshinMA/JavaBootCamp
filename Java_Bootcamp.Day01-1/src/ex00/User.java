package ex00;

public class User {

  private long identifier;
  private String name;
  private double balance;

  public User(long identifier, String name, double balance) {
    this.identifier = identifier;
    this.name = name;
    if (balance < 0) {
      throw new IllegalArgumentException("Balance can't be negative");
    }

    this.balance = balance;
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
