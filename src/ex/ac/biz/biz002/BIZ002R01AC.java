package ex.ac.biz.biz002;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  [EX] Tactics 조회 (BIZ002.R01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060712
 *
 *  유스케이스 명       : [EX] Tactics
 *  유스케이스 아이디   : BIZ002
 *  이벤트 명           : 조회
 *  이벤트 아이디       : R01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB-CM-001
 *  출력 채널 유형      : XML-CM-001
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class BIZ002R01AC extends NormalTxService {


    public BIZ002R01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

        /**
         * 본 예제는 아래와 같은 테이블을 작성한후 테스트할 수 있습니다.
         * 
  
			 create table EX_INFO
			  (ID 		char(10) not null,
			   NAME		VARCHAR2(20),
			   AGE 		NUMBER(3),
			   REG_DT	DATE,
			   MEMO		VARCHAR2(100),
			   primary key (ID));
         */
     	
     	/**
     	 * DB 조회시 Tactics ORM은 두가지 구현 패턴을 제시하는데, 그하나는 각 TABLE 별로 기본적인 CRUD를 제공하는
     	 * TacticsPrime 클래스를 이용하는 방법이 있으며, 나머지는 일반적인 모든 쿼리를 수행할 수 있는
     	 * Tactics 클래스를 이용하는 방법입니다.
     	 * 
     	 * 1. Tactics 를 사용한 조회방법
     	 * 
     	 * prime 의 경우와 비슷하나, 테이블명을 작성하는 파라메타에, TSQL 파일 위치를 기재한다.
     	 * 자세한 기재방법은 개발 가이드라인을 참조한다.
     	 */
    	
    	Tactics tactics = TacticsFactory.getInstance(input.getTx());
    	
     	MTO clauseMto = new MTO();
     	clauseMto.put("name", "전찬모");
     	clauseMto.put("age", "20");
     	
     	output.putMTO("TACTICS-SINGLE", tactics.select("EX.BIZ.축약정보조회", clauseMto));
     	
     	output.putLTO("TACTICS-MULTI",  tactics.selectList("EX.BIZ.전체정보조회", clauseMto));
     	
     	//tactics.selectList("EX.BIZ.전체정보조회", clauseMto);
     	
     	//output.putLTO("TACTICS-MULTI",  tactics.selectList("EX.BIZ.전체정보조회-UTF8", clauseMto));
     	
     	
    }
}
