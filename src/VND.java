/**
 * Represent the general structure of VND, it allows us to create different VND algorithms easily
 */
public class VND {

    int k_max;
    LocalSearch[] localSearches;

    Instance instance;

    /**
     * Constructor of the VND algorithm, it requires the instance to solve and local searches used.
     *
     * @param localSearches Array of Local Searches
     * @param instance Instance of the problem to solve
     */
    public VND(LocalSearch[] localSearches, Instance instance) {
        this.k_max = localSearches.length;
        this.localSearches = localSearches;
        this.instance = instance;

    }

    /**
     * Method to execute VND algorithm, it improves the solution sol.
     *
     * @param sol Initial solution to the algorithm
     * @return Solution sol improved, in case it can not be improved, sol is returned.
     */

    public Solution mejorarSolucion(Solution sol){
        int k = 0;

        Solution s1 = sol.clone();
        int value1 = s1.evaluateSolution();

        while(k<k_max){
            Solution s2 = s1.clone();

            localSearches[k].mejorarSolucion(s2);

            int value2 = s2.evaluateSolution();

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
