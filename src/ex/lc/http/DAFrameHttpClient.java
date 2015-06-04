package ex.lc.http;

import com.cni.fw.ff.dto.entity.ATO;
import com.cni.fw.ff.dto.entity.MTO;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.ff.log.SysLog;
import com.cni.fw.net.http.HttpConnecter;

public class DAFrameHttpClient extends SysLog {

	/**
	 * <pre>
	 * 로컬로 웹 이벤트를 호출 (배치 스케줄링에서 활용)
	 * </pre>
	 * @param args
	 * @throws FrameException 
	 * @since 2009. 11. 10
	 */
	public static void main(String[] args) throws FrameException {

		// URL에 붙일 전송 파라메타.
		MTO paramMto = new MTO();
		
		// 일반 key/value 형태는 아래와 같이 처리.
		paramMto.put("key1","value1");
		
		// 배열을 보내야할 경우에는 아래와 같이 처리한다. 
		// 예: key2 에 value2-1,value2-2,value2-3 3개를 배열로 보낼때
		ATO array = new ATO();
		array.add("value2-1");
		array.add("value2-2");
		array.add("value2-3");
		
		paramMto.putObject("key2", array);
		
		// POST방식으로 URL을 호출한 후 결과를 String으로 받는다.
		String resultText = HttpConnecter.getStringByParam("http://localhost/CASE000.H01.cmd", paramMto, "UTF-8");
		
		out(resultText);
	}

}
