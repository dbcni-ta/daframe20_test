package edu.case008;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import java.sql.SQLException;

/**
 *  <pre>
 *  CASE008 (웹에서실행X) 배치 실행 (VMS) (CASE008.B01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE008 (웹에서실행X)
 *  유스케이스 아이디   : CASE008
 *  이벤트 명           : 배치 실행 (VMS)
 *  이벤트 아이디       : B01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : VMS
 *  출력 채널 유형      : VMS
 *  출력 URL            : 
 *
 *  비고                : 예제
 *  </pre>
 */
public class CASE008B01AC extends NormalTxService {


    public CASE008B01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

        // →→ 본 라인을 삭제하신 후 코딩을 진행하십시요.
        
    }
}
