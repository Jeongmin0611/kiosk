package kiosk;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OrderFormView extends JPanel{
	private static OrderFormView ofv=null;
	JPanel bannerArea=new JPanel();
		ImageIcon image = new ImageIcon("img/banner2.jpg");
		JLabel bannerImg=new JLabel(image);
	
	public OrderFormView() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800,500));
		bannerArea.setPreferredSize(new Dimension(800,320));
		bannerArea.add(bannerImg);
		add("North",bannerArea);
		
		
	}
	public static OrderFormView getInstance() {
		if(ofv==null) {
			ofv=new OrderFormView();
		}
		return ofv;
	}
}
