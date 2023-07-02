package br.com.financial_manager.trabalho.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.financial_manager.trabalho.Dados.Usuario;

public class Login extends JFrame {
    private JTextField textFieldNome        = new JTextField(40);
    private JTextField textFieldSenha       = new JTextField(40);
    private JButton    logarButton          = new JButton("Logar");
    private JButton    cadastrarButton      = new JButton("Cadastrar");
    private JLabel     labelConfirmacao     = new JLabel();

    public Login(Principal telaPrincipal) {
        setTitle("Login");
        setBounds(50, 50, 600, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setBackground(Color.DARK_GRAY);
        setLayout(new BorderLayout(5,5));

        logarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (telaPrincipal.sistema.login(textFieldNome.getText(), textFieldSenha.getText())) {
                    setVisible(false);
                    telaPrincipal.setVisible(true);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(telaPrincipal, "Credenciais inválidas!");
                }

            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Usuario tentativa = new Usuario(textFieldNome.getText(), textFieldSenha.getText());

                if (telaPrincipal.sistema.cadastrarUsuario(tentativa)) {
                    JOptionPane.showMessageDialog(telaPrincipal, "Usuário cadastrado!");
                }
                else {
                    JOptionPane.showMessageDialog(telaPrincipal, "Erro ao cadastrar usuario");
                }
            }
        });

        JPanel labels = new JPanel(new GridLayout(0, 1));
        labels.add(new JLabel("Nome"));
        labels.add(new JLabel("Senha"));

        JPanel textos = new JPanel(new GridLayout(0, 1));
        textos.add(textFieldNome);
        textos.add(textFieldSenha);

        JPanel botoes = new JPanel(new GridLayout(1, 0));
        botoes.add(new JLabel());
        botoes.add(logarButton);
        botoes.add(labelConfirmacao);
        botoes.add(cadastrarButton);
        botoes.add(new JLabel());

        add(labels, BorderLayout.WEST);
        add(textos, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);
    }
}
