public class Palavra {

    /*
     * A palavra é um nó da árvore, portanto, possui
     * propriedades de um nó. Toda palavra tem uma lista de ocorrências,
     * vazias ou não. Como a lista de ocorrências é uma lista encadeada,
     * buscar, inserir e imprimir são métodos da lista.
     */

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
