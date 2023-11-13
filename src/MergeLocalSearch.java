import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MergeLocalSearch implements LocalSearch {
    /*
    Cuando dos medianas están suficientemente cerca pueden ser intercambiadas por una doble entre ellas.
    Para buscar donde colocar la media se usara los valores intermedios del camino más corto en distancia que tenga el número de nodos más alto.
    Realmente para crear la instancia se usa el algoritmo de floyd, por lo que la distancia más corta ya viene calculada por d[i][j].
     */

    Instance instance;

    int n;

    int p;

    public MergeLocalSearch(Instance instance, int n, int p) {
        this.instance = instance;
        this.n = n;
        this.p = p;
    }

    @Override
    public Solution mejorarSolucion(Solution s) {
        int value1 = s.evaluarSolucion();
        int[] abiertas = s.getAbiertas();

        boolean sigue = true;


        for (int i = 0; i < p && sigue; i++) {
            for (int j = 0; j < p && sigue; j++) {
                int fusion1 = abiertas[i];
                int fusion2 = abiertas[j];
                if (fusion1 == fusion2) continue;

                LinkedList<Integer> camino = caminoDijsktra(fusion1,fusion2);

                for(Integer k : camino){
                    if(k==fusion1 || k==fusion2) continue;
                    Solution s2 = s.clone();
                    s2.cerrarMediana(fusion1);
                    s2.cerrarMediana(fusion2);
                    s2.abrirMediana(k);
                    s2.abrirMediana(k);
                    int value2 = s2.evaluarSolucion();
                    if (value2 < value1) {
                        s = s2;
                        sigue = false;
                        break;
                    }
                }
            }

        }

        return s;

    }

    public LinkedList<Integer> caminoDijsktra(int i, int j) {

        int[][] d = instance.getDistance();

        PriorityQueue<Par> pq = new PriorityQueue<>();
        int[] dist = new int[n];
        int[] dislow = new int[n];
        int[] padre = new int[n];
        Arrays.fill(padre,-1);
        Arrays.fill(dist, 1000000000);
        Arrays.fill(dislow, 1000000000);
        dist[i] = 0;
        dislow[i] = 0;
        pq.add(new Par(i,0));
        while (!pq.isEmpty()) {
            Par top = pq.poll();
            if (top.evaluation > dist[top.point]) continue;
            for (int nodo = 0; nodo < n; nodo++) {
                if(nodo == top.point) continue;
                if (dist[top.point] + d[top.point][nodo] > dist[nodo]) continue;
                else if(dist[top.point] + d[top.point][nodo] < dist[nodo]){
                    dislow[nodo] = d[top.point][nodo];
                    padre[nodo] = top.point;
                    dist[nodo] = dist[top.point] + d[top.point][nodo];
                    pq.offer(new Par(nodo, dist[nodo]));
                }
                else if(dist[top.point] + d[top.point][nodo] == dist[nodo]){
                    if(dislow[nodo] <=  d[top.point][nodo]) continue;
                        dislow[nodo] = d[top.point][nodo];
                        padre[nodo] = top.point;
                        dist[nodo] = dist[top.point] + d[top.point][nodo];
                        pq.offer(new Par(nodo, dist[nodo]));

                }


            }
        }

        LinkedList<Integer> camino = new LinkedList<>();

        int sig = j;
        while(sig!=-1){
            camino.add(sig);
            sig = padre[sig];
        }
        return camino;



    }

    private class Par implements Comparable {
        int point;
        int evaluation;

        public Par(int i, int f) {
            point = i;
            evaluation = f;
        }


        @Override
        public int compareTo(Object o) {
            Par i = (Par) o;
            return Integer.compare(this.evaluation, i.evaluation);
        }
    }


}
