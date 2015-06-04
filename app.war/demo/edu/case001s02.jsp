<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>

<%
	MTO corpInfo = output.getMTO("corpInfo");
	ATO position = output.getATO("position");
	LTO teamList = output.getLTO("teamList");
%>

<link href="../css/demo.css" rel="stylesheet" type="text/css" />

<hr>
* 사원정보 (MASTER)
<br>
<br>
이름 : <%= output.get("name") %>
<br>
나이 : <%= output.get("age") %>
<br>
도시 : <%= output.get("city") %>
<br>
직급 : <%= position.get(IntMaker.make(output.get("position"))) %>
<br>
<hr>
* 소속회사 (MTO)
<br>
<br>
이름 : <%= corpInfo.get("corpName") %>
<br>
도시 : <%= corpInfo.get("corpCity") %>
<br>
<hr>
* 직급체계 (ATO)
<br>
<br>
<SELECT>
<%
	for (int i=0;i<position.size();i++) {
%>
	<OPTION > <%= position.get(i) %> </OPTION>
<%
	}
%>
</SELECT>
<hr>
* 팀원 (LTO)
<br>
<br>
<TABLE border=1>
<%
	MTO team = null;
	for (int i=0;i<teamList.size();i++) {
		team = teamList.get(i);
%>
		<tr>
			<td><%= i %></td>
			<td><%= team.get("name") %></td>
			<td><%= position.get(IntMaker.make(team.get("position"))) %></td>
		</tr>
<%
	}
%>
</TABLE>
<hr>