package com.alex.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caozhennan on 2020/8/12 9:43 上午.
 */
public class Cloner {
    Map<Integer, Node> cache = new HashMap<>();

    public Node clone(Node node) {
        if (node == null) {
            return null;
        }
        Node c = cache.get(node.val);
        if (c == null) {
            c = new Node(node.val);
            cache.put(c.val, c);
            for (Node n : node.neighbors) {
                c.neighbors.add(clone(n));
            }
        }
        return c;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        n1.neighbors.add(n2);
        Node c = new Cloner().clone(n1);
        System.out.println(c.val);
        for (Node n : c.neighbors) {
            System.out.printf("\t%d\n", n.val);
        }
    }
}
