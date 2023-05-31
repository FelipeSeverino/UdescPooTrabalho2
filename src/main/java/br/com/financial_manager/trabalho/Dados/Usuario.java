package br.com.financial_manager.trabalho.Dados;

public class Usuario {
    private String nome;
    private String senha;
    
    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean login (Usuario candidate) {
        if (candidate.getNome().equals(nome) && candidate.getSenha().equals(senha)) {
            return true;
        }
        
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Usuario)) {
            return false;
        }

        Usuario candidate = (Usuario) obj;
        if (nome.equals(candidate.getNome())) {
            return true;
        }

        return false;
    }
}
