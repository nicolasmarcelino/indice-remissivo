import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;

public class Main {
     public static void main(String[] args) {
          String path_texto = "C:\\Projects\\indice-remissivo\\texto.txt";
          String path_keywords = "C:\\Projects\\indice-remissivo\\palavras.txt";

          Hash tabela = new Hash();

          try (BufferedReader reader = new BufferedReader(new FileReader(path_texto))) {
               String linha;
               int counter = 1;

               while ((linha = reader.readLine()) != null) {
                    String linha_normalizada = normaliza(linha);
                    String[] palavras = linha_normalizada.split(" ");

                    for (String palavra : palavras) {
                         tabela.insere(palavra, counter);
                    }

                    counter++;
               }
          } catch (Exception e) {
               System.out.println("Erro lendo texto.");
          }

          // Índice vir-a-ser
          IndiceRemissivo toIndex = toIndex(path_keywords);

          // Indexação
          index(tabela, toIndex);
     }

     public static void index(Hash tabela, IndiceRemissivo keywords) {
          keywords.percorrer(tabela);
     }

     public static IndiceRemissivo toIndex(String path) {
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
