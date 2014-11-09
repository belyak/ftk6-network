<%@page import="java.lang.Integer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include flush="true" page="includes/common/head.jsp"></jsp:include>
    <body>
    <jsp:include flush="true" page="includes/common/navigation.jsp"></jsp:include>
        <div class="container">
        <jsp:include page="includes/person_info.jsp" flush="true"></jsp:include>
        </div><!-- /.container -->
        <jsp:include flush="true" page="includes/common/footer.jsp"></jsp:include>
    </body>
</html>
