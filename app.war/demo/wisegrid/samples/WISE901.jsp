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
	setHeader(GridObj);
	setProperty(GridObj);
}

function setHeader(GridObj) {		  
	//그리드에 컬럼을 등록한다.	 
	GridObj.AddHeader("CRUD",		"구분",			"t_text", 		8, 			40,		true);
	GridObj.AddHeader("COMBO1", 	"1차콤보",		"t_combo", 		30, 		100,		true);		
	GridObj.AddHeader("COMBO2", 	"2차콤보",		"t_combo", 		30, 		100,		true);
	GridObj.AddHeader("COMBO3", 	"3차콤보",		"t_combo", 		30, 		100,		true);
	GridObj.AddHeader("MEMO", 		"메모",			"t_text", 		30, 		100,		true);
	
	//AddHeader를 완료한 후 헤더를 그리드에 바인딩한다
	GridObj.BoundHeader();

	//저장모드를 사용해 서버사이드와 통신한다. 	
	GridObj.SetCRUDMode("CRUD", "생성", "수정", "삭제");
	
	AddComboList(GridObj);
}

/*  콤보 리스트 추가 */
function AddComboList(GridObj) {

	// 1차 콤보 설정 COMBO1 생성 (생성시 부모 콤보의 키를 ROOT로 설정하였음)
	GridObj.AddComboList("COMBO1","ROOT");	
	GridObj.AddComboListValue("COMBO1","생물",  "A1","ROOT");
	GridObj.AddComboListValue("COMBO1","무생물","A2","ROOT");

	// 2차 콤보 설정 COMBO2 생성 (1차 콤보의 값의 갯수만큼 하위 2차콤보를 만든다. 본 예제에선 2개)
	GridObj.AddComboList("COMBO2","A1");		
	GridObj.AddComboListValue("COMBO2","사람","B1","A1");
	GridObj.AddComboListValue("COMBO2","동물","B2","A1");
	
	GridObj.AddComboList("COMBO2","A2");	
	GridObj.AddComboListValue("COMBO2","무생물","B3","A2");
	
	// 3차 콤보 설정 COMBO3 생성
	GridObj.AddComboList("COMBO3","B1");	
	GridObj.AddComboListValue("COMBO3","남자","C1","B1");
	GridObj.AddComboListValue("COMBO3","여자","C2","B1");
	
	GridObj.AddComboList("COMBO3","B2");	
	GridObj.AddComboListValue("COMBO3","숫컷","C3","B2");
	GridObj.AddComboListValue("COMBO3","암컷","C4","B2");
	
	GridObj.AddComboList("COMBO3","B3");	
	GridObj.AddComboListValue("COMBO3","무생물","C5","B3");	
}

/*  조회 */
function doQuery() {

	var comQuery = new CommonQuery();
	comQuery.setGridUrl("WISE901.R01.cmd");
	comQuery.addGridObj("WiseGrid");
	
	comQuery.doGridQuery();

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
	
	//현재 로우인덱스를 구한다.	
	var nRow = GridObj.GetActiveRowIndex();
	
	// 1단콤보값을 디폴트로 준다. 
	GridObj.SetComboSelectedIndex("COMBO1", nRow, -1, "ROOT");
	// 2,3단 콤보는 수정못하게 한다. (아래의 체인지콤보이벤트로 1단콤보를 설정하면 풀린다.) 
	GridObj.SetCellActivation('COMBO2', nRow, 'disable'); 	
	GridObj.SetCellActivation('COMBO3', nRow, 'disable'); 	
}

/*
	ChangeCombo Event 발생시 
*/
function GridChangeCombo(strColumnKey, nRow) {
	var GridObj = document.WiseGrid;

	// 1단콤보값이 변화하면
 	if(strColumnKey == "COMBO1") {
		combokey = GridObj.GetComboHiddenValue("COMBO1", GridObj.GetComboSelectedIndex('COMBO1',nRow), "ROOT");
		
		// 2단콤보리스트 값 초기화 그리고 에디트 가능하게.
		GridObj.SetComboSelectedIndex("COMBO2", nRow, -1, combokey); 
		GridObj.SetCellActivation('COMBO2', nRow, 'edit');
		// 2단콤보리스트 값 초기화 그리고 에디트 불가능하게.	
		GridObj.SetComboSelectedIndex("COMBO3", nRow, -1, GridObj.GetComboSelectedListKey("COMBO3",nRow));
		GridObj.SetCellActivation('COMBO3', nRow, 'disable'); 	
		
	} else if(strColumnKey == "COMBO2") {	  
		combokey = GridObj.GetComboHiddenValue("COMBO2",GridObj.GetComboSelectedIndex('COMBO2',nRow),GridObj.GetComboSelectedListKey("COMBO2",nRow)) ;
		
		// 3단콤보리스트 값 초기화 그리고 에디트 불가능하게.				  
		GridObj.SetCellActivation('COMBO3', nRow, 'edit'); 
		GridObj.SetComboSelectedIndex("COMBO3",nRow,-1,combokey); 		
	}	
} 


/* 저장모드에서 저장 플래그를 모두 삭제하고 초기 데이터로 롤백한다. */
function doSaveCancel() {
	var GridObj = document.WiseGrid;
	
	if(confirm("저장 플래그를 모두 초기화 합니다"))
		GridObj.CancelCRUD();
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
    	&nbsp;WISE901<br><hr>
    </td>
  </tr>
  <tr height="20">
    <td class="title2_k">
			다단콤보 예제, 입출력[WISE->JSP] 유형 (WISE-WISE가 아님에 유의)
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
