<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>

<%
	LTO result_A = output.getLTO("result_A");
	
	MTO row = null;
%>

<link href="../css/demo.css" rel="stylesheet" type="text/css" />

<H1> 경기장정보 </H1>
<table width="100%" border="1" cellpadding="1" cellspacing="1" >
	<tr>
		<td><b>경기장명</b></td>
		<td><b>주소</b></td>
		<td><b>좌석수</b></td>
	</tr>

<%	
	for (int i=0;i<result_A.size();i++) {
%>
<tr>
<%		
		row = result_A.get(i);
%>
	<td>
		<%= row.get("NAME") %>
	</td>
	<td>
		<%= row.get("ADDRESS") %>
	</td>
	<td>
		<%= row.get("SEATS") %>
	</td>
</tr>
<%
	}
%>
</table>

