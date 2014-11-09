<%@ page import="info.aservices.ftk6.dc.AccountInfo" %>
<%@ page import="info.aservices.ftk6.dc.entities.AccountMovement" %>
<%@ page import="info.aservices.ftk6.dc.entities.Account" %>
<%@ page import="java.util.Collection" %>
<%@ page import="info.aservices.ftk6.dc.entities.Person" %>
<%@ page import="info.aservices.ftk6.dc.ReportsRemote" %>
<%@ page import="javax.naming.InitialContext" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%! InitialContext ic; %>
        <%
            ic  = new InitialContext();
            ReportsRemote ejbRef = (ReportsRemote) ic.lookup(ReportsRemote.class.getName());
            int personId = Integer.parseInt(request.getParameter("id"));
            Person person = ejbRef.getPerson(personId);
            Collection<Account> accounts = person.getAccountCollection();
            
        %>
<div class="row">
    <div class="col-md-9">
        <div class="panel panel-default panel-primary">
            <!-- Default panel contents -->
            <div class="panel-heading">
                <h2 class="panel-title">
                    Счета в системе
                </h2>
            </div>
            <div class="panel-body">
                <h2>
                    <%=person.getLastName().toUpperCase() + ' ' + person.getFirstName()  + ' ' + person.getPatronymicName() + ' ' %>
                </h2>
            </div>
                <ul class="list-group">
                <% for (Account account: accounts) { %>
                <li class="list-group-item">#<%=account.getId() %><br>( $$<%=account.getBalance()%>)</li>
                <ul>
                <% 
                    AccountInfo ai = ejbRef.getAccountHistoryInfo(account.getId(), 10);
                    for (AccountMovement am: ai.operationsHistory) {
                        %><li><%=am.getTs()%>&nbsp;<%=am.getAmount()%></li><%
                    }
                %>
                </ul>
                <% } %>
            </ul>
        </div>
    </div>
</div>
        