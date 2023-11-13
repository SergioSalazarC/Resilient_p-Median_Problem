public class Solution {

    /*
    Las instancias a utilizar (OR-Library) aportan un conjunto de n puntos, entre ellos debemos elegir p para que sean las medianas
    De esta manera podemos utilizar un array de enteros, indicando cuantas localidades podemos situar en cada punto.
     */

    private int n;
    private int p;


    private Instance instance;
    private int[] abiertas;

    public Solution(int[] abiertas, int n, int p, Instance instance) {
        this.abiertas = abiertas;
        this.n = n;
        this.p = p;
        this.instance = instance;
    }

    public Solution(int n, int p, Instance instance) {
        this.n = n;
        this.p = p;
        this.instance = instance;
        for (int i = 0; i < p; i++) {
            abiertas[i]=i;
        }
    }

    public void abrirMediana(int index){
        for (int i = 0; i < p; i++) {
            if(abiertas[i] == -1){
                abiertas[i] = index;
                break;
            }
        }
    }
    public void cerrarMediana(int index){
        for (int i = 0; i < p; i++) {
            if(abiertas[i] == index){
                abiertas[i] = -1;
                break;
            }
        }
    }
    public void intercambiarMediana(int index1, int index2){
        for (int i = 0; i < p; i++) {
            if(abiertas[i] == index1){
                abiertas[i] = index2;
                break;
            }
        }
    }

    public int evaluarSolucion(){
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



    public Instance getInstancia() {
        return instance;
    }

    public void setInstancia(Instance instance) {
        this.instance = instance;
    }

    public int[] getAbiertas() {
        return abiertas;
    }


    @Override
    public Solution clone(){
        Solution s = new Solution(this.abiertas.clone(),n,p, instance);
        return s;
    }
}
