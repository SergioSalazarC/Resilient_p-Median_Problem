public class SwapLocalSearch implements LocalSearch {
    /*
    Esta busqueda local intercambia una mediana por otra
     */

    Instance instance;

    int n;

    int p;

    public SwapLocalSearch(Instance instance, int n, int p) {
        this.instance = instance;
        this.n = n;
        this.p = p;
    }

    @Override
    public Solution mejorarSolucion(Solution s) {
        /*
        debemos elegir que facility cerrar, probaremos todas en caso de que exista una que mejore la solución.
         */
        int value1 = s.evaluarSolucion();

        boolean sigue = true;

        for (int i = 0; i < p && sigue; i++) {
            int[] abiertas = s.getAbiertas();
            int cerrar = abiertas[i];
            for (int j = 0; j < n && sigue; j++) {
                Solution s2 = s.clone();
                if(j==cerrar) continue;
                s2.intercambiarMediana(cerrar,j);
                int value2 = s2.evaluarSolucion();
                if(value2<value1){
                    s = s2;
                    sigue = false;
                }
            }

        }

        return s;

    }
}
