package relatorios;
import br.com.sistema.jdbc.ConexaoBanco;
import br.com.sistema.jdbc.ConexaoBancoRelatorios;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperPrint;
/**
 *
 * @author cleyton
 */
public class RelHistoricoVendas {
    private Connection con;
    
    public void gerarRelatorio(Date dataInicial, Date dataFinal) {
        this.con =new ConexaoBanco().pegarConexao();
        try {
            // Caminho do arquivo .jasper
            String jasper = "C:\\sistema Estoque\\src\\relatorios\\relHistoriocoVendas.jasper";
            
            // Parâmetros do relatório
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("data_inicial", dataInicial);
            parametros.put("data_final", dataFinal);                                                                
            
            // Preenchendo o relatório
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parametros, con);
                        
            // Exibir o relatório            
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);                        
            jasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);            
            jasperViewer.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}