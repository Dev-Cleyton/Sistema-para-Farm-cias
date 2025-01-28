/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.Fornecedores;
import br.com.sistema.model.Funcionarios;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Classe FornecedoresDAO
 *
 * Esta classe é responsável por executar operações de banco de dados
 * relacionadas à entidade Funcionarios. Ela se conecta ao banco de dados
 * utilizando a classe ConexaoBanco e fornece métodos para salvar clientes.
 *
 * @author Cleyton
 */
public class FornecedoresDAO {

    private Connection conn;

    // Método Construtor 
    public FornecedoresDAO() {
        this.conn = new ConexaoBanco().pegarConexao();
    }

    /**
     * Salva um novo Fornecedores no banco de dados.
     *
     * Este método insere um novo registro na tabela `tb_clientes` utilizando os
     * dados fornecidos no objeto {@link Fornecedores}. O Fornecedore deve ter seus
     * atributos preenchidos corretamente para ser inserido no banco de dados.
     *
     * O método segue os seguintes passos:
     *
     * 1. Define a query SQL de inserção (INSERT) para adicionar um novo
     * Fornecedores ao banco. 2. Prepara a conexão com o banco de dados e
     * atribui os valores dos campos do Fornecedores à consulta. 3. Executa a
     * consulta para salvar o Fornecedores no banco de dados. 4. Fecha a conexão
     * após a execução e exibe uma mensagem de sucesso. Em caso de erro, uma
     * mensagem de erro é exibida ao usuário.
     *
     * Exemplo de uso:
     *
     * ``` Funcionarios novoFornecedores = new Fornecedores();
     * novoFornecedores.setNome("Maria Silva"); // Definir os demais atributos
     * do cliente dao.salvarDao(novoFornecedores); ```
     *
     * @param obj O objeto {@link Fornecedores} contendo os dados do
     * Fornecedores a ser salvo.
     *
     * @see Funcionarios
     * @see java.sql.PreparedStatement
     */
    public void SalvarFornecedoresDao(Fornecedores obj) {
        try {
            // 1º Definir a query SQL de inserção
            String sql = "INSERT INTO tb_fornecedores(nome, cnpj, email,telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

            // 2º Preparar a instrução SQL e atribuir os valores do objeto Clientes
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());

            // 3º Executar a query de inserção
            stmt.execute();

            // 4º Fechar a conexão e exibir mensagem de sucesso
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedores salvo com sucesso!");
        } catch (SQLException e) {
            // Exibir mensagem de erro em caso de falha durante a execução da query
            JOptionPane.showMessageDialog(null, "Erro ao salvar o Fornecedores: " + e.getLocalizedMessage());
        }
    }

    /**
     * Edita as informações de um Fornecedores no banco de dados com base nos
     * dados fornecidos no objeto {@link Fornecedores}.
     *
     * Este método atualiza os dados de um Fornecedores existente na tabela
     * `tb_Fornecedores`, identificando-o pelo seu `id`. Ele recebe um objeto
     * {@link Fornecedores} contendo as informações atualizadas do Fornecedores
     * e as persiste no banco de dados.
     *
     * O método segue os seguintes passos:
     *
     * 1. Define a query SQL de atualização (UPDATE) para modificar os dados do
     * Funcionarios correspondente ao `id`. 2. Prepara a instrução SQL com o
     * banco de dados, conectando-se e atribuindo os novos valores de cada campo
     * do Fornecedores. 3. Executa a instrução SQL para efetivar a alteração no
     * banco de dados. 4. Após a execução, a conexão com o banco é encerrada e
     * uma mensagem de sucesso é exibida. Caso ocorra um erro, uma mensagem de
     * erro é exibida.
     *
     * Exemplo de uso:
     *
     * ``` Funcionarios FornecedoreAtualizado = new Fornecedores();
     * FFornecedoreAtualizado.setId(1); FornecedoreAtualizado.setNome("João
     * Silva"); // Defina os demais campos dao.EditarDao(FornecedoreAtualizado);
     * ```
     *
     * @param obj O objeto {@link Fornecedores} contendo os dados atualizados do
     * Fornecedores.
     *
     * @see Funcionarios
     * @see java.sql.PreparedStatement
     */
    public void EditarFornecedoreDao(Fornecedores obj) {
        try {
            // 1º Definir a query SQL para atualizar os dados do Fornecedores com base no ID
            String sql = "UPDATE tb_fornecedores SET nome = ?, cnpj = ?, email = ?,"
                    + "telefone = ?, celular = ?, cep = ?, endereco = ?,"
                    + "numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ? WHERE id = ?";

            // 2º Preparar a instrução SQL e atribuir os valores do objeto Fornecedores
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());
            stmt.setInt(13, obj.getId());  // O ID do Fornecedores a ser atualizado

            // 3º Executar a query de atualização
            stmt.execute();

            // 4º Fechar a conexão e exibir mensagem de sucesso
            stmt.close();
            JOptionPane.showMessageDialog(null, "Fornecedores editado com sucesso!");
        } catch (SQLException e) {
            // Exibir mensagem de erro em caso de falha durante a execução da query
            JOptionPane.showMessageDialog(null, "Erro ao editar o Fornecedores: " + e.getLocalizedMessage());
        }
    }

    /**
     * Exclui um Fornecedores do banco de dados.
     *
     * Este método remove um Fornecedores do banco de dados com base no ID
     * fornecido. Caso a exclusão seja bem-sucedida, uma mensagem de confirmação
     * será exibida. Em caso de falha, uma mensagem de erro será apresentada.
     *
     * Passos do método:
     *
     * 1. Prepara a instrução SQL para excluir o Fornecedores com base no ID. 2.
     * Preenche o ID no comando SQL usando o método
     * {@link PreparedStatement#setInt(int, int)}. 3. Executa o comando SQL de
     * exclusão. 4. Fecha o {@link PreparedStatement} após a execução. 5. Exibe
     * uma mensagem de confirmação em caso de sucesso. 6. Em caso de erro, exibe
     * uma mensagem de erro contendo o detalhe da exceção.
     *
     * Exemplo de uso:
     *
     * ``` // Cria um objeto Fornecedore com o ID do clienteFornecedore que será excluído
     * Fornecedores funcionario = new Fornecedores(); cliente.setId(1);
     *
     * // Exclui o cliente do banco de dados FuncionariosDAO dao = new
     * FornecedoresDAO(); dao.ExcluirDao(Fornecedores); ```
     *
     * @param obj Objeto {@link Fornecedores} que contém o ID do Funcionarios a
     * ser excluído.
     * @see FornecedoresDAO
     */
    public void ExcluirFornecedoresDao(Fornecedores obj) {
        try {
            // 1º Preparar a instrução SQL para excluir um cliente baseado no ID
            String sql = "DELETE FROM tb_fornecedores WHERE id=?";

            // 2º Preparar a conexão com o SQL e associar o ID ao comando
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            // 3º Executar o comando SQL para excluir o cliente
            stmt.execute();

            // 4º Fechar a conexão com o banco de dados
            stmt.close();

            // Exibir mensagem de sucesso para o usuário
            JOptionPane.showMessageDialog(null, "Fornecedores excluído com sucesso!");
        } catch (SQLException e) {
            // Exibir mensagem de erro em caso de falha na exclusão
            JOptionPane.showMessageDialog(null, "Erro ao excluir o Fornecedores: " + e.getLocalizedMessage());
        }
    }

    /**
     * Busca um Fornecedores no banco de dados com base no nome fornecido.
     *
     * @param nome O nome do Fornecedores a ser buscado. Este parâmetro é utilizado
     * para encontrar o Fornecedores na tabela "tb_fornecedores".
     * @return Um objeto {@link Fornecedores} contendo os dados do Fornecedores
     * encontrado. Retorna {@code null} se o Fornecedores não for encontrado ou
     * se ocorrer um erro durante a busca.
     */
    public Fornecedores BuscarFornecedoresDao(String nome) {
        try {
            // 1º Criar a consulta SQL para buscar um Fornecedores pelo nome
            String sql = "select * from tb_fornecedores where nome =?";
            // 2º Preparar a conexão com o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            // 3º Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery();
            // 4º Criar um objeto Fornecedores para armazenar os dados do Fornecedores
            Fornecedores obj = new Fornecedores();

            // 5º Verificar se algum resultado foi retornado e, em caso afirmativo, preencher o objeto com os dados
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
            }
            // 6º Retornar o objeto Fornecedores preenchido ou null se nenhum cliente for encontrado
            return obj;
        } catch (SQLException erro) {
            // Em caso de erro durante a execução da consulta, exibir uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao buscar Fornecedores: " + erro.getLocalizedMessage());
        }
        // Retornar null se ocorrer um erro ou se nenhum Funcionarios for encontrado
        return null;
    }

    /**
     * Lista todos os Fornecedores cadastrados na tabela `tb_fornecedores` do banco
     * de dados.
     *
     * Este método executa uma consulta SQL para buscar todos os registros da
     * tabela `tb_fornecedores` e os armazena em uma lista de objetos
     * {@link Fornecedores}. Cada registro da tabela é mapeado para um objeto
     * {@link Fornecedores}, que é adicionado a uma lista que é retornada ao final
     * do método.
     *
     * @return Uma lista de objetos {@link Fornecedores} representando todos os
     * Fornecedores encontrados na tabela. Retorna {@code null} se ocorrer um
     * erro durante a consulta.
     */
    public List<Fornecedores> ListarFornecedoreDao() {
        List<Fornecedores> lista = new ArrayList<>();
        try {
            // 1º Criar a consulta SQL para buscar todos os registros da tabela tb_fornecedores
            String sql = "SELECT * FROM tb_fornecedores";  // Correção de "selet" para "SELECT"

            // 2º Preparar a conexão com o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            // 3º Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery();

            // 4º Iterar sobre o ResultSet e mapear os dados para objetos Funcionarios
            while (rs.next()) {
                Fornecedores obj = new Fornecedores();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                lista.add(obj);
            }

            // 5º Retornar a lista de Fornecedores
            return lista;
        } catch (SQLException e) {
            // Em caso de erro durante a execução da consulta, exibir uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao buscar os Fornecedores: " + e.getLocalizedMessage());
        }

        // Retornar null se ocorrer um erro
        return null;
    }

    /**
     * Filtra e retorna uma lista de Fornecedores cujos nomes correspondem ao padrão
     * fornecido.
     *
     * Este método executa uma consulta SQL para buscar Fornecedores na tabela
     * `tb_fornecedores` cujo nome corresponde ao padrão especificado. O padrão de
     * filtragem é passado como argumento e pode incluir caracteres coringa
     * (wildcards) para realizar buscas parciais.
     *
     * O método realiza os seguintes passos: 1. Cria uma consulta SQL para
     * buscar clientes com um nome que corresponda ao padrão fornecido. O padrão
     * usa o operador `LIKE` para permitir buscas parciais. 2. Prepara a
     * instrução SQL com o padrão de nome fornecido. 3. Executa a consulta e
     * obtém o resultado no `ResultSet`. 4. Itera sobre o `ResultSet`, mapeando
     * cada registro para um objeto {@link Fornecedores} e adiciona esses objetos a
     * uma lista. 5. Retorna a lista de clientes encontrados.
     *
     * @param nome O padrão de nome para filtrar os Fornecedores. Pode incluir
     * caracteres coringa (por exemplo, `%` para buscar qualquer sequência de
     * caracteres).
     * @return Uma lista de objetos {@link Fornecedores} que correspondem ao padrão
     * de nome fornecido. Retorna {@code null} se ocorrer um erro durante a
     * execução da consulta.
     */
    public List<Fornecedores> FiltarFornecedoresDao(String nome) {
        List<Fornecedores> lista = new ArrayList<>();
        try {
            // 1º Criar a consulta SQL para buscar Fornecedores cujo nome corresponde ao padrão fornecido
            String sql = "SELECT * FROM tb_fornecedores WHERE nome LIKE ?";

            // 2º Preparar a conexão com o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);

            // 3º Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery();

            // 4º Iterar sobre o ResultSet e mapear os dados para objetos Fornecedores
            while (rs.next()) {
                Fornecedores obj = new Fornecedores();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
                lista.add(obj);
            }

            // 5º Retornar a lista de Funcionarios
            return lista;
        } catch (SQLException e) {
            // Em caso de erro durante a execução da consulta, exibir uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao buscar os Fornecedores: " + e.getLocalizedMessage());
        }

        // Retornar null se ocorrer um erro
        return null;
    }

}
