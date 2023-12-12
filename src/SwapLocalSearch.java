/** Local Search that move an open median to other place.
 *
 */
public class SwapLocalSearch implements LocalSearch {

    Instance instance;

    int n;

    int p;

    /** Creates the set-up for the Local Search.
     *
     * @param instance The instance of the problem to solve.
     */
    public SwapLocalSearch(Instance instance) {
        this.instance = instance;
        this.n = instance.getN();
        this.p = instance.getP();
    }

    /**Method to use the local search and improve the given solution s.
     *
     * @param s
     * @return The solution s improved by moving a median, if is not possible improve s, it returns s.
     */
    @Override
    public Solution mejorarSolucion(Solution s) {
        int value1 = s.evaluateSolution();

        boolean sigue = true;

        for (int i = 0; i < p && sigue; i++) {
            int[] abiertas = s.getAbiertas();
            int cerrar = abiertas[i];
            for (int j = 0; j < n && sigue; j++) {
                Solution s2 = s.clone();
                if(j==cerrar) continue;
                s2.changeMedian(cerrar,j);
                int value2 = s2.evaluateSolution();
                if(value2<value1){
                    s = s2;
                    sigue = false;
                }
            }

        }

        return s;

    }
}
