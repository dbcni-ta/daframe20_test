package ex.ac.biz.biz001;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  [EX] 업무일반 DTO 핸들링 기본 (BIZ001.N01)
 *
 *  개발자              : 홍길동
 *
 *  작성날짜            : 20060711
 *
 *  유스케이스 명       : [EX] 업무일반
 *  유스케이스 아이디   : BIZ001
 *  이벤트 명           : DTO 핸들링 기본
 *  이벤트 아이디       : N01 
 *  설계자              : 홍길동
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB-CM-001
 *  출력 채널 유형      : XML-CM-001
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class BIZ001N01AC extends NormalTxService {


	public BIZ001N01AC(Class clazz) throws FrameException {
		super(clazz);
	}

	protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

		info("처리정보 로그는 이와같이 출력합니다.");

		// 입력데이타 전체 확인을 위한 로깅
		debug(input);

		/**
		 * 입력데이타[CauseDTO, input] 사용해 출력데이타[EffectDTO, output]를 작성해봅니다. 
		 * 
		 * 각 공통데이타는 공통DTO(CommonDTO)를 상속받아 4개의 데이타 저장영역을 가집니다.
		 * 
		 * 1. SYSTEM : setSystem(), getSystem()
		 * 2. MASTER MTO : get(), set()
		 * 3. MTO : getMTO(), putMTO()
		 * 4. LTO : getLTO()l, putLTO()
		 * 
		 * 이때 MTO는 (Key, Value) 들의 쌍인 Map 유형의 DTO를 의미하며
		 * LTO는 MTO들의 집합을 유지할 수 있는 List 유형의 DTO를 의미한다.
		 * 
		 * 기본적으로 MTO는 STRING 객체를 디폴트로 관리하지만 경우에 따라 타 객체도 지원한다.
		 * 마찬가지로 LTO는 MTO 객체를 디폴트로 관리하지만 경우에 따라 타 객체도 지원한다.
		 */ 

		MTO systemMto = new MTO();
		systemMto.put("COMMAND",input.getCommand().getCommand());
		output.setSystem(systemMto);
		/**
		 * 입력데이타의 Master MTO 영역에서 특정한 값을 가져와봅니다.
		 */
		String key = input.get("KEY");
		
		/**
		 * 입력데이타의 Master MTO 전체를 얻어와봅니다.
		 */
		MTO inputMto = input.getMaster();
		
		/**
		 *  입력데이타의 특정 MTO를 가져옵니다. (입력채널에서 지원할 경우)
		 */
		MTO empMto = input.getMTO("EMP");
		
		/**
		 * 입력데이타의 특정 LTO를 가져옵니다. (입력채널에서 지원할 경우)
		 */
		LTO empLto = input.getLTO("EMPLIST");
		

		/**
		 * 출력데이타의 Model 영역에 값을 세팅해봅니다.
		 */
		output.setModel("MODEL-01", "모델 영역");
		output.setModel("MODEL-02", systemMto.getNexter());

		/**
		 * 출력데이타의 Master MTO 영역에 입력값 전체를 세팅해봅니다.
		 */
		output.setMaster(new MTO(input.getMaster()));

		
		/**
		 * 출력데이타의 Master MTO 영역에 값을 추가해봅니다.
		 */
		output.put("MASTER-01", "마스터 영역");
		

		/**
		 * 출력데이타의 MTO 영역에 MTO 2개를 등록해봅니다.
		 */
		MTO mto1 = new MTO();
		mto1.put("FIELD-1", "01");
		mto1.put("FIELD-2", "02");
		mto1.put("FIELD-3", "03");
		output.putMTO("MTO-1", mto1);

		MTO mto2 = new MTO();
		mto2.put("FIELD-1", "04");
		mto2.put("FIELD-2", "05");
		mto2.put("FIELD-3", "06");
		output.putMTO("MTO-2", mto2);

		/**
		 * 출력데이타의 LTO 영역에 MTO 다수를 포함하고 있는 LTO 1개를 등록해봅니다.
		 * 
		 * 테이블과 비교하면, LTO는 TABLE이며, MTO는 ROW에 해당합니다.
		 */
		LTO lto = new LTO();

		MTO row = null;
		for (int i=01;i<=10;i++) {
			row = new MTO();
			for (int j=0;j<10;j++) {
				row.put("COLUMN"+j, ""+j);
			}
			lto.add(row);
		}
		output.putLTO("LTO_01", lto);

		/**
		 * 출력데이타에 코드와 메시지를 아래와 같이 세팅해봅니다. 세팅을 하지 않을 경우 디폴트로 세팅됩니다.
		 */
		output.setCode("A-001");
		output.setMessage("결과메시지입니다");

		//필요시 아래와 같이 세팅된 output을 확인할 수 있습니다.
		debug("DEBUG");
		info("INFO");
		warn("WARN");
	}
}
