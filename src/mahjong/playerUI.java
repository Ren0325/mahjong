package mahjong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class playerUI extends JPanel{
	private JLabel front= new JLabel("",JLabel.CENTER);
	private JLabel hand= new JLabel("",JLabel.CENTER);
	private JTextPane Paihe= new JTextPane();
	public playerUI(int num) {
		setLayout(new BorderLayout());
		
		//分玩家0-3的位置與牌
		switch(num) {
		case 0:{
			add(hand,BorderLayout.SOUTH);
			add(front,BorderLayout.NORTH);
			setPreferredSize(new Dimension(1350,80));
			Paihe.setPreferredSize(new Dimension(650,100));
			break;
		}
		case 1:{
			add(hand,BorderLayout.EAST);
			add(front,BorderLayout.WEST);
			setPreferredSize(new Dimension(80,600));
			Paihe.setPreferredSize(new Dimension(300,400));
			break;
		}
		case 2:{
			add(hand,BorderLayout.NORTH);
			add(front,BorderLayout.SOUTH);
			setPreferredSize(new Dimension(1350,80));
			Paihe.setPreferredSize(new Dimension(650,100));
			break;
		}
		case 3:{
			add(hand,BorderLayout.WEST);
			add(front,BorderLayout.EAST);
			setPreferredSize(new Dimension(80,600));
			Paihe.setPreferredSize(new Dimension(300,400));
			break;
		}
		}
		
		initArea(num);
		
		StyledDocument doc = Paihe.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center,StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
//		front.setBackground(Color.green);
//		hand.setBackground(Color.green);
//		Paihe.setBackground(Color.green);
//		this.setBackground(Color.green);
		front.setOpaque(false);
		hand.setOpaque(false);
		Paihe.setOpaque(false);
		this.setOpaque(false);
		Paihe.setEditable(false);
		}
	
	
	private void initArea(int num) {
		Font font2 = new Font(null, Font.PLAIN, 40);
		Font font3 = new Font(null, Font.PLAIN, 30);
			hand.setFont(font3);
			front.setFont(font3);
			Paihe.setFont(font2);
	}
		

	public void putinArea(int num , player player) {
		String h = "";String f = "";String p = "";
		String pai = "🀫";
		switch(num) {
			case 0:{
				for(String x : player.getHandstr()) {
					h = h + x + " ";
				}
				hand.setText(h);
				
				for(String x : player.getFlowerstr()) {
					f = f + x + " ";
				}
				front.setText(f);
				
				int z = player.getPaihestr().size();
				
				if(z < 9) {
					for(String x : player.getPaihestr()) {
						p = p + x ;
					}
					Paihe.setText(p);
					
				}else {
					for(int i = 0 ; i < z ; i++) {
						String x = player.getPaihestr().get(i);
						p = p + x;
						if(i%9 == 8) {
							p = p + "\n" ;
						}
					}
					Paihe.setText(p);
				}
				break;
			}
			case 1: case 3:{
				for(int i = 0 ; i < 16 ; i++) {
					h = h + pai +"<br>";
				}
				hand.setText("<html><body>" + h + "</body></html>");
				
				
				for(String x : player.getFlowerstr()) {
					f = f + x + "<br>";
				}
				front.setText("<html><body>" + f + "</body></html>");
				
				int z;
				if(player.getPaihestr().size() > 6) {
					z = 6;
				}else {
					z = player.getPaihestr().size();
				}
				int j = player.getPaihestr().size() - 1 / 6;
				for(int i = 0 ; i < z ;i++) {
					String x = player.getPaihestr().get(i);
					for (int g = 1 ; g <= j ; g++) {
						if (i+ (g * 6) < player.getPaihestr().size() ) {
							x = x + player.getPaihestr().get(i + (g * 6));
						}
					}
					p = p + x +"\n";
				}

				Paihe.setText(p);
				break;
			}
			case 2:{
				for(int i = 0 ; i < 16 ; i++) {
					h = h + pai +" ";
				}
				hand.setText(h);
				
				for(String x : player.getFlowerstr()) {
					f = f + x + " ";
				}
				front.setText(f);
				
				int z = player.getPaihestr().size();
				
				if(z < 9) {
					for(String x : player.getPaihestr()) {
						p = p + x ;
					}
					Paihe.setText(p);
					
				}else {
					for(int i = 0 ; i < z ; i++) {
						String x = player.getPaihestr().get(i);
						p = p + x;
						if(i%9 == 8) {
							p = p + "\n" ;
						}
					}
					Paihe.setText(p);
				}
				break;
			}
			
			
			
		}
		
	}


	public JTextPane getPaihe() {
		return Paihe;
	}
	


}
