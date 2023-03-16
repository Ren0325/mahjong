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
import javax.swing.JTextField;

public class hu extends JFrame{
	private LinkedList<LinkedList<Integer>> set = new LinkedList<>();
	private JButton jb = new JButton("結束");
	private String[] str;
	private JTextField jt = new JTextField();
	public hu(){
		setVisible(true);
		setLayout(new BorderLayout());
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		int w =(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 -200;
		int h =(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 -100;
		setLocation(w,h);
		
		
		add(jb,BorderLayout.NORTH);
		add(jt,BorderLayout.CENTER);
		jt.setHorizontalAlignment(JTextField.CENTER);
		jt.setText("恭喜胡牌");
		jb.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
	}
}
