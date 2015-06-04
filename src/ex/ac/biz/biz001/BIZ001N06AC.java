package ex.ac.biz.biz001;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.DateUtil;
import com.cni.fw.web.session.so.CommonSession;

/**
 *  <pre>
 *  [EX] 업무일반 세션처리 기본 (BIZ001.N06)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 업무일반
 *  유스케이스 아이디   : BIZ001
 *  이벤트 명           : 세션처리 기본
 *  이벤트 아이디       : N06 
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
public class BIZ001N06AC extends NormalTxService {


    public BIZ001N06AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	// CauseDTO인 input의 getSession API를 통해 세션 오브젝트를 얻을수 있다.
    	// 기본적으로 CommonSession 을 상속받은 모든 객체를 사용할 수 있으므로 프로젝트별로 가끔 달라질 수도 있다.
        CommonSession so = input.getCommonSession();
        
        if (so != null) {
        
	        // 세션정보의 일부를 출력하는 방법.
	        output.put("UserId",so.getUserId());
	        output.put("UserIp", so.getUserIp());
	        output.put("lastAccessTime", DateUtil.dateToStr(so.getLastAccessTime(), "yyyy-MM-dd HH:MM:ss")); 
	        output.put("firstCommand", so.getFirstCommand());
	        output.put("lastCommand", so.getLastCommand()); 
	        output.putMTO("SessionData", so.getDataMap());
        } else {
        	throw new ServiceException("C-001", "세션이 존재하지 않습니다.");
        }
        
    }
}
