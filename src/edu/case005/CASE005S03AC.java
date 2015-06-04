package edu.case005;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.web.session.so.CommonSession;

import java.sql.SQLException;

/**
 *  <pre>
 *  CASE005 로그아웃 (CASE005.S03)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE005
 *  유스케이스 아이디   : CASE005
 *  이벤트 명           : 로그아웃
 *  이벤트 아이디       : S03 
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
public class CASE005S03AC extends NormalTxService {


    public CASE005S03AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	// 세션정보를 얻어 로그아웃 여부를 세팅한후.
        CommonSession cs = input.getCommonSession();
        cs.setLogOut(true); // 로그아웃 된것으로 설정한다.
         
        output.put("login", "로그아웃 성공");
        
    }
}
