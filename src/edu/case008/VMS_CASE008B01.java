package edu.case008;

import com.cni.fw.ff.log.SysLog;
import com.cni.fw.vms.app.VmsServiceGate;
import com.cni.fw.vms.app.VmsServiceStarter;
import com.cni.fw.vms.data.VmsData;

public class VMS_CASE008B01 extends SysLog {

	/**
	 * <pre>
	 * 로컬로 DAFrame의 이벤트를 실행할 수 있다.
	 * 
	 * 이클립스에서 Java Application 으로 실행하되, lib 폴더의 모든 jar파일을 등록해서 실행토록 한다.
	 * </pre>
	 * @param args
	 * @since 2009. 11. 10
	 */
	public static void main(String[] args) {
		
		// args 를 사용하여 DAFrame Home을 설정한다. 
		out(args.length);
		if (args.length == 1) {
			VmsServiceStarter.setHomePath(args[0]);
	
			// VmsFrame 서비스 초기화가 성공하면...
			if (VmsServiceStarter.startup() != -1) {
				VmsServiceGate gate = new VmsServiceGate();
				VmsData request = new VmsData();
				request.setCommand("CASE008.B01");
				
				out(gate.doProcess(request));
				
				VmsServiceStarter.shutdown();
			}
		} else {
			out("Argument 1에 DAFrame Home Path를 설정하십시요.");
		}
	}

}
