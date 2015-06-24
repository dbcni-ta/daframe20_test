package edu.case006;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import java.sql.SQLException;

/**
 *  <pre>
 *  CASE006 업로드 실행 (CASE006.F02)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE006
 *  유스케이스 아이디   : CASE006
 *  이벤트 명           : 업로드 실행
 *  이벤트 아이디       : F02 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : FILE
 *  출력 채널 유형      : XML
 *  출력 URL            : 
 *
 *  비고                : 예제
 *  </pre>
 */
public class CASE006F02AC extends NormalTxService {


    public CASE006F02AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

        // →→ 본 라인을 삭제하신 후 코딩을 진행하십시요.
        
    }
}
