<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>

<link href="../css/demo.css" rel="stylesheet" type="text/css" />


<H1>파일업로드</H1>
<form name="uploadForm" action="CASE006.F02.cmd" method="post" enctype="multipart/form-data">
	<br>
	<input type="file" name="file" size="35">
	<br>
    <input type="submit" value="업로드">
</form>

