package com.daframe.mci.cc.ip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xlib.cmc.GridData;
import xlib.cmc.GridHeader;
import xlib.cmc.OperateGridData;

import com.cni.fw.arch.smb.cc.WebControl;
import com.cni.fw.ff.dto.channel.ChannelDataHeader;
import com.cni.fw.ff.dto.channel.ChannelDataStructure;
import com.cni.fw.ff.dto.entity.ATO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.dto.impl.ImplCauseDTO;
import com.cni.fw.ff.dto.impl.ImplEffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.StrUtil;
import com.cni.fw.ff.util.maker.StringMaker;
import com.cni.fw.made.web.cc.common.http.HttpRequestMapper;

/**
 * WISE IP 처리기  (버전 3 - DAFrame 1.9.1 이상 대응)
 * <pre>
 * 
 * 예약파라메타를 활용해 WISE 입력데이타를 input 객체로 파싱한다.
 * 
 * 예약#1 [WISEGRID_DATA] : WISE의 자체 예약어임,
 * 예약#2 [WISEGRID_NAME] : input의 LTO명으로 사용할 그리드명이며 ( ',' 구분자로 여러개를 가지고 있을 수 있음)
 * 예약#3 [WISEGRID_SUBDATA_#] 0~N 개까지 상기 NAME과 쌍을 이루는 데이타 영역에 대한 key 
 * 
 * 파싱처리
 * 1단계, WISEGRID_DATA 를 찾아서 GridData 객체 확보
 * 2단계, WISEGRID_NAME 을 찾아서 전체 전달 그리드 수 확보 (1~N개)
 * 3단계, [반복] (1~N개) 의 그리드
 * 3-1, GridData 레퍼런스를 System 영역에 저장 (WISEGRID_[그리드명])
 * 3-2, GridData 의 파라메타들을 MTO 영역에 저장 (그리드명)
 * 3-3, GridData 의 row 정보를 LTO 영역에 저장 (그리드명)
 * 
 * </pre>
 * @version : 1.0
 * @author : WinterMute (Jeon Chan-Mo)
 * @since 2008. 08. 21
 */
public class IPWISE003CC extends WebControl {

	public IPWISE003CC(Class clazz) {
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

		boolean isSaveMode = false;
		
		// 입력데이타 구조
		ChannelDataStructure inputDS = new ChannelDataStructure();
		// 출력데이타 구조
		ChannelDataStructure outputDS = new ChannelDataStructure();
		
		HttpRequestMapper.execBasicMapping(request, input);
		//debug(input);

		String rawData = request.getParameter("WISEGRID_DATA");
		debug("======>> rawData : " + rawData + " <<=====");
		
		try {
			debug("======>> " + OperateGridData.parse(rawData)==null?"null":"not null" + " <<=====");
			GridData masterData = OperateGridData.parse(rawData);
			

			if (masterData == null) {
				throw new FrameException("프로토콜 오류");
			}
			ATO nameList = StrUtil.makeAtoBySeperator(masterData.getParam("WISEGRID_NAME"), ",");
			
			//debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			//debug("IPWISE001CC(subGrdCnt) : masterData에 1개이상의 서브그리드 정보가 있는 경우,.,,, ");
			//debug("IPWISE001CC(WISEGRID_DATA) :  " + request.getParameter("WISEGRID_DATA"));
			//debug("??GRID List :  " + nameList);
			//debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");	

			GridData grdData = null;
			String grdName = null;
			
			/**
			 * 0 단계 : 전체 그리드명을 ATO에 우선 적재.
			 */
			input.put("WISEGRID_NAME", nameList.get(0)); // 최초의 MASTER 그리드명은 별도로 Master 영역에 적재.
			input.putATO("WISEGRID_NAME", nameList);
			
			/**
			 * 0.5단계 : 현재요청모드를 체크 (WISEGRID_MODE 가 TRANS 일경우 저장모드임)
			 */
			if (StringMaker.make(masterData.getParam("WISEGRID_MODE")).equals("TRANS")) {
				isSaveMode = true;
			}
			
//			debug(input);
//			debug("그리드수:"+nameList.size());

			for (int i=0;i<nameList.size();i++) {
				//String subGrdName = masterData.getParam("WISE_SUB_NAME_"+i);

				//debug(new MTO(new HashMap(request.getParameterMap())));

				// 멀티그리드일경우..
				if (i > 0) {
					String subRawData = request.getParameter("WISEGRID_SUBDATA_"+i);
					grdData = OperateGridData.parse(subRawData);
				} else {
					grdData = masterData;
				}

				grdName = nameList.get(i);
				
				/**
				 * 1단계 : GridData 레퍼런스 저장 및 GridNameList를 ATO에 저장
				 */
				input.getSystem().putObject("WISEGRID_"+grdName, grdData);

				/**
				 * 2단계 : 각 그리드 내부 파라메타 저장
				 */
				String paramKeyList[] = grdData.getParamNames();
				MTO paramInfo = new MTO();
				for (int paramIdx=0;paramIdx<paramKeyList.length;paramIdx++) {
					String key = paramKeyList[paramIdx];
					String value = grdData.getParam(key);
					paramInfo.put(key,value);
				}
				
				/**
				 * 2.5단계 : 페이징조회형 그리드 여부
				 */
//				debug(grdData.getTotalCount());
//				debug(grdData.getNavigateValue());
				
				if (grdData.getTotalCount() != -1L) {
					String naviValue[] = grdData.getNavigateValue().split(",");
					
					/* 0번째 배열에는 보여질 데이터 수
					 * 1번째 배열에는 페이지 인덱스 수
					 */
					long pageSize = Long.parseLong(naviValue[0]);
					long pageNo = Long.parseLong(naviValue[1]) + 1; // 와이즈는 0 부터, DAFrame은 1부터임.
					
					paramInfo.put("WISEGRID_PAGE_NO", pageNo);
					paramInfo.put("WISEGRID_PAGE_SIZE", pageSize);
				}

				input.putMTO(grdName, paramInfo);

				/**
				 * 3단계 : 각 그리드 로우 데이타 및 구조정보 저장.
				 */

				// 그리드 구조정보,
				ChannelDataHeader header = new ChannelDataHeader();
				header.setNames(new ATO(grdData.getHeaderSequence()));

				// 저장형 해당그리드가 가진 로우수를 구하자. 아래처럼.....
				int subRowCnt = grdData.getHeaders()[0].getRowCount();
				
				//debug(subRowCnt);
				
				if (subRowCnt > 0) {
					inputDS.putHeader(grdName, header);

					LTO table = new LTO();
					MTO row =  null;
					for(int j = 0; j < subRowCnt; j++) {
						row = new MTO();
						for (int k=0;k<header.size();k++) {
							String key = header.getNames().get(k);
							String value = null;

							GridHeader grdHeader = grdData.getHeader(key);
							
							if (grdHeader.getDataType().equals(OperateGridData.t_combo)) {
								// 한컬럼내에 콤보의 종류가 틀릴때 (대중소 분류가 있을때 중분류 이하의 경우는 대분류의 종류에 따라 달라지는 경우)
								if (grdHeader.hasComboList()) {
									
									value = grdHeader.getComboHiddenValues(grdHeader.getSelectedComboListKey(j))[grdHeader.getSelectedIndex(j)];
								}else{
									//debug(grdHeader.getComboHiddenValues().length);
									//debug(grdHeader.getSelectedIndex(j));
									if (grdHeader.getSelectedIndex(j) >= 0) { 
										value = grdHeader.getComboHiddenValues()[grdHeader.getSelectedIndex(j)];
									} else {
										value = "";
									}
								}

							}else {

								if (key.equals("CRUD")) {
									value = grdHeader.getHiddenValue(j);
									//엑셀임포트시 에러방지 (CRUD 이용시)
									if (value.length() == 0) {
										row.setState('C');
									} else {
										row.setState(value.charAt(0));	
									}
								} else {
									value = grdHeader.getValue(j);
								}
							}

							row.put(header.getNames().get(k), value);
						}


						table.add(row);
					}

					input.putLTO(grdName, table);
					
				} else {
					// 데이타가 없는 저장형.
					if (isSaveMode) {
						//debug("!!!!!!!!!!!!!!");
						inputDS.putHeader(grdName, header);
					// 조회형
					} else {
						outputDS.putHeader(grdName, header);
					}
				}


			}

		} catch (Exception e) {
			throw new FrameException(e);
		}

		input.setDataStructure(inputDS);
		output.setDataStructure(outputDS);

		//debug(input);
		debug("[inputDS]"+input.getDataStructure());
		debug("[outputDS]"+output.getDataStructure());
	}
}
