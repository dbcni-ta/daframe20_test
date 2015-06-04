<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>
<html>
<head>
<title>WISE130</title>

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
	comQuery.setGridUrl("WISE130.R01.cmd");
	comQuery.addGridObj("WiseGrid");
	comQuery.addGridParam("WiseGrid", "mode", "search");
	
	comQuery.doGridQuery();

}
/* 서버와의 통신이 정상적으로 완료되면 발생한다. */
function GridEndQuery() {

}

</script>
</head>

<body>
<form name="form">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr height="20">
    <td class="title1_k">
    	&nbsp;WISE130<br><hr>
    </td>
  </tr>
  <tr height="20">
    <td class="title2_k">
			페이징처리 예제.
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
			<script>initWiseGrid("WiseGrid", "100%", "280");</script>
		</td>
	</tr>
</table>

<br>

</form>
</body>
</html>
