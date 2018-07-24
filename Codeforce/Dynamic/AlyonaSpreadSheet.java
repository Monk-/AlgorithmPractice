package Codeforce.Dynamic;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AlyonaSpreadSheet {

    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int arr[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        int[][] counte = count(arr, n, m);
        int p = scanner.nextInt();
        for (int i = 0; i < p; i++) {
            out.println(check(counte, scanner.nextInt(), scanner.nextInt()));
        }
        out.close();

    }

    static String check(int[][] d, int s, int e) {
        int[] numbers = d[s - 1];
        int es = e - s + 1;
        if (numbers[numbers.length - 2] >= es) {
            return "Yes";
        }

        return "No";
    }

    static int[][] count(int d[][], int n, int m) {
        int temp[][] = new int[n][m + 2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(temp[i], 1);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (j == m - 1) {
                    temp[i][m + 1] = Integer.MAX_VALUE;
                }
                if (d[i + 1][j] >= d[i][j]) {
                    temp[i][j] = temp[i + 1][j] + 1;
                }
                temp[i][m] = temp[i][m] < temp[i][j] ? temp[i][j] : temp[i][m]; //max
                temp[i][m + 1] = temp[i][m + 1] > temp[i][j] ? temp[i][j] : temp[i][m + 1]; // min

            }
        }
        return temp;
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

