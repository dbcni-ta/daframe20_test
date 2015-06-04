package edu.case102;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.cache.CommonCacheManager;
import com.cni.fw.ff.common.SystemInfo;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.mapper.PropertyMapper;

import java.sql.SQLException;

/**
 *  <pre>
 *  CASE102 프로퍼티 및 캐쉬 활용 예시 (CASE102.L01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE102
 *  유스케이스 아이디   : CASE102
 *  이벤트 명           : 프로퍼티 및 캐쉬 활용 예시
 *  이벤트 아이디       : L01 
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
public class CASE102L01AC extends NormalTxService {
	
	static String CASE102L01AC_ENG = "CASE102L01AC_ENG";
	static String CASE102L01AC_KOR = "CASE102L01AC_KOR";


    public CASE102L01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    /**
     * 프로퍼티 파일들의 캐쉬 예시.
     * 
     * 본 예시에서는 2개의 프로퍼티파일(한국어,영어)를 각각 두고 CommonCacheManager 클래스를 사용하여
     * 매번 로딩하지 않고 최초에만 파일에서 읽고 이후에는 캐쉬에 등록된 프로퍼티를 읽도록 처리합니다.
     * 
     * CommonCacheManager로 관리되는 캐쉬 데이타들은 DAFrame의 콘솔에서 Config 버튼을 실행함에 따라 refresh 될 수 있습니다.
     */
    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {
    	
    	MTO titleWord = null;
    	
    	if (input.getDefault("WORD","KOR").equals("ENG")) {

	    	// 캐쉬메니저에 관련 키로 존재여부를 확인후 존재하지 않으면 로딩토록 함.
			if (!CommonCacheManager.getInstance().isCache(CASE102L01AC_ENG)) {
				CommonCacheManager.getInstance().put(CASE102L01AC_ENG, PropertyMapper.generate(SystemInfo.getHomePath()+"/WEB-INF/conf/prop/example_eng.properties"));
			}
			titleWord = (MTO) CommonCacheManager.getInstance().get(CASE102L01AC_ENG);
    	} else { 
			// 캐쉬메니저에 관련 키로 존재여부를 확인후 존재하지 않으면 로딩토록 함.
			if (!CommonCacheManager.getInstance().isCache(CASE102L01AC_KOR)) {
				CommonCacheManager.getInstance().put(CASE102L01AC_KOR, PropertyMapper.generate(SystemInfo.getHomePath()+"/WEB-INF/conf/prop/example_kor.properties"));
			}
			titleWord = (MTO) CommonCacheManager.getInstance().get(CASE102L01AC_KOR);
    	}
    	
    	output.setMessage("입력파라메타[WORD]의 값을 [KOR:ENG]로 변경해보십시요. (디폴트:KOR)");
    	output.putMTO("TITLEWORD", titleWord);
        
    }
}
