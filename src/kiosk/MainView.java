package kiosk;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainView extends JPanel{
	private static MainView mv=null;
	JPanel mainImageArea=new JPanel();
		ImageIcon image = new ImageIcon("img/main.jpg");
		JLabel innerImg=new JLabel(image);
	public MainView() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(800,1200));
		mainImageArea.setPreferredSize(new Dimension(800,950));
		mainImageArea.add(innerImg);
		add(mainImageArea);
	}
	public static MainView getInstance() {
		if(mv==null) {
			mv=new MainView();
		}
		return mv;
	}
}
