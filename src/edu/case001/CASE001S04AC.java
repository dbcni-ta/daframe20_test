package edu.case001;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.ATO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.support.Nexter;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  CASE001 입력채널 WEB (CASE001.S04)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE001
 *  유스케이스 아이디   : CASE001
 *  이벤트 명           : 입력채널 WEB
 *  이벤트 아이디       : S04 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : XML
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class CASE001S04AC extends NormalTxService {


    public CASE001S04AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	// MASTER 설정
        output.setMaster(input.getMaster());
        
        // ATO Set 설정
        Nexter atoKeys = input.getAtoSet().getNexter();
        while (atoKeys.hasNext()) {
        	String key = atoKeys.next();
        	ATO ato = input.getATO(key);
        	
        	output.putATO(key, ato);
        }
        
        
        // ATO Set 설정
        Nexter ltoKeys = input.getLtoSet().getNexter();
        while (ltoKeys.hasNext()) {
        	String key = ltoKeys.next();
        	LTO lto = input.getLTO(key);
        	
        	output.putLTO(key, lto);
        }
    }
}
