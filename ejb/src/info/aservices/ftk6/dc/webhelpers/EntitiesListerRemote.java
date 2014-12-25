/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.aservices.ftk6.dc.webhelpers;

import info.aservices.ftk6.dc.entities.Person;

import java.util.Collection;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface EntitiesListerRemote {
    <T1> List<T1> getList(Class entityClass);
    int getPagesCount(Class entityClass, int itemsPerPage);
    <T1> List<T1> getList(Class<T1> entityClass, int page, int itemsPerPage);
    Collection<Person> getPersonsFilteredList(String firstName, String lastName, String patronymicName);
}
