package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Account;
import java.math.BigDecimal;

/**
 * Операции над счетами: пополнение счета, перевод между счетами.
 */
public interface FinancialOperations {

    /**
     * Пополнение счета
     *
     * @param account пополняемы счета
     * @param amount сумма
     */
    void Recharge(Account account, BigDecimal amount);

    /**
     * Перевод между счетами
     *
     * @param remitter счет отправителя
     * @param beneficiary счет получателя
     * @param amount сумма
     * @param description назначение перевода
     * @return идентификатор перевода
     */
    Integer Transfer(Account remitter, Account beneficiary,
                     BigDecimal amount, String description);
    
}
