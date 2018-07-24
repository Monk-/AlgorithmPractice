package Codeforce.Dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestCommonSubsequence {


    public static void main(String[] args) {
        MyScanner myScanner = new MyScanner();
        System.out.println(lcs(myScanner.nextLine(), myScanner.nextLine()));
    }

    public static int lcs(String first, String second) {
        char[] firstA = first.toCharArray();
        char[] secondA = second.toCharArray();
        int[][] arr = new int[first.length() + 1][second.length() + 1];
        for (int i = 0; i <= first.length(); i++) {
            for (int j = 0; j <= second.length(); j++) {
                if (i == 0 || j == 0) {
                    arr[i][j] = 0;
                } else if (firstA[i - 1] == secondA[j - 1]) {
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                } else {
                    arr[i][j] = max(arr[i - 1][j], arr[i][j - 1]);
                }
            }
        }
        return arr[first.length()][second.length()];
    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }


    public static class MyScanner {
        BufferedReader br;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }

}