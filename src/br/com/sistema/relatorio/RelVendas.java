/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import br.com.sistema.jdbc.ConexaoBancoRelatorios;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.util.HashNMap;

/**
 *
 * @author cleyton
 */
public class RelVendas {
    ConexaoBancoRelatorios conexao = new ConexaoBancoRelatorios();
    
    public RelVendas() {
        try {
            conexao.conecta();
            conexao.executeSQL("select i.venda_id,p.descricao,p.preco,i.qtd,i.subtotal from tb_itensvendas as i inner join tb_produtos as p on(i.produto_id=p.id) where venda_id =(select max(id) from tb_vendas)");
            String caminhoRelatorioCliente ="C:\\sistema Estoque\\src\\relatorios\\relVendas.jasper" ;
            JRResultSetDataSource jrRs = new JRResultSetDataSource(conexao.resultSet);
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorioCliente,new HashMap(),jrRs);
            JasperViewer.viewReport(jasperPrint,false);                        
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro no meu ireport" + erro.getMessage());
        }
    }
    
    /**
     * @param args the command line arguments
     
    public static void main(String[] args) {
        
    }*/
    
}
