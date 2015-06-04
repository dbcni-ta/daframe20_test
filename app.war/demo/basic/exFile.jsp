<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page extends="com.cni.fw.arch.ArchJsp" %>
<%@ page import="com.cni.fw.ff.dto.*, com.cni.fw.ff.dto.entity.*, com.cni.fw.ff.dto.support.*" %>

<%
	EffectDTO output = (EffectDTO) getEffectDTO(request);
%>


<html>
<head>
<title>CNI Framework Example</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../base/css/cni.css" rel="stylesheet" type="text/css">
<script>
    function executeUpload() {
        document.uploadForm.submit();
    }
    
    function deleteFile(target) {
        document.dummyForm.action = "BIZ005.F03.cmd?fileName="+target;
        document.dummyForm.submit();
    }
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">
<table width="100%" border="0" cellspacing="1" cellpadding="1">
  <tr>
    <td height="50" align="left" class="tle">▒▒▒ 파일 업/다운로드 예제 ▒▒▒</td>
  </tr>
</table>

<form name="dummyForm" target=right method="post">
</form>

<table width="100%" border="0" cellspacing="1" cellpadding="1">
<% 
	ATO fileList = output.getATO("fileList");
	String fileName = null;
	for (int i=0;i<fileList.size();i++) {
		fileName = fileList.get(i);
%>
  <tr>
    <td><input type=button value="삭제" onclick="deleteFile('<%= fileName %>');"/> 저장파일<%= i+1 %> : <a href="BIZ005.F02.cmd?fileName=<%= fileName %>"><%= fileName %></a> </td>
  </tr>
<%
	}
%>
</table>
<br>
    <form name="uploadForm" target=right action="BIZ005.F01.cmd" method="post" enctype="multipart/form-data">
        <input type="hidden" name="attachSize" value="3">
        
        <table width="70%" border="1" cellpadding="1" cellspacing="0" bordercolorlight="#C3C0C0" bordercolordark="FFFFFF">
            <tr>
                <td class="tdr_c" colspan="2">Upload Files</td>
                <td class="td_c" rowspan="4" width="100">
                    <input type="button" value="Upload" title="Upload" class="butt"
                     onClick="executeUpload();">
                </td>
            </tr>
            <tr>
                <td class="tdn_c" width="50">file0</td>
                <td class="td_l"><input type="file" name="attach" size="35"></td>
            </tr>
            <tr>
                <td class="tdn_c">file1</td>
                <td class="td_l"><input type="file" name="attach" size="35"></td>
            </tr>
            <tr>
                <td class="tdn_c">file2</td>
                <td class="td_l"><input type="file" name="attach" size="35"></td>
            </tr>
        </table>
    </form></element>
<br>

<script>
<%
	if (!getResultCode(request).equals("S-001")) {
%>
	alert("<%= getResultMessage(request) %>");
<%
	}
%>
</script>

</body>
</html>
