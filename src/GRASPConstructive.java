import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GRASPConstructive implements Constructive {

    private int n;
    private int p;
    private Instance instance;

    private double alpha;
    PointFunction function;

    public GRASPConstructive(int n, int p, Instance instance, PointFunction function, double alpha) {
        this.n = n;
        this.p = p;
        this.instance = instance;
        this.function = function;
        this.alpha = alpha;

    }

    @Override
    public Solution generarSolucion() {
        //En GRASP se asgina un valor a cada punto factible a ser parte de la solucion.
        ArrayList<Par> listaCandidatos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            listaCandidatos.add(new Par(i, function.f(i)));
        }

        int[] abiertas = new int[p];
        int size = 0;

        Collections.sort(listaCandidatos);

        while(size<p){
            Random r = new Random();
            double cmin = listaCandidatos.get(0).evaluation;
            double cmax = listaCandidatos.get(listaCandidatos.size()-1).evaluation;

            //Cuando alpha=1 es greedy cuando alpha=0 es random
            double cota = cmax + alpha*(cmin-cmax);

            int index = busquedaBinaria(listaCandidatos,cota);

            int elegido = r.nextInt(index+1);

            abiertas[size] = listaCandidatos.remove(elegido).point;
            size++;

        }


        return new Solution(abiertas,n,p, instance);


    }

    private  int busquedaBinaria(ArrayList<Par> lista, double cota) {
        int izquierda = 0;
        int derecha = lista.size() - 1;
        int indice = -1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            Par elementoMedio = lista.get(medio);

            if (elementoMedio.evaluation < cota) {
                // Actualizamos el índice y seguimos buscando a la derecha
                indice = medio;
                izquierda = medio + 1;
            } else {
                // El elemento en medio es mayor o igual a la cota, buscamos a la izquierda
                derecha = medio - 1;
            }
        }

        if(indice == -1) indice = 0;

        return indice;
    }

    private class Par implements Comparable {
        int point;
        int evaluation;

        public Par(int i, int f) {
            point = i;
            evaluation = f;
        }


        @Override
        public int compareTo(Object o) {
            Par i = (Par) o;
            return Integer.compare(this.evaluation, i.evaluation);
        }
    }



}
