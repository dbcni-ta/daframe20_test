package edu.case003;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.maker.IntMaker;

import java.sql.SQLException;

/**
 *  <pre>
 *  CASE003 페이징 조회 (캡션활용*) (CASE003.R04)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE003
 *  유스케이스 아이디   : CASE003
 *  이벤트 명           : 페이징 조회 (캡션활용*)
 *  이벤트 아이디       : R04 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : JSP
 *  출력 URL            : /edu/case003r02.jsp
 *
 *  비고                : 
 *  </pre>
 */
public class CASE003R04AC extends NormalTxService {


    public CASE003R04AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	// 택틱스 객체
    	Tactics tactics = TacticsFactory.getInstance(input.getTx());
        
        int pgNum = IntMaker.make(input.get("pgNum"),1);
        int pgSize = IntMaker.make(input.get("pgSize"),5);
        
        output.putLTO("result_page", tactics.selectPageWithLTO("EDU.조회_STADIUM", input.getMaster(), pgNum, pgSize));
        output.put("title", "경기장정보 (페이징+캡션정보활용)");
        
    }
}
