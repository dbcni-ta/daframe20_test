package edu.case004;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import java.sql.SQLException;

/**
 *  <pre>
 *  CASE004 등록처리 A (Chained) (CASE004.C01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE004
 *  유스케이스 아이디   : CASE004
 *  이벤트 명           : 등록처리 A (Chained)
 *  이벤트 아이디       : C01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : CHAINED
 *  출력 URL            : CASE004.R01
 *
 *  비고                : 예제
 *  </pre>
 */
public class CASE004C01AC extends NormalTxService {


    public CASE004C01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

        // →→ 본 라인을 삭제하신 후 코딩을 진행하십시요.
        
    }
}
