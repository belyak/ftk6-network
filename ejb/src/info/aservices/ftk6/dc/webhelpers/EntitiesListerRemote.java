/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.aservices.ftk6.dc.webhelpers;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author andy
 */
@Remote
public interface EntitiesListerRemote {
    <T1> List<T1> getList(Class entityClass);
    <T1> int getPagesCount(Class entityClass, int itemsPerPage);
    <T1> List<T1> getList(Class entityClass, int page, int itemsPerPage);
    <T1> List<T1> getSearchList(Class entityClass, String firstName, String lastName, String patronymicName);
}
