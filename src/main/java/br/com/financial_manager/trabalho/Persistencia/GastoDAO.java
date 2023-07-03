package br.com.financial_manager.trabalho.Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.financial_manager.trabalho.Dados.Gasto;

public class GastoDAO {
    private static GastoDAO instance;
    private PreparedStatement selectNewId;
    private PreparedStatement select;
    private PreparedStatement insert;
    private PreparedStatement selectById;
    private PreparedStatement update;
    private PreparedStatement delete;

    private GastoDAO() throws Exception{
        Connection conexao = Conexao.getConexao();
        selectNewId = conexao.prepareStatement("select nextval('gastos_id_gasto')");
        insert      = conexao.prepareStatement("insert into gastos values(?,?,?,?,?,?)");
        select      = conexao.prepareStatement("select * from gastos");
        selectById  = conexao.prepareStatement("select * from gastos where id_gasto = ?");
        update      = conexao.prepareStatement("update gastos set nome = ?, datagasto = ?, descricao = ?, valor = ?, categoria = ? where id_gasto = ?");
        delete      = conexao.prepareStatement("delete from gastos where id_gasto = ?");
        
    }

    public static GastoDAO getInstace() throws Exception{
        if (instance == null) {
            instance = new GastoDAO();
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

    public void insert(Gasto gasto) throws Exception{
        insert.setString(2, gasto.getNome());
        insert.setDate(3, new java.sql.Date(gasto.getDate().getTime()));
        insert.setString(4, gasto.getDescricao());
        insert.setDouble(5, gasto.getValor());
        insert.setString(6, gasto.getCategoria());
        insert.setInt(1, selectNewId());

        insert.executeQuery();
    }

    public ResultSet selectAll() throws Exception{
        return select.executeQuery();
    }

    public List<Gasto> selectAllGasto() throws Exception{
        ResultSet rs = select.executeQuery();
        List<Gasto> gastos = new ArrayList<Gasto>();

        while (rs.next()) {
            Gasto gasto = new Gasto();
            gasto.setCodigo(rs.getInt(1));
            gasto.setNome(rs.getString(2));
            gasto.setData(rs.getDate(3));
            gasto.setDescricao(rs.getString(4));
            gasto.setValor(rs.getFloat(5));
            gasto.setCategoria(rs.getString(6));

            gastos.add(gasto);
        }
        
        return gastos;
    }

    public Gasto select(int codigo) throws Exception{
        selectById.setInt(1, codigo);
        ResultSet rs = selectById.executeQuery();

        if (rs.next()) {
            return new Gasto(rs.getString(2), rs.getDate(3), rs.getString(4), rs.getFloat(5), rs.getString(6));
        }
        
        throw new Exception();
    }

    public void update(int codigo, Gasto gastoAlterado) throws Exception{
        update.setString(1, gastoAlterado.getNome());
        update.setDate(2, new java.sql.Date(gastoAlterado.getDate().getTime()));
        update.setString(3, gastoAlterado.getDescricao());
        update.setDouble(4, gastoAlterado.getValor());
        update.setString(5, gastoAlterado.getCategoria());
        update.setInt(6, codigo);

        update.executeUpdate();
    }

    public void delete(int codigo) throws Exception {
        delete.setInt(1, codigo);
        delete.executeUpdate();
    }
}
