package br.com.financial_manager.trabalho.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.financial_manager.trabalho.Dados.Gasto;

public class AbaRemoverEditarGastos extends JPanel {
    private int codigo = -1;

    private JPanel panelBusca = new JPanel();
    private JButton buttonBuscar = new JButton("Buscar");
    private JTextField textCodigo = new JTextField(10);

    private JPanel panelTexts = new JPanel();
    private JTextField textFieldNome = new JTextField(40);
    private JTextField textFieldData = new JTextField(40);
    private JTextField textFieldDescricao = new JTextField(40);
    private JTextField textFieldValor = new JTextField(40);

    private ComboBoxCategorias comboBoxCategoria = new ComboBoxCategorias();

    private JPanel panelLabels = new JPanel();
    private JLabel labelNome = new JLabel("Nome");
    private JLabel labelData = new JLabel("Data");
    private JLabel labelDescricao = new JLabel("Descricao");
    private JLabel labelValor = new JLabel("Valor");
    private JLabel labelCategoria = new JLabel("categoria");

    private JPanel panelBotoes = new JPanel();
    private JButton buttonRemover = new JButton("Remover");
    private JButton buttonEditar = new JButton("Editar");

    public AbaRemoverEditarGastos(Principal principal) {
        setLayout(new BorderLayout(5, 5));
        panelBusca.setLayout(new GridLayout(1, 6));
        
        buttonBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                codigo = Integer.parseInt(textCodigo.getText());
                //Gasto gasto = principal.sistema.getGastos().get(codigo);
                try {
                    Gasto gasto = principal.sistema.getGasto(codigo);

                    textFieldNome.setText(gasto.getNome());
                    textFieldData.setText(gasto.getData());
                    textFieldDescricao.setText(gasto.getDescricao());
                    textFieldValor.setText(String.valueOf(gasto.getValor()));
                    // textFieldCategoria.setText(gasto.getCategoria());
    
                    comboBoxCategoria.getComboBox().setSelectedItem(gasto.getCategoria());
                } catch (Exception ex) {
                    textFieldNome.setText("");
                    textFieldData.setText("");
                    textFieldDescricao.setText("");
                    textFieldValor.setText("");
                    JOptionPane.showMessageDialog(principal, "Codigo invalido!");
                }
            }
        });

        panelBusca.add(new JLabel());
        panelBusca.add(new JLabel());
        panelBusca.add(buttonBuscar);
        panelBusca.add(textCodigo);
        panelBusca.add(new JLabel());
        panelBusca.add(new JLabel());

        add(panelBusca, BorderLayout.NORTH);

        //-----------------------------------------------------------------
        panelTexts.setLayout(new GridLayout(5, 1, 0, 10));
        panelTexts.add(textFieldNome);
        panelTexts.add(textFieldData);
        panelTexts.add(textFieldDescricao);
        panelTexts.add(textFieldValor);
        panelTexts.add(comboBoxCategoria);

        add(panelTexts, BorderLayout.CENTER);

        //-----------------------------------------------------------------

        panelLabels.setLayout(new GridLayout(5, 1, 0, 10));
        panelLabels.add(labelNome);
        panelLabels.add(labelData);
        panelLabels.add(labelDescricao);
        panelLabels.add(labelValor);
        panelLabels.add(labelCategoria);
        
        add(panelLabels, BorderLayout.WEST);

        //------------------------------------------------------------------

        buttonRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (principal.sistema.removerGasto(codigo)) {
                    JOptionPane.showMessageDialog(principal, "Gasto removido!");
                }
                else {JOptionPane.showMessageDialog(principal, "Codigo invalido!");}

                principal.gastosObserver();
                textFieldNome.setText("");
                textFieldData.setText("");
                textFieldDescricao.setText("");
                textFieldValor.setText("");
                comboBoxCategoria.getComboBox().setSelectedItem("");

            }
        });

        buttonEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Date data = null;
                try {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    data = dateFormat.parse(textFieldData.getText());
                }
                catch (Exception ex) {}

                Gasto gasto = new Gasto();
                gasto.setNome(textFieldNome.getText());
                gasto.setData(data);
                gasto.setDescricao(textFieldDescricao.getText());
                gasto.setValor(Double.parseDouble(textFieldValor.getText()));
                gasto.setCategoria((String) comboBoxCategoria.getComboBox().getSelectedItem());

                if (principal.sistema.alterarGasto(codigo, gasto)) {
                    principal.gastosObserver();
                    JOptionPane.showMessageDialog(principal, "Gasto editado!");
                }
                else {JOptionPane.showMessageDialog(principal, "Erro ao editar gasto!");}

                textFieldNome.setText("");
                textFieldData.setText("");
                textFieldDescricao.setText("");
                textFieldValor.setText("");
                comboBoxCategoria.getComboBox().setSelectedItem("");

            }
        });

        panelBotoes.setLayout(new GridLayout(1, 4, 5, 5));
        panelBotoes.add(new JLabel());
        panelBotoes.add(buttonRemover);
        panelBotoes.add(buttonEditar);
        panelBotoes.add(new JLabel());

        add(panelBotoes, BorderLayout.SOUTH);
    }
}
