package ex.sap;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.SapTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.CommonDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.ATO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.dto.impl.ImplCommonDTO;
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
public class SAP01EX01AC extends SapTxService {


    public SAP01EX01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {
    	
        /**
		 * SapConnection 객체 확보
		 */
        SapConnection sapConnection = input.getSapConnection();
        
		if (sapConnection == null) {
			throw new FrameException("본 예제는 소스 확인 전용 예제입니다. 실행은 불가합니다.");
		}
    	
    	/**
    	 * 입력 파라메타
    	 */
    	MTO importParamerters = new MTO();

		/**
		 * 입력 데이타 (단일)
		 */
        LTO importTable = new LTO();
        
        
        /**
		 *  예제 1 : Table 조회
		 */
        LTO resultTable1 = sapConnection.selectList("RFC_FUNC", importParamerters, "TABLE");
        debug(resultTable1.getInfoMap()); // ExportParam는 이렇게 얻을수 있다.
        
        /**
         *  예제 2 : Table 조회 (+importTable 포함형)
         */
        LTO resultTable2 = sapConnection.selectList("RFC_FUNC", importParamerters, "TABLE", importTable);
        debug(resultTable1.getInfoMap()); // ExportParam는 이렇게 얻을수 있다.
        
        
        /**
         *  예제 3 : 멀티 Table 조회
         */
        ATO targetTables1 = new ATO();
        targetTables1.add("TABLE1");
        targetTables1.add("TABLE2"); // 얼마든지 넣을수 있다. N개까지..
        
        CommonDTO resultMultiTable1 = sapConnection.selectMultiList("RFC_FUNC", importParamerters, targetTables1);
        
        debug(resultMultiTable1.getLTO("TABLE1")); // 이런식으로 결과를 각각 얻어낼수 있다.
        debug(resultMultiTable1.getLTO("TABLE2"));
        debug(resultMultiTable1.getMaster()); //  ExportParam는 이렇게 얻을수 있다.
        
        /**
         *  예제 4 : 멀티 Table 조회 (+importTable 포함형)
         */
        CommonDTO targetTables2 = new ImplCommonDTO();
        LTO importTable1 = new LTO();
        LTO importTable2 = new LTO();
        LTO importTable3 = new LTO();
        
        targetTables2.putLTO("TABLE1",importTable1);
        targetTables2.putLTO("TABLE2",importTable2);
        targetTables2.putLTO("TABLE3",importTable3); // 얼마든지 넣을수 있다. N개까지..
        
        CommonDTO resultMultiTable2 = sapConnection.selectMultiList("RFC_FUNC", importParamerters, targetTables2);
        
        debug(resultMultiTable2.getLTO("TABLE1")); // 이런식으로 결과를 각각 얻어낼수 있다.
        debug(resultMultiTable2.getLTO("TABLE2")); 
        debug(resultMultiTable2.getLTO("TABLE3")); 
        debug(resultMultiTable2.getMaster()); //  ExportParam는 이렇게 얻을수 있다.
        
        /**
         * 예제 5 : Table 등록/수정/삭제
         */
        
        MTO exportParam1 = sapConnection.insert("RFC_FUNC", importParamerters, "TABLE", importTable);
        MTO exportParam2 = sapConnection.update("RFC_FUNC", importParamerters, "TABLE", importTable);
        MTO exportParam3 = sapConnection.delete("RFC_FUNC", importParamerters, "TABLE", importTable);

        
        /**
         * 예제 6 : 멀티 Table 등록/수정/삭제 
         */
        CommonDTO targetTables3 = new ImplCommonDTO();
        LTO importTable5 = new LTO();
        LTO importTable6 = new LTO();
        LTO importTable7 = new LTO();
        targetTables3.putLTO("TABLE1",importTable5);
        targetTables3.putLTO("TABLE2",importTable6);
        targetTables3.putLTO("TABLE3",importTable7); // 얼마든지 넣을수 있다. N개까지..
        
        MTO exportParam4 = sapConnection.updateMulti("RFC_FUNC", importParamerters, targetTables3);
        
    }
}
