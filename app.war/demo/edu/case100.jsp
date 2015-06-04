<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>

<link href="../css/demo.css" rel="stylesheet" type="text/css" />

<script>
    function execute() {
        document.tableForm.submit();
    }
</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">

<br>
테이블의 FORM 정보 -> 서버 LTO 예시.
<br>

  <form name="tableForm" target=right action="CASE100.R01.cmd" method="post" >
      <input type="hidden" name="LTO_NAME" value="inputTable">
      <input type="hidden" name="LTO_HEADER" value="NAME">
      <input type="hidden" name="LTO_HEADER" value="ADDRESS">
      <input type="hidden" name="LTO_HEADER" value="PHONE">
      
      <!-- 아래는 실제로는 row단위별로 들어가야하겠지만 편의상 예시에서는 3줄을 내리 아래에 적었다. -->
      <input type="hidden" id="LTO_CUD_FLAG" name="LTO_CUD_FLAG" value="C" />
      <input type="hidden" id="LTO_CUD_FLAG" name="LTO_CUD_FLAG" value="U" />
      <input type="hidden" id="LTO_CUD_FLAG" name="LTO_CUD_FLAG" value="D" />
      
      <table width="70%" border="1" cellpadding="1" cellspacing="0" bordercolorlight="#C3C0C0" bordercolordark="FFFFFF">
      
    <tr>
		<td>NAME</td>
		<td>ADDRESS</td>
		<td>PHONE</td>
	</tr>
	<tr>
		<td><input id="NAME" name="NAME" type="text"/></td>
		<td><input id="ADDRESS" name="ADDRESS" type="text"/></td>
		<td><input id="PHONE" name="PHONE" type="text"/></td>
	</tr>
	<tr>
		<td><input id="NAME" name="NAME" type="text"/></td>
		<td><input id="ADDRESS" name="ADDRESS" type="text"/></td>
		<td><input id="PHONE" name="PHONE" type="text"/></td>
	</tr>
	<tr>
		<td><input id="NAME" name="NAME" type="text"/></td>
		<td><input id="ADDRESS" name="ADDRESS" type="text"/></td>
		<td><input id="PHONE" name="PHONE" type="text"/></td>
	</tr>
      </table>
      <br>
      <input type="submit" value="테이블 전송"/>
  </form>
