public class DuplicateLocalSearch implements LocalSearch {

    Instance instance;

    int n;

    int p;

    public DuplicateLocalSearch(Instance instance, int n, int p) {
        this.instance = instance;
        this.n = n;
        this.p = p;
    }

    @Override
    public Solution mejorarSolucion(Solution s) {
        /*
        Esta búsqueda local busca eliminar una mediana y duplicar otra. De esta manera podemos observar que para los puntos asociados a esa mediana
        al estar duplicada, es equivalente a la mediana mas cercana.
         */
        int value1 = s.evaluarSolucion();
        int[] abiertas = s.getAbiertas();

        boolean sigue = true;


        for (int i = 0; i < p  && sigue; i++) {
            int duplicar = abiertas[i];
            for (int j = 0; j < p && sigue; j++) {
                int cerrar = abiertas[j];
                if(duplicar == cerrar) continue;
                Solution s2 = s.clone();
                s2.cerrarMediana(cerrar);
                s2.abrirMediana(duplicar);
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
