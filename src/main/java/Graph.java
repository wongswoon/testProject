import java.util.*;

/**
 * Created by Lenovo on 15-4-23.
 * 图数据结构及算法类
 */
public class Graph {
    //顶点数
    int vexNum;
    //边数
    int edgeNum;
    //边集
    List<Edge> edges;
    //放边的索引号，从0开始
    List<Integer> vex[];
    //该顶点是否被访问过
    boolean done[];
    //到各点最短距离集合
    int dvex[];
    //结果计数
    int cnt = 0;

    class Edge {
        int from;
        int to;
        //距离
        int dist;

        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }
    //求最短路放优先队列中以距离为权的节点
    static class GraphNode implements Comparable<GraphNode> {
        int d;
        int u;
        GraphNode(int d, int u) {
            this.d = d;
            this.u = u;
        }

        @Override
        public int compareTo(GraphNode o) {
            return ((Integer) this.d).compareTo(o.d);
        }
    }

    public Graph(int n) {
        vexNum = n;
        vex = new List[n];
        for (int i = 0; i < n; i++) {
            vex[i] = new ArrayList<Integer>();
        }
        edges = new ArrayList<Edge>();
    }

    public void addEdge(int from, int to, int dist) {
        edges.add(new Edge(from, to, dist));
        edgeNum = edges.size();
        vex[from].add(edgeNum - 1);
    }

    public static int char2Int(char ch) {
        return ch - 'A';
    }

    public static char int2Char(int i) {
        return (char) ('A' + i);
    }

    /**
     * dijktra算法求start节点到所有节点的最短路
     * @param start
     */
    public void dijkstra(int start) {
        dvex = new int[vexNum];
        Queue<GraphNode> queue = new PriorityQueue<GraphNode>();
        for (int i = 0; i < vexNum; i++) {
            dvex[i] = Integer.MAX_VALUE;
        }
        dvex[start] = 0;
        done = new boolean[vexNum];
        queue.add(new GraphNode(0, start));
        while (!queue.isEmpty()) {
            GraphNode x = queue.peek();
            queue.remove();
            int u = x.u;

            if (done[u]) continue;
            done[u] = true;
            for (int i = 0; i < vex[u].size(); i++) {
                Edge e = edges.get(vex[u].get(i));
                if (dvex[e.to] > dvex[u] + e.dist) {
                    dvex[e.to] = dvex[u] + e.dist;
                    queue.add(new GraphNode(dvex[e.to], e.to));
                }
            }
        }
    }

    /**
     * 获取v的所有邻接节点
     * @param v
     * @return key 节点编号，value 距离
     */
    Map<Integer, Integer> getAdjVexsDist(int v) {
        Map<Integer, Integer> adjVexMap = new HashMap<Integer, Integer>();
        for (int i : vex[v]) {
            adjVexMap.put(edges.get(i).to, edges.get(i).dist);
        }
        return adjVexMap;
    }
    List<Integer> getAdjVexs(int v){
        List<Integer> adjVexs = new ArrayList<Integer>();
        for (int i : vex[v]) {
            adjVexs.add(edges.get(i).to);
        }
        return  adjVexs;
    }
    /**
     * 沿着str查找并计算路径长度之和
     * @param str
     */
    public void findDist(String str) {
        int sum = 0;
        boolean isFind = false;
        for (int i = 0; i < str.length() - 1; i++) {
            isFind = false;
            for (int j : vex[char2Int(str.charAt(i))]) {
                if (edges.get(j).to == char2Int(str.charAt(i + 1))) {
                    sum += edges.get(j).dist;
                    isFind = true;
                    break;
                }
            }
            if (!isFind) {
                System.out.println("NO SUCH ROUTE");
                break;
            }
        }
        if (isFind)
            System.out.println(sum);
    }


    void zeroEnd(char ch[], int begin) {
        for (int i = begin; i < ch.length; i++) {
            ch[i] = '\0';
        }
    }

    /**
     * 深度遍历有向图
     * @param param
     * @param out
     */
    public void dfsGraph(Param param, char[] out) {
        if (param.getConditionEnum().isFailed(param)) {
            return;
        } else {

            if (param.getConditionEnum().isGoal(param)) {
                cnt++;
                out[param.getDepth()] = int2Char(param.getStart());
                zeroEnd(out, param.getDepth() + 1);
                //System.out.println(JsonUtil.toJson(out));

            }
            out[param.getDepth()] = int2Char(param.getStart());
            for (int next : getAdjVexsDist(param.getStart()).keySet()) {
                dfsGraph(new Param(next, param.getEnd(), param.getDist() + getAdjVexsDist(param.getStart()).get(next),
                        param.getDepth() + 1, param.getConditionEnum()), out);
            }
        }
    }
}
