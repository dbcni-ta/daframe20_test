package ex.ac.biz.biz001;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.dto.support.Nexter;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  [EX] 업무일반 WEB[JSP] 입력값 핸들링 기본 (BIZ001.N08)
 *
 *  개발자              : 홍길동
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 업무일반
 *  유스케이스 아이디   : BIZ001
 *  이벤트 명           : 입력과출력의 기본
 *  이벤트 아이디       : N08 
 *  설계자              : 홍길동
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : JSP
 *  출력 채널 유형      : XML
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class BIZ001N08AC extends NormalTxService {


    public BIZ001N08AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

        /**
         * 본 입력은 WEB,JSP 형태의 입력채널에서 동작하는 예제입니다.
         * 
         * 어떠한 연산도 없이 가져온후 그대로 출력해주는 형태로 코딩되어 있습니다.
         */
    	
    	
    	
    	/**
    	 *  1.WEBFORM의 단일 필드값 가져와 출력하기
    	 */
    	
    	MTO masterMto = input.getMaster();
    	output.setMaster(masterMto);
    	
    	// **활용설명**
    	// 폼에서 넘긴 key1=value1 이라는 데이타가 있을 경우 이를 꺼낼때 위의 소스를 가정하면 아래와 같은 형태 모두가 가능하다.
    	// 1. masterMto.get("key1");
    	// 2. input.Master().get("key1");
    	// 3. input.get("key1");             <--- 주로 사용되는 코드
    	
    	
    	/**
    	 *  2.WEBFORM의 다중 필드값 가져와 출력하기
    	 */
    	
    	Nexter nx = input.getNexterATO();
    	while (nx.hasNext()) {
    		String atoKey = nx.next();
    		output.putATO(atoKey, input.getATO(atoKey));
    	}
    	
    	// **활용설명**
    	// 폼에서 넘긴 key1=value1&key1=value2 이라는 데이타가 있을 경우 이를 꺼내어 사용하는 방법.
    	// ATO arrayAto = input.getATO("key1");
    	// for (int i=0;i<arrayAto.size();i++) {
    	//   debug(arrayAto.get(i)); <---  여기서 value1, value2이 순서대로 꺼내져서 찍히게 되는 예시.
    	// }
    }
}
