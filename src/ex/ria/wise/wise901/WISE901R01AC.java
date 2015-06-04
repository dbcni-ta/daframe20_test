package ex.ria.wise.wise901;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  [비표준-API활용] 다단링크콤보-Client (출력만JSP) 조회 (WISE901.R01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [비표준-API활용] 다단링크콤보-Client (출력만JSP)
 *  유스케이스 아이디   : WISE901
 *  이벤트 명           : 조회
 *  이벤트 아이디       : R01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WISE
 *  출력 채널 유형      : JSP
 *  출력 URL            : /RIA/WiseGrid/samples/WISE901R01.jsp
 *
 *  비고                : 
 *  </pre>
 */
public class WISE901R01AC extends NormalTxService {


    public WISE901R01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	LTO lto = new LTO();
    	
    	MTO mto = null;
    	
    	mto = new MTO();
    	mto.put("COMBO1","A1");
    	mto.put("COMBO2","B1");
    	mto.put("COMBO3","C1");
    	mto.put("MEMO","야호");
    	lto.add(mto);
    	
    	mto = new MTO();
    	mto.put("COMBO1","A2");
    	mto.put("COMBO2","B3");
    	mto.put("COMBO3","C5");
    	mto.put("MEMO","음냐");
    	lto.add(mto);
    	
    	output.putLTO("result", lto);
        
    }
}
