package Codeforce.Graph;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class TheTagGame {

    static int alicePath[];
    static int bobPath[];
    static boolean visited[];
    static List<List<Integer>> graph;

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int n = sc.nextInt();
        int bobPos = sc.nextInt() - 1;
        alicePath = new int[n];
        bobPath = new int[n];
        visited = new boolean[n];
        graph = new ArrayList<>();
        readGraph(sc, n, graph);
        dfsAlice(0);
        Arrays.fill(visited, false);
        dfsBob(bobPos);
        int maxAl = alicePath[bobPos];
        for (int i = 0; i < n; i++) {
            if (bobPath[i] != 0) {
                maxAl = max(alicePath[i], maxAl);
            }
        }


        out.println(2 * maxAl);
        out.close();
    }

    static void dfsAlice(int pos) {
        visited[pos] = true;
        for (int next : graph.get(pos)) {
            if (!visited[next]) {
                alicePath[next] = alicePath[pos] + 1;
                dfsAlice(next);
            }
        }
    }

    static void dfsBob(int pos) {
        visited[pos] = true;
        int nextB;
        for (int next : graph.get(pos)) {
            if (!visited[next]) {
                nextB = bobPath[pos] + 1;
                if (nextB < alicePath[next]) {
                    bobPath[next] = bobPath[pos] + 1;
                    dfsBob(next);
                } else {
                    visited[next] = true;
                }
            }
        }
    }

    static int max(int a, int b) {
        return a > b ? a : b;
    }

    private static void readGraph(MyScanner sc, int n, List<List<Integer>> graph) {
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int s, e;
        for (int i = 0; i < n - 1; i++) {
            s = sc.nextInt() - 1;
            e = sc.nextInt() - 1;
            graph.get(s).add(e);
            graph.get(e).add(s);
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
