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
 *  [EX] 활용편 임시 (BIZ005.R02)
 *
 *  개발자              : 홍길동
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 활용편
 *  유스케이스 아이디   : BIZ005
 *  이벤트 명           : 임시
 *  이벤트 아이디       : R02 
 *  설계자              : 홍길동
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : JSP
 *  출력 채널 유형      : XML
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class BIZ005R02AC extends NormalTxService {


    public BIZ005R02AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {
    	output.putATO("fileList", FileUtil.getFileList(SystemInfo.getHomePath()+"upload"));
    }
}
