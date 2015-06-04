package ex.ria.wise.wise110;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import java.sql.SQLException;

/**
 *  <pre>
 *  [EX] 기본 [CHECK] 체크된것만 업데이트 (WISE110.X01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 기본 [CHECK]
 *  유스케이스 아이디   : WISE110
 *  이벤트 명           : 체크된것만 업데이트
 *  이벤트 아이디       : X01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WISE
 *  출력 채널 유형      : WISE
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class WISE110X01AC extends NormalTxService {


    public WISE110X01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	Tactics tactics = TacticsFactory.getInstance(input.getTx());
        
    	LTO grid = input.getLTO("WiseGrid");
    	
    	if (grid == null) {
    		throw new ServiceException("처리할 데이타가 없습니다.");
    	}
    	
    	int cnt = 0;
    	
    	for(int i=0;i<grid.size();i++) {
    		MTO row = grid.get(i);
    		cnt += tactics.update("EDU.수정_STADIUM", row);
    	}
    	

        // 그리드 ID (서버/클라이언트 식별id)
        String gridName = "WiseGrid";
        
        MTO param = input.getMTO(gridName);
        param.put("total", cnt);
        
        // 출력할 파라메타 설정
        output.putMTO(gridName, param);
        
    }
}
