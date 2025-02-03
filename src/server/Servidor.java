package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Servidor {
    public static void main(String[] args) {
        try {
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
}

