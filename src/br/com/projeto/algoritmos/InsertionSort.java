package br.com.projeto.algoritmos;

/**
 * Implementação do Insertion Sort (Ordenação por Inserção).
 */
public class InsertionSort implements AlgoritmoDeOrdenacao {
    
    @Override
    public void ordenar(int[] vetor) {
        int n = vetor.length;
        
        for (int i = 1; i < n; ++i) {
            int chave = vetor[i]; // 'key' -> 'chave'
            int j = i - 1;

            /* Move os elementos da parte ordenada (vetor[0...i-1]) 
             * que são maiores que a 'chave', para uma posição à frente
             */
            while (j >= 0 && vetor[j] > chave) {
                vetor[j + 1] = vetor[j];
                j = j - 1;
            }
            // Insere a 'chave' na posição correta
            vetor[j + 1] = chave;
        }
    }
}