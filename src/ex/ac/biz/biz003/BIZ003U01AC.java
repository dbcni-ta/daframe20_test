package ex.ac.biz.biz003;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.query.TacticsPrime;
import com.cni.fw.db.cdq.tactics.query.TacticsPrimeFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.maker.StringMaker;

/**
 *  <pre>
 *  [EX] Prime 수정 (BIZ003.U01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] Prime
 *  유스케이스 아이디   : BIZ003
 *  이벤트 명           : 수정
 *  이벤트 아이디       : U01 
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
public class BIZ003U01AC extends NormalTxService {
;

    public BIZ003U01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

        TacticsPrime prime = TacticsPrimeFactory.getInstance(input.getTx());

        MTO updateMto = new MTO();
        
        updateMto.put("ID", "Mute");
        updateMto.put("AGE", "30");
        
        output.put("입력ID", updateMto.get("id"));
        
        output.put("처리결과", StringMaker.make(prime.update("EX_BIZ_INFO", updateMto)));
        
    }
}
