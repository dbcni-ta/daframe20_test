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

/*
 * BC 템플릿 기본 유형 (본 주석은 개발시 삭제해주세요)
 * 
 * 통상의 BC는 본 템플릿을 복사해서 사용합니다.
 * 본 템플릿의 기본 유형이라 함은 아래와 같습니다.
 * 
 * 1) MasterBusiness 를 상속받는다.
 * 2) TX 유형의 업무에서 사용할 수 있는 BC 임을 전제로 전역변수 및 생성자를 작성한다.
 *   	
 *      Tactics tactics = null;

	    public TemplateBC(MasterTx tx) throws FrameException {
		    super(TemplateBC.class);
		    this.tactics = TacticsFactory.getInstance(tx);
	    }
 * 3) 3가지 예외[FrameException, ServiceException, SQLException]를 Throw하는 형태로 재사용 메소드를 작성한다.
 * 4) 단순 CRUD 기능이 아닌 추가 로직이 포함된 형태의 재사용로직일 경우에만 사용한다.
 *    (단순 CRUD 라면 AC에서 그냥 해당 TSQL을 호출하는 것과 별 차이가 없기에)
 *    
 * 5) 원칙적으로 상기조건을 만족할 경우 BC의 재사용 메소드들은 입출력 파라메타 사용에 제약은 없지만,
 *    가급적 템플릿에 제시된 유형의 메소드 형태로 표준화해서 개발한다.
 */

/**
 *  <pre>
 *  BC 명칭 (BIZ001BC)
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
public class TemplateBC extends MasterBusiness {
	
	Tactics tactics = null;

	public TemplateBC(MasterTx tx) throws FrameException {
		super(TemplateBC.class);
		this.tactics = TacticsFactory.getInstance(tx);
	}
	
	public LTO execR01(MTO input) throws FrameException, ServiceException, SQLException {
		LTO result = null;
		
		result = tactics.selectList("대상TSQL파일", input);
		
		return result;
	}
	
	public int execC01(MTO input) throws FrameException, ServiceException, SQLException {
		int result;
		
		result = tactics.insert("대상TSQL파일", input);
		
		return result;
	}
	
	public int execU01(MTO input) throws FrameException, ServiceException, SQLException {
		int result;
		
		result = tactics.update("대상TSQL파일", input);
		
		return result;
	}
	
	public int execD01(MTO input) throws FrameException, ServiceException, SQLException {
		int result;
		
		result = tactics.delete("대상TSQL파일", input);
		
		return result;
	}
}
