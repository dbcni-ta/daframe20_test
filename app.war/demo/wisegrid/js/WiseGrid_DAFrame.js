/**
 * WiseGrid 통신용 공통 스크립트 클래스
 * @version V1,0,0,1
 */

/**
 * TODO
 *  트랜잭션 시 
 *  - CRUD : 컬럼키
 *  - CHECKBOX : 컬럼키
 *  - TRANS : none
 *  - ALL : ALL_ROWS
 *  - SELECTDROWS : SELECTED_ROWS
 */
function CommonQuery()
{
  // initialize the member variables for this instance
  this.arrIdx = 0;
  this.grdNameIdx = new Array();
  
  /**
   * Grid 통신에 사용할 객체
   */
  this.grdObj = new Array();
  this.grdUrl = "";
  this.transColName = "";
  this.transType = {};
  this.WISEGRID_NAME='WISEGRID_NAME';
  this.WISEGRID_MODE='WISEGRID_MODE';
}
CommonQuery.prototype = {
	/**
	 * grdName이 와이즈그리드 인지 체크
	 * @param {Object} grdName
	 */
	hasWiseGridObj : function(grdName){
		var grd = document.getElementById(grdName);
		if(typeof grd == "undefined" || grd == null)
			throw new WiseGridException("\"" + grdName + "\"는 존재하지 않은 WiseGrid Object 입니다.");		
	},
	/**
 	* 통신시에 사용할 grdObj가 존재하는지 체크.
 	*/
	existWiseGridObj : function(){
		if(this.grdObj == null || this.grdObj.length == 0)
		throw new WiseGridException("등록된 WiseGrid 오브젝트가 없습니다.");
	},
	/**
 	* 그리드 통신시에 사용할 URL이 존재하는지 판단.
 	*/
	hasGridUrl : function(){
		if(this.getGridUrl() == null || this.getGridUrl() == "")
			throw new WiseGridException("URL이 입력되지 않았습니다.");
	},
	/**
	 * CRUD모드시에 사용할 컬림키가 저장되었는지 판단.
	 */
	hasTransColName : function(){
		if(this.getTransColName() == null || this.getTransColName() == "")
			throw new WiseGridException("Transaction을 위한 컬럼키가 입력되지 않았습니다.");
	},
	/**
	 * 통신시에 사용할 URL 저장
	 * @param {Object} grdUrl
	 */
	setGridUrl : function(grdUrl){
		this.grdUrl = grdUrl;
	},
	/**
	 * 현재 저장된 URL 반환
	 */
	getGridUrl : function(){
		return this.grdUrl;
	},
	/**
	 * 통신시에 사용할 CRUD컬럼 키값 저장
	 * @param {Object} transColName
	 */
	setTransColName : function(transColName){
		this.transColName = transColName;
	},
	/**
	 * private
	 * @param {Object} _type
	 */
	setTransType : function(gridName, type){
		this.transType[gridName] = type;
	},
	getTransType : function(gridName){
		if(gridName){
			return this.transType[gridName];
		}else{
			return this.transType;
		}
	},
	/**
	 * 저장된 CRUD 키값 반환
	 */
	getTransColName : function(){
		return this.transColName;
	},
	/**
	 * grdObj에 WiseGrid Object 추가
	 * @param {Object} grdName
	 */
	addGridObj : function(grdName, columnKey) {
		try
		{
			this.hasWiseGridObj(grdName);
			this.grdObj[this.arrIdx] = grdName;
			this.grdNameIdx[grdName] = this.arrIdx++;
			this.setTransType(grdName,columnKey);
		}
		catch(e)
		{
			throw new Error("CommonQuery "+ e.name +"[addGridObj] : " + e.message);
		}
	},
	/**
	 * gridObj에서 grdIdx 에 해당하는 Grid객체 반환
	 * @param {Object} grdIdx
	 */
	getGridObj : function(grdIdx){
		try
		{
			if(grdIdx >= this.grdObj.length && grdIdx == null)
				throw RangeError("등록된 WisGrid 개수의 범위를 벗어나는 Index입니다.\n등록된 WiseGrid 개수 : " + this.grdObj.length + "\n입력된 WiseGrid Index : " + grdIdx);
			
			return (grdIdx !=null ? this.grdObj[grdIdx] : this.grdObj) ;
		}
		catch(e)
		{
			throw new Error("CommonQuery "+ e.name +"[getGridObj] : " + e.message);
		}
	},
	/**
	 * [grdName]이름을 갖는 그리드에 파리미터 추가
	 * @param {Object} grdName 그리드명
	 * @param {Object} paramName 파라미터키
	 * @param {Object} paramValue 파라미터 값
	 */
	addGridParam : function(grdName, paramName, paramValue){
		try
		{
			
			this.hasWiseGridObj(grdName);
			
			document.getElementById(grdName).SetParam(paramName, paramValue);
		}
		catch(e)
		{
			throw new Error("CommonQuery "+ e.name +"[addGridParam] : " + e.message);
		}
	},
	/**
	 * grdObj(code Line 15)에 저장된 그리드 객체들을 WISEGRID_SUBDATA_{index} 키값으로
	 * 마스터 그리드에 추가 한후 통신
	 * 서버에서 받을 전문 키값은 WISEGRID_NAME
	 */
	doGridQuery : function(){

			this.existWiseGridObj();
			this.hasGridUrl();
			
			var grdObjCnt = this.grdObj.length;
			if(grdObjCnt> 1 ){
				for(var i = 1; i < grdObjCnt; i++){
					var grdName = this.getGridObj(i);
					document.getElementById(this.getGridObj(0)).AddGridRawData("WISEGRID_SUBDATA_" + i, document.getElementById(grdName).GetGridRawData());
				}
			}
			this.addGridParam(this.getGridObj(0), this.WISEGRID_NAME, this.getGridObj());
			
			document.getElementById(this.getGridObj(0)).DoQuery(this.getGridUrl());
	},
	/**
	 * CRUD모드에서 사용,
	 * 체크박스컬럼이 있을경우 getTransColName() 메소드에서 지정된 체크박스 헤더를 받아와서
	 * 전송시에 인자로 보낸다.
	 * 
	 */
	doGridTrans : function(){
			this.existWiseGridObj();
			this.hasGridUrl();
			//this.hasTransColName();
			
			var grdObjCnt = this.grdObj.length;
			var masterGrid = this.getGridObj(0);
			//alert('grdObjCnt:'+grdObjCnt)
			for(var i = 0; i < grdObjCnt; i++)
			{
				var grdName = "";
				if(grdObjCnt > 1 && (grdObjCnt-1) != i)
				{
					grdName = this.getGridObj(i+1);
					document.getElementById(grdName).SetParam(this.WISEGRID_NAME, grdName);
					
					
					var transType = this.getTransType(grdName);
					
					switch(transType){
						case 'SELECTED_ROWS' :
							transType = this.getSelectedRows(grdName);
							break;
						case 'ALL_ROWS' : 
							transType = 'WISEGRIDDATA_ALL';
							break;
					}
					//alert('grid:'+i +', transType:'+transType)
					var subGridRawData = document.getElementById(grdName).GetGridRawData(transType);
					document.getElementById(masterGrid).AddGridRawData("WISEGRID_SUBDATA_" + (i+1), subGridRawData);
				}
				else
				{
					if(grdObjCnt > 0){
						document.getElementById(masterGrid).SetParam(this.WISEGRID_NAME, this.getGridObj());
					}					
					else{
						document.getElementById(masterGrid).SetParam(this.WISEGRID_NAME, "WISEGRID_MASTER");
					}
						
						
					//hasTransColName();
					
					var transType = this.getTransType(masterGrid);
					
					switch(transType){
						case 'SELECTED_ROWS' :
							transType = this.getSelectedRows(masterGrid);
							break;
						case 'ALL_ROWS' : 
							transType = 'WISEGRIDDATA_ALL';
							break;
					}
					
					/* 수정 : 전찬모 => 저장모드에 대해 서버에서 체크할 필요가 발생 */
					document.getElementById(masterGrid).SetParam(this.WISEGRID_MODE, "TRANS");
					document.getElementById(masterGrid).DoQuery(this.getGridUrl(), transType);		
				}
			}
	},
	getSelectedRows : function(grdName){
		var rtnArr =[]
		var rows = document.getElementById(grdName).GetSelectedCells();
		if (rows.length > 0) {
	        var aArr = rows.split(',');
			var index =0;
	        for (var i = 0; i < aArr.length; i += 2) {
				rtnArr[index] = parseInt(aArr[i + 1],10);
				index++;
	        }
		}
		return rtnArr;
	}
}

/**
 * 에러처리
 * @param {Object} message
 */
function WiseGridException(message)
{
	this.name = "WiseGridException";
	this.message = message;
}

/**
 * 통신 후 공통된 후처리 (현재 NOSESSION만 처리, 필요시 수정해서 사용하세요.)
 */
function GridEndQueryCommon(gridObj)
{
	if (gridObj.getStatus() == "NOSESSION") {
		alert("세션이 존재하지 않습니다. 다시 로그인해 주십시요.");
	}
}