import java.util.NoSuchElementException;

public class ListaEncadeada {

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

     public void insereInicio(int elemento) {
          Nodo novo = new Nodo(elemento);
          novo.proximo = primeiro;
          primeiro = novo;

          if (ultimo == null) {
               ultimo = novo;
          }
          tamanho++;
     }

     // Método novo
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

     public void removeInicio() {
          if (primeiro == null) {
               throw new NoSuchElementException("Lista vazia");
          }

          primeiro = primeiro.proximo;
          tamanho--;

          if (tamanho == 0) {
               ultimo = null;
          }
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

     public void removeFinal() {
          if (ultimo == null) {
               throw new NoSuchElementException("Lista vazia");
          }

          if (primeiro == ultimo) {
               primeiro = ultimo = null;
          } else {
               Nodo atual = primeiro;
               while (atual.proximo != ultimo) {
                    atual = atual.proximo;
               }
               atual.proximo = null;
               ultimo = atual;
          }
          tamanho--;
     }

     public void inserePosicao(int elemento, int posicao) {
          if (posicao < 0 || posicao > tamanho)
               throw new IndexOutOfBoundsException("Posição inválida");

          if (posicao == 0) {
               insereInicio(elemento);
          } else if (posicao == tamanho) {
               insereFinal(elemento);
          } else {
               Nodo atual = primeiro;
               for (int i = 0; i < posicao - 1; i++)
                    atual = atual.proximo;

               Nodo novo = new Nodo(elemento);
               novo.proximo = atual.proximo;
               atual.proximo = novo;
               tamanho++;
          }
     }

     public void removePosicao(int posicao) {
          if (posicao < 0 || posicao >= tamanho)
               throw new IndexOutOfBoundsException("Posição inválida");

          if (posicao == 0) {
               removeInicio();
          } else if (posicao == tamanho - 1) {
               removeFinal();
          } else {
               Nodo atual = primeiro;
               for (int i = 0; i < posicao - 1; i++)
                    atual = atual.proximo;

               atual.proximo = atual.proximo.proximo;
               tamanho--;
          }
     }

     public void imprimirLista() {
          Nodo atual = primeiro;
          while (atual != null) {
               System.out.print(atual.elemento + " ");
               atual = atual.proximo;
          }
          System.out.println();
     }
}
