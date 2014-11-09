<%@taglib prefix="layout" uri="http://kwonnam.pe.kr/jsp/template-inheritance" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include flush="true" page="../../includes/common/head.jsp"></jsp:include>
    <body>
    <jsp:include flush="true" page="../../includes/common/navigation.jsp"></jsp:include>
        <div class="container">
            <layout:block name="content">
                <div class="jumbotron">
                    <h1>Hello, world!</h1>
                </div>
            </layout:block>
        </div><!-- /.container -->
        <jsp:include flush="true" page="../../includes/common/footer.jsp"></jsp:include>
    </body>
</html>
