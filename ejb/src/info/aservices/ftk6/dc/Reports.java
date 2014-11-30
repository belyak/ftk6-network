package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Account;
import info.aservices.ftk6.dc.entities.AccountMovement;
import info.aservices.ftk6.dc.transferobjects.AccountTO;
import info.aservices.ftk6.dc.transferobjects.PersonTO;

import java.util.Date;
import java.util.List;

public interface Reports {
    List<AccountMovement> getAccountMovements(Account acccount);

    PersonTO getPerson(Integer person_id);

    AccountTO getAccountHistoryInfo(Integer account_id, Date from, Date till);

    AccountTO getAccountHistoryInfo(Integer account_id, int lastOperationsCnt);
}
