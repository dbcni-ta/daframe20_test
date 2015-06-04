<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>
<%@page import="com.cni.fw.meta.util.CmdMetaUtil"%>
<%@page import="com.cni.fw.meta.cmd.base.CommandMeta"%>
<%@page import="xlib.cmc.GridData"%>
<%@page import="xlib.cmc.OperateGridData"%>
<%
	if (cs != null) {
        CommandMeta cmdMeta = input.getCommand();
        String cmdStr = cmdMeta.getCommand();
        //�Ʒ��� ���� CMD ���� ��Ÿ�� ���ϱ� ���� ����.
        //debug("������������CMD:"+cmdStr);  // ���� �������� CMD�� ���ʿ� ��û�� CMD�� �ٸ����� �ִ� (CHAINED ���� ������ ����..)
        //debug("UC-ID:"+StrUtil.leftGetOff(cmdStr, ".", false));
        //debug("EV-ID:"+StrUtil.rightGetOff(cmdStr, ".", false));
        //debug("�Է�ä������(������):"+cmdMeta.getIpType());
        //debug("���ä������(������):"+cmdMeta.getOpType());
        //debug("�����������ο�û����������CMD:"+cs.getFirstCommand()); // CHAINED ������ ����� �̺�Ʈ�� ��� ����CMD�� ã������ �̿� ���� �ؾ��Ѵ�.
        //debug("�Է�ä������(����):"+CmdMetaUtil.getCommand(cs.getFirstCommand()).getIpType()); 
        //debug("���ä������(����):"+CmdMetaUtil.findLastOpType(cmdStr)); // CHAINED ������ ����� �̺�Ʈ�� ��� ���� CMD�� ���ä�������� ã������ �̿� ���� �ؾ��Ѵ�.
  
        String UC_ID = StrUtil.leftGetOff(cmdStr, ".", false);
        String lastOpType = CmdMetaUtil.findLastOpType(cs.getFirstCommand());
		
		// ���� ��� ä���� ������ XML �� ��û�� ���� ����Ÿ�Ӿƿ� ó��
		if (lastOpType.equals("XML")) {
	    	String msg = "������ �������� �ʽ��ϴ�.";
	        String code = "NOSESSION";

	        response.setContentType("text/xml");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setHeader("pragma","no-cache");
	        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	        out.println("<EffectDTO>");
	        out.println("<Summary>");
	        out.println("<Success>false</Success>");
	        out.println("<Code>" + code + "</Code>");
	        out.println("<Message>" + msg + "</Message>");
	        out.println("<TX>" + output.getTxId() +"</TX>");
	        out.println("</Summary>");
	        out.println("</EffectDTO>");
	            
	        return; 
	    // ���� ��� ä���� ������ WISE �� ��û�� ���� ����Ÿ�Ӿƿ� ó��
		} else if (lastOpType.equals("WISE")) {
			
			response.setContentType("text/html;charset=UTF-8");
			GridData gdObj = new GridData();
			gdObj.setMessage("������ �������� �ʽ��ϴ�.");
			gdObj.setStatus("NOSESSION");
			try {
				OperateGridData.write(gdObj, response.getWriter());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
	}
%>
<link href="<%= request.getContextPath() %>/base/css/cni.css" rel="stylesheet" type="text/css">
<br>
<br>
<b>�α����� ���°� �ƴϸ� ������ �� ���� ����Դϴ�. </b>
<br>
<br>
<font color="red">�α����� �����Ͻʽÿ�.</font>
