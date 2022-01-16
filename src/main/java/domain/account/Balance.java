package domain.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Balance {

  private BigDecimal totalAmount;

  private LocalDateTime date;

  public Balance() {
  }

  public Balance(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
    this.date = LocalDateTime.now();
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public LocalDateTime getDate() {
    return date;
  }

}
