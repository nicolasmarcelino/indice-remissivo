import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Main {
     public static void main(String[] args) {
          String caminho = "C:\\Projects\\indice-remissivo\\texto.txt";

          ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

          int counter = 0;

          try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {

               String line;
               // Lê até a última linha
               while ((line = reader.readLine()) != null) {
                    // Normaliza a linha
                    String linha_normalizada = normaliza(line);

                    // Separa as palavras e guarda em lista
                    String[] palavras_da_linha = linha_normalizada.split(" ");

                    for (String palavra : palavras_da_linha) {
                         if (palavra.startsWith("p")) {
                              arvore.insere(palavra, (counter+1));
                         }
                         
                    }

                    // Avança para a próxima linha
                    counter++;
               }

          } catch (FileNotFoundException e) {
               System.out.println("Arquivo não encontrado.");
          } catch (IOException e) {
               System.out.println("Erro.");
          }

          arvore.imprimeEmOrdem();
     }

     public static String normaliza(String linha) {
          // Separa caractere e acentuação
          String txt_base = Normalizer.normalize(linha, Normalizer.Form.NFD);

          // Remove acentuação
          String txt_sem_acentos = txt_base.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

          // Remove pontuação
          String txt_sem_pontuacao = txt_sem_acentos.replaceAll("\\p{Punct}", "");

          // Converte tudo para minúsculas
          String txt_normalizado = txt_sem_pontuacao.toLowerCase();

          return txt_normalizado;
     }

     public static void adicionarPalavra(List<Palavra> lista, String termo, int posicao) {
          // Recebe a palavra da linha
          for (Palavra p : lista) {
               // Já existe na lista/árvore?
               if (p.termo.equals(termo)) {
                    // Se sim, adiciona apenas a ocorrência
                    p.adcOcorrencia(posicao);
                    return;
               }
          }

          // Não existe na lista/árvore? Cria nova
          Palavra nova = new Palavra(termo);
          nova.adcOcorrencia(posicao);
          lista.add(nova);
     }
}
