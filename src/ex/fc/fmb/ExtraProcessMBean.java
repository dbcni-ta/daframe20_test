/*
 * 시스템명: TODO <<각 업무 시스템 이름>>
 * 최초작성일: 2008. 10. 30
 * $Id: ExtraProcessMBean.java,v 1.1 2012/10/23 03:51:05 cvsuser Exp $
 */
package ex.fc.fmb;

import com.cni.fw.ff.exception.FrameException;

/**
 * TODO <<클래스에 대한 설명 입력>>
 *
 * @author  wangza
 * @version TODO <<클래스 버전>>
 */
public interface ExtraProcessMBean {
    
    public Object action(Object cmd) throws FrameException;
    
}
