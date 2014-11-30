package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.AccountMovement;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import info.aservices.ftk6.dc.entities.Account;
import info.aservices.ftk6.dc.entities.Person;
import info.aservices.ftk6.dc.transferobjects.AccountTO;
import info.aservices.ftk6.dc.transferobjects.PersonTO;

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
    public PersonTO getPerson(Integer person_id) {
        PersonTO pi =  new PersonTO();
        Person person = em.find(Person.class, person_id);
        pi.person = person;
        pi.accounts = new ArrayList<>(person.getAccountCollection());
        return pi;
    }

    @Override
    public Account getAccount(Integer account_id) {
        return (Account) em.createNamedQuery("Account.findById").setParameter("id", account_id).getSingleResult();
    }

    @Override
    public AccountTO getAccountHistoryInfo(Integer account_id, Date from, Date till) {
        AccountTO ato = getAccountHistoryCommonData(account_id);

        String query = "SELECT am FROM AccountMovement am " +
                       "WHERE am.ts BETWEEN :date_from AND :date_till " +
                       "AND (am.beneficiary.id = :account_id OR am.remitter.id = :account_id) ORDER BY am.ts DESC";

        TypedQuery<AccountMovement> intermediateResult;
        intermediateResult = em.createQuery(query, AccountMovement.class);
        intermediateResult = intermediateResult.setParameter("date_from", from);
        intermediateResult = intermediateResult.setParameter("date_till", till);
        intermediateResult = intermediateResult.setParameter("account_id", account_id);
        ato.operationsHistory = intermediateResult.getResultList();
        return ato;
    }

    @Override
    public AccountTO getAccountHistoryInfo(Integer account_id, int lastOperationsCnt) {
        AccountTO ato = getAccountHistoryCommonData(account_id);

        String query = "SELECT am FROM AccountMovement am " +
                       "WHERE am.remitter.id = :account_id " +
                       "OR am.beneficiary.id = :account_id ORDER BY am.ts DESC";
        TypedQuery<AccountMovement> intermediateResult;
        intermediateResult = em.createQuery(query, AccountMovement.class);
        intermediateResult = intermediateResult.setParameter("account_id", account_id);

        ato.operationsHistory = intermediateResult.setMaxResults(lastOperationsCnt).getResultList();
        return ato;
    }

    private AccountTO getAccountHistoryCommonData(Integer account_id) {
        AccountTO ato = new AccountTO();
        ato.account = em.find(Account.class, account_id);
        ato.owner = ato.account.getPerson();
        return ato;
    }

}
