package br.com.projeto.algoritmos;

/**
 * Implementação do Heap Sort.
 * Complexidade: O(n log n) em todos os casos.
 */
public class HeapSort implements AlgoritmoDeOrdenacao {
    
    @Override
    public void ordenar(int[] vetor) {
        int n = vetor.length;

        // 1. Constrói o "Max Heap" (árvore onde o pai é sempre maior que os filhos)
        // Começa da metade, pois os últimos n/2 são folhas
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(vetor, n, i);
        }

        // 2. Extrai elementos um por um do heap
        for (int i = n - 1; i > 0; i--) {
            // Move a raiz atual (maior elemento) para o fim do vetor
            int temporario = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = temporario;

            // Chama 'heapify' no heap reduzido (tamanho 'i')
            heapify(vetor, i, 0);
        }
    }

    /**
     * Converte uma subárvore em um Max Heap.
     * @param vetor O vetor
     * @param n O tamanho do heap
     * @param i O índice da raiz da subárvore
     */
    // 'heapify' é um termo técnico, então mantive o nome
    private void heapify(int[] vetor, int n, int i) {
        int maiorIndice = i;     // 'largest' -> 'maiorIndice' (Inicializa o maior como a raiz)
        int esq = 2 * i + 1; // Índice do filho da esquerda
        int dir = 2 * i + 2; // Índice do filho da direita

        // Se o filho da esquerda for maior que a raiz
        if (esq < n && vetor[esq] > vetor[maiorIndice]) {
            maiorIndice = esq;
        }

        // Se o filho da direita for maior que o 'maiorIndice' atual
        if (dir < n && vetor[dir] > vetor[maiorIndice]) {
            maiorIndice = dir;
        }

        // Se o 'maiorIndice' não for a raiz
        if (maiorIndice != i) {
            // Troca a raiz com o maior
            int troca = vetor[i];
            vetor[i] = vetor[maiorIndice];
            vetor[maiorIndice] = troca;

            // Recursivamente, continua "descendo" o heapify
            heapify(vetor, n, maiorIndice);
        }
    }
}