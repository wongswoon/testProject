package Trie;

/**
 * Created by Lenovo on 15-5-12.
 */
class TrieNode {
    final static int maxnode = 40000;
    final static int sigma_size = 26;
    // Initialize your data structure here.
    int ch[][] = new int[maxnode][sigma_size];
    int val[] = new int[maxnode];
    int sz = 1; // 结点总数


    int idx(char c) {
        return c - 'a';
    } // 字符c的编号

    // 插入字符串s，附加信息为v。注意v必须非0，因为0代表“本结点不是单词结点”
    void insert(String s, int v) {
        int u = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            int c = idx(s.charAt(i));
            if (ch[u][c] == 0) { // 结点不存在
                //memset(ch[sz], 0, sizeof(ch[sz]));
                val[sz] = 0;  // 中间结点的附加信息为0
                ch[u][c] = sz++; // 新建结点
            }
            u = ch[u][c]; // 往下走
        }
        val[u] = v;
    }

    int find(String s)//查找s，返回权值
    {
        int u = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = idx(s.charAt(i));
            if (ch[u][c] == 0) return -1;
            u = ch[u][c];
        }
        return val[u];
    }

    int findRescure(String s, int u, int start) {
        if (start == s.length()) return val[u];
        int c = idx(s.charAt(start));
        if (ch[u][c] == 0) return -1;
        return findRescure(s, ch[u][c], start + 1);
    }

    int findWithDot(String s, int u, int start) {
        if (start == s.length()) {
            return val[u];
        }
        if (s.charAt(start) == '.') {
            for (int j = 0; j < 26; j++) {
                if (ch[u][j] == 0) continue;
                int res = findWithDot(s, ch[u][j], start + 1);
                if (res != -1 && res != 0)
                    return res;
            }
        } else {
            int c = idx(s.charAt(start));
            if (ch[u][c] == 0) {
                return -1;
            }
            return findWithDot(s, ch[u][c], start + 1);
        }
        return -1;
    }

    public TrieNode() {

    }
}
