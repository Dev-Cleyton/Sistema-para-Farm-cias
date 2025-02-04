package server;

import br.com.sistema.rmi.ClienteService;
import br.com.sistema.rmi.ClienteServiceImpl;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
            try {
            // Criar um registro RMI na porta 1099
            LocateRegistry.createRegistry(1099);
            
            // Criar uma instância do serviço
            ClienteService service = new ClienteServiceImpl();
            
            // Vincular o serviço ao registro
            Naming.rebind("ClienteService", service);
            
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

