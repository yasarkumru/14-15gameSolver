package main;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import model.Node;

public class GameManager {

	private static GameManager instance;

	private GameManager() {
	}

	public static GameManager getInstance() {
		if (instance != null)
			return instance;
		instance = new GameManager();
		return instance;
	}

	public Node solve(Node startPos) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		Set<Node> evaluatedNodes = new HashSet<>();
		evaluatedNodes.add(startPos);
		queue.add(startPos);
		while (!queue.isEmpty()) {
			Node current = queue.remove();
			if (current.getDistance() == 0) {
				return current;
			}
			List<Node> children = current.getChildren();
			for (Node node : children) {
				if (evaluatedNodes.contains(node))
					continue;
				evaluatedNodes.add(node);
				queue.add(node);
			}
		}
		return null;
	}
}
