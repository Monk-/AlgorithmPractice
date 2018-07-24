package Codeforce.Graph;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Kuro {

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int n = sc.nextInt();
        int x = sc.nextInt(), y = sc.nextInt();
        boolean[] visited = new boolean[n];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0;i<n;i++) {
            graph.add(new ArrayList<>());
        }
        int s, e;
        for (int i = 0; i < n - 1; i++) {
            s = sc.nextInt();
            e = sc.nextInt();
            graph.get(s-1).add(e-1);
            graph.get(e-1).add(s-1);
        }
        long all = ((long) n) * (n - 1);
        long d = dfs(graph, visited, x - 1, y - 1);
        for (int i = 0; i < n; ++i) {
            visited[i] = false;
        }
        long d2 = dfs(graph, visited, y - 1, x - 1);
        out.println(all - (n - d) * (n - d2));
        out.close();
    }

    static long dfs(List<List<Integer>> g, boolean[] vis, int n, int y) {
        if (n == y) {
            return 0;
        }

        vis[n] = true;

        long ans = 1;

        for (Integer vert : g.get(n)) {
            if (!vis[vert]) {
                ans += dfs(g, vis, vert, y);
            }
        }
        return ans;
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

        long nextLong() {
            return Long.parseLong(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

    }

}
