package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.AccountMovement;
import info.aservices.ftk6.dc.entities.Person;
import info.aservices.ftk6.dc.entities.Account;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless()
public class FinancialOperationsSession implements FinancialOperationsRemote,
        FinancialOperationsLocal {
    
    @PersistenceContext(unitName = "ESystem-ejbPU")
    private EntityManager em;

    @Override
    public void Recharge(Account account, BigDecimal amount) {
        Date operation_ts = new Date();
        AccountMovement transaction = new AccountMovement(true, operation_ts);
        transaction.setBeneficiary(account);
        transaction.setTs(operation_ts);
        transaction.setAmount(amount);
        transaction.setDescription("Зачисление денег на счет.");
        
        em.persist(transaction);
        
        BigDecimal currentBalance = account.getBalance();
        account.setBalance(currentBalance.add(amount));

        em.merge(account);
    }

    @Override
    public Integer Transfer(Account remitter, Account beneficiary,
            BigDecimal amount, String description) {
        Date operationTs = new Date();
        AccountMovement transaction = new AccountMovement(false, operationTs);
        transaction.setRemitter(remitter);
        transaction.setBeneficiary(beneficiary);
        transaction.setTs(operationTs);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        
        em.persist(transaction);
        
        remitter.DecreaseBalance(amount);
        beneficiary.IncreaseBalance(amount);

        em.merge(remitter);
        em.merge(beneficiary);

        return transaction.getId();
    }
}
