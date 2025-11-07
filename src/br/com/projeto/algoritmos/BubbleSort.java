package br.com.projeto.algoritmos;

/**
 * Implementação do Bubble Sort (Ordenação por Bolha).
 */
public class BubbleSort implements AlgoritmoDeOrdenacao {
    
    @Override
    public void ordenar(int[] vetor) { // 'arr' -> 'vetor'
        int n = vetor.length;
        boolean trocou; // 'swapped' -> 'trocou'

        for (int i = 0; i < n - 1; i++) {
            trocou = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    // Troca os elementos
                    int temporario = vetor[j]; // 'temp' -> 'temporario'
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temporario;
                    trocou = true;
                }
            }
            // OTIMIZAÇÃO: Se não houve trocas, o vetor está ordenado.
            if (!trocou) {
                break;
            }
        }
    }
}