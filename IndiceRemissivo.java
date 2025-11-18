import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class IndiceRemissivo {

    /*
     * Esta classe é responsável por receber uma lista de palavras-chaves
     * e gerar um índice remissivo em um .txt.
     */

    private String[] palavrasChave;

    public IndiceRemissivo(String[] palavrasChave) {
        this.palavrasChave = palavrasChave;

        Arrays.sort(this.palavrasChave);
    }

    public void percorrer(Hash tabela) {
        try (FileWriter writer = new FileWriter("indice.txt")) {

            for (int i = 0; i < palavrasChave.length; i++) {

                String termoOriginal = palavrasChave[i];
                String termoNormalizado = Main.normaliza(termoOriginal);

                Palavra p = tabela.busca(termoNormalizado);

                if (p != null) {
                    writer.write(termoOriginal + " ");
                    writer.write(p.ocorrencias.toString() + "\n");
                } else {
                    writer.write(termoOriginal + " ---\n");
                }
            }

            System.out.println("Índice concluído.");

        } catch (IOException e) {
            System.out.println("Erro ao gerar índice.");
        }
    }
}