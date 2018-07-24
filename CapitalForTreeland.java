import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class CapitalForTreeland {

    static int [] redOnPath;
    static int [] distanceFromRoot;
    static int [] ans;
    static boolean [] visited;
    static Queue<Node> queue;
    static List<List<Pair>> graph;
    static int red = 0;

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int n = sc.nextInt();
        queue = new LinkedList<>();
        graph = new ArrayList<>();
        for (int i=0;i<n+1;i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            graph.get(from).add(new Pair(to, false));
            graph.get(to).add(new Pair(from, true));
        }

        redOnPath = new int[n+1];
        distanceFromRoot = new int[n+1];
        ans = new int[n+1];
        visited = new boolean[n+1];

        bfs(1);

        int min = Integer.MAX_VALUE;
        for (int i=1;i<=n;i++) {
            int greenOnpathToRoot = distanceFromRoot[i] - redOnPath[i];
            int redToInverse = red - redOnPath[i];
            ans[i] = greenOnpathToRoot + redToInverse;
            min = ans[i] < min ? ans[i] : min;
        }

        out.println(min);

        for (int i=1;i<=n;i++) {
           if (ans[i] == min) {
               out.print(i + " ");
           }
        }


        out.close();

    }

    static void bfs(int st) {
        visited[st] = true;
        queue.add(new Node(st, 0, 0));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            for (Pair pair : graph.get(node.getStart())) {
                if (!visited[pair.getNode()]) {
                    Node toPush;
                    if (pair.isToNode()) {
                        red += 1;
                        toPush = new Node(pair.getNode(), node.getDistance() + 1, node.getColor() + 1);
                        redOnPath[pair.getNode()] = node.getColor() + 1;
                    } else {
                        toPush = new Node(pair.getNode(), node.getDistance() + 1, node.getColor());
                        redOnPath[pair.getNode()] = node.getColor();
                    }
                    distanceFromRoot[pair.getNode()] = node.getDistance() + 1;
                    visited[pair.getNode()] = true;
                    queue.add(toPush);
                }
            }
        }
    }

    static class Pair {
        int node;
        boolean isToNode;

        public Pair(int node, boolean isToNode) {
            this.node = node;
            this.isToNode = isToNode;
        }

        public int getNode() {
            return node;
        }

        public void setNode(int node) {
            this.node = node;
        }

        public boolean isToNode() {
            return isToNode;
        }

        public void setToNode(boolean toNode) {
            this.isToNode = toNode;
        }

    }

    static class Node {
        int start;
        int distance;
        int color;

        public Node(int start, int distance, int color) {
            this.start = start;
            this.distance = distance;
            this.color = color;
        }

        public int getStart() {
            return start;
        }

        public int getDistance() {
            return distance;
        }

        public int getColor() {
            return color;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public void setColor(int color) {
            this.color = color;
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
