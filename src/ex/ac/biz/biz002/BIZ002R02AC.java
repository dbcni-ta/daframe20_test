package ex.ac.biz.biz002;

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
 *  [EX] Tactics 페이징 (BIZ002.R02)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] Tactics
 *  유스케이스 아이디   : BIZ002
 *  이벤트 명           : 페이징
 *  이벤트 아이디       : R02 
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
public class BIZ002R02AC extends NormalTxService {


    public BIZ002R02AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	Tactics tactics = TacticsFactory.getInstance(input.getTx());
     	
     	MTO clauseMto = new MTO();
     	clauseMto.put("name", "전찬모");

     	output.putLTO("TACTICS-MULTI", tactics.selectPage("EX.BIZ.전체정보조회", clauseMto, "1", "5").getResult());
        
    }
}
