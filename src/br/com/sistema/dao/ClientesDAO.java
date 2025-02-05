/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.Clientes;
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
 * a classe {@link br.com.sistema.jdbc.ConexaoBanco} e fornece métodos para salvar clientes.
 *
 * @author Cleyton
 */
public class ClientesDAO {
    
    private Connection conn;

     /**
     * Construtor da classe ClientesDAO.
     *
     * Inicializa a conexão com o banco de dados utilizando a classe ConexaoBanco.
     */
    public ClientesDAO() {
        this.conn = new ConexaoBanco().pegarConexao();
    }
/**
 * Salva um novo cliente no banco de dados.
 * 
 * Este método insere um novo registro na tabela `tb_clientes` utilizando os dados fornecidos
 * no objeto {@link Clientes}. O cliente deve ter seus atributos preenchidos corretamente para 
 * ser inserido no banco de dados.
 * 
 * O método segue os seguintes passos:
 * 
 * 1. Define a query SQL de inserção (INSERT) para adicionar um novo cliente ao banco.
 * 2. Prepara a conexão com o banco de dados e atribui os valores dos campos do cliente à consulta.
 * 3. Executa a consulta para salvar o cliente no banco de dados.
 * 4. Fecha a conexão após a execução e exibe uma mensagem de sucesso. Em caso de erro, uma mensagem
 *    de erro é exibida ao usuário.
 * 
 * Exemplo de uso:
 * 
 * ```
 * Clientes novoCliente = new Clientes();
 * novoCliente.setNome("Maria Silva");
 * // Definir os demais atributos do cliente
 * dao.salvarDao(novoCliente);
 * ```
 * @param obj O objeto {@link br.com.sistema.model.Clientes} contendo os dados do cliente a ser salvo.
 * @see ClientesDAO#salvarDao(Clientes)
 * @see Clientes
 * @see java.sql.PreparedStatement
 */
public void SalvarClienteDao(Clientes obj) {
    try {
        // 1º Definir a query SQL de inserção
        String sql = "INSERT INTO tb_clientes(nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // 2º Preparar a instrução SQL e atribuir os valores do objeto Clientes
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getRg());
        stmt.setString(3, obj.getCpf());
        stmt.setString(4, obj.getEmail());
        stmt.setString(5, obj.getTelefone());
        stmt.setString(6, obj.getCelular());
        stmt.setString(7, obj.getCep());
        stmt.setString(8, obj.getEndereco());
        stmt.setInt(9, obj.getNumero());
        stmt.setString(10, obj.getComplemento());
        stmt.setString(11, obj.getBairro());
        stmt.setString(12, obj.getCidade());
        stmt.setString(13, obj.getEstado());

        // 3º Executar a query de inserção
        stmt.execute();

        // 4º Fechar a conexão e exibir mensagem de sucesso
        stmt.close();
        JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!");
    } catch (SQLException e) {
        // Exibir mensagem de erro em caso de falha durante a execução da query
        JOptionPane.showMessageDialog(null, "Erro ao salvar o cliente: " + e.getLocalizedMessage());
    }
}


   /**
 * Edita as informações de um cliente no banco de dados com base nos dados fornecidos no objeto {@link Clientes}.
 * 
 * Este método atualiza os dados de um cliente existente na tabela `tb_clientes`, identificando-o pelo seu `id`.
 * Ele recebe um objeto {@link br.com.sistema.model.Clientes} contendo as informações atualizadas do cliente e as persiste no banco de dados.
 * 
 * O método segue os seguintes passos:
 * 
 * 1. Define a query SQL de atualização (UPDATE) para modificar os dados do cliente correspondente ao `id`.
 * 2. Prepara a instrução SQL com o banco de dados, conectando-se e atribuindo os novos valores de cada campo do cliente.
 * 3. Executa a instrução SQL para efetivar a alteração no banco de dados.
 * 4. Após a execução, a conexão com o banco é encerrada e uma mensagem de sucesso é exibida. Caso ocorra um erro,
 *    uma mensagem de erro é exibida.
 * 
 * Exemplo de uso:
 * 
 * ```
 * Clientes clienteAtualizado = new Clientes();
 * clienteAtualizado.setId(1);
 * clienteAtualizado.setNome("João Silva");
 * // Defina os demais campos
 * dao.EditarDao(clienteAtualizado);
 * ```
 * 
 * @param obj O objeto {@link br.com.sistema.model.Clientes} contendo os dados atualizados do cliente.
 * 
 * @see Clientes
 * @see java.sql.PreparedStatement
 */
public void EditarClienteDao(Clientes obj) {
    try {
        // 1º Definir a query SQL para atualizar os dados do cliente com base no ID
        String sql = "UPDATE tb_clientes SET nome = ?, rg = ?, cpf = ?, email = ?, telefone = ?, celular = ?, cep = ?, endereco = ?, "
                   + "numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ? WHERE id = ?";

        // 2º Preparar a instrução SQL e atribuir os valores do objeto Clientes
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getRg());
        stmt.setString(3, obj.getCpf());
        stmt.setString(4, obj.getEmail());
        stmt.setString(5, obj.getTelefone());
        stmt.setString(6, obj.getCelular());
        stmt.setString(7, obj.getCep());
        stmt.setString(8, obj.getEndereco());
        stmt.setInt(9, obj.getNumero());
        stmt.setString(10, obj.getComplemento());
        stmt.setString(11, obj.getBairro());
        stmt.setString(12, obj.getCidade());
        stmt.setString(13, obj.getEstado());
        stmt.setInt(14, obj.getId());  // O ID do cliente a ser atualizado

        // 3º Executar a query de atualização
        stmt.execute();

        // 4º Fechar a conexão e exibir mensagem de sucesso
        stmt.close();
        JOptionPane.showMessageDialog(null, "Cliente editado com sucesso!");
    } catch (SQLException e) {
        // Exibir mensagem de erro em caso de falha durante a execução da query
        JOptionPane.showMessageDialog(null, "Erro ao editar o cliente: " + e.getLocalizedMessage());
    }
}

/**
 * Exclui um cliente do banco de dados.
 * 
 * Este método remove um cliente do banco de dados com base no ID fornecido. 
 * Caso a exclusão seja bem-sucedida, uma mensagem de confirmação será exibida. 
 * Em caso de falha, uma mensagem de erro será apresentada.
 * 
 * Passos do método:
 * 
 * 1. Prepara a instrução SQL para excluir o cliente com base no ID.
 * 2. Preenche o ID no comando SQL usando o método {@link PreparedStatement#setInt(int, int)}.
 * 3. Executa o comando SQL de exclusão.
 * 4. Fecha o {@link PreparedStatement} após a execução.
 * 5. Exibe uma mensagem de confirmação em caso de sucesso.
 * 6. Em caso de erro, exibe uma mensagem de erro contendo o detalhe da exceção.
 * 
 * Exemplo de uso:
 * 
 * ```
 * // Cria um objeto cliente com o ID do cliente que será excluído
 * Clientes cliente = new Clientes();
 * cliente.setId(1);
 * 
 * // Exclui o cliente do banco de dados
 * ClientesDAO dao = new ClientesDAO();
 * dao.ExcluirDao(cliente);
 * ```
 * 
 * @param obj Objeto {@link br.com.sistema.model.Clientes} que contém o ID do cliente a ser excluído.
 * @see ClientesDAO
 */
public void ExcluirClienteDao(Clientes obj) {
    try {
        // 1º Preparar a instrução SQL para excluir um cliente baseado no ID
        String sql = "DELETE FROM tb_clientes WHERE id=?";
        
        // 2º Preparar a conexão com o SQL e associar o ID ao comando
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, obj.getId());
        
        // 3º Executar o comando SQL para excluir o cliente
        stmt.execute();
        
        // 4º Fechar a conexão com o banco de dados
        stmt.close();
        
        // Exibir mensagem de sucesso para o usuário
        JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
    } catch (SQLException e) {
        // Exibir mensagem de erro em caso de falha na exclusão
        JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente: " + e.getLocalizedMessage());
    }
}


    /**
     * Busca um cliente no banco de dados com base no nome fornecido.
     *
     * @param nome O nome do cliente a ser buscado. Este parâmetro é utilizado
     * para encontrar o cliente na tabela "tb_clientes".
     * @return Um objeto {@link br.com.sistema.model.Clientes} contendo os dados do cliente
     * encontrado. Retorna {@code null} se o cliente não for encontrado ou se
     * ocorrer um erro durante a busca.
     */
    public Clientes BuscarClienteDao(String nome) {
        try {
            // 1º Criar a consulta SQL para buscar um cliente pelo nome
            String sql = "select * from tb_clientes where nome =?";
            // 2º Preparar a conexão com o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            // 3º Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery();
            // 4º Criar um objeto Clientes para armazenar os dados do cliente
            Clientes obj = new Clientes();

            // 5º Verificar se algum resultado foi retornado e, em caso afirmativo, preencher o objeto com os dados
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
            // 6º Retornar o objeto Clientes preenchido ou null se nenhum cliente for encontrado
            return obj;
        } catch (SQLException erro) {
            // Em caso de erro durante a execução da consulta, exibir uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao buscar o cliente: " + erro.getLocalizedMessage());
        }
        // Retornar null se ocorrer um erro ou se nenhum cliente for encontrado
        return null;
    }

     public Clientes BuscarClienteCPFDao(String cpf) {
        try {
            // 1º Criar a consulta SQL para buscar um cliente pelo nome
            String sql = "select * from tb_clientes where cpf =?";
            // 2º Preparar a conexão com o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            // 3º Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery();
            // 4º Criar um objeto Clientes para armazenar os dados do cliente
            Clientes obj = new Clientes();

            // 5º Verificar se algum resultado foi retornado e, em caso afirmativo, preencher o objeto com os dados
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
            // 6º Retornar o objeto Clientes preenchido ou null se nenhum cliente for encontrado
            return obj;
        } catch (SQLException erro) {
            // Em caso de erro durante a execução da consulta, exibir uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao buscar o cliente: " + erro.getLocalizedMessage());
        }
        // Retornar null se ocorrer um erro ou se nenhum cliente for encontrado
        return null;
    }
    
    
    /**
     * Lista todos os clientes cadastrados na tabela `tb_clientes` do banco de
     * dados.
     *
     * Este método executa uma consulta SQL para buscar todos os registros da
     * tabela `tb_clientes` e os armazena em uma lista de objetos
     * {@link Clientes}. Cada registro da tabela é mapeado para um objeto
     * {@link Clientes}, que é adicionado a uma lista que é retornada ao final
     * do método.
     *
     * @return Uma lista de objetos {@link Clientes} representando todos os
     * clientes encontrados na tabela. Retorna {@code null} se ocorrer um erro
     * durante a consulta.
     */
    public List<Clientes> ListarClienteDao() {
        List<Clientes> lista = new ArrayList<>();
        try {
            // 1º Criar a consulta SQL para buscar todos os registros da tabela tb_clientes
            String sql = "SELECT * FROM tb_clientes";  // Correção de "selet" para "SELECT"

            // 2º Preparar a conexão com o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            // 3º Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery();

            // 4º Iterar sobre o ResultSet e mapear os dados para objetos Clientes
            while (rs.next()) {
                Clientes obj = new Clientes();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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

            // 5º Retornar a lista de clientes
            return lista;
        } catch (SQLException e) {
            // Em caso de erro durante a execução da consulta, exibir uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao buscar os clientes: " + e.getLocalizedMessage());
        }

        // Retornar null se ocorrer um erro
        return null;
    }

    /**
     * Filtra e retorna uma lista de clientes cujos nomes correspondem ao padrão
     * fornecido.
     *
     * Este método executa uma consulta SQL para buscar clientes na tabela
     * `tb_clientes` cujo nome corresponde ao padrão especificado. O padrão de
     * filtragem é passado como argumento e pode incluir caracteres coringa
     * (wildcards) para realizar buscas parciais.
     *
     * O método realiza os seguintes passos: 1. Cria uma consulta SQL para
     * buscar clientes com um nome que corresponda ao padrão fornecido. O padrão
     * usa o operador `LIKE` para permitir buscas parciais. 2. Prepara a
     * instrução SQL com o padrão de nome fornecido. 3. Executa a consulta e
     * obtém o resultado no `ResultSet`. 4. Itera sobre o `ResultSet`, mapeando
     * cada registro para um objeto {@link Clientes} e adiciona esses objetos a
     * uma lista. 5. Retorna a lista de clientes encontrados.
     *
     * @param nome O padrão de nome para filtrar os clientes. Pode incluir
     * caracteres coringa (por exemplo, `%` para buscar qualquer sequência de
     * caracteres).
     * @return Uma lista de objetos {@link Clientes} que correspondem ao padrão
     * de nome fornecido. Retorna {@code null} se ocorrer um erro durante a
     * execução da consulta.
     */
    public List<Clientes> FiltarClienteDao(String nome) {
        List<Clientes> lista = new ArrayList<>();
        try {
            // 1º Criar a consulta SQL para buscar clientes cujo nome corresponde ao padrão fornecido
            String sql = "SELECT * FROM tb_clientes WHERE nome LIKE ?";

            // 2º Preparar a conexão com o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);

            // 3º Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery();

            // 4º Iterar sobre o ResultSet e mapear os dados para objetos Clientes
            while (rs.next()) {
                Clientes obj = new Clientes();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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

            // 5º Retornar a lista de clientes
            return lista;
        } catch (SQLException e) {
            // Em caso de erro durante a execução da consulta, exibir uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao buscar os clientes: " + e.getLocalizedMessage());
        }

        // Retornar null se ocorrer um erro
        return null;
    }
    
}
