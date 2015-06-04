package edu.case002;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import java.sql.SQLException;

/**
 *  <pre>
 *  CASE002 데이타 설치 (CASE002.Z01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE002
 *  유스케이스 아이디   : CASE002
 *  이벤트 명           : 데이타 설치
 *  이벤트 아이디       : Z01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : XML
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class CASE002Z01AC extends NormalTxService {


    public CASE002Z01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {
    	Tactics tactics = TacticsFactory.getInstance(input.getTx());
    	
    	if (input.getMaster().containsKey("install")) {
    		tactics.executeMultiQuery("EDU.INSTALL", true);
    		output.setMessage("본 CASE 관련 DB를 생성하였습니다.");
    	} else {
    		output.setMessage("주의! 본 서비스는 본 CASE 관련 DB를 초기화합니다. 신규로 테이블을 생성하거나, 초기화할때 사용하십시요."+
    				          "(요청시 파라메타에 <install>을 입력할 경우 강제로 TABLE을 제거/생성하고 데이타를 등록 합니다)");
    	}
    }
}
