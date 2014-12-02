<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include flush="true" page="includes/common/head.jsp"/>
    <body>
    <jsp:include flush="true" page="includes/common/navigation.jsp"/>
        <div class="container">
        <jsp:include page="includes/person_info.jsp" flush="true"/>
        </div><!-- /.container -->
        <jsp:include flush="true" page="includes/common/footer.jsp"/>
    </body>
</html>
