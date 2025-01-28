/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * Esta classe é responsável por gerenciar a conexão com o banco de dados MySQL utilizado no sistema de estoque.
 * Ela encapsula os detalhes de conexão, como URL, usuário e senha, e fornece um método para obter uma conexão ativa com o banco.
 *
 */
package br.com.sistema.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Classe ConexaoBanco
 *
 * Esta classe é responsável por estabelecer a conexão com o banco de dados
 * MySQL. Ela utiliza os detalhes de conexão definidos como variáveis de
 * instância e fornece um método público para acessar a conexão.
 *
 * @author cleyton
 */
public class ConexaoBanco {

    // URL de conexão com o banco de dados. 
    // No exemplo, a URL aponta para o banco "sistemaestoque" no servidor local
    final private String url = "jdbc:mysql://192.168.0.106/sistemaestoque";

    // Nome de usuário para autenticação no banco de dados. 
    // No exemplo, o usuário é "root", que é o padrão do MySQL
    final private String usuario = "ESTOQUE";//"root";

    // Senha para autenticação no banco de dados.
    // Neste exemplo, a senha é uma string vazia, o que significa que não foi definida uma senha para o usuário "root".
    final private String senha = "123456789";//"";

    /**
     * Este método tenta estabelecer uma conexão com o banco de dados usando as
     * credenciais fornecidas. Se a conexão for bem-sucedida, ele retorna um
     * objeto Connection que pode ser usado para interagir com o banco. Caso
     * ocorra uma falha na conexão, uma mensagem de erro será exibida ao
     * usuário.
     *
     * @return Connection - retorna um objeto de conexão ativa com o banco de
     * dados ou null em caso de falha.
     */
    public Connection pegarConexao() {
        try {
            // Tentativa de estabelecer a conexão com o banco de dados usando a URL, usuário e senha fornecidos.
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            // Em caso de falha, uma mensagem de erro é exibida para o usuário com detalhes sobre a exceção ocorrida.
            JOptionPane.showMessageDialog(null, "Erro ao se conectado com o banco de dados!" + e);
        }
        // Se a conexão falhar, o método retorna null
        return null;
    }
}
