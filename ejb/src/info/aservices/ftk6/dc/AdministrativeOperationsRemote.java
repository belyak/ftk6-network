/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Person;
import java.math.BigDecimal;
import javax.ejb.Remote;

/**
 *
 * @author andy
 */
@Remote
public interface AdministrativeOperationsRemote {
    public Integer createPerson(String firstName, String patronymicName, String lastName);
    public Integer createPersonAndAccount(String firstName, String patronymicName, String lastName, BigDecimal initialBalance);
    public Integer createAccount(Person person, BigDecimal initialBalance);
    public Integer createAccount(Person person);
}
