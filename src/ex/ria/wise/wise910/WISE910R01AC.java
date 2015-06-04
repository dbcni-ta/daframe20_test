package ex.ria.wise.wise910;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.maker.IntMaker;

/**
 *  <pre>
 *  [비표준-API활용] 동적헤더 조회 (WISE910.R01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [비표준-API활용] 동적헤더
 *  유스케이스 아이디   : WISE910
 *  이벤트 명           : 조회
 *  이벤트 아이디       : R01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WISE
 *  출력 채널 유형      : JSP
 *  출력 URL            : /RIA/WiseGrid/samples/WISE910R01.jsp
 *
 *  비고                : 
 *  </pre>
 */
public class WISE910R01AC extends NormalTxService {


    public WISE910R01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	int colCnt = IntMaker.make(input.getMTO("WiseGrid").get("colCnt"),1);
    	
    	LTO lto = new LTO();
    	
    	MTO mto = null;
    	
    	mto = new MTO();
    	mto.put("MEMO","야호");
    	for (int i=0;i<colCnt;i++) {
    		mto.put("DYNAMIC_"+i,"신데렐라"+i);
    	}
    	lto.add(mto);
    	
    	mto = new MTO();
    	mto.put("MEMO","야호야호");
    	for (int i=0;i<colCnt;i++) {
    		mto.put("DYNAMIC_"+i,"백설공주"+i);
    	}
    	lto.add(mto);
    	
    	output.putLTO("result", lto);
        
    }
}
