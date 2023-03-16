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

public class selfhu extends JFrame{
	private JButton jb = new JButton("胡");
	private JButton cancel = new JButton("取消");
	private JPanel jp = new JPanel();

	public selfhu(Table table){
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
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new hu();
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

}
