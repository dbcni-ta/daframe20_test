package ex.ac.biz.biz004;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.db.cdq.tactics.obj.BlobData;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.maker.StringMaker;

/**
 *  <pre>
 *  [EX] 업무고급 LOB 조회 (BIZ004.T09)
 *
 *  개발자              : 홍길동
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 업무고급
 *  유스케이스 아이디   : BIZ004
 *  이벤트 명           : LOB 조회
 *  이벤트 아이디       : T09 
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
public class BIZ004T09AC extends NormalTxService {


    public BIZ004T09AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

    	Tactics tactics = TacticsFactory.getInstance(input.getTx());
    	
    	MTO mto = tactics.select("EX.BIZ.LOB조회",null);
    	BlobData blobData = (BlobData) mto.getObject("LOB_BLOB");

    	// 로그로 확인할 때... (스트링형~)
    	debug(StringMaker.make(blobData.getInputStream()));
    	
    	output.putMTO("단건처리", mto);
    	
    	output.putLTO("복건처리", tactics.selectList("EX.BIZ.LOB조회",null));
    	
    	//output.putLTO("페이징처리", tactics.selectPage("EX.BIZ.LOB조회",null,1,1).getResult());
    	
    	
        
    }
}
