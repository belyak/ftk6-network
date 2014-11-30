/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.aservices.ftk6.dc.transferobjects;

import info.aservices.ftk6.dc.entities.Account;
import info.aservices.ftk6.dc.entities.Person;
import java.io.Serializable;
import java.util.Collection;

public class PersonTO implements Serializable {
    public Person person;
    public Collection<Account> accounts;
}
