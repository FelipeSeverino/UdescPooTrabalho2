package br.com.financial_manager.trabalho.View;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import br.com.financial_manager.trabalho.Dados.Gasto;
import br.com.financial_manager.trabalho.Persistencia.GastoDAO;

public class ScatterChartGastos extends JPanel{
    private Principal principal = null;
    private GastoDAO gastoDao = null;

    public ScatterChartGastos(Principal principal) {
        this.principal = principal;
        try {
            gastoDao = GastoDAO.getInstace();
    
            // based on the dataset we create the chart
            JFreeChart chart = ChartFactory.createScatterPlot("Gastos anuais", "Mes", "Gasto", createDataset(),
            PlotOrientation.VERTICAL, true, true, true);
    
            // Changes background color
            XYPlot plot = (XYPlot) chart.getPlot();
            plot.setBackgroundPaint(new Color(255, 228, 196));
    
            // Adding chart into a chart panel
            ChartPanel chartPanel = new ChartPanel(chart);
    
            // settind default size
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
    
            setLayout(new GridLayout(1, 1));
            add(chartPanel);
        } catch(Exception ex) {JOptionPane.showConfirmDialog(principal, "Erro ao carregar grafico");}
    }

    private XYDataset createDataset() throws Exception{

        // create the dataset...
        final XYSeriesCollection dataset = new XYSeriesCollection();
    
        XYSeries gastosMensais = new XYSeries("Ano");
        
        List<Gasto> gastos = gastoDao.selectAllGasto();
        Map<Integer, Double> mapGastos = new HashMap<>();

        for (Gasto gasto : gastos) {
            int mes = Integer.parseInt(gasto.getData().substring(3, 5));

            Double valorMensal = mapGastos.get(mes);
            if (valorMensal == null) {
                valorMensal = Double.valueOf(0);
            }

            valorMensal += gasto.getValor();

            mapGastos.put(mes, valorMensal);
        }

        for (int mes = 1; mes <= 12; mes++) {
            Double valorTotal = mapGastos.get(mes);
            if (valorTotal == null) {
                valorTotal = Double.valueOf(0.0);
            }

            gastosMensais.add(mes, valorTotal.doubleValue());
        }

         
        
    
        dataset.addSeries(gastosMensais);
        
        return dataset;
    
    }
}
