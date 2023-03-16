package mahjong;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class gang extends JFrame{
	private LinkedList<LinkedList<Integer>> set = new LinkedList<>();
	private JButton jb = new JButton("確認");
	private JButton cancel = new JButton("確認");
	private String[] str;
	private JComboBox<String> jcb;
	private LinkedList<Integer> p0;
	private LinkedList<LinkedList<Integer>> gang;
	public gang(Table table , int pai){
		p0 = table.getPlayers()[0].getHandint();
		gang = check.take_gang(p0, pai);
		int i = gang.size();
		str = new String[i];
		for(int k = 0 ; k < i ; k++) {
			LinkedList<String> s = new LinkedList<>();
			player.numis(gang.get(k),s);
			for(String z : s) {
				if(str[k] != null) {
					str[k] = str[k] + z;
				}else {
					str[k] = z;
				}
			}
		}
		setVisible(true);
		setLayout(new BorderLayout());
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		int w =(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 -200;
		int h =(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 -100;
		setLocation(w,h);
		
		
		add(jb,BorderLayout.NORTH);
		addjcb(getContentPane());
		
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int x = jcb.getSelectedIndex();
				LinkedList<Integer> f = new LinkedList<>();
				LinkedList<LinkedList<Integer>> gang = check.take_gang(p0, pai);
				int i = gang.size();
				for(int k : gang.get(x)) {
					p0 = table.getPlayers()[0].getHandint();
					for(int j = 0 ; j < p0.size() ; j++) {
						if(p0.get(j) == k) { 
							f.add(table.getPlayers()[0].getHandint().remove(j));
							break;
						}
					}
				
				}
				for(int z : f) {
					table.getPlayers()[0].getFlowerint().add(z);
				}
				table.getPlayers()[0].getFlowerint().add(pai);
				for(int z = 0 ; z < 4 ;z++) {
					table.getPlayersUI()[z].putinArea(z, table.getPlayers()[z]);
				}
				table.gangmopai(0);
				if(table.getMahjongTable().size() <= 8) {
					new liuju();
				}else if(check.check_selfgang(table.getPlayers()[0].getHandint()) || 
						check.check_selffrontgang(table.getPlayers()[0])||
						check.check_selfhu(table.getPlayers()[0].getHandint())) {
					new event(table);
				}else {
					new da(0,table);
				}
				dispose();
			}
		});
	}
	private void addjcb(Container pane) {
		jcb= new JComboBox<>(str);
		Font font = new Font(null, Font.PLAIN, 50);
		jcb.setFont(font);
		pane.add(jcb);
	}
}
