import java.util.function.Function;
import java.util.function.Predicate;

public class Fila<E> {
    private Celula primeiro, ultimo;

    public Fila(){
        primeiro = new Celula<>();
        ultimo = primeiro;
    }

    public void inserir(int x){
        ultimo.prox = new Celula<>(x);
        ultimo = ultimo.prox;
    }

    public int remover(){
        if(primeiro == ultimo){
            System.out.println("Erro ao remover: fila vazia");
            return -1;
        }
        Celula<E> tmp = primeiro;

        primeiro = primeiro.prox;

        int resp = (int) primeiro.getItem();

        tmp.prox = null;

        return resp;
    }
    
    public void mostrar(){
        Celula<E> i;
        for(i = primeiro.prox; i != null; i = i.prox){
            System.out.print(i.getItem() + " ");
        }
        System.out.println();
    }

    public double calcularValorMedio(Function<E, Double> extrator, int quantidade) {
        double soma = 0.0;
        Celula<E> atual = primeiro.prox;

        while (atual != null) {
            soma += extrator.apply(atual.getItem());
            atual = atual.prox;
        }

        return quantidade > 0 ? soma / quantidade : 0.0;
    }

    public Fila<E> filtrar(Predicate<E> condicional, int quantidade){
        Fila<E> novaFila = new Fila<>();
        Celula<E> atual = primeiro.prox;
        int count = 0;

        while (atual != null && count < quantidade) {
            if (condicional.test(atual.getItem())) {
                novaFila.inserir((int) atual.getItem());
            }
            atual = atual.prox;
            count++;
        }

        return novaFila;
    }
}
