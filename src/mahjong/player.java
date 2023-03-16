package mahjong;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class player{ 
	private LinkedList<Integer> handint ;
	private LinkedList<Integer> flowerint ;
	private LinkedList<Integer> paiheint ;
	private LinkedList<String> handstr;
	private LinkedList<String> flowerstr;
	private LinkedList<String> paihestr ;
	
	
	public player() {
		handint = new LinkedList<>();
		flowerint = new LinkedList<>();
		paiheint = new LinkedList<>();
		handstr = new LinkedList<>();
		flowerstr = new LinkedList<>();
		paihestr = new LinkedList<>();
	}

	public void mahjongsort() {
		int[] x =  new int[handint.size()];
		for(int z  = 0; z < x.length; z++) {
			x[z] = handint.removeFirst();
		}
		Arrays.sort(x);
		for(int z : x) {
			handint.add(z);
		}		
	}
	
	public static void numis(LinkedList<Integer> i , LinkedList<String> str) {

		String [] pic = {"🀇","🀈","🀉","🀊","🀋","🀌","🀍","🀎","🀏",
				"🀙","🀚","🀛","🀜","🀝","🀞","🀟","🀠","🀡",
				"🀐","🀑","🀒","🀓","🀔","🀕","🀖","🀗","🀘",
				"🀀","🀁","🀂","🀃","🀄","🀅","🀆",
				"🀢","🀣","🀤","🀥","🀦","🀧","🀨","🀩"};
		for(int card : i) {
			str.add(pic[card]);
		}
	}
	public LinkedList<Integer> getHandint() {
		mahjongsort();
		return handint;
	}
	
	public LinkedList<Integer> getHandintNotsort() {
		return handint;
	}

	public LinkedList<String> getHandstr() {
		handstr.clear();
		numis(handint , handstr);
		return handstr;
	}

	public LinkedList<Integer> getFlowerint() {
		mahjongsort();
		return flowerint;
	}

	public LinkedList<String> getFlowerstr() {
		flowerstr.clear();
		numis(flowerint , flowerstr);
		return flowerstr;
	}

	public LinkedList<Integer> getPaiheint() {
		return paiheint;
	}

	public LinkedList<String> getPaihestr() {
		paihestr.clear();
		numis(paiheint,paihestr);
		return paihestr;
	}
	
	

}
