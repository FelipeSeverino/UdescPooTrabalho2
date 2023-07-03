package br.com.financial_manager.trabalho.Negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.financial_manager.trabalho.Dados.*;
import br.com.financial_manager.trabalho.Persistencia.GastoDAO;
import br.com.financial_manager.trabalho.Persistencia.UsuarioDAO;

public class Sistema {
    private Usuario usuarioLogado;
    private GastoDAO gastoDao;
    private UsuarioDAO usuarioDao;

    private List<Usuario>   usuarios; 
    private List<Gasto>     gastos;

    public Sistema() {
        usuarios = new ArrayList<>();
        gastos = new ArrayList<>();

        try {
            gastoDao = GastoDAO.getInstace();
            usuarioDao = UsuarioDAO.getInstance();
        } catch (Exception ex) {System.out.println("Erro ao estabelecer conexao com o DB -> " + ex);}


    }

    public boolean cadastrarUsuario(Usuario usuario) {
        //Se ja existe
        try {
            if (usuarioDao.validate(usuario.getNome(), usuario.getSenha())) {
                return false;
            }
        } catch(Exception ex) {return false;}

        //Adiciona usuario
        try {
            usuarioDao.insert(usuario.getNome(), usuario.getSenha());
        } catch(Exception ex) {return false;}
        return true;
    }

    public boolean login(String nome, String senha) {
        try {
            if (usuarioDao.validate(nome, senha)) {
                return true;
            }
        } catch(Exception ex) {return false;}

        return false;
    }

    public void adicionarGasto(Gasto gasto) {
        try {
            GastoDAO.getInstace().insert(gasto);
        } catch (Exception e) {System.out.println("Erro ao inserir gasto no banco -> " + e);}
    }

    public Gasto getGasto(int idGasto) throws Exception{
        return gastoDao.select(idGasto);
    }

    public boolean alterarGasto(int codigo, Gasto gastoAlterado) {
        try {
            gastoDao.update(codigo, gastoAlterado);
            return true;
        } catch (Exception ex) {return false;}
    }

    public boolean removerGasto(int codigo) {
        try {
            gastoDao.delete(codigo);
            return true;
        } catch (Exception ex) {return false;}
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
