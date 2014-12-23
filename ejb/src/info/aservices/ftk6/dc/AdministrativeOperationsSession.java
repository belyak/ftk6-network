package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Account;
import info.aservices.ftk6.dc.entities.Person;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;

@Stateless
public class AdministrativeOperationsSession
        implements AdministrativeOperationsRemote, AdministrativeOperationsLocal {

    @PersistenceContext(unitName = "ESystem-ejbPU")
    private EntityManager em;

    @EJB
    private FinancialOperationsLocal fol;

    /**
     * Создание физического лица
     *
     * @param firstName имя
     * @param patronymicName фамилия
     * @param lastName отчество
     * @return идентификатор физического лица
     */
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

    /**
     * Создание физического лица и счета с начальным балансом
     *
     * @param firstName имя
     * @param patronymicName фамилия
     * @param lastName отчество
     * @param initialBalance начальный баланс
     * @return идетификатор физического лица
     */
    @Override
    public Integer createPersonAndAccount(String firstName, String patronymicName, String lastName, BigDecimal initialBalance) {
        Person p = new Person(firstName, patronymicName, lastName);
        em.persist(p);
        Integer account_id = createAccount(p, initialBalance);
        System.out.print(account_id);
        return p.getId();
    }

    /**
     * Создание счета с начальным балансом
     * @param person физ.лицо-владелец счета
     * @param initialBalance начальный баланс
     * @return идентификатор счета
     */
    @Override
    public Integer createAccount(Person person, BigDecimal initialBalance) {
        Account account = new Account();
        account.setPerson(person);
        account.setBalance(BigDecimal.ZERO);
        person.getAccountCollection().add(account);
        em.persist(account);

        if (!initialBalance.equals(BigDecimal.ZERO)) {
            fol.Recharge(account, initialBalance);
        }

        return account.getId();
    }

    /**
     * Создание счета
     *
     * @param person физ.лицо-владелец счета
     * @return идентификатор счета
     */
    @Override
    public Integer createAccount(Person person) {
        return createAccount(person, BigDecimal.ZERO);
    }
}
