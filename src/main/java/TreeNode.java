import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 15-4-22.
 */
public class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    public TreeNode(int x) {
        this.val = x;
    }


    static void preOrderWalk(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + ",");
            preOrderWalk(root.left);
            preOrderWalk(root.right);

        }

    }

    static void inOrderWalk(TreeNode root) {
        if (root != null) {
            inOrderWalk(root.left);
            System.out.print(root.val + ",");
            inOrderWalk(root.right);

        }

    }

    static void postOrderWalk(TreeNode root) {
        if (root != null) {
            postOrderWalk(root.left);
            postOrderWalk(root.right);
            System.out.print(root.val + ",");
        }

    }

    static int getRootIndexInOrder(int inorder[], int root, int l, int r) {
        while (l <= r) {
            if (inorder[l] != root)
                l++;
            else break;
        }
        return l > r ? -1 : l;
    }

    static TreeNode create(int preorder[], int inorder[], int l1, int r1, int l2, int r2) {
        if (l1 > r1) return null;
        int root = preorder[l1];
        int index = getRootIndexInOrder(inorder, root, l2, r2);
        TreeNode rootNode = new TreeNode(root);

        TreeNode lchild = create(preorder, inorder, l1 + 1, l1 + index - l2, l2, index - 1);
        TreeNode rchild = create(preorder, inorder, l1 + index - l2 + 1, r1, index + 1, r2);

        rootNode.left = lchild;
        rootNode.right = rchild;
        return rootNode;
    }


    static public TreeNode buildTree(int[] preorder, int[] inorder) {
        return create(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    static int[] preorder = {1, 2, 4, 5, 3, 6, 7};
    static int[] inorder = {4, 2, 5, 1, 6, 7, 3};

    static boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        else if (right == null) {
            return false;
        } else if (left == null) {
            return false;
        } else {
            if (left.val != right.val) return false;

            return isMirror(left.right, right.left) && isMirror(left.left, right.right);
        }
    }

    static public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (q == null) {
            return false;
        } else if (p == null) {
            return false;
        } else {
            if (p.val != q.val) return false;
            return isSameTree(p.right, q.right) && isSameTree(p.left, q.left);
        }
    }

    public static void main(String[] args) {
      /*  TreeNode root = buildTree(preorder, inorder);
        preOrderWalk(root);
        System.out.println();

        inOrderWalk(root);
        System.out.println();
        postOrderWalk(root);*/

        //System.out.println(numTrees(19));
        String str[] = {"dog", "a", "fuck", "god", "odg"};
        System.out.println(JsonUtil.toJson(anagrams(str)));
    }

    static public int numTrees(int n) {
        return (int) (comb(2 * n, n) / (n + 1));

    }

    static long comb(int n, int m) {
        if (m == 0)
            return 1;
        else if (m > n)
            return 0;
        else {
            long c = 1;
            for (long i = 1; i <= n - m; i++)
                c = c * (i + m) / i;
            return c;
        }
    }


    static int prime[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

    static public int mapping(String s) {
        int val = 1;
        for (int i = 0; i < s.length(); i++) {
            val *= prime[s.charAt(i) - 'a'];
        }
        return val;
    }

    static public List<String> anagrams(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        for (int i = 0; i < strs.length; i++) {
            if (map.get(mapping(strs[i])) == null) {
                List<String> list = new ArrayList<String>();
                list.add(strs[i]);
                map.put(mapping(strs[i]), list);
            } else {
                map.get(mapping(strs[i])).add(strs[i]);
            }

        }
        List<String> ansList = new ArrayList<String>();
        for (int key : map.keySet()) {
            if (map.get(key).size() > 1) {
                ansList.addAll(map.get(key));
            }
        }
        return ansList;
    }

    static int max3(int a, int b, int c) {
        return a > b ? (a > c ? a : c) : (b > c ? b : c);
    }

    static int max2(int a, int b) {
        return a > b ? a : b;
    }

    static int heightSum(TreeNode root) {
        if (root == null) return 0;
        return max2(heightSum(root.left) + root.val, heightSum(root.left) + root.val);
    }

    static int maxDis = 0;

    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        else if (root.left == null && root.right == null)
            return root.val;
        int dis = max3(heightSum(root.left) + heightSum(root.right), maxPathSum(root.left), maxPathSum(root.right)) + root.val;
        if (maxDis < dis)
            maxDis = dis;
        return dis;
    }

}
