<%-- 
    Document   : person
    Created on : Oct 21, 2014, 12:25:25 PM
    Author     : andy
--%>

<%@page import="info.aservices.ftk6.dc.AccountInfo"%>
<%@page import="info.aservices.ftk6.dc.entities.AccountMovement"%>
<%@page import="java.util.Collection"%>
<%@page import="info.aservices.ftk6.dc.PersonInfo"%>
<%@page import="info.aservices.ftk6.dc.entities.Account"%>
<%@page import="info.aservices.ftk6.dc.entities.Person"%>
<%@page import="info.aservices.ftk6.dc.ReportsRemote"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person information</title>
    </head>
    <body>
        <% 
            InitialContext ic  = new InitialContext(); 
            ReportsRemote ejbRef = (ReportsRemote) ic.lookup(ReportsRemote.class.getName());
            PersonInfo pi = ejbRef.getPersonInfo(1);
            Person person = pi.person;
            Collection<Account> accounts = pi.accounts;
            
        %>
        <h1>ФИО: <%=person.getLastName()  + ' ' + person.getFirstName()  + ' ' + person.getPatronymicName() + ' ' %></h1>
        <h2>Счета в системе:</h2>
        <ul>
            <% for (Account account: accounts) { %>
            <li>#<%=account.getId() %><br>( $$<%=account.getBalance()%>)</li>
            <ul>
            <% 
                AccountInfo ai = ejbRef.getAccountHistoryInfo(account.getId(), 10); 
                for (AccountMovement am: ai.operationsHistory) {
                    %><li><=am.getTs()%>&nbsp;<%=am.getAmount()%></li><%
                }
            %>
            </ul>
            <% } %>
        </ul>
    </body>
</html>
