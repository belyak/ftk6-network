/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Account;
import java.math.BigDecimal;

/**
 * @author andy
 * 
 * Операции над счетами: пополнение счета, перевод между счетами.
 */
public interface FinancialOperations {

    void Recharge(Account account, BigDecimal amount);

    Integer Transfer(Account remitter, Account beneficiary, BigDecimal amount, String description);
    
}
