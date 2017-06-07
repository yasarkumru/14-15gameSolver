package main;

import model.Node;

public class FifteenGameMain {
	public static void main(String[] args) {
		Node startPos = new Node(new int[][]{
			{6,11,2,12,13},
			{5,3,14,8,10},
			{7,1,9,4,15},
			{16,17,18,0,0}}, null);
		
//		Node startPos = new Node(new int[][]{
//			{15,14,8,12},
//			{10,11,9,13},
//			{2,6,5,1},
//			{3,7,4,0}}, null);
		
		Node solution = GameManager.getInstance().solve(startPos);
		if(solution == null){
			System.out.println("no solution");
			return;
		}
		
		Node cursor = solution;
		
		while (cursor != null) {
			System.out.println(cursor);
			cursor = cursor.getParent();
		}
		
		System.out.println("depth of the solution is "+solution.getDepth());
	}
	
}
