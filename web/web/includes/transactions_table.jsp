<%@page import="info.aservices.ftk6.dc.entities.AccountMovement"%>
<%@page import="info.aservices.ftk6.dc.webhelpers.RemoteInterfaceFactory"%>
<%@page import="info.aservices.ftk6.dc.webhelpers.EntitiesListerRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-md-9">
        <div class="panel panel-default panel-primary">
            <!-- Default panel contents -->
            <div class="panel-heading">
                <h3 class="panel-title">Transactions list</h3>
            </div>
            <div class="panel-body">
            <p>Full list of all the transactions in the system.</p>
            </div>
            <table class="table table-striped">
                <%  
                    try {
                        EntitiesListerRemote listerEjbRef = RemoteInterfaceFactory.get(EntitiesListerRemote.class);
                        for (AccountMovement am: listerEjbRef.<AccountMovement>getList(AccountMovement.class)) {
                            %><tr>
                                <td><%=am.getId()%></td>
                                <td><%=am.getAmount()%></td>
                                <td><%=am.getBeneficiary().getPerson().getFullName()%></td>
                                <td><a href="/person.jsp?id=<%=am.getId()%>">view transactions details</a></td>
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