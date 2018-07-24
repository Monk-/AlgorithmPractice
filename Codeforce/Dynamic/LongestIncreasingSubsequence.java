package Codeforce.Dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        MyScanner myScanner = new MyScanner();
        int subsequenceLength = myScanner.nextInt();
        int arr[] = new int[subsequenceLength+1];
        for (int i=0;i<subsequenceLength;i++) {
            arr[i] = myScanner.nextInt();
        }
        System.out.println(lis(arr));
    }

    static int lis(int []d) {
        int temp[] = new int[d.length+1];
        int counter = 1;
        temp[0] = d[0];
        for (int i=1;i<d.length-1;i++) {
            int num = d[i];
            if (num < temp[0]) {
                temp[0] = num;
            } else if (num > temp[counter-1]) {
                temp[counter++] = num;
            } else {
                int place = binarySearch(temp, 0, counter - 1, num);
                temp[place] = num;
            }
        }
        return counter;
    }


    static int binarySearch(int a[], int l, int r, int value)
    {
        while (r-l>1)
        {
            int m = l + (r - l)/2;
            if (a[m]>=value)
                r = m;
            else
                l = m;
        }
        return r;
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
