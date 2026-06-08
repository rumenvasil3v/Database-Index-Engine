package main;

import java.util.ArrayList;
import java.util.List;

public class DatabaseIndexEngine {
	private IndexNode root = null;
	
	private int getHeight(IndexNode node) {
		return (node == null) ? 0 : node.getHeight();
	}
	
	private int getBalanceFactor(IndexNode node) {
		return (node == null) ? 0 : getHeight(node.getLeft()) - getHeight(node.getRight());
	}
	
	private void updateHeight(IndexNode node) {
		if (node != null) {
			node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()))); 
		}
	}
	
	private IndexNode rotateRight(IndexNode y) {
		IndexNode x = y.getLeft();
		IndexNode T2 = x.getRight();
		
		x.setRight(y);
		y.setLeft(T2);
		
		updateHeight(y);
		updateHeight(x);
		
		return x;
	}
	
	private IndexNode rotateLeft(IndexNode x) {
        IndexNode y = x.getRight();
        IndexNode T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        updateHeight(x);
        updateHeight(y);

        return y;
    }
	
	public void insert(int key, String dataPayload) {
		root = recursiveInsert(root, key, dataPayload);
	}
	
	private IndexNode recursiveInsert(IndexNode node, int key, String dataPayload) {
        if (node == null) {
            return new IndexNode(key, dataPayload);
        }

        if (key < node.getKey()) {
            node.setLeft(recursiveInsert(node.getLeft(), key, dataPayload));
        } else if (key > node.getKey()) {
            node.setRight(recursiveInsert(node.getRight(), key, dataPayload));
        } else {
            node.setDataPayload(dataPayload);
            return node;
        }

        updateHeight(node);

        int balance = getBalanceFactor(node);

        if (balance > 1 && key < node.getLeft().getKey()) {
            return rotateRight(node);
        }

        if (balance < -1 && key > node.getRight().getKey()) {
            return rotateLeft(node);
        }

        if (balance > 1 && key > node.getLeft().getKey()) {
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        if (balance < -1 && key < node.getRight().getKey()) {
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }

    public String find(int key) {
        IndexNode current = root;
        while (current != null) {
            if (key == current.getKey()) {
                return current.getDataPayload();
            } else if (key < current.getKey()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return null;
    }

    public List<String> rangeSearch(int minKey, int maxKey) {
        List<String> results = new ArrayList<>();
        recursiveRangeSearch(root, minKey, maxKey, results);
        return results;
    }

    private void recursiveRangeSearch(IndexNode node, int min, int max, List<String> results) {
        if (node == null) return;

        if (node.getKey() > min) {
            recursiveRangeSearch(node.getLeft(), min, max, results);
        }

        if (node.getKey() >= min && node.getKey() <= max) {
            results.add(node.getDataPayload());
        }

        if (node.getKey() < max) {
            recursiveRangeSearch(node.getRight(), min, max, results);
        }
    }

    public int getRootKey() {
        return (root == null) ? -1 : root.getKey();
    }
}
