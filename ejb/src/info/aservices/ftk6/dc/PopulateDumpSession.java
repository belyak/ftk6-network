/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Account;
import info.aservices.ftk6.dc.entities.Person;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andy
 */
@Stateless
public class PopulateDumpSession implements PopulateDumpRemote {
    
    @PersistenceContext(unitName = "ESystem-ejbPU")
    private EntityManager em;
    
    @EJB FinancialOperationsLocal financialOperations; 
    
    /**
     * Наполняет хранилище данными.
     */
    @Override
    public void populate() {
        int personsCnt = 1;
        long minimalBalance = 0;
        long maximalBalance = 4000;
        int minAccountsPerPerson = 1;
        int maxAccountsPerPerson = 5;
        int transfersCnt = 50;
        long minimalTransfer = 10;
        long maximalTransfer = 2000;
        
        for (int i = 0; i < personsCnt; i++) {
            Person person = new Person();
            person.setFirstName("Name" + i);
            person.setPatronymicName("PatromynicName" + i);
            person.setLastName("LastName" + i);
            em.persist((Object)person);
            
            long accountsCnt = Math.round(Math.random()*(maxAccountsPerPerson - minAccountsPerPerson));
            for (int j = 0; j < accountsCnt; j++) {
                Account account = new Account();
                long cents = Math.round(10*(minimalBalance + Math.random()*maximalBalance));
                person.getAccountCollection().add(account);
                account.setPerson(person);
                account.setBalance(BigDecimal.ZERO);
                BigDecimal newBlanace = new BigDecimal(cents).movePointLeft(2);
                financialOperations.Recharge(account, newBlanace);
                em.persist(account);
            }
            
            for (int t = 0; t < transfersCnt; t++) {
                
            }
        }
    }

    @Override
    public List<Person> dumpPersons() {
        List<Person> persons = em.createNamedQuery("Person.findAll").getResultList();
        return persons;
    }    
}
