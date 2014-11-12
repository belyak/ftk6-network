<%@ page import="info.aservices.ftk6.dc.PopulateDumpRemote" %>
<%@ page import="info.aservices.ftk6.dc.webhelpers.RemoteInterfaceFactory" %>
<%@ page import="javax.naming.NamingException" %><%

  PopulateDumpRemote ejbRef = null;
  try {
      ejbRef = RemoteInterfaceFactory.get(PopulateDumpRemote.class);
      ejbRef.populateIfEmpty();
  } catch (NamingException e) {
    e.printStackTrace();
  }

%>

