public class ArvoreBinariaBusca {

     private Palavra raiz;
     private int nElementos;

     public ArvoreBinariaBusca() {
          this.raiz = null;
          this.nElementos = 0;
     }

     public int tamanho() {
          return this.nElementos;
     }

     public boolean estaVazia() {
          return this.raiz == null;
     }

     public void insere(String termo, int posicao) {
          if (raiz == null) {
               raiz = new Palavra(termo);
               raiz.adcOcorrencia(posicao);
               nElementos++;
               return;
          }

          Palavra atual = raiz;
          Palavra pai = null;

          while (atual != null) {
               pai = atual;
               int ordem = termo.compareTo(atual.termo);

               if (ordem < 0) {
                    atual = atual.esquerda;
               } else if (ordem > 0) {
                    atual = atual.direita;
               } else {
                    atual.adcOcorrencia(posicao);
                    return;
               }
          }

          Palavra novo = new Palavra(termo);
          novo.adcOcorrencia(posicao);

          if (termo.compareTo(pai.termo) < 0) {
               pai.esquerda = novo;
          } else {
               pai.direita = novo;
          }

          nElementos++;
     }

     public Palavra busca(String termo) {
          Palavra atual = raiz;

          while (atual != null) {
               int ordem = termo.compareTo(atual.termo);

               if (ordem < 0) {
                    atual = atual.esquerda;
               } else if (ordem > 0) {
                    atual = atual.direita;
               } else {
                    return atual;
               }
          }
          return null;
     }

     public void imprimeEmOrdem() {
          emOrdem(this.raiz);
          System.out.println();
     }

     private void emOrdem(Palavra atual) {
          if (atual == null)
               return;
          emOrdem(atual.esquerda);
          atual.mostrarOcorrencias();
          emOrdem(atual.direita);
     }
}
