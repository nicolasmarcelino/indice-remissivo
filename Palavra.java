public class Palavra {
    String termo;
    ListaEncadeada ocorrencias;
    Palavra esquerda;
    Palavra direita;

    public Palavra(String termo) {
        this.termo = termo;
        this.ocorrencias = new ListaEncadeada();
        this.esquerda = null;
        this.direita = null;
    }

    public void adcOcorrencia(int posicao) {
        if (!ocorrencias.contem(posicao)) {
            ocorrencias.insereFinal(posicao);
        }
    }

    public void mostrarOcorrencias() {
        System.out.print(termo + ": ");
        ocorrencias.imprimirLista();
    }
}
