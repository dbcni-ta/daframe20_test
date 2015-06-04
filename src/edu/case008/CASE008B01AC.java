package edu.case008;

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
 *  CASE008 배치 실행 (VMS) (CASE008.B01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE008
 *  유스케이스 아이디   : CASE008
 *  이벤트 명           : 배치 실행 (VMS)
 *  이벤트 아이디       : B01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : VMS
 *  출력 채널 유형      : VMS
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class CASE008B01AC extends NormalTxService {


    public CASE008B01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	Tactics tactics = TacticsFactory.getInstance(input.getTx());
    	
    	LTO lto = new LTO();
    	MTO mto = null;
    	
    	for (int i=0;i<10;i++) {
    		mto = new MTO();
    		
        	mto.put("CODE", RandomString.randomAscii(3));
        	mto.put("NAME", "BATCH-TEST");
        	mto.put("CONTINENT", "BATCH-TEST");
        	mto.put("CAPITAL", "BATCH-TEST");
        	
        	lto.add(mto);
    	}
    	
        tactics.doBatch("EDU.등록_NATION", lto);
    }
}
