package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Account;
import java.math.BigDecimal;

/**
 * Операции над счетами: пополнение счета, перевод между счетами.
 */
public interface FinancialOperations {

    void Recharge(Account account, BigDecimal amount);

    Integer Transfer(Account remitter, Account beneficiary, BigDecimal amount, String description);
    
}
