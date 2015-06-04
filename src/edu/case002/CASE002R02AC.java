package edu.case002;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import java.sql.SQLException;

/**
 *  <pre>
 *  CASE002 단순형 조회 B (CASE002.R02)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE002
 *  유스케이스 아이디   : CASE002
 *  이벤트 명           : 단순형 조회 B
 *  이벤트 아이디       : R02 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : JSP
 *  출력 URL            : /edu/case002r02.jsp
 *
 *  비고                : 
 *  </pre>
 */
public class CASE002R02AC extends NormalTxService {


    public CASE002R02AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

        Tactics tactics = TacticsFactory.getInstance(input.getTx());
        
        output.putLTO("result_B", tactics.selectList("EDU.조회_NATION", input.getMaster()));
    }
}
