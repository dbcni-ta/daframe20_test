package edu.case007;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.conf.AppConfig;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.mail.CommonMail;
import com.cni.fw.ff.util.StrUtil;

import java.sql.SQLException;

/**
 *  <pre>
 *  CASE007 E-Mail 처리 (기본) (CASE007.S01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE007
 *  유스케이스 아이디   : CASE007
 *  이벤트 명           : E-Mail 처리 (기본)
 *  이벤트 아이디       : S01 
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
public class CASE007S01AC extends NormalTxService {


    public CASE007S01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {
    	
		// to 값이 없으면 예외를 강제로 발생시킨다.
		if (StrUtil.isEmpty(input.get("to"))) {
			throw new ServiceException("<to> 키에 보낼 사람의 email 주소를 넣으세요");
		}

		CommonMail email = new CommonMail();
		// SMTP 서버
		email.setHostName(AppConfig.get("mail.server"));
		email.setSmtpPort(587);
		email.setAuthentication("seongj@dongbucni.com", "20112082");

		// 캐릭터 셋 (생략시 EUC-KR)
		// email.setCharset("EUC-KR");

		// 보내는 사람.
		email.setFrom("seongj@dongbucni.com", "다프레임");

		// 받는 사람. (addTo를 계속 해서 여러명을 할수 있음)
		email.addTo(input.get("to"));
		// 받는 사람명을 별도로 주고 싶은 경우
		//email.addTo("mute@dongbu.com", "전찬모");
		// 참조 할 경우
		//email.addCc("mute@dongbu.com", "전찬모");
		// 숨은참조할 경우
		//email.addBcc("mute@dongbu.com", "전찬모");

		// 메일제목
		email.setSubject("[DAFrame] 메일 테스트 (TEXT)");

		// 메일 본문
		email.setTextMsg("본 메일은 테스트 메일입니다\n\n ^^;");

		// 메일 발송
		email.send();
    }
}
