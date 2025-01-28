/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import br.com.sistema.jdbc.ConexaoBanco;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author cleyton
 */
public class Rel2viacupom {
private Connection con;

public Rel2viacupom(int id){
    this.con =new ConexaoBanco().pegarConexao();
    try {
        String jasper = "C:\\sistema Estoque\\src\\relatorios\\rel2viaCupom.jasper";
        Map<String,Object> param = new HashMap<>();
        param.put("id",id);
        JasperPrint japerPrint = JasperFillManager.fillReport(jasper,param,con);
        JasperViewer jasperViewer = new JasperViewer(japerPrint,false);
        jasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jasperViewer.setVisible(true);
    } catch (Exception erro) {
        JOptionPane.showMessageDialog(null,"Deu erro:" + erro);
    }
        
}
}
