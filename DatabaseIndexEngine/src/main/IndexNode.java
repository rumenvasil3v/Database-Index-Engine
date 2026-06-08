package main;

public class IndexNode {
	private int key;
	private String dataPayload;
	private int height;
	
	private IndexNode left;
	private IndexNode right;
	
	public IndexNode(int key, String dataPayload) {
		this.setKey(key);
		this.setDataPayload(dataPayload);
		this.setHeight(1);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public IndexNode getRight() {
		return right;
	}

	public void setRight(IndexNode right) {
		this.right = right;
	}

	public IndexNode getLeft() {
		return left;
	}

	public void setLeft(IndexNode left) {
		this.left = left;
	}

	public String getDataPayload() {
		return dataPayload;
	}

	public void setDataPayload(String dataPayload) {
		this.dataPayload = dataPayload;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
}
