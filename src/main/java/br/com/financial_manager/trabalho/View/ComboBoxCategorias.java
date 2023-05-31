package br.com.financial_manager.trabalho.View;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ComboBoxCategorias extends JPanel{
    public JComboBox<String> comboBox = null;
    private String[] categorias = new String[] { "Comida", "Lazer", "Educação", "Saúde", "Transporte", "Outros"};
    
    public ComboBoxCategorias() {
        comboBox = new JComboBox<>(categorias);
        add(comboBox);
    }

    public ComboBoxCategorias(String[] categorias) {
        this.categorias = categorias;

        comboBox = new JComboBox<>(categorias);
        add(comboBox);
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    
}
