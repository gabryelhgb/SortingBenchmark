package br.com.projeto.algoritmos;

/**
 * Implementação do Shell Sort.
 * Variação do Insertion Sort com 'gaps' (intervalos).
 */
public class ShellSort implements AlgoritmoDeOrdenacao {
    
    @Override
    public void ordenar(int[] vetor) {
        int n = vetor.length;
        
        // Sequência de Gaps (Intervalos) de Ciura.
        int[] intervalos = {701, 301, 132, 57, 23, 10, 4, 1}; // 'gaps' -> 'intervalos'

        // Itera sobre cada 'intervalo'
        for (int intervalo : intervalos) { // 'gap' -> 'intervalo'
            
            if (intervalo < n) {
                
                // Insertion Sort modificado para este 'intervalo'
                for (int i = intervalo; i < n; i++) {
                    int temporario = vetor[i];
                    int j;
                    for (j = i; j >= intervalo && vetor[j - intervalo] > temporario; j -= intervalo) {
                        vetor[j] = vetor[j - intervalo];
                    }
                    vetor[j] = temporario;
                }
            }
        }
    }
}