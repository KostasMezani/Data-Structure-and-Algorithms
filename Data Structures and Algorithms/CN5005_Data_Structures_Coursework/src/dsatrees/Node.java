package dsatrees;

public class Node {
    private int key;
    private int count;
    private Node left;
    private Node right;
//Ερωτημα 1.1
    public Node(int key) {
        this.key = key;
        this.count = 1;
        this.left = null;
        this.right = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
