package testesOrder;

import classeOrder.SortingAlgorithms;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class InsertionSortTest {


    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000, 20000, 30000};

        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries insertionSortSeriesAscending = new XYSeries("Insertion Sort Crescente");
        XYSeries insertionSortSeriesDescending = new XYSeries("Insertion Sort Decrescente");
        XYSeries insertionSortSeriesRandom = new XYSeries("Insertion Sort Aleátorio");

        for (int size : sizes) {
            int[] ascending = generateAscendingArray(size);
            int[] descending = generateDescendingArray(size);
            int[] random = generateRandomArray(size);

            long insertionSortTimeAscending = measureExecutionTime(() -> SortingAlgorithms.insertionSort(ascending.clone()));
            insertionSortSeriesAscending.add(size, insertionSortTimeAscending);

            long insertionSortTimeDescending = measureExecutionTime(() -> SortingAlgorithms.insertionSort(descending.clone()));
            insertionSortSeriesDescending.add(size, insertionSortTimeDescending);

            long insertionSortTimeRandom = measureExecutionTime(() -> SortingAlgorithms.insertionSort(random.clone()));
            insertionSortSeriesRandom.add(size, insertionSortTimeRandom);
        }

        dataset.addSeries(insertionSortSeriesAscending);
        dataset.addSeries(insertionSortSeriesDescending);
        dataset.addSeries(insertionSortSeriesRandom);

        createAndShowChart(dataset, "Insertion Sort");
    }

    private static void createAndShowChart(XYSeriesCollection dataset, String title) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Sorting Algorithm Performance - " + title,
                "Array Size",
                "Execution Time (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Exibição do gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);

        JFrame frame = new JFrame("Sorting Algorithms Performance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static long measureExecutionTime(Runnable function) {
        long startTime = System.currentTimeMillis();
        function.run();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private static int[] generateAscendingArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private static int[] generateDescendingArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }

    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size);
        }
        return arr;
    }
}