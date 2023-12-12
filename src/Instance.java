import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class represents an instance of the resilient p-median problem.
 *
 */
public class Instance {
    private int n;
    private int p;
    private int[][] distance;

    private static int INF = 1000000000;

    /**
     * This method reads an instance from a File, the structure of the file has to be:
     * n m p
     * a_1 b_1 d_1
     * ...
     * a_m b_m d_p
     *
     * Where n and p are the parameters of the problem, m are the quantity of distance samples given
     * and a_i, b_i, d_i represents that distance d_i exists betweeen a_i and b_i.
     *
     * @param file Name of the file of the instance
     * @throws FileNotFoundException
     */

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
    public int getP() {
        return p;
    }
    public int[][] getDistance() {
        return distance;
    }

}
