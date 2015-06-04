<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/base/include/ArchJsp.jspf"%>

<link href="../css/demo.css" rel="stylesheet" type="text/css" />

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">

	<br> <b>메시지 압축에 의한 비밀번호 암호화 예시 </b>
	<br>
	<br> - 본 암호화는 서버단에서의 암호화로 클라이언트에서 서버간의 구간암호화는 고려하지 않음
	<br>
	<br>
	이전 비밀번호 : <%=input.getDefault("PASSWORD","NONE")%> [<%=output.getDefault("PRE_PASSWORD","NONE")%>]
	<br>
	<form name="tableForm" target=right action="CASE200.R01.cmd" method="post">
		<input id="PRE_PASSWORD" name="PRE_PASSWORD" type="hidden" value="<%=output.getDefault("PRE_PASSWORD")%>"/>
		<table width="70%" border="1" cellpadding="1" cellspacing="0"
			bordercolorlight="#C3C0C0" bordercolordark="FFFFFF">
			<tr>
				<td>비밀번호</td>
				<td><input id="PASSWORD" name="PASSWORD" type="text" /></td>
			</tr>
		</table>
		<br> <input type="submit" value="비밀번호 전송" />
	</form>
</body>


<%
	String checkSum = output.get("CHECKSUM");

	// 처음 화면을 열었거나, 최초로 비밀번호를 전송했을때
	if (checkSum == null) {
%>	

<%		
	// 재차 비밀번호를 전송한 후 서버에서 MD 처리를 한 결과로 이전 MD된 비밀번호와 비교해 일치할 경우
	} else if (checkSum.equals("YES")) {
%>	
<script>
	alert("[일치] 이전에 입력한 비밀번호와 일치합니다.");
</script>
<%
	// 일치하지 않을 경우
	} else if (checkSum.equals("NO")) {
%>
<script>
	alert("[불일치] 이전에 입력한 비밀번호와 일치하지 않습니다.");
</script>
<%
	}
%>


