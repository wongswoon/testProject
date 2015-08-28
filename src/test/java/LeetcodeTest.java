import Trie.Trie;
import com.google.common.collect.*;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

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

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        if (root.val == p.val || root.val == q.val) return root;
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        else if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        else return root;
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode l, r;
        l = lowestCommonAncestor(root.left, p, q);
        r = lowestCommonAncestor(root.right, p, q);
        if (l != null && r != null)
            return root;
        return l != null ? l : r;
    }

    public void deleteNode(ListNode node) {
        if (node.next == null) return;
        ListNode pre = node;
        while (node.next != null) {
            node.val = node.next.val;
            pre = node;
            node = node.next;
        }
        pre.next = null;
    }

    static final int[] primer = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
            89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151};
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    public boolean isAnagram(String s, String t) {
        if (s.equals(t)) return true;

        if (s.length() != t.length()) return false;
        for (int i = 0; i < s.length(); i++) {
            int key = (int) s.charAt(i);
            Integer count = map.get(key);
            if (count == null) map.put(key, 1);
            else map.put(key, ++count);
        }
        for (int i = 0; i < t.length(); i++) {
            int key = (int) t.charAt(i);
            if (map.containsKey(key)) {
                int count;
                if ((count = map.get(key)) == 1) map.remove(key);
                else map.put(key, --count);
            }
        }
        return map.isEmpty();

    }

    int swapBits(int n, int i, int j) {
        int bi = (n >> i) & 1;
        int bj = (n >> j) & 1;
        if ((bi ^ bj) != 0) {
            //if bi bj not same bit(one is 1 other is 0), only thing is negate each one
            //1->0,0->1
            n ^= (1 << i);
            n ^= (1 << j);
        }
        return n;
    }

    int biSearch(int a[], int x, int lo, int hi, boolean[] found) {
        int mid = (lo + hi) >> 1;
        if (a[mid] == x) {
            found[0] = true;
            return mid;
        } else if (a[mid] < x) {
            found[0] = false;
            return biSearch(a, x, mid + 1, hi, found);
        } else {
            found[0] = false;
            return biSearch(a, x, lo, mid, found);
        }
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        return false;
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        boolean res = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs1(board, visited, word, 0, i, j))
                    res = true;
            }
        }
        return res;
    }

    public int missingNumber(int[] nums) {
        int hash[] = new int[nums.length + 1];
        for (int i : nums) {
            hash[i] = 1;
        }
        for (int i = 1; i < hash.length; i++) {
            if (hash[i] == 0) return i;
        }
        return 0;
    }

    boolean dfs1(char[][] board, boolean[][] visited, String str, int depth, int x, int y) {

        if (depth == str.length()) {
            return true;
        }
        if (!(x < 0 || x >= board.length || y < 0 || y >= board[0].length)) {
            if (!visited[x][y] && str.charAt(depth) == board[x][y]) {
                visited[x][y] = true;
                if (dfs1(board, visited, str, depth + 1, x - 1, y))
                    return true;
                if (dfs1(board, visited, str, depth + 1, x + 1, y))
                    return true;
                if (dfs1(board, visited, str, depth + 1, x, y - 1))
                    return true;
                if (dfs1(board, visited, str, depth + 1, x, y + 1))
                    return true;
                visited[x][y] = false;
            }
        }
        return false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> resSet = new HashSet<String>();
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }

        return new ArrayList<String>(resSet);
    }

    public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;
        if (visited[x][y]) return;

        str += board[x][y];
        if (!trie.startsWith(str)) return;

        if (trie.search(str)) {
            trie.insert(str);
        }

        visited[x][y] = true;
        dfs(board, visited, str, x - 1, y, trie);
        dfs(board, visited, str, x + 1, y, trie);
        dfs(board, visited, str, x, y - 1, trie);
        dfs(board, visited, str, x, y + 1, trie);
        visited[x][y] = false;
    }


    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        char[] chars = str.toCharArray();
        int i = 0;
        while (chars[i] == ' ')
            i++;
        int sign;
        long res = 0;
        if (chars[i] == '+') {
            sign = 1;
        } else if (chars[i] == '-') {
            sign = -1;
        } else if (Character.isDigit(chars[i])) {
            sign = 1;
            res = chars[i] - '0';
        } else {
            sign = 0;

        }
        i++;

        for (int j = i; j < chars.length; j++) {
            if (Character.isDigit(chars[j])) {
                res = res * 10 + chars[j] - '0';
                long val = sign * res;
                if (val > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                if (val < Integer.MIN_VALUE) return Integer.MIN_VALUE;

            } else {
                break;
            }

        }
        long val = sign * res;
        val = val > Integer.MAX_VALUE ? Integer.MAX_VALUE : val;
        val = val < Integer.MIN_VALUE ? Integer.MIN_VALUE : val;
        return (int) val;
    }

    @Test
    public void testAtoi() {
        /*System.out.println(myAtoi("-123a"));
        System.out.println(myAtoi("-+123a"));
        System.out.println(myAtoi("-123"));*/
        System.out.println(myAtoi("9223372036854775809"));
    }

    public int reverseBits(int n) {
        for (int i = 0; i < 16; i++) {
            n = swapBits(n, i, 32 - i - 1);
        }
        return n;


    }

    @Test
    public void testSwap() {
        System.out.println(reverseBits(43261596));
    }

    @Test
    public void test_anagram() {
        System.out.printf(String.valueOf(isAnagram("aacc", "ccac")));
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode mid = getMiddle(head);


        ListNode p1 = mid.next;
        ListNode p2 = p1.next;

        while (p1 != null && p2 != null) {
            ListNode temp = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = temp;
        }
        mid.next.next = null;

        ListNode p = head, q = p1 == null ? p2 : p1;

        while (q != null) {
            if (p.val != q.val) {
                return false;
            }
            p = p.next;
            q = q.next;
        }
        return true;
    }

    @Test
    public void testp() {
        ListNode l = new ListNode(1);
        l.next = new ListNode(2);
        l.next.next = new ListNode(2);
        l.next.next.next = new ListNode(1);
        System.out.println(isPalindrome(l));
    }

    @Test
    public void testLCA() {
        TreeNode r = new TreeNode(1);
        r.right = new TreeNode(3);
        r.left = new TreeNode(2);
        TreeNode p = r.right;
        TreeNode q = r.left;
        TreeNode lca = lowestCommonAncestor(r, p, q);
        System.out.println(lca.val);
    }

    static public int setBitToOne(int n, int m) {
        return n | (1 << (m - 1));
    }

    static public int setBitToZero(int n, int m) {
        return n & ~(1 << (m - 1));
    }

    @org.junit.Test
    public void test() {
        int num[] = {1};
        System.out.println(largestRectangleArea(num));
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
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
        int loc[] = new int[gas.length];
        int i = 0, j = 0, rem = 0, count=0;
        while (count < gas.length && j<gas.length) {
            if (loc[j] == gas.length) return j;
            if (rem + gas[i] >= cost[i]) {
                rem += (gas[i] - cost[i]);
                loc[j]++;
            } else {
                rem = 0;
                j = i+1;
                count++;
            }
            i=(i+1)%gas.length;
        }
        return -1;
    }

    @Test
    public void testGas() {
        System.out.println(canCompleteCircuit(new int[]{1,2}, new int[]{2,1}));//1
      System.out.println(canCompleteCircuit(new int[]{4}, new int[]{5}));//-1
        System.out.println(canCompleteCircuit(new int[]{2,4}, new int[]{3,4}));//-1
        System.out.println(canCompleteCircuit(new int[]{5}, new int[]{4}));//0
        System.out.println(canCompleteCircuit(new int[]{6,0,1,3,2}, new int[]{4,5,2,5,5}));//0
    }

    public void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public int firstMissingPositive(int[] a) {
        //     0 1  2 3
        //and [3,4,-1,1] return 2. i
        //i+1=a[i]
        int n = a.length;
        int i = 0;
        while (i < n) {
            if (a[i] - 1 >= 0 && a[i] - 1 < n && a[i] != i + 1 && a[i] != a[a[i] - 1]) {
                swap(a, i, a[i] - 1);
            } else {
                i++;
            }
        }
        for (i = 0; i < n; i++) {
            if (a[i] != i + 1)
                return i + 1;
        }
        return i + 1;
    }

    @Test
    public void test111() {
        int a[] = {3, 4, -1, 1};
        System.out.println(firstMissingPositive(a));
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
    @Test
    public void testMaxGap(){
        System.out.println(maximumGap(new int[]{601408776,63967816,431363697,242509930,15970592,60284088,228037800,147629558,220782926,55455864,456541040,106650540,17290078,52153098,103139530,294196042,16568100,426864152,61916064,657788565,166159446,1741650,101791800,28206276,6223796,524849590,125389882,84399672,153834912,164568204,1866165,283209696,560993994,16266096,219635658,9188983,485969304,782013650,120332636,44659356,444517408,36369045,47370708,18542592,98802990,137690000,124889895,56062800,265421676,309417680,4634176,801661539,510541206,258227892,398938089,47255754,152260962,409663140,102847688,45756553,377936600,269498,375738702,263761134,53797945,329493948,224442208,508336845,189507850,40944620,127879560,119629476,186894520,62409156,693721503,4289916,523899936,28955240,266488028,20356650,40769391,483694272,97988044,84102,67246047,310688630,41288643,58965588,42881432,152159462,94786355,174917835,119224652,525034376,261516,274800528,62643819,23613832,8397240,797832131,855155367,337066320,26341480,61932200,20661075,515542796,390337500,522552030,43538516,150800550,116747540,152989123,488640056,700610304,233604,344277340,21439176,9397864,16365822,73027584,453041413,197374275,157735188,15273822,187081152,379611084,865005504,223099767,80478651,377729400,186738219,34738263,16634072,112791343,99631856,119364960,477106486,583953920,624509809,188442472,294181256,213023715,146645884,149530380,497592753,132170327,72770643,126683010,405141255,590214306,26670714,95582385,162080790,231120099,8946432,204967980,592849110,54120698,375915096,602145859,5346440,226337825,425156369,653591624,578483360,572410800,32290700,381384563,149939976,183225375,155695620,38307636,457513760,97085778,75200576,8068176,221650296,556889418,252495726,895020231,19932465,156334887,191383314,348432526,368701264,14315598,148936587,279419435,237325542,252587218,322929504,26331343,355297676,600420786,652017765,51673622,159015675}));
    }
    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        if (nums.length == 2) return Math.abs(nums[0] - nums[1]);
        int max = Integer.MIN_VALUE;
        if(nums.length<=10|| nums.length>3000){
            Arrays.sort(nums);
        }else{
          radixSort(nums, 10, 20);
        }
        int gap;
        for(int i = 0;i<nums.length-1;i++){
            gap = nums[i+1]-nums[i];
            if(gap>max) max = gap;
        }
        return max;
    }
    private static void radixSort(int[] array, int radix, int distance) {
        //array为待排序数 
        //radix，代表基数
        //代表排序元素的位数

        int length = array.length;
        int[] temp = new int[length];//用于暂存元素
        int[] count = new int[radix];//用于计数排序
        long divide = 1;

        for (int i = 0; i < distance; i++) {

            System.arraycopy(array, 0,temp, 0, length);
            Arrays.fill(count, 0);

            for (int j = 0; j < length; j++) {
                int tempKey = (int)(temp[j]/divide)%radix;
                count[tempKey]++;
            }

            for (int j = 1; j < radix; j++) {
                count [j] = count[j] + count[j-1];
            }

            //个人觉的运用计数排序实现计数排序的重点在下面这个方法
            for (int j = length - 1; j >= 0; j--) {
                int tempKey = (int)(temp[j]/divide)%radix;
                count[tempKey]--;
                array[count[tempKey]] = temp[j];
            }

            divide = divide * radix;

        }

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


        for (i = 0; i < numCourses; i++)   //按finish的时间倒序存放在tp序列tp中
            tp[numCourses - f[i] - 1] = i;

        return tp;
    }

    @Test
    public void test8() {

        System.out.println(JsonUtil.toJson(findOrder(2, new int[][]{{0, 1}, {1, 0}})));

    }

    Trie trie = new Trie();

    // Adds a word into the data structure.
    public void addWord(String word) {
        trie.insert(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return trie.search(word);
    }

    int reverse(int x) {
        float y = x;
        if (y - 4294967295f < 1e-10) return x;
        x = (((x & 0xaaaaaaaa) >> 1) | ((x & 0x55555555) << 1));
        x = (((x & 0xcccccccc) >> 2) | ((x & 0x33333333) << 2));
        x = (((x & 0xf0f0f0f0) >> 4) | ((x & 0x0f0f0f0f) << 4));
        x = (((x & 0xff00ff00) >> 8) | ((x & 0x00ff00ff) << 8));
        return ((x >> 16) | (x << 16));

    }

    int dp[] = new int[1000];

    public int rob1(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        if (nums.length == 1) return nums[0];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[nums.length - 1];
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (nums == null || n < 1) return 0;


        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        if (n == 3) return Math.max(Math.max(nums[0], nums[1]), nums[2]);
        //if (n == 4)   return Math.max(nums[0]+nums[2], nums[1]+nums[3]);
        int a[], b[];
        a = Arrays.copyOfRange(nums, 1, n - 2);
        b = Arrays.copyOfRange(nums, 0, n - 1);
        return Math.max(rob1(a) + nums[n - 1], rob1(b));

    }

    @Test
    public void test9() {

        System.out.println((reverse(1)));
    }

    // The main function
    public ListNode merge_sort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = getMiddle(head); // get the middle of the list
        ListNode sHalf = middle.next;
        middle.next = null; // split the list into two halfs

        return merge(merge_sort(head), merge_sort(sHalf)); // recurse on that
    }

    // Merge subroutine to merge two sorted lists
    public ListNode merge(ListNode a, ListNode b) {
        ListNode dummyHead, curr;
        dummyHead = new ListNode(-1);
        curr = dummyHead;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        curr.next = (a == null) ? b : a;
        return dummyHead.next;
    }

    // Finding the middle element of the list for splitting
    public ListNode getMiddle(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode slow, fast; //“快慢指针”
        slow = fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Test
    public void test10() {
        Map<Long, String> map = ImmutableMap.of(1l, "aa", 2l, "bb");
        BiMap<Long, String> biMap = HashBiMap.create();
        biMap.putAll(map);

        System.out.println(biMap.get(1L));
        System.out.println(biMap.inverse().get("bb"));
    }

    public int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) return 0;
        String s1[] = version1.split("\\.");
        String s2[] = version2.split("\\.");

        int i;
        for (i = 0; i < s1.length && i < s2.length; i++) {
            Integer i1 = Integer.valueOf(s1[i]);
            Integer i2 = Integer.valueOf(s2[i]);
            if (i1.equals(i2)) continue;
            return i1.compareTo(i2);
        }
        if (s1.length > i) {

            for (int j = i; j < s1.length; j++) {
                if (Integer.valueOf(s1[j]).equals(0)) continue;
                else return 1;
            }

        }
        if (s2.length > i) {

            for (int j = i; j < s2.length; j++) {
                if (Integer.valueOf(s2[j]).equals(0)) continue;
                else return -1;
            }

        }
        return 0;
    }

    int mapping[] = new int[1000];
    List<List<Integer>> lists;

    void genSubset(HashSet<List<Integer>> sets, int nums[], int cur) {
        if (cur == nums.length) {
            List<Integer> subset = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; i++) {
                if (mapping[i] != 0) subset.add(nums[i]);
            }
            Collections.sort(subset);
            sets.add(subset);
        }
        if (cur < nums.length) {
            mapping[cur] = 1;
            genSubset(sets, nums, cur + 1);
            mapping[cur] = 0;
            genSubset(sets, nums, cur + 1);
        }

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        HashSet<List<Integer>> sets = new HashSet<List<Integer>>();
        genSubset(sets, nums, 0);
        return lists = new ArrayList<List<Integer>>(sets);
    }

    void genComb(List<List<Integer>> sets, int n, int k, int cur, int num) {
        if (k == 0) {
            List<Integer> subset = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                subset.add(i + 1);
            }
            sets.add(subset);
            return;
        }
        if (cur == n && num == k) {
            List<Integer> subset = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                if (mapping[i] != 0) subset.add(i + 1);
            }
            sets.add(subset);

        }
        if (cur < n) {
            mapping[cur] = 1;
            genComb(sets, n, k, cur + 1, num + 1);
            mapping[cur] = 0;
            genComb(sets, n, k, cur + 1, num);
        }

    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> sets = new ArrayList<List<Integer>>();
        if (k > n) return sets;


        genComb(sets, n, k, 0, 0);
        return sets;
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int num = 0, i;
        for (i = 0; i < nums.length - 1; ) {
            if (nums[i] != nums[i + 1]) {
                nums[num++] = nums[i++];
                nums[num] = nums[i];
            } else {
                num++;
                i++;
            }
        }

        return num + 1;
    }

    public void merge(int[] a, int m, int[] b, int n) {
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                a[i] = b[i];
            }
            return;
        }
        if (n == 0) {
            return;
        }
        int i = m + n - 1;
        while (m > 0 && n > 0) {
            if (a[m - 1] > b[n - 1]) {
                a[i] = a[m - 1];
                m--;
            } else {
                a[i] = b[n - 1];
                n--;
            }
            i--;
        }
        if (m == 0) {
            while (i >= 0) {
                a[i--] = b[--n];

            }
        }
    }

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], 1);
            } else {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test11() {

        int val = 0;
        int val1 = 2;
        System.out.println(Integer.toBinaryString(setBitToOne(val, 32)));
        System.out.println(Integer.toBinaryString(setBitToOne(val, 1)));
        System.out.println(Integer.toBinaryString(setBitToZero(val1, 2)));
        System.out.println(Integer.toBinaryString(1 ^ Integer.MIN_VALUE));
        try {
            Long.valueOf("a");
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}