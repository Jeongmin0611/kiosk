package kiosk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Kiosk extends JFrame implements ActionListener {
	private static Kiosk kiosk=null;
	MainView mv=MainView.getInstance();
	OrderView ov=OrderView.getInstance();
	OrderFormView ofv=OrderFormView.getInstance();
	CreditView cv=CreditView.getInstance();
	Image logo;

	JButton startBtn=new JButton("�ֹ�����");
	JPanel orderBtnArea=new JPanel();
		JButton cancelBtn=new JButton("�ֹ����");
		JButton orderBtn=new JButton("�ֹ��ϱ�");
		List<OrderVO> list=new ArrayList<OrderVO>();
		ImageIcon helpIcon=new ImageIcon("img/help.jpg");
		JButton helpBtn=new JButton("ȣ�⺧",helpIcon);
		
		
		JButton backBtn=new JButton("�ڷ�");
		
	JPanel orderFormArea=new JPanel();
		JButton eatInbtn=new JButton("����Ļ�");
		JButton takeoutBtn=new JButton("����ũ�ƿ�");
	
	JPanel askOrderArea=new JPanel();
		JButton yesOrder=new JButton("ī�����");
		JButton goback=new JButton("�ڷΰ���");
		
	OrderDAO odo=OrderDAO.getInstence();
	WaitingDAO wdo=WaitingDAO.getInstence();
	public Kiosk() {
		try {
			setTitle("mcdonald's kiosk");
			File logoFile=new File("img/logo.jpg");
			logo = ImageIO.read(logoFile);
			setIconImage(logo);
			setBounds(150, 0, 800, 1050); 
			add(mv);
			startBtn.setPreferredSize(new Dimension(800,100));
			startBtn.setBackground(new Color(39, 174, 96));
			add("South",startBtn);
			setResizable(false);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			orderBtnArea.setPreferredSize(new Dimension(800,80));
			
			orderBtnArea.add(cancelBtn);
			orderBtnArea.add(orderBtn);
			orderBtnArea.add(helpBtn);
			cancelBtn.setPreferredSize(new Dimension(300,50));
			orderBtn.setPreferredSize(new Dimension(300,50));
			
			
			ov.northArea.add(backBtn);
			backBtn.setPreferredSize(new Dimension(100,100));
			
			
			ofv.add(orderFormArea);
				orderFormArea.add(eatInbtn);
				orderFormArea.add(takeoutBtn);
				eatInbtn.setPreferredSize(new Dimension(300,300));
				takeoutBtn.setPreferredSize(new Dimension(300,300));
			
				
			askOrderArea.add(yesOrder);
			askOrderArea.add(goback);
				yesOrder.setPreferredSize(new Dimension(150,150));
				goback.setPreferredSize(new Dimension(150,150));
				
				
		
			startBtn.addActionListener(this);
			backBtn.addActionListener(this);
			orderBtn.addActionListener(this);
			eatInbtn.addActionListener(this);
			takeoutBtn.addActionListener(this);
			yesOrder.addActionListener(this);
			goback.addActionListener(this);
			cancelBtn.addActionListener(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		Kiosk kiosk=new Kiosk();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String event=e.getActionCommand();
		switch (event) {
		case "�ֹ�����":
			add(ov);
			add("South",orderBtnArea);
			mv.setVisible(false);
			startBtn.setVisible(false);
			ov.setVisible(true);
			orderBtnArea.setVisible(true);
			break;
		case "�ڷ�":
			ov.model.setRowCount(0);
			ov.setVisible(false);
			mv.setVisible(true);
			orderBtnArea.setVisible(false);
			add("South",startBtn);
			startBtn.setVisible(true);
			break;
		case "�ֹ��ϱ�":
			int rows=ov.orderList.getRowCount();
			int columns=ov.model.getColumnCount();
		if(rows==0) {
			JOptionPane.showMessageDialog(this, "�Ŵ��� �����Ͽ��ֽʽÿ�.");
		}else {
			orderBtnArea.setVisible(false);
			ov.setVisible(false);
			for(int i=0;i<rows;i++) {
				OrderVO vo=new OrderVO();
				for(int j=0;j<columns;j++){
					if(j==0) {
						vo.setOrderNum((int)ov.model.getValueAt(i,j));
					}else if(j==1) {
						vo.setMenuName(String.valueOf(ov.model.getValueAt(i,j)));
					}else if(j==2) {
						vo.setAmount((int)ov.model.getValueAt(i,j));
					}else if(j==3) {
						vo.setCategory(String.valueOf(ov.model.getValueAt(i,j)));
					}else if(j==4) {
						vo.setPrice((int)ov.model.getValueAt(i,j));
					}else if(j==5) {
						vo.setLine((int)ov.model.getValueAt(i,j));
					}
				}
				list.add(vo);
			}
				add("Center",ofv);
				ofv.setVisible(true);
		}
			break;
		case "����Ļ�":
			ofv.setVisible(false);
			for(int i=0;i<list.size();i++) {
				OrderVO vo=list.get(i);
				vo.setOrderForm("����Ļ�");
			}
			add(cv);
			cv.setVisible(true);
			for(int i=0;i<list.size();i++) {
				OrderVO vo=list.get(i);
				Object[] record= {vo.getMenuName(),vo.getAmount(),vo.getPrice()};
				cv.model.addRow(record);
				cv.orderList.changeSelection(0, 0, false, false);
			}
			add("South",askOrderArea);
			askOrderArea.setVisible(true);
			int countSum=0;
			for(int i=0;i<cv.model.getRowCount();i++){
				countSum+=(int)cv.model.getValueAt(i,2);
			}
			cv.sum.setText(countSum+" ��");
			break;
		case "ī�����":
			int orderNum=list.get(0).getOrderNum();
			wdo.addWaitingList(orderNum);
			int result=odo.addOrderList(list);
			odo.plusOrderNum();
			if(result>0) {
				JOptionPane.showMessageDialog(this, "�ֹ����Ϸ��Ͽ����ϴ�.������ �ֹ���ȣ��  "+orderNum+"���Դϴ�.");
				list.clear();
				ov.model.setRowCount(0);
				cv.setVisible(false);
				askOrderArea.setVisible(false);
				mv.setVisible(true);
				startBtn.setVisible(true);	
			}else {
				JOptionPane.showMessageDialog(this, "�ֹ��� �����Ͽ����ϴ�.");
			}
			break;	
		case "�ڷΰ���":
			cv.setVisible(false);
			askOrderArea.setVisible(false);
			list.clear();
			cv.model.setRowCount(0);
			ov.setVisible(true);
			orderBtnArea.setVisible(true);
			break;	
		case "�ֹ����":
			int row=ov.orderList.getSelectedRow();
			if(row>=0) {
				ov.orderList.changeSelection(row-1, 0, false, false);
				ov.model.removeRow(row);
			}
			break;	
		case "����ũ�ƿ�":
			ofv.setVisible(false);
			for(int i=0;i<list.size();i++) {
				OrderVO vo=list.get(i);
				vo.setOrderForm("����ũ�ƿ�");
			}
			add(cv);
			cv.setVisible(true);
			for(int i=0;i<list.size();i++) {
				OrderVO vo=list.get(i);
				Object[] record= {vo.getMenuName(),vo.getAmount(),vo.getPrice()};
				cv.model.addRow(record);
				cv.orderList.changeSelection(0, 0, false, false);
			}
			add("South",askOrderArea);
			askOrderArea.setVisible(true);
			int countSum2=0;
			for(int i=0;i<cv.model.getRowCount();i++){
				countSum2+=(int)cv.model.getValueAt(i,2);
			}
			cv.sum.setText(countSum2+" ��");
			break;
		}
	}
}
