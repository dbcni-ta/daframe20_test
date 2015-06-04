package ex.ac.biz.biz004;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

import ex.bc.BIZ004BC;

/**
 *  <pre>
 *  [EX] 활용편 BC 개발 패턴 기본 (BIZ004.R01)
 *
 *  개발자              : 홍길동
 *
 *  작성날짜            : 20060712
 *
 *  유스케이스 명       : [EX] 활용편
 *  유스케이스 아이디   : BIZ004
 *  이벤트 명           : BC 개발 패턴 기본
 *  이벤트 아이디       : R01 
 *  설계자              : 홍길동
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB-CM-001
 *  출력 채널 유형      : XML-CM-001
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class BIZ004R01AC extends NormalTxService {


    public BIZ004R01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

        /** 
         * 사용할 BC를 단순 생성한다.
         * 
         * 본 예시의 경우 TX 유형의 BC 이므로 AC의 트랜잭션 자원인 tx를 BC의 생성자를 통해 넘겨준다.
         */ 
    	BIZ004BC bc = new BIZ004BC(input.getTx());
    	
    	output.putLTO("R처리결과", bc.execR01(input.getMaster()));
    	
    	output.put("C처리결과", bc.execC01(input.getMaster()));
    	
    	// 테스트용 데이타 입력. (실제로는 화면에서 데이타를 주어야하겠지요)
    	MTO temp = new MTO();
    	temp.put("id", "Mute      ");
    	temp.put("age", "34");
    	
    	output.put("U처리결과", bc.execU01(temp));
    	
    	output.put("D처리결과", bc.execD01(input.getMaster()));
        
    }
}
