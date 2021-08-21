import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Exercitiul1 {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);


        List<Integer> normal = new LinkedList<>();
        Queue<Integer> sum = new LinkedList<>();
        getInOrderQueue(root, normal);
        List<Integer> anormal = new LinkedList<>();
        ReplaceWithSum(normal, sum);

        replaceNode(root, sum);
        getInOrderQueue(root, anormal);
        System.out.println();
        anormal.stream().forEach(e -> System.out.print(e + " "));


    }


    static void getInOrderQueue(Node root, List<Integer> ints) {
        if (root == null) {
            return;
        }
        getInOrderQueue(root.left, ints);
        ints.add(root.value);
        getInOrderQueue(root.right, ints);
    }

    static void replaceNode(Node root, Queue<Integer> inOrderSum) {
        if (root == null) {
            return;
        }
        replaceNode(root.left, inOrderSum);
        root.value = inOrderSum.remove();
        replaceNode(root.right, inOrderSum);
    }

    static void ReplaceWithSum(List<Integer> normal, Queue<Integer> sum) {
        for (int i = 0; i <= normal.size() - 1; i++) {
            if (i == 0) {
                sum.add(normal.get(i + 1));
            } else if (i == normal.size() - 1) {
                sum.add(normal.get(i - 1));
            } else {
                sum.add(add(normal.get(i - 1), normal.get(i + 1)));
            }
        }
    }

    static int add(int z, int x) {
        return z + x;
    }


}

class Node {
    int value;
    Node left, right;

    public Node() {

    }

    public Node(int value) {
        this();
        this.value = value;
    }


}