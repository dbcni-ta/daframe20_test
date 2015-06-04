<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>
<html>
<head>
<title>WISE220</title>

<link href="../../css/demo.css" rel="stylesheet" type="text/css" />

<script language='JavaScript' src='../js/WiseGrid_TAG.js'></script>
<script language='JavaScript' src='../js/WiseGrid_Property.js'></script>
<script language='JavaScript' src='../js/WiseGrid_DAFrame.js'></script>

<!--
	WiseGrid 오브젝트가 생성되고 초기화된 후 발생하는 
	JavaScript Event인 Initialize()를 받아 그리드의 헤더를 셋팅한다.	
-->
<script language=javascript for='WiseGrid' event='Initialize()'>
	init(this);
</script>

<!--  	WiseGrid의 Tree Node를 클릭했을때 발생한다.   -->
<script language=javascript for="WiseGrid" event="TreeNodeClick(strTreeKey, strArea)">
	GridClickMenu(strTreeKey, strArea);
</script>

<script language=javascript>
var globalTreeNodeNo = 1000;

function init(GridObj) {	
	setProperty(GridObj);

	//클릭한 컬럼의 셀을 선택한다 
	GridObj.strHDClickAction = "select";

	//헤더를 WiseGrid에서 숨긴다.
	//GridObj.bHDVisible = false;

	//Statusbar를 WiseGrid에서 숨긴다.
	GridObj.bStatusbarVisible = false;
	
	//로우 셀렉터를 WiseGrid에서 숨긴다.
	GridObj.bRowSelectorVisible = false;

	//셀의 테두리에 아무것도 나타나지 않는다.
	GridObj.strCellBorderStyle = "none";

	//그리드의의 테두리에 아무것도 나타나지 않는다.
	GridObj.strGridBorderStyle = "none";

	//로우의 테두리에 아무것도 나타나지 않는다.
	GridObj.strRowBorderStyle = "none";

	//사용자 컨텍스트메뉴를 사용한다.
	GridObj.bUserContextMenu = true;

	// 기본 컨텍스트 메뉴를 사용하지 않는다.
	GridObj.bUseDefaultContextMenu = false;

	//Drag로 선택된 셀의 배경색상을 변경할 수 있다 
	GridObj.strSelectedCellBgColor = "255|255|255";	
	
	setHeader(GridObj);

	doQuery();
}

function setHeader(GridObj) {    
	//그리드에 컬럼을 등록한다.	 
	GridObj.AddHeader("ORG", 		"조직",		"t_imagetext", 		100, 	195,	false);	
	GridObj.AddHeader("ORG_SUM", 	"조직원",	"t_number",			100, 	200,	false);	

	//AddHeader를 완료한 후 헤더를 그리드에 바인딩한다
	GridObj.BoundHeader();	

	//WiseGrid에 트리모드를 설정한다. 
	GridObj.SetTreeMode("ORG", "*", ","); 

	//이미지리스트에 이미지 URL을 추가한다.
	GridObj.AddImageList("ORG", "../images/menu_favorite.gif");
	GridObj.AddImageList("ORG", "../images/menu_folder_opened.gif");
	GridObj.AddImageList("ORG", "../images/menu_ie.gif");

	// Tree의 셀을 클릭해도 Expand되거나 Collapse되지 않게 한다.
	// TreeNodeClick 이벤트로 signbox를 클릭했을때만 Expand되도록 작업하기 위해
	// Tree를 움직이지 않게 한다.
	GridObj.SetTreeClickAction(false);
}

/* 조회 */
function doQuery() {

	var comQuery = new CommonQuery();
	comQuery.setGridUrl("WISE220.R01.cmd");
	comQuery.addGridObj("WiseGrid");
	
	comQuery.doGridQuery();
}

/* WiseGrid의 Tree Node를 클릭했을때 발생 */
function GridClickMenu(strTreeKey, strArea) {
	var GridObj = document.WiseGrid;

	var menu_flag = GridObj.GetCellImage("ORG", GridObj.GetRowIndexFromTreeKey(strTreeKey));
	if(strArea == "signbox") {
		if(GridObj.IsTreeNodeExpand(strTreeKey)) { //해당 트리 노드가 열려있는지 체크한다. 
			//해당 트리 노드를 닫는다. 
			GridObj.CollapseTreeNode(strTreeKey); 
			
		} else {
			//해당 트리 노드를 연다
			GridObj.ExpandTreeNode(strTreeKey, false); 
			
		}
	} else if(strArea == "text" || strArea == "image") {
		alert("아이템 클릭");
	}
}
</script>

</HEAD>
<BODY bgcolor='#FFFFFF' text='#000000'>
<form name="Form">
<input type="hidden" name="imageUrl" value="">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr height="20">
		<td class="title1_k">
			&nbsp;WISE220<br><hr>
		</td>
	</tr>
	<tr height="20">
		<td class="title2_k">
				트리 그리드 예제
		</td>
	</tr>
</table>

<hr>

<table width="98%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td >
			<script>initWiseGrid("WiseGrid", "100%", "350");</script>
		</td>
	</tr>
</table>
<br>

</form>
</BODY>
</HTML>
