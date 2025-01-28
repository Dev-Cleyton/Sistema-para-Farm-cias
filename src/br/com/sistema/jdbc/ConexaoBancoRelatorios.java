/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistema.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author cleyton
 */
public class ConexaoBancoRelatorios {
   
    final private String driver = "com.mysql.jdbc.Driver";
    // URL de conexão com o banco de dados. 
    // No exemplo, a URL aponta para o banco "sistemaestoque" no servidor local
    private String ip = "192.168.0.106";
    
    final private String url = "jdbc:mysql://"+ip+"/sistemaestoque";

    // Nome de usuário para autenticação no banco de dados. 
    // No exemplo, o usuário é "root", que é o padrão do MySQL
    final private String usuario = "ESTOQUE";//"root";

    // Senha para autenticação no banco de dados.
    // Neste exemplo, a senha é uma string vazia, o que significa que não foi definida uma senha para o usuário "root".
    final private String senha = "123456789";//"";
    
    private Connection conexao;
    public Statement statement;
    public ResultSet resultSet;
    
    public boolean conecta(){
        boolean resultado = true;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,usuario,senha);            
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null,"Driver não encontrado "+ Driver);
            resultado = false;            
        }catch (SQLException fonte){
            JOptionPane.showMessageDialog(null,"Erro na minha fonte de dados "+ fonte.getMessage());
            resultado = false;
        }
        return resultado;
    }

    public void desconecta(){
        boolean resultado = true;
        try {
            conexao.close();
            JOptionPane.showMessageDialog(null,"Banco fechado!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Erro ao desconectar ao banco de dados!");
            resultado = false;            
        }
    }
    public void executeSQL(String sql){
        try {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro de sql "+ e.getMessage());
        }
    }
}
