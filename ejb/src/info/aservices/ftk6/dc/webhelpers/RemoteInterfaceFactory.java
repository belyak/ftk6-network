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
        String remoteName = interfaceClass.getCanonicalName();
        return InitialContext.doLookup(remoteName);
    }
}
