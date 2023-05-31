package br.com.financial_manager.trabalho.View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.financial_manager.trabalho.Dados.Gasto;

public class TabelaGastos extends AbstractTableModel implements GastosListener{
    private Principal principal = null;
    private List<Gasto> listaGasto = null;

    public TabelaGastos(Principal principal) {
        this.principal = principal;

        listaGasto = new ArrayList<Gasto>();
        listaGasto.addAll(principal.sistema.getGastos());
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        return listaGasto.size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Gasto gasto = listaGasto.get(linha);

        if (coluna == 0) {
            if (listaGasto.size() != principal.sistema.getGastos().size()) {
                return -1;
            }

            return linha;
        }

        switch (coluna) {
            case 1:
                return gasto.getNome();
            case 2:
                return gasto.getData();
            case 3:
                return gasto.getDescricao();
            case 4:
                return gasto.getValor();
            case 5:
                return gasto.getCategoria();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Codigo";
            case 1:
                return "Nome";
            case 2:
                return "Data";
            case 3:
                return "Descrição";
            case 4:
                return "Valor";
            case 5:
                return "Categoria";
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void atualizarGastos() {
        // TODO Auto-generated method stub
        listaGasto.clear();
        listaGasto.addAll(principal.sistema.getGastos());
        
        this.fireTableStructureChanged();
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public List<Gasto> getListaGasto() {
        return listaGasto;
    }

    public void setListaGasto(List<Gasto> listaGasto) {
        this.listaGasto = listaGasto;
    }

    
}
