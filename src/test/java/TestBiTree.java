import com.google.common.collect.Collections2;
import com.sun.org.apache.regexp.internal.recompile;
import org.junit.*;
import sun.net.www.content.text.plain;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created by Lenovo on 15-4-25.
 */
public class TestBiTree {
    TreeNode root = null;

    @Before
    public void init() {

    }


    int heightSum(TreeNode root) {
        if (root == null) return 0;
        return Math.max(heightSum(root.left), heightSum(root.right)) + root.val;
    }

    public int maxPathSum(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;
        int lsum = heightSum(root.left);
        int rsum = heightSum(root.right);
        int rootSum = Math.max(lsum, 0) + Math.max(rsum, 0) + root.val;
        int max = Math.max(lsum, rsum);
        return rootSum > max ? rootSum : max;

    }


    @org.junit.Test
    public void test1() {
        root = new TreeNode(1);
        root.right = new TreeNode(2);

        root.right.right = new TreeNode(3);

        System.out.println(maxPathSum(root));
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int max, sum;
        max = sum = 0;
        int d[] = new int[prices.length - 1];
        for (int i = 0; i < prices.length - 1; i++) {
            d[i] = prices[i + 1] - prices[i];
        }
        for (int i = 0; i < d.length; i++) {
            if (sum > 0) {
                sum += d[i];

            } else {
                sum = d[i];
            }
            max = Math.max(max, sum);
        }

        return max;
    }

    public int titleToNumber(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum *= 26;
            sum += (s.charAt(i) - 'A' + 1);
        }
        return sum;
    }

    public int comp(String a, String b) {
        if (a.equals(b)) return 0;
        int res = 0;
        for (int i = 0; i < a.length() && i < b.length(); i++) {
            if (a.charAt(i) > b.charAt(i)) {
                res = 1;
                break;
            } else if (a.charAt(i) < b.charAt(i)) {
                res = -1;
                break;
            } else {
                continue;
            }
        }
        if (res == 0) {
            return a.length() > b.length() ? 1 : -1;
        }
        return res;
    }

    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < nums.length; i++) {
            list.add(String.valueOf(nums[i]));
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                if (a.equals(b)) return 0;
                int res = 0;
                int i;
                for (i = 0; i < a.length() && i < b.length(); i++) {
                    if (a.charAt(i) > b.charAt(i)) {
                        res = -1;
                        break;
                    } else if (a.charAt(i) < b.charAt(i)) {
                        res = 1;
                        break;
                    } else {
                        continue;
                    }
                }
                if (res == 0) {
                    return b.concat(a).compareTo(a.concat(b));

                }
                return res;
            }
        });

        String res = "";
        boolean isHead0 = true;
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.equals("0") && isHead0) {
                continue;
            } else {
                res += list.get(i);
                isHead0 = false;
            }
        }
        return res == "" ? "0" : res;
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null || head.val == val && head.next == null) return null;
        if (head.val == val) {
            return removeElements(head.next, val);
        } else {
            head.next = removeElements(head.next, val);
        }
        return head;
    }


    List<Integer> trans(int n) {
        Stack<Integer> stack = new Stack<Integer>();
        while (n > 0) {
            stack.add(n % 10);
            n /= 10;
        }
        return stack;
    }

    HashSet<Integer> set = new HashSet<Integer>();

    public boolean isHappy(int n) {

        if (n == 1) return true;

        List<Integer> nums = trans(n);
        int m = 0;
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i);
            m += val * val;

        }
        if (set.contains(m)) return false;
        else set.add(m);
        return isHappy(m);
    }

    int leftMax = Integer.MIN_VALUE, rightMin = Integer.MAX_VALUE;

    public static boolean isValidBST(TreeNode root) {
        return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean validate(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }

        // not in range
        if (root.val <= min || root.val >= max) {
            return false;
        }

        // left subtree must be < root.val && right subtree must be > root.val
        return validate(root.left, min, root.val) && validate(root.right, root.val, max);
    }


    public List<TreeNode> generateTrees(int l, int r) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        if (l > r) {
            list.add(null);
            return list;
        }
        for (int i = l; i <= r; i++) {
            List<TreeNode> lefts = generateTrees(l, i - 1);
            List<TreeNode> rights = generateTrees(i + 1, r);

            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;
    }

    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    @org.junit.Test
    public void test2() {

        for (TreeNode t : generateTrees(3)) {
            TreeNode.inOrderWalk(t);
            System.out.println();
        }
    }
}
