<%@page import="info.aservices.ftk6.dc.webhelpers.RemoteInterfaceFactory"%>
<%@page import="info.aservices.ftk6.dc.webhelpers.EntitiesListerRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="info.aservices.ftk6.dc.entities.Account"%>

<div class="row">
    <div class="col-md-9">
        <div class="panel panel-default panel-primary">
            <!-- Default panel contents -->
            <div class="panel-heading">
                <h3 class="panel-title">Accounts list</h3>
            </div>
            <div class="panel-body">
            <p>Список счетов и их текущих балансов</p>
            </div>
            <table class="table table-striped">
                <%  
                    try {
                        EntitiesListerRemote listerEjbRef = RemoteInterfaceFactory.get(EntitiesListerRemote.class);
                        for (Account a: listerEjbRef.<Account>getList(Account.class)) {
                            %><tr>
                                <td>#<%=a.getId()%></td>
                                <td><%=a.getBalance()%>
                                <td><a href="/account.jsp?id=<%=a.getId()%>">view account details</a></td>
                            </tr><%
                        }
                    } catch (Exception e) {
                        %>Got exception: <%=e%><%
                    }
                    %>
            </table>
        </div>
    </div>
</div>