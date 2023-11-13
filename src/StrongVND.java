import java.io.FileNotFoundException;

public class StrongVND {

    public static void main(String[] args) throws FileNotFoundException {

        StringBuilder valores = new StringBuilder();
        StringBuilder ts = new StringBuilder();
        for (int t = 1; t <= 40; t++) {
            String file = "Instancias/pmed"+t+".txt";
            Instance instance = new Instance(file);

            int p = instance.getP();
            int n = instance.getN();

            int solfinal = 1000000000;

            double a = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                Constructive c = new GRASPConstructive(instance.getN(), instance.getP(), instance,new kClosestUserFunction(n/p, instance),0.9);

                Solution s = c.generarSolucion();

                LocalSearch[] BLs = new LocalSearch[3];

                BLs[0] = new SwapLocalSearch(instance, instance.getN(), instance.getP());
                BLs[1] = new DuplicateLocalSearch(instance, instance.getN(), instance.getP());
                BLs[2] = new MergeLocalSearch(instance, instance.getN(), instance.getP());




                VND vnd = new VND(BLs, instance);
                s = vnd.mejorarSolucion(s);

                int val = s.evaluarSolucion();
                solfinal = Math.min(val,solfinal);
            }
            double b = System.currentTimeMillis();

            valores.append(solfinal).append("\n");
            double val = (b-a)/1000;
            ts.append(val).append("\n");
        }

        System.out.println(valores);
        System.out.println(ts);


    }

}
