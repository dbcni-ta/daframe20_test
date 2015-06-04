package ex.bc;

import java.sql.SQLException;

import com.cni.fw.arch.smb.bc.MasterBusiness;
import com.cni.fw.arch.tx.MasterTx;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.CalUtil;
import com.cni.fw.ff.util.gen.RandomString;

/**
 *  <pre>
 *  BC 명칭 (BIZ004BC)
 *
 *  개발자              : 홍길동
 *
 *  작성날짜            : 20060711
 *
 *  비지니스케이스 명   : 재사용 업무 일반
 *  비지니스케이스 명   : BIZ001
 *  설계자              : 홍길동
 * 
 *  업무 유형           : Tx
 *
 *  비고                : 
 *  </pre>
 */
public class BIZ004BC extends MasterBusiness {
	
	Tactics tactics = null;

	public BIZ004BC(MasterTx tx) throws FrameException {
		super(BIZ004BC.class);
		this.tactics = TacticsFactory.getInstance(tx);
	}
	
	/**
	 * 조회형 재사용 BC 메소드 개발 예시
	 * <pre>
	 * 
	 * </pre>
	 * @param input
	 * @return
	 * @throws FrameException
	 * @throws ServiceException
	 * @throws SQLException
	 * @since 2008. 03. 26
	 */
	public LTO execR01(MTO input) throws FrameException, ServiceException, SQLException {
		LTO result = new LTO();
		
		/**
		 * BC 개발 대상 포인트 1.
		 * 
		 * 아래와 같이 Tactics 처리전에 입력값에 대한 전처리프로세스(조건, 분기, 반복, 첨삭등)가 반드시 존재하고
		 * 이것을 여러 AC에서 반복적으로 사용할 경우 BC로 개발하는것이 바람직함.
		 */
		if (!input.containsKey("name")) {
			input.put("name", "전찬모");
		}
     	
     	LTO temp = tactics.selectList("EX.BIZ.전체정보조회", input);

     	/**
     	 * BC 개발 대상 포인트2.
     	 * 
     	 * 아래와 같이 Tactics 처리후에 출력값에 대한 후처리프로세스(조건, 분기, 반복, 첨삭등)가 반드시 존재하고
     	 * 이것을 여러 AC에서 반복적으로 사용할 경우 BC로 개발하는것이 바람직함.
     	 */
     	MTO row = null;
     	for (int i=0;i<temp.size();i++) {
     		row = temp.get(i);
     		if (CalUtil.compare(row.get("AGE"),">=","30")) {
     			row.put("Rank", "중년");
     		} else {
     			row.put("Rank", "청년");
     		}
     		result.add(row);
     	}
		
		return result;
	}
	
	/**
	 * 입력형 재사용 BC 메소드 개발 예시
	 * <pre>
	 * 
	 * </pre>
	 * @param input
	 * @return
	 * @throws FrameException
	 * @throws ServiceException
	 * @throws SQLException
	 * @since 2008. 03. 26
	 */
	public int execC01(MTO input) throws FrameException, ServiceException, SQLException {
		/**
		 * *주의사항*
		 * 
		 * 입력값인 input 의 레퍼런스에 영향을 주지 않도록
		 * new MTO(input) 형태의 생성자를 통해 value를 복사하였음.
		 * 
		 * DTO 레퍼런스의 기존값을 유지할 필요가 있을 경우 흔히 사용함.
		 * 예를 들어 본 execC01()을 호출할때 AC에서 execC01(input.getMaster()) 형태로 호출할 경우
		 * 최초 입력데이타인 CauseDTO의 객체 input의 레퍼런스가 그대로 넘어온다. 이때 아래와같이
		 * 값을 복사하지 않고 input.put() 처리를 수행하면 원데이타에도 반영되여,
		 * 원치않는 결과가 발생할 수 있음.
		 * 
		 */
		MTO value = new MTO(input); // input의 레퍼런스의 값을 복사하여 새로운 MTO 생성.
		
		if (!value.containsKey("id")) {
			value.put("id",RandomString.randomAlphabetic(10));
			value.put("name", "전찬모");
			value.put("age", "27");
			value.put("memo", "복제데이타[BC].");
		}
		
		return tactics.insert("EX.BIZ.임의입력", value);
	}
	
	/**
	 * 수정형 재사용 BC 메소드 개발 예시
	 * <pre>
	 * 
	 * </pre>
	 * @param input
	 * @return
	 * @throws FrameException
	 * @throws ServiceException
	 * @throws SQLException
	 * @since 2008. 03. 26
	 */
	public int execU01(MTO input) throws FrameException, ServiceException, SQLException {
		
		MTO value = new MTO(input);
		
		/**
		 * 아래의 유효성 체크로직은 샘플로 한번 넣어본것이며, 실제 이러한 유형의 유효성 체크는
		 * UI 입력단에서 처리하는것이 바람직함.
		 */
		
		if (!value.containsKey("id")) {
			throw new ServiceException("E-001","수정하는데 필요한 키값이 없습니다.");
		}
		
		if (!value.containsKey("age")) {
			throw new ServiceException("E-001","수정할 컬럼이 입력되지 않았습니다.");
		}
		
		return tactics.insert("EX.BIZ.임의수정", value);
	}
	
	/**
	 * 삭제형 재사용 BC 메소드 개발 예시
	 * <pre>
	 * 
	 * </pre>
	 * @param input
	 * @return
	 * @throws FrameException
	 * @throws ServiceException
	 * @throws SQLException
	 * @since 2008. 03. 26
	 */
	public int execD01(MTO input) throws FrameException, ServiceException, SQLException {
		int result;
		
		MTO value = new MTO(input);
        
		value.put("memo", "복제데이타.");
		
		result = tactics.delete("EX.BIZ.임의삭제", value);
		
		/**
		 * 아래의 예시도 그저 후처리 로직의 예일뿐 실전에서의 상황과는 무관합니다.
		 */
		if (result == 0) {
			warn("모두 삭제할 경우 하나를 임의 생성합니다.");
			this.execC01(null);
		}
        
		return result;
	}
}
