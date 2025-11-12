import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

public class Main {
     public static void main(String[] args) {
          String caminho = "C:\\Projects\\indice-remissivo\\texto.txt";

          Hash tabela = new Hash();

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
                         tabela.insere(palavra, (counter + 1));
                    }

                    // Avança para a próxima linha
                    counter++;
               }

          } catch (FileNotFoundException e) {
               System.out.println("Arquivo não encontrado.");
          } catch (IOException e) {
               System.out.println("Erro.");
          }

          String[] palavrasIndice = { "calvo", "amigo", "chuva", "pré-anuncio", "beija-flor" };
          gerarIndiceRemissivo(tabela, palavrasIndice);
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

     public static void gerarIndiceRemissivo(Hash tabela, String[] palavrasIndice) {
          for (String termoOriginal : palavrasIndice) {
               String termoNormalizado = normaliza(termoOriginal);
               Palavra p = tabela.busca(termoNormalizado);

               if (p != null) {
                    System.out.print(termoOriginal + " ");
                    p.ocorrencias.imprimirLista();
               } else {
                    System.out.println(termoOriginal + " ---");
               }
          }
     }

}
