<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>

<SCRIPT LANGUAGE="JavaScript">
	alert("<%= getResultMessage(request) %> ");
	history.go(-1);
</SCRIPT>

<!-- 
결과적으로 case001s03.jsp와 동일하다. 즉 이런것은 공통화 시키는게 좋다. (예:errMsg.jsp)
 -->