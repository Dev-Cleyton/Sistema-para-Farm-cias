package client;

import br.com.sistema.model.Fornecedores;
import br.com.sistema.rmi.FornecedoresService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Fornecedore {

    public static void main(String[] args) {
        try {
            // Obter o registro RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // Procurar o serviço remoto
            FornecedoresService service = (FornecedoresService) registry.lookup("FornecedoresService");
            
            Fornecedores fornecedor = new Fornecedores();
            fornecedor.setNome("Laboratorios RMI"); //String Laboratorios RMI
            fornecedor.setCnpj("10.737.667/2317-07");// String ##.###.###/####-## 
            fornecedor.setEmail("Lab@gmail.com");// Livre Lab@gmail.com
            fornecedor.setTelefone("(12) 8765-4521");//String (##) ####-#### 
            fornecedor.setCelular("(99)9 9999-9999");//(##)# ####-####
            fornecedor.setCep("41322-111");//Strin #####-###
            fornecedor.setEndereco("DISTRIAM Distribuidora");//String 
            fornecedor.setNumero(432);// int 
            fornecedor.setComplemento("LTDA");
            fornecedor.setBairro("jardim das naçoes");
            fornecedor.setCidade("Betim");
            fornecedor.setEstado("MG");//Tem que ter esse espaço
            service.SalvarFornecedoresDao(fornecedor);
            System.out.println("Fornecedor salvo via RMI!");
            
            Fornecedores fornecedoresEncotrado = service.BuscarFornecedoresDao("Laboratorios RMI");
            System.out.println("Fornecedor encontrado: " + fornecedoresEncotrado.getNome());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}