package edu.case005;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.DateUtil;
import com.cni.fw.ff.util.maker.StringMaker;
import com.cni.fw.web.session.so.CommonSession;

/**
 *  <pre>
 *  CASE005 로그인 (CASE005.S01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE005
 *  유스케이스 아이디   : CASE005
 *  이벤트 명           : 로그인
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
public class CASE005S01AC extends NormalTxService {


	public CASE005S01AC(Class clazz) throws FrameException {
		super(clazz);
	}

	protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

		String id = StringMaker.make(input.get("id"));
		String pw = StringMaker.make(input.get("pw"));

		if (id.equals("")) {
			output.setMessage("'id' 를 입력해주세요");
		} else {
			// 로그인 성공
			if (id.equals(pw)) {
				CommonSession cs = input.getCommonSession();
				cs.put("USERID", id); // 실제 사용할 USERID
				cs.put("DATE", DateUtil.getToday());

				cs.setUserId(id); // 로그상 혹은 통계상 USERID를 FW가 인식할수 있도록 세팅해준다. 
				cs.setLogIn(true); // 로그인 된것으로 설정한다.

				output.setMessage(id+"님 로그인에 성공하였습니다");
			// 로그인 실패
			} else {
				output.setMessage("'pw' 는 'id' 와 동일합니다.");
			}
		}
	}
}
