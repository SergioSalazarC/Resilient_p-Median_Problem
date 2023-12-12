import java.io.FileNotFoundException;

public class FastVND {

    public static void main(String[] args) throws FileNotFoundException {

        StringBuilder valores = new StringBuilder();
        StringBuilder ts = new StringBuilder();
        for (int t = 1; t <= 40; t++) {
            String file = "Instancias/pmed"+t+".txt";
            Instance instance = new Instance(file);

            int solfinal = 1000000000;

            double a = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                Constructive c = new RandomConstructive(instance);

                Solution s = c.generateSolution();

                LocalSearch[] BLs = new LocalSearch[2];
                BLs[0] = new SwapLocalSearch(instance);
                BLs[1] = new DuplicateLocalSearch(instance);

                VND vnd = new VND(BLs, instance);
                s = vnd.mejorarSolucion(s);

                int val = s.evaluateSolution();
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
