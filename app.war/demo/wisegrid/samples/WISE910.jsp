<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/base/include/ArchJsp.jspf" %>
<html>
<head>
<title>WISE901</title>

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


<!--  	WiseGrid의 셀이 변경 했을때 발생하는 Javacript Event인 ChangeCell()을 받아 해당하는 작업을 진행한다.  -->
<script language=javascript for="WiseGrid" event="ChangeCombo(strColumnKey, nRow, nOldIndex, nNewIndex)">
	GridChangeCombo(strColumnKey, nRow);
</script>

<script language="JavaScript">

function init(GridObj) {
	setProperty(GridObj);
	
	// 동적헤더에 대한 선언을 한다.
	GridObj.bDoQueryDynamic = true;	
	
	// 바인딩을 해야 통신이 된다.
	setHeader(GridObj);
}

function setHeader(GridObj) {		  
	// 1개라도 등록해야지 addGridParam()이 되기 때문에 하나 등록한다. 
	GridObj.AddHeader("MEMO", 		"메모",			"t_text", 		30, 		150,		true);
	
	//AddHeader를 완료한 후 헤더를 그리드에 바인딩한다.
	GridObj.BoundHeader();
}

/*  조회 (다이나믹컬럼1) */
function doQuery1() {

	var comQuery = new CommonQuery();
	comQuery.setGridUrl("WISE910.R01.cmd");
	comQuery.addGridObj("WiseGrid");
	comQuery.addGridParam("WiseGrid","colCnt","1");
	
	comQuery.doGridQuery();

}

/*  조회 (다이나믹컬럼2) */
function doQuery2() {

	var comQuery = new CommonQuery();
	comQuery.setGridUrl("WISE910.R01.cmd");
	comQuery.addGridObj("WiseGrid");
	comQuery.addGridParam("WiseGrid","colCnt","2");
	
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
    	&nbsp;WISE910<br><hr>
    </td>
  </tr>
  <tr height="20">
    <td class="title2_k">
			그리드의 컬럼이 조회시마다 가변일 경우 입출력[WISE->JSP]
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
							<td><script language="javascript">btn("doQuery1()","조회(1개)")</script></td>	
							<td><script language="javascript">btn("doQuery2()","조회(2개)")</script></td>	
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
