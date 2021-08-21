import java.util.*;

public class Exercitiul2 {


    public static void main(String[] args) {
        int[] preorder = {40, 30, 35, 80, 100};
        int[] preorder1 = {40, 30, 32, 35, 80, 90, 100, 120};


        int root = preorder[0];
        quickSort(preorder, 0, preorder.length - 1);
        quickSort(preorder1, 0, preorder1.length - 1);

        int[] post = new int[preorder.length];
        int[] post2 = new int[preorder1.length];
        postOrder(preorder, post, root);
        Arrays.stream(post).forEach(e -> System.out.print(e + ", "));
        postOrder(preorder1, post2, root);
        System.out.println();
        Arrays.stream(post2).forEach(e -> System.out.print(e + ", "));


    }


    static void quickSort(int[] preOrder, int start, int end) {
        if (start < end) {
            int piv = getPartition(preOrder, start, end);
            quickSort(preOrder, start, piv - 1);
            quickSort(preOrder, piv + 1, end);
        }

    }

    private static int getPartition(int[] preOrder, int start, int end) {
        int piv = preOrder[end];
        int i = start - 1;
        for (int j = start; j <= preOrder.length - 1; j++) {
            if (preOrder[j] < piv) {
                i++;
                change(preOrder, i, j);
            }
        }
        change(preOrder, i + 1, end);
        return i + 1;
    }

    private static void change(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static Stack<Integer> left(int[] inOrder, int root) {
        Stack<Integer> leftS = new Stack<>();
        int poz = binarrySearch(inOrder, root, 0, inOrder.length - 1);
        for (int i = poz - 1; i >= 0; i--) {
            leftS.add(inOrder[i]);
        }
        return leftS;
    }

    private static Queue<Integer> right(int[] inOrder, int root) {
        Queue<Integer> rightQ = new LinkedList<>();
        int poz = binarrySearch(inOrder, root, 0, inOrder.length - 1);
        for (int i = poz + 1; i <= inOrder.length - 1; i++) {
            rightQ.add(inOrder[i]);
        }
        return rightQ;
    }

    private static int binarrySearch(int[] arr, int value, int start, int end) {
        int mid = (start + end) / 2;
        if (start > end) {
            return -1;
        }
        if (arr[mid] == value) {
            return mid;
        }
        if (value > mid) {
            return binarrySearch(arr, value, mid + 1, end);
        }
        return binarrySearch(arr, value, start, mid - 1);
    }

    private static void postOrder(int[] pre, int[] post, int root) {
        Queue<Integer> right = right(pre, root);
        Stack<Integer> left = left(pre, root);
        post[post.length - 1] = root;
        for (int i = post.length - 2; i >= 0; i--) {
            while (!right.isEmpty()) {
                post[i] = right.remove();
                i--;
            }
            while (!left.isEmpty()) {
                post[i] = left.pop();
                i--;
            }
        }
    }
}
