package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Account;
import info.aservices.ftk6.dc.entities.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Stateless
public class AdministrativeOperationsSession implements AdministrativeOperationsRemote {

    @PersistenceContext(unitName = "ESystem-ejbPU")
    private EntityManager em;

    @Override
    public Integer createPerson(String firstName, String patronymicName,
                                String lastName) {
        Person p = new Person();
        p.setFirstName(firstName);
        p.setPatronymicName(patronymicName);
        p.setLastName(lastName);
        em.persist(p);

        return p.getId();
    }

    @Override
    public Integer createPersonAndAccount(String firstName, String patronymicName, String lastName, BigDecimal initialBalance) {
        Person p = new Person(firstName, patronymicName, lastName);
        em.persist(p);
        Integer account_id = createAccount(p, initialBalance);
        System.out.print(account_id);
        return p.getId();
    }

    @Override
    public Integer createAccount(Person person, BigDecimal initialBalance) {
        Account account = new Account();
        account.setPerson(person);
        account.setBalance(initialBalance);
        person.getAccountCollection().add(account);
        em.persist(account);
        return account.getId();
    }

    @Override
    public Integer createAccount(Person person) {
        return createAccount(person, BigDecimal.ZERO);
    }
}
