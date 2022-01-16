package domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  private BigDecimal amount;

  private TransactionType type;

  private LocalDateTime date;


  public Transaction(BigDecimal amount, TransactionType type, LocalDateTime date) {
    this.amount = type.equals(TransactionType.WITHDRAWAL) ? amount.negate() : amount;
    this.type = type;
    this.date = date;
  }

  public StringBuilder printTransaction() {
    StringBuilder print= new StringBuilder();
    return print.append("***** Transaction ***** ")
        .append("\n").append("Type : ")
        .append(type).append("\n")
        .append("Amount : ")
        .append(amount)
        .append("\n")
        .append("Date : ")
        .append(date.format(dateTimeFormatter));
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public TransactionType getType() {
    return type;
  }

  public LocalDateTime getDate() {
    return date;
  }

}
