package info.aservices.ftk6.dc;

import info.aservices.ftk6.dc.entities.Person;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface PopulateDumpRemote {
    List<Person> dumpPersons();

    void populateIfEmpty();

    void populate();
}
