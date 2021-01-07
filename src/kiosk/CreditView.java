package kiosk;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CreditView extends JPanel{
	private static CreditView cv=null;
	ImageIcon image = new ImageIcon("img/banner3.jpg");
	JLabel innerImg=new JLabel(image);
	
	JPanel creditArea= new JPanel(new BorderLayout());
	JLabel askTxt=new JLabel("결제하시겠습니까?");
	
	Object[] field= {"매뉴명","수량","금액"};
	DefaultTableModel model=new DefaultTableModel(field,0);
	JTable orderList=new JTable(model);
	JScrollPane listScroll=new JScrollPane(orderList);
	
	JPanel textArea=new JPanel();
		JLabel sumTxt=new JLabel("총 금액 :");
		JLabel sum=new JLabel("0 원");
		
 	public CreditView() { 
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800,1200));
		add("North",innerImg);
		
		add(creditArea);
			creditArea.add("North",askTxt);
			creditArea.add(listScroll);
				listScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				
		add("South",textArea);
			textArea.add(sumTxt);
			textArea.add(sum);
			
	}
	public static CreditView getInstance() {
		if(cv==null) {
			cv=new CreditView();
		}
		return cv;
	}
	public void setList(List<OrderVO> list) {
		for(int i=0;i<list.size();i++) {
			OrderVO vo=list.get(i);
			for (int j=0;j<model.getColumnCount();j++) {
				if(j==0) {
					model.setValueAt(vo.getMenuName(), i, j);
				}else if(j==1) {
					model.setValueAt(vo.getAmount(), i, j);
				}else if(j==2) {
					model.setValueAt(vo.getPrice(), i, j);
				}
			}
		}
	}
}
