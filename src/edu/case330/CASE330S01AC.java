package edu.case330;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.net.process.ProcessExecuter;

import java.sql.SQLException;

/**
 *  <pre>
 *  연계 API 3 (로컬 명령어 실행) 로컬 OS 명령어 실행 (CASE330.S01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : 연계 API 3 (로컬 명령어 실행)
 *  유스케이스 아이디   : CASE330
 *  이벤트 명           : 로컬 OS 명령어 실행
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
public class CASE330S01AC extends NormalTxService {


    public CASE330S01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	// 단순 호출 (로그는 Systemout으로 처리된다)
    	int resultA = ProcessExecuter.execute("cmd /c dir");
    	
    	// 로컬 실행 로그를 StringBuffer에 받아온다.
    	StringBuffer buffer = new StringBuffer();
    	int resultB = ProcessExecuter.execute("cmd /c dir",buffer);
    	
    	output.put("ResultCodeA",resultA);
    	output.put("ResultCodeB",resultB);
        output.put("ResultDumpB", buffer.toString());
    }
}
