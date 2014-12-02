<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" uri="http://kwonnam.pe.kr/jsp/template-inheritance" %>
<layout:extends name="base">
    <layout:put block="title" type="REPLACE">E-System: accounts list</layout:put>
    <layout:put block="content" type="REPLACE">
        <jsp:include page="includes/accounts_table.jsp" flush="true" />
    </layout:put>
</layout:extends>
