package ex.ac.biz.biz001;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.dto.entity.LTO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.CalUtil;

/**
 *  <pre>
 *  [EX] 업무일반 계산 핸들링 기본 (BIZ001.N02)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060712
 *
 *  유스케이스 명       : [EX] 업무일반
 *  유스케이스 아이디   : BIZ001
 *  이벤트 명           : 계산 핸들링 기본
 *  이벤트 아이디       : N02 
 *  설계자              : 전찬모
 * 
 *  업무 유형           : Tx
 *  입력 채널 유형      : WEB-CM-001
 *  출력 채널 유형      : XML-CM-001
 *  출력 URL            : 
 *
 *  비고                : 
 *  </pre>
 */
public class BIZ001N02AC extends NormalTxService {


	public BIZ001N02AC(Class clazz) throws FrameException {
		super(clazz);
	}

	protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

		String a = "100";
		String b = "30.0001";
		
		// 기본 연산 (나눗셈의 경우 소수점 4자리에서 반올림, 나머지의 경우 가장 디테일한 스케일로 그대로 처리)
		output.put("a.덧셈", CalUtil.math(a, '+', b));
		output.put("b.뺄셈", CalUtil.math(a, '-', b));
		output.put("c.곱셈", CalUtil.math(a, '*', b));
		output.put("d.나눗셈", CalUtil.math(a, '/', b));
		
		// 스케일을 지정한 경우 해당 스케일 아래자리수에서 반올림.
		output.put("e.덧셈2", CalUtil.math(a, '+', b, 3));
		output.put("f.뺄셈2", CalUtil.math(a, '-', b, 3));
		output.put("g.곱셈2", CalUtil.math(a, '*', b, 3));
		output.put("h.나눗셈2", CalUtil.math(a, '/', b, 3));
		
		// 데이타에 콤마가 들어간 상태일 경우에도 정상계산. (여기선 스케일을 0을 준 예시임)
		a = "1,000,000";
		b = "1,000";
		output.put("i.덧셈3", CalUtil.math(a, '+', b, 0));
		output.put("j.뺄셈3", CalUtil.math(a, '-', b, 0));
		output.put("k.곱셈3", CalUtil.math(a, '*', b, 0));
		output.put("l.나눗셈3", CalUtil.math(a, '/', b, 0));
		
		// 비교연산자.
		if (CalUtil.compare(a, ">", b)) {
			output.put("m.비교", "크다");
		} else {
			output.put("m.비교", "같거나적다");
		}
		
		// 합계, 평균, 분산, 표준편차
		LTO lto = new LTO();
		MTO mto = null;
		for (int i=1;i<100;i++) {
			mto = new MTO();
			mto.put("data",i);
			lto.add(mto);
		}
		
		output.put("n.합계", CalUtil.sum(lto, "data"));
		output.put("o.평균", CalUtil.avg(lto, "data"));
		output.put("p.분산", CalUtil.var(lto, "data"));
		output.put("q.표준편차", CalUtil.std(lto, "data"));
		
	}
}
