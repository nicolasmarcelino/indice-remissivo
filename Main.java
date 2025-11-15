import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;

public class Main {
     public static void main(String[] args) {
          String path_texto = "C:\\Projects\\indice-remissivo\\texto.txt";
          String path_glossario = "C:\\Projects\\indice-remissivo\\palavras.txt";

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

          String[] palavrasIndice = glossario(path_glossario);

          gerarIndiceRemissivo(tabela, palavrasIndice);
     }

     public static String[] glossario(String caminho) {
          int linhas = 0;

          try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
               while (reader.readLine() != null) {
                    linhas++;
               }
          } catch (Exception e) {
               System.out.println("Erro.");
               return new String[0];
          }

          String[] palavras = new String[linhas];

          try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
               String line;
               int i = 0;

               while ((line = reader.readLine()) != null) {
                    //System.out.println(line);
                    //System.out.println(line.trim());
                    palavras[i] = line.trim();
                    i++;
               }
          } catch (Exception e) {
               System.out.println("Erro.");
          }

          return palavras;
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
          try (FileWriter writer = new FileWriter("indice.txt")) {

               for (String termoOriginal : palavrasIndice) {
                    String termoNormalizado = normaliza(termoOriginal);
                    Palavra p = tabela.busca(termoNormalizado);

                    if (p != null) {
                         writer.write(termoOriginal + " ");
                         String ocorrencias = p.ocorrencias.toString();
                         writer.write(ocorrencias + "\n");
                    } else {
                         writer.write(termoOriginal + " ---\n");
                    }
               }
               System.out.println("Índice concluído.");
          } catch (IOException e) {
               System.out.println("Erro.");
          }
     }

}
