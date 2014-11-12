/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.AccountMovement;
import info.aservices.ftk6.dc.entities.Account;
import info.aservices.ftk6.dc.entities.Person;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author andy
 */
@Remote
public interface ReportsRemote {
    public List<AccountMovement> getAccountMovements(Account acccount);
    public PersonInfo getPerson(Integer person_id);
    public AccountInfo getAccountHistoryInfo(Integer account_id, Date from, Date till);
    public AccountInfo getAccountHistoryInfo(Integer account_id, int lastOperationsCnt);
}
