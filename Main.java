import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Normalizer;

public class Main {
     public static void main(String[] args) {
          // Define caminho do texto e da lista de palavras-chaves
          String path_texto = "C:\\Projects\\indice-remissivo\\texto.txt";
          String path_keywords = "C:\\Projects\\indice-remissivo\\palavras.txt";

          // Cria tabela
          Hash tabela = new Hash();

          // Lê o texto
          try (BufferedReader reader = new BufferedReader(new FileReader(path_texto))) {
               String linha;
               int counter = 1;

               while ((linha = reader.readLine()) != null) {
                    // Normaliza linha
                    String linha_normalizada = normaliza(linha);

                    // Separa as palavras normalizadas
                    String[] palavras = linha_normalizada.split(" ");

                    for (String palavra : palavras) {
                         // Insere a palavra e sua ocorrência nas estruturas
                         tabela.insere(palavra, counter);
                    }

                    counter++;
               }
          } catch (Exception e) {
               System.out.println("Erro lendo texto.");
          }

          // Coleta as palavras-chaves
          IndiceRemissivo palavras_chaves = coletarPalavrasChaves(path_keywords);

          // Cria index
          criarIndice(tabela, palavras_chaves);
     }

     public static void criarIndice(Hash tabela, IndiceRemissivo keywords) {
          keywords.percorrer(tabela);
     }

     public static IndiceRemissivo coletarPalavrasChaves(String path) {
          IndiceRemissivo ls = new IndiceRemissivo();

          try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
               String linha;

               while ((linha = reader.readLine()) != null) {
                    ls.inserir(linha.trim());
               }

          } catch (Exception e) {
               System.out.println("Erro ao ler glossário.");
          }

          return ls;
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
