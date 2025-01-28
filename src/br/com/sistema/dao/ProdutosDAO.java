/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.Fornecedores;
import br.com.sistema.model.Produtos;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Classe ClientesDAO
 *
 * Esta classe é responsável por executar operações de banco de dados
 * relacionadas à entidade Clientes. Ela se conecta ao banco de dados utilizando
 * a classe ConexaoBanco e fornece métodos para salvar clientes.
 *
 * @author Cleyton
 */
public class ProdutosDAO {

    private Connection conn;

    // Método Construtor 
    public ProdutosDAO() {
        this.conn = new ConexaoBanco().pegarConexao();
    }

    public void SalvarProdutoDao(Produtos obj) {
        try {
            // 1º Definir a query SQL de inserção
            String sql = "INSERT INTO tb_produtos(descricao, preco, qtd_estoque, for_id) "
                    + "VALUES (?, ?, ?, ?)";

            // 2º Preparar a instrução SQL e atribuir os valores do objeto Produtos
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedores().getId());

            // 3º Executar a query de inserção
            stmt.execute();

            // 4º Fechar a conexão e exibir mensagem de sucesso
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
        } catch (SQLException e) {
            // Exibir mensagem de erro em caso de falha durante a execução da query
            JOptionPane.showMessageDialog(null, "Erro ao salvar o Produto: " + e.getLocalizedMessage());
        }
    }

    public void EditarProdutoDao(Produtos obj) {
        try {
            // 1º Definir a query SQL para atualizar os dados do cliente com base no ID
            String sql = "update tb_produtos set descricao=?,preco=?,qtd_estoque=?,for_id=? WHERE id = ?";

            // 2º Preparar a instrução SQL e atribuir os valores do objeto Clientes
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedores().getId());
            stmt.setInt(5, obj.getId());

            // 3º Executar a query de atualização
            stmt.execute();

            // 4º Fechar a conexão e exibir mensagem de sucesso
            stmt.close();
            JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");
        } catch (SQLException e) {
            // Exibir mensagem de erro em caso de falha durante a execução da query
            JOptionPane.showMessageDialog(null, "Erro ao editar o Produto: " + e.getLocalizedMessage());
        }
    }

    public void ExcluirProdutoDao(Produtos obj) {
        try {
            // 1º Preparar a instrução SQL para excluir um cliente baseado no ID
            String sql = "delete from tb_produtos where id=?";

            // 2º Preparar a conexão com o SQL e associar o ID ao comando
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            // 3º Executar o comando SQL para excluir o cliente
            stmt.execute();

            // 4º Fechar a conexão com o banco de dados
            stmt.close();

            // Exibir mensagem de sucesso para o usuário
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
        } catch (SQLException e) {
            // Exibir mensagem de erro em caso de falha na exclusão
            JOptionPane.showMessageDialog(null, "Erro ao excluir o Produto: " + e.getLocalizedMessage());
        }
    }

    public Produtos BuscarProdutoDao(String nome) {
        try {
            // 1º Criar a consulta SQL para buscar um Produto pelo nome
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id) where p.descricao=?";
            // 2º Preparar a conexão com o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            // 3º Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery();
            // 4º Criar um objeto Clientes para armazenar os dados do cliente
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            // 5º Verificar se algum resultado foi retornado e, em caso afirmativo, preencher o objeto com os dados
            if (rs.next()) {
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));
                obj.setFornecedores(f);
            }
            // 6º Retornar o objeto Produtos preenchido ou null se nenhum Produto for encontrado
            return obj;
        } catch (SQLException erro) {
            // Em caso de erro durante a execução da consulta, exibir uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao buscar o Produto: " + erro.getLocalizedMessage());
        }
        // Retornar null se ocorrer um erro ou se nenhum cliente for encontrado
        return null;
    }

    public Produtos BuscarProdutoCodDao(int id) {
        try {
            // 1º Criar a consulta SQL para buscar um Produto pelo nome
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id) where p.id=?";
            // 2º Preparar a conexão com o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            // 3º Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery();
            // 4º Criar um objeto Clientes para armazenar os dados do cliente
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            // 5º Verificar se algum resultado foi retornado e, em caso afirmativo, preencher o objeto com os dados
            if (rs.next()) {
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));
                obj.setFornecedores(f);
            }
            // 6º Retornar o objeto Produtos preenchido ou null se nenhum Produto for encontrado
            return obj;
        } catch (SQLException erro) {
            // Em caso de erro durante a execução da consulta, exibir uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao buscar o Produto: " + erro.getLocalizedMessage());
        }
        // Retornar null se ocorrer um erro ou se nenhum cliente for encontrado
        return null;
    }

    /**
     * Retorna uma lista de produtos, incluindo seus respectivos fornecedores, a
     * partir do banco de dados.
     *
     * Este método busca todos os produtos cadastrados na tabela `tb_produtos` e
     * realiza uma junção (INNER JOIN) com a tabela `tb_fornecedores` para
     * associar os fornecedores aos produtos.
     *
     * Passos do método:
     *
     * 1. Cria a consulta SQL que realiza um INNER JOIN entre a tabela de
     * produtos e a tabela de fornecedores, retornando o ID do produto,
     * descrição, preço, quantidade em estoque e o nome do fornecedor. 2.
     * Prepara a declaração SQL para execução. 3. Executa a consulta SQL e obtém
     * os resultados. 4. Itera sobre o resultado da consulta (ResultSet) e
     * mapeia cada linha para um objeto `Produtos`. 5. Retorna a lista de
     * produtos preenchida com os dados do banco. 6. Em caso de erro na execução
     * da consulta, exibe uma mensagem de erro.
     *
     * Exemplo de uso:
     *
     * ``` // Chamando o método para obter a lista de produtos List<Produtos>
     * produtos = dao.ListarProdutoDao(); ```
     *
     * @return lista de produtos com seus respectivos fornecedores, ou `null` se
     * houver um erro.
     * @see Produtos
     * @see Fornecedores
     */
    public List<Produtos> ListarProdutoDao() {
        // 1º Criar a lista que irá armazenar os produtos
        List<Produtos> lista = new ArrayList<>();

        try {
            // 2º Criar a consulta SQL para buscar todos os registros da tabela tb_produtos
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id)";

            // 3º Preparar a conexão com o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            // 4º Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery();

            // 5º Iterar sobre o ResultSet e mapear os dados para objetos Produtos
            while (rs.next()) {
                // Criar um novo objeto Produto
                Produtos obj = new Produtos();
                // Criar um novo objeto Fornecedores para armazenar os dados do fornecedor
                Fornecedores f = new Fornecedores();

                // Preencher os dados do produto
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                // Preencher os dados do fornecedor
                f.setNome(rs.getString("f.nome"));
                obj.setFornecedores(f);

                // Adicionar o produto à lista
                lista.add(obj);
            }

            // 6º Retornar a lista de produtos
            return lista;
        } catch (SQLException e) {
            // Em caso de erro durante a execução da consulta, exibir uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao buscar os Produtos: " + e.getLocalizedMessage());
        }

        // Retornar null se ocorrer um erro
        return null;
    }

    public List<Produtos> FiltarProdutoDao(String nome) {
        List<Produtos> lista = new ArrayList<>();
        try {
            // 1º Criar a consulta SQL para buscar Produtos cujo nome corresponde ao padrão fornecido
            String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id) where p.descricao like ?";

            // 2º Preparar a conexão com o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);

            // 3º Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery();

            // 4º Iterar sobre o ResultSet e mapear os dados para objetos Produtos
            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));
                obj.setFornecedores(f);

                lista.add(obj);
            }

            // 5º Retornar a lista de Produtos
            return lista;
        } catch (SQLException e) {
            // Em caso de erro durante a execução da consulta, exibir uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao buscar os Produtos: " + e.getLocalizedMessage());
        }

        // Retornar null se ocorrer um erro
        return null;
    }

    public void adicionarEstoque(int id, int qtd_nova) {
        try {
            String sql = "update tb_produtos set qtd_estoque=? where id = ?";

            // 2º Preparar a instrução SQL e atribuir os valores do objeto Clientes
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);

            // 3º Executar a query de atualização
            stmt.execute();

            // 4º Fechar a conexão e exibir mensagem de sucesso
            stmt.close();
            JOptionPane.showMessageDialog(null, "Adicionado com sucesso ao Estoque!");
        } catch (SQLException e) {
            // Exibir mensagem de erro em caso de falha durante a execução da query
            JOptionPane.showMessageDialog(null, "Erro ao adicionar ao estoque: " + e.getLocalizedMessage());
        }
    }

    public void baixaEstoque(int id, int qtd_nova) {
        try {
            String sql = "update tb_produtos set qtd_estoque=? where id = ?";

            // 2º Preparar a instrução SQL e atribuir os valores do objeto Clientes
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);

            // 3º Executar a query de atualização
            stmt.execute();

            // 4º Fechar a conexão e exibir mensagem de sucesso
            stmt.close();
           // JOptionPane.showMessageDialog(null, "baixa no estoque com sucesso!");
        } catch (SQLException e) {
            // Exibir mensagem de erro em caso de falha durante a execução da query
            JOptionPane.showMessageDialog(null, "Erro ao dar baixa ao estoque: " + e.getLocalizedMessage());
        }
    }

    /**
     * Retorna a quantidade atual do estoque de um produto.
     *
     * Este método consulta a quantidade disponível de um determinado produto na
     * tabela `tb_produtos` com base no seu `id`. Ele retorna o valor da coluna
     * `qtd_estoque`, que representa a quantidade atual no estoque.
     *
     * Passos do método:
     *
     * 1. Cria uma instrução SQL para buscar a quantidade atual de estoque de um
     * produto específico pelo seu ID. 2. Prepara a instrução SQL e atribui o
     * valor do ID do produto como parâmetro. 3. Executa a consulta SQL e obtém
     * o resultado. 4. Retorna a quantidade de estoque se o produto for
     * encontrado. 5. Lança uma exceção em caso de erro durante a execução.
     *
     * @param id O ID do produto cuja quantidade de estoque será consultada.
     * @return A quantidade atual de estoque do produto.
     * @throws RuntimeException Se ocorrer um erro ao realizar a consulta.
     */
    public int retornaQtdAtualEstoque(int id) {
        try {
            // Variável para armazenar a quantidade atual do estoque
            int qtd_atual_estoque = 0;

            // 1. Cria a instrução SQL para buscar a quantidade de estoque do produto com base no ID
            String sql = "SELECT qtd_estoque FROM tb_produtos WHERE id=?";

            // 2. Prepara a instrução SQL e define o ID do produto como parâmetro
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);  // Define o valor do parâmetro ID

            // 3. Executa a consulta SQL
            ResultSet rs = stmt.executeQuery();

            // 4. Se o produto for encontrado, obtém a quantidade atual de estoque
            if (rs.next()) {
                qtd_atual_estoque = rs.getInt("qtd_estoque");
            }

            // 5. Retorna a quantidade atual de estoque
            return qtd_atual_estoque;

        } catch (SQLException erro) {
            // Lança uma exceção em caso de erro durante a execução da consulta
            throw new RuntimeException("Erro ao realizar a consulta de quantidade atual do estoque: " + erro.getMessage());
        }
    }

}
