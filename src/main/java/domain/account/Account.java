package domain.account;

import domain.account.exception.AccountException;
import domain.transaction.Transaction;
import domain.transaction.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class Account {

  private static final Logger LOGGER = Logger.getLogger(Account.class.getName());

  private final static BigDecimal MIN_AMOUNT_DEPOSIT_WITHDRAWAL = new BigDecimal(10);

  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  private Balance balance;

  private Statement statement;


  public Account() {
    this.balance = new Balance();
    this.statement = new Statement();
  }

  public Account(Balance balance, Statement statement) {
    this.balance = balance;
    this.statement = statement;
  }

  public void deposit (BigDecimal amount, LocalDateTime date) {
      BigDecimal totalAmount = getTotalAmountByTransactionType(amount, TransactionType.DEPOSIT, date);
      balance = new Balance(totalAmount);
      LOGGER.info("Success deposit");
  }

  public void withdrawal (BigDecimal amount, LocalDateTime date) {
    BigDecimal totalAmount = getTotalAmountByTransactionType(amount, TransactionType.WITHDRAWAL, date);
    if(totalAmount.compareTo(BigDecimal.ZERO) < 0) {
      throw AccountException.balanceExceeded();
    }
    balance = new Balance(totalAmount);
    LOGGER.info("Success withdrawal");
  }

  public void printAccount() {
    StringBuilder printAccount= new StringBuilder();
    printAccount.append("----- BANK ACCOUNT ----- ")
        .append("\n").append("Balance : ")
        .append(balance.getTotalAmount()).append("\n")
        .append("date : ")
        .append(balance.getDate().format(dateTimeFormatter))
        .append("\n");
    System.out.println(printAccount);
    statement.printStatement();
  }

  public Balance getBalance() {
    return balance;
  }

  public Statement getStatement() {
    return statement;
  }

  private BigDecimal getTotalAmountByTransactionType(BigDecimal amount, TransactionType type, LocalDateTime date) {
    if(amount == null || amount.compareTo(MIN_AMOUNT_DEPOSIT_WITHDRAWAL) < 0) {
      throw AccountException.invalidAmount();
    } else {
      Transaction transaction = new Transaction(amount, type, date);
      statement.addStatement(transaction);
      return statement.getTransactions().stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
  }

}
