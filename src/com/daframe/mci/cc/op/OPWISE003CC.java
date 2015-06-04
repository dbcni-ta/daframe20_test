package com.daframe.mci.cc.op;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xlib.cmc.GridData;
import xlib.cmc.GridHeader;
import xlib.cmc.OperateGridData;

import com.cni.fw.arch.smb.cc.WebOutControl;
import com.cni.fw.ff.dto.channel.ChannelDataHeader;
import com.cni.fw.ff.dto.channel.ChannelDataStructure;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.dto.impl.ImplCauseDTO;
import com.cni.fw.ff.dto.impl.ImplEffectDTO;
import com.cni.fw.ff.dto.support.Nexter;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.maker.IntMaker;
import com.cni.fw.ff.util.maker.LongMaker;
import com.cni.fw.id.LtoInfoC;
import com.cni.fw.meta.msg.MessageRepository;

/**
 * WISE OP 처리기 (버전 3 - DAFrame 1.9.1 이상 대응)
 * 
 * 2011-07-08 : WISEGRID_LEVEL_컬럼명 처리 추가 (레벨별 이미지 변경을 위해)
 * 
 * <pre>
 * 2007. 09. 28
 * </pre>
 * @version : 1.0 
 * @author : WinterMute (Jeon Chan-Mo)
 */
public class OPWISE003CC extends WebOutControl {

	public OPWISE003CC(Class clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 정상처리시
	 * 
	 * <pre>
	 * 2007. 09. 28
	 * </pre>
	 * @param input
	 * @param output
	 * @param request
	 * @param response
	 * @throws FrameException
	 * @throws ServiceException
	 * @see com.cni.fw.arch.smb.cc.WebControl#process(com.cni.fw.ff.dto.impl.ImplCauseDTO, com.cni.fw.ff.dto.impl.ImplEffectDTO, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void process(ImplCauseDTO input, ImplEffectDTO output, HttpServletRequest request, HttpServletResponse response) throws FrameException, ServiceException {

		if (output.getSecondUrl() != null) {
			debug("[SKIP] 동작중 대체 URL이 설정되면 WISE 출력 처리를 생략함");
			return;
		}
		
		ChannelDataStructure inputDS = (ChannelDataStructure) input.getDataStructure();
		ChannelDataStructure outputDS = (ChannelDataStructure) output.getDataStructure();
		
		if (outputDS == null) {
			throw new FrameException("프로토콜 오류, WISE 타입 출력은 WISE 타입 입력을 필요로 합니다.");
		}

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out =  null;

		GridData gridDatas[] = null;
		String gridNames[] = null;
		
		int subGrdCnt = outputDS.getSize();

		try {
			out = response.getWriter();

			gridNames = new String[inputDS.getSize()+outputDS.getSize()];
			gridDatas = new GridData[inputDS.getSize()+outputDS.getSize()];
			
//			debug(gridNames.length);
//			debug(gridDatas.length);

			int grdCnt = 0;

			/**
			 * 출력할 그리드에 대한 데이타 처리.
			 */
			String dsName = null;
			ChannelDataHeader dsHeader = null;
			LTO dsData = null;
			MTO dsDataInfo = null;
			for (int i=0;i<outputDS.getSize();i++) {
				dsName = outputDS.getKey(i); 			// 출력할 그리드 이름.
				dsHeader = outputDS.getHeader(i);  		// 출력할 그리드 구조.
				dsData = output.getLTO(dsName);			// 출력할 그리드에 대응하는 데이타.
				dsDataInfo = output.getMTO(dsName);		// 출력할 그리드에 대한 파라메타

				if(grdCnt==0) {
					gridNames[0] = "WISEGRIDDATA_MASTER";
				} else {
					gridNames[grdCnt] = dsName;
				}
				
				/**
				 * 1단계 GridData 레퍼런스 획득.
				 */
				gridDatas[grdCnt] = OperateGridData.cloneResponseGridData((GridData)input.getSystem().getObject("WISEGRID_"+dsName));
				
				MTO row = null;

				if (dsData != null) {
					/**
					 * 1.5단계 페이징 여부 체크
					 */
					long rowTot = LongMaker.make(dsData.getInfo(LtoInfoC.ROW_TOT_STR),0);
					long pageNo = LongMaker.make(dsData.getInfo(LtoInfoC.PG_NUM_STR),0);
					long pageSize = LongMaker.make(dsData.getInfo(LtoInfoC.PG_SIZE_STR),0);
					
					// 페이징 처리된 LTO 일 경우에는.
					if (pageNo > 0 && pageSize > 0) {
						pageNo = pageNo-1;
						gridDatas[grdCnt].setTotalCount(rowTot);
						gridDatas[grdCnt].setNavigateValue(pageSize+","+pageNo);
					}
					
					/**
					 * 1.6 단계 서버 콤보 데이타 체크. 데이타 없을 경우에 대해 사전 처리하는것으로 수정. 2010.29.
					 */
					for (int j=0;j<dsHeader.size();j++) {
						String colName = dsHeader.getNames().get(j);
						GridHeader gridHeader = gridDatas[grdCnt].getHeader(colName);
						if(gridHeader.getDataType().equals(OperateGridData.t_combo)) {
							LTO comboInfo = (LTO) dsData.getInfoObject("WISEGRID_COMBO_"+colName);
							if (comboInfo != null) {
								gridHeader.setComboValues(comboInfo.extractColumn("VALUE").toArray(), comboInfo.extractColumn("HIDDEN_VALUE").toArray());
							}
						}
					}
					
					/**
					 * 다단콤보의 경우 아래와 같이 코딩한다. (그러나 이건 아직 지원하지 않음)
					 */
					/**
					if (gridHeader.hasComboList(colName)) {
						gridHeader.addSelectedHiddenValue(colName,row.getDefault(colName));
					} else {
						LTO comboInfo = (LTO) dsData.getInfoObject("WISEGRID_COMBO_"+colName);
						if (comboInfo != null) {
							gridHeader.addComboListValues(colName, comboInfo.extractColumn("VALUE").toArray(), comboInfo.extractColumn("HIDDEN_VALUE").toArray());
							gridHeader.addSelectedHiddenValue(colName,row.getDefault(colName));
						}
					}*/
					
					/**
					 * 2단계 GridData 전송
					 */
					
					for (int j=0;j<dsData.size();j++) {
						row = dsData.get(j);
						for (int k=0;k<dsHeader.size();k++) {
							String colName = dsHeader.getNames().get(k);
							GridHeader gridHeader = gridDatas[grdCnt].getHeader(colName);
							//debug("## " + colName + ":" + gridHeader.getDataType() +"/" + OperateGridData.t_combo );
							
							// 해당컬럼이 t_combo로 설정된경우 (Ver3 작업완료)
							if(gridHeader.getDataType().equals(OperateGridData.t_combo)) {
								
								/**
								 * 만약 본 컬럼에 대해 콤보정보가 설정되어 있지 않다면, 보이지 않을 수 있음.
								 * (클라이언트에서라도 설정되어 있으면 상관없음) 
								 */
								gridHeader.addSelectedHiddenValue(row.getDefault(colName));

								
							// 해당컬럼이 t_imagetext로 설정된 경우 (통상트리일때임) 
							} else if (gridHeader.getDataType().equals(OperateGridData.t_imagetext)) {
								String treeColName = "WISEGRID_TREE_"+colName;
								// 2011-07-08 트리의 레벨에 따라 이미지를 달리 주고 싶을때 WISEGRID_LEVEL_컬럼명 을 추가로 활용할 수 있다. 없을 경우 무조껀 0.
								// 2011-07-12 레벨을 단독으로 사용할 수 있도록 하고 (트리 아닌 곳의 이미지를 위해..) 없을 경우 디폴트를 -1에서 0으로 변경.
								String levelColName = "WISEGRID_LEVEL_"+colName;
								if (row.containsKey(treeColName)) {
									if (row.containsKey(levelColName)) {
										gridHeader.addValue(row.getDefault(colName), row.getDefault(treeColName), IntMaker.make(row.getDefault(levelColName)));
									} else {
										gridHeader.addValue(row.getDefault(colName), row.getDefault(treeColName), 0);
									}
								} else {
									if (row.containsKey(levelColName)) {
										gridHeader.addValue(row.getDefault(colName), "", IntMaker.make(row.getDefault(levelColName)));
									} else {
										gridHeader.addValue(row.getDefault(colName), "", 0);
									}
								}
							// 해당컬럼이 t_checkbox 일 경우에는 키가 없을때 디폴트로 0을 주어야한다.
							} else if (gridHeader.getDataType().equals(OperateGridData.t_checkbox)) {
								gridHeader.addValue(row.getDefault(colName,"0"),"");
							} else {
								gridHeader.addValue(row.getDefault(colName), "");
							}
						}
					}


				}

				/**
				 * 3단계 Grid별 Param 데이타 전송
				 */

				if (dsDataInfo != null) {
					Nexter nx = dsDataInfo.getNexter();
					while (nx.hasNext()) {
						String key = nx.next();
						String value = dsDataInfo.getDefault(key);
						gridDatas[grdCnt].addParam(key, value);
					}
				}



				/**
				 * 4단계 메시지 및 코드 전송 (요청CMD 추가 2010.10.07)
				 */
				gridDatas[grdCnt].addParam("WISEGRID_CMD", input.getCommonSession().getFirstCommand());
				gridDatas[grdCnt].setStatus(output.getCode());
				gridDatas[grdCnt].setMessage(MessageRepository.getInstance().getMessage(output.getCode(), output.getMessage()));
				
				grdCnt++;      

			}

			/**
			 * 저장처리된 그리드에 대한 결과 처리.
			 * 
			 * Versin 2. 2010.08.12
			 * 기존에는 저장처리후 재조회하는 기능이 없어 위의 조회시의 로직을 긁어서
			 * 저장요청된 그리드에 대해서도 output에 동일한 LTO를 추가해줄 경우 해당 내용으로 재조회를 하도록
			 * 구현한 것임.
			 * 단 급히 만드느라 코드를 붙어넣길해서 깔금하지 못함.
			 * 
			 * if (output에 저장요청된그리드명이 존재하면) 재조회
			 * else 기존
			 */
			for (int i=0;i<inputDS.getSize();i++) {
				dsName = inputDS.getKey(i); 			// 출력할 그리드 이름.
				dsHeader = inputDS.getHeader(i);  		// 출력할 그리드 구조.
				dsData = output.getLTO(dsName);			// 출력할 그리드에 대응하는 데이타.
				dsDataInfo = output.getMTO(dsName);		// 출력할 그리드에 대한 파라메타

				if(grdCnt==0) {
					gridNames[0] = "WISEGRIDDATA_MASTER";
				} else {
					gridNames[grdCnt] = dsName;
				}
				
				/**
				 * 1단계 GridData 레퍼런스 획득.
				 */
				
				
				MTO row = null;
				
				if (dsData != null) {
					gridDatas[grdCnt] = OperateGridData.cloneResponseGridData((GridData)input.getSystem().getObject("WISEGRID_"+dsName));
					
					/**
					 * 1.5단계 페이징 여부 체크
					 */
					long rowTot = LongMaker.make(dsData.getInfo(LtoInfoC.ROW_TOT_STR),0);
					long pageNo = LongMaker.make(dsData.getInfo(LtoInfoC.PG_NUM_STR),0);
					long pageSize = LongMaker.make(dsData.getInfo(LtoInfoC.PG_SIZE_STR),0);
					
					// 페이징 처리된 LTO 일 경우에는.
					if (pageNo > 0 && pageSize > 0) {
						pageNo = pageNo-1;
						gridDatas[grdCnt].setTotalCount(rowTot);
						gridDatas[grdCnt].setNavigateValue(pageSize+","+pageNo);
					}
					
					/**
					 * 1.6 단계 서버 콤보 데이타 체크. 데이타 없을 경우에 대해 사전 처리하는것으로 수정. 2010.29.
					 */
					for (int j=0;j<dsHeader.size();j++) {
						String colName = dsHeader.getNames().get(j);
						GridHeader gridHeader = gridDatas[grdCnt].getHeader(colName);
						if(gridHeader.getDataType().equals(OperateGridData.t_combo)) {
							LTO comboInfo = (LTO) dsData.getInfoObject("WISEGRID_COMBO_"+colName);
							if (comboInfo != null) {
								gridHeader.setComboValues(comboInfo.extractColumn("VALUE").toArray(), comboInfo.extractColumn("HIDDEN_VALUE").toArray());
							}
						}
					}
					
					/**
					 * 다단콤보의 경우 아래와 같이 코딩한다. (그러나 이건 아직 지원하지 않음)
					 */
					/**
					if (gridHeader.hasComboList(colName)) {
						gridHeader.addSelectedHiddenValue(colName,row.getDefault(colName));
					} else {
						LTO comboInfo = (LTO) dsData.getInfoObject("WISEGRID_COMBO_"+colName);
						if (comboInfo != null) {
							gridHeader.addComboListValues(colName, comboInfo.extractColumn("VALUE").toArray(), comboInfo.extractColumn("HIDDEN_VALUE").toArray());
							gridHeader.addSelectedHiddenValue(colName,row.getDefault(colName));
						}
					}*/
					
					/**
					 * 2단계 GridData 전송
					 */
					
					for (int j=0;j<dsData.size();j++) {
						row = dsData.get(j);
						for (int k=0;k<dsHeader.size();k++) {
							String colName = dsHeader.getNames().get(k);
							GridHeader gridHeader = gridDatas[grdCnt].getHeader(colName);
							//debug("## " + colName + ":" + gridHeader.getDataType() +"/" + OperateGridData.t_combo );
							
							// 해당컬럼이 t_combo로 설정된경우 (Ver3 작업완료)
							if(gridHeader.getDataType().equals(OperateGridData.t_combo)) {
								
								/**
								 * 만약 본 컬럼에 대해 콤보정보가 설정되어 있지 않다면, 보이지 않을 수 있음.
								 * (클라이언트에서라도 설정되어 있으면 상관없음) 
								 */
								gridHeader.addSelectedHiddenValue(row.getDefault(colName));

								
							// 해당컬럼이 t_imagetext로 설정된 경우 (통상트리일때임) 
							} else if (gridHeader.getDataType().equals(OperateGridData.t_imagetext)) {
								String treeColName = "WISEGRID_TREE_"+colName;
								// 2011-07-08 트리의 레벨에 따라 이미지를 달리 주고 싶을때 WISEGRID_LEVEL_컬럼명 을 추가로 활용할 수 있다. 없을 경우 무조껀 0.
								// 2011-07-12 레벨을 단독으로 사용할 수 있도록 하고 (트리 아닌 곳의 이미지를 위해..) 없을 경우 디폴트를 -1에서 0으로 변경.
								String levelColName = "WISEGRID_LEVEL_"+colName;
								if (row.containsKey(treeColName)) {
									if (row.containsKey(levelColName)) {
										gridHeader.addValue(row.getDefault(colName), row.getDefault(treeColName), IntMaker.make(row.getDefault(levelColName)));
									} else {
										gridHeader.addValue(row.getDefault(colName), row.getDefault(treeColName), 0);
									}
								} else {
									if (row.containsKey(levelColName)) {
										gridHeader.addValue(row.getDefault(colName), "", IntMaker.make(row.getDefault(levelColName)));
									} else {
										gridHeader.addValue(row.getDefault(colName), "", 0);
									}
								}
							} else {
								gridHeader.addValue(row.getDefault(colName), "");
							}
						}
					}


				} else {
					gridDatas[grdCnt] = new GridData();
				}

				/**
				 * 3단계 Grid별 Param 데이타 전송
				 */

				if (dsDataInfo != null) {
					Nexter nx = dsDataInfo.getNexter();
					while (nx.hasNext()) {
						String key = nx.next();
						String value = dsDataInfo.getDefault(key);
						gridDatas[grdCnt].addParam(key, value);
					}
				}



				/**
				 * 4단계 메시지 및 코드 전송 (요청CMD 추가 2010.10.07)
				 */
				gridDatas[grdCnt].addParam("WISEGRID_CMD", input.getCommonSession().getFirstCommand());
				gridDatas[grdCnt].setStatus(output.getCode());
				gridDatas[grdCnt].setMessage(MessageRepository.getInstance().getMessage(output.getCode(), output.getMessage()));
				
				grdCnt++;

			}
			
			OperateGridData.write(gridNames, gridDatas, out);
			
		} catch (Exception e) {
			throw new FrameException(e);
		}  finally {
			output.setSkipNextStep(true);
		}			

	}

	/**
	 * 예외 처리
	 * <pre>
	 * 2007. 09. 28
	 * </pre>
	 * @param input
	 * @param output
	 * @param request
	 * @param response
	 * @throws FrameException
	 * @throws ServiceException
	 * @see com.cni.fw.arch.smb.cc.WebControl#errorProcess(com.cni.fw.ff.dto.impl.ImplCauseDTO, com.cni.fw.ff.dto.impl.ImplEffectDTO, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void errorProcess(ImplCauseDTO input, ImplEffectDTO output, HttpServletRequest request, HttpServletResponse response) throws FrameException, ServiceException {

		// 예외처리도 정상처리시와 동일한 로직으로 처리한다.
		process(input,output,request,response);
	}

}
