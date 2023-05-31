package br.com.financial_manager.trabalho.View;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.financial_manager.trabalho.Dados.Gasto;

public class AbaCriarGastosPane extends JPanel {
    private JPanel textos  =  new JPanel();
    private JPanel labels = new JPanel();

    private JTextField textFieldNome = new JTextField(40);
    private JTextField textFieldData = new JTextField(40);
    private JTextField textFieldDescricao = new JTextField(40);
    private JTextField textFieldValor = new JTextField(40);

    private JLabel labelNome = new JLabel("Nome     ");
    private JLabel labelData = new JLabel("Data     ");
    private JLabel labelDescricao = new JLabel("Descricao");
    private JLabel labelValor = new JLabel("Valor");

    private JButton enviarButton = new JButton("Cadastrar");


    public AbaCriarGastosPane(Principal principal) {
        setLayout(new BorderLayout(5, 5));
        
        textos.setLayout(new GridLayout(4, 1, 1, 5));

        textos.add(textFieldNome);
        textos.add(textFieldData);
        textos.add(textFieldDescricao);
        textos.add(textFieldValor);

        labels.setLayout(new GridLayout(4, 1));
        labels.add(labelNome);
        labels.add(labelData);
        labels.add(labelDescricao);
        labels.add(labelValor);

        add(textos, BorderLayout.CENTER);
        add(labels, BorderLayout.WEST);

        //----------------------------------
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1, 2, 2));
        
        ComboBoxCategorias comboBoxCategorias = new ComboBoxCategorias();


        enviarButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                try {
                    String nome = textFieldNome.getText();
                    
                    Date date = null;
                    
                    date = new SimpleDateFormat("dd/MM/yyyy")
                            .parse(textFieldData.getText());

                    
                    String categoria = (String) comboBoxCategorias.getComboBox().getSelectedItem();
                    

                    Gasto gasto = new Gasto(nome, date, textFieldDescricao.getText(), Double.parseDouble(textFieldValor.getText()), categoria);
                    principal.sistema.adicionarGasto(gasto);

                    principal.gastosObserver();

                    textFieldNome.setText("");
                    textFieldData.setText("");
                    textFieldDescricao.setText("");
                    textFieldValor.setText("");

                    JOptionPane.showMessageDialog(principal, "Gasto cadastrado");
                }
                catch (Exception ex) {}
            }
        });
        
        southPanel.add(comboBoxCategorias);
        southPanel.add(enviarButton);

        add(southPanel, BorderLayout.SOUTH);
    }
}