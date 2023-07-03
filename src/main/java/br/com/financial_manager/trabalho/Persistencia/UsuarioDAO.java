package br.com.financial_manager.trabalho.Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    private static UsuarioDAO instance;
    private Connection conexao = Conexao.getConexao();
    private PreparedStatement validate;
    private PreparedStatement insert;
    private PreparedStatement selectNewId;
    
    private UsuarioDAO() throws Exception{
        validate = conexao.prepareStatement("select * from usuarios where nome = ? and senha = ?");
        insert = conexao.prepareStatement("insert into usuarios values(?,?,?)");
        selectNewId = conexao.prepareStatement("select nextval('usuarios_id_usuario')");
    }

    public static UsuarioDAO getInstance() throws Exception{
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;
    }

     public int selectNewId() throws Exception{
            ResultSet rs = selectNewId.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        
            return 0;
    }

    public void insert(String nome, String senha) throws Exception{
        insert.setString(2, nome);
        insert.setString(3, senha);
        insert.setInt(1, selectNewId());

        insert.executeUpdate();
    }

    public boolean validate(String nome, String senha) throws Exception{
        validate.setString(1, nome);
        validate.setString(2, senha);
        try {
            ResultSet rs = validate.executeQuery();
            if (rs.next()) {return true;}
        } catch (Exception ex) {return false;}

        return false;
    }
}
