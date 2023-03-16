package mahjong;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Table extends Thread{
	private int mahjongmount = 144;
	private LinkedList<Integer> mahjongTable = new LinkedList<>();
	private LinkedList<Integer> Table = new LinkedList<>();
	private player[] players = new player[4];
	private playerUI[] playersUI;
	public Table(playerUI[] playersUI) {
		this.playersUI = playersUI;
		for(int i = 0 ; i < 4 ; i++) {
			players[i] = new player();
		}
		
		shuffle();
		license();
		changeFlowers();
		
		for(int i = 0 ; i < 4 ; i++) {
			playersUI[i].putinArea(i, players[i]);
		}
		
	}
	
	private void shuffle() {
		for(int x = 0 ; x < 4 ; x++) {
			for(int i = 0 ; i < 34 ; i++) {
				Table.add(i);
			}
		}
		for(int x = 0 ; x < 8 ; x++) {
			Table.add(x+34);
		}
		
		for(int i = mahjongmount-1 ; i > 0 ; i-- ) {
			mahjongTable.add(Table.remove((int)(Math.random() * i))) ;  			
		}		
	}
	
	private void license() {
		
		for(int i = 0; i < 64  ; i++) {
			players[(i-(i/16)*16) / 4].getHandint().add(mahjongTable.removeFirst());
		}
	}
	
	private  void changeFlowers() {
		for(int j=0; j < 8 ;j++) {//防止補牌後又進花牌 巡8次
			for(int k = 0 ; k < 4 ; k++) {
				for(int i = 0 ; i < 16; i++) {
					if (players[k].getHandint().get(i) > 33) {
						players[k].getFlowerint().add(players[k].getHandint().get(i));
						players[k].getHandint().set(i, mahjongTable.removeLast());
					}
				}
			}
		}
	}
			
	public player[] getPlayers() {
		return players;
	}
	
	public void mopai(int num) {
		int m = mahjongTable.removeFirst();
		while(m > 33) {
			players[num].getFlowerint().add(m);
			m = mahjongTable.removeLast();
		}
		players[num].getHandint().add(m);
		playersUI[num].putinArea(num, players[num]);
	}

	public void gangmopai(int num) {
		int m = mahjongTable.removeLast();
		while(m >= 33) {
			players[num].getFlowerint().add(m);
			m = mahjongTable.removeLast();
		}
		players[num].getHandint().add(m);
		playersUI[num].putinArea(num, players[num]);
	}
	
	
	public LinkedList<Integer> getMahjongTable() {
		return mahjongTable;
	}

	
	public  playerUI[] getPlayersUI() {
		return playersUI;
	}

	
}




