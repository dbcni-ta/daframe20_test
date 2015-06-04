<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>

<%
	LTO result_page = output.getLTO("result_page");
	
	MTO row = null;
%>

<link href="../css/demo.css" rel="stylesheet" type="text/css" />

<H1> 국가정보 (페이징) </H1>
<b>전체 : <%= result_page.getInfo(LtoInfoC.ROW_TOT_STR) %> [페이지수 : <%= result_page.getInfo(LtoInfoC.PG_TOT_STR)  %>]</b>
<br>
<table width="100%" border="1" cellpadding="1" cellspacing="1" >
	<tr>
		<td><b>코드</b></td>
		<td><b>국가명</b></td>
		<td><b>수도명</b></td>
		<td><b>대륙명</b></td>
	</tr>
<%	
	for (int i=0;i<result_page.size();i++) {
%>
<tr>
<%		
		row = result_page.get(i);
%>
	<td>
		<%= row.get("CODE") %>
	</td>
	<td>
		<%= row.get("NAME") %>
	</td>
	<td>
		<%= row.get("CAPITAL") %>
	</td>
	<td>
		<%= row.get("CONTINENT") %>
	</td>
</tr>
<%
	}
%>
</table>

</table>
<center>
<%=PageNavi.getInstance().printPageNavi(result_page)%>
</center>
<SCRIPT LANGUAGE="JavaScript">
function goPage(pageNum)
{
	location.href = "<%= input.getSystem().get(SystemC.WEB_REQUEST_URI) %>?pgNum="+pageNum;
}
</SCRIPT>
