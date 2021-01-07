package kiosk;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO extends DBConnection{
	private static MenuDAO mdo=null;
	public MenuDAO() {
		
	}

	public static MenuDAO getInstence() {
		if(mdo==null) {
			mdo=new MenuDAO();
		}
		return mdo;
	} 
	public List<MenuVO> getMenuName(String category) {
		List<MenuVO> menuList=new ArrayList<MenuVO>();
		try{
			getConnection();
			String sql="select menu_code,category,menu_name,price from menu where category='"+category+"' "
					+ "order by menu_code";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				MenuVO vo=new MenuVO(rs.getString(1),rs.getString(2),
						rs.getString(3),rs.getInt(4));
				menuList.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return menuList;
	}
	public List<MenuVO> getAllMenu() {
		List<MenuVO> menuList=new ArrayList<MenuVO>();
		try{
			getConnection();
			String sql="select menu_code,category,menu_name,price from menu";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				MenuVO vo=new MenuVO(rs.getString(1),rs.getString(2),
						rs.getString(3),rs.getInt(4));
				menuList.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return menuList;
	}
	public List<String> getCategory() {
		List<String> category=new ArrayList<String>();
		try {
			getConnection();
			String sql="Select distinct category from menu";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				category.add(rs.getString(1));
			}
		}catch(Exception e) {
			
		}finally{
			getClose();
		}
		return category;
		
	}
	public List<MenuVO> selectMenu(String option,String search) {
		List<MenuVO> list=new ArrayList<MenuVO>();
		try {
			getConnection();
			String sql="select menu_code,category,menu_name,price from menu where "+option+" like '%"+search+"%'order by menu_code asc";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MenuVO vo=new MenuVO(rs.getString(1),rs.getString(2),
						rs.getString(3),rs.getInt(4));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			getClose();
		}
		return list;
	}
	public int insertMenu(MenuVO vo) {
		int result=0;
		try {
			getConnection();
			String sql="insert into menu values(?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,vo.getMenuCode());
			pstmt.setString(2,vo.getCategory());
			pstmt.setString(3,vo.getMenuName());
			pstmt.setInt(4, vo.getPrice());
			
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			getClose();
		}
		return result;
	}
	public int deleteMenu(String name) {
		int result=0;
		try {
			getConnection();
			String sql="delete from menu where menu_name='"+name+"'";
			pstmt=conn.prepareStatement(sql);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			getClose();
		}
		return result;
	}
	public int updateMenu(String name) {
		int result=0;
		try {
			getConnection();
			String sql="delete from menu where menu_name='"+name+"'";
			pstmt=conn.prepareStatement(sql);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			getClose();
		}
		return result;
	}
}
