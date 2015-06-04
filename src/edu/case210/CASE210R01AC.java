package edu.case210;

import java.security.Key;
import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.crypto.sk.SymmetricKeyUtil;
import com.cni.fw.ff.cache.CommonCacheManager;
import com.cni.fw.ff.common.SystemInfo;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  CASE210 대칭키 암호화 (AES) 테스트 (CASE210.R01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE210
 *  유스케이스 아이디   : CASE210
 *  이벤트 명           : 대칭키 암호화 (AES) 테스트
 *  이벤트 아이디       : R01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : JSP
 *  출력 URL            : /demo/edu/case210.jsp
 *
 *  비고                : 예제
 *  </pre>
 */
public class CASE210R01AC extends NormalTxService {
	
	static String CACHE_ID = "CACHE_KEY_DES_KEY";


    public CASE210R01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {
    	
    	Key desKey = null;
    	
    	// 캐쉬 매니저를 사용하여 사전에 만들어 둔 대칭키를 캐쉬로드 한다.
    	// 처음 호출될 경우 키 로딩후 캐쉬에 등록, 재차 호출될 경우 캐쉬에서 로딩하게 된다.
    	if (!CommonCacheManager.getInstance().isCache(CACHE_ID)) {
    		String keyPath = SystemInfo.getHomePath()+"/WEB-INF/conf/keys/AES.key";
    		desKey = SymmetricKeyUtil.loadKey( keyPath);
			CommonCacheManager.getInstance().put(CACHE_ID, desKey);
		} else {
			desKey = (Key) CommonCacheManager.getInstance().get(CACHE_ID);
		}
    	
    	String type = input.getDefault("TYPE");
    	String resultText = null;
    	
    	if (type.equals("ENCRYPT")) {
    		String text = input.getDefault("TEXT");
    		resultText = SymmetricKeyUtil.encryptAES(desKey, text);
    	} else if (type.equals("DECRYPT")) {
    		String text = input.getDefault("TEXT");
    		resultText = SymmetricKeyUtil.decryptAES(desKey, text);
    	}
		
    	output.put("RESULT_TEXT", resultText);
    }
}
