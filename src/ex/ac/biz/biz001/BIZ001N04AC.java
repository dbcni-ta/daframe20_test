package ex.ac.biz.biz001;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.StrUtil;
import com.cni.fw.web.session.so.CommonSession;

/**
 *  <pre>
 *  [EX] 업무일반 로그인 기본 (BIZ001.N04)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 업무일반
 *  유스케이스 아이디   : BIZ001
 *  이벤트 명           : 로그인 기본
 *  이벤트 아이디       : N04 
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
public class BIZ001N04AC extends NormalTxService {


	public BIZ001N04AC(Class clazz) throws FrameException {
		super(clazz);
	}

	protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

		// 테스트를 위해 입력값에 임의로 넣어줌.
		input.put("userid","mute93");

		// 실제 로직 시작.
		String userId = StrUtil.nullToStr(input.get("userid"));
		
		if (userId.length() < 3) {
			throw new ServiceException("C-001","아이디를 3글자 이상으로 입력해주세요.");
		}

		// 이부분에서 DB처리를 수행하여, 아이디가 존재하는지 체크하고,
		// 사용자정보를 DB로부터 확보하여, so 객체를 생성한다.

		CommonSession so = input.getCommonSession();
		so.setLogIn(true); // 로그인 된것으로 설정한다.
		so.setUserId(userId); // 로그상 혹은 통계상 USERID를 FW가 인식할수 있도록 세팅해준다. 실질적인 세션정보로 굳이 본 항목을 사용할 필요는 없다.
		so.put("USERID", userId); // 실제 사용할 USERID
		so.put("KEY01", "기타세션데이타");

		output.put("login", "로그인 성공");
	}
}
