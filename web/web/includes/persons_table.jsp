<%@page import="java.lang.Class"%>
<%@page import="info.aservices.ftk6.dc.webhelpers.RemoteInterfaceFactory"%>
<%@page import="info.aservices.ftk6.dc.webhelpers.EntitiesListerRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="info.aservices.ftk6.dc.entities.Person"%>
<%@page import="java.util.List"%>
<%@page import="info.aservices.ftk6.dc.PopulateDumpRemote"%>
<%@page import="javax.naming.InitialContext"%>
<div class="row">
    <div class="col-xs-10">
        <div class="panel panel-default panel-primary">
            <!-- Default panel contents -->
            <div class="panel-heading">
                <h3 class="panel-title">Persons list</h3>
            </div>
            <div class="panel-body">
            <p>Список фамилий и инициалов физических лиц.</p>
            </div>
            <table class="table table-striped">
                <%  
                    try {
                        PopulateDumpRemote ejbRef = RemoteInterfaceFactory.<PopulateDumpRemote>get(PopulateDumpRemote.class);
                        ejbRef.populate();
                        
                        EntitiesListerRemote listerEjbRef = RemoteInterfaceFactory.<EntitiesListerRemote>get(EntitiesListerRemote.class);
                        List<Person> persons;
                        
                        String doSearch = request.getParameter("doSearch");
                        if (doSearch != null && doSearch.equals("true")) {

                            String lastName = request.getParameter("lastName");
                            String firstName = request.getParameter("firstName");
                            String patromynicName = request.getParameter("patromynicName");
                            persons = listerEjbRef.getSearchList(Person.class, firstName, lastName, patromynicName);
                        }
                        else {
                            persons = listerEjbRef.<Person>getList(Person.class);
                        }
                        
                        for (Person p: persons) {
                            %><tr>
                                <td><%=p.getFullName(true) %></td>
                                <td><a href="/person.jsp?id=<%=p.getId()%>">view person details</a></td>
                            </tr><%
                        }
                    } catch (Exception e) {
                        %>Got exception: <%=e%><%
                    }
                    %>
            </table>
        </div>
    </div>
</div>