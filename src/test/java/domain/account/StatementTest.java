package domain.account;

import static org.mockito.Mockito.times;

import domain.transaction.Transaction;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatementTest {

  private Statement statement;

  @Mock
  private Transaction transaction;

  @Mock
  private List<Transaction> transactions;

  @Before
  public void setUp() {
    transactions.add(transaction);
    statement = new Statement();
  }

  @Test
  public void should_add_new_statement() {

    //When
    statement.addStatement(transaction);

    //Then
    Mockito.verify(transactions, times(1)).add(transaction);

  }

}
