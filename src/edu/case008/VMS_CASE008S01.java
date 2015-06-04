package edu.case008;

import com.cni.fw.ff.log.SysLog;
import com.cni.fw.vms.app.VmsServiceGate;
import com.cni.fw.vms.app.VmsServiceStarter;
import com.cni.fw.vms.data.VmsData;

public class VMS_CASE008S01 extends SysLog {

	/**
	 * <pre>
	 * 로컬로 DAFrame의 이벤트를 실행할 수 있다.
	 * 
	 * </pre>
	 * @param args
	 * @since 2010. 12. 10
	 */
	public static void main(String[] args) {
		
		//out(System.getProperty("user.dir"));
		
		// 현재 실행 디렉토리를 사용하여 DAFrame Home 패스를 설정한다.
		VmsServiceStarter.setHomePath(System.getProperty("user.dir")+"/app.war");

		// VmsFrame 서비스 초기화가 성공하면...
		if (VmsServiceStarter.startup() != -1) {
			VmsServiceGate gate = new VmsServiceGate();
			VmsData request = new VmsData();
			request.setCommand("CASE008.S01");
			
			out(gate.doProcess(request));
			
			VmsServiceStarter.shutdown();
		}
	}

}
