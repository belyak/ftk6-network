/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Account;
import info.aservices.ftk6.dc.entities.AccountMovement;
import info.aservices.ftk6.dc.entities.Person;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class AccountInfo implements Serializable {
    public Account account;
    public Person owner;
    public Date creationDate;
    public Collection<AccountMovement> operationsHistory;
}
