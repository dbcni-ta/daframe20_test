package ex.ac.biz.biz002;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.gen.RandomString;

/**
 *  <pre>
 *  [EX] Tactics 배치 (BIZ002.B01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] Tactics
 *  유스케이스 아이디   : BIZ002
 *  이벤트 명           : 배치
 *  이벤트 아이디       : B01 
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
public class BIZ002B01AC extends NormalTxService {


	public BIZ002B01AC(Class clazz) throws FrameException {
		super(clazz);
	}

	protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

		Tactics tactics = TacticsFactory.getInstance(input.getTx());
		
		// INSERT 배치 예시

		LTO batchLto = new LTO();
		MTO inputLto = null;

		for (int i=0;i<10;i++) {
			inputLto = new MTO();
			inputLto.put("id",RandomString.randomAlphabetic(10));
			inputLto.put("name", "전찬모");
			inputLto.put("age", "27");
			inputLto.put("memo", "복제데이타.");
			batchLto.add(inputLto);
		}

		tactics.doBatch("EX.BIZ.임의입력", batchLto);
        
        output.put("배치처리", "완료");


	}
}
