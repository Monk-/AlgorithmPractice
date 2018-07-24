import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Hongcow {

    static List<List<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
        List<Integer> governments = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            governments.add(sc.nextInt());
        }
        graph = new ArrayList<>();
        visited = new boolean[n+1];
        for (int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            graph.get(from).add(to);
            graph.get(to).add(from);
        }


        long result = 0;
        Pair pair;
        Pair max = new Pair(0, 0);
        for (int i = 0; i < k; i++) {
            pair = new Pair(0, 0);
            dfs(governments.get(i), pair);
            max = pair.getNodes() > max.getNodes() ? pair : max;
            result += pair.getNodes() * (pair.getNodes() - 1) / 2 - pair.getEdges() / 2;
        }


        Pair pairino;
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                pairino = new Pair(0, 0);
                dfs(i, pairino);
                if (pairino.getNodes() > 1) {
                    int counter = 0;
                    while (counter < pairino.getNodes()) {
                        result += max.getNodes();
                        max.setNodes(max.getNodes() + 1);
                        counter += 1;
                    }
                    result -= pairino.getEdges()/2;
                } else {
                    result += max.getNodes();
                    max.setNodes(max.getNodes() + 1);
                }
            }
        }
        out.println(result);
        out.close();
    }


    static void dfs(int start, Pair pair) {
        visited[start] = true;
        List<Integer> connections = graph.get(start);
        pair.setNodes(pair.getNodes() + 1);
        pair.setEdges(pair.getEdges() + connections.size());
        for (int connection : connections) {
            if (!visited[connection]) {
                dfs(connection, pair);
            }
        }
    }

    static class Pair {
        int nodes;
        int edges;

        public Pair(int nodes, int edges) {
            this.nodes = nodes;
            this.edges = edges;
        }

        public int getNodes() {
            return nodes;
        }

        public void setNodes(int nodes) {
            this.nodes = nodes;
        }

        public int getEdges() {
            return edges;
        }

        public void setEdges(int edges) {
            this.edges = edges;
        }
    }


    static class MyScanner {
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
