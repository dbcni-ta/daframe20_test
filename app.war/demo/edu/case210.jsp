<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/base/include/ArchJsp.jspf"%>

<link href="../css/demo.css" rel="stylesheet" type="text/css" />

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">

	<br> <b>대칭키에 의한 암호화 및 복호화 예시 </b>
	<br>
	<br> - 본 암호화는 서버단에서의 암호화로 클라이언트에서 서버간의 구간암호화는 고려하지 않음
	<br>
	<br>
	<br><b>요청문자열 : <%=input.getDefault("TEXT","NONE")%></b>
	<br><b>결과문자열 : <%=output.getDefault("RESULT_TEXT","NONE")%></b>
	<br>
	<form name="tableForm" target=right action="CASE210.R01.cmd?TYPE=ENCRYPT" method="post">
		<table width="70%" border="1" cellpadding="1" cellspacing="0"
			bordercolorlight="#C3C0C0" bordercolordark="FFFFFF">
			<tr>
				<td>암호화할 문자열</td>
				<td><input id="TEXT" name="TEXT" type="text" /></td>
			</tr>
		</table>
		<br> <input type="submit" value="암호화 요청" />
	</form>
	
	<form name="tableForm" target=right action="CASE210.R01.cmd?TYPE=DECRYPT" method="post">
		<table width="70%" border="1" cellpadding="1" cellspacing="0"
			bordercolorlight="#C3C0C0" bordercolordark="FFFFFF">
			<tr>
				<td>복호화할 문자열</td>
				<td><input id="TEXT" name="TEXT" type="text" value="<%=output.getDefault("RESULT_TEXT")%>" /></td>
			</tr>
		</table>
		<br> <input type="submit" value="복호화 요청" />
	</form>
</body>


