public class Hash {

     /* 
      * Cada bucket é uma árvore vazia contendo palavras
      * com a mesma inicial. A função hash retorna -1
      * para qualquer caractere que não seja uma letra minúscula
      * de 'a' a 'z'. Como cada bucket é uma árvore,
      * buscar e inserir são métodos da árvore.
      */

     private ArvoreBinariaBusca[] tabela;

     public Hash() {
          this.tabela = new ArvoreBinariaBusca[26];

          for (int i = 0; i < 26; i++) {
               tabela[i] = new ArvoreBinariaBusca();
          }
     }

     private int funcaoHash(String palavra) {
          if (palavra == null || palavra.isEmpty()) {
               return -1;
          }

          char inicial = palavra.charAt(0);

          if (inicial < 'a' || inicial > 'z') {
               return -1;
          }

          return inicial - 'a';
     }

     public void insere(String palavra, int linha) {
          int indice = funcaoHash(palavra);
          //System.out.println(indice);
          if (indice == -1)
               return;
          tabela[indice].insere(palavra, linha);
     }

     public Palavra busca(String palavra) {
          int indice = funcaoHash(palavra);
          return tabela[indice].busca(palavra);
     }
}
