<%@ page import="info.aservices.ftk6.dc.transferobjects.AccountTO" %>
<%@ page import="info.aservices.ftk6.dc.entities.AccountMovement" %>
<%@ page import="info.aservices.ftk6.dc.entities.Account" %>
<%@ page import="java.util.Collection" %>
<%@ page import="info.aservices.ftk6.dc.entities.Person" %>
<%@ page import="info.aservices.ftk6.dc.ReportsRemote" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="info.aservices.ftk6.dc.transferobjects.PersonTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%! InitialContext ic; %>
        <%
            ReportsRemote ejbRef;
            PersonTO personTO;
            Person person;
            Collection<Account> accounts;
            try {

                ic  = new InitialContext();
                ejbRef = (ReportsRemote) ic.lookup(ReportsRemote.class.getName());
                int personId = Integer.parseInt(request.getParameter("id"));
                personTO = ejbRef.getPerson(personId);
                person = personTO.person;
                accounts = personTO.accounts;
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
                            <li class="list-group-item">
                                <h4>Счет # <%=account.getId() %> (текущий баланс: $<%=account.getBalance()%>)</h4>
                            </li>
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Дата операции</th>
                                    <th>Отправитель</th>
                                    <th>Получатель</th>
                                    <th>Сумма</th>
                                    <th>Назначение</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    AccountTO ai = ejbRef.getAccountHistoryInfo(account.getId(), 10);
                                    for (AccountMovement am: ai.operationsHistory) {
                                %>
                                    <tr>
                                        <td><%=am.getTs()%></td>
                                        <td><%=(am.getRemitter() != null) ? am.getRemitter().getPerson().getFullName() : ""%></td>
                                        <td><%=am.getBeneficiary().getPerson().getFullName()%></td>
                                        <td>$<%=am.getAmount()%></td>
                                        <td><%=am.getDescription()%></td>
                                    </tr>
                                <%
                                }
                            %>
                                </tbody>
                            </table>
                            <% } %>
                        </ul>
                    </div>
                </div>
            </div>
            <%
            } catch (NamingException e) {
                e.printStackTrace();
            }


            %>
