package ex.ac.biz.biz003;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.query.TacticsPrime;
import com.cni.fw.db.cdq.tactics.query.TacticsPrimeFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  [EX] Prime 조회 (BIZ003.R01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060712
 *
 *  유스케이스 명       : [EX] Prime
 *  유스케이스 아이디   : BIZ003
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
public class BIZ003R01AC extends NormalTxService {


    public BIZ003R01AC(Class clazz) throws FrameException {
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
     	 * 1. TacticsPrime 을 사용한 조회방법. (AC만으로 코딩 완료)
     	 * 
     	 * 예제쿼리 : SELECT * FROM EX_INFO <WHERE>
     	 * 
     	 */
     	
     	TacticsPrime prime = TacticsPrimeFactory.getInstance(input.getTx());
     	
     	// prime.select() 에서 두번째 파라메타는 동적 조회조건이며, null 일경우 조회조건이 없다는 의미입니다.
     	// 동적 조회조건은 MTO의 형태로만 입력받으며, 포함된 키값이 설정한 테이블의 컬럼명과 일치할 경우
     	// 조회조건으로 사용함에 유의합니다. 또한 prime 은 모든 조건이 AND 이며 = 인 WHERE 오퍼레이션으로 처리합니다.
     	
     	output.putLTO("PRIME-MULTI", prime.selectList("EX_BIZ_INFO", null));
     	
     	MTO clauseMto = new MTO();
     	clauseMto.put("NAME", "전찬모");
     	output.putMTO("PRIME-SINGLE", prime.select("EX_BIZ_INFO", clauseMto));
    }
}
