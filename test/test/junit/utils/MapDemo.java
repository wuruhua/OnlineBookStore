package test.junit.utils;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String,Integer> scores = new HashMap<String,Integer> ();
		scores.put("zs", 80);
		scores.put("ls", 60);
		scores.put("ww", 70);
		
//		for(Integer score:scores.values()){
//			System.out.println(score);
//		}
		
		for(Map.Entry<String,Integer> entry:scores.entrySet()){
			System.out.println("key:"+ entry.getKey()+"\tvalue:"+ entry.getValue());
		}
		
		
	}

}
