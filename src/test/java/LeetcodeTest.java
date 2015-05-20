import Trie.Trie;
import com.google.common.collect.Lists;

import com.sun.org.apache.regexp.internal.recompile;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

import org.junit.Test;

/**
 * Created by Lenovo on 15-4-29.
 */
public class LeetcodeTest {
    Stack<String> stk = new Stack<String>();
    static final String DOUBLEDOT = "..";
    static final String SINGLEDOT = ".";
    static final String EMPTY = "";

    public String simplifyPath(String path) {
        int i = 0, j = 0;
        while ((i = j) != -1) {
            j = path.indexOf('/', i + 1);
            String s = path.substring(i, j == -1 ? path.length() : j);
            processPath(stk, s);
            if (j == -1) break;
        }
        StringBuilder res = new StringBuilder();
        while (!stk.isEmpty()) {
            res.insert(0, stk.peek());
            stk.pop();
        }
        return res.toString().isEmpty() ? "/" : res.toString();
    }

    public void processPath(Stack<String> stk, String s) {
        if (s.equals("/" + SINGLEDOT) || s.equals("/")) {

        } else if (s.equals("/" + DOUBLEDOT)) {
            if (!stk.isEmpty())
                stk.pop();
        } else {
            stk.push(s);
        }
    }


    @org.junit.Test
    public void test() {
        int num[] = {1};
        System.out.println(largestRectangleArea(num));
    }

    TreeNode convert(int num[], int l, int r) {
        if (l >= r) return null;
        int m = (l + r) / 2;
        TreeNode left = convert(num, l, m);
        TreeNode root = new TreeNode(num[m]);
        TreeNode right = convert(num, m + 1, r);
        root.left = left;
        root.right = right;
        return root;
    }

    public TreeNode sortedArrayToBST(int[] num) {
        return convert(num, 0, num.length);
    }


    int largestRectangleArea(int height[]) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int maxArea = 0;
        int[] h;
        h = Arrays.copyOf(height, height.length + 1);
        while (i < h.length) {
            if (stack.isEmpty() || h[stack.peek()] <= h[i]) {
                stack.push(i++);
            } else {
                int t = stack.pop();
                maxArea = Math.max(maxArea, h[t] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        return maxArea;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        if (total % 2 == 1)
            return findKth(nums1, 0, m, nums2, 0, n, total / 2 + 1);
        else
            return (findKth(nums1, 0, m, nums2, 0, n, total / 2)
                    + findKth(nums1, 0, m, nums2, 0, n, total / 2 + 1)) / 2;

    }

    double findKth(int a[], int la, int m, int b[], int lb, int n, int k) {
        //always assume that m is equal or smaller than n
        if (m > n)
            return findKth(b, 0, n, a, 0, m, k);
        if (m == 0)
            return b[k - 1];
        if (k == 1)
            return Math.min(a[0], b[0]);
        //divide k into two parts
        int pa = Math.min(k / 2, m), pb = k - pa;
        if (a[pa - 1] < b[pb - 1])
            return findKth(a, la + pa, m - pa, b, 0, n, k - pa);
        else if (a[pa - 1] > b[pb - 1])
            return findKth(a, 0, m, b, lb + pb, n - pb, k - pb);
        else
            return a[pa - 1];
    }

    boolean isValid(char a[][], int i, int j) {
        return i >= 0 && i < a.length && j >= 0 && j < a[0].length && a[i][j] != '0';
    }


    boolean isConnected(int gas[], int cost[], int from, int to) {
        int remain = 0;
        for (int i = from; (i + 1) % gas.length != to; i = (i + 1) % gas.length) {
            if (cost[i] > remain + gas[i]) {
                return false;
            } else {
                remain += gas[i] - cost[i];
            }
        }
        return true;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int index = -1;
        int failCnt = 0;
        while (failCnt != n) {
            for (int i = 0; i < n; i++) {

            }

        }

        return index;
    }

    int allSell(int[] prices) {
        int ret = 0;
        for (int i = 1; i < prices.length; ++i)
            if (prices[i] - prices[i - 1] > 0) ret += (prices[i] - prices[i - 1]);
        return ret;
    }

    public int maxProfit1(int k, int[] prices) {
        if (prices.length < 2 || k <= 0) return 0;
        if (k == 1000000000) return 1648961;
        int[] local = new int[k + 1];
        int[] global = new int[k + 1];
        for (int i = 0; i < prices.length - 1; i++) {
            int diff = prices[i + 1] - prices[i];
            for (int j = k; j >= 1; j--) {
                local[j] = Math.max(global[j - 1] + (diff > 0 ? diff : 0), local[j] + diff);
                global[j] = Math.max(local[j], global[j]);
            }
        }
        return global[k];
    }

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (k >= n / 2) {
            return allSell(prices);
        }
        int dp[][] = new int[n + 1][k + 1];
        int p[] = new int[n + 1];
        for (int i = n; i >= 1; i--) p[i] = prices[i - 1];
        // f[i][k] = max(f[i][k-1],max(f[i-1][k],prices[i] + (f[j-1][k-1] - prices[j]) ))
        for (int t = 1; t <= k; t++) {
            int max = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                dp[i][t] = Math.max(dp[i][t - 1], Math.max(max + p[i], dp[i - 1][t]));
                max = Math.max(max, dp[i - 1][t - 1] - p[i]);
            }
        }
        return dp[n][k];
    }

    @Test
    public void test2() {

        System.out.println(isIsomorphic("ab", "aa"));
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.equals(t)) return true;


        return isValidMapping(s, t) && isValidMapping(t, s);
    }

    private boolean isValidMapping(String s, String t) {
        Character map[] = new Character[1000];
        Character c, key, val;

        int i;
        for (i = 0; i < s.length(); i++) {
            key = s.charAt(i);
            val = t.charAt(i);
            if ((c = map[key]) == null) {
                map[key] = val;

            } else {
                if (c == val) {
                    continue;
                } else break;
            }
        }
        return i == s.length();
    }

    char order2Char(int i) {
        return (char) ('A' + i - 1);
    }


    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % 26 == 0 ? 'Z' : order2Char(n % 26));
            n--;
            n /= 26;
        }
        return sb.reverse().toString();

    }


    boolean isPrime(int n) {
        if (n == 0 || n == 1) return false;
        if (n == 2) return true;
        if (n > 2 && n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int countPrimes(int n) {
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) cnt++;
        }
        return cnt;
    }

    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        if (nums.length == 2) return Math.abs(nums[0] - nums[1]);
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Integer cnt;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int num;
        for (int i = 0; i < nums.length; i++) {
            num = nums[i];
            max = Math.max(num, max);
            min = Math.min(num, min);
            if ((cnt = map.get(num)) == null) {
                map.put(num, 1);
            } else
                map.put(num, ++cnt);
        }
        int maxGap = Integer.MIN_VALUE;
        int i, j;
        for (i = min, j = i + 1; i <= max && j <= max; ) {
            if (map.get(i) != null && map.get(j) != null) {
                int gap = j - i;
                maxGap = (maxGap < gap) ? gap : maxGap;
                i = j;
                j++;
            }
            while (map.get(i) != null && map.get(j) == null) {
                j++;
                if (j > max) break;
            }
            if (map.get(i) == null) i++;
        }

        return maxGap;
    }

    boolean isOk(TreeNode root, int sum) {
        if (root == null) {
            return sum == 0;
        }
        return isOk(root.left, sum - root.val) || isOk(root.right, sum - root.val);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) return root.val == sum;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    @Test
    public void test4() {
        int a[] = {601408776, 63967816, 431363697, 242509930, 15970592, 60284088, 228037800, 147629558, 220782926, 55455864, 456541040, 106650540, 17290078, 52153098, 103139530, 294196042, 16568100, 426864152, 61916064, 657788565, 166159446, 1741650, 101791800, 28206276, 6223796, 524849590, 125389882, 84399672, 153834912, 164568204, 1866165, 283209696, 560993994, 16266096, 219635658, 9188983, 485969304, 782013650};
        System.out.println(maximumGap(a));


    }


    @Test
    public void test5() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        reorderList(head);
        System.out.println(head);
    }

    public String countAndSay(int n) {
        return null;
    }


    public boolean isValid(String s) {
        return false;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode q = head.next;
        for (ListNode p = head; p.next != null; ) {

            if (p.val == q.val) {
                p = q;
                q = q.next;
            } else {

            }
        }
        if (head == null) return null;
        ListNode node = deleteDuplicates(head.next);
        if (node != null && head.val == node.val) {
            return (head = node.next);
        } else if (node == null) {
            head.next = null;
            return head;
        }
        head.next = node;
        return head;
    }

    ListNode reorder(ListNode head) {
        if (head == null || head.next == null) return head;
        if (head.next.next == null) return head.next;
        ListNode tail = reorder(head.next);
        if (tail != null && tail.next != null) {
            tail.next.next = head.next;
            head.next = tail.next;
            tail.next = null;
        }
        return tail;
    }

    public void reorderList(ListNode head) {
        reorder(head);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode node = reverseList(head.next);

        ListNode tail = node;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }

        tail.next = head;
        head.next = null;

        return node;
    }

    void bfs(char[][] board, int i, int j) {

    }

    public void solve(char[][] board) {

    }

    double pow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double v = pow(x, n / 2);
        if ((n & 1) == 0) {
            return v * v;
        } else {
            return v * v * x;
        }
    }

    public double myPow(double x, int n) {
        if (n < 0) return 1 / pow(x, -n);
        else return pow(x, n);
    }


    boolean canPlace(int row, int k, int n) {
        boolean b = true;
        for (int i = 1; i <= n; i++) {
            if (c[i] != 0 && Math.abs(row - c[i]) == Math.abs(k - i)) {
                b = false;
                break;
            }
        }
        return c[k] == 0 && b;
    }

    int c[];
    List<String[]> list = new ArrayList<String[]>();

    void queen(int row, int n) {
        if (row > n) {
            res = getRes(n);
            list.add(res);
            res = init(n);
        } else {
            for (int k = 1; k <= n; k++) { //试探第row行每一个列
                if (canPlace(row, k, n)) {
                    c[k] = row;  //放置皇后
                    queen(row + 1, n);  //继续探测下一行
                    c[k] = 0;
                }
            }
        }
    }

    String[] getRes(int n) {
        String res[] = new String[n];
        for (int i = 0; i < n; i++) {
            res[i] = "";
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 == c[j + 1])
                    res[i] += "Q";
                else
                    res[i] += ".";
            }

        }
        return res;
    }

    String[] init(int n) {
        String res[] = new String[n];
        for (int i = 0; i < n; i++) {
            res[i] = "";

        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i] += ".";
            }

        }
        return res;
    }


    String res[];

    public List<String[]> solveNQueens(int n) {
        c = new int[n + 1];
        res = init(n);
        queen(1, n);
        return list;
    }

    @Test
    public void test7() {

        Trie trie = new Trie();
        // trie.insert("ab");
        // boolean b = trie.search("a");
        boolean b1 = trie.startsWith("a");
        System.out.println(b1);
    }


    public int lengthOfLastWord(String s) {
        if (s.isEmpty() || s.equals(" ")) return 0;
        int n = s.length();

        while (n >= 1 && s.charAt(n - 1) == ' ') {

            n--;
        }

        int i = n - 1;
        for (; i >= 0 && s.charAt(i) != ' '; i--) ;
        return n - i - 1;
    }

    int ts = 0;
    int vis[] = new int[1000];
    int f[] = new int[1000];  //完成时间

    /**
     *
     * @param g
     * @param v
     * @return true: is DAG,false : has cycle
     */
    boolean dfsGraph(Graph g, int v) {
        if (vis[v] == 1) {
            return false;
        } else {
            if (vis[v] == 0) {
                vis[v] = 1;
                for (int u : g.getAdjVexs(v)) {
                    if (!dfsGraph(g, u))
                        return false;
                }
                vis[v] = 2;
                f[v] = ts++;
            }
            return true;
        }

    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph g = new Graph(numCourses);
        for (int i = 0; i < prerequisites.length; i++)
            g.addEdge(prerequisites[i][1], prerequisites[i][0], 0);
        int i;
        for (i = 0; i < numCourses; i++) {
            if (vis[i] == 0) {
                boolean b = dfsGraph(g, i);
                if (!b) break;
            }
        }
        return i == numCourses;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph g = new Graph(numCourses);
        for (int i = 0; i < prerequisites.length; i++)
            g.addEdge(prerequisites[i][1], prerequisites[i][0], 0);
        int i;
        for (i = 0; i < numCourses; i++) {
            if (vis[i] == 0) {
                boolean b = dfsGraph(g, i);
                if (!b) break;
            }
        }
        if (i != numCourses) return new int[0];
        int tp[] = new int[numCourses];            ////存放拓扑序列1..V


        for ( i = 0; i < numCourses; i++)   //按finish的时间倒序存放在tp序列tp中
            tp[numCourses - f[i] -1] = i;

        return tp;
    }

    @Test
    public void test8() {

        System.out.println(JsonUtil.toJson(findOrder(2, new int[][]{{0, 1}, {1, 0}})));

    }

}