package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Account;
import info.aservices.ftk6.dc.entities.AccountMovement;
import info.aservices.ftk6.dc.transferobjects.AccountTO;
import info.aservices.ftk6.dc.transferobjects.PersonTO;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

@Remote
public interface ReportsRemote {
    /**
     * Получение всех операций связанных с лицевым счетом
     *
     * @param account лицевой счет
     * @return список операций
     */
    List<AccountMovement> getAccountMovements(Account account);

    /**
     * Получение данных физического лица
     *
     * @param person_id идентификатор физического лица
     * @return данные
     */
    PersonTO getPerson(Integer person_id);

    /**
     * Получение операций в указанном диапазоне дат
     * @param account_id идентификатор лицевого счета
     * @param from начало диапазона
     * @param till конец диапазона
     * @return список операций
     */
    AccountTO getAccountHistoryInfo(Integer account_id, Date from, Date till);

    /**
     * Получение N последних операций
     *
     * @param account_id идентификатор лицевого счета
     * @param lastOperationsCnt количество операций
     * @return список операций
     */
    AccountTO getAccountHistoryInfo(Integer account_id, int lastOperationsCnt);

    /**
     * Получение лицевого счета по идентификатору
     *
     * @param account_id идентификатор лицевого счета
     * @return лицевой счет
     */
    Account getAccount(Integer account_id);
}
