package ex.ac.biz.biz004;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.query.dynamic.DynamicTactics;
import com.cni.fw.db.cdq.tactics.query.dynamic.DynamicTacticsFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  [EX] 업무고급 동적 쿼리 (DynamicTactics) (BIZ004.T02)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060713
 *
 *  유스케이스 명       : [EX] 업무고급
 *  유스케이스 아이디   : BIZ004
 *  이벤트 명           : 동적 쿼리 (DynamicTactics)
 *  이벤트 아이디       : T02 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB-CM-001
 *  출력 채널 유형      : XML-CM-001
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class BIZ004T02AC extends NormalTxService {


    public BIZ004T02AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	DynamicTactics tactics = DynamicTacticsFactory.getInstance(input.getTx());
    	
     	MTO clauseMto = new MTO();
     	clauseMto.put("name", "전찬모");
     	
     	output.putLTO("TACTICS-MULTI",  tactics.selectList("EX.BIZ.동적정보조회", clauseMto));
        
    }
}
