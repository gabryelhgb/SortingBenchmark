package br.com.projeto.algoritmos;

/**
 * Interface (Contrato) AlgoritmoDeOrdenacao.
 *
 * Qualquer classe que implementar esta interface é obrigada a fornecer
 * um método 'ordenar' que recebe um vetor de inteiros.
 */
public interface AlgoritmoDeOrdenacao { // Interface 'SortAlgorithm' traduzida
    
    /**
     * Ordena o vetor 'vetor' em ordem crescente.
     * @param vetor O vetor a ser ordenado.
     */
    void ordenar(int[] vetor); // Método 'sort' traduzido
}