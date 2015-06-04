package ex.ac.biz.biz009;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  [EX] 흐름제어 커밋or롤백 (BIZ009.X07)
 *
 *  개발자              : 홍길동
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 흐름제어
 *  유스케이스 아이디   : BIZ009
 *  이벤트 명           : 커밋or롤백
 *  이벤트 아이디       : X07 
 *  설계자              : 홍길동
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : JSP
 *  출력 채널 유형      : XML
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class BIZ009X07AC extends NormalTxService {


    public BIZ009X07AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	if (input.get("rollback") != null) {
    		throw new FrameException("롤벡테스트");
    	}
        
    }
}
