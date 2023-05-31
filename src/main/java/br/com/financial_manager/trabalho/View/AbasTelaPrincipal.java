package br.com.financial_manager.trabalho.View;

import javax.swing.JTabbedPane;

public class AbasTelaPrincipal extends JTabbedPane implements GastosListener{

    private Principal principal = null;
    public AbasTelaPrincipal(Principal principal) {
        this.principal = principal;

        this.addTab("Adicionar Gasto", new AbaCriarGastosPane(principal));
        this.addTab("Tabela Gastos", new AbaTabelaGastos(principal));
        this.addTab("Remover / editar", new AbaRemoverEditarGastos(principal));
        this.addTab("Grafico anual", new ScatterChartGastos(principal));
        this.addTab("Visualizar gastos", new BarCharGastos(principal));

    }

    public void atualizarGastos() {
        this.remove(3);
        this.remove(3);

        this.addTab("Grafico anual", new ScatterChartGastos(principal));        
        this.addTab("Visualizar gastos", new BarCharGastos(principal));

    }
}
