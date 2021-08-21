import java.util.*;


public class Exercitiul4 {
    public static void main(String[] args) {
        Node root = new Node(6);
        root.left = new Node(3);
        root.right = new Node(5);
        root.left.left = new Node(7);
        root.left.right = new Node(8);
        root.right.left = new Node(1);
        root.right.right = new Node(3);
        areCousins(root, 7, 1);
        areCousins(root, 3, 5);
        areCousins(root, 7, 5);


    }

    static void levelList(Node root, int level, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            list.add(root.value);
        }
        levelList(root.left, level - 1, list);
        levelList(root.right, level - 1, list);
    }

    static int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        int hLeft = getHeight(root.left);
        int hRight = getHeight(root.right);
        if (hLeft > hRight) {
            return hLeft + 1;
        } else {
            return hRight + 1;
        }
    }


    static List<List<Integer>> treeLevelList(Node root, int h) {
        List<List<Integer>> listList = new LinkedList<>();
        List<Integer> nodes = new LinkedList<>();
        for (int i = 0; i <= h; i++) {
            levelList(root, i, nodes);
            listList.add(nodes);
            nodes = new LinkedList<>();
        }
        return listList;
    }

    static boolean isParent(Node root, int left, int right) {
        boolean isParent = false;
        if (root == null) {
            return false;
        }
        if (root.left != null && root.right != null) {
            if (root.left.value == left && root.right.value == right) {
                /* linia urmatoare returneaza true daca root.left == left||right && root.right == left||right
                 */
                // if((root.left.value==left || root.right.value==left) && (root.right.value==right||root.right.value==left)){
                isParent = true;
            }
        }
        isParent(root.left, left, right);
        isParent(root.right, left, right);
        return isParent;
    }

    static boolean isOnSameLevel(int left, int right, List<List<Integer>> levels) {
        boolean isOnSameLevel = false;
        for (var list : levels) {
            if (isOnSameLevel) {
                break;
            }
            if (list.contains(left) && list.contains(right)) {
                isOnSameLevel = true;
            }
        }
        return isOnSameLevel;

    }

    static void areCousins(Node root, int left, int right) {
        boolean areCousins = false;
        boolean isSameParent = isParent(root, left, right);
        List<List<Integer>> levelValues = treeLevelList(root, getHeight(root));
        boolean areOnSameLevel = isOnSameLevel(left, right, levelValues);
        if ((!isSameParent) && areOnSameLevel) {
            areCousins = true;
        }
        System.out.println(left + " " + right + " " + areCousins);
    }
}
