import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Huffman {

    static PriorityQueue<HuffmanNode> queue;

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        queue = new PriorityQueue<>();
        int testCases = sc.nextInt(), numberOfWords;
        while (testCases-- > 0) {
            numberOfWords = sc.nextInt();
            for (int i = 0; i < numberOfWords; i++) {
                queue.add(new HuffmanNode(sc.nextInt()));
            }
            HuffmanNode root = buildTree(queue);

            out.println(getCode(root, 0));
        }
        out.close();
    }


    static HuffmanNode buildTree(PriorityQueue<HuffmanNode> pq) {
        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            queue.add(new HuffmanNode(left.frequency + right.frequency, left, right));
        }
        return pq.poll();
    }

    static long getCode(HuffmanNode root, long cnt) {
        if (root.left == null && root.right == null) {
            return root.frequency * (cnt == 0 ? 1L : cnt);
        }
        return getCode(root.left, cnt + 1L) + getCode(root.right, cnt + 1L);
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


    static class HuffmanNode implements Comparable<HuffmanNode> {
        int frequency;
        HuffmanNode left;
        HuffmanNode right;

        public HuffmanNode(int frequency) {
            this.frequency = frequency;
        }

        public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(HuffmanNode o) {
            return this.frequency - o.frequency;
        }
    }


}
