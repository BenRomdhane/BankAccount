package domain.account;

import domain.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;

public class Statement {

  private List<Transaction> transactions;

  public Statement() {
    transactions = new ArrayList<>();
  }

  public void addStatement(Transaction transaction) {
    transactions.add(transaction);
  }

  public void printStatement() {
    transactions.stream().map(Transaction::printTransaction).forEach(System.out::println);
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }
}
