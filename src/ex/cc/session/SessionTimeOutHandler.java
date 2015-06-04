package ex.cc.session;

import java.sql.SQLException;

import com.cni.fw.arch.smb.cc.SessionControl;
import com.cni.fw.arch.tx.MasterTx;
import com.cni.fw.db.cdq.tactics.query.Tactics;
import com.cni.fw.db.cdq.tactics.query.TacticsFactory;
import com.cni.fw.db.core.connect.ConnectionManager;
import com.cni.fw.ff.exception.FrameException;
import com.cni.fw.web.session.so.CommonSession;

public class SessionTimeOutHandler extends SessionControl {

	public SessionTimeOutHandler(Class clazz) {
		super(clazz);
	}

	public void process(CommonSession cs) throws FrameException {
		
		debug("세션타임아웃된 CS:"+cs);
		
		/**
         * 쿼리를 하고자 할 경우 (Main DB의 경우임) 
         * 
         * 조회만 한다면 commit, rollback 부문은 제외하고 코딩하시길..
         */
		/*
        MasterTx  mTx = new MasterTx(ConnectionManager.getInstance().getConnection());
        try {
        	Tactics tactics = TacticsFactory.getInstance(mTx);
        	
        	tactics.insert("X-Query", cs.getDataMap());

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

}
