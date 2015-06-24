package edu.case001;

import com.cni.fw.arch.smb.ac.BasicService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import java.sql.SQLException;

/**
 *  <pre>
 *  CASE001 출력채널 XML (CASE001.S01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE001
 *  유스케이스 아이디   : CASE001
 *  이벤트 명           : 출력채널 XML
 *  이벤트 아이디       : S01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : NA
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : XML
 *  출력 URL            : 
 *
 *  비고                : 예제
 *  </pre>
 */
public class CASE001S01AC extends BasicService {


    public CASE001S01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

        // →→ 본 라인을 삭제하신 후 코딩을 진행하십시요.
        
    }
}
