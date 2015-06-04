package edu.case330;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.common.SystemInfo;
import com.cni.fw.ff.conf.BaseConfig;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.net.process.ProcessExecuter;

import java.sql.SQLException;

/**
 *  <pre>
 *  연계 API 3 (로컬 명령어 실행) 로컬 배치 파일 실행 (CASE330.S02)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : 연계 API 3 (로컬 명령어 실행)
 *  유스케이스 아이디   : CASE330
 *  이벤트 명           : 로컬 배치 파일 실행
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
public class CASE330S02AC extends NormalTxService {


    public CASE330S02AC(Class clazz) throws FrameException {
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

    	// 로컬 실행 로그를 StringBuffer에 받아온다.
    	StringBuffer buffer = new StringBuffer();
    	
    	// 실행명령, 실행위치, 결과덤프
    	int result = ProcessExecuter.execute(SystemInfo.getHomePath()+"WEB-INF/batch/DAFrameHttpClient.bat",SystemInfo.getHomePath()+"WEB-INF/batch/", buffer);
    	
    	output.put("ResultCode",result);
        output.put("ResultDump", buffer.toString());
        
    }
}
