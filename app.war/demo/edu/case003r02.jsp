<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>

<%
	LTO result_page = output.getLTO("result_page");
%>

<link href="../css/demo.css" rel="stylesheet" type="text/css" />

<H1><%= output.get("title") %></H1>
<%@ include file="/demo/edu/include/std_grid.jsp" %>
<SCRIPT LANGUAGE="JavaScript">
function goPage(pageNum)
{
	location.href = "<%= input.getSystem().get(SystemC.WEB_REQUEST_URI) %>?pgNum="+pageNum;
}
</SCRIPT>