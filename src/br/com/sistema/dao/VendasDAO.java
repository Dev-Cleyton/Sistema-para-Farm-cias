/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.Clientes;
import br.com.sistema.model.Vendas;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author cleyton
 */
public class VendasDAO {

    private Connection conn;

    // Método Construtor 
    public VendasDAO() {
        this.conn = new ConexaoBanco().pegarConexao();
    }

    /**
     * Salva uma nova venda no banco de dados.
     *
     * Este método insere um registro na tabela `tb_vendas` contendo os detalhes
     * de uma venda, como o ID do cliente, data da venda, total da venda e
     * observações.
     *
     * Passos do método:
     *
     * 1. Cria a instrução SQL para inserir uma nova venda na tabela
     * `tb_vendas`. A consulta insere os seguintes campos: cliente_id,
     * data_venda, total_venda e observacoes. 2. Prepara a declaração SQL,
     * associando os valores correspondentes dos atributos da venda. 3. Executa
     * a consulta SQL para salvar os dados no banco. 4. Fecha a declaração para
     * liberar os recursos. 5. Exibe uma mensagem de sucesso caso a venda seja
     * salva corretamente. 6. Lança uma exceção se ocorrer algum erro ao
     * realizar a venda.
     *
     * Exemplo de uso:
     *
     * ``` Vendas obj = new Vendas(); obj.setId_Cliente(cliente); // Define o
     * cliente obj.setDataVenda("2024-09-14"); // Define a data da venda
     * obj.setTotal_venda(100.50); // Define o valor total da venda
     * obj.setObservacoes("Venda realizada com sucesso"); // Define as
     * observações dao.SalvarVendasDAO(obj); // Chama o método para salvar a
     * venda ```
     *
     * @param obj Objeto da classe Vendas contendo os detalhes da venda a ser
     * salva.
     * @throws RuntimeException Se ocorrer um erro ao salvar a venda.
     */
    public void SalvarVendasDAO(Vendas obj) {
        try {
            // 1. Cria a instrução SQL para inserir uma nova venda na tabela tb_vendas
            String sql = "INSERT INTO tb_vendas (cliente_id, data_venda, total_venda, observacoes) VALUES (?, ?, ?, ?)";

            // 2. Prepara a declaração SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            // 3. Associa os parâmetros da venda aos valores correspondentes
            stmt.setInt(1, obj.getId_Cliente().getId());  // ID do cliente
            stmt.setString(2, obj.getDataVenda());        // Data da venda (certifique-se que o formato da data esteja correto)
            stmt.setDouble(3, obj.getTotal_venda());      // Total da venda
            stmt.setString(4, obj.getObservacoes());      // Observações sobre a venda

            // 4. Executa a consulta SQL
            stmt.execute();

            // 5. Fecha a declaração para liberar recursos
            stmt.close();

            // 6. Exibe uma mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
        } catch (Exception erro) {
            // Lança uma exceção em caso de erro
            throw new RuntimeException("Erro ao realizar a venda: " + erro);
        }
    }

    /**
     * Retorna o ID da última venda registrada no banco de dados.
     *
     * Este método executa uma consulta SQL para obter o maior valor de ID
     * presente na tabela `tb_vendas`, o que corresponde ao ID da última venda
     * realizada. O ID da última venda é então retornado.
     *
     * Passos do método:
     *
     * 1. Cria a instrução SQL para buscar o maior ID da tabela `tb_vendas`, que
     * representa a última venda registrada. 2. Prepara a declaração SQL e
     * executa a consulta. 3. Processa o `ResultSet` para obter o valor do maior
     * ID. 4. Armazena o valor do último ID encontrado e o retorna. 5. Lança uma
     * exceção em caso de erro durante o processo.
     *
     * Exemplo de uso:
     *
     * ``` int ultimoId = dao.retonaUltimoIdVenda(); System.out.println("O ID da
     * última venda é: " + ultimoId); ```
     *
     * @return O ID da última venda registrada.
     * @throws RuntimeException Se ocorrer um erro ao tentar recuperar o último
     * ID da venda.
     */
    public int retonaUltimoIdVenda() {
        try {
            // Variável para armazenar o último ID
            int ultimoId = 0;

            // 1. Cria a instrução SQL para buscar o maior ID na tabela tb_vendas
            String sql = "SELECT MAX(id) AS id FROM tb_vendas";

            // 2. Prepara a declaração SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            // 3. Executa a consulta e processa o resultado
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // 4. Cria um objeto Vendas e obtém o valor do maior ID
                Vendas v = new Vendas();
                v.setId(rs.getInt("id"));  // Define o ID da venda a partir do ResultSet
                ultimoId = v.getId();      // Atribui o valor do último ID à variável
            }

            // 5. Retorna o valor do último ID
            return ultimoId;
        } catch (Exception e) {
            // Lança uma exceção em caso de erro
            throw new RuntimeException("Erro ao retornar o último ID da venda: " + e.getMessage());
        }
    }
    
/**
 * Método responsável por buscar o histórico de vendas entre duas datas.
 * 
 * @param data_inicio Data de início do intervalo de busca (formato LocalDate).
 * @param data_fim Data de fim do intervalo de busca (formato LocalDate).
 * @return Uma lista de objetos Vendas que ocorreram no intervalo de datas especificado.
 * @throws RuntimeException Se houver algum erro de SQL ao buscar as vendas.
 */
public List<Vendas> historicoVendas(LocalDate data_inicio, LocalDate data_fim) {
    try {
        // 1. Cria uma lista para armazenar os resultados da consulta
        List<Vendas> lista = new ArrayList<>();
        
        // 2. Cria a instrução SQL para buscar o histórico de vendas com base no intervalo de datas
        String sql = "SELECT v.id, c.nome, DATE_FORMAT(v.data_venda,'%d/%m/%Y') AS data_formatada, " +
                     "v.total_venda, v.observacoes " +
                     "FROM tb_vendas AS v " +
                     "INNER JOIN tb_clientes AS c ON (v.cliente_id = c.id) " +
                     "WHERE v.data_venda BETWEEN ? AND ?";
        
        // 3. Prepara a declaração SQL
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        // 4. Define os parâmetros da consulta usando as datas fornecidas
        stmt.setString(1, data_inicio.toString());  // Converte LocalDate para String no formato yyyy-MM-dd
        stmt.setString(2, data_fim.toString());
        
        // 5. Executa a consulta e armazena os resultados no ResultSet
        ResultSet rs = stmt.executeQuery();
        
        // 6. Processa os resultados, criando e populando objetos Vendas e Clientes
        while (rs.next()) {
            Vendas v = new Vendas();
            Clientes c = new Clientes();
            
            // Popula os dados de Vendas
            v.setId(rs.getInt("v.id"));  // ID da venda
            v.setDataVenda(rs.getString("data_formatada"));  // Data da venda no formato dd/MM/yyyy
            v.setTotal_venda(rs.getDouble("v.total_venda"));  // Valor total da venda
            v.setObservacoes(rs.getString("v.observacoes"));  // Observações da venda
            
            // Popula os dados do Cliente
            c.setNome(rs.getString("nome"));  // Nome do cliente
            
            // Define o cliente no objeto Vendas
            v.setId_Cliente(c);
            
            // Adiciona a venda à lista
            lista.add(v);
        }
        
        // 7. Retorna a lista de vendas
        return lista;
    } catch (SQLException e) {
        // Lança uma exceção com a mensagem de erro caso algo dê errado no SQL
        throw new RuntimeException("Erro ao criar o histórico de vendas: " + e.getMessage(), e);
    }
}

/**
 * Método responsável por calcular o total das vendas de um dia específico.
 *
 * @param data_veda A data para a qual o total das vendas será calculado.
 * @return O valor total das vendas realizadas na data especificada.
 * @throws RuntimeException Se ocorrer um erro durante a execução da consulta SQL.
 */
public double posicaoDoDia(LocalDate data_veda) {
    try {
        // Inicializa a variável para armazenar o total das vendas do dia
        double total_do_dia = 0;
        
        // SQL para buscar a soma das vendas do dia
        String sql = "select sum(total_venda)as total from tb_vendas where data_venda=?";
        
        // Prepara a declaração SQL
        PreparedStatement stmt = conn.prepareStatement(sql);
        
        // Define o parâmetro da data da venda
        stmt.setString(1, data_veda.toString());
        
        // Executa a consulta e armazena os resultados no ResultSet
        ResultSet rs = stmt.executeQuery();
        
        // Verifica se há resultados e atribui o valor ao total_do_dia
        if (rs.next()) {
            total_do_dia = rs.getDouble("total");
        }
        
        // Retorna o total das vendas do dia
        return total_do_dia;
        
    } catch (SQLException erro) {
        // Lança uma exceção em caso de erro na execução da consulta SQL
        throw new RuntimeException("Erro ao retornar a posição do dia: " + erro.getMessage(), erro);
    }
}

}
