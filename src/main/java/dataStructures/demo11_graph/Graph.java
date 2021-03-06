package dataStructures.demo11_graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @ClassName: Graph
 * @Author: WuXiangShuai
 * @Time: 12:09 2019/11/23.
 * @Description:
 */
public class Graph {

    public static void main(String[] args) {
        int n = 5;
        String vertexValue[] = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        // 添加顶点
        for (String val : vertexValue) {
            graph.insertVertex(val);
        }
        // 添加边
        // A-B, A-C, B-C, B-D, B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
//        graph.showGraph();
//        graph.depthFirstSearch(0);
//        graph.dfs();
        graph.bfs();
    }

    // 存储顶点
    private ArrayList<String> vertexs;
    // 存储图对应的邻接矩阵
    private int edges[][];
    // 边的数目
    private int numOfEdges;
    // 定义 boolean 类型数组，记录结点是否被访问
    private boolean visited[];

    public Graph(int n) {
        // 初始化矩阵 和 vertextList
        vertexs = new ArrayList<>(n);
        edges = new int[n][n];
        numOfEdges = 0;
        visited = new boolean[n];
    }

    //
    public void bfs() {
        for (int i = 0; i < vertexs.size(); i++)
            if (!visited[i])
                boardFirshSearch(i);
    }

    // 自练习：广度优先算法 √
    private void boardFirshSearch(int i) {
        // u 记录头结点
        int u;
        // neighbor 记录邻接节点
        int neighbor;
        // 队列 记录结点的访问顺序
        LinkedList<Object> queue = new LinkedList<>();
        // 首先访问 i 结点
        System.out.print(getValByIndex(i) + " -> ");
        // 标记 i 结点被访问
        visited[i] = true;
        // 将 i 结点置入队列
        queue.addLast(i);
        // 遍历 i 结点的邻接结点
        while (queue.size() > 0) {
            // 取得队列头结点下标
            u = (int) queue.removeLast();
            // 得到第一个邻接结点的下标 neighbor
            neighbor = getFirstNeighbor(u);
            // 广度优先遍历
            while (neighbor != -1) {
                // 若 neighbor 未被访问过
                if (!visited[neighbor]) {
                    // 输出
                    System.out.print(getValByIndex(neighbor) + " -> ");
                    // 记录
                    visited[neighbor] = true;
                    // 入队列
                    queue.add(neighbor);
                }
                // 以头结点 u 为前驱结点，向后查询下一个结点
                neighbor = getNextNeighbor(u, neighbor);// 体现广度优先
            }
        }
    }

    // 重载dsf，遍历所有的结点，进行 dsf
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++)
            if (!visited[i])
                dfs(i);
    }

    // 深度优先遍历
    private void dfs(/*boolean[] visited, */int i) {
        // 首先输出结点
        System.out.println(vertexs.get(i));
        // 将该结点设置为已经访问
        visited[i] = true;
        // 查找结点 i 的第一个邻接结点
        int first = getFirstNeighbor(i);
        // 若有
        while (-1 != first) {
            // 若该结点没有被访问
            if (!visited[i]) {
                dfs(i);
            } else
            // 若该结点被访问
            first = getNextNeighbor(i, first);
        }
    }

    // 自练习：深度优先遍历
    public void depthFirstSearch(int index) {
        for (int i = index; i < visited.length; i++) {
            if (visited[i]) continue;
            if (edges[index][i] > 0) {
                System.out.println(vertexs.get(i));
                visited[i] = true;
                depthFirstSearch(i);
            }
        }
    }

    /**
     * 深度优先：根据前一个邻接结点的下标获取下一个邻接结点
     * @param v1 行
     * @param v2 列
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexs.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 深度优先：获取第一个邻接结点的下标
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexs.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 插入结点
    public void insertVertex(String versex) {
        vertexs.add(versex);
    }

    /**
     * @Description: 添加边
     * @Author: WuXiangShuai
     * @Param: [v1 第v1个顶点, v2 第v2个顶点, weight 标注有连接]
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    // 返回结点的个数
    public int getNumOfVertex() {
        return vertexs.size();
    }

    // 返回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    // 返回结点 i 对应的数据
    public String getValByIndex(int i) {
        return vertexs.get(i);
    }

    // 返回 v1 和 v2 的权值，即是否相连
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for (int link[]: edges) {
            System.out.println(Arrays.toString(link));
        }
    }
}
