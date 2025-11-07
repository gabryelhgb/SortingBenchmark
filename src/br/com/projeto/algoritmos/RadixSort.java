package br.com.projeto.algoritmos;

import java.util.Arrays;

/**
 * Implementação do Radix Sort (Ordenação por Raiz/Base).
 * NOTA: Esta implementação só funciona para números NÃO-NEGATIVOS.
 */
public class RadixSort implements AlgoritmoDeOrdenacao {
    
    @Override
    public void ordenar(int[] vetor) {
        if (vetor.length == 0) return;
        
        // 1. Encontra o maior número
        int maximo = encontrarMaiorValor(vetor); // 'max' -> 'maximo'

        // 2. Faz o 'Counting Sort' para cada dígito (expoente 1, 10, 100...)
        for (int exp = 1; maximo / exp > 0; exp *= 10) {
            ordenacaoPorContagem(vetor, exp); // 'countSort' -> 'ordenacaoPorContagem'
        }
    }

    /**
     * Encontra o maior valor no vetor.
     */
    private int encontrarMaiorValor(int[] vetor) { // 'getMax' -> 'encontrarMaiorValor'
        int maximo = vetor[0];
        for (int i = 1; i < vetor.length; i++) {
            if (vetor[i] > maximo) {
                maximo = vetor[i];
            }
        }
        return Math.max(maximo, 0); // Garante que é no mínimo 0
    }

    /**
     * O 'Counting Sort' usado pelo Radix para ordenar pelo dígito 'exp'.
     */
    private void ordenacaoPorContagem(int[] vetor, int exp) {
        int n = vetor.length;
        int[] saida = new int[n]; // 'output' -> 'saida'
        int[] contagem = new int[10]; // 'count' -> 'contagem' (dígitos 0-9)
        Arrays.fill(contagem, 0);

        // 1. Conta a ocorrência de cada dígito
        for (int i = 0; i < n; i++) {
            int digito = (vetor[i] / exp) % 10;
            contagem[digito]++;
        }

        // 2. Ajusta 'contagem[i]' para que contenha a posição real
        for (int i = 1; i < 10; i++) {
            contagem[i] += contagem[i - 1];
        }

        // 3. Constrói o array de 'saida'
        for (int i = n - 1; i >= 0; i--) {
            int digito = (vetor[i] / exp) % 10;
            saida[contagem[digito] - 1] = vetor[i];
            contagem[digito]--;
        }

        // 4. Copia o array 'saida' de volta para 'vetor'
        System.arraycopy(saida, 0, vetor, 0, n);
    }
}