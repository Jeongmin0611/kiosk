package kiosk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class OrderView extends JPanel {
	private static OrderView ov=null;
		ImageIcon image = new ImageIcon("img/banner1.jpg");
		JLabel bannerImg=new JLabel(image);
		
		JPanel orderArea=new JPanel(new BorderLayout());
			JPanel northArea=new JPanel();
				ImageIcon logo = new ImageIcon("img/smalllogo.jpg");
				JLabel logoImg=new JLabel(logo);
				JLabel categoryLabel=new JLabel("맥도날드버거");	
				
			
			JPanel categoryArea=new JPanel();
				ImageIcon image1 = new ImageIcon("img/cateSet.jpg");
				ArrayList<JLabel> labelList=new ArrayList<JLabel>();
				JScrollPane cateScroll=new JScrollPane();
				JPanel catePanel=new JPanel(new GridLayout(0,1,0,0));
			JPanel menuArea=new JPanel();
				JScrollPane menuScroll=new JScrollPane();
				JPanel menuPanel=new JPanel(new GridLayout(0,3,0,0));
			
			JPanel southArea=new JPanel();
				JLabel orderTxt=new JLabel("주문내역");
				Object[] option= {"주문번호","매뉴명","수량","카테고리","금액"};
				DefaultTableModel model=new DefaultTableModel(option,0);
				JTable orderList=new JTable(model);
				JScrollPane listScroll=new JScrollPane(orderList);
				
				
		ImageIcon cateSet = new ImageIcon("img/cateSet.jpg");
		ImageIcon cateOnly = new ImageIcon("img/cateOnly.jpg");
		ImageIcon cateSide = new ImageIcon("img/cateSide.png");
		ImageIcon cateMccafe = new ImageIcon("img/cateMccafe.jpg");
		ImageIcon cateDesert = new ImageIcon("img/cateDesert.jpg");
		ImageIcon cateHappy = new ImageIcon("img/cateHappy.jpg");
		ImageIcon[] cateImg= {cateSet,cateSide,cateMccafe,cateDesert,cateOnly,cateHappy};

		ArrayList<JLabel> jlabelList=new ArrayList<JLabel>();
		
		MenuDAO mdo=MenuDAO.getInstence();
		OrderDAO odo=OrderDAO.getInstence();
	public OrderView() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800,500));
		bannerImg.setPreferredSize(new Dimension(800,150));
		add("North",bannerImg);
		
		add("Center",orderArea);
			orderArea.add("North",northArea);
				northArea.setOpaque(true);
				northArea.setBackground(Color.WHITE);
				northArea.setLayout(new FlowLayout(FlowLayout.LEFT));
				northArea.add(logoImg);
				northArea.add(categoryLabel);
				categoryLabel.setPreferredSize(new Dimension(500,100));
				categoryLabel.setOpaque(true);
				categoryLabel.setBackground(Color.WHITE);
				categoryLabel.setFont(new Font("굴림체",Font.BOLD,50));
				
			orderArea.add("West",cateScroll);
				cateScroll.setOpaque(true);
				cateScroll.setBackground(Color.WHITE);
				cateScroll.setPreferredSize(new Dimension(200,200));
				cateScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				cateScroll.setViewportView(catePanel);
				List<String> cateList=mdo.getCategory();
				for (int i=0; i <cateList.size(); i++) {
					String name=cateList.get(i);
					JLabel d=new JLabel(name,cateImg[i],0);
					labelList.add(d);
					d.setOpaque(true);
					d.setBackground(Color.WHITE);
					d.setPreferredSize(new Dimension(180,180));
					catePanel.add(d);
				}
				
				
				List<MenuVO> menuList=mdo.getMenuName("세트메뉴");
				for(int i=1;i<=menuList.size();i++){
					MenuVO vo=menuList.get(i-1);
					String path = "img/세트메뉴/세트메뉴"+i+".png";
					ImageIcon imageA = new ImageIcon(path);
					JLabel a=new JLabel(vo.getMenuName(),imageA,0);
					a.setOpaque(true);
					a.setBackground(Color.WHITE);
					a.setPreferredSize(new Dimension(180,200));
					a.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							JLabel event=(JLabel)e.getSource();
							List<MenuVO> list=mdo.getAllMenu();
							System.out.println(event.getText());
							for (int j=0;j<list.size();j++) {
								MenuVO vo=list.get(j);
								if(event.getText().equals(vo.getMenuName())) {
									int orderNum=odo.getOrderSeq();
									int cookLine=odo.getCookLines();
									if(orderList.getRowCount()==0) {
										Object[] record= {orderNum,vo.getMenuName(),1,vo.getCategory(),vo.getPrice()};
										model.addRow(record);
										orderList.changeSelection(0, 0, false, false);
									}else if(orderList.getRowCount()>0) {
										int row=orderList.getSelectedRow();
										Object menuName=model.getValueAt(row,1);
										if(menuName.equals(event.getText())){
											int count=(int)model.getValueAt(row,2)+1;
											model.setValueAt(count,row, 2);
											model.setValueAt(vo.getPrice()*count,row,4);
										}else if(!menuName.equals(event.getText())){
											Object[] record= {orderNum,vo.getMenuName(),1,vo.getCategory(),vo.getPrice()};
											model.addRow(record);
											int newRow=orderList.getRowCount();
											orderList.changeSelection(newRow-1, 0, false, false);
										}
									}
								}
							}
						}
					});
					menuPanel.add(a);
				}
			orderArea.add("Center",menuScroll);
				menuScroll.setOpaque(true);
				menuScroll.setBackground(Color.WHITE);
				menuScroll.setPreferredSize(new Dimension(600,200));
				menuScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				menuScroll.setViewportView(menuPanel);
				menuPanel.setOpaque(true);
				menuPanel.setBackground(Color.WHITE);
				
			


				
			orderArea.add("South",southArea);
				southArea.setLayout(new BorderLayout());
				southArea.setPreferredSize(new Dimension(800,150));
				southArea.add("North",orderTxt);
				orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				orderList.getColumn("카테고리").setWidth(0);
				orderList.getColumn("카테고리").setMinWidth(0);
				orderList.getColumn("카테고리").setMaxWidth(0);
				orderList.getColumn("금액").setWidth(0);
				orderList.getColumn("금액").setMinWidth(0);
				orderList.getColumn("금액").setMaxWidth(0);
				listScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				listScroll.setPreferredSize(new Dimension(800,130));
				southArea.add(listScroll);
				
	for (int i=0;i<labelList.size();i++) {
		labelList.get(i).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
					JLabel event=(JLabel)e.getSource();
					jlabelList.clear();
					String labelName=event.getText();
					categoryLabel.setText(labelName);
					menuPanel.setVisible(false);
					menuPanel.removeAll();
					menuPanel.setVisible(true);
					List<MenuVO> menuNameList=mdo.getMenuName(labelName);
					for(int j=0;j<menuNameList.size();j++) {
						MenuVO vo=menuNameList.get(j);
						String path = "img/"+vo.getCategory()+"/"+vo.getCategory()+(j+1)+".png";
						ImageIcon imageA = new ImageIcon(path);
						JLabel a=new JLabel(vo.getMenuName(),imageA,0);
						a.setOpaque(true);
						a.setBackground(Color.WHITE);
						a.setPreferredSize(new Dimension(180,200));
						a.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseReleased(MouseEvent e) {
								JLabel event=(JLabel)e.getSource();
								List<MenuVO> list=mdo.getAllMenu();
								System.out.println(event.getText());
								for (int j=0;j<list.size();j++) {
									MenuVO vo=list.get(j);
									if(event.getText().equals(vo.getMenuName())) {
										int orderNum=odo.getOrderSeq();
										int cookLine=odo.getCookLines();
										if(orderList.getRowCount()==0) {
											Object[] record= {orderNum,vo.getMenuName(),1,vo.getCategory(),vo.getPrice()};
											model.addRow(record);
											orderList.changeSelection(0, 0, false, false);
										}else if(orderList.getRowCount()>0) {
											int row=orderList.getSelectedRow();
											Object menuName=model.getValueAt(row,1);
											if(menuName.equals(event.getText())){
												int count=(int)model.getValueAt(row,2)+1;
												model.setValueAt(count,row, 2);
												model.setValueAt(vo.getPrice()*count,row,4);
											}else if(!menuName.equals(event.getText())){
												Object[] record= {orderNum,vo.getMenuName(),1,vo.getCategory(),vo.getPrice()};
												model.addRow(record);
												int newRow=orderList.getRowCount();
												orderList.changeSelection(newRow-1, 0, false, false);
											}
										}
									}
								}
							}
						});
						jlabelList.add(a);
						menuPanel.add(a);
					}
			}
		});
	}	
}
	public static OrderView getInstance() {
		if(ov==null) {
			ov=new OrderView();
		}
		return ov;
	}
}
