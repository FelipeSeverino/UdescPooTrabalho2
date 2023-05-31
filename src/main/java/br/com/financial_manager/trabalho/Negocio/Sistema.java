package br.com.financial_manager.trabalho.Negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.financial_manager.trabalho.Dados.*;

public class Sistema {
    private Usuario usuarioLogado;

    private List<Usuario>   usuarios; 
    private List<Gasto>     gastos;

    public Sistema() {
        usuarios = new ArrayList<>();
        gastos = new ArrayList<>();
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        if (usuarios.contains(usuario) || usuario.getNome().equals("") || usuario.getSenha().equals("")) {
            return false;
        }

        usuarios.add(usuario);
        return true;
    }

    public boolean login(String nome, String senha) {
        Usuario tentativa = new Usuario(nome, senha);

        if (usuarios.contains(tentativa)) {
            Usuario usuario = usuarios.stream().filter((Usuario user) -> user.getNome().equals(tentativa.getNome())).collect(Collectors.toList()).get(0);
            
            if (usuario.login(tentativa)) {
                usuarioLogado = tentativa;
                return true;
            }
        }

        return false;
    }

    public void adicionarGasto(Gasto gasto) {
            gastos.add(gasto);
    }

    public boolean alterarGasto(Gasto gasto, Gasto gastoAlterado) {
        if (gastos.contains(gasto)) {
            gastos.remove(gasto);
            gastos.add(gastoAlterado);

            return true;
        }

        return false;
    }

    public boolean removerGasto(Gasto gasto) {
        if (gastos.contains(gasto)) {
            gastos.remove(gasto);
            
            return true;
        }

        return false;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    
}
