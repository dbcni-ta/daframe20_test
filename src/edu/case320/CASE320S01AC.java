package edu.case320;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.conf.BaseConfig;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.net.http.xml.DTOSender;

/**
 *  <pre>
 *  연계 API 2 (XML Request) HTTP XML Request 요청 (CASE320.S01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : 연계 API 2 (XML Request)
 *  유스케이스 아이디   : CASE320
 *  이벤트 명           : HTTP XML Request 요청
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
public class CASE320S01AC extends NormalTxService {


    public CASE320S01AC(Class clazz) throws FrameException {
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
    	
    	String targetUrl = "http://localhost/CASE320.S02.cmd";
    	String charSet = "UTF-8";
    	
    	output.put("NAME", "홍길동");
    	output.put("AGE", "20");
    	
    	LTO testTable = new LTO();
    	MTO testRow = new MTO();
    	testRow.put("NAME", "동부CNI");
    	testRow.put("ADDR", "서울시 강남구 삼성동");
    	testTable.add(testRow);
    	
    	output.putLTO("CORP", testTable);
    	
    	EffectDTO remoteOutput = DTOSender.send(targetUrl, charSet, output);
    	
        output.setMaster(remoteOutput.getMaster());
        output.setMaster(remoteOutput.getMaster());
        
    }
}
