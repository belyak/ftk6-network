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
        <h3 class="panel-title">Пополнить лицевой счет:</h3>
      </div>
      <div class="panel-body collapse in" id="createPanelBody" aria-expanded="true">

        <form role="form">
          <input type="hidden" name="doRechargeAccount" value="true">
          <div class="form-group">
            <label for="ln">Номер лицевого счета:</label>
            <input type="text" class="form-control" id="ln" name="accountNumber" placeholder="#">
          </div>

          <div class="form-group">
            <label for="ib">Сумма:</label>
            <input type="text" class="form-control" id="ib" name="valueToRecharge" placeholder="0">
          </div>
          <button type="submit" class="btn btn-primary">пополнить</button>
        </form>

      </div>
    </div>
  </div>
</div>

<%
  String doRechargeAccount = request.getParameter("doRechargeAccount");
  if (doRechargeAccount != null && doRechargeAccount.equals("true")) {

    Integer accountId = Integer.parseInt(request.getParameter("accountNumber"));
    BigDecimal amountToRecharge;
    try {
      amountToRecharge = new BigDecimal(request.getParameter("valueToRecharge"));
    } catch (NumberFormatException e) {
      amountToRecharge = new BigDecimal("0");
    }

    try {

      ReportsRemote rr = RemoteInterfaceFactory.get(ReportsRemote.class);
      Account account = rr.getAccount(accountId);
      FinancialOperationsRemote fOpRemote = RemoteInterfaceFactory.get(FinancialOperationsRemote.class);
      fOpRemote.Recharge(account, amountToRecharge);

%>
<div class="row">
  <div class="column">
        <span class="alert-success">
            Account has been recharged.
        </span>
  </div>
</div>
<%

    } catch (NamingException e) {
      e.printStackTrace();
    }
  }
%>

