package edu.case310;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.net.http.HttpConnecter;

/**
 *  <pre>
 *  연계 API 1 (HTTP POST) HTTP 호출 A (대상CMD유형:WEB-JSP) (CASE310.S01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : 연계 API 1 (HTTP POST)
 *  유스케이스 아이디   : CASE310
 *  이벤트 명           : HTTP 호출 A (대상CMD유형:WEB-JSP)
 *  이벤트 아이디       : S01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : XML
 *  출력 URL            : 
 *
 *  비고                : 예제
 *  </pre>
 */
public class CASE310S01AC extends NormalTxService {


    public CASE310S01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	String targetUrl = "http://www.naver.com";
    	MTO param = new MTO();
    	String charSet = "UTF-8";
    	
        String resultHtml = HttpConnecter.getStringByParam(targetUrl, param, charSet);
        
        output.put("ResultHtml", resultHtml);
        
    }
}
