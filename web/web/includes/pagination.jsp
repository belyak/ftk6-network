<%@ page import="info.aservices.ftk6.dc.webhelpers.PaginationHelper" %>
<%@ page import="java.util.Collection" %>
<%
  int totalPages = Integer.parseInt(request.getParameter("totalPages"));
  int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
  boolean paginate = Boolean.parseBoolean(request.getParameter("paginate"));

  if (paginate) {
%>
<div class="clearfix"></div>
<ul class="pagination pull-right">
  <li class="disabled"><a href="#"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
  <%
    PaginationHelper ph = new PaginationHelper();
    for (PaginationHelper.PaginationEntry pe: ph.getPagination(pageNumber, totalPages)) {
      if (pe.number == pageNumber) {
  %>
  <li class="active"><a href="#"><%=pe.label%></a></li>
  <%
  } else {
  %>
  <li><a href="<%=pe.href%>"><%=pe.label%></a></li>
  <%
    }
  %>
  <%
    }
  %>
  <li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
</ul>
<%
  } // end if paginate
%>