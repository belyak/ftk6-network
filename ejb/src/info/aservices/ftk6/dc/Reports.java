package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Account;
import info.aservices.ftk6.dc.entities.AccountMovement;

import java.util.Date;
import java.util.List;

public interface Reports {
    List<AccountMovement> getAccountMovements(Account acccount);

    PersonInfo getPerson(Integer person_id);

    AccountInfo getAccountHistoryInfo(Integer account_id, Date from, Date till);

    AccountInfo getAccountHistoryInfo(Integer account_id, int lastOperationsCnt);
}
