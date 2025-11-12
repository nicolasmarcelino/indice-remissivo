public class Hash {

     // Vetor fixo de 26 árvores binárias de busca (A–Z)
     private ArvoreBinariaBusca[] tabela;

     public Hash() {
          // Cria 26 compartimentos, um para cada letra do alfabeto
          this.tabela = new ArvoreBinariaBusca[26];

          // Inicializa cada posição com uma nova árvore
          for (int i = 0; i < 26; i++) {
               tabela[i] = new ArvoreBinariaBusca();
          }
     }

     // Função hash simples: transforma a letra inicial da palavra em índice (0 a 25)
     private int funcaoHash(String palavra) {
          if (palavra == null || palavra.isEmpty()) {
               // retorna -1 para sinalizar "inválido"
               return -1;
          }

          char inicial = palavra.charAt(0);
          return inicial - 'a';
     }

     // Insere a palavra na árvore correspondente ao seu bucket
     public void insere(String palavra, int linha) {
          int indice = funcaoHash(palavra);
          if (indice == -1) return;
          tabela[indice].insere(palavra, linha);
     }

     // Busca uma palavra na árvore correspondente à sua letra inicial
     public Palavra busca(String palavra) {
          int indice = funcaoHash(palavra);
          return tabela[indice].busca(palavra);
     }
}
