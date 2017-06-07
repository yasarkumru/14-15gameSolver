package model;

import java.util.LinkedList;
import java.util.List;

public class Node implements Comparable<Node> {
	private Node parent;
	private int[][] board;
	private List<Node> children;
	private String hashString;
	private int depth;
	private int evaluation = -1;
	private int distance = -1;

	public Node(int[][] board, Node parent) {
		this.board = board;
		this.parent = parent;
		if (parent != null)
			depth = parent.depth + 1;
	}

	public int getDistance() {
		if (distance != -1)
			return distance;

		distance = 0;
		int width = board[0].length;
		int height = board.length;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (board[y][x] == 0)
					continue;
				int val = board[y][x] - 1;
				int expectedX = val % width;
				int expectedY = val / width;
				distance += Math.abs(x - expectedX) + Math.abs(y - expectedY);
			}
		}
		return distance;
	}

	public int getEvaluation() {
		if (evaluation != -1)
			return evaluation;

		evaluation =  getDistance() + depth/2;
		return evaluation;
	}

	public Node getParent() {
		return parent;
	}

	public List<Node> getChildren() {
		if (children != null)
			return children;
		children = new LinkedList<>();
		for (int x = 0; x < board[0].length; x++) {
			for (int y = 0; y < board.length; y++) {
				if (board[y][x] != 0)
					continue;

				try {
					int[][] makeMove = makeMove(x, y, 0, -1);
					if (makeMove != null)
						children.add(new Node(makeMove, this));
				} catch (Exception e) {
				}

				try {
					int[][] makeMove = makeMove(x, y, -1, 0);
					if (makeMove != null)
						children.add(new Node(makeMove, this));
				} catch (Exception e) {
				}
				try {
					int[][] makeMove = makeMove(x, y, 1, 0);
					if (makeMove != null)
						children.add(new Node(makeMove, this));
				} catch (Exception e) {
				}
				try {
					int[][] makeMove = makeMove(x, y, 0, 1);
					if (makeMove != null)
						children.add(new Node(makeMove, this));
				} catch (Exception e) {
				}
			}
		}

		return children;
	}

	private int[][] makeMove(int x, int y, int xOffset, int yOffset) {
		if (board[y + yOffset][x + xOffset] == 0)
			return null;
		int[][] clone = getCloneOfTheBoard();
		clone[y][x] = clone[y + yOffset][x + xOffset];
		clone[y + yOffset][x + xOffset] = 0;
		return clone;
	}

	private int[][] getCloneOfTheBoard() {
		int[][] clone = new int[board.length][];
		for (int i = 0; i < clone.length; i++) {
			clone[i] = board[i].clone();
		}
		return clone;
	}

	@Override
	public int compareTo(Node o) {
		return this.getEvaluation() - o.getEvaluation();
	}

	public String getHashString() {
		if (hashString != null)
			return hashString;
		StringBuilder sb = new StringBuilder();
		for (int[] is : board) {
			for (int i : is) {
				sb.append(i);
			}
		}
		hashString = sb.toString();
		return hashString;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;
		return this.getHashString().equals(other.getHashString());
	}

	@Override
	public int hashCode() {
		return getHashString().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int[] is : board) {
			for (int i : is) {
				sb.append(i).append(" ");
				if (i < 10)
					sb.append(" ");
			}
			sb.append("\n");
		}
		sb.append("##############\n");

		return sb.toString();
	}

	public int getDepth() {
		return depth;
	}

}
