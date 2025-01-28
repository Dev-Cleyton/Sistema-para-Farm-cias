/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistema.dao;

import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.model.ItensVendas;
import br.com.sistema.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cleyton
 */
public class ItensVendasDAO {

    private Connection conn;

    // Método Construtor 
    public ItensVendasDAO() {
        this.conn = new ConexaoBanco().pegarConexao();
    }

    /**
     * Salva os itens de uma venda no banco de dados.
     *
     * Este método insere um registro na tabela `tb_itensvendas`, representando
     * os itens de uma venda específica. Ele recebe um objeto `ItensVendas` e
     * utiliza os dados deste objeto para preencher a consulta SQL e salvar as
     * informações no banco de dados.
     *
     * Passos do método:
     *
     * 1. Cria a instrução SQL para inserir os dados dos itens da venda na
     * tabela `tb_itensvendas`. 2. Prepara a declaração SQL com os dados
     * fornecidos no objeto `ItensVendas`. 3. Executa a declaração SQL para
     * salvar os dados no banco. 4. Fecha a conexão com o banco após a execução.
     * 5. Lança uma exceção em caso de erro durante o processo.
     *
     * Exemplo de uso:
     *
     * ``` ItensVendas item = new ItensVendas(venda, produto, quantidade,
     * subtotal); dao.salvar(item); ```
     *
     * @param obj O objeto `ItensVendas` contendo os dados dos itens da venda a
     * serem salvos.
     * @throws RuntimeException Se ocorrer um erro ao salvar os itens da venda.
     */
    public void salvar(ItensVendas obj) {
        try {
            // 1. Cria a instrução SQL para inserir dados na tabela tb_itensvendas
            String sql = "INSERT INTO tb_itensvendas (venda_id, produto_id, qtd, subtotal) VALUES (?, ?, ?, ?)";

            // 2. Prepara a declaração SQL
            PreparedStatement stmt = conn.prepareStatement(sql);

            // 3. Preenche a declaração SQL com os dados do objeto ItensVendas
            stmt.setInt(1, obj.getVedas().getId());       // Define o ID da venda
            stmt.setInt(2, obj.getProdutos().getId());     // Define o ID do produto
            stmt.setInt(3, obj.getQtd());                  // Define a quantidade vendida
            stmt.setDouble(4, obj.getSubtotal());          // Define o subtotal do item

            // 4. Executa a declaração SQL
            stmt.execute();

            // 5. Fecha a conexão
            stmt.close();

        } catch (Exception e) {
            // Lança uma exceção em caso de erro
            throw new RuntimeException("Erro ao salvar os itens da venda: " + e.getMessage());
        }
    }
    /**
 * Método responsável por listar os itens de uma venda específica.
 * 
 * @param venda_id ID da venda para a qual os itens serão listados.
 * @return Uma lista de objetos ItensVendas que representam os itens da venda.
 * @throws RuntimeException Se ocorrer um erro ao tentar recuperar os itens da venda.
 */
    public List<ItensVendas> listaIntens(int venda_id) {
        // Cria a lista para armazenar os itens da venda
        try {
            List<ItensVendas> lista = new ArrayList<>();
             
            // SQL para buscar os itens da venda
            String sql = "select p.id,p.descricao, i.qtd,p.preco,i.subtotal from tb_itensvendas as i inner join tb_produtos as p on(i.produto_id=p.id)where i.venda_id=?";
            
            // Prepara a declaração SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Define o parâmetro venda_id
            stmt.setInt(1, venda_id);
            // Executa a consulta
            ResultSet rs = stmt.executeQuery();
            // Processa o resultado da consulta
            while (rs.next()) {
                ItensVendas item = new ItensVendas();
                Produtos p = new Produtos();
                p.setId(rs.getInt("p.id"));// ID do produto
                item.setProdutos(p);
                p.setDescricao(rs.getString("p.descricao"));// Descrição do produto
                item.setProdutos(p);
                item.setQtd(rs.getInt("i.qtd"));// Quantidade do item
                p.setPreco(rs.getDouble("p.preco"));// Preço do produto
                
                item.setProdutos(p);
                item.setSubtotal(rs.getInt("i.subtotal")); // Subtotal do item
                
                 // Adiciona o item à lista
                lista.add(item);
                 // Retorna a lista de itens da venda
                return lista;
            }
        } catch (SQLException erro) {
             // Lança uma exceção com a mensagem de erro caso algo dê errado no SQL
            throw new RuntimeException("Erro ao cria a lista de itens!" + erro);
        }
        return null;
    }
}