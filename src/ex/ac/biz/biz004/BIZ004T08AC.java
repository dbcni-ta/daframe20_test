package ex.ac.biz.biz004;

import java.io.ByteArrayInputStream;
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
import com.cni.fw.ff.util.gen.RandomString;
import com.cni.fw.ff.util.maker.StringMaker;

/**
 *  <pre>
 *  [EX] 업무고급 LOB 등록 (BIZ004.T08)
 *
 *  개발자              : 홍길동
 *
 *  작성날짜            : 20060714
 *
 *  유스케이스 명       : [EX] 업무고급
 *  유스케이스 아이디   : BIZ004
 *  이벤트 명           : LOB 등록
 *  이벤트 아이디       : T08 
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
public class BIZ004T08AC extends NormalTxService {


	public BIZ004T08AC(Class clazz) throws FrameException {
		super(clazz);
	}

	protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {
		
		/**
		 * 
			CREATE TABLE EX_LOB
			(
			  LOB_KEY   VARCHAR2(10 BYTE),
			  LOB_CLOB  CLOB,
			  LOB_BLOB  BLOB
			)
		 * 
		 */

		Tactics tactics = TacticsFactory.getInstance(input.getTx());

		MTO insertMto = new MTO();

		insertMto.put("lob_key",RandomString.randomAlphabetic(10));
		insertMto.put("lob_clob", RandomString.randomAlphabetic(10));
		//insertMto.put("lob_blob", RandomString.randomAlphabetic(10));
		//insertMto.put("lob_blob", "1234aaaatttt");
		
		ByteArrayInputStream stream = new ByteArrayInputStream(RandomString.randomAlphabetic(4000).getBytes());
		BlobData blobData = new BlobData(stream, 4000);
		
		insertMto.putObject("lob_blob", blobData);
		
		output.put("입력ID", insertMto.get("lob_key"));

		output.put("처리결과", StringMaker.make(tactics.insert("EX.BIZ.LOB입력", insertMto)));

	}
}
