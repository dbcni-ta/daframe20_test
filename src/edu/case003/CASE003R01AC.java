package edu.case003;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.maker.IntMaker;

/**
 *  <pre>
 *  CASE003 페이징 조회 (기본) (CASE003.R01)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20091110
 *
 *  유스케이스 명       : CASE003
 *  유스케이스 아이디   : CASE003
 *  이벤트 명           : 페이징 조회 (기본)
 *  이벤트 아이디       : R01 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB
 *  출력 채널 유형      : JSP
 *  출력 URL            : /edu/case003r01.jsp
 *
 *  비고                : 
 *  </pre>
 */
public class CASE003R01AC extends NormalTxService {


    public CASE003R01AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	// 택틱스 객체
    	Tactics tactics = TacticsFactory.getInstance(input.getTx());
        
        int pgNum = IntMaker.make(input.get("pgNum"),1);
        int pgSize = IntMaker.make(input.get("pgSize"),10);
        
        output.putLTO("result_page", tactics.selectPageWithLTO("EDU.조회_NATION", input.getMaster(), pgNum, pgSize));
        output.put("title", "국가정보 (페이징+캡션정보활용)");
        
    }
}
