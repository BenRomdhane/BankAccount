package main;

import domain.account.Account;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankAccountMain {

  public static void main(String[] args) {

    Account account = new Account();

    account.deposit(new BigDecimal(500), LocalDateTime.of(2022, 01, 14, 8, 10));

    account.deposit(new BigDecimal(1000), LocalDateTime.of(2022, 01, 15, 13, 15));

    account.withdrawal(new BigDecimal(700), LocalDateTime.of(2022, 01, 16, 19, 22));

    account.printAccount();

  }

}
