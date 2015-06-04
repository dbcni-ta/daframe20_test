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
 *  [EX] SAP TABLE-저장 (SAP01.X01)
 *
 *  개발자              : 홍길동
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] SAP
 *  유스케이스 아이디   : SAP01
 *  이벤트 명           : TABLE-저장
 *  이벤트 아이디       : X01 
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
public class SAP01X01AC extends SapTxService {


    public SAP01X01AC(Class clazz) throws FrameException {
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
    	 * 입력 파라메타
    	 */
    	MTO importParam = new MTO();
    	importParam.put("I_ZEBID","test01");
    	importParam.put("I_BLDAT","20081110");
    	importParam.put("I_BLART","DI");
    	importParam.put("I_BUDAT","20081110");
    	//importParam.put("I_BKTXT","test");
		importParam.put("I_BELNR","");
		importParam.put("I_GJAHR","");
		importParam.put("I_TYPE","0");

		/**
		 * 입력 데이타.
		 */
        LTO tableData = new LTO();
        MTO row = null;
        
        row = new MTO();
        row.put("ZEBID", "TEST01");
        row.put("ZEBLINEID", "001");
        row.put("BSCHL", "31");
        row.put("LIFNR", "E200255");
        row.put("HKONT", "");
        row.put("WRBTR", "10000000");
        row.put("MWSKZ", "");
        row.put("GSBER", "1000");
        row.put("ZLSCH", "K");
        row.put("SGTXT", "예산 체크2");

        tableData.add(row);
        
        row = new MTO();
        row.put("ZEBID", "TEST01");
        row.put("ZEBLINEID", "002");
        row.put("BSCHL", "40");
        row.put("LIFNR", "");
        row.put("HKONT", "611110007");
        row.put("WRBTR", "10000000");
        row.put("MWSKZ", "");
        row.put("GSBER", "");
        row.put("ZLSCH", "");
        row.put("SGTXT", "예산체크2하하하하가나다라마바사");
        row.put("HBKID", "");
        row.put("KOSTL", "1503");
        row.put("POSID", "");
        row.put("FWBAS", "");
        row.put("ZUONR", "");
        row.put("ZTERM", "");
        tableData.add(row);
        
        
        /**
		 * SAP 수정
		 * 
		 * 결과로 exportParam 을 MTO 형태로 반환한다.
		 */
        MTO exportParam = sapConnection.update("ZFI_RFC_EB_DOC", importParam, "T_EB_BSEG", tableData);
		output.putMTO("RESULT",exportParam);
		
		/**
		debug("처리결과 E_SUBRC   : "+exportParam.get("E_SUBRC"));
		debug("처리결과 E_MESSAGE : "+exportParam.get("E_MESSAGE"));
		debug("처리결과 E_BELNR   : "+exportParam.get("E_BELNR"));
		debug("처리결과 E_GJAHR   : "+exportParam.get("E_GJAHR"));
		*/
        
    }
}
