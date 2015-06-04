package edu.case001;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.BasicService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.ATO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  CASE001 출력채널 XML (CASE001.S01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE001
 *  유스케이스 아이디   : CASE001
 *  이벤트 명           : 출력채널 XML
 *  이벤트 아이디       : S01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : NA
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : XML
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class CASE001S01AC extends BasicService {


    public CASE001S01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {
    	
    	// DAFrame 에서 사용하는 핵심 오브젝트 데이타 모델
    	output.getMtoSet(); // MTO Set  MTO : [(key1, value1), (key2, value2), ...]
    	output.getAtoSet(); // ATO Set	ATO : [value1, value2, , ...]
    	output.getLtoSet(); // LTO Set	LTO : [MTO1, MTO2, ...]
    	output.getMaster(); // MASTER (MTO)
    	output.getSystem(); // SYSTEM (MTO)
    	output.getSystem(); // SYSTEM (MTO)
    	output.getSystem(); // SYSTEM (MTO)
    	output.getSystem(); // SYSTEM (MTO)
    	output.getSystem(); // SYSTEM (MTO)
    	output.getSystem(); // SYSTEM (MTO)

    	/**
    	 * MASTER 예제 // 직원 정보
    	 */ 
        output.put("name","홍길동");
        output.put("age","30");
        output.put("city", "서울");
        output.put("position", "2");
        
        /**
         * MTO 예제 : 소속 회사
         */ 
        MTO mto = new MTO();
        mto.put("corpName", "동부CNI");
        mto.put("corpCity", "서울");
        
        output.putMTO("corpInfo", mto);
        
        /**
         * ATO 예제 : 직위 체계
         */ 
        ATO ato = new ATO();
        ato.add("사원");
        ato.add("대리");
        ato.add("과장");
        ato.add("차장");
        ato.add("부장");
        
        output.putATO("position", ato);
        
        /**
         *  LTO 예제 : 팀원 정보
         */
        MTO team1 = new MTO();
        team1.put("name","김철수");
        team1.put("position", "1");
        
        MTO team2 = new MTO();
        team2.put("name","박영희");
        team2.put("position", "2");
        
        LTO lto = new LTO();
        lto.add(team1);
        lto.add(team2);
        
        output.putLTO("teamList", lto);
        
    }
}
