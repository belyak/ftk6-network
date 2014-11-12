<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="info.aservices.ftk6.dc.webhelpers.RemoteInterfaceFactory" %>
<%@ page import="info.aservices.ftk6.dc.FinancialOperationsRemote" %>
<%@ page import="info.aservices.ftk6.dc.ReportsRemote" %>
<%@ page import="info.aservices.ftk6.dc.entities.Account" %>
<div class="row">
  <div class="col-xs-10">
    <div class="panel panel-primary">
      <div class="panel-heading" data-toggle="collapse" data-target="#createPanelBody">
        <h3 class="panel-title">Create new person:</h3>
      </div>
      <div class="panel-body collapse in" id="createPanelBody" aria-expanded="true">

        <form role="form">
          <input type="hidden" name="doCreateTransaction" value="true">
          <div class="form-group">
            <label for="ran">Remitter account number</label>
            <input type="text" class="form-control" id="ran" name="remitterAccountNumber" placeholder="#">
          </div>

          <div class="form-group">
            <label for="ban">Beneficiary account number</label>
            <input type="text" class="form-control" id="ban" name="beneficiaryAccountNumber" placeholder="#">
          </div>

          <div class="form-group">
            <label for="ib">Amount:</label>
            <input type="text" class="form-control" id="ib" name="amount" placeholder="0">
          </div>

          <div class="form-group">
            <label for="desc">Description:</label>
            <input type="text" class="form-control" id="desc" name="description" placeholder="0">
          </div>

          <button type="submit" class="btn btn-primary">Process</button>
        </form>

      </div>
    </div>
  </div>
</div>

<%
  String doCreateTransaction = request.getParameter("doCreateTransaction");
  if (doCreateTransaction != null && doCreateTransaction.equals("true")) {

    Integer remitterAccountId = Integer.parseInt(request.getParameter("remitterAccountNumber"));
    Integer beneficiaryAccountId = Integer.parseInt(request.getParameter("beneficiaryAccountNumber"));
    BigDecimal amount;
    String description = request.getParameter("description");
    try {
      amount = new BigDecimal(request.getParameter("amount"));
    } catch (NumberFormatException e) {
      amount = new BigDecimal("0");
    }

    try {

      ReportsRemote rr = RemoteInterfaceFactory.get(ReportsRemote.class);
      Account remitter = rr.getAccount(remitterAccountId);
      Account beneficiary = rr.getAccount(beneficiaryAccountId);
      FinancialOperationsRemote fOpRemote = RemoteInterfaceFactory.get(FinancialOperationsRemote.class);
      int transactionId = fOpRemote.Transfer(remitter, beneficiary, amount, description);

%>
<div class="row">
  <div class="column">
        <span class="alert-success">
            Transaction has been processed: <a href="/transaction.jsp?id=<%=transactionId%>">transaction page</a>
        </span>
  </div>
</div>
<%

    } catch (NamingException e) {
      e.printStackTrace();
    }
  }
%>

