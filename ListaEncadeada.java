public class ListaEncadeada {

     /*
      * TAD em grande parte reutilizado do disponibilizado do Ava,
      * pelos nós serem apenas números inteiros. contem() foi adicionado para busca.
      */

     private static class Nodo {
          int elemento;
          Nodo proximo;

          Nodo(int elemento) {
               this.elemento = elemento;
          }
     }

     private Nodo primeiro;
     private Nodo ultimo;
     private int tamanho;

     public boolean contem(int valor) {
          Nodo atual = primeiro;
          while (atual != null) {
               if (atual.elemento == valor) {
                    return true;
               }
               atual = atual.proximo;
          }
          return false;
     }

     public void insereFinal(int elemento) {
          Nodo novo = new Nodo(elemento);

          if (ultimo == null) {
               primeiro = ultimo = novo;
          } else {
               ultimo.proximo = novo;
               ultimo = novo;
          }
          tamanho++;
     }

     public void imprimirLista() {
          Nodo atual = primeiro;
          while (atual != null) {
               System.out.print(atual.elemento + " ");
               atual = atual.proximo;
          }
          System.out.println();
     }

     @Override
     public String toString() {
          String resultado = "";
          Nodo atual = primeiro;

          while (atual != null) {
               resultado += atual.elemento;
               if (atual.proximo != null) {
                    resultado += " ";
               }
               atual = atual.proximo;
          }

          return resultado;
     }

}
