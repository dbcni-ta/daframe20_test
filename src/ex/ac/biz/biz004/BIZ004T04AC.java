package ex.ac.biz.biz004;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  [EX] 업무고급 프로시져 호출 (BIZ004.T04)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 업무고급
 *  유스케이스 아이디   : BIZ004
 *  이벤트 명           : 프로시져 호출
 *  이벤트 아이디       : T04 
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
public class BIZ004T04AC extends NormalTxService {


    public BIZ004T04AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	Tactics tactics = TacticsFactory.getInstance(input.getTx());
    	
		MTO param = new MTO();
		param.put("param01", "1000");
		
		output.putMTO("처리결과", tactics.executeSP("EX.BIZ.프로시져", param));
		
    }
}
