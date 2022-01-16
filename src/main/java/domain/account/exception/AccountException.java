package domain.account.exception;

public class AccountException extends RuntimeException {

  private static final String BALANCE_EXCEEDED = "Balance has been exceeded";
  private static final String INVALID_AMOUNT = "You must enter a valid amount";

  public AccountException(String message) {
    super(message);
  }

  public static AccountException balanceExceeded(){
    return new AccountException(BALANCE_EXCEEDED);
  }

  public static AccountException invalidAmount(){
    return new AccountException(INVALID_AMOUNT);
  }

}
