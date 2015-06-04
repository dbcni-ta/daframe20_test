<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>

<%
	LTO result_B = output.getLTO("result_B");
	
	MTO row = null;
%>

<link href="../css/demo.css" rel="stylesheet" type="text/css" />


<H1> 국가정보 </H1>
<table width="100%" border="1" cellpadding="1" cellspacing="1" >
	<tr>
		<td><b>코드</b></td>
		<td><b>국가명</b></td>
		<td><b>수도명</b></td>
		<td><b>대륙명</b></td>
	</tr>

<%	
	for (int i=0;i<result_B.size();i++) {
%>
<tr>
<%		
		row = result_B.get(i);
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

