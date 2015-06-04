package edu.case002;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  CASE002 단순형 조회 A (CASE002.R01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE002
 *  유스케이스 아이디   : CASE002
 *  이벤트 명           : 단순형 조회 A
 *  이벤트 아이디       : R01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : JSP
 *  출력 URL            : /edu/case002r01.jsp
 *
 *  비고                : 
 *  </pre>
 */
public class CASE002R01AC extends NormalTxService {


    public CASE002R01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {
    	
        Tactics tactics = TacticsFactory.getInstance(input.getTx());
        
        output.putLTO("result_A", tactics.selectList("EDU.조회_STADIUM", null));
    }
}
