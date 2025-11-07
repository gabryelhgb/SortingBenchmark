package br.com.projeto.algoritmos;

/**
 * Implementação do Selection Sort (Ordenação por Seleção).
 */
public class SelectionSort implements AlgoritmoDeOrdenacao {
    
    @Override
    public void ordenar(int[] vetor) {
        int n = vetor.length;
        
        for (int i = 0; i < n - 1; i++) {
            
            // Encontra o índice do menor elemento
            int indiceMenor = i; // 'minIdx' -> 'indiceMenor'
            for (int j = i + 1; j < n; j++) {
                if (vetor[j] < vetor[indiceMenor]) {
                    indiceMenor = j;
                }
            }
            
            // Troca o menor elemento com o elemento da posição 'i'
            int temporario = vetor[indiceMenor];
            vetor[indiceMenor] = vetor[i];
            vetor[i] = temporario;
        }
    }
}