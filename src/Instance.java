import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Instance {
    private int n;
    private int p;
    private int[][] distance;

    private static int INF = 1000000000;

    public Instance(String file) throws FileNotFoundException {
        Scanner in = new Scanner(new File(file));
        n = in.nextInt();
        int d = in.nextInt();
        p = in.nextInt();
        distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i],INF);
        }
        for (int i = 0; i < n; i++) {
            distance[i][i]=0;
        }
        //Los nodos vienen codificados de 1 a n, para sencillez de programación los codificaremos de 0 a n-1.
        for (int i = 0; i < d; i++) {
            int nodoA = in.nextInt()-1;
            int nodoB = in.nextInt()-1;
            int distancia = in.nextInt();
            distance[nodoA][nodoB] = distancia;
            distance[nodoB][nodoA] = distancia;
        }
        floydWarshall(distance,n);
    }

    private void floydWarshall(int[][] distancias, int n){
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distancias[i][j] = Math.min(distancias[i][j],distancias[i][k]+distancias[k][j]);
                }
            }
        }
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int[][] getDistance() {
        return distance;
    }

    public void setDistance(int[][] distance) {
        this.distance = distance;
    }
}
