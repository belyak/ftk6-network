<%@ page import="java.math.BigDecimal" %>
<%@ page import="info.aservices.ftk6.dc.webhelpers.RemoteInterfaceFactory" %>
<%@ page import="info.aservices.ftk6.dc.AdministrativeOperationsRemote" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" uri="http://kwonnam.pe.kr/jsp/template-inheritance" %>

<layout:extends name="base">
  <layout:put block="title" type="REPLACE">E-System: создать новое физ.лицо</layout:put>
  <layout:put block="content" type="REPLACE">
    <jsp:include page="includes/create_person_form.jsp" flush="true" />
  </layout:put>
</layout:extends>