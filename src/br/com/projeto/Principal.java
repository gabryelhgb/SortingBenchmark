package br.com.projeto;

// Importamos as classes que vamos usar
import br.com.projeto.algoritmos.*; // O '*' importa TODAS as classes do pacote 'algoritmos'
import org.jfree.data.xy.XYSeries; // Classe do JFreeChart para guardar dados (pontos X, Y)

import java.util.HashMap;
import java.util.Map;

/**
 * Classe Principal.
 *
 * Ponto de partida da aplicação.
 * 1. Define quais algoritmos testar.
 * 2. Chama o 'ExecutorDeBenchmark' para rodar os testes.
 * 3. Chama o 'GeradorDeGrafico' para desenhar os gráficos.
 */
public class Principal { // Classe 'Main'

    public static void main(String[] args) {
        
        // --- 1. Definições do Benchmark ---
        
        // Define o intervalo de 'N' (tamanho do vetor)
        int nInicial = 100;    // Começa com N = 100
        int nFinal = 2000;     // Termina com N = 2000
        int passo = 100;       // Testa de 100 em 100 (100, 200, 300, ...)

        /*
         * Usamos um 'Map' para ligar o nome de um algoritmo (String)
         * à sua implementação (um objeto do tipo AlgoritmoDeOrdenacao).
         */
        Map<String, AlgoritmoDeOrdenacao> algoritmos = new HashMap<>();
        
        // Mantive os nomes em inglês pois são nomes "próprios" dos algoritmos
        algoritmos.put("1. Bubble Sort", new BubbleSort());
        algoritmos.put("2. Selection Sort", new SelectionSort());
        algoritmos.put("3. Insertion Sort", new InsertionSort());
        algoritmos.put("4. Quick Sort", new QuickSort());
        algoritmos.put("5. Merge Sort", new MergeSort());
        algoritmos.put("6. Radix Sort", new RadixSort());
        algoritmos.put("7. Heap Sort", new HeapSort());
        algoritmos.put("8. Shell Sort", new ShellSort());

        // --- 2. Execução dos Testes ---
        
        System.out.println("Iniciando benchmarks... Isso pode demorar alguns segundos.");

        // Criamos uma instância do 'ExecutorDeBenchmark'
        ExecutorDeBenchmark executor = new ExecutorDeBenchmark(algoritmos, nInicial, nFinal, passo);
        
        // Chamamos o método que executa os testes.
        Map<String, Map<String, XYSeries>> resultados = executor.executarTestes();

        System.out.println("Benchmarks concluídos. Gerando gráficos...");

        // --- 3. Geração dos Gráficos ---
        
        // Passamos os resultados para a classe que desenha os gráficos
        GeradorDeGrafico.criarEExibirGraficos(resultados);
        
        System.out.println("Programa finalizado. Veja as janelas dos gráficos.");
    }
}