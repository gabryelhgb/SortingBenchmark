package br.com.projeto;

// Importações do JFreeChart (para desenhar) e Swing (para criar a janela)
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Esta classe gera os gráficos de linha usando a biblioteca JFreeChart.
 */
public class GeradorDeGrafico { // Classe 'ChartGenerator' traduzida

    /**
     * Pega os resultados e cria e exibe as duas janelas de gráfico.
     */
    public static void criarEExibirGraficos(Map<String, Map<String, XYSeries>> resultados) {
        
        // --- 1. Preparar os "Datasets" (Conjunto de Dados) ---
        // Um "Dataset" é uma coleção de "Séries". Cada "Série" é uma linha.

        // Cria o dataset para o Gráfico 1 (Melhor Caso)
        XYSeriesCollection conjuntoDadosMelhorCaso = new XYSeriesCollection();
        Map<String, XYSeries> seriesMelhorCaso = resultados.get("MelhorCaso");
        for (XYSeries serie : seriesMelhorCaso.values()) {
            conjuntoDadosMelhorCaso.addSeries(serie); // Adiciona a linha de cada algoritmo
        }

        // Cria o dataset para o Gráfico 2 (Pior Caso)
        XYSeriesCollection conjuntoDadosPiorCaso = new XYSeriesCollection();
        Map<String, XYSeries> seriesPiorCaso = resultados.get("PiorCaso");
        for (XYSeries serie : seriesPiorCaso.values()) {
            conjuntoDadosPiorCaso.addSeries(serie);
        }

        // --- 2. Criar os Gráficos ---

        // Cria o Gráfico 1
        JFreeChart graficoMelhorCaso = criarGrafico(
                "Complexidade de Ordenação (Melhor Caso)", // Título
                "Tamanho do Vetor (N)",                    // Rótulo Eixo X
                "Tempo (nanossegundos)",                   // Rótulo Eixo Y
                conjuntoDadosMelhorCaso                    // Dados
        );

        // Cria o Gráfico 2
        JFreeChart graficoPiorCaso = criarGrafico(
                "Complexidade de Ordenação (Pior Caso)",
                "Tamanho do Vetor (N)",
                "Tempo (nanossegundos)",
                conjuntoDadosPiorCaso
        );

        // --- 3. Exibir os Gráficos ---

        // Cada gráfico será exibido em sua própria janela (JFrame)
        exibirGrafico(graficoMelhorCaso, "Gráfico 1: Melhor Caso");
        exibirGrafico(graficoPiorCaso, "Gráfico 2: Pior Caso");
    }

    /**
     * Método auxiliar que cria e configura um objeto JFreeChart.
     */
    private static JFreeChart criarGrafico(String titulo, String rotuloEixoX, String rotuloEixoY, XYSeriesCollection conjuntoDados) {
        
        JFreeChart grafico = ChartFactory.createXYLineChart(
                titulo,
                rotuloEixoX,
                rotuloEixoY,
                conjuntoDados,
                PlotOrientation.VERTICAL, // Orientação
                true,  // Incluir legenda? Sim
                true,  // Incluir tooltips? Sim
                false  // Incluir URLs? Não
        );

        // --- Customização Opcional (Estética) ---
        
        XYPlot areaGrafico = grafico.getXYPlot();
        XYLineAndShapeRenderer renderizador = new XYLineAndShapeRenderer();
        
        // Deixa as linhas um pouco mais grossas
        for(int i = 0; i < conjuntoDados.getSeriesCount(); i++) {
             renderizador.setSeriesStroke(i, new BasicStroke(2.0f));
        }
        areaGrafico.setRenderer(renderizador);

        // Define cores
        areaGrafico.setBackgroundPaint(Color.WHITE);
        areaGrafico.setRangeGridlinePaint(Color.LIGHT_GRAY);
        areaGrafico.setDomainGridlinePaint(Color.LIGHT_GRAY);

        // Remove a borda da legenda
        grafico.getLegend().setFrame(BlockBorder.NONE);
        
        // Deixa o título principal em negrito
        grafico.setTitle(new org.jfree.chart.title.TextTitle(titulo,
                        new java.awt.Font("SansSerif", java.awt.Font.BOLD, 18)
                )
        );

        return grafico;
    }

    /**
     * Método auxiliar que cria uma janela (JFrame) e coloca o gráfico nela.
     */
    private static void exibirGrafico(JFreeChart grafico, String tituloJanela) {
        
        // Um 'ChartPanel' é um componente Swing que contém o gráfico
        ChartPanel painelGrafico = new ChartPanel(grafico);
        painelGrafico.setPreferredSize(new java.awt.Dimension(800, 600)); // Tamanho
        
        // Um 'JFrame' é a janela
        JFrame janela = new JFrame(tituloJanela);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        janela.setContentPane(painelGrafico); // Coloca o gráfico na janela
        
        janela.pack(); // Ajusta o tamanho
        janela.setLocationRelativeTo(null); // Centraliza na tela
        janela.setVisible(true); // Mostra a janela
    }
}