package edu.case310;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.conf.BaseConfig;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.XmlUtil;
import com.cni.fw.net.http.HttpConnecter;

import java.sql.SQLException;

import org.w3c.dom.Document;

/**
 *  <pre>
 *  연계 API 1 (HTTP POST) HTTP 호출 B (대상CMD유형:WEB-XML) (CASE310.S02)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : 연계 API 1 (HTTP POST)
 *  유스케이스 아이디   : CASE310
 *  이벤트 명           : HTTP 호출 B (대상CMD유형:WEB-XML)
 *  이벤트 아이디       : S02 
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
public class CASE310S02AC extends NormalTxService {


    public CASE310S02AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	// DAFrame은 개발모드에서 한번에 1개의 요청을 받은 후 끝날때까지 처리를 동기화하고 있기때문에 
    	// 서비스 진행중 다시 HTTP로 DAFrame 호출하면 LOCK 현상이 발생합니다. 
    	// 따라서 운영모드로 변경하여 테스트 합니다.
    	// 물론 타 서버로 연계할 때에는 아무런 문제가 없습니다. ^^
    	if (BaseConfig.isDevMode()) {
    		throw new FrameException("본 예제는 서비스처리중 서버에서 다시 HTTP로 서비스를 연속 호출함으로 개발모드에서는 동기화 문제로 테스트할 수 없습니다. 운영 모드로 설정을 변경하여 테스트해주십시요.");
    	}
    	
    	String targetUrl = "http://localhost/CASE001.S04.cmd";
    	MTO param = new MTO();
    	param.put("NAME", "홍길동");
    	param.put("AGE", "20");
    	String charSet = "UTF-8";
    	
        Document resultXml = HttpConnecter.getXmlByParam(targetUrl, param, charSet);
        
        MTO result = XmlUtil.extractMtoByChildElementTextNode(resultXml, "/EffectDTO/Master");
        
        output.setMaster(result);
    }
}
