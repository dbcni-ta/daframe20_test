package ex.ac.biz.biz005;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.common.SystemInfo;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.id.SystemC;
import com.cni.fw.web.data.file.SendFileData;

import java.sql.SQLException;

/**
 *  <pre>
 *  [EX] 활용편 파일다운로드 (BIZ005.F02)
 *
 *  개발자              : 홍길동
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 활용편
 *  유스케이스 아이디   : BIZ005
 *  이벤트 명           : 파일다운로드
 *  이벤트 아이디       : F02 
 *  설계자              : 홍길동
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : JSP
 *  출력 채널 유형      : FILE
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class BIZ005F02AC extends NormalTxService {


    public BIZ005F02AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

        String targetFile = input.get("fileName");
        
        SendFileData sendFileData = null;
        sendFileData = new SendFileData(targetFile,SystemInfo.getHomePath()+"upload/"+targetFile);
        
        output.getSystem().putObject(SystemC.DOWNLOAD_FILE, sendFileData);
        
    }
}
