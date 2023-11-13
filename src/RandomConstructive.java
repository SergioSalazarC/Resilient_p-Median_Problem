import java.util.HashSet;
import java.util.Random;

public class RandomConstructive implements Constructive {

    private int n;
    private int p;
    private Instance instance;

    public RandomConstructive(int n, int p, Instance instance) {
        this.n = n;
        this.p = p;
        this.instance = instance;
    }

    @Override
    public Solution generarSolucion() {
        HashSet<Integer> conjunto = new HashSet<>();
        Random r = new Random();
        while(conjunto.size() < p){
            conjunto.add(r.nextInt(n));
        }
        int[] abiertas = new int[p];

        int index = 0;
        for(int i : conjunto){
            abiertas[index] = i;
            index++;
        }
        return new Solution(abiertas,n,p, instance);
    }
}
