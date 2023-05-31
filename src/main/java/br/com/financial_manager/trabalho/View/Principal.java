package br.com.financial_manager.trabalho.View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import br.com.financial_manager.trabalho.Negocio.Sistema;

public class Principal extends JFrame {
    public Sistema sistema;
    public List<GastosListener> gastosListener = new ArrayList<>();
    
    public Principal () {
        sistema = new Sistema();

        setTitle("Financial Manager");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.setVisible(false);

        Login telaDeLogin = new Login(principal);
        telaDeLogin.setVisible(true);

        AbasTelaPrincipal abas = new AbasTelaPrincipal(principal);
        principal.gastosListener.add(abas);
        principal.add(abas);
    }

    public void gastosObserver() {
        for (GastosListener listener : gastosListener) {
            listener.atualizarGastos();
        }
    }
}
