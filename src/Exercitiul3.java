
public class Exercitiul3 {
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(8);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.right = new Node(2);
        root.right.left = new Node(2);
        postOrder(root);
        int x = postOrder(root);
        System.out.println(x);
        System.out.println(getBool(x, root.value));
    }

    static int postOrder(Node root) {
        if (root == null) {
            return root.value;
        }
        if (root.left == null && root.right == null) {
            return root.value;
        }
        if (root.left == null && root.right != null) {
            return root.right.value;
        }
        if (root.left != null && root.right == null) {
            return root.left.value;
        }
        int left = postOrder(root.left);
        int right = postOrder(root.right);

        return left + right;
    }

    static boolean getBool(int x, int y) {
        return x == y;
    }
}
