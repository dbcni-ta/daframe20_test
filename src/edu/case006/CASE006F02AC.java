package edu.case006;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.common.SystemInfo;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.FileUtil;
import com.cni.fw.web.data.file.FileData;

/**
 *  <pre>
 *  CASE006 업로드 실행 (CASE006.F02)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE006
 *  유스케이스 아이디   : CASE006
 *  이벤트 명           : 업로드 실행
 *  이벤트 아이디       : F02 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : FILE
 *  출력 채널 유형      : XML
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class CASE006F02AC extends NormalTxService {


	public CASE006F02AC(Class clazz) throws FrameException {
		super(clazz);
	}

	protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {


		FileData fileData = (FileData)input.getObject("file");

		if ((fileData != null) && (fileData.getName() != null) && (fileData.getName().length() > 0)) {

			String fileName = FileUtil.parseName(fileData.getName());
			debug("전송 파일명:"+fileName);
			String path = SystemInfo.getHomePath()+"upload/";
			debug("목표 저장패스:"+path);
			debug("파일 저장결과:"+fileData.write(path + fileName, false));

			output.setMessage("["+fileName+"] 파일 업로드에 성공하였습니다.");
		} else {
			output.setMessage("파일 업로드 처리가 실패했습니다.");
			
		}

	}
}
