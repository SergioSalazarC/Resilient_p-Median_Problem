import java.io.FileNotFoundException;

public class PruebaReal {
    public static void main(String[] args) throws FileNotFoundException {

        int instance_number = 30;

        String file = "Instancias/pmed"+instance_number+".txt";
        Instance instance = new Instance(file);

        int p = instance.getP();
        int n = instance.getN();

        int solfinal = 1000000000;

        double a = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            //Constructive c = new GRASPConstructive(instance,new kClosestUserFunction(n/p, instance),0.9);
            Constructive c = new RandomConstructive(instance);

            Solution s = c.generateSolution();

            LocalSearch[] BLs = new LocalSearch[2];

            //BLs[0] = new SwapLocalSearch(instance);
            BLs[0] = new DuplicateLocalSearch(instance);
            BLs[1] = new MergeLocalSearch(instance);

            VND vnd = new VND(BLs, instance);
            s = vnd.mejorarSolucion(s);

            int val = s.evaluateSolution();
            solfinal = Math.min(val,solfinal);
        }
        double b = System.currentTimeMillis();


        double val = (b-a)/1000;

        System.out.println(solfinal);
        System.out.println(val);


    }

}
