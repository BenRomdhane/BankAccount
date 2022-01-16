package domain.account;

import static org.mockito.Mockito.times;

import domain.account.exception.AccountException;
import domain.transaction.Transaction;
import domain.transaction.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

  private Account account;

  @Mock
  private Statement statement;

  @Mock
  private Balance balance;

  @Captor
  private ArgumentCaptor<Transaction> transaction;

  @Before
  public void setUp() {
    account = new Account(balance, statement);
  }

  @Test
  public void should_deposit_when_amount_is_valid() {
    //Given
    BigDecimal amount = new BigDecimal("20");
    LocalDateTime date = LocalDateTime.of(2022, 1, 14, 12, 15);

    //When
    Mockito.when(statement.getTransactions()).thenReturn(List.of(new Transaction(amount, TransactionType.DEPOSIT, date)));
    account.deposit(amount, date);

    //Then
    Mockito.verify(statement, times(1)).addStatement(transaction.capture());
    Mockito.verify(statement, times(1)).getTransactions();
    Assert.assertEquals(statement.getTransactions().get(0).getAmount(), amount);
    Assert.assertEquals(statement.getTransactions().get(0).getType(), TransactionType.DEPOSIT);
  }

  @Test
  public void should_withdrawal_when_amount_is_valid() {
    //Given
    BigDecimal amount = new BigDecimal("15");
    LocalDateTime date = LocalDateTime.of(2022, 1, 14, 12, 15);

    //When
    Mockito.when(statement.getTransactions()).thenReturn(
        Arrays.asList(new Transaction(new BigDecimal("100"), TransactionType.DEPOSIT, date),
            new Transaction(amount, TransactionType.WITHDRAWAL, date)));
    account.withdrawal(amount, date);

    //Then
    Mockito.verify(statement, times(1)).addStatement(transaction.capture());
    Mockito.verify(statement, times(1)).getTransactions();
    Assert.assertEquals(statement.getTransactions().get(1).getAmount(), amount.negate());
    Assert.assertEquals(statement.getTransactions().get(1).getType(), TransactionType.WITHDRAWAL);
  }

  @Test(expected = AccountException.class)
  public void should_get_exception_when_amount_is_null () {
    account.deposit(null, LocalDateTime.of(2022, 1, 14, 12, 15));
  }

  @Test(expected = AccountException.class)
  public void should_get_exception_when_amount_is_under_10 () {
    account.deposit(new BigDecimal("9"), LocalDateTime.of(2022, 1, 14, 12, 15));
  }

  @Test(expected = AccountException.class)
  public void should_get_exception_when_balance_is_exceeded () {
    //Given
    BigDecimal amount = new BigDecimal("100");
    LocalDateTime date = LocalDateTime.of(2022, 1, 14, 12, 15);

    //When
    Mockito.when(statement.getTransactions()).thenReturn(Arrays.asList(new Transaction(amount, TransactionType.WITHDRAWAL, date)));
    account.withdrawal(amount, date);
  }

  @Test
  public void should_print_account_statement() {

    //When
    Mockito.when(balance.getDate()).thenReturn(LocalDateTime.now());
    account.printAccount();

    //Then
    Mockito.verify(statement, times(1)).printStatement();
  }

}
