import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/** Local Search that close two open medians and open two in the same place. This place is in the shortest path
 * with more nodes between the two close medians.
 *
 *
 */
public class MergeLocalSearch implements LocalSearch {

    Instance instance;

    int n;

    int p;

    /** Creates the set-up for the Local Search.
     *
     * @param instance The instance of the problem to solve.
     */
    public MergeLocalSearch(Instance instance) {
        this.instance = instance;
        this.n = instance.getN();
        this.p = instance.getP();
    }

    /**Method to use the local search and improve the given solution s.
     *
     * @param s
     * @return The solution s improved by merging two medians, if is not possible improve s, it returns s.
     */

    @Override
    public Solution mejorarSolucion(Solution s) {
        int value1 = s.evaluateSolution();
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
                    s2.closeMedian(fusion1);
                    s2.closeMedian(fusion2);
                    s2.openMedian(k);
                    s2.openMedian(k);
                    int value2 = s2.evaluateSolution();
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
