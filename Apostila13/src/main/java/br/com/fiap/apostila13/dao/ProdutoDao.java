package br.com.fiap.apostila13.dao;

import br.com.fiap.apostila13.exception.EntidadeNaoEncontradaException;
import br.com.fiap.apostila13.factory.ConnectionFactory;
import br.com.fiap.apostila13.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//DAO -> Data Access Object
//Resposável por realizar as ações no Banco
public class ProdutoDao {

    private Connection conexao;

    public ProdutoDao() throws SQLException, ClassNotFoundException {
        conexao = ConnectionFactory.getConnection();
    }

    //CRUD (Create, Read, Update, Delete)
    public void cadastrar(Produto produto) throws SQLException {
        //Criar o comando SQL
        PreparedStatement stmt = conexao
                .prepareStatement("insert into t_jdbc_produto (cd_produto, " +
                        "nm_produto, ds_produto, vl_produto, st_estoque) " +
                        "values (sq_t_jdbc_produto.nextval, ?, ?, ?, ?)", new String[]{"cd_produto"});
        //Setar os paramentros no comando SQL
        stmt.setString(1, produto.getNome());
        stmt.setString(2, produto.getDescricao());
        stmt.setDouble(3, produto.getValor());
        stmt.setBoolean(4, produto.isTemEstoque());
        //Executar o comando SQL no banco
        stmt.executeUpdate();
        //Recuperar o id gerado
        ResultSet resultSet = stmt.getGeneratedKeys();
        if (resultSet.next()){
            int codigo = resultSet.getInt(1);
            produto.setCodigo(codigo);
        }
    }

    public Produto buscar(int id) throws SQLException, EntidadeNaoEncontradaException {
        //Criar o Comando SQL
        PreparedStatement stmt = conexao.prepareStatement(
                "select * from t_jdbc_produto where cd_produto = ?");
        //Setar o valor no comando SQL
        stmt.setInt(1, id);
        //Executar o comando SQL
        ResultSet resultSet = stmt.executeQuery(); //Executa comandos de pesquisa
        //Validar se existe um produto no resultado, se não existir lança uma exception
        if (!resultSet.next()){
            throw new EntidadeNaoEncontradaException("Produto não encontrado");
        }
        return getProduto(resultSet);
    }

    private static Produto getProduto(ResultSet resultSet) throws SQLException {
        //Recuperar as informações do ResulSet (codigo, nome, descrição, valor, estoque)
        int codigo = resultSet.getInt("cd_produto");
        String nome = resultSet.getString("nm_produto");
        String descricao = resultSet.getString("ds_produto");
        double valor = resultSet.getDouble("vl_produto");
        boolean temEstoque = resultSet.getBoolean("st_estoque");
        //Cria o produto com os dados do banco e retorna no método
        return new Produto(codigo, nome, descricao, valor, temEstoque);
    }

    public List<Produto> listar() throws SQLException {
        //Criar o comando SQL
        PreparedStatement stmt = conexao
                .prepareStatement("select * from t_jdbc_produto");
        //Executar o comando SQL
        ResultSet resultSet = stmt.executeQuery();
        //Criar a lista de Produto
        List<Produto> lista = new ArrayList<>();
        //Percorer todos os registros encontrados
        while (resultSet.next()) {
            lista.add(getProduto(resultSet));
        }
        //Retornar a lista
        return lista;
    }

    public void atualizar(Produto produto) throws SQLException, EntidadeNaoEncontradaException {
        //Criar o prepared statement com o comando SQL
        PreparedStatement stmt = conexao.prepareStatement("update t_jdbc_produto set nm_produto = ?, " +
                "ds_produto = ?, vl_produto = ?, st_estoque = ? where cd_produto = ?");
        //Setar os valores na query
        stmt.setString(1, produto.getNome());
        stmt.setString(2, produto.getDescricao());
        stmt.setDouble(3, produto.getValor());
        stmt.setBoolean(4, produto.isTemEstoque());
        stmt.setInt(5, produto.getCodigo());
        //Executar o comando
        int linhas = stmt.executeUpdate(); //Retorna o número de linhas afetadas no banco de dados
        if (linhas == 0)
            throw new EntidadeNaoEncontradaException("Produto não encontrado");
    }

    public void apagar(int id) throws SQLException, EntidadeNaoEncontradaException {
        //Criar o preparedStatement com o comando SQL
        PreparedStatement stmt = conexao.prepareStatement("delete from t_jdbc_produto where cd_produto = ?");
        //Setar o id no comando SQL
        stmt.setInt(1, id);
        //Executar o comando SQL
        int linhas = stmt.executeUpdate();
        //Validar se realmente apagou algo no banco
        if (linhas == 0)
            throw new EntidadeNaoEncontradaException("Produto não encontrado");
    }

    public List<Produto> buscarPorPrecoMaior(double preco){
        return null;
    }

}
