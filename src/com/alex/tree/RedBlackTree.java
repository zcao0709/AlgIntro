package com.alex.tree;

/**
 * Created by Administrator on 2016/10/20.
 */
// Chapter 13
public class RedBlackTree {
    private enum Color {BLACK, RED};
    private static class Node {
        private int key;
        private Node left;
        private Node right;
        private Node parent;
        private Color color;

        public Node(int key, Node left, Node right, Node parent, Color color) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.color = color;
        }

        @Override
        public String toString() {
            if (color == Color.BLACK) {
                return String.valueOf(key) + "B";
            } else {
                return String.valueOf(key) + "R";
            }
        }
    }

    private Node root;
    private Node nil = new Node(0, null, null, null, Color.BLACK);

    private static final int MAX_VALUE_LEN = 18;

    public RedBlackTree() {
        root = nil;
    }

    public void printTree() {
        inOrder4Print(root, 0, "H");
        System.out.println();
    }

    private void inOrder4Print(Node node, int height, String to) {
        if (node == nil) {
            return;
        }
        inOrder4Print(node.right, height+1, "v");
        String val = to + node.toString() + to;
        int lenM = val.length();
        int lenL = (MAX_VALUE_LEN - lenM) / 2;
        int lenR = MAX_VALUE_LEN - lenM - lenL;
        val = getSpace(lenL).append(val).append(getSpace(lenR).toString()).toString();
        System.out.println(getSpace(height * MAX_VALUE_LEN).append(val));
        inOrder4Print(node.left, height+1, "^");
    }

    private StringBuilder getSpace(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(" ");
        }
        return sb;
    }

    private int keyOrException(Node node) {
        if (node != nil) {
            return node.key;
        }
        throw new RuntimeException("No such value");
    }

    public boolean search(int key) {
        if (searchNode(key) == nil) {
            return false;
        } else {
            return true;
        }
    }
    private Node searchNode(int key) {
        Node n = root;
        while (n != nil) {
            if (key > n.key) {
                n = n.right;
            } else if (key < n.key) {
                n = n.left;
            } else {
                return n;
            }
        }
        return nil;
    }

    public int lowest() {
        return keyOrException(lowestNode(root));
    }
    private Node lowestNode(Node node) {
        if (node == nil) {
            return nil;
        }
        while (node.left != nil) {
            node = node.left;
        }
        return node;
    }

    public int highest() {
        return keyOrException(highestNode(root));
    }
    private Node highestNode(Node node) {
        if (node == nil) {
            return nil;
        }
        while (node.right != nil) {
            node = node.right;
        }
        return node;
    }

    public boolean insert(int key) {
        Node n = new Node(key, nil, nil, nil, Color.RED);
        return insertNode(n);
    }
    private boolean insertNode(Node node) {
        Node n = root;
        Node p = nil;
        while (n != nil) {
            p = n;
            if (node.key > n.key) {
                n = n.right;
            } else if (node.key < n.key) {
                n = n.left;
            } else {
                return false;
            }
        }
        node.parent = p;
        if (p == nil) {
            root = node;
        } else {
            if (node.key < p.key) {
                p.left = node;
            } else {
                p.right = node;
            }
        }
        fixAfterInsert(node);
        return true;
    }
    private void fixAfterInsert(Node z) {
        while (z.parent.color == Color.RED) {
            if (z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right;
                if (y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                Node y = z.parent.parent.left;
                if (y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    public boolean remove(int key) {
        Node n = searchNode(key);
        if (n == nil) {
            return false;
        } else {
            removeNode(n);
            return true;
        }
    }
    /*
    *  z is the node to be deleted
    *  y is z if z has less than 2 children, otherwise,
    *  y is z's successor which moves into z's position after delete
    *  x is the node moving to y's original position
    */
    private void removeNode(Node z) {
        Node y = z;
        Color original = y.color;
        Node x;
        if (z.left == nil) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == nil) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = lowestNode(z.right);
            original = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y; // x could be nil
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (original == Color.BLACK) {
            fixAfterDelete(x);
        }
    }
    private void fixAfterDelete(Node x) {
        while (x != root && x.color == Color.BLACK) {
            if (x == x.parent.left) {
                Node w = x.parent.right;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.right.color == Color.BLACK) {
                        w.left.color = Color.BLACK;
                        w.color = Color.RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.right.color = Color.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                Node w = x.parent.left;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;
                } else {
                    if (w.left.color == Color.BLACK) {
                        w.right.color = Color.BLACK;
                        w.color = Color.RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    w.left.color = Color.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = Color.BLACK;
    }

    private void transplant(Node u, Node v) {
        if (u.parent == nil) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        if (y.left != nil) {
                y.left.parent = x;
        }
        x.right = y.left;
        if (x.parent == nil) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.parent = x.parent;
        x.parent = y;
        y.left = x;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        if (y.right != nil) {
            y.right.parent = x;
        }
        x.left = y.right;
        if (x.parent == nil) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.parent = x.parent;
        x.parent = y;
        y.right = x;
    }

    public static void main(String[] args) {
        int[] values1 = new int[]{41, 38, 31, 12, 19, 8};
        RedBlackTree rbt = new RedBlackTree();
        for (int v : values1) {
            rbt.insert(v);
            rbt.printTree();
        }
        int[] values2 = new int[]{8, 12, 19, 31, 38, 41};
        for (int v : values2) {
            rbt.remove(v);
            rbt.printTree();
        }
    }
}
