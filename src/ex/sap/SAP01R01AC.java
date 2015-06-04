package ex.sap;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.SapTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.net.sap.SapConnection;

/**
 *  <pre>
 *  [EX] SAP TABLE-조회 (SAP01.R01)
 *
 *  개발자              : 홍길동
 *
 *  작성날짜            : 20060712
 *
 *  유스케이스 명       : [EX] SAP
 *  유스케이스 아이디   : SAP01
 *  이벤트 명           : TABLE-조회
 *  이벤트 아이디       : R01 
 *  설계자              : 홍길동
 * 
 *  업무 유형           : SAP
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : XML
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class SAP01R01AC extends SapTxService {


	public SAP01R01AC(Class clazz) throws FrameException {
		super(clazz);
	}

	protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {
		
		/**
		 * SapConnection 객체 확보
		 */
		SapConnection sapConnection = input.getSapConnection();
		
		if (sapConnection == null) {
			throw new FrameException("SAP 커낵션풀이 설정되지 않았거나 초기화에 문제가 있습니다.");
		}

		/**
		 * 입력파라메타 설정
		 */
		MTO importParam = new MTO();
		importParam.put("I_MWSKZ","");
		
		/**
		 * SAP 조회
		 * 
		 * 결과데이타는 LTO로 반환되며, exportParam 값들은 result.getInfoMap() 으로 얻을 수 있음.
		 */
		LTO result = sapConnection.selectList("ZFI_RFC_EB_MWSKZ", importParam, "T_T007A");
		MTO exportParam = result.getInfoMap();
		debug(exportParam);
		
		output.putLTO("RESULT", result);
		
		/**
		 * SapConnection 객체는 업무유형이 SAP일 경우 (extends SapTxService) 자동적으로 close() 된다. 
		 */
	}

}
