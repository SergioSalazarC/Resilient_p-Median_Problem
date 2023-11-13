import java.util.ArrayList;

public class kClosestUserFunction implements PointFunction {

    private int k;
    private Instance instance;

    public kClosestUserFunction(int k, Instance instance) {
        this.k = k;
        this.instance = instance;
    }


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
