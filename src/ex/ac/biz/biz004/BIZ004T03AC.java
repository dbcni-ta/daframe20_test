package ex.ac.biz.biz004;

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
import com.cni.fw.ff.util.CalUtil;

/**
 *  <pre>
 *  [EX] 업무고급 쿼리 결과의 사후 첨삭 (BIZ004.T03)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 업무고급
 *  유스케이스 아이디   : BIZ004
 *  이벤트 명           : 쿼리 결과의 사후 첨삭
 *  이벤트 아이디       : T03 
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
public class BIZ004T03AC extends NormalTxService {


    public BIZ004T03AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	Tactics tactics = TacticsFactory.getInstance(input.getTx());
    	
     	MTO clauseMto = new MTO();
     	clauseMto.put("name", "전찬모");
     	
     	LTO result = new LTO();
     	
     	LTO temp = tactics.selectList("EX.BIZ.전체정보조회", clauseMto);
     	
     	MTO row = null;
     	for (int i=0;i<temp.size();i++) {
     		row = temp.get(i);
     		if (CalUtil.compare(row.get("AGE"),">=","30")) {
     			row.put("Rank", "중년");
     		} else {
     			row.put("Rank", "청년");
     		}
     		result.add(row);
     	}
     	output.putLTO("TACTICS-MULTI-MODIFY", result);
        
    }
}
