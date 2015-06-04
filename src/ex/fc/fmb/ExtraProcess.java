package ex.fc.fmb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cni.fw.arch.fmb.WebFrame;
import com.cni.fw.ff.dto.impl.ImplCauseDTO;
import com.cni.fw.ff.dto.impl.ImplEffectDTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.util.DateUtil;
import com.cni.fw.ff.util.StrUtil;
import com.cni.fw.meta.cmd.base.CommandMeta;
import com.cni.fw.meta.util.CmdMetaUtil;
import com.cni.fw.web.session.so.CommonSession;

/**
 * FMB 추가 개발 예시
 * <pre>
 * 
 * </pre>
 * @version : 1.0
 * @author : WinterMute (Jeon Chan-Mo)
 * @since 2010. 06. 29
 */
public class ExtraProcess extends WebFrame implements ExtraProcessMBean {

	public ExtraProcess(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

    public void execute(ImplCauseDTO input, ImplEffectDTO output, HttpServletRequest request, HttpServletResponse response) throws FrameException {

        /**
         * 콘트롤 정보 파악하여
         * 본 FMB 실행 이전에 에러가 발생했을 경우 처리를 스킵하는 처리임.
         * 만약 에러여부와 상관없이 본 FMB를 실행하고자 한다면 아래의 로직을 제거한다.
         * 단 이경우에는 이전 FMB에서 확보할 수 있는 데이타(예를 들면 cmd정보, session 정보) 등이 존재하지 않을 수도 있음에 유의. 
         */
        if (output.isError()) {
            info("SKIP! [전단계 에러발생]");
            output.setNoWork(true); // 실처리를 하지 않은것으로 설정해야 통계량에서 제외되어 정상적인 정보를 얻을수 있음.
            return; 
        }

        /**
         * 예시 : 현재 CMD 에 대한 정보. (전체 Excel 내용은 아님에 유의 - 필수적인 것만 존재함)
         */

        CommandMeta cmdMeta = input.getCommand();
        String cmdStr = cmdMeta.getCommand();
        debug("현재진행중인CMD:"+cmdStr);  // 현재 진행중인 CMD는 최초에 요청한 CMD와 다를수도 있다 (CHAINED 등의 사유로 인해..)
        debug("UC-ID:"+StrUtil.leftGetOff(cmdStr, ".", false));
        debug("EV-ID:"+StrUtil.rightGetOff(cmdStr, ".", false));
        debug("입력채널유형(진행중):"+cmdMeta.getIpType());
        debug("출력채널유형(진행중):"+cmdMeta.getOpType());

        /**
         * 예시 : 세션을 사용한 메타 정보.
         */
        CommonSession cs = input.getCommonSession();
        debug("현재진행중인요청에대한최초CMD:"+cs.getFirstCommand()); // CHAINED 등으로 연쇄된 이벤트의 경우 최초CMD를 찾으려면 이와 같이 해야한다.
        debug("입력채널유형(최초):"+CmdMetaUtil.getCommand(cs.getFirstCommand()).getIpType()); 
        debug("출력채널유형(최종):"+CmdMetaUtil.findLastOpType(cmdStr)); // CHAINED 등으로 연쇄된 이벤트의 경우 최종 CMD의 출력채널유형을 찾으려면 이와 같이 해야한다.
        
        
        debug("세션ID:"+cs.getSessionId());
        debug("최초접속일시:"+DateUtil.dateToStr(cs.getFirstAccessTime(), "yyyy-MM-dd HH:mm:ss"));
        debug("최종사용일시:"+DateUtil.dateToStr(cs.getLastAccessTime(), "yyyy-MM-dd HH:mm:ss"));
        debug("현재사용자의기타세션정보:"+cs.getDataMap());
        //debug("Session:"+commonSession);

        /**
         * 예시 : frame.xml에서 선언된 param 값 조회할때에는 아래와 같이...
         */
        debug("FMB 파라메타:"+getParamMap().get("ParamKey"));     
        
        /**
         * 쿼리를 하고자 할 경우 (Main DB의 경우임) 
         * 
         * 조회만 한다면 commit, rollback 부문은 제외하고 코딩하시길..
         */
        /*
        MasterTx  mTx = new MasterTx(ConnectionManager.getInstance().getConnection());
        try {
        	Tactics tactics = TacticsFactory.getInstance(mTx);
        	
        	tactics.insert("X-Query", input.getMaster());
        	
        	mTx.commit();
        } catch (SQLException e) {
        	try {
				mTx.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        } finally {
            try {
                if (mTx != null) {
                    mTx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
    }
    
    /**
     * 시스템이 기동할때 본 FMB에서 처리해야할 초기화가 있다면 기술한다.
     * <pre>
     * 
     * </pre>
     * @throws FrameException
     * @see com.cni.fw.arch.ArchManager#init()
     * @since 2008. 12. 18
     */
    public void init() throws FrameException {
    	// 필요시 사용
    }

    /**
     * 시스템이 종료될때 본 FMB에서 제거해야할 종료처리가 있다면 기술한다.
     * <pre>
     * 
     * </pre>
     * @see com.cni.fw.arch.ArchManager#disuse()
     * @since 2008. 12. 18
     */
    public void disuse() {
    	// 필요시 사용
    }

}
