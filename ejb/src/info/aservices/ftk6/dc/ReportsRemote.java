package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Account;

import javax.ejb.Remote;

@Remote
public interface ReportsRemote extends Reports {
    Account getAccount(Integer account_id);
}
