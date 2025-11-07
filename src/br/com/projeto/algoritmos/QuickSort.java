package br.com.projeto.algoritmos;

/**
 * Implementação do Quick Sort (Ordenação Rápida).
 * Pior Caso: O(n^2) (quando o pivô é sempre o menor/maior)
 */
public class QuickSort implements AlgoritmoDeOrdenacao {
    
    @Override
    public void ordenar(int[] vetor) {
        quickSort(vetor, 0, vetor.length - 1);
    }

    /**
     * Método recursivo principal.
     */
    private void quickSort(int[] vetor, int inicio, int fim) { // 'low' -> 'inicio', 'high' -> 'fim'
        if (inicio < fim) {
            // 'pivoIndice' é o índice do pivô, que já está no lugar certo
            int pivoIndice = particionar(vetor, inicio, fim); // 'pi' -> 'pivoIndice'
            
            // Ordena recursivamente os elementos antes e depois do pivô
            quickSort(vetor, inicio, pivoIndice - 1);
            quickSort(vetor, pivoIndice + 1, fim);
        }
    }

    /**
     * Particiona o vetor.
     */
    private int particionar(int[] vetor, int inicio, int fim) {
        // Usa o último elemento como pivô (força o pior caso em vetor ordenado)
        int pivoValor = vetor[fim]; // 'pivot' -> 'pivoValor'
        
        int i = (inicio - 1); // Índice do último elemento menor que o pivô

        for (int j = inicio; j < fim; j++) {
            // Se o elemento atual for menor que o pivô
            if (vetor[j] < pivoValor) {
                i++;
                // Troca vetor[i] com vetor[j]
                int temporario = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temporario;
            }
        }

        // Coloca o pivô no seu lugar correto (i+1)
        int temporario = vetor[i + 1];
        vetor[i + 1] = vetor[fim];
        vetor[fim] = temporario;

        return i + 1; // Retorna a posição final do pivô
    }
}