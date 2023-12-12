/**
 * This class represents a solution for a given instance.
 */

public class Solution {


    private int n;
    private int p;


    private Instance instance;
    private int[] abiertas;


    /**
     * Constructor to build a solution for an instance of the p-median problem. This function is used by
     * constructive methods.
     * 
     * @param abiertas
     * @param instance
     */
    public Solution(int[] abiertas, Instance instance) {
        this.abiertas = abiertas;
        this.n = instance.getN();
        this.p = instance.getP();
        this.instance = instance;
    }

    /**
     * Constructor to build a solution for an instance of the p-median problem. This function is used by
     * constructive methods. The medians open are 1 to p.
     *
     * @param instance
     */
    public Solution(Instance instance) {
        this.instance = instance;
        for (int i = 0; i < p; i++) {
            abiertas[i]=i;
        }
    }

    /**
     * Method used for open a median
     * 
     * @param index place to open the median
     */

    public void openMedian(int index){
        for (int i = 0; i < p; i++) {
            if(abiertas[i] == -1){
                abiertas[i] = index;
                break;
            }
        }
    }

    /**
     * Method use to  close a median
     * 
     * @param index place to close a median
     */
    public void closeMedian(int index){
        for (int i = 0; i < p; i++) {
            if(abiertas[i] == index){
                abiertas[i] = -1;
                break;
            }
        }
    }

    /**
     * Method used to change the place of a median. It closes a median in index1 and open other in index2.
     *
     * @param index1 place to close a median
     * @param index2 place to open a median
     */
    
    public void changeMedian(int index1, int index2){
        for (int i = 0; i < p; i++) {
            if(abiertas[i] == index1){
                abiertas[i] = index2;
                break;
            }
        }
    }

    /**
     * Method to evaluate the actual solution codified for the instance. It calculates the objective function.
     *
     * @return Value of the objective function.
     */

    public int evaluateSolution(){
        int[][] distancias = instance.getDistance();
        int sol = 0;
        for (int i = 0; i < n; i++) {
            int masCercana = 1000000000;
            int segundaCercana = 1000000000;
            for (int j = 0; j < p; j++) {
                int mediana = abiertas[j];
                if(distancias[i][mediana] < masCercana){
                    segundaCercana = masCercana;
                    masCercana = distancias[i][mediana];
                }
                else if(distancias[i][mediana] < segundaCercana){
                    segundaCercana = distancias[i][mediana];
                }
            }
            sol+=segundaCercana;
        }
        return sol;
    }



    public int getN() {
        return n;
    }


    public int getP() {
        return p;
    }




    public int[] getAbiertas() {
        return abiertas;
    }


    /**
     * Method to clone a solution
     *
     * @return Give another object solution, with the same values in their parameters.
     */
    @Override
    public Solution clone(){
        Solution s = new Solution(this.abiertas.clone(), instance);
        return s;
    }
}
