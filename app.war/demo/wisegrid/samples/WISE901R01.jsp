<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>
<%@page import="com.cni.fw.meta.msg.MessageRepository"%>
<%@page import="xlib.cmc.OperateGridData"%>
<%@page import="xlib.cmc.GridData"%>
<%

GridData gridData = null;

response.setContentType("text/html;charset=UTF-8");

try {
	// 그리드 객체를 얻는다. "WISEGRID_"+ 실제 그리드명
	gridData = OperateGridData.cloneResponseGridData((GridData)input.getSystem().getObject("WISEGRID_WiseGrid"));
	
	// 출력할 LTO 객체.
	LTO result = output.getLTO("result");
	
	// WISE에 데이타 매핑
	MTO row = null;
	for (int i = 0; i < result.size(); i++) {
		row = result.get(i);
		// 주의해야할 점은 모든 컬럼을 포함시켜야한다. (없으면 에러남)
		// CRUD필드 (일반값처럼 빈스트링이라도 넣어주어야한다)
		gridData.getHeader("CRUD").addValue("", "");
		// 다단콤보값을 설정 (상위콤보키, 자신의값)
		gridData.getHeader("COMBO1").addSelectedHiddenValue("ROOT",  	       row.get("COMBO1"));
		gridData.getHeader("COMBO2").addSelectedHiddenValue(row.get("COMBO1"), row.get("COMBO2"));
		gridData.getHeader("COMBO3").addSelectedHiddenValue(row.get("COMBO2"), row.get("COMBO3"));
		// 일반값
		gridData.getHeader("MEMO").addValue(row.get("MEMO"), "");
	}		

	// 결과코드
	gridData.setStatus(output.getCode());
	// 결과메시지
	gridData.setMessage(MessageRepository.getInstance().getMessage(output.getCode(), output.getMessage()));
	
} catch (Exception e) {
	gridData = new GridData();
	// 에러코드
	gridData.setStatus("E-001");
	// 에러메시지
	gridData.setMessage("Error: " + e.getMessage());
	e.printStackTrace();
} finally {
	try {
		// 전문 전송
		OperateGridData.write(gridData, out);		 
	} catch (Exception e) {
		e.printStackTrace();
	}
}
%>
