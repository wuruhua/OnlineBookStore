package test.junit.dao;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TestSet {

	public static void main(String[] args) {
		Set<Integer> scores = new LinkedHashSet<Integer>();
		
		List<Integer> temp = new ArrayList<Integer>();
		temp.add(34);
		temp.add(99);
		temp.add(100);
		
		scores.addAll(temp);
		System.out.println(scores);
		System.out.println(scores.size());
	}

}
