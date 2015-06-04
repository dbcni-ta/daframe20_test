<%@page import="xlib.cmc.OperateGridData"%>
<%@page import="xlib.cmc.GridData"%>
<%@ page contentType="text/html; charset=euc-kr"%>
<%

GridData gdReq = null;
GridData gdRes = null;

// Encode Type을 UTF-8로 변환한다.
response.setContentType("text/html;charset=UTF-8");

int rowCount = 0;

try {
	// WISEGRID_DATA 라는 Param으로 WiseGrid의 전문이 올라온다.
	String rawData = request.getParameter("WISEGRID_DATA");			
	
	// 올라온 전문을 Parsing하여 자료구조 형태로 반환해준다.
	gdReq = OperateGridData.parse(rawData);
				
	// 전달받은 파라미터값을 가져온다.
	String mode = gdReq.getParam("mode");		

	gdRes = OperateGridData.cloneResponseGridData(gdReq);			
							 
	// 데이터 셋팅
	rowCount = 6;
	for (int i = 0; i < rowCount; i++) {
		gdRes.getHeader("SEQ_NO").addValue(String.valueOf(i++), "");
		gdRes.getHeader("SELECTED").addValue("0", "");
		gdRes.getHeader("B_CATE").addSelectedHiddenValue(gdRes.getHeader("B_CATE").getComboListKey(0), "C");
		gdRes.getHeader("M_CATE").addSelectedHiddenValue("C", "C1");
		gdRes.getHeader("S_CATE").addSelectedHiddenValue("C1", "N1");			
		gdRes.getHeader("GOODS").addValue("삼성 노트북 sens R70A /w251 가방쿠폰/광마우스/패드 증정", "");
		gdRes.getHeader("COMPANY").addValue("삼성전자", ""); 
		gdRes.getHeader("SELLER").addValue("pcstaion", "");
		gdRes.getHeader("PRICE").addValue("1804950", "");
		
		gdRes.getHeader("SEQ_NO").addValue(String.valueOf(i++), "");
		gdRes.getHeader("SELECTED").addValue("0", "");
		gdRes.getHeader("B_CATE").addSelectedHiddenValue(gdRes.getHeader("B_CATE").getComboListKey(0), "C");
		gdRes.getHeader("M_CATE").addSelectedHiddenValue("C", "C1");
		gdRes.getHeader("S_CATE").addSelectedHiddenValue("C1", "N9");			
		gdRes.getHeader("GOODS").addValue("MackBook MB403KH/A", "");
		gdRes.getHeader("COMPANY").addValue("Apple", ""); 
		gdRes.getHeader("SELLER").addValue("노트북", "");
		gdRes.getHeader("PRICE").addValue("1321370", "");
		
		gdRes.getHeader("SEQ_NO").addValue(String.valueOf(i++), "");
		gdRes.getHeader("SELECTED").addValue("0", "");
		gdRes.getHeader("B_CATE").addSelectedHiddenValue(gdRes.getHeader("B_CATE").getComboListKey(0), "C");
		gdRes.getHeader("M_CATE").addSelectedHiddenValue("C", "C2");
		gdRes.getHeader("S_CATE").addSelectedHiddenValue("C2", "A1");			
		gdRes.getHeader("GOODS").addValue("울프데일 E8400  2G램 인텔 945 FX8600GT -512M 삼성 250G ", "");
		gdRes.getHeader("COMPANY").addValue("Feel.N", ""); 
		gdRes.getHeader("SELLER").addValue("Feel-N", "");
		gdRes.getHeader("PRICE").addValue("489000", "");
		
		gdRes.getHeader("SEQ_NO").addValue(String.valueOf(i++), "");
		gdRes.getHeader("SELECTED").addValue("0", "");
		gdRes.getHeader("B_CATE").addSelectedHiddenValue(gdRes.getHeader("B_CATE").getComboListKey(0), "C");
		gdRes.getHeader("M_CATE").addSelectedHiddenValue("C", "C3");
		gdRes.getHeader("S_CATE").addSelectedHiddenValue("C3", "D2");			
		gdRes.getHeader("GOODS").addValue("DB-Z68/Z200", "");
		gdRes.getHeader("COMPANY").addValue("삼성전자", ""); 
		gdRes.getHeader("SELLER").addValue("noteprime-N", "");
		gdRes.getHeader("PRICE").addValue("2000000", "");
		
		gdRes.getHeader("SEQ_NO").addValue(String.valueOf(i++), "");
		gdRes.getHeader("SELECTED").addValue("0", "");
		gdRes.getHeader("B_CATE").addSelectedHiddenValue(gdRes.getHeader("B_CATE").getComboListKey(0), "M");
		gdRes.getHeader("M_CATE").addSelectedHiddenValue("M", "M1");
		gdRes.getHeader("S_CATE").addSelectedHiddenValue("M1", "M4");			
		gdRes.getHeader("GOODS").addValue("e-zone 220WT [55.88cm(22)]", "");
		gdRes.getHeader("COMPANY").addValue("다비디스플레이", ""); 
		gdRes.getHeader("SELLER").addValue("다비디스플레이", "");
		gdRes.getHeader("PRICE").addValue("257200", "");
		
		gdRes.getHeader("SEQ_NO").addValue(String.valueOf(i++), "");
		gdRes.getHeader("SELECTED").addValue("0", "");
		gdRes.getHeader("B_CATE").addSelectedHiddenValue(gdRes.getHeader("B_CATE").getComboListKey(0), "M");
		gdRes.getHeader("M_CATE").addSelectedHiddenValue("M", "M1");
		gdRes.getHeader("S_CATE").addSelectedHiddenValue("M1", "M11");			
		gdRes.getHeader("GOODS").addValue("ZEUS7000 240MA-8FD(P) DELUXE", "");
		gdRes.getHeader("COMPANY").addValue("비티씨정보통신", ""); 
		gdRes.getHeader("SELLER").addValue("icarus", "");
		gdRes.getHeader("PRICE").addValue("619000", "");
		
		gdRes.getHeader("SEQ_NO").addValue(String.valueOf(i++), "");
		gdRes.getHeader("SELECTED").addValue("0", "");
		gdRes.getHeader("B_CATE").addSelectedHiddenValue(gdRes.getHeader("B_CATE").getComboListKey(0), "M");
		gdRes.getHeader("M_CATE").addSelectedHiddenValue("M", "M1");
		gdRes.getHeader("S_CATE").addSelectedHiddenValue("M1", "M11");			
		gdRes.getHeader("GOODS").addValue("LG 플래트론 W1952TQ-PF", "");
		gdRes.getHeader("COMPANY").addValue("LG", ""); 
		gdRes.getHeader("SELLER").addValue("net-market", "");
		gdRes.getHeader("PRICE").addValue("245000", "");
	}		
		
	/* 화면에 전달할 파라미터를 설정한다.
	 * 메세지를 셋팅한다.
	 * Status를 설정한다
	 */		
	gdRes.addParam("mode", "search");		
	gdRes.setMessage("성공적으로 작업하였습니다.");
	gdRes.setStatus("true");
	
} catch (Exception e) {
	gdRes = new GridData();
	gdRes.setMessage("Error: " + e.getMessage());
	gdRes.setStatus("false");
	e.printStackTrace();
} finally {
	try {
		// 자료구조를 전문으로 변경해 Write한다.			 
		OperateGridData.write(gdRes, out);		 
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
%>
