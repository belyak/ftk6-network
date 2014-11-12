<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="info.aservices.ftk6.dc.AdministrativeOperationsRemote" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="info.aservices.ftk6.dc.webhelpers.RemoteInterfaceFactory" %>
<div class="row">
    <div class="col-xs-10">
        <div class="panel panel-primary">
            <div class="panel-heading" data-toggle="collapse" data-target="#createPanelBody">
                <h3 class="panel-title">Create new person:</h3>
            </div>
            <div class="panel-body collapse in" id="createPanelBody" aria-expanded="true">

                <form role="form">
                    <input type="hidden" name="doCreatePerson" value="true">
                    <div class="form-group">
                        <label for="ln">Last name</label>
                        <input type="text" class="form-control" id="ln" name="lastName" placeholder="Enter last name">
                    </div>
                    <div class="form-group">
                        <label for="fn">First name</label>
                        <input type="text" class="form-control" id="fn" name="firstName" placeholder="Enter first name">
                    </div>
                    <div class="form-group">
                        <label for="pn">Patronymic name</label>
                        <input type="text" class="form-control" id="pn" name="patronymicName" placeholder="Enter patronymic name">
                    </div>
                    <div class="form-group">
                        <label for="ib">Initial balance:</label>
                        <input type="text" class="form-control" id="ib" name="initialBalance" placeholder="0">
                    </div>
                    <button type="submit" class="btn btn-primary">Create</button>
                </form>

            </div>
        </div>
    </div>
</div>

<%
    String doCreatePerson = request.getParameter("doCreatePerson");
    if (doCreatePerson!= null && doCreatePerson.equals("true")) {

        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String patromynicName = request.getParameter("patronymicName");
        BigDecimal initialBalance;
        try {
            initialBalance = new BigDecimal(request.getParameter("initialBalance"));
        } catch (NumberFormatException e) {
            initialBalance = new BigDecimal("0");
        }

        try {
            AdministrativeOperationsRemote aor = RemoteInterfaceFactory.get(AdministrativeOperationsRemote.class);
            Integer personId = aor.createPersonAndAccount(firstName, patromynicName, lastName, initialBalance);

            %>
<div class="row">
    <div class="column">
        <span class="alert-success">
            Person has been created, you can visit its page: <a href="/person.jsp?id=<%=personId%>">person page</a>
        </span>
    </div>
</div>
            <%

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
%>

