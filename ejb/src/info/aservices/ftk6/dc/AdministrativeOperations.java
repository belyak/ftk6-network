package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Person;

import java.math.BigDecimal;

public interface AdministrativeOperations {
    Integer createPerson(String firstName, String patronymicName, String lastName);
    Integer createPersonAndAccount(String firstName, String patronymicName,
                                   String lastName, BigDecimal initialBalance);
    Integer createAccount(Person person, BigDecimal initialBalance);
    Integer createAccount(Person person);
}
