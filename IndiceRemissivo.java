import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;

class No {
    String valor;
    No proximo;

    No(String valor) {
        this.valor = valor;
        this.proximo = null;
    }
}

public class IndiceRemissivo {
    private No inicio;

    public IndiceRemissivo() {
        this.inicio = null;
    }

    public void inserir(String palavra) {
        No novo = new No(palavra);

        if (inicio == null) {
            inicio = novo;
            return;
        }

        No atual = inicio;
        while (atual.proximo != null) {
            atual = atual.proximo;
        }

        atual.proximo = novo;
    }

    public void percorrer(Hash tabela) {
        try (FileWriter writer = new FileWriter("indice.txt")) {

            No atual = inicio;
            while (atual != null) {

                // termo original vem da lista
                String termoOriginal = atual.valor;

                // normaliza
                String termoNormalizado = normaliza(termoOriginal);

                // busca na tabela
                Palavra p = tabela.busca(termoNormalizado);

                // escreve no arquivo
                if (p != null) {
                    writer.write(termoOriginal + " ");
                    writer.write(p.ocorrencias.toString() + "\n");
                } else {
                    writer.write(termoOriginal + " ---\n");
                }

                atual = atual.proximo;
            }

            System.out.println("Índice concluído.");

        } catch (IOException e) {
            System.out.println("Erro.");
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
