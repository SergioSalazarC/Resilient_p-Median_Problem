/** Local Search that close an open median and open other one in a place where other median already exists
 *
 *
 */
public class DuplicateLocalSearch implements LocalSearch {


    Instance instance;

    int n;

    int p;

    /** Creates the set-up for the Local Search.
     *
     * @param instance The instance of the problem to solve.
     */
    public DuplicateLocalSearch(Instance instance) {
        this.instance = instance;
        this.n = instance.getN();
        this.p = instance.getP();
    }

    /**Method to use the local search and improve the given solution s.
     *
     * @param s
     * @return The solution s improved by duplicating a median, if is not possible improve s, it returns s.
     */
    @Override
    public Solution mejorarSolucion(Solution s) {
        int value1 = s.evaluateSolution();
        int[] abiertas = s.getAbiertas();

        boolean sigue = true;


        for (int i = 0; i < p  && sigue; i++) {
            int duplicar = abiertas[i];
            for (int j = 0; j < p && sigue; j++) {
                int cerrar = abiertas[j];
                if(duplicar == cerrar) continue;
                Solution s2 = s.clone();
                s2.closeMedian(cerrar);
                s2.openMedian(duplicar);
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
