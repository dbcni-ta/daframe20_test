package ex.ria.wise.wise220;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.ATO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import java.sql.SQLException;

/**
 *  <pre>
 *  [EX] 트리 조회 (WISE220.R01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 트리
 *  유스케이스 아이디   : WISE220
 *  이벤트 명           : 조회
 *  이벤트 아이디       : R01 
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
public class WISE220R01AC extends NormalTxService {


    public WISE220R01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

        // 그리드 ID (서버/클라이언트 식별id)
        String gridName = "WiseGrid";
        
        // 입력파라메타
        MTO param = input.getMTO(gridName);
        
        // 트리 데이타 하드 코딩 ^^
        // 동부CNI(C01)
        //         - AO사업부(S01)
        //                   - 제조운영팀(T01)
        //                   - 보험운영팀(T02)
        //         - SD사업부(S02)
        //                   - 솔루션개발팀(T03)
        //         - 연구소(S03)
        //                   - TA팀(T04)
        
        ATO org = new ATO(new String[]      {"동부CNI","AO사업부", "제조운영팀", "보험운영팀", "SD사업부", "솔루션개발팀", "연구소", "TA팀"});
        ATO org_sum = new ATO(new String[]  {"1000"   ,"500",      "250",        "250",        "400",      "400",          "100",    "100"});
        ATO org_tree = new ATO(new String[] {"*,C01",	"C01,S01", 	"S01,T01", 	  "S01,T02", 	"C01,S02", 	"S02,T03",		"C01,S03","S03,T04"});
        // org_level 의 경우 트리의 레벨에 따른 이미지 표현을 위한것임 (본 예시에서는 0,1,2 의 3단계임
        ATO org_level = new ATO(new String[] {"0",	"1", "2", "2", 	"1", "2", "1","2"});
        
        
    	LTO result = new LTO();
    	result.appendColumn("ORG", org);
    	result.appendColumn("ORG_SUM", org_sum);
    	result.appendColumn("WISEGRID_TREE_ORG", org_tree); // "WISEGRID_TREE_" + 트리기준 컬럼명("ORG")
    	result.appendColumn("WISEGRID_LEVEL_ORG", org_level); // "WISEGRID_LEVEL_" + 트리기준 컬럼명("ORG")
    	
        
        // 출력할 파라메타 설정
        output.putMTO(gridName, param);
        // 출력할 그리드 설정
        output.putLTO(gridName, result);
        
    }
}
