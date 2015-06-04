<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>

<%
	MTO titleWord = output.getMTO("TITLEWORD");
%>
<hr>
<br>
<%= titleWord.get("label1") %> : <input type=text />
<br>
<%= titleWord.get("label2") %> : <input type=text />
<br>
<%= titleWord.get("label3") %> : <input type=text />
<br>
<hr>
<a href="CASE102.L02.cmd?WORD=KOR">한글화면보기</a>
<a href="CASE102.L02.cmd?WORD=ENG">영문화면보기</a>
<hr>