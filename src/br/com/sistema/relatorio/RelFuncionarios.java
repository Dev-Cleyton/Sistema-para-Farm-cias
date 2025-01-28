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
public class RelFuncionarios {
    ConexaoBancoRelatorios conexao = new ConexaoBancoRelatorios();
    
    public RelFuncionarios() {
        try {
            conexao.conecta();
            conexao.executeSQL("select * from tb_funcionarios");
            String caminhoRelatorioCliente ="C:\\sistema Estoque\\src\\relatorios\\relFuncionarios.jasper" ;
            JRResultSetDataSource jrRs = new JRResultSetDataSource(conexao.resultSet);
            JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorioCliente,new HashMap(),jrRs);
            JasperViewer.viewReport(jasperPrint,false);                        
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro no meu ireport" + erro.getMessage());
        }
    }
      
}
