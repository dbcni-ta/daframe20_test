package ex.ria.wise.wise210;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.ATO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import java.sql.SQLException;

/**
 *  <pre>
 *  [EX] 콤보-Server 콤보로딩 (WISE210.L02)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060712
 *
 *  유스케이스 명       : [EX] 콤보-Server
 *  유스케이스 아이디   : WISE210
 *  이벤트 명           : 콤보로딩
 *  이벤트 아이디       : L02 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WISE
 *  출력 채널 유형      : WISE
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class WISE210L02AC extends NormalTxService {


    public WISE210L02AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	Tactics tactics = TacticsFactory.getInstance(input.getTx());
    	
        // 그리드 ID (서버/클라이언트 식별id)
        String gridName = "WiseGrid";
        
        // 아래는 콤보테이블 설정용 하드코딩임.
        // 만약 쿼리로 이것을 한다면
        // SELECT [CODE_VALUE:화면에보이는것] as VALUE, [CODE_KEY:실제데이타] as HIDDEN_VALUE from XXX
        // 와 같은 형태로 LTO를 조회한 후 해당 LTO를 그리드의 LTO에 putInfoObject()함수를 사용하여 WISEGRID_COMBO_[콤보형의컬럼명] 을 key값으로 등록하면됨.
        // 이후 WISE 출력처리에서 t_combo로 설정되어 있고 위의 WISEGRID_COMBO_[콤보형의컬럼명] 에 대한 LTO가 적절히 확보되면
        // 서버에서 콤보정보를 만들어서 내려보내는것이 가능함.
        
        // LTO에 컬럼단위로 ATO를 추가해나가는 API를 사용하였음. (이건 하드코딩용 예시임)
        ATO cb_value = new ATO(new String[] {"호주","스페인", "독일", "한국", "미국"});
        ATO cb_hiddenValue = new ATO(new String[] {"AUS","ESP", "GRE", "KOR", "USA"});
        
        LTO comboInfo = new LTO();
        comboInfo.appendColumn("VALUE", cb_value);
        comboInfo.appendColumn("HIDDEN_VALUE", cb_hiddenValue);
        
        LTO result = new LTO();
        
        // 출력 그리드정보에 필요한 콤보정보를 해당 컬럼별로 LTO로 아래와 같이 등록한다. [WISEGRID_COMBO_]+컬럼명
        result.putInfoObject("WISEGRID_COMBO_NATION_CODE", comboInfo);
        
        // 출력할 그리드 설정
        output.putLTO(gridName, result);
        
    }
}
