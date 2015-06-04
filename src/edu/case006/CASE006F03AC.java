package edu.case006;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.common.SystemInfo;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.FileUtil;
import com.cni.fw.ff.util.StrUtil;
import com.cni.fw.id.SystemC;
import com.cni.fw.web.data.file.SendFileData;

/**
 *  <pre>
 *  CASE006 다운로드 (CASE006.F03)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE006
 *  유스케이스 아이디   : CASE006
 *  이벤트 명           : 다운로드
 *  이벤트 아이디       : F03 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : FILE
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class CASE006F03AC extends NormalTxService {


    public CASE006F03AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	String file = input.get("file");
    	
    	if (StrUtil.isEmpty(file)) {
    		throw new ServiceException("file 명이 누락되었습니다.");
    	}
        
        String path = SystemInfo.getHomePath() +"upload/"+file;
        
        if (FileUtil.isExist(path)) {
        	SendFileData sendFileData = null;
            sendFileData = new SendFileData(file,path);
            output.getSystem().putObject(SystemC.DOWNLOAD_FILE, sendFileData);
        } else {
        	throw new ServiceException("["+file+"] 파일이 존재하지 않습니다");
        }
        
    }
}
