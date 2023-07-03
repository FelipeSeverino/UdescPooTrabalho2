package br.com.financial_manager.trabalho.Dados;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Gasto {
    private int codigo;
    private String nome;
    private Date data;
    private String descricao;
    private double valor;
    private String categoria;
    
    public Gasto() {}

    public Gasto(String nome, Date data, String descricao, double valor, String categoria) {
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        return dateFormat.format(data);
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDate() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    } 
    
    

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Gasto)) {
            return false;
        }

        Gasto candidate = (Gasto) obj;
        if (nome.equals(candidate.getNome()) && data.equals(candidate.getData()) && valor == candidate.getValor()) {
            return true;
        }

        return false;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
