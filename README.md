# 📊 SortingBenchmark (Projeto de Análise de Algoritmos)

Este é um projeto acadêmico desenvolvido em Java para analisar e comparar o desempenho de diferentes algoritmos de ordenação.

O objetivo principal é medir o tempo de execução de cada algoritmo em dois cenários específicos:
* **Melhor Caso:** Um vetor que já está ordenado.
* **Pior Caso:** Um vetor ordenado em ordem decrescente.

O programa executa os testes para diferentes tamanhos de vetor (N) e, ao final, gera dois gráficos de linha (um para cada cenário) para que se possa visualizar e comparar a complexidade de tempo de cada algoritmo.

## 🏛️ Arquitetura do Projeto

O projeto é dividido em três componentes principais e um pacote de algoritmos, demonstrando o uso de **Interfaces** e o **Padrão de Projeto Strategy**.

1.  **`AlgoritmoDeOrdenacao` (Interface)**
    * Este é o "contrato" principal do projeto. É uma interface que define um único método: `void ordenar(int[] vetor)`.
    * Qualquer classe de algoritmo que queira ser testada *deve* implementar esta interface.

2.  **Pacote `algoritmos/` (As Estratégias)**
    * Contém as implementações concretas (as "estratégias") da interface `AlgoritmoDeOrdenacao`.
    * Cada classe (`BubbleSort`, `QuickSort`, etc.) fornece sua própria lógica para o método `ordenar()`.

3.  **`ExecutorDeBenchmark.java` (O Motor)**
    * Esta classe é responsável por executar os testes.
    * Ela recebe um mapa com todos os algoritmos que devem ser testados.
    * Ela itera em um loop, aumentando o tamanho do vetor (N).
    * Para cada N, ela cria o `vetorMelhorCaso` (ordenado) e o `vetorPiorCaso` (ordem inversa).
    * Ela mede o tempo de execução (usando `System.nanoTime()`) de cada algoritmo em ambos os vetores.
    * Os resultados (N, Tempo) são armazenados em objetos `XYSeries` da biblioteca JFreeChart.

4.  **`GeradorDeGrafico.java` (A Visualização)**
    * Esta classe recebe os dados coletados pelo `ExecutorDeBenchmark`.
    * Ela usa a biblioteca **JFreeChart** para criar e exibir dois gráficos de linha em janelas Swing (`JFrame`).
    * **Gráfico 1:** "Complexidade de Ordenação (Melhor Caso)"
    * **Gráfico 2:** "Complexidade de Ordenação (Pior Caso)"

5.  **`Principal.java` (O Ponto de Partida)**
    * É a classe que contém o método `main()`.
    * Ela define os parâmetros do teste (tamanho inicial, final e o "passo" do vetor N).
    * Ela "cadastra" todos os algoritmos que serão testados em um `Map`.
    * Ela coordena a execução, chamando primeiro o `ExecutorDeBenchmark` e depois passando os resultados para o `GeradorDeGrafico`.

## 📈 Algoritmos Testados

O projeto está configurado para testar os seguintes 8 algoritmos:
* Bubble Sort
* Selection Sort
* Insertion Sort
* Quick Sort
* Merge Sort
* Radix Sort
* Heap Sort
* Shell Sort

## 🛠️ Dependências

Para compilar e executar, o projeto depende de duas bibliotecas externas:
* **JFreeChart (`jfreechart-1.5.3.jar`)**
* **JCommon (`jcommon-1.0.23.jar`)**

## 🚀 Como Executar

1.  **Importar o Projeto:** Importe o projeto em sua IDE (como o Eclipse).
2.  **Configurar o Build Path:** Adicione os arquivos `jfreechart-1.5.3.jar` e `jcommon-1.0.23.jar` ao Build Path do projeto.
3.  **Executar:** Encontre o arquivo `Principal.java` (em `src/br/com/projeto/`) e execute-o como uma Aplicação Java.
4.  **Visualizar:** Após alguns segundos (o console mostrará o progresso), duas janelas aparecerão, cada uma contendo um gráfico de resultados.

## Diagrama de Classes (UML)

```mermaid
classDiagram
    direction TD

    class Principal {
        +main(String[] args)
    }

    class ExecutorDeBenchmark {
        -Map&lt;String, AlgoritmoDeOrdenacao&gt; algoritmos
        -int nInicial, nFinal, passo
        +executarTestes() : Map
        -criarVetorMelhorCaso(int n) : int[]
        -criarVetorPiorCaso(int n) : int[]
    }

    class GeradorDeGrafico {
        +static criarEExibirGraficos(Map resultados)
        -static criarGrafico(...) : JFreeChart
        -static exibirGrafico(JFreeChart grafico, String titulo)
    }

    class AlgoritmoDeOrdenacao {
        <<Interface>>
        +ordenar(int[] vetor)
    }

    class JFreeChart {
        <<Biblioteca Externa>>
        +XYSeries
        +JFreeChart
        +ChartPanel
        +...
    }

    class Swing {
        <<Biblioteca Externa>>
        +JFrame
        +...
    }

    ' --- Relacionamentos Principais ---
    Principal ..> ExecutorDeBenchmark : cria e usa
    Principal ..> GeradorDeGrafico : usa
    Principal ..> AlgoritmoDeOrdenacao : usa (no Map)
    
    ExecutorDeBenchmark o-- "8" AlgoritmoDeOrdenacao : agrega (mapa de algoritmos)
    ExecutorDeBenchmark ..> JFreeChart : usa (XYSeries)
    
    GeradorDeGrafico ..> JFreeChart : usa (JFreeChart, ChartPanel)
    GeradorDeGrafico ..> Swing : usa (JFrame)

    ' --- Implementações (Estratégias) ---
    AlgoritmoDeOrdenacao <|-- BubbleSort
    AlgoritmoDeOrdenacao <|-- SelectionSort
    AlgoritmoDeOrdenacao <|-- InsertionSort
    AlgoritmoDeOrdenacao <|-- QuickSort
    AlgoritmoDeOrdenacao <|-- MergeSort
    AlgoritmoDeOrdenacao <|-- RadixSort
    AlgoritmoDeOrdenacao <|-- HeapSort
    AlgoritmoDeOrdenacao <|-- ShellSort

    class BubbleSort { +ordenar(int[] vetor) }
    class SelectionSort { +ordenar(int[] vetor) }
    class InsertionSort { +ordenar(int[] vetor) }
    class QuickSort { +ordenar(int[] vetor) }
    class MergeSort { +ordenar(int[] vetor) }
    class RadixSort { +ordenar(int[] vetor) }
    class HeapSort { +ordenar(int[] vetor) }
    class ShellSort { +ordenar(int[] vetor) }