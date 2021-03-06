package edu.case007;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.common.SystemInfo;
import com.cni.fw.ff.conf.AppConfig;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.mail.CommonMail;
import com.cni.fw.ff.mail.MailAttach;
import com.cni.fw.ff.util.StrUtil;

/**
 *  <pre>
 *  CASE007 E-Mail 처리 (첨부파일) (CASE007.S02)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE007
 *  유스케이스 아이디   : CASE007
 *  이벤트 명           : E-Mail 처리 (첨부파일)
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
public class CASE007S02AC extends NormalTxService {


    public CASE007S02AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

		if (StrUtil.isEmpty(input.get("to"))) {
			throw new ServiceException("<to> 키에 보낼 사람의 email 주소를 넣으세요");
		}

		CommonMail email = new CommonMail();
		email.setHostName(AppConfig.get("mail.server"));

		email.setFrom("daframe@dongbu.com", "다프레임");
		email.addTo(input.get("to"));
		email.setSubject("[DAFrame] 메일 테스트 (첨부파일)");
		email.setTextMsg("본 메일은 테스트 메일입니다\n\n ^^;");

		/**
		 * 첨부파일
		 */
		MailAttach attach = new MailAttach();
		attach.setPath(SystemInfo.getHomePath()+"/WEB-INF/meta/CommandMeta.xls");
		email.attach(attach);
		
		email.send();
        
    }
}
