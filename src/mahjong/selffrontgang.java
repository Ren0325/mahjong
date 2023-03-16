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
import javax.swing.JPanel;

public class selffrontgang extends JFrame{
	private LinkedList<LinkedList<Integer>> set = new LinkedList<>();
	private JButton jb = new JButton("槓");
	private JButton cancel = new JButton("取消");
	private JPanel jp = new JPanel();
	private String[] str;
	private JComboBox<String> jcb;
	private LinkedList<Integer> p0;
	private LinkedList<LinkedList<Integer>> gang;
	public selffrontgang(Table table){
		gang = check.take_selffrontgang(table.getPlayers()[0]);
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
		
		
		jp.setLayout(new FlowLayout());
		jp.add(jb);
		jp.add(cancel);
		
		add(jp,BorderLayout.NORTH);
		addjcb(getContentPane());
		
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int x = jcb.getSelectedIndex();
				LinkedList<Integer> f = new LinkedList<>();
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
				for(int z = 0 ; z < 4 ;z++) {
					table.getPlayersUI()[z].putinArea(z, table.getPlayers()[z]);
				}
				table.gangmopai(0);
				
				if(check.check_selfgang(table.getPlayers()[0].getHandint())) {
					new selffrontgang(table);
				}else if(check.check_selfhu(table.getPlayers()[0].getHandint())){
					new selfhu(table);
				}else {
					new da(0,table);
				}
				dispose();
			}
		});
	
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new da(0,table);
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
