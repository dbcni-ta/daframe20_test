package edu.case400;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.arch.tx.MasterTx;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.db.core.connect.ConnectionManager;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;

/**
 *  <pre>
 *  API 레벨의 DB Connection 확보 DAFrame 내에 설정된 DB 커낵션 확보 (CASE400.S01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : API 레벨의 DB Connection 확보
 *  유스케이스 아이디   : CASE400
 *  이벤트 명           : DAFrame 내에 설정된 DB 커낵션 확보
 *  이벤트 아이디       : S01 
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
public class CASE400S01AC extends NormalTxService {


    public CASE400S01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	/**
    	 * 본 예시는 메타가 아닌 API로 커낵션을 가져오는 예시입니다만,
    	 * 트랜잭션이 프레임워크에 의해 관리되지 않으므로 특별히 필요할 경우에만 사용하십시요.
    	 * 
    	 * 아래의 TRY-CATCH 구조에 의해 반드시 커낵션이 반환될 수 있도록 처리해주어야합니다.
    	 * 단순히 조회만 할 경우에는 아래의 commit 과 rollback 부분의 코딩은 하지 않아도 됩니다. 
    	 */
    	
    	// 메인 데이타베이스로 설정된 커낵션을 얻을 경우
    	//MasterTx  mTx = new MasterTx(ConnectionManager.getInstance().getConnection());
    	
    	// 별도 선언된 데이타베이스 커낵션을 얻을 경우에는 framework.conf에서 선언한 alias 명을 파라메타로 주면됩니다.
    	MasterTx  mTx = new MasterTx(ConnectionManager.getInstance().getConnection("db.xa.site4"));
    	
        try {
        	Tactics tactics = TacticsFactory.getInstance(mTx);
        	
        	output.putLTO("result", tactics.selectList("EDU.조회_STADIUM", null));
        	
        	// CUD 형 쿼리 일 경우 반드시 Commit을 해줍니다.
        	mTx.commit();
        } catch (FrameException e) {
        	try {
				mTx.rollback();
			} catch (SQLException e1) {
				throw new FrameException(e);
			}
        } finally {
            try {
                if (mTx != null) {
                    mTx.close();
                }
            } catch (SQLException e) {
            	throw new FrameException(e);
            }
        }
        
    }
}
