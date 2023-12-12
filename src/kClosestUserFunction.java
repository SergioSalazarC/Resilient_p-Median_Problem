import java.util.ArrayList;

/**
 * This class represent the function kClosestUser, it consists in a tipical heuristic for p-median problems
 *
 * It calculates the distance to the k closest users.
 */
public class kClosestUserFunction implements PointFunction {

    private int k;
    private Instance instance;

    /**
     * Set up for claculate the function.
     *
     * @param k Quantity of users to calculate distance
     * @param instance Instance of the problem to solve
     */

    public kClosestUserFunction(int k, Instance instance) {
        this.k = k;
        this.instance = instance;
    }


    /**
     *
     * @param p Selected place for evaluate
     * @return Distance between p and the k closest users.
     */
    @Override
    public int f(int p) {
        int[][] distancias = instance.getDistance();
        int n = instance.getN();
        ArrayList<Integer> valores = new ArrayList<>();
        int cota = -1;

        for(int i = 0 ;i<n ;i++){
            if(valores.size() < k){
                valores.add(distancias[i][p]);
                cota = Math.max(cota,distancias[i][p]);
            }
            else{
                int dist = distancias[i][p];
                if(dist < cota){
                    valores.remove(Integer.valueOf(cota));
                    valores.add(dist);
                    cota = -1;
                    for(int d : valores){
                        cota = Math.max(cota,d);
                    }
                }
            }
        }
        int sol = 0;
        for(double d : valores)sol+=d;
        return sol;
    }
}
