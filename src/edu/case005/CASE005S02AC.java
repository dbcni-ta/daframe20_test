package edu.case005;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.DateUtil;
import com.cni.fw.web.session.so.CommonSession;

import java.sql.SQLException;

/**
 *  <pre>
 *  CASE005 세션정보확인 (CASE005.S02)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE005
 *  유스케이스 아이디   : CASE005
 *  이벤트 명           : 세션정보확인
 *  이벤트 아이디       : S02 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : XML
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class CASE005S02AC extends NormalTxService {


    public CASE005S02AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {
    	
    	CommonSession cs = input.getCommonSession();
    	
        output.put("UserId",cs.getUserId());
        output.put("UserIp", cs.getUserIp());
        output.put("lastAccessTime", DateUtil.dateToStr(cs.getLastAccessTime(), "yyyy-MM-dd HH:MM:ss")); 
        output.put("firstCommand", cs.getFirstCommand());
        output.put("lastCommand", cs.getLastCommand()); 
        output.putMTO("SessionData", cs.getDataMap());
        
    }
}
