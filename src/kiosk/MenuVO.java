package kiosk;

public class MenuVO {
	private String menuCode;
	private String category;
	private String menuName;
	private int price;
	public MenuVO() {
		
	}
	public MenuVO(String menuCode,String category,String menuName,int price) {
		this.menuCode=menuCode;
		this.category=category;
		this.menuName=menuName;
		this.price=price;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	
}
