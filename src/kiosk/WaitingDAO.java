package kiosk;

public class WaitingDAO extends DBConnection{
	private static WaitingDAO wdo=null;
	public WaitingDAO() {
		
	}
	public int addWaitingList(int orderNum) {
		int result=0;
		try {
			getConnection();
			String sql="insert into waiting(order_num,pick_up,writedate) values("+orderNum+",'픽업대기',sysdate)";
			pstmt=conn.prepareStatement(sql);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return result;	
	}
	public int updateWaitingList(int orderNum) {
		int result=0;
		try {
			getConnection();
			String sql="update waiting set pick_up='픽업완료' where order_num="+orderNum;
			pstmt=conn.prepareStatement(sql);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return result;
	}
	public static WaitingDAO getInstence() {
		if(wdo==null) {
			wdo=new WaitingDAO();
		}
		return wdo;
	} 
}
