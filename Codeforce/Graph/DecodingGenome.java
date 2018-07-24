package Codeforce.Graph;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DecodingGenome {

    static final long MODULO = 1000000007;
    static long[][] starting;

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        long lenghtOfNucleotide = sc.nextLong();
        int numberOfNucleotides = sc.nextInt(), numberOfForbiddenPairs =
                sc.nextInt();
        if (lenghtOfNucleotide == 1) {
            out.println(numberOfNucleotides);
            out.close();
            return;
        }
        long answer = 0;
        long[][] m = getLongsWith1(numberOfNucleotides);
        for (int i = 0; i < numberOfForbiddenPairs; i++) {
            String banned = sc.nextLine();
            int q = Character.isUpperCase(banned.charAt(0)) ? banned.charAt(0) - 39 : banned.charAt(0) - 97;
            int w = Character.isUpperCase(banned.charAt(1)) ? banned.charAt(1) - 39 : banned.charAt(1) - 97;

            m[q][w] = 0;
        }
        starting = new long[numberOfNucleotides][numberOfNucleotides];
        System.arraycopy(m, 0, starting, 0, m.length);
        long[][] multiplyM = multiplyM(m, lenghtOfNucleotide - 2);
        for (int i = 0; i < numberOfNucleotides; i++) {
            for (int j = 0; j < numberOfNucleotides; j++) {
                answer = (answer + multiplyM[j][i]) % MODULO;
            }
        }
        out.println(answer);
        out.close();
    }

    private static long[][] getLongsWith1(int numberOfNucleotides) {
        long[][] m = new long[numberOfNucleotides][numberOfNucleotides];
        for (int i = 0; i < numberOfNucleotides; i++) {
            Arrays.fill(m[i], 1);
        }
        return m;
    }

    static long[][] multiplyM(long m[][], long p) {
        if (p > 0) {
            if (p % 2 == 1) {
                return mult(m, multiplyM(m, --p));
            } else {
                long[][] m2 = mult(m, m);
                return multiplyM(m2, p / 2);
            }
        } else if (p == 0) {
            return starting;
        } else {
            return starting;
        }
    }

    static long[][] mult(long m1[][], long m2[][]) {
        long[][] p = new long[m1.length][m1.length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1.length; j++) {
                for (int k = 0; k < m1.length; k++) {
                    p[i][j] = (p[i][j] + (m1[i][k] * m2[k][j]) % MODULO) % MODULO;
                }
            }
        }
        return p;
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

        long nextLong() {
            return Long.parseLong(next());
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
