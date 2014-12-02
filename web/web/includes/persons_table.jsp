<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="info.aservices.ftk6.dc.webhelpers.RemoteInterfaceFactory"%>
<%@page import="info.aservices.ftk6.dc.webhelpers.EntitiesListerRemote"%>
<%@page import="info.aservices.ftk6.dc.entities.Person"%>
<%@page import="java.util.List"%>
<%@page import="info.aservices.ftk6.dc.PopulateDumpRemote"%>
<%@ page import="java.util.Collection" %>
<div class="row">
    <div class="col-xs-10">
        <div class="panel panel-default panel-primary">
            <!-- Default panel contents -->
            <div class="panel-heading">
                <h3 class="panel-title">Список физ.лиц</h3>
            </div>
            <div class="panel-body">
            <p>Список фамилий и инициалов физических лиц.</p>
            </div>

                <%  
                    try {
                        EntitiesListerRemote listerEjbRef = RemoteInterfaceFactory.get(EntitiesListerRemote.class);
                        Collection<Person> persons;

                        boolean paginate = false;
                        int pageNumber = 0;
                        int itemsPerPage = 20;
                        int totalPages = 0;

                        String doSearch = request.getParameter("doSearch");
                        if (doSearch != null && doSearch.equals("true")) {

                            String lastName = request.getParameter("lastName");
                            String firstName = request.getParameter("firstName");
                            String patromynicName = request.getParameter("patronymicName");
                            persons = listerEjbRef.getPersonsFilteredList(firstName, lastName, patromynicName);
                        }
                        else {
                            try {
                                pageNumber = Integer.parseInt(request.getParameter("page"));
                            } catch (NumberFormatException e) {
                                pageNumber = 1;
                            }
                            persons = listerEjbRef.getList(Person.class, pageNumber, itemsPerPage);
                            totalPages = listerEjbRef.<Person>getPagesCount(Person.class, itemsPerPage);
                            paginate = true;
                        }

                        %>
                        <table class="table table-striped">
                            <%
                        for (Person p: persons) {
                            %>
                            <tr>
                                <td><%=p.getFullName(true) %></td>
                                <td><a href="/person.jsp?id=<%=p.getId()%>">view person details</a></td>
                            </tr>
                            <%
                        }
                        %>
                        </table>
                        <jsp:include page="pagination.jsp" flush="true">
                            <jsp:param name="paginate" value="<%=paginate%>"/>
                            <jsp:param name="totalPages" value="<%=totalPages%>"/>
                            <jsp:param name="pageNumber" value="<%=pageNumber%>"/>
                        </jsp:include>
                        <%
                    } catch (Exception e) {
                        %>Got exception: <%=e%><%
                    }
                    %>
        </div>
    </div>
</div>