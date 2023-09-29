package ex03;

import java.util.UUID;

enum TransferCategory {
  DEBIT,
  CREDIT
}

public class Transaction {
  private UUID identifier;
  private User recipient;
  private User sender;
  private TransferCategory transferCategory;
  private double transferAmount;

  public Transaction(UUID id, User recipient, User sender, TransferCategory transferCategory, double transferAmount) {
    if (transferAmount < 0) {
      throw new IllegalArgumentException("transfer amount must be positive");
    }
    this.identifier = id;
    this.recipient = recipient;
    this.sender = sender;
    this.transferCategory = transferCategory;
    this.transferAmount = transferAmount;
  }

  public void performTransfer() {
    if (transferCategory == TransferCategory.DEBIT) {
      sender.setBalance(-transferAmount);
      recipient.setBalance(transferAmount);
    } else if (transferCategory == TransferCategory.CREDIT) {
      sender.setBalance(transferAmount);
      recipient.setBalance(-transferAmount);
    }
  }

  public UUID getId() {
    return identifier;
  }

  public User getRecipient() {
    return recipient;
  }

  public User getSender() {
    return sender;
  }

  public TransferCategory getTransferCategory() {
    return transferCategory;
  }

  @Override
  public String toString() {
    return "Transaction [id=" + identifier + ", recipient=" + recipient.toString() + ", sender=" + sender.toString()
        + ", transfer amount=" + transferAmount + "]";
  }
}
