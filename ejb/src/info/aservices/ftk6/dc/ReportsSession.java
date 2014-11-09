/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.AccountMovement;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import info.aservices.ftk6.dc.entities.Account;
import info.aservices.ftk6.dc.entities.Person;
import java.util.Date;
import javax.persistence.TypedQuery;


@Stateless
public class ReportsSession implements ReportsRemote {

    @PersistenceContext(unitName = "ESystem-ejbPU")
    private EntityManager em;
    
    @Override
    public List<AccountMovement> getAccountMovements(Account account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person getPerson(Integer person_id) {
        Person person = em.find(Person.class, person_id);
        int s = person.getAccountCollection().size();
        System.out.println("size: " + s);
        return person;
    }

    @Override
    public AccountInfo getAccountHistoryInfo(Integer account_id, Date from, Date till) {
        AccountInfo ai = getAccountHistoryCommonData(account_id);
        
        String query = "SELECT am FROM AccountMovement am " + 
                       "WHERE am.ts.after(:date_from) AND am.ts.before(:date_till) " +
                       "AND account.id = :account_id ORDER BY am.ts DESC";
        
        TypedQuery<AccountMovement> intermediateResult;
        intermediateResult = em.createQuery(query, AccountMovement.class);
        intermediateResult = intermediateResult.setParameter("date_from", from);
        intermediateResult = intermediateResult.setParameter("date_till", till);
        intermediateResult = intermediateResult.setParameter("account_id", account_id);
        ai.operationsHistory = intermediateResult.getResultList();
        return ai;
    }

    @Override
    public AccountInfo getAccountHistoryInfo(Integer account_id, int lastOperationsCnt) {
        AccountInfo ai = getAccountHistoryCommonData(account_id);
        
        String query = "SELECT am FROM AccountMovement am " + 
                       "WHERE am.remitter.id = :account_id " + 
                       "OR am.beneficiary.id = :account_id ORDER BY am.ts DESC";
        TypedQuery<AccountMovement> intermediateResult;
        intermediateResult = em.createQuery(query, AccountMovement.class);
        intermediateResult = intermediateResult.setParameter("account_id", account_id);
                
        ai.operationsHistory = intermediateResult.setMaxResults(lastOperationsCnt).getResultList();
        return ai;
    }
    
    private AccountInfo getAccountHistoryCommonData(Integer account_id) {
        AccountInfo ai = new AccountInfo();
        ai.account = em.find(Account.class, account_id);
        ai.owner = ai.account.getPerson();
        return ai;
    }
}
