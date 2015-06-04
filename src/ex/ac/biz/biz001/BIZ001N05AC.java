package ex.ac.biz.biz001;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.web.session.so.CommonSession;

/**
 *  <pre>
 *  [EX] 업무일반 로그아웃 기본 (BIZ001.N05)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 업무일반
 *  유스케이스 아이디   : BIZ001
 *  이벤트 명           : 로그아웃 기본
 *  이벤트 아이디       : N05 
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
public class BIZ001N05AC extends NormalTxService {


    public BIZ001N05AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	// 세션정보를 얻어 로그아웃 여부를 세팅한후.
        CommonSession so = input.getCommonSession();
        so.setLogOut(true); // 로그아웃 된것으로 설정한다.
         
        output.put("login", "로그아웃 성공");
        
    }
}
