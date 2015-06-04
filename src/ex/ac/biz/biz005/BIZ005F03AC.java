package ex.ac.biz.biz005;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.common.SystemInfo;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.FileUtil;

/**
 *  <pre>
 *  [EX] 활용편 파일삭제 (BIZ005.F03)
 *
 *  개발자              : 홍길동
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 활용편
 *  유스케이스 아이디   : BIZ005
 *  이벤트 명           : 파일삭제
 *  이벤트 아이디       : F03 
 *  설계자              : 홍길동
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : JSP
 *  출력 채널 유형      : CHAINED
 *  출력 URL            : BIZ005.R02
 *
 *  비고                : 
 *  </pre>
 */
public class BIZ005F03AC extends NormalTxService {


    public BIZ005F03AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	 String targetFile = input.get("fileName");
    	 
    	 debug(targetFile);
         
         FileUtil.deleteFile(SystemInfo.getHomePath()+"upload/"+targetFile);
         
         output.setMessage("S-002","삭제 ["+targetFile+"]");
    }
}
