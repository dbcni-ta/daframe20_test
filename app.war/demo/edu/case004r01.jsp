<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>
<!-- 
DAFrame 에서 제공하는 기본 JS (일부 소트, AJAX 관련 기능이 있음)
 -->
<script type="text/javascript" src="<%= request.getContextPath() %>/base/js/daf.js"></script>

<link href="../css/demo.css" rel="stylesheet" type="text/css" />

<%
	LTO result_page = output.getLTO("result_page");
%>
<H1>국가정보 관리 (조회+등록폼)</H1>
<%@ include file="/demo/edu/include/std_grid.jsp" %>
<SCRIPT LANGUAGE="JavaScript">
function goPage(pageNum)
{
	location.href = "CASE004.R01.cmd?pgNum="+pageNum;
}
</SCRIPT>

<!-- 
TABLE 다음부분에 입력폼 필드를 동적으로 생성한다. colName 객체는 std_grid.jspf 에 선언되어 있음에 유의.
 -->
<br>
<form id="frmInsert" name="frmInsert" method="POST">
<table border=1>
	<tr>
<%	
	for (int i=0;i<colNames.size();i++) {
		String colStr = colNames.get(i);
%>
		<td><%= colStr %></td> 
<%
	}
%>		
	</tr>
	<tr>	
<%
	for (int i=0;i<colNames.size();i++) {
		String colStr = colNames.get(i);
%>
		<td><input id="<%= colStr %>" name="<%= colStr %>" type="text"/> </td>
<%
	}
%>	
	</tr>
</table>
</form>
<br><input type="button" value="등록1 (등록 후 재조회-CHAINED)" onclick="doInsertType1();"/>
<br><input type="button" value="등록2 (등록 후 단순 Alert 후 현 화면 유지)" onclick="doInsertType2();"/>
<br><input type="button" value="등록3 (AJAX를 사용한 등록, 화면갱신없음)" onclick="doInsertType3();"/>

<script type="text/javascript">
<!--
	function doInsertType1() {
		if (confirm('데이타를 등록합니다. 계속 진행하시겠습니까?') == true) {
		 	window.document.frmInsert.action = "CASE004.C01.cmd"
		 	window.document.frmInsert.submit();
	 	}
	}
	
	function doInsertType2() {
		if (confirm('데이타를 등록합니다. 계속 진행하시겠습니까?') == true) {
			window.document.frmInsert.action = "CASE004.C02.cmd"
		 	window.document.frmInsert.submit();
	 	}
	}
	
	function doInsertType3() {
		if (confirm('데이타를 등록합니다. 계속 진행하시겠습니까?') == true) {
		 	HTTP.post("CASE004.C03.cmd", window.document.frmInsert, callback);
	 	}
	}
	
	function callback(result) {
		alert(result);
		
		var doc = XML.parse(result);
		
		if (getBrowserType() == 1) {
			alert(doc.documentElement.selectSingleNode("*/Message").text);
		} else {
			alert(doc.getElementsByTagName("Message")[0].childNodes[0].nodeValue);
		}
		
		// 재조회.
		location.href = "CASE004.R01.cmd";
	}
	
//-->
</script>
