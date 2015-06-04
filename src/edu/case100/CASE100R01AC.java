package edu.case100;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.ATO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

import java.sql.SQLException;

/**
 *  <pre>
 *  CASE100 WEB-LTO 입력채널 예제 (CASE100.R01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE100
 *  유스케이스 아이디   : CASE100
 *  이벤트 명           : WEB-LTO 입력채널 예제
 *  이벤트 아이디       : R01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB-LTO
 *  출력 채널 유형      : XML
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class CASE100R01AC extends NormalTxService {


    public CASE100R01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

        LTO lto = input.getLTO("inputTable");
        
        output.putLTO("inputTable", lto);
        
    }
}
