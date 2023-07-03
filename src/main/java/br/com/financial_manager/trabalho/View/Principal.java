package br.com.financial_manager.trabalho.View;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JFrame;

import br.com.financial_manager.trabalho.Dados.Gasto;
import br.com.financial_manager.trabalho.Negocio.Sistema;
import br.com.financial_manager.trabalho.Persistencia.Conexao;
import br.com.financial_manager.trabalho.Persistencia.GastoDAO;

public class Principal extends JFrame {
    public Sistema sistema;
    public List<GastosListener> gastosListener = new ArrayList<>();

    private String filtroMes = null;
    private String filtroCategoria = null;
    
    public Principal () {
        sistema = new Sistema();

        setTitle("Financial Manager");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Conexao.setSenha("1234");
        try {
            GastoDAO gastoDao = GastoDAO.getInstace();
            GregorianCalendar cal = new GregorianCalendar(2023, 07, 03);
            Gasto gasto = new Gasto("Teste", cal.getTime(), "Teste1", 29, "Inicio");
            gastoDao.insert(gasto);

        } catch (Exception ex) {System.out.println("ERRO GASTODAO" + ex.getMessage());}

        try {
            ResultSet teste = GastoDAO.getInstace().selectAll();

            while (teste.next()) {
                System.out.println(teste.getString(2));
                Date data = teste.getDate(3);
                System.out.println("Data -> " + data);
            }
        } catch (Exception ex) {System.out.println("Erro -> " + ex.toString());}


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

    public String getFiltroMes() {
        return filtroMes;
    }

    public void setFiltroMes(String filtroMes) {
        this.filtroMes = filtroMes;
    }

    public String getFiltroCategoria() {
        return filtroCategoria;
    }

    public void setFiltroCategoria(String filtroCategoria) {
        this.filtroCategoria = filtroCategoria;
    }

    
}
