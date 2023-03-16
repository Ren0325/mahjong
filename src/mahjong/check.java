package mahjong;


import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class check {
	private static LinkedList<LinkedList<Integer>> take_Yan(LinkedList<Integer> hand) {
		//取出將眼
		LinkedList<LinkedList<Integer>> set = new LinkedList();
		LinkedList<Integer> can;
		
		for(int i = 0 ; i < hand.size() - 1;i++ ) {
			for(int x = i + 1 ; x < hand.size(); x++) {
				if(hand.get(i) == hand.get(x)) {
					can = new LinkedList<>();
					for(int k : hand) {
						can.add(k);
					}
					can.remove(x);
					can.remove(i);
					set.add(can);
					break;
				}	
			}
		}		
		return set;
	}
	
	private static LinkedList<LinkedList<Integer>> take_Shun(LinkedList<LinkedList<Integer>> set){
		LinkedList<LinkedList<Integer>> all = new LinkedList<>();
		LinkedList<Integer> can;
		HashSet<Integer> hset = new HashSet<>();
		for(LinkedList<Integer> i : set) {	
			boolean check = false;
			for(int k : i) {
				hset.add(k);
			}
			for(int k = 0 ; k < i.size() ; k++) {
				if (i.get(k) <= 24 && hset.contains(i.get(k)+1) && (hset.contains(i.get(k)+2)
						&& ((i.get(k)/9) == ((i.get(k)+1)/9)) &&((i.get(k)/9) == ((i.get(k)+2)/9)))) {
					can = new LinkedList<>();
					check = true;
					for(int j : i) {
						can.add(j);
					}
					
					
					for(int j =0 ; j < can.size() ; j++) {
						if(can.get(j) == i.get(k)) {
							can.remove(j);
						}
						break;
					}
					
					for(int j =0 ; j < can.size() ; j++) {
						if(can.get(j) == (i.get(k)+1)) {
							can.remove(j);
						}
						break;
					}
					for(int j =0 ; j < can.size() ; j++) {
						if(can.get(j) == (i.get(k)+2)) {
							can.remove(j);
						}
						break;
					}
					all.add(can);
				}
			}
			if (!check) {
				all.add(i);
			} 
			
		}
		
		
		return all;
	}

	private static LinkedList<LinkedList<Integer>> take_Ke(LinkedList<LinkedList<Integer>> set){	
		LinkedList<LinkedList<Integer>> all = new LinkedList<>();
		LinkedList<Integer> can;
		
		
		for(LinkedList<Integer> i : set) {
			boolean check = false;
			for(int k = 0 ; k < i.size()- 2 ; k++) {
				
				if(((i.get(k)) == (i.get(k+1))) && ((i.get(k)) == (i.get(k+2)))) {
					can = new LinkedList<>();
					check = true;
					for(int j : i) {
						can.add(j);
					}
					can.remove(k+2);
					can.remove(k+1);
					can.remove(k);
					all.add(can);
				}
			}
			
			if (!check) {
				all.add(i);
			}
			
		}
		return all;
	}
	
	public static boolean check_hu(LinkedList<Integer> hand , int pai) {
		LinkedList<LinkedList<Integer>> all = new LinkedList<>();
		LinkedList<LinkedList<Integer>> all_1 = new LinkedList<>();
		LinkedList<LinkedList<Integer>> all_2 = new LinkedList<>();
		LinkedList<Integer> hands = new LinkedList<>();
		for(int x : hand) {
			hands.add(x);
		}
		hands.add(pai);
		//排序
		int[] xx =  new int[hands.size()];
		for(int z  = 0; z < xx.length; z++) {
			xx[z] = hands.removeFirst();
		}
		Arrays.sort(xx);
		for(int z : xx) {
			hands.add(z);
		}
		
		
		boolean isHu = false;
		for(LinkedList<Integer> i: take_Yan(hands)) {
			all_1.add(i);
		}
		for(int x = 0 ; x < 5 ;x++) {
			for(LinkedList<Integer> i : take_Shun(all_1)) {
				all_2.add(i);
				all.add(i);
			}
			all_1.clear();
			for(LinkedList<Integer> i : take_Ke(all_2)) {
				all_1.add(i);
				all.add(i);
			}
			all_2.clear();
		}
		for(LinkedList<Integer> i : all) {
			if(i.size() == 0) {
				isHu = true;
			}
		}
		return isHu;
	}

	public static LinkedList<LinkedList<Integer>> take_chi(LinkedList<Integer> hand, int pai) {
		LinkedList<LinkedList<Integer>> all = new LinkedList<>();
		LinkedList<Integer> can;
		HashSet<Integer> hset = new HashSet<>();
		for(int x : hand) {
			hset.add(x);
		}
		hset.add(pai);
		if(pai <=24) {
			if (hset.contains(pai+1) && (hset.contains(pai+2)
					&& ((pai/9) == (pai+1)/9)) &&((pai/9) == (pai+2)/9)) {
				can = new LinkedList<>();
				can.add(pai+1);
				can.add(pai+2);
				all.add(can);
			}
			if (hset.contains(pai+1) && (hset.contains(pai-1)
					&& ((pai/9) == (pai+1)/9)) &&((pai/9) == (pai-1)/9)) {
				can = new LinkedList<>();
				can.add(pai-1);
				can.add(pai+1);
				
				all.add(can);
			}
			if (hset.contains(pai-1) && (hset.contains(pai-2)
					&& ((pai/9) == (pai-1)/9)) &&((pai/9) == (pai-2)/9)) {
				can = new LinkedList<>();
				can.add(pai-2);
				can.add(pai-1);
				all.add(can);
			}
		}
		
		return all;
	}
	
	public static boolean check_chi(LinkedList<Integer> hand, int pai) {
		boolean isOK=false;
		if(take_chi(hand,pai).size() != 0) {
			isOK= true;
		}
		
		return isOK;
	}
	
	public static LinkedList<LinkedList<Integer>> take_pong(LinkedList<Integer> hand, int pai) {
		LinkedList<LinkedList<Integer>> all = new LinkedList<>();
		LinkedList<Integer> can;
		LinkedList<Integer> i = new LinkedList<>(); ;
		for(int x : hand) {
			i.add(x);
		}
		
		for(int k = 0 ; k < i.size()- 2 ; k++) {
			if((pai == (i.get(k))) && (pai == (i.get(k+1)))) {
				can = new LinkedList<>();
				can.add(pai);
				can.add(pai);
				all.add(can);
			}
		}
		return all;
	}
	
	public static boolean check_pong(LinkedList<Integer> hand, int pai) {
		boolean isOK=false;
		if(take_pong(hand,pai).size() != 0) {
			isOK= true;
		}
		return isOK;
	}
	
	public static LinkedList<LinkedList<Integer>> take_gang(LinkedList<Integer> hand, int pai) {
		LinkedList<LinkedList<Integer>> all = new LinkedList<>();
		LinkedList<Integer> can;
		LinkedList<Integer> i = new LinkedList<>(); ;
		for(int x : hand) {
			i.add(x);
		}

		
		//取4張一樣
		for(int k = 0 ; k < i.size()- 3 ; k++) {
			if(pai == (i.get(k)) && (pai == (i.get(k+1))) && (pai == (i.get(k+2)))) {
				can = new LinkedList<>();
				can.add(pai);
				can.add(pai);
				can.add(pai);
				can.add(pai);
				all.add(can);
			}
		}
		return all;
	}
	
	public static boolean check_gang(LinkedList<Integer> hand, int pai) {
		boolean isOK=false;
		if(take_gang(hand,pai).size() != 0) {
			isOK= true;
		}
		return isOK;
	}
	
	public static LinkedList<LinkedList<Integer>> take_selfgang(LinkedList<Integer> hand) {
		LinkedList<LinkedList<Integer>> all = new LinkedList<>();
		LinkedList<Integer> can;
		LinkedList<Integer> i = new LinkedList<>();
		for(int x : hand) {
			i.add(x);
		}

		//取4張一樣
		for(int k = 0 ; k < i.size()- 3 ; k++) {
			if(((i.get(k)) == (i.get(k+1))) && ((i.get(k)) == (i.get(k+2))) && ((i.get(k)) == (i.get(k+3)))) {
				can = new LinkedList<>();
				can.add(i.get(k));
				can.add(i.get(k));
				can.add(i.get(k));
				can.add(i.get(k));
				all.add(can);
			}
		}
		return all;
	}
	
	public static boolean check_selfgang(LinkedList<Integer> hand) {
		boolean isOK=false;
		if(take_selfgang(hand).size() != 0) {
			isOK= true;
		}
		return isOK;
	}

	public static boolean check_selfhu(LinkedList<Integer> hand) {
		LinkedList<LinkedList<Integer>> all = new LinkedList<>();
		LinkedList<LinkedList<Integer>> all_1 = new LinkedList<>();
		LinkedList<LinkedList<Integer>> all_2 = new LinkedList<>();
		LinkedList<Integer> hands = new LinkedList<>();
		for(int x : hand) {
			hands.add(x);
		}
		
		//排序
		int[] xx =  new int[hands.size()];
		for(int z  = 0; z < xx.length; z++) {
			xx[z] = hands.removeFirst();
		}
		Arrays.sort(xx);
		for(int z : xx) {
			hands.add(z);
		}
		
		
		boolean isHu = false;
		for(LinkedList<Integer> i: take_Yan(hands)) {
			all_1.add(i);
		}
		for(int x = 0 ; x < 5 ;x++) {
			for(LinkedList<Integer> i : take_Shun(all_1)) {
				all_2.add(i);
				all.add(i);
			}
			all_1.clear();
			for(LinkedList<Integer> i : take_Ke(all_2)) {
				all_1.add(i);
				all.add(i);
			}
			all_2.clear();
		}
		for(LinkedList<Integer> i : all) {
			if(i.size() == 0) {
				isHu = true;
			}
		}
		return isHu;
	}
	
	public static LinkedList<LinkedList<Integer>> take_selffrontgang(player player) {
		LinkedList<LinkedList<Integer>> all = new LinkedList<>();
		LinkedList<Integer> can;
		LinkedList<Integer> h = new LinkedList<>();
		LinkedList<Integer> f = new LinkedList<>();
		for(int x : player.getFlowerint()) {
			f.add(x);
		}
		for(int x : player.getHandint()) {
			h.add(x);
		}
		//取4張一樣
		for(int x : h) {
			for(int i = 0 ; i < f.size()-2 ; i++) {
				if((x == (f.get(i))) && (x == (f.get(i+1))) && (x == (f.get(i+2)))) {
					can = new LinkedList<>();
					can.add(x);
					all.add(can);
				}
			}
		}
		return all;
	}

	public static boolean check_selffrontgang(player player) {
		boolean isOK=false;
		if(take_selffrontgang(player).size() != 0) {
			isOK= true;
		}
		return isOK;
	}
	
	public static boolean[] take_da(LinkedList<Integer> hand){
		LinkedList<Integer> h = new LinkedList<>();
		boolean[] can = new boolean[hand.size()];
		HashSet<Integer> hsetwan = new HashSet<>();
		HashSet<Integer> hsetton = new HashSet<>();
		HashSet<Integer> hsettiao = new HashSet<>();
		int s = hand.size();
		for(int i : hand) {
			h.add(i);
			if(i < 9) {
				hsetwan.add(i);
			}else if(i < 18) {
				hsetton.add(i);
			}else if(i < 27) {
				hsettiao.add(i);
			}
			
		}
		
		for(int i = 0 ; i < s ; i++) {
			int hh = h.get(i);
			if(i == 0) {
				switch(hh/9) {
				case 0:{
					if(hsetwan.contains(hh+1) || hsetwan.contains(hh-1) || hh == h.get(i+1) ) {
						can[i] = true;
					}else {
						can[i] = false;
					}
					break;
				}
				case 1:{
					if(hsetton.contains(hh+1) || hsetton.contains(hh-1) || hh == h.get(i+1) ) {
						can[i] = true;
					}else {
						can[i] = false;
					}
					break;
				}
				case 2:{
					if(hsettiao.contains(hh+1) || hsettiao.contains(hh-1) || hh == h.get(i+1) ) {
						can[i] = true;
					}else {
						can[i] = false;
					}
					break;
				}
				default :{
					if(hh == h.get(i+1)) {
						can[i] = true;
					}else {
						can[i] = false;
					}
					break;
				}
				}
			}else if (i == s-1) {
				switch(hh/9) {
				case 0:{
					if(hsetwan.contains(hh+1) || hsetwan.contains(hh-1) || hh == h.get(i-1)) {
						can[i] = true;
					}else {
						can[i] = false;
					}
					break;
				}
				case 1:{
					if(hsetton.contains(hh+1) || hsetton.contains(hh-1) || hh == h.get(i-1)) {
						can[i] = true;
					}else {
						can[i] = false;
					}
					break;
				}
				case 2:{
					if(hsettiao.contains(hh+1) || hsettiao.contains(hh-1) || hh == h.get(i-1)) {
						can[i] = true;
					}else {
						can[i] = false;
					}
					break;
				}
				default :{
					if(hh == h.get(i-1)) {
						can[i] = true;
					}else {
						can[i] = false;
					}
					break;
				}
				}
			}else {
				switch(hh/9) {
				case 0:{
					if(hsetwan.contains(hh+1) || hsetwan.contains(hh-1) || hh == h.get(i+1) || hh == h.get(i-1)) {
						can[i] = true;
					}else {
						can[i] = false;
					}
					break;
				}
				case 1:{
					if(hsetton.contains(hh+1) || hsetton.contains(hh-1) || hh == h.get(i+1) || hh == h.get(i-1)) {
						can[i] = true;
					}else {
						can[i] = false;
					}
					break;
				}
				case 2:{
					if(hsettiao.contains(hh+1) || hsettiao.contains(hh-1) || hh == h.get(i+1) || hh == h.get(i-1)) {
						can[i] = true;
					}else {
						can[i] = false;
					}
					break;
				}
				default :{
					if(hh == h.get(i+1) || hh == h.get(i-1)) {
						can[i] = true;
					}else {
						can[i] = false;
					}
					break;
				}
				}
			}
		}
		
		
		
		return can;
	}
}
