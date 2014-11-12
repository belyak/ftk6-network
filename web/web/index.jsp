<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" uri="http://kwonnam.pe.kr/jsp/template-inheritance" %>
<layout:extends name="base">
  <layout:put block="title" type="REPLACE">E-System start page.</layout:put>
  <layout:put block="additional_css">
    <link href="css/intro.css" rel="stylesheet">
  </layout:put>
  <layout:put block="content" type="REPLACE">
    <jsp:include page="includes/populate_db.jsp" flush="true" />
    <jsp:include page="includes/intro.jsp" flush="true" />
  </layout:put>
</layout:extends>
