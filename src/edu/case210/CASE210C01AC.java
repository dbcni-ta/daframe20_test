package edu.case210;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.crypto.sk.SymmetricKeyUtil;
import com.cni.fw.ff.common.SystemInfo;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  CASE210 대칭키 암호화 (AES) 키생성 (CASE210.C01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE210
 *  유스케이스 아이디   : CASE210
 *  이벤트 명           : 대칭키 암호화 (AES) 키생성
 *  이벤트 아이디       : C01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : XML
 *  출력 URL            : 
 *
 *  비고                : 예제
 *  </pre>
 */
public class CASE210C01AC extends NormalTxService {


    public CASE210C01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	String keyPath = SystemInfo.getHomePath()+"WEB-INF/conf/keys/AES.key";
    	
    	// 대칭키를 생성해서 저장한다. (AES, DES, TripleDES 등 사용가능)
		SymmetricKeyUtil.generateKeyAES(keyPath);
		
		output.setMessage(keyPath+" 위치에 대칭키를 생성하였습니다.");
        
    }
}
