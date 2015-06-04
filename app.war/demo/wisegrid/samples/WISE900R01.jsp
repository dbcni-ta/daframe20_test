<%@page import="xlib.cmc.OperateGridData"%>
<%@page import="xlib.cmc.GridData"%>
<%@ page contentType="text/html; charset=euc-kr"%>
<%

GridData gdReq = null;
GridData gdRes = null;

// Encode Type�� UTF-8�� ��ȯ�Ѵ�.
response.setContentType("text/html;charset=UTF-8");

int rowCount = 0;

try {
	// WISEGRID_DATA ��� Param���� WiseGrid�� ������ �ö�´�.
	String rawData = request.getParameter("WISEGRID_DATA");			
	
	// �ö�� ������ Parsing�Ͽ� �ڷᱸ�� ���·� ��ȯ���ش�.
	gdReq = OperateGridData.parse(rawData);
				
	// ���޹��� �Ķ���Ͱ��� �����´�.
	String mode = gdReq.getParam("mode");		

	gdRes = OperateGridData.cloneResponseGridData(gdReq);			
							 
	// ������ ����
	rowCount = 6;
	for (int i = 0; i < rowCount; i++) {
		gdRes.getHeader("SEQ_NO").addValue(String.valueOf(i++), "");
		gdRes.getHeader("SELECTED").addValue("0", "");
		gdRes.getHeader("B_CATE").addSelectedHiddenValue(gdRes.getHeader("B_CATE").getComboListKey(0), "C");
		gdRes.getHeader("M_CATE").addSelectedHiddenValue("C", "C1");
		gdRes.getHeader("S_CATE").addSelectedHiddenValue("C1", "N1");			
		gdRes.getHeader("GOODS").addValue("�Ｚ ��Ʈ�� sens R70A /w251 ��������/�����콺/�е� ����", "");
		gdRes.getHeader("COMPANY").addValue("�Ｚ����", ""); 
		gdRes.getHeader("SELLER").addValue("pcstaion", "");
		gdRes.getHeader("PRICE").addValue("1804950", "");
		
		gdRes.getHeader("SEQ_NO").addValue(String.valueOf(i++), "");
		gdRes.getHeader("SELECTED").addValue("0", "");
		gdRes.getHeader("B_CATE").addSelectedHiddenValue(gdRes.getHeader("B_CATE").getComboListKey(0), "C");
		gdRes.getHeader("M_CATE").addSelectedHiddenValue("C", "C1");
		gdRes.getHeader("S_CATE").addSelectedHiddenValue("C1", "N9");			
		gdRes.getHeader("GOODS").addValue("MackBook MB403KH/A", "");
		gdRes.getHeader("COMPANY").addValue("Apple", ""); 
		gdRes.getHeader("SELLER").addValue("��Ʈ��", "");
		gdRes.getHeader("PRICE").addValue("1321370", "");
		
		gdRes.getHeader("SEQ_NO").addValue(String.valueOf(i++), "");
		gdRes.getHeader("SELECTED").addValue("0", "");
		gdRes.getHeader("B_CATE").addSelectedHiddenValue(gdRes.getHeader("B_CATE").getComboListKey(0), "C");
		gdRes.getHeader("M_CATE").addSelectedHiddenValue("C", "C2");
		gdRes.getHeader("S_CATE").addSelectedHiddenValue("C2", "A1");			
		gdRes.getHeader("GOODS").addValue("�������� E8400  2G�� ���� 945 FX8600GT -512M �Ｚ 250G ", "");
		gdRes.getHeader("COMPANY").addValue("Feel.N", ""); 
		gdRes.getHeader("SELLER").addValue("Feel-N", "");
		gdRes.getHeader("PRICE").addValue("489000", "");
		
		gdRes.getHeader("SEQ_NO").addValue(String.valueOf(i++), "");
		gdRes.getHeader("SELECTED").addValue("0", "");
		gdRes.getHeader("B_CATE").addSelectedHiddenValue(gdRes.getHeader("B_CATE").getComboListKey(0), "C");
		gdRes.getHeader("M_CATE").addSelectedHiddenValue("C", "C3");
		gdRes.getHeader("S_CATE").addSelectedHiddenValue("C3", "D2");			
		gdRes.getHeader("GOODS").addValue("DB-Z68/Z200", "");
		gdRes.getHeader("COMPANY").addValue("�Ｚ����", ""); 
		gdRes.getHeader("SELLER").addValue("noteprime-N", "");
		gdRes.getHeader("PRICE").addValue("2000000", "");
		
		gdRes.getHeader("SEQ_NO").addValue(String.valueOf(i++), "");
		gdRes.getHeader("SELECTED").addValue("0", "");
		gdRes.getHeader("B_CATE").addSelectedHiddenValue(gdRes.getHeader("B_CATE").getComboListKey(0), "M");
		gdRes.getHeader("M_CATE").addSelectedHiddenValue("M", "M1");
		gdRes.getHeader("S_CATE").addSelectedHiddenValue("M1", "M4");			
		gdRes.getHeader("GOODS").addValue("e-zone 220WT [55.88cm(22)]", "");
		gdRes.getHeader("COMPANY").addValue("�ٺ���÷���", ""); 
		gdRes.getHeader("SELLER").addValue("�ٺ���÷���", "");
		gdRes.getHeader("PRICE").addValue("257200", "");
		
		gdRes.getHeader("SEQ_NO").addValue(String.valueOf(i++), "");
		gdRes.getHeader("SELECTED").addValue("0", "");
		gdRes.getHeader("B_CATE").addSelectedHiddenValue(gdRes.getHeader("B_CATE").getComboListKey(0), "M");
		gdRes.getHeader("M_CATE").addSelectedHiddenValue("M", "M1");
		gdRes.getHeader("S_CATE").addSelectedHiddenValue("M1", "M11");			
		gdRes.getHeader("GOODS").addValue("ZEUS7000 240MA-8FD(P) DELUXE", "");
		gdRes.getHeader("COMPANY").addValue("��Ƽ���������", ""); 
		gdRes.getHeader("SELLER").addValue("icarus", "");
		gdRes.getHeader("PRICE").addValue("619000", "");
		
		gdRes.getHeader("SEQ_NO").addValue(String.valueOf(i++), "");
		gdRes.getHeader("SELECTED").addValue("0", "");
		gdRes.getHeader("B_CATE").addSelectedHiddenValue(gdRes.getHeader("B_CATE").getComboListKey(0), "M");
		gdRes.getHeader("M_CATE").addSelectedHiddenValue("M", "M1");
		gdRes.getHeader("S_CATE").addSelectedHiddenValue("M1", "M11");			
		gdRes.getHeader("GOODS").addValue("LG �÷�Ʈ�� W1952TQ-PF", "");
		gdRes.getHeader("COMPANY").addValue("LG", ""); 
		gdRes.getHeader("SELLER").addValue("net-market", "");
		gdRes.getHeader("PRICE").addValue("245000", "");
	}		
		
	/* ȭ�鿡 ������ �Ķ���͸� �����Ѵ�.
	 * �޼����� �����Ѵ�.
	 * Status�� �����Ѵ�
	 */		
	gdRes.addParam("mode", "search");		
	gdRes.setMessage("���������� �۾��Ͽ����ϴ�.");
	gdRes.setStatus("true");
	
} catch (Exception e) {
	gdRes = new GridData();
	gdRes.setMessage("Error: " + e.getMessage());
	gdRes.setStatus("false");
	e.printStackTrace();
} finally {
	try {
		// �ڷᱸ���� �������� ������ Write�Ѵ�.			 
		OperateGridData.write(gdRes, out);		 
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
%>
