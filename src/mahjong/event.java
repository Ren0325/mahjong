package mahjong;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class event extends JFrame{
	private JButton chib ,pongb ,gangb ,hub, cancel , gangan , gangming;
	private JPanel jp = new JPanel();
	private JTextField jt = new JTextField();
	private LinkedList<Integer> p0;
	private String [] pic = {"🀇","🀈","🀉","🀊","🀋","🀌","🀍","🀎","🀏",
			"🀙","🀚","🀛","🀜","🀝","🀞","🀟","🀠","🀡",
			"🀐","🀑","🀒","🀓","🀔","🀕","🀖","🀗","🀘",
			"🀀","🀁","🀂","🀃","🀄","🀅","🀆",
			"🀢","🀣","🀤","🀥","🀦","🀧","🀨","🀩","🀫"};
	public event(Table table , int pai , int p) {
		//設定畫面
		setVisible(true);
		setLayout(new BorderLayout());
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("要做什麼事");
		jp.setLayout(new FlowLayout());
		add(jp,BorderLayout.NORTH);
		add(jt,BorderLayout.CENTER);
		Font font = new Font(null, Font.PLAIN, 100);
		jt.setHorizontalAlignment(JTextField.CENTER);
		jt.setFont(font);
		int w =(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 -200;
		int h =(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 -100;
		setLocation(w,h);
		
		
		//畫面出現觸發事件的是什麼牌
		jt.setText(pic[pai]);
		
		
		
		
		//觸發吃碰槓胡後加入相對應的按鈕與事件
		if(check.check_chi(table.getPlayers()[0].getHandint(), pai)) {
			chib = new JButton("吃");
			jp.add(chib);
			chib.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					table.getPlayers()[p].getPaiheint().removeLast();
					new chi(table , pai);
					dispose();
				}
			});
		}
		if(check.check_pong(table.getPlayers()[0].getHandint(), pai)) {
			pongb = new JButton("碰");
			jp.add(pongb);
			pongb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					table.getPlayers()[p].getPaiheint().removeLast();
					new pong(table , pai);
					dispose();
				}
			});
		}
		if(check.check_gang(table.getPlayers()[0].getHandint(), pai)) {
			gangb = new JButton("槓");
			jp.add(gangb);
			gangb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					table.getPlayers()[p].getPaiheint().removeLast();
					new gang(table , pai);
					dispose();
				}
			});
		}
		if(check.check_hu(table.getPlayers()[0].getHandint(), pai)) {
			hub = new JButton("胡");
			jp.add(hub);
			hub.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new hu();
					dispose();
				}
			});
		}
		cancel = new JButton("取消");
		jp.add(cancel);
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p0 = table.getPlayers()[0].getHandint();
				int i = p;
				int pr = da.otherplayerda(table, i);
				while(i < 3){
					i++;
					pr = da.otherplayerda(table, i);
					if((i ==1 || i==2) && (check.check_pong(p0, pr) || check.check_gang(p0, pr)|| check.check_hu(p0, pr))) {
						table.getPlayers()[i].getPaiheint().add(pr);
						table.getPlayersUI()[i].putinArea(i, table.getPlayers()[i]);
						new event(table,pr,i);
						break;
					}else if(i == 3 && (check.check_chi(p0, pr)|| check.check_pong(p0, pr) || check.check_gang(p0, pr)|| check.check_hu(p0, pr))) {
						table.getPlayers()[i].getPaiheint().add(pr);
						table.getPlayersUI()[i].putinArea(i, table.getPlayers()[i]);	
						new event(table,pr,i);
							break;
					}else {
						table.getPlayers()[i].getPaiheint().add(pr);
						table.getPlayersUI()[i].putinArea(i, table.getPlayers()[i]);
					}
				
				}
				if(i == 3) {
					table.mopai(0);
					if(table.getMahjongTable().size() <= 8) {
						new liuju();
					}else if(check.check_selfgang(table.getPlayers()[0].getHandint())) {
						new selfgang(table);
					}else if(check.check_selffrontgang(table.getPlayers()[0])) {
						new selffrontgang(table);
					}else if(check.check_selfhu(table.getPlayers()[0].getHandint())){
						new selfhu(table);
					}else {
						new da(0,table);
					}
				}
				dispose();
			}
		});

		
	}
	
	public event(Table table) {
		setVisible(true);
		setLayout(new BorderLayout());
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("要做什麼事");
		jp.setLayout(new FlowLayout());
		add(jp,BorderLayout.NORTH);
		int w =(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 -200;
		int h =(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 -100;
		setLocation(w,h);
		
		if(check.check_selfgang(table.getPlayers()[0].getHandint())) {
			gangan = new JButton("暗槓");
			jp.add(gangan);
			gangan.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new selfgang(table);
					dispose();
				}
			});
		}
		if(check.check_selffrontgang(table.getPlayers()[0])) {
			gangming = new JButton("明槓");
			jp.add(gangming);
			gangming.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new selffrontgang(table);
					dispose();
				}
			});
		}
		if(check.check_selfhu(table.getPlayers()[0].getHandint())) {
			hub = new JButton("胡");
			jp.add(hub);
			hub.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new hu();
					dispose();
				}
			});
		}
		cancel = new JButton("取消");
		jp.add(cancel);
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new da(0,table);
				dispose();
			}
		});
	}


}


