package mahjong;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Event;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import javax.swing.JPanel;


public class da extends JFrame{
	private JButton jb = new JButton("確認");
	private String [] str;
	private JComboBox<String> jcb;
	private LinkedList<Integer> p0;
	public da(int num ,Table table) {
		
		str = new String[table.getPlayers()[num].getHandstr().size()];
		for(int i = 0; i < table.getPlayers()[num].getHandstr().size(); i++ ) {
			str[i] = table.getPlayers()[num].getHandstr().get(i);
		}
		
		//設定畫面
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		setSize(400, 200);
		int w =(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 -200;
		int h =(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 -100;
		setLocation(w,h);
		
		
		addjcb(getContentPane());
		add(jb,BorderLayout.NORTH);
		setVisible(true);
		setTitle("打哪一張");
		
		//畫面異動後做的事
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				p0 = table.getPlayers()[0].getHandint();
				int i = 0;
				int pr ;
				do {
					i++;
					pr = otherplayerda(table, i);
					boolean chi = check.check_chi(p0, pr);
					boolean pong= check.check_pong(p0, pr);
					boolean gang = check.check_gang(p0, pr);
					boolean hu = check.check_hu(p0, pr);
					
					if((i ==1 || i==2) && (pong || gang|| hu)) {
						table.getPlayers()[i].getPaiheint().add(pr);
						table.getPlayersUI()[i].putinArea(i, table.getPlayers()[i]);
						new event(table,pr,i);
						break;
					}else if(i == 3 && (chi || pong || gang|| hu)) {
						table.getPlayers()[i].getPaiheint().add(pr);
						table.getPlayersUI()[i].putinArea(i, table.getPlayers()[i]);
						new event(table,pr,i);
						break;
					}else {
						table.getPlayers()[i].getPaiheint().add(pr);
						table.getPlayersUI()[i].putinArea(i, table.getPlayers()[i]);
					}
				}while(i < 3);
				if(i == 3 && !(check.check_chi(p0, pr)|| check.check_pong(p0, pr) || check.check_gang(p0, pr)|| check.check_hu(p0, pr))) {
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
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//按下確定後做的事
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int x = jcb.getSelectedIndex();
				table.getPlayers()[num].getPaiheint().add(table.getPlayers()[num].getHandintNotsort().remove(x));
				table.getPlayersUI()[num].putinArea(num, table.getPlayers()[num]);
				dispose();
			}
		});
	}
	
	//jcombobox 的設定
	public void addjcb(Container pane) {
		jcb= new JComboBox<>(str);
		Font font = new Font(null, Font.PLAIN, 50);
		jcb.setFont(font);
		pane.add(jcb);
	}	
	
	//
	public static int otherplayerda(Table table ,int i) {
		table.mopai(i);
		int s =  table.getPlayers()[i].getHandint().size();
		boolean[] can = check.take_da(table.getPlayers()[i].getHandint());
		int r;
		do {
			r =  (int)(Math.random() * s);
		}while(!can[r]);
		int pr = table.getPlayers()[i].getHandint().remove(r);
		return pr;
	}
}
