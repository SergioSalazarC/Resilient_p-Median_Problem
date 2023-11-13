public class VND {

    int k_max;
    LocalSearch[] busquedasLocales;

    Instance instance;

    public VND(LocalSearch[] busquedasLocales, Instance instance) {
        this.k_max = busquedasLocales.length;
        this.busquedasLocales = busquedasLocales;
        this.instance = instance;

    }

    public Solution mejorarSolucion(Solution sol){
        int k = 0;

        Solution s1 = sol.clone();
        int value1 = s1.evaluarSolucion();

        while(k<k_max){
            Solution s2 = s1.clone();

            busquedasLocales[k].mejorarSolucion(s2);

            int value2 = s2.evaluarSolucion();

            if(value2<value1){
                k=0;
                s1 = s2;
                value1 = value2;
            }
            else{
                k++;
            }
        }

        return s1;

    }


}
