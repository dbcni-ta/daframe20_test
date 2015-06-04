<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>
<html>
<head>
<title>WISE300</title>

<link href="../../css/demo.css" rel="stylesheet" type="text/css" />

<script language='JavaScript' src='../js/WiseGrid_TAG.js'></script>
<script language='JavaScript' src='../js/WiseGrid_Property.js'></script>
<script language='JavaScript' src='../js/WiseGrid_DAFrame.js'></script>


<!-- 
멀티 그리드 초기화.
 -->
<script language=javascript for="WiseGrid" event="Initialize()">
	init(this);
</script>

<script language=javascript for="WiseGridMulti" event="Initialize()">
	init(this);
</script>

<!--  	서버와의 통신이 정상적으로 완료되면 발생한다.   -->
<script language=javascript for="WiseGrid" event="EndQuery()">
	GridEndQueryCommon(this); // 공통 후처리
	GridEndQueryMaster(this); // 멀티-마스터그리드 후처리
</script>

<script language=javascript for="WiseGridMulti" event="EndQuery()">
	GridEndQuerySlave(this); // 멀티-슬레이브그리드 후처리
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
	comQuery.addGridObj("WiseGrid");
	comQuery.addGridObj("WiseGridMulti");
	comQuery.setGridUrl("WISE300.R01.cmd");
	comQuery.addGridParam("WiseGrid", "mode", "search");
	comQuery.addGridParam("WiseGridMulti", "mode", "search");
	comQuery.doGridQuery();

}

/* 저장 */
function doSave() {

	var comQuery = new CommonQuery();
	comQuery.setGridUrl("WISE300.X01.cmd");
	comQuery.addGridObj("WiseGrid","CRUD");
	comQuery.addGridObj("WiseGridMulti", "CRUD");
	comQuery.addGridParam("WiseGrid", "mode", "save");
	comQuery.addGridParam("WiseGridMulti", "mode", "save");
	
	comQuery.doGridTrans();
}


/* 저장모드에서 저장 플래그를 모두 삭제하고 초기 데이터로 롤백한다. */
function doSaveCancel() {
	var GridObj = document.WiseGrid;
	var GridObjMulti = document.WiseGridMulti;
	
	if(confirm("저장 플래그를 모두 초기화 합니다")) {
		GridObj.CancelCRUD();
		GridObjMulti.CancelCRUD();
	}
}

/* 서버와의 통신이 정상적으로 완료되면 발생한다. -MASTER 그리드- */
function GridEndQueryMaster(GridObj) {
	
	//요청한 CMD 명을 사용해서 조회/저장 별 후처리를 진행한다.
	var cmd = GridObj.GetParam("WISEGRID_CMD");

	if (cmd == "WISE300.R01") { 
		if(GridObj.GetStatus() == "S-001") 	{// 서버에서 전송한 상태코드를 가져온다.
			alert("[그리드1번]"+ GridObj.GetParam("total") + " 건이 조회되었습니다.");
		} else	{
			var error_msg = GridObj.GetMessage(); // 서버에서 전송한 상태코드값이 false라면 에러메세지를 가져온다.
			alert(error_msg);			
		}
	} else if (cmd == "WISE300.X01") {
		if(GridObj.GetStatus() == "S-001") 	{// 서버에서 전송한 상태코드를 가져온다.
			//서버에서 saveData 셋팅한 파라미터를 가져온다.
			//alert("[그리드1번]"+ GridObj.GetParam("total") + " 건이 저장되었습니다.");
		} else	{
			var error_msg = GridObj.GetMessage(); // 서버에서 전송한 상태코드값이 false라면 에러메세지를 가져온다.
			alert(error_msg);			
		}
	}
	
	// 서브그리드에 대한 처리
	//  아래의 처리를 하면 상기 EndQuery() 이벤트의 GridEndQuerySlave() 영역이 호출된다.
	
	// 서브 그리드	
	var GridObjMulti = document.WiseGridMulti;
	// 서브 그리드 로우데이타 작업.
	GridObjMulti.SetGridRawData(GridObj.GetRecvRawData("WiseGridMulti"),false);
}

/* 서버와의 통신이 정상적으로 완료되면 발생한다. -SLAVE 그리드- */
function GridEndQuerySlave(GridObj) {
	
	//요청한 CMD 명을 사용해서 조회/저장 별 후처리를 진행한다.
	var cmd = GridObj.GetParam("WISEGRID_CMD");

	if (cmd == "WISE300.R01") { 
		if(GridObj.GetStatus() == "S-001") 	{// 서버에서 전송한 상태코드를 가져온다.
			alert("[그리드2번]"+ GridObj.GetParam("total") + " 건이 조회되었습니다.");
		} else	{
			var error_msg = GridObj.GetMessage(); // 서버에서 전송한 상태코드값이 false라면 에러메세지를 가져온다.
			alert(error_msg);			
		}
	} else if (cmd == "WISE300.X01") {
		if(GridObj.GetStatus() == "S-001") 	{// 서버에서 전송한 상태코드를 가져온다.
			//서버에서 saveData 셋팅한 파라미터를 가져온다.
			alert("[그리드2번]"+ GridObj.GetParam("total") + " 건이 저장되었습니다.");
		} else	{
			var error_msg = GridObj.GetMessage(); // 서버에서 전송한 상태코드값이 false라면 에러메세지를 가져온다.
			alert(error_msg);			
		}
	}
}


</script>
</head>



<body>
<form name="form">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr height="20">
    <td class="title1_k">
    	&nbsp;WISE300<br><hr>
    </td>
  </tr>
  <tr height="20">
    <td class="title2_k">
			멀티그리드 조회 및 저장 예제
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
			<script>initWiseGrid("WiseGrid", "100%", "250");</script>
		</td>
	</tr>
	<tr>
		<td align='left'>
			&nbsp;
		</td>
	</tr>
		<tr>
		<td align='left'>
			<script>initWiseGrid("WiseGridMulti", "100%", "250");</script>
		</td>
	</tr>
</table>

<br>

</form>
</body>
</html>
