package server;

import br.com.sistema.model.Clientes;
import br.com.sistema.model.Fornecedores;
import br.com.sistema.rmi.ClienteService;
import br.com.sistema.rmi.ClienteServiceImpl;
import br.com.sistema.rmi.FornecedoresService;
import br.com.sistema.rmi.FornecedoresServiceImpl;
import br.com.sistema.rmi.FuncionariosService;
import br.com.sistema.rmi.FuncionariosServiceImpl;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.List;

public class Servidor {
    public static void main(String[] args) {
            try {
            // Criar um registro RMI na porta 1099
            LocateRegistry.createRegistry(1099);
            
            // Criar uma instância do serviço
            ClienteService serviceCliente = new ClienteServiceImpl();
            FornecedoresService serviceFornecedoresService = new FornecedoresServiceImpl();
            
            //Imprimir lista de clientes
            List<Clientes> listaClientes = serviceCliente.ListarClienteDao();
            System.out.println("Lista de clientes cadastrados:");
                     
            List<Fornecedores> listaFornecedores = serviceFornecedoresService.ListarFornecedoreDao();
            System.out.println("Lista de Fornecedores cadastrados:");
            
            for(Fornecedores f : listaFornecedores){
              System.out.println("Lista: " + serviceFornecedoresService.ListarFornecedoreDao());   
            }
            
            for (Clientes c : listaClientes) {
                System.out.println("Lista: " + serviceCliente.ListarClienteDao());
                System.out.println("\n");
            }
            //FornecedoresService serviceFornecedo = new FornecedoresServiceImpl();            
            
            // Vincular o serviço ao registro
            Naming.rebind("ClienteService", serviceCliente);
            Naming.rebind("FornecedoresService", serviceFornecedoresService);
            
            
            System.out.println("Servidor RMI iniciado...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
