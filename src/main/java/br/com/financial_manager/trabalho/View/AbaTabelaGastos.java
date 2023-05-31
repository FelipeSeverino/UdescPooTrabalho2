package br.com.financial_manager.trabalho.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.financial_manager.trabalho.Dados.Gasto;

public class AbaTabelaGastos extends JPanel{

    private PainelTabelaGastos painelTabelaGastos;
    private JButton filtrarButton = new JButton("Filtrar");
    ComboBoxCategorias comboBoxCategorias = new ComboBoxCategorias(new String[] { "", "Comida", "Lazer", "Educação", "Saúde", "Transporte", "Outros"});

    public AbaTabelaGastos(Principal principal) {
        painelTabelaGastos = new PainelTabelaGastos(principal);

        setLayout(new BorderLayout(5, 5));
        add(painelTabelaGastos);

        JPanel filtroPanel = new JPanel();
        filtroPanel.setLayout(new GridLayout(2, 3));
        
        //-----
        JPanel labelsFiltroPanel = new JPanel();
        labelsFiltroPanel.setLayout(new GridLayout(1, 3));

        JLabel mesLabel = new JLabel("MES");
        mesLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel categoriaLabel = new JLabel("CATEGORIA");
        categoriaLabel.setHorizontalAlignment(JLabel.CENTER);

        labelsFiltroPanel.add(mesLabel);
        labelsFiltroPanel.add(new JLabel());
        labelsFiltroPanel.add(categoriaLabel);
        //-----

        filtroPanel.add(labelsFiltroPanel);
    
        //------
        JPanel textFiltroPanel = new JPanel();
        textFiltroPanel.setLayout(new GridLayout(1, 3));
    

        JTextField textFieldMes = new JTextField(40);
        textFiltroPanel.add(textFieldMes);
        
        filtrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String mesAux = textFieldMes.getText();

                int mes = 0;
                if (mesAux.matches("\\d{1,2}")) {
                    mes = Integer.parseInt(mesAux);
                }

                String categoria = (String) comboBoxCategorias.getComboBox().getSelectedItem();

                if (mes == 0 && categoria.equals("")) {
                    principal.gastosObserver();
                    return;
                }

                List<Gasto> listaFiltrada = new ArrayList<>();
                for (Gasto gasto : principal.sistema.getGastos()) {
                    int mesGasto = Integer.parseInt(gasto.getData().substring(3, 5));
                    
                    if (mes != 0 && mes != mesGasto) {
                        continue;
                    }

                    if (!categoria.equals("") && !categoria.equals(gasto.getCategoria())) {
                        continue;
                    }

                    listaFiltrada.add(gasto);
                }

                painelTabelaGastos.getTabelaGastos().setListaGasto(listaFiltrada);
                painelTabelaGastos.getTabelaGastos().fireTableStructureChanged();
            }
        });
        textFiltroPanel.add(filtrarButton);

        textFiltroPanel.add(comboBoxCategorias);
        //------

        filtroPanel.add(textFiltroPanel);

        add(filtroPanel, BorderLayout.SOUTH);
        
    }
    
}
