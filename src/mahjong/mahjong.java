package mahjong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class mahjong extends JFrame {
	private static Table table ;
	private static JPanel tablepan = new JPanel(new BorderLayout());
	private static playerUI[] playersUI = new playerUI[4];
	public mahjong() {
		super("麻將");
		for(int i = 0 ; i < 4 ; i++) {
			playersUI[i] = new playerUI(i);
		}
		
		
		//全螢幕
		int w =(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int h =(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 30;
		Dimension screenSize = new Dimension(w, h);
		setSize(screenSize);
		setLayout(new BorderLayout());
		
		//設定版面
		tablepan.add(playersUI[0].getPaihe(),BorderLayout.SOUTH);
		tablepan.add(playersUI[1].getPaihe(),BorderLayout.EAST);
			tablepan.add(playersUI[2].getPaihe(),BorderLayout.NORTH);
			tablepan.add(playersUI[3].getPaihe(),BorderLayout.WEST);
		table =new Table(playersUI);
		add(playersUI[0],BorderLayout.SOUTH);
		add(playersUI[1],BorderLayout.EAST);
		add(playersUI[2],BorderLayout.NORTH);
		add(playersUI[3],BorderLayout.WEST);
		add(tablepan,BorderLayout.CENTER);
		
		tablepan.setOpaque(false);
		setBackground(Color.GREEN);
		//設定畫面
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1500, 800);
		setLocation(0,0);
	}	
	

	
	
	
	public static void main(String[] args) {
		new mahjong();
		table.mopai(0);
		if(table.getMahjongTable().size() <= 8) {
			new liuju();
		}else if(check.check_selfgang(table.getPlayers()[0].getHandint()) || 
				check.check_selffrontgang(table.getPlayers()[0])||
				check.check_selfhu(table.getPlayers()[0].getHandint())) {
			new event(table);
		}else {
			new da(0,table);
		}
	}
}