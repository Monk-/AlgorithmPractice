import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Jugglers {


    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int[][] d = new int[501][501];

        for (int i = 0; i < 33; i++) {
            for (int j = 0; j < 33; j++) {
                for (int r = 50; r > 33; r--) {
                    for (int b = 50; b > 33; b--) {
                        d[r][b] = max(d[r][b], 1 + d[r - i][b - j]);
                    }
                }
            }
        }


        out.close();
    }

    static int max(int a, int b) {
        return a > b ? a : b;
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
