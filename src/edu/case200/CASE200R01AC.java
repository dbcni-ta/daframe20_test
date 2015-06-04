package edu.case200;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.crypto.md.MessageDigestUtil;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  CASE100 비밀번호 암호화 (SHA256) (CASE200.R01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE200
 *  유스케이스 아이디   : CASE200
 *  이벤트 명           : 메시지압축 암호화 (SHA256)
 *  이벤트 아이디       : R01 
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
public class CASE200R01AC extends NormalTxService {


	public CASE200R01AC(Class clazz) throws FrameException {
		super(clazz);
	}

	protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

		String passwd = input.getDefault("PASSWORD");
		String pre_passwd = input.getDefault("PRE_PASSWORD");

		// 입력한 패스워드가 있을때...
		if (!passwd.equals("")) {
			
			// SHA256 알고리즘으로 메시지 암호화 (MD5, SHA1, SHA128, SHA256, SHA384, SHA512 등도 가능)
			String md_passwd = MessageDigestUtil.digestSHA256(passwd);
			
			// 이전에 입력한 패스워드가 존재할 경우 해당 SHA256 결과와 비교.
			if (!pre_passwd.equals("")) {
				// 동일한지 비교.
				if (md_passwd.equals(pre_passwd)) {
					output.put("CHECKSUM","YES");
				} else {
					output.put("CHECKSUM","NO");
				}
			}

			// 현재 암호화된 압축
			output.put("PRE_PASSWORD", md_passwd);
		}
	}
}
