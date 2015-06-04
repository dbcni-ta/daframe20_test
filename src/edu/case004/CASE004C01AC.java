package edu.case004;

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
 *  CASE004 등록처리 A (Chained) (CASE004.C01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE004
 *  유스케이스 아이디   : CASE004
 *  이벤트 명           : 등록처리 A (Chained)
 *  이벤트 아이디       : C01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : CHAINED
 *  출력 URL            : TEST04.L01
 *
 *  비고                : 
 *  </pre>
 */
public class CASE004C01AC extends NormalTxService {


    public CASE004C01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	debug(input);
    	
    	Tactics tactics = TacticsFactory.getInstance(input.getTx());
        
        tactics.insert("EDU.등록_NATION", input.getMaster());
        
    }
}
