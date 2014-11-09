<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="layout" uri="http://kwonnam.pe.kr/jsp/template-inheritance" %>
<layout:extends name="base">
    <layout:put block="title" type="REPLACE">E-System: persons list</layout:put>
    <layout:put block="content" type="REPLACE">
        <jsp:include page="includes/common/search_form.jsp" flush="true"></jsp:include>
        <jsp:include page="includes/persons_table.jsp" flush="true"></jsp:include>
    </layout:put>
</layout:extends>