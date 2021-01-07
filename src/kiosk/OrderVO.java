package kiosk;

import java.io.Serializable;

public class OrderVO implements Serializable{
	private static final long serialVersionUID = 1234567890L;
	private int orderNum;
	private String category;
	private String menuName;
	private int amount;
	private int price;
	private String costForm;
	private String state;
	private int line;
	private String orderTime;
	private String orderForm;
	private String pickUp;

	
	public OrderVO() {
	
	}
	public OrderVO(int orderNum,String menuName,int amount,int price,String costForm,String state,
			int line,String orderTime,String orderForm,String pickUp) {
		this.orderNum=orderNum;
		this.menuName=menuName;
		this.amount=amount;
		this.price=price;
		this.costForm=costForm;
		this.state=state;
		this.line=line;
		this.orderTime=orderTime;
		this.orderForm=orderForm;
		this.pickUp=pickUp;
	}
	public OrderVO(int orderNum,String menuName,int amount,String costForm,int line,String orderTime,String orderForm,String pickUp) {
		this.orderNum=orderNum;
		this.menuName=menuName;
		this.amount=amount;
		this.costForm=costForm;
		this.line=line;
		this.orderTime=orderTime;
		this.orderForm=orderForm;
		this.pickUp=pickUp;
	}
	public OrderVO(int orderNum,String menuName,int amount,int price,String costForm,String state,int line) {
		this.orderNum=orderNum;
		this.menuName=menuName;
		this.amount=amount;
		this.price=price;
		this.costForm=costForm;
		this.state=state;
		this.line=line;
	}
	public OrderVO(int orderNum,String menuName,int amount,int price,String orderForm,int line,String state,String pickUp) {
		this.orderNum=orderNum;
		this.menuName=menuName;
		this.amount=amount;
		this.orderForm=orderForm;
		this.line=line;
		this.state=state;
		this.pickUp=pickUp;
		
	}
	public OrderVO(int orderNum,String category,String menuName,int amount,int price,String orderForm) {
		this.orderNum=orderNum;
		this.category=category;
		this.menuName=menuName;
		this.amount=amount;
		this.price=price;
		this.orderForm=orderForm;
		
	}
	public OrderVO(int orderNum,String menuName,int amount,int price,String costForm,String orderTime) {
		this.orderNum=orderNum;
		this.menuName=menuName;
		this.amount=amount;
		this.price=price;
		this.costForm=costForm;
		this.orderTime=orderTime;
	}
	public OrderVO(int orderNum, String menuName, int amount, String orderForm, int line, String state, String pickUp) {
		this.orderNum=orderNum;
		this.menuName=menuName;
		this.amount=amount;
		this.orderForm=orderForm;
		this.line=line;
		this.state=state;
		this.pickUp=pickUp;
	}
	public OrderVO(int orderNum, String menuName, String orderForm) {
		this.orderNum=orderNum;
		this.menuName=menuName;
		this.orderForm=orderForm;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int cost) {
		this.price = cost;
	}
	public String getCostForm() {
		return costForm;
	}
	public void setCostForm(String costForm) {
		this.costForm = costForm;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderForm() {
		return orderForm;
	}
	public void setOrderForm(String orderForm) {
		this.orderForm = orderForm;
	}
	public String getPickUp() {
		return pickUp;
	}
	public void setPickUp(String pickUp) {
		this.pickUp = pickUp;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
