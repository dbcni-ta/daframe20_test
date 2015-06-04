<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>
<html>
<head>
<title>WISE110</title>

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
	GridObj.AddHeader("CHECK",			"선택",			"t_checkbox", 	1, 		40,		true);
	GridObj.AddHeader("CODE", 			"코드",			"t_text", 		5, 		50,		true);		
	GridObj.AddHeader("NATION_CODE", 	"국가코드",		"t_text", 		3, 		70,		true);		
	GridObj.AddHeader("NAME", 			"경기장명",		"t_text", 		50, 	160,	true);	
	GridObj.AddHeader("SEATS", 			"좌석수",		"t_number", 	20, 	80,		true);
	GridObj.AddHeader("ADDRESS", 		"주소",			"t_text", 		200, 	200,	true);
	
	//AddHeader를 완료한 후 헤더를 그리드에 바인딩한다
	GridObj.BoundHeader();
	
	GridObj.SetNumberFormat("SEATS", "#,##0");
}

/*  조회 
CommonQuery 객체를 사용하여 조회한다.

1단계 : [필수] 
setGridUrl(String url) 을 사용해 해당 서비스의 URL을 설정한다.
2단계 : [필수]
addGridObj(String gridId) 를 사용해 관련그리드ID를 설정한다.
3단계 : [선택]
addGridParam(String gridId, String paramKey, String paramValue) 를 사용해 해당 그리드에 대한 Key,Value 쌍을 설정한다 (조회조건등)
4단계 : [필수]
doGridQuery() 를 호출한다.

*/
function doQuery() {

	var comQuery = new CommonQuery();
	comQuery.setGridUrl("WISE110.R01.cmd");
	comQuery.addGridObj("WiseGrid");
	comQuery.addGridParam("WiseGrid", "mode", "search");
	
	comQuery.doGridQuery();

}

/* 저장 
CommonQuery 객체를 사용하여 저장한다.

1단계 : [필수] 
setGridUrl(String url) 을 사용해 해당 서비스의 URL을 설정한다.
2단계 : [필수]
addGridObj(String gridId, String SaveMethod) 를 사용해 관련그리드ID를 설정 및 저장방식을 설정한다.

저장방식 (SaveMethod)
1. CRUD 방식 : 해당그리드에서 CUD 에 해당하는 Row 정보만 서버로 전송하여 저장처리에 활용하는 방식. 
GridObj.SetCRUDMode()로 세팅된 CRUD 컬럼명을 작성한다. 단 통상 이부분은 "CRUD"의 예약어를 사용해야한다.
2. CHECK 방식 : 해당그리드에서 Check 처리된 Row 정보만 서버로 전송하여 저장처리에 활용하는 방식
t_checkbox 타입으로 설정된 컬럼명을 작성한다.
3. ALL 방식 : 해당그리드의 전체 Row 정보를 서버로 전송한다.
"ALL_ROWS" 라는 예약어를 사용한다.
4. SELECTED 방식 : 해당그리드에서 마우스로 선택된 (반전된) Row 정보만 서버로 전송한다.
"SELECTED_ROWS" 라는 예약어를 사용한다.

3단계 : [선택]
addGridParam(String gridId, String paramKey, String paramValue) 를 사용해 해당 그리드에 대한 Key,Value 쌍을 설정한다 (조회조건등)
4단계 : [필수]
doGridQuery() 를 호출한다.

*/
function doSave() {

	var comQuery = new CommonQuery();
	comQuery.setGridUrl("WISE110.X01.cmd");
	comQuery.addGridObj("WiseGrid","CHECK"); // CHECK 방식.
	comQuery.addGridParam("WiseGrid", "mode", "save");
	
	comQuery.doGridTrans();
}

/* 서버와의 통신이 정상적으로 완료되면 발생한다. */
function GridEndQuery(GridObj) {
	
	//요청한 CMD 명을 사용해서 조회/저장 별 후처리를 진행한다.
	var cmd = GridObj.GetParam("WISEGRID_CMD");

	if (cmd == "WISE110.R01") { 
		if(GridObj.GetStatus() == "S-001") 	{// 서버에서 전송한 상태코드를 가져온다.
			alert(GridObj.GetParam("total") + " 건이 조회되었습니다.");
		} else	{
			var error_msg = GridObj.GetMessage(); // 서버에서 전송한 상태코드값이 false라면 에러메세지를 가져온다.
			alert(error_msg);			
		}
	} else if (cmd == "WISE110.X01") {
		if(GridObj.GetStatus() == "S-001") 	{// 서버에서 전송한 상태코드를 가져온다.
			//서버에서 saveData 셋팅한 파라미터를 가져온다.
			alert(GridObj.GetParam("total") + " 건이 저장되었습니다.");
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
    	&nbsp;WISE110<br><hr>
    </td>
  </tr>
  <tr height="20">
    <td class="title2_k">
			WISE100예제와 다른점은 선택된 ROW를 그냥 서버로 전송하는 방식임.
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
							<td><script language="javascript">btn("doSave()","선택전송")</script></td>
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
