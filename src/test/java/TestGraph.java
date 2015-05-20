import org.junit.Before;

/**
 * Created by Lenovo on 15-4-24.
 * 测试类
 */
public class TestGraph {
    Graph g = new Graph(5);
    Graph g1 = new Graph(5);

    @Before
    public void init() {
        //Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
        g.addEdge(Graph.char2Int('A'), Graph.char2Int('B'), 5);
        g.addEdge(Graph.char2Int('B'), Graph.char2Int('C'), 4);
        g.addEdge(Graph.char2Int('C'), Graph.char2Int('D'), 8);
        g.addEdge(Graph.char2Int('D'), Graph.char2Int('C'), 8);
        g.addEdge(Graph.char2Int('D'), Graph.char2Int('E'), 6);
        g.addEdge(Graph.char2Int('A'), Graph.char2Int('D'), 5);
        g.addEdge(Graph.char2Int('C'), Graph.char2Int('E'), 2);
        g.addEdge(Graph.char2Int('E'), Graph.char2Int('B'), 3);
        g.addEdge(Graph.char2Int('A'), Graph.char2Int('E'), 7);

        //反向建图
        g1.addEdge(Graph.char2Int('B'), Graph.char2Int('A'), 5);
        g1.addEdge(Graph.char2Int('C'), Graph.char2Int('B'), 4);
        g1.addEdge(Graph.char2Int('D'), Graph.char2Int('C'), 8);
        g1.addEdge(Graph.char2Int('C'), Graph.char2Int('D'), 8);
        g1.addEdge(Graph.char2Int('E'), Graph.char2Int('D'), 6);
        g1.addEdge(Graph.char2Int('D'), Graph.char2Int('A'), 5);
        g1.addEdge(Graph.char2Int('E'), Graph.char2Int('C'), 2);
        g1.addEdge(Graph.char2Int('B'), Graph.char2Int('E'), 3);
        g1.addEdge(Graph.char2Int('E'), Graph.char2Int('A'), 7);
    }

    @org.junit.Test
    public void test1_5() {

/**
 * The distance of the route A-B-C.
 The distance of the route A-D.
 The distance of the route A-D-C.
 The distance of the route A-E-B-C-D.
 The distance of the route A-E-D.
 */
        g.findDist("ABC");
        g.findDist("AD");
        g.findDist("ADC");
        g.findDist("AEBCD");
        g.findDist("AED");


    }

    @org.junit.Test
    public void test6() {
        g.cnt = 0;
        Param param = new Param.ParamBuilder()
                .setCondition(ConditionEnum.MAX_3_STOPS)
                .setStart(Graph.char2Int('C'))
                .setEnd(Graph.char2Int('C'))
                .setDepth(0).build();
        g.dfsGraph(param, new char[4]);
        System.out.println(g.cnt);

    }

    @org.junit.Test
    public void test7() {
        g.cnt = 0;
        Param param = new Param.ParamBuilder()
                .setCondition(ConditionEnum.EXACT_4_STOPS)
                .setStart(Graph.char2Int('A'))
                .setEnd(Graph.char2Int('C'))
                .setDepth(0).build();
        g.dfsGraph(param, new char[5]);
        System.out.println(g.cnt);

    }

    @org.junit.Test
    public void test8() {
        g.dijkstra(Graph.char2Int('A'));
        System.out.println(g.dvex[Graph.char2Int('C')]);
    }

    @org.junit.Test
    public void test9() {
        g.dijkstra(Graph.char2Int('B'));
        g1.dijkstra(Graph.char2Int('B'));
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < g.vexNum; i++) {
            if (i != Graph.char2Int('B')) {
                if (g.dvex[i] < Integer.MAX_VALUE && g1.dvex[i] < Integer.MAX_VALUE) {
                    long di = g.dvex[i] + g1.dvex[i];
                    min = min > di ? di : min;
                }
            }

        }
        System.out.println(min);
    }

    @org.junit.Test
    public void test10() {
        g.cnt = 0;
        Param param = new Param.ParamBuilder()
                .setCondition(ConditionEnum.DIST_LESSTHAN_30)
                .setStart(Graph.char2Int('C'))
                .setEnd(Graph.char2Int('C'))
                .setDepth(0)
                .setDist(0).build();
        g.dfsGraph(param, new char[11]);
        System.out.println(g.cnt);

    }
}
