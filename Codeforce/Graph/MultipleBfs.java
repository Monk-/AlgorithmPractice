package Codeforce.Graph;

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

//Poszukiwania

public class MultipleBfs {
    static List<List<Integer>> graph;
    static int policeStations[];
    static int crimes[];
    static int citiesTimeFromStation[];
    static int whichStationIsClosest[];
    static boolean visited[];

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int allCities = sc.nextInt(), allRoads = sc.nextInt(),
                policeStationsNumber = sc.nextInt(), crimesNumber = sc.nextInt();
        graph = new ArrayList<>();
        visited = new boolean[allCities + 1];
        citiesTimeFromStation = new int[allCities + 1];
        whichStationIsClosest = new int[allCities + 1];
        crimes = new int[crimesNumber];
        policeStations = new int[policeStationsNumber];
        readGraph(sc, allCities, allRoads, graph);
        readDataToPoliceArray(sc, policeStationsNumber);
        readDataToCrimeArray(sc, crimesNumber);
        bfs();
        for (int i = 0; i < crimesNumber; i++) {
            out.println(citiesTimeFromStation[crimes[i]] + " " + whichStationIsClosest[crimes[i]]);
        }
        out.close();
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        for (int policeStation : policeStations) {
            visited[policeStation] = true;
            queue.add(policeStation);
        }

        int curr, time;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            time = citiesTimeFromStation[curr];
            List<Integer> adjacentCities = graph.get(curr);
            for (int city : adjacentCities) {
                if (!visited[city]) {
                    visited[city] = true;
                    citiesTimeFromStation[city] = time + 1;
                    whichStationIsClosest[city] = whichStationIsClosest[curr];
                    queue.add(city);
                }
            }
        }

    }

    private static void readDataToPoliceArray(MyScanner sc, int inputNumber) {
        for (int i = 0; i < inputNumber; i++) {
            policeStations[i] = sc.nextInt();
            whichStationIsClosest[policeStations[i]] = i + 1;
        }
    }

    private static void readDataToCrimeArray(MyScanner sc, int inputNumber) {
        for (int i = 0; i < inputNumber; i++) {
            crimes[i] = sc.nextInt();
        }
    }

    private static void readGraph(MyScanner sc, int n, int m, List<List<Integer>> graph) {
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        int s, e;
        for (int i = 0; i < m; i++) {
            s = sc.nextInt();
            e = sc.nextInt();
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
