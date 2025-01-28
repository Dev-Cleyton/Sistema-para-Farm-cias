/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.Clientes;
import br.com.sistema.model.Funcionarios;
import br.com.sistema.view.AreaTrabalho;
import br.com.sistema.view.FormularioLogin;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Classe FuncionariosDAO
 *
 * Esta classe é responsável por executar operações de banco de dados
 * relacionadas à entidade Funcionarios. Ela se conecta ao banco de dados
 * utilizando a classe ConexaoBanco e fornece métodos para salvar clientes.
 *
 * @author Cleyton
 */
public class FuncionariosDAO {

    private Connection conn;

    // Método Construtor 
    public FuncionariosDAO() {
        this.conn = new ConexaoBanco().pegarConexao();
    }

    /**
     * Salva um novo Funcionarios no banco de dados.
     *
     * Este método insere um novo registro na tabela `tb_clientes` utilizando os
     * dados fornecidos no objeto {@link Funcionarios}. O cliente deve ter seus
     * atributos preenchidos corretamente para ser inserido no banco de dados.
     *
     * O método segue os seguintes passos:
     *
     * 1. Define a query SQL de inserção (INSERT) para adicionar um novo
     * Funcionarios ao banco. 2. Prepara a conexão com o banco de dados e
     * atribui os valores dos campos do Funcionarios à consulta. 3. Executa a
     * consulta para salvar o Funcionarios no banco de dados. 4. Fecha a conexão
     * após a execução e exibe uma mensagem de sucesso. Em caso de erro, uma
     * mensagem de erro é exibida ao usuário.
     *
     * Exemplo de uso:
     *
     * ``` Funcionarios novoFuncionario = new Funcionarios();
     * novoFuncionarios.setNome("Maria Silva"); // Definir os demais atributos
     * do cliente dao.salvarDao(novoFuncionarios); ```
     *
     * @param obj O objeto {@link Funcionarios} contendo os dados do
     * Funcionarios a ser salvo.
     *
     * @see Funcionarios
     * @see java.sql.PreparedStatement
     */
    public void SalvarFuncionarioDao(Funcionarios obj) {
        try {
            // 1º Definir a query SQL de inserção
            String sql = "INSERT INTO tb_funcionarios(nome, rg, cpf, email,senha,cargo,nivel_acesso, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            // 2º Preparar a instrução SQL e atribuir os valores do objeto Clientes
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());

            // 3º Executar a query de inserção
            stmt.execute();

            // 4º Fechar a conexão e exibir mensagem de sucesso
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionarios salvo com sucesso!");
        } catch (SQLException e) {
            // Exibir mensagem de erro em caso de falha durante a execução da query
            JOptionPane.showMessageDialog(null, "Erro ao salvar o funcionarios: " + e.getLocalizedMessage());
        }
    }

    /**
     * Edita as informações de um Funcionarios no banco de dados com base nos
     * dados fornecidos no objeto {@link Funcionarios}.
     *
     * Este método atualiza os dados de um Funcionarios existente na tabela
     * `tb_funcionarios`, identificando-o pelo seu `id`. Ele recebe um objeto
     * {@link Funcionarios} contendo as informações atualizadas do Funcionarios
     * e as persiste no banco de dados.
     *
     * O método segue os seguintes passos:
     *
     * 1. Define a query SQL de atualização (UPDATE) para modificar os dados do
     * Funcionarios correspondente ao `id`. 2. Prepara a instrução SQL com o
     * banco de dados, conectando-se e atribuindo os novos valores de cada campo
     * do Funcionarios. 3. Executa a instrução SQL para efetivar a alteração no
     * banco de dados. 4. Após a execução, a conexão com o banco é encerrada e
     * uma mensagem de sucesso é exibida. Caso ocorra um erro, uma mensagem de
     * erro é exibida.
     *
     * Exemplo de uso:
     *
     * ``` Funcionarios funcionarioAtualizado = new Funcionarios();
     * FuncionarioAtualizado.setId(1); FuncionarioAtualizado.setNome("João
     * Silva"); // Defina os demais campos dao.EditarDao(funcionarioAtualizado);
     * ```
     *
     * @param obj O objeto {@link Funcionarios} contendo os dados atualizados do
     * funcionarios.
     *
     * @see Funcionarios
     * @see java.sql.PreparedStatement
     */
    public void EditarFuncionarioDao(Funcionarios obj) {
        try {
            // 1º Definir a query SQL para atualizar os dados do Funcionarios com base no ID
            String sql = "UPDATE tb_funcionarios SET nome = ?, rg = ?, cpf = ?, email = ?,"
                    + "senha =?,cargo=?,nivel_acesso =?,"
                    + "telefone = ?, celular = ?, cep = ?, endereco = ?,"
                    + "numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ? WHERE id = ?";

            // 2º Preparar a instrução SQL e atribuir os valores do objeto Funcionarios
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());
            stmt.setInt(17, obj.getId());  // O ID do funcionarios a ser atualizado

            // 3º Executar a query de atualização
            stmt.execute();

            // 4º Fechar a conexão e exibir mensagem de sucesso
            stmt.close();
            JOptionPane.showMessageDialog(null, "Funcionario editado com sucesso!");
        } catch (SQLException e) {
            // Exibir mensagem de erro em caso de falha durante a execução da query
            JOptionPane.showMessageDialog(null, "Erro ao editar o funcionario: " + e.getLocalizedMessage());
        }
    }

    /**
     * Exclui um funcionarios do banco de dados.
     *
     * Este método remove um funcionarios do banco de dados com base no ID
     * fornecido. Caso a exclusão seja bem-sucedida, uma mensagem de confirmação
     * será exibida. Em caso de falha, uma mensagem de erro será apresentada.
     *
     * Passos do método:
     *
     * 1. Prepara a instrução SQL para excluir o funcionarios com base no ID. 2.
     * Preenche o ID no comando SQL usando o método
     * {@link PreparedStatement#setInt(int, int)}. 3. Executa o comando SQL de
     * exclusão. 4. Fecha o {@link PreparedStatement} após a execução. 5. Exibe
     * uma mensagem de confirmação em caso de sucesso. 6. Em caso de erro, exibe
     * uma mensagem de erro contendo o detalhe da exceção.
     *
     * Exemplo de uso:
     *
     * ``` // Cria um objeto cliente com o ID do cliente que será excluído
     * Funcionarios funcionario = new Funcionarios(); cliente.setId(1);
     *
     * // Exclui o cliente do banco de dados FuncionariosDAO dao = new
     * FuncionariosDAO(); dao.ExcluirDao(cliente); ```
     *
     * @param obj Objeto {@link Funcionarios} que contém o ID do Funcionarios a
     * ser excluído.
     * @see FuncionariosDAO
     */
    public void ExcluirFuncionarioDao(Funcionarios obj) {
        try {
            // 1º Preparar a instrução SQL para excluir um cliente baseado no ID
            String sql = "DELETE FROM tb_funcionarios WHERE id=?";

            // 2º Preparar a conexão com o SQL e associar o ID ao comando
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            // 3º Executar o comando SQL para excluir o cliente
            stmt.execute();

            // 4º Fechar a conexão com o banco de dados
            stmt.close();

            // Exibir mensagem de sucesso para o usuário
            JOptionPane.showMessageDialog(null, "Funcionarios excluído com sucesso!");
        } catch (SQLException e) {
            // Exibir mensagem de erro em caso de falha na exclusão
            JOptionPane.showMessageDialog(null, "Erro ao excluir o funcionarios: " + e.getLocalizedMessage());
        }
    }

    /**
     * Busca um cliente no banco de dados com base no nome fornecido.
     *
     * @param nome O nome do cliente a ser buscado. Este parâmetro é utilizado
     * para encontrar o cliente na tabela "tb_clientes".
     * @return Um objeto {@link Funcionarios} contendo os dados do funcionario
     * encontrado. Retorna {@code null} se o Funcionario não for encontrado ou
     * se ocorrer um erro durante a busca.
     */
    public Funcionarios BuscarFuncionariosDao(String nome) {
        try {
            // 1º Criar a consulta SQL para buscar um cliente pelo nome
            String sql = "select * from tb_funcionarios where nome =?";
            // 2º Preparar a conexão com o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            // 3º Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery();
            // 4º Criar um objeto Clientes para armazenar os dados do cliente
            Funcionarios obj = new Funcionarios();

            // 5º Verificar se algum resultado foi retornado e, em caso afirmativo, preencher o objeto com os dados
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
            // 6º Retornar o objeto Funcionarios preenchido ou null se nenhum cliente for encontrado
            return obj;
        } catch (SQLException erro) {
            // Em caso de erro durante a execução da consulta, exibir uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao buscar funcionarios: " + erro.getLocalizedMessage());
        }
        // Retornar null se ocorrer um erro ou se nenhum Funcionarios for encontrado
        return null;
    }

    /**
     * Lista todos os clientes cadastrados na tabela `tb_funcionarios` do banco
     * de dados.
     *
     * Este método executa uma consulta SQL para buscar todos os registros da
     * tabela `tb_funcionarios` e os armazena em uma lista de objetos
     * {@link Clientes}. Cada registro da tabela é mapeado para um objeto
     * {@link Clientes}, que é adicionado a uma lista que é retornada ao final
     * do método.
     *
     * @return Uma lista de objetos {@link Funcionarios} representando todos os
     * Funcionarios encontrados na tabela. Retorna {@code null} se ocorrer um
     * erro durante a consulta.
     */
    public List<Funcionarios> ListarFuncionarioDao() {
        List<Funcionarios> lista = new ArrayList<>();
        try {
            // 1º Criar a consulta SQL para buscar todos os registros da tabela tb_funcionarios
            String sql = "SELECT * FROM tb_funcionarios";  // Correção de "selet" para "SELECT"

            // 2º Preparar a conexão com o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            // 3º Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery();

            // 4º Iterar sobre o ResultSet e mapear os dados para objetos Funcionarios
            while (rs.next()) {
                Funcionarios obj = new Funcionarios();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
            JOptionPane.showMessageDialog(null, "Erro ao buscar os funcionarios: " + e.getLocalizedMessage());
        }

        // Retornar null se ocorrer um erro
        return null;
    }

    /**
     * Filtra e retorna uma lista de clientes cujos nomes correspondem ao padrão
     * fornecido.
     *
     * Este método executa uma consulta SQL para buscar funcionario na tabela
     * `tb_funcionario` cujo nome corresponde ao padrão especificado. O padrão de
     * filtragem é passado como argumento e pode incluir caracteres coringa
     * (wildcards) para realizar buscas parciais.
     *
     * O método realiza os seguintes passos: 1. Cria uma consulta SQL para
     * buscar clientes com um nome que corresponda ao padrão fornecido. O padrão
     * usa o operador `LIKE` para permitir buscas parciais. 2. Prepara a
     * instrução SQL com o padrão de nome fornecido. 3. Executa a consulta e
     * obtém o resultado no `ResultSet`. 4. Itera sobre o `ResultSet`, mapeando
     * cada registro para um objeto {@link Funcionario} e adiciona esses objetos a
     * uma lista. 5. Retorna a lista de clientes encontrados.
     *
     * @param nome O padrão de nome para filtrar os Funcionario. Pode incluir
     * caracteres coringa (por exemplo, `%` para buscar qualquer sequência de
     * caracteres).
     * @return Uma lista de objetos {@link Funcionario} que correspondem ao padrão
     * de nome fornecido. Retorna {@code null} se ocorrer um erro durante a
     * execução da consulta.
     */
    public List<Funcionarios> FiltarFuncionarioDao(String nome) {
        List<Funcionarios> lista = new ArrayList<>();
        try {
            // 1º Criar a consulta SQL para buscar clientes cujo nome corresponde ao padrão fornecido
            String sql = "SELECT * FROM tb_funcionarios WHERE nome LIKE ?";

            // 2º Preparar a conexão com o SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);

            // 3º Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery();

            // 4º Iterar sobre o ResultSet e mapear os dados para objetos Clientes
            while (rs.next()) {
                Funcionarios obj = new Funcionarios();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
            JOptionPane.showMessageDialog(null, "Erro ao buscar os Funcionarios: " + e.getLocalizedMessage());
        }

        // Retornar null se ocorrer um erro
        return null;
    }

    /**
     * Realiza o processo de login de um funcionário no sistema.
     *
     * Este método verifica se o funcionário com o email e senha fornecidos está
     * registrado no banco de dados. Se as credenciais estiverem corretas, o
     * acesso ao sistema será concedido. Caso contrário, uma mensagem de erro
     * será exibida e o usuário retornará à tela de login.
     *
     * Passos do método:
     *
     * 1. Cria um comando SQL para buscar o funcionário na tabela
     * `tb_funcionarios` com base no email e senha. 2. Prepara a declaração SQL
     * e define os parâmetros (email e senha). 3. Executa a consulta SQL e
     * verifica se há resultados correspondentes: - Se o funcionário for
     * encontrado, exibe uma mensagem de boas-vindas e abre a tela da área de
     * trabalho. - Se não houver correspondência, exibe uma mensagem de "Dados
     * Inválidos" e retorna para a tela de login. 4. Trata possíveis exceções
     * que possam ocorrer durante a execução do SQL.
     *
     * Exemplo de uso:
     *
     * ``` // Pressionando o botão de login na interface gráfica
     * efetuarLogin(emailDigitado, senhaDigitada); ```
     *
     * @param email O email do funcionário que está tentando fazer login.
     * @param senha A senha do funcionário que está tentando fazer login.
     * @see AreaTrabalho
     * @see FormularioLogin
     */
    public boolean efetuarLogin(String email, String senha) {
    try {
        // 1º Cria o comando SQL para verificar email e senha no banco de dados
        String sql = "SELECT * FROM tb_funcionarios WHERE email = ? AND senha = ?";

        // 2º Prepara a declaração SQL e define os parâmetros (email e senha)
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, senha);

        // 3º Executa a consulta e verifica se o funcionário existe
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Se o funcionário for encontrado, verifica o nível de acesso
            String nivelAcesso = rs.getString("nivel_acesso");
            String nomeUsuario = rs.getString("nome");

            AreaTrabalho at = new AreaTrabalho();
            at.usuarioLogado = nomeUsuario;

            if (nivelAcesso.equalsIgnoreCase("ADMINISTRADOR")) {
                JOptionPane.showMessageDialog(null, 
                    "Seja Bem-Vindo ao Sistema, Administrador!\n" + nomeUsuario);
                at.setVisible(true);

            } else if (nivelAcesso.equalsIgnoreCase("USUARIO")) {
                // Configura as permissões para usuário comum
                at.menu_fornecedores.setVisible(false);
                at.menu_funcionario.setEnabled(false);
                at.menu_estoque.setEnabled(false);

                JOptionPane.showMessageDialog(null, 
                    "Seja Bem-Vindo ao Sistema, Usuário!\n" + nomeUsuario);
                at.setVisible(true);
            }

            return true; // Login bem-sucedido
        } else {
            // 4º Exibe uma mensagem de erro em caso de login inválido
            JOptionPane.showMessageDialog(null, 
                "Dados Inválidos! Verifique seu e-mail e senha.");
            return false; // Login falhou
        }

    } catch (SQLException erroLogin) {
        // 5º Tratamento de erro em caso de falha na execução da consulta SQL
        JOptionPane.showMessageDialog(null, 
            "Erro ao efetuar login: " + erroLogin.getMessage());
        return false; // Login falhou devido a erro
    }
}
   
    
    public void efetuar_Login(String email, String senha) {
        try {
            // 1º Cria o comando SQL para verificar email e senha no banco de dados
            String sql = "SELECT * FROM tb_funcionarios WHERE email=? and senha=?";

            // 2º Prepara a declaração SQL e define os parâmetros (email e senha)
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            // 3º Executa a consulta e verifica se o funcionário existe
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { // Se o funcionário for encontrado
                // Verifica o nível de acesso
                String nivelAcesso = rs.getString("nivel_acesso");

                AreaTrabalho at = new AreaTrabalho();
                at.usuarioLogado = rs.getString("nome");

                if (nivelAcesso.equals("ADMINISTRADOR")) {
                    // 4º Exibe uma mensagem de boas-vindas e abre a área de trabalho de administrador
                    JOptionPane.showMessageDialog(null, "Seja Bem-Vindo ao Sistema, Administrador!\n" + at.usuarioLogado);
                    at.setVisible(true);

                } else if (nivelAcesso.equals("USUARIO")) {
                    // Configura as permissões para usuário comum
                    at.menu_fornecedores.setVisible(false);
                    at.menu_funcionario.setEnabled(false);
                    at.menu_estoque.setEnabled(false);

                    // Exibe uma mensagem de boas-vindas para o usuário comum
                    JOptionPane.showMessageDialog(null, "Seja Bem-Vindo ao Sistema, Usuário!\n" + at.usuarioLogado);
                    at.setVisible(true);
                }
            } else {
                // 5º Exibe uma mensagem de erro e retorna para a tela de login
                JOptionPane.showMessageDialog(null, "Dados Inválidos!");
                FormularioLogin login = new FormularioLogin();
                login.setVisible(true);
            }

        } catch (SQLException erroLogin) {
            // 6º Tratamento de erro em caso de falha na execução da consulta SQL
            JOptionPane.showMessageDialog(null, "Erro ao efetuar login: " + erroLogin.getMessage());
        }
    }
}
