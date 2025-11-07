package br.com.projeto;

import br.com.projeto.algoritmos.AlgoritmoDeOrdenacao;
import org.jfree.data.xy.XYSeries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta classe executa os testes (benchmarks).
 * Ela gera os vetores de "Melhor Caso" e "Pior Caso"
 * e mede o tempo de execução de cada algoritmo.
 */
public class ExecutorDeBenchmark {

    // Variáveis que guardam as configurações
    private final Map<String, AlgoritmoDeOrdenacao> algoritmos;
    private final int nInicial, nFinal, passo;

    // Construtor
    public ExecutorDeBenchmark(Map<String, AlgoritmoDeOrdenacao> algoritmos, int nInicial, int nFinal, int passo) {
        this.algoritmos = algoritmos;
        this.nInicial = nInicial;
        this.nFinal = nFinal;
        this.passo = passo;
    }

    /**
     * Executa todos os benchmarks.
     * @return Um Map com os resultados para "MelhorCaso" e "PiorCaso".
     */
    public Map<String, Map<String, XYSeries>> executarTestes() {
        
        // Mapas para guardar as "séries" (linhas do gráfico)
        Map<String, XYSeries> resultadosMelhorCaso = new HashMap<>();
        Map<String, XYSeries> resultadosPiorCaso = new HashMap<>();

        // Para cada algoritmo, criamos uma "Série XY"
        for (String nome : algoritmos.keySet()) {
            resultadosMelhorCaso.put(nome, new XYSeries(nome));
            resultadosPiorCaso.put(nome, new XYSeries(nome));
        }

        // Loop principal: vai de N=100 até N=2000, de 100 em 100
        for (int n = nInicial; n <= nFinal; n += passo) {
            System.out.println("Testando para N = " + n);

            // 1. Gera os vetores de teste
            int[] vetorMelhorCaso = criarVetorMelhorCaso(n);  // Vetor ordenado
            int[] vetorPiorCaso = criarVetorPiorCaso(n); // Vetor em ordem decrescente

            // 2. Testa cada algoritmo da nossa lista
            for (Map.Entry<String, AlgoritmoDeOrdenacao> entrada : algoritmos.entrySet()) {
                String nome = entrada.getKey();
                AlgoritmoDeOrdenacao algoritmo = entrada.getValue();

                // --- Teste de Pior Caso ---
                try {
                    // IMPORTANTE: Criamos uma CÓPIA do vetor
                    int[] copiaPiorCaso = Arrays.copyOf(vetorPiorCaso, vetorPiorCaso.length);

                    long tempoInicioPior = System.nanoTime(); // Mede o tempo ANTES
                    algoritmo.ordenar(copiaPiorCaso); // Executa a ordenação
                    long tempoFimPior = System.nanoTime(); // Mede o tempo DEPOIS
                    
                    // Adiciona o ponto (N, Tempo) no gráfico de Pior Caso
                    resultadosPiorCaso.get(nome).add(n, (tempoFimPior - tempoInicioPior));
                
                } catch (StackOverflowError e) {
                    System.out.println("AVISO: StackOverflow (estouro de pilha) no " + nome + " para N=" + n);
                }

                // --- Teste de Melhor Caso ---
                int[] copiaMelhorCaso = Arrays.copyOf(vetorMelhorCaso, vetorMelhorCaso.length);
                
                long tempoInicioMelhor = System.nanoTime();
                algoritmo.ordenar(copiaMelhorCaso);
                long tempoFimMelhor = System.nanoTime();
                
                // Adiciona o ponto (N, Tempo) no gráfico de Melhor Caso
                resultadosMelhorCaso.get(nome).add(n, (tempoFimMelhor - tempoInicioMelhor));
            }
        }

        // Junta os resultados em um mapa final
        Map<String, Map<String, XYSeries>> resultadosFinais = new HashMap<>();
        resultadosFinais.put("MelhorCaso", resultadosMelhorCaso);
        resultadosFinais.put("PiorCaso", resultadosPiorCaso);
        
        return resultadosFinais;
    }

    /**
     * Cria um vetor já ordenado (Melhor Caso para Bubble, Insertion).
     */
    private int[] criarVetorMelhorCaso(int n) {
        int[] vetor = new int[n];
        for (int i = 0; i < n; i++) {
            vetor[i] = i; // Ex: [0, 1, 2, 3, 4, ...]
        }
        return vetor;
    }

    /**
     * Cria um vetor em ordem decrescente (Pior Caso para Bubble, Insertion, Quick).
     */
    private int[] criarVetorPiorCaso(int n) {
        int[] vetor = new int[n];
        for (int i = 0; i < n; i++) {
            vetor[i] = n - 1 - i; // Ex: [4, 3, 2, 1, 0] para N=5
        }
        return vetor;
    }
}