package ex.ria.wise.wise100;

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
 *  [EX] 기본 [CRUD] 조회 (WISE100.R01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 기본 [CRUD]
 *  유스케이스 아이디   : WISE100
 *  이벤트 명           : 조회
 *  이벤트 아이디       : R01 
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
public class WISE100R01AC extends NormalTxService {


    public WISE100R01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	Tactics tactics = TacticsFactory.getInstance(input.getTx());

        // 그리드 ID (서버/클라이언트 식별id)
        String gridName = "WiseGrid";
        
        // 입력파라메타
        MTO param = input.getMTO(gridName);
        // 입력파라메타 확인
        debug(param);
        
        // 쿼리 실행
        LTO result = tactics.selectList("EDU.조회_STADIUM", param);
        
        // 결과 조회수를 전달하기 위해 파라메타를 덧붙임
        param.put("total", result.size());
        
        // 출력할 파라메타 설정
        output.putMTO(gridName, param);
        // 출력할 그리드 설정
        output.putLTO(gridName, result);
        
    }
}
