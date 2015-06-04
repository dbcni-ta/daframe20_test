<%@ page contentType="text/html; charset=UTF-8" %>
<!-- 
	본 JSP 는 include 전용임.
	
	include 하는 부모 Page는 반드시 LTO result_page 객체를 가지고 있어야함.
 -->
<%
	MTO row = null;
	
	// $Caption 을 통해 입력한 캡션 정보
	Caption caption = (Caption) result_page.getInfoMap().getObject(LtoInfoC.COL_CAPTN_CAPTION);
	
	// Tactics 처리시 저장되어 있는 컬럼이름의 순서가 담긴 ATO
	ATO colNames = (ATO) result_page.getInfoMap().getObject(LtoInfoC.COL_NAMES_ATO);
	
	ATO capNames = null;
	// $Caption 을 통해 입력한 캡션의 문자정보
	if (caption.getCapText().size() > 0) {
		capNames = caption.getCapText();
	} else {
		// 캡션 정보가 없다면 컬럼명을 그대로 사용.
		capNames = colNames;
	}
	// $Caption 을 통해 입력한 캡션의 사이즈정보
	ATO capSizes = null;
	if (caption.getCapSize().size() > 0) {
		capSizes = caption.getCapSize();
	} 
%>

<b>전체 : <%= result_page.getInfo(LtoInfoC.ROW_TOT_STR) %> [페이지수 : <%= result_page.getInfo(LtoInfoC.PG_TOT_STR)  %>]</b>
<br>
<table width="100%" border="1" cellpadding="1" cellspacing="1" >
<tr>
<%	
	// capName 및 capSize를 사용해 tsql에서 $caption을 사용할 수 있다.
	for (int i=0;i<capNames.size();i++) {
		// capSize를 따로 등록안했을 경우 처리 : 디폴트 100
		String widthDefault = "100";
		// capSize가 등록되어 있다면 (사이즈 * 8) 픽셀로 처리함.
		if (capSizes != null) {
			widthDefault = CalUtil.math(capSizes.get(i),'*',"8");
		}
%>
	<td width=<%= widthDefault %>>
		<b><%= capNames.get(i) %></b>
	</td>
<%
	}
%>	
	
</tr>
<%	
	for (int i=0;i<result_page.size();i++) {
%>
<tr>
<%		
		row = result_page.get(i);

		Nexter nexter = colNames.getNexter(); // case002r01.jsp 와 다르게, 이젠 컬럼순서를 알수 있다.
		
		while (nexter.hasNext()) {
			String colName = nexter.next();
			String colValue = row.get(colName);
		%>
			<td>
				<%= colValue %>
			</td>
		<%
		}
		%>
</tr>
		<%
	}
		%>

</table>
<center>
<%=PageNavi.getInstance().printPageNavi(result_page)%>
</center>