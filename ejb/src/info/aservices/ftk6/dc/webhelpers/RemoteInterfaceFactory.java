/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.aservices.ftk6.dc.webhelpers;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author andy
 */
public class RemoteInterfaceFactory {
    public static<Interface> Interface get(Class interfaceClass) throws NamingException {
        //TODO: Избавится от дублирования параметра и аргумента
        InitialContext ic = new InitialContext();
        String remoteName = interfaceClass.getCanonicalName();
        Interface lookupResult = (Interface)ic.lookup(remoteName);
        return lookupResult;
    }
}
