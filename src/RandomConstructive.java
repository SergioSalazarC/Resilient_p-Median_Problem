import java.util.HashSet;
import java.util.Random;

/**
 * This class creates the necessary methods to build an initial solution randomly.
 */
public class RandomConstructive implements Constructive {

    private int n;
    private int p;
    private Instance instance;

    public RandomConstructive(Instance instance) {
        this.n = instance.getN();
        this.p = instance.getP();
        this.instance = instance;
    }

    /**This method builds the initial solution for the instance given in the constructor.
     *
     * @return Solution generated randomly
     */
    @Override
    public Solution generateSolution() {
        HashSet<Integer> conjunto = new HashSet<>();
        Random r = new Random();
        while (conjunto.size() < p) {
            conjunto.add(r.nextInt(n));
        }
        int[] abiertas = new int[p];

        int index = 0;
        for (int i : conjunto) {
            abiertas[index] = i;
            index++;
        }
        return new Solution(abiertas, instance);
    }
}
