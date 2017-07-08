package net.debisschop.algorithms.questions.week4;


public class BinaryTree<Key extends Comparable<Key>, Value> {

  private Node root;

  private class Node {
    private Key key;
    private Value val;
    private Node left;
    private Node right;
    Node(Key key, Value val) {
      this.key = key;
      this.val = val;
    }
  }

  public void put(Key key, Value val) {
    root = put(root, key, val);
  }
  
  private Node put(Node x, Key key, Value val) {
    if (x == null) return new Node(key, val);
    int cmp = key.compareTo(x.key);
    if (cmp < 0) x.left = put(x.left, key, val);
    else if (cmp > 0) x.right = put(x.right, key, val);
    else x.val = val;
    return x;
  }
  
  public Value get(Key key) {
    Node x = root;
    while (x != null) {
      int cmp = key.compareTo(x.key);
      if (cmp < 0) x = x.left;
      else if (cmp > 0) x = x.right;
      else return x.val;
    }
    return null;
  }

  public void exch() {
    exch(root);
  }

  private void exch(Node node) {
    Key temp = node.key;
    node.key = node.left.key;
    node.left.key = temp;
  }

  public boolean isSearchTree() {
    return isSearchTree(root);
  }

  private boolean isSearchTree(Node node) {
    boolean isLeftSearchTree = true;
    boolean isRightSearchTree = true;
    if (node.left != null) {
      if (node.key.compareTo(node.left.key) <= 0) return false;
      isLeftSearchTree = isSearchTree(node.left);
    }
    if (node.right != null) {
      if (node.key.compareTo(node.right.key) >= 0) return false;
      isRightSearchTree = isSearchTree(node.right);
    }
    return isLeftSearchTree && isRightSearchTree;
  }


}
