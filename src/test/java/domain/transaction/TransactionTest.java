package domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class TransactionTest {

  private Transaction transaction;

  @Before
  public void setUp() {
    transaction = new Transaction(new BigDecimal("50"), TransactionType.DEPOSIT, LocalDateTime.now());
  }

  @Test
  public void should_print_transaction() {
    //When
    StringBuilder result = transaction.printTransaction();

    //Then
    Assert.assertNotNull(result);

  }

}
