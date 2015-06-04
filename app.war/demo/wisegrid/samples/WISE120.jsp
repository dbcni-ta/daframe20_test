<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>
<html>
<head>
<title>WISE120</title>

<link href="../../css/demo.css" rel="stylesheet" type="text/css" />

<script language='JavaScript' src='../js/WiseGrid_TAG.js'></script>
<script language='JavaScript' src='../js/WiseGrid_Property.js'></script>
<script language='JavaScript' src='../js/WiseGrid_DAFrame.js'></script>

<!--
	WiseGrid 오브젝트가 생성되고 초기화된 후 발생하는 
	JavaScript Event인 Initialize()를 받아 그리드의 헤더를 셋팅한다.	
-->
<script language=javascript for="WiseGrid" event="Initialize()">
	init(this);
</script>

<!--  	서버와의 통신이 정상적으로 완료되면 발생한다.   -->
<script language=javascript for="WiseGrid" event="EndQuery()">
	GridEndQueryCommon(this); // 공통 후처리
	GridEndQuery(this); // 트랜잭션 후처리
</script>

<script language="JavaScript">

function init(GridObj) {
	setHeader(GridObj);
	setProperty(GridObj);
}

function setHeader(GridObj) {		  
	//그리드에 컬럼을 등록한다.	 
	GridObj.AddHeader("CRUD",			"구분",			"t_text", 		8, 		40,		true);
	GridObj.AddHeader("CODE", 			"코드",			"t_text", 		5, 		50,		true);		
	GridObj.AddHeader("NATION_CODE", 	"국가코드",		"t_text", 		3, 		70,		true);		
	GridObj.AddHeader("NAME", 			"경기장명",		"t_text", 		50, 	160,	true);	
	GridObj.AddHeader("SEATS", 			"좌석수",		"t_number", 	20, 	80,		true);
	GridObj.AddHeader("ADDRESS", 		"주소",			"t_text", 		200, 	200,	true);
	
	//AddHeader를 완료한 후 헤더를 그리드에 바인딩한다
	GridObj.BoundHeader();
	
	GridObj.SetNumberFormat("SEATS", "#,##0");

	//저장모드를 사용해 서버사이드와 통신한다. 	
	GridObj.SetCRUDMode("CRUD", "생성", "수정", "삭제");
}

/*  조회 */
function doQuery() {

	var comQuery = new CommonQuery();
	comQuery.setGridUrl("WISE120.R01.cmd");
	comQuery.addGridObj("WiseGrid");
	comQuery.addGridParam("WiseGrid", "mode", "search");
	
	comQuery.doGridQuery();

}

/* 저장 */
function doSave() {

	var comQuery = new CommonQuery();
	comQuery.setGridUrl("WISE120.X01.cmd");
	comQuery.addGridObj("WiseGrid","CRUD");
	comQuery.addGridParam("WiseGrid", "mode", "save");
	
	comQuery.doGridTrans();
}

/* 삭제 */
function doDelete() {
	var GridObj = document.WiseGrid;
	// Active된 로우의 인덱스 위치의 행을 삭제한다. 
	GridObj.DeleteRow(GridObj.GetActiveRowIndex());
}

/* 행추가 */
function doLineInsert() {
	var GridObj = document.WiseGrid;
	
	//그리드의 마지막 열에 빈 로우를 추가한다. 
	GridObj.AddRow();	
}



/* 저장모드에서 저장 플래그를 모두 삭제하고 초기 데이터로 롤백한다. */
function doSaveCancel() {
	var GridObj = document.WiseGrid;
	
	if(confirm("저장 플래그를 모두 초기화 합니다"))
		GridObj.CancelCRUD();
}

/* 서버와의 통신이 정상적으로 완료되면 발생한다. */
function GridEndQuery() {
	var GridObj = document.WiseGrid;
}

</script>
</head>

<body>
<form name="form">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr height="20">
    <td class="title1_k">
    	&nbsp;WISE120<br><hr>
    </td>
  </tr>
  <tr height="20">
    <td class="title2_k">
			WISE100 예제와 다른점은 저장시 서버에서 재조회 실시하는 것임.
    </td>
  </tr> 
</table>
<hr>
<table width="98%" border="0" cellspacing="0" cellpadding="0"  class="title3">
	<tr>
		<table width="100%" border="0" cellpadding="2" cellspacing="0" >
			<tr>
				<td valign="top" align="right">
					<table border=0 cellpadding="0" cellspacing="0">
						<tr>
							<td><script language="javascript">btn("doQuery()","조회")</script></td>	
							<td><script language="javascript">btn("doDelete()","삭제")</script></td>
							<td><script language="javascript">btn("doLineInsert()","행추가")</script></td>
							<td><script language="javascript">btn("doSave()","저장")</script></td>
							<td><script language="javascript">btn("doSaveCancel()","저장취소")</script></td>
						</tr>
					</table>
				 </td>
			</tr>
		</table>
	  </tr>
</table>

 <table width="100%" border="0" cellpadding="0" cellspacing="0" >
	<tr> 
		<td bgcolor="#00455d" height="3"></td>
	</tr>
	<tr>
		<td align='left'>
			<script>initWiseGrid("WiseGrid", "100%", "350");</script>
		</td>
	</tr>
</table>

<br>

</form>
</body>
</html>
