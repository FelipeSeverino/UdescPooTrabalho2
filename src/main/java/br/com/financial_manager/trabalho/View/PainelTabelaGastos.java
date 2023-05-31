package br.com.financial_manager.trabalho.View;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PainelTabelaGastos extends JScrollPane{
    TabelaGastos tabelaGastos;

    public PainelTabelaGastos(Principal principal) {
        tabelaGastos = new TabelaGastos(principal);
        principal.gastosListener.add(tabelaGastos);
        
        JTable tabela = new JTable(tabelaGastos);
        setViewportView(tabela);
    }

    public TabelaGastos getTabelaGastos() {
        return tabelaGastos;
    }

    public void setTabelaGastos(TabelaGastos tabelaGastos) {
        this.tabelaGastos = tabelaGastos;
    }

    
}
