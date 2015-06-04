package ex.ac.biz.biz005;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.common.SystemInfo;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.ATO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.FileUtil;
import com.cni.fw.web.data.file.FileData;

/**
 *  <pre>
 *  [EX] 활용편 파일업로드 (BIZ005.F01)
 *
 *  개발자              : 홍길동
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 활용편
 *  유스케이스 아이디   : BIZ005
 *  이벤트 명           : 파일업로드
 *  이벤트 아이디       : F01 
 *  설계자              : 홍길동
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : JSP
 *  출력 채널 유형      : CHAINED
 *  출력 URL            : BIZ005.L01
 *
 *  비고                : 
 *  </pre>
 */
public class BIZ005F01AC extends NormalTxService {


    public BIZ005F01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {
    	
    	debug(input);
    	
    	ATO fileAto = input.getATO("attach"); 
    	int cnt = 0;
    	
    	if (fileAto != null) {
	    	for (int i=0; i<fileAto.size(); i++) {
	    		FileData fileData = (FileData) fileAto.getObject(i);
	    			if ((fileData != null) && (fileData.getName() != null) && (fileData.getName().length() > 0)) {
	                String fileName = FileUtil.parseName(fileData.getName());
	                debug("전송 파일명:"+fileName);
	                String path = SystemInfo.getHomePath()+"upload/";
	                debug("목표 저장패스:"+path);
	                debug("파일 저장결과:"+fileData.write(path + fileName, false));
	                cnt++;
	            }
	    	}
    	}

    	output.setMessage("S-002","업로드[총:"+cnt+"개]");
        
    }
}
