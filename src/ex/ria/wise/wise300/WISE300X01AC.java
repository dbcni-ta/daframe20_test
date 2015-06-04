package ex.ria.wise.wise300;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.ATO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.dto.support.Nexter;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import java.sql.SQLException;

/**
 *  <pre>
 *  [EX] 멀티그리드 [CRUD] 저장(CUD) (WISE300.X01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 멀티그리드 [CRUD]
 *  유스케이스 아이디   : WISE300
 *  이벤트 명           : 저장(CUD)
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
public class WISE300X01AC extends NormalTxService {


    public WISE300X01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {
    	
    	LTO lto1 = input.getLTO("WiseGrid");
    	
    	Tactics tactics = TacticsFactory.getInstance(input.getTx());
    	
    	ATO gridNames = input.getATO("WISEGRID_NAME");
    	
    	Nexter nexter = gridNames.getNexter();
    	while (nexter.hasNext()) {
    		
    		String gridName = nexter.next();
        
	    	LTO grid = input.getLTO(gridName);
	    	
	    	debug(gridName+grid);
	    	
	    	if (grid == null) {
		        MTO param = input.getMTO(gridName);
		        param.put("total", "0");
		        
		        // 출력할 파라메타 설정
		        output.putMTO(gridName, param);
	    		continue;
	    	}
	    	
	    	int cnt = 0;
	    	
	    	for(int i=0;i<grid.size();i++)
	    	{
	    		MTO row = grid.get(i);
	    		
	    		switch (row.getState()) {
	    			case 'C' : cnt += tactics.insert("EDU.등록_STADIUM", row);
	    				        break;
	    			case 'U' : cnt += tactics.update("EDU.수정_STADIUM", row);
			        			break;
	    			case 'D' : cnt += tactics.delete("EDU.삭제_STADIUM", row);
			        			break;
	    		}
	    	}
	    	
	        MTO param = input.getMTO(gridName);
	        param.put("total", cnt);
	        
	        // 출력할 파라메타 설정
	        output.putMTO(gridName, param);
    	}
        
    }
}
