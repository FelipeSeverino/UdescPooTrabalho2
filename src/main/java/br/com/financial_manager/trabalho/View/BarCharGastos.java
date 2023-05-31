package br.com.financial_manager.trabalho.View;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import br.com.financial_manager.trabalho.Dados.Gasto;

public class BarCharGastos extends JPanel{
        private static final long serialVersionUID = 1L;
        private Principal principal = null;
     
        public BarCharGastos(Principal principal) {     
            this.principal = principal;

             // based on the dataset we create the chart
             JFreeChart pieChart = ChartFactory.createBarChart("Gastos", "Gasto", "Valor", createDataset(),PlotOrientation.VERTICAL, true, true, false);
     
             // Adding chart into a chart panel
             ChartPanel chartPanel = new ChartPanel(pieChart);
           
             // settind default size
             chartPanel.setPreferredSize(new java.awt.Dimension(550, 350));
           
             // add to contentPane
             setLayout(new GridLayout(1, 1));
             add(chartPanel);
         }
       
        private CategoryDataset createDataset() {

            // create the dataset...
            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            List<Gasto> gastos = principal.sistema.getGastos();
            for (Gasto gasto : gastos) {
                dataset.addValue(gasto.getValor(), gasto.getCategoria(), gasto.getNome());
            }
            
            return dataset;
          
       }
     
}
