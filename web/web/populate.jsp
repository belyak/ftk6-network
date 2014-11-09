<%@page import="javax.ejb.EJBException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@page import="javax.naming.*, info.aservices.ftk6.dc.*,
                info.aservices.ftk6.dc.PopulateDumpRemote" %>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
    </head>
    <body>
        <%! InitialContext ic; %>
        
        <% ic = new InitialContext(); %>
        <% PopulateDumpRemote ejbRef = null; %>
        <%!
            String remoteName = PopulateDumpRemote.class.getName();
        %>
        <BR>
        The remote name: `<%=remoteName%><br>
        <%
            List<String> persons;
        %>
        <% 
            try {
                ejbRef = (PopulateDumpRemote)ic.lookup(remoteName);
                ejbRef.populate();
                persons = ejbRef.dumpPersons();
                %><ul><%
                for (String person: persons) {
                    %><li><%=person%></li><%
                }
                %></ul><%
            } catch (Exception e) {
                %>Got exception: <%=e%><%
            }
            %>

</body>
</html>
        
    
