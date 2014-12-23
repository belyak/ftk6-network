package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Person;

import java.math.BigDecimal;

public interface AdministrativeOperations {
    /**
     * Создание физического лица
     *
     * @param firstName имя
     * @param patronymicName фамилия
     * @param lastName отчество
     * @return идентификатор физического лица
     */
    Integer createPerson(String firstName, String patronymicName, String lastName);

    /**
     * Создание физического лица и счета с начальным балансом
     *
     * @param firstName имя
     * @param patronymicName фамилия
     * @param lastName отчество
     * @param initialBalance начальный баланс
     * @return идетификатор физического лица
     */
    Integer createPersonAndAccount(String firstName, String patronymicName,
                                   String lastName, BigDecimal initialBalance);

    /**
     * Создание счета с начальным балансом
     * @param person физ.лицо-владелец счета
     * @param initialBalance начальный баланс
     * @return идентификатор счета
     */
    Integer createAccount(Person person, BigDecimal initialBalance);

    /**
     * Создание счета
     *
     * @param person физ.лицо-владелец счета
     * @return идентификатор счета
     */
    Integer createAccount(Person person);
}
