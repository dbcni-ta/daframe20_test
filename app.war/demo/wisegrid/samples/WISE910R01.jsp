<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>
<%@page import="com.cni.fw.meta.msg.MessageRepository"%>
<%@page import="xlib.cmc.OperateGridData"%>
<%@page import="xlib.cmc.GridData"%>
<%

GridData gridData = null;

response.setContentType("text/html;charset=UTF-8");

try {
	// 그리드 객체를 아예 새롭게 만든다.
	gridData = new GridData();
	
	int colCnt = IntMaker.make(input.getMTO("WiseGrid").get("colCnt"),1);
	
	// 정적 컬럼을 선언한다.
	gridData.addDynamicHeader("MEMO", "메모", OperateGridData.t_text, "30", "150", "true");
	
	// 동적 컬럼을 선언한다.
	for (int i=0;i<colCnt;i++) {
		gridData.addDynamicHeader("DYNAMIC_"+i, "다이나믹_"+i, OperateGridData.t_text, "30", "150", "true");
	}
	
	// 출력할 LTO 객체.
	LTO result = output.getLTO("result");
	
	// WISE에 데이타 매핑
	MTO row = null;
	for (int i = 0; i < result.size(); i++) {
		row = result.get(i);
		gridData.getHeader("MEMO").addValue(row.get("MEMO"), "");
		for (int j=0;j<colCnt;j++) {
			gridData.getHeader("DYNAMIC_"+j).addValue(row.get("DYNAMIC_"+j), "");
		}
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
