package kiosk;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DBConnection {
	private static OrderDAO dao;
	public OrderDAO() {
	
	}
	
	
	
	
	public static OrderDAO getInstence() {
		if(dao==null) {
			dao=new OrderDAO();
		}
		return dao;
	} 
	
	public int getOrderSeq() {
		int eatinSeq = 0;
		try {
			getConnection();
			String sql="select last_number from user_sequences where sequence_name='ORDER_SEQ'";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				eatinSeq=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return eatinSeq;
	}
	public void plusOrderNum() {
		try {
			getConnection();
			String sql="SELECT order_SEQ.NEXTVAL from dual";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
		}catch(Exception e) {
			
			
		}finally {
			getClose();
			
		}
	}
	public int getCookLines() {
		int cookLine = 0;
		try {
			getConnection();
			String sql="select last_number from user_sequences where sequence_name='COOK_LINES'";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				cookLine=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return cookLine;
	}
	public void plusCookLines() {
		try {
			getConnection();
			String sql="SELECT cook_LINES.NEXTVAL from dual";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
		}catch(Exception e) {
			
			
		}finally {
			getClose();
			
		}
	}
		public List<OrderVO> getWaitingOrder() {
			List<OrderVO> list=new ArrayList<OrderVO>();
		try {
			getConnection();
			String sql="select o.order_num,menu_name,amount,order_form,lines,state,w.pick_up \r\n" + 
					"from ordertable o ,waiting w where o.order_num=w.order_num and w.pick_up='픽업대기'";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				OrderVO vo=new OrderVO(rs.getInt(1),rs.getString(2),rs.getInt(3),
						rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
			
		}
		return list;
		}
	public List<OrderVO> getOrderList(String datestr1,String datestr2) {
			List<OrderVO> list=new ArrayList<OrderVO>();
			try {
				getConnection();
				String sql="select order_num,menu_name,amount,cost,cost_form,to_char(order_time,'YYYY-MM-DD HH24:Mi:SS')from ordertable "
						+ "where order_time BETWEEN TO_DATE('"+datestr1+"', 'YYYY-MM-DD') AND TO_DATE('"+datestr2+"', 'YYYY-MM-DD') - (INTERVAL '1' SECOND)";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					OrderVO vo=new OrderVO(rs.getInt(1),rs.getString(2),
							rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6));
					list.add(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getClose();
				
			}
			return list;
	}
	public int getMonthProfit(String dateStr1,String dateStr2) {
			int sum=0;
			try {
				getConnection();
				
				String sql="select sum(cost)from ordertable where order_time BETWEEN TO_DATE('"+dateStr1+"', 'YYYY-MM-DD') " 
						+ " AND TO_DATE('"+dateStr2+"', 'YYYY-MM-DD') - (INTERVAL '1' SECOND)";
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					sum=rs.getInt(1);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getClose();
			}
			
			return sum;	
		}
	public int addOrderList(List<OrderVO> list) {
			int result=0;
			try {
				getConnection();
				conn.setAutoCommit(false);
				for(int i=0;i<list.size();i++) {
					OrderVO vo=list.get(i);
					String sql="insert into ordertable(order_num,category,menu_name,amount,cost,cost_form,order_form,state,lines,order_time) \r\n" + 
							" values("+vo.getOrderNum()+",'"+vo.getCategory()+"','"+vo.getMenuName()+"',"+vo.getAmount()+","+vo.getPrice()+","
									+ "'카드결제','"+vo.getOrderForm()+"','조리대기',"+(i+1)+",sysdate)";
					pstmt=conn.prepareStatement(sql);
					result=pstmt.executeUpdate();
				}
				 conn.commit();
			}catch(Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
				}
				e.printStackTrace();
			}finally {
				getClose();
			}
			return result;
		}
	public int deleteOrderList(OrderVO vo) {
			int result=0;
			try {
				getConnection();
				String sql="delete from ordertable where order_num="+vo.getOrderNum()+" and "
						+ "menu_name='"+vo.getMenuName()+"' and order_form='"+vo.getOrderForm()+"'";
				pstmt=conn.prepareStatement(sql);
				result=pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getClose();
			}
			return result;
		}
	public int updateOrderForm(int orderNum) {
			int result=0;
			try {
				getConnection();
				String sql="update ordertable set Order_form='테이크아웃' where order_num="+orderNum;
				pstmt=conn.prepareStatement(sql);
				result=pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				getClose();
			}
			return result;
		}
}
