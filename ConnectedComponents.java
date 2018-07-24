import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ConnectedComponents {

    static List<List<Integer>> graph;
    static boolean [] visited;

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int numberOfVertexes = sc.nextInt(), numberOfEdges = sc.nextInt();
        visited = new boolean[numberOfVertexes+1];
        graph = new ArrayList<>();
        readGraph(sc, numberOfVertexes, numberOfEdges, graph);
        int counter = 0;
        for (int i=1;i<=numberOfVertexes;i++) {
            if (!visited[i]) {
                dfs(i);
                counter += 1;
            }
        }
        out.print(counter);
        out.close();

    }

    static void dfs(int s) {
        visited[s] = true;
        List<Integer> connections = graph.get(s);
        for (Integer vertex: connections) {
            if (!visited[vertex]) {
                dfs(vertex);
            }
        }
    }

    private static void readGraph(MyScanner sc, int n, int m, List<List<Integer>> graph) {
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        int s, e;
        for (int i = 0; i < m; i++) {
            s = sc.nextInt();
            e = sc.nextInt();
            graph.get(s).add(e);
            graph.get(e).add(s);
        }
    }

    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

    }

}
