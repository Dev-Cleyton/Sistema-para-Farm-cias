package server;

import br.com.sistema.rmi.ClienteService;
import br.com.sistema.rmi.ClienteServiceImpl;
import br.com.sistema.rmi.FornecedoresService;
import br.com.sistema.rmi.FornecedoresServiceImpl;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {
    public static void main(String[] args) {
            try {
            // Criar um registro RMI na porta 1099
            LocateRegistry.createRegistry(1099);
            
            // Criar uma instância do serviço
            ClienteService serviceCliente = new ClienteServiceImpl();
            FornecedoresService serviceFornecedo = new FornecedoresServiceImpl();
            
            // Vincular o serviço ao registro
            Naming.rebind("ClienteService", serviceCliente);
            Naming.rebind("FornecedoresService", serviceFornecedo);
            
            System.out.println("Servidor RMI iniciado...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
            /*
                try{
            // Cria o registro RMI
            LocateRegistry.createRegistry(1099);

            // Cria uma instância do serviço
            BoletoService boletoService = new BoletoServiceImpl();

            // Registra o serviço no registro RMI
            Naming.rebind("BoletoService", boletoService);

            System.out.println("Servidor RMI de Boletos está pronto!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/

