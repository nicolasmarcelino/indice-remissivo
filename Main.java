import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Normalizer;

public class Main {
     public static void main(String[] args) {
          
          // Caminho do texto e das palavras-chaves
          String path_texto = "C:\\Projects\\indice-remissivo\\texto.txt";
          String path_keywords = "C:\\Projects\\indice-remissivo\\palavras.txt";

          // Tabela hash com 26 buckets do tipo ArvoreBinariaBusca
          Hash tabela = new Hash();

          // Leitura do texto
          try (BufferedReader reader = new BufferedReader(new FileReader(path_texto))) {
               String linha;
               int counter = 1;

               while ((linha = reader.readLine()) != null) {
                    // Normaliza linha
                    String linha_normalizada = normaliza(linha);

                    // Separa as palavras normalizadas
                    String[] palavras = linha_normalizada.split(" ");

                    // Inserção na hash -> árvores
                    for (String palavra : palavras) {
                         // Insere a palavra e sua ocorrência nas estruturas
                         tabela.insere(palavra, counter);
                    }

                    counter++;
               }
          } catch (Exception e) {
               System.out.println("Erro ao ler texto.");
          }

          // Instância das palavra-chaves
          String[] palavras_chaves = null;

          // Leitura das palavras-chaves (ex.: programming,programs,easy,by,human-engineered,and,be,to)
          try (BufferedReader reader = new BufferedReader(new FileReader(path_keywords))) {
               String linha = reader.readLine();

               if (linha != null) {
                    palavras_chaves = linha.split(",");
               }

          } catch (Exception e) {
               System.out.println("Erro ao ler as palavras-chave.");
          }

          if (palavras_chaves != null) {
               IndiceRemissivo indice = new IndiceRemissivo(palavras_chaves);
               indice.percorrer(tabela);
          } else {
               System.out.println("Nenhuma palavra-chave carregada.");
          }
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

}
