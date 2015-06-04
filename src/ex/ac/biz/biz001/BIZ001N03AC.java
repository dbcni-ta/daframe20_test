package ex.ac.biz.biz001;

import java.sql.SQLException;

import com.cni.fw.arch.smb.ac.NormalTxService;
import com.cni.fw.ff.dto.CauseDTO;
import com.cni.fw.ff.dto.EffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.exception.ServiceException;
import com.cni.fw.ff.util.DateUtil;

/**
 *  <pre>
 *  [EX] 업무일반 날짜 핸들링 기본 (BIZ001.N03)
 *
 *  개발자              : 전찬모
 *
 *  작성날짜            : 20060713
 *
 *  유스케이스 명       : [EX] 업무일반
 *  유스케이스 아이디   : BIZ001
 *  이벤트 명           : 날짜 핸들링 기본
 *  이벤트 아이디       : N03 
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
public class BIZ001N03AC extends NormalTxService {


    public BIZ001N03AC(Class clazz) throws FrameException {
        super(clazz);
    }

    protected void process(CauseDTO input, EffectDTO output) throws FrameException, ServiceException, SQLException {

       
    	String today = DateUtil.getToday();
    	
    	// 오늘 날짜
    	output.put("a.오늘날짜", today);
    	// 오늘 요일
    	output.put("b.오늘요일", DateUtil.getWeekDay(today));
    	
    	String tomarrow = DateUtil.getStringAddDate(today, 1);
    	
    	// 내일 날짜
    	output.put("c.내일날짜", tomarrow);
    	// 내일 요일
    	output.put("d.내일요일", DateUtil.getWeekDay(tomarrow));
    	
    	// 이번달
    	String month = DateUtil.getToday("yyyyMM");
    	
    	String lastDate = DateUtil.getLastDate(month); // today를 넣어도 상관없음.
    	
    	// 이번달 마지막 날짜
    	output.put("e.이달마지막날짜", month+lastDate);
    	
    	// 이번달 마지막 일까지 남은 일수
    	output.put("f.이달말까지남은일수",DateUtil.getDateDiff(today, month+lastDate));
    	
    	// 날자형 포맷팅.
    	output.put("g.포맷1", DateUtil.format(today));
    	output.put("h.포맷2", DateUtil.format(today,"yyyy/MM/dd"));
    	output.put("i.포맷3", DateUtil.format(today,"yyyy년MM월dd일"));
    	
    }
}
