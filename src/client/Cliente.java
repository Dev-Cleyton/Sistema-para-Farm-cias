package client;

import br.com.sistema.model.Clientes;
import br.com.sistema.rmi.ClienteService;
//import server.BoletoService;
//import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//import server.BoletoService;

public class Cliente {
    public static void main(String[] args) {
        try {
            // Obter o registro RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // Procurar o serviço remoto
            ClienteService service = (ClienteService) registry.lookup("ClienteService");
            
            // Criar um cliente e salvar no banco remoto
            Clientes cliente = new Clientes();
            cliente.setNome("João RMI");//String João RMI
            cliente.setRg("12.345.678-9");//String ##.###.###-#
            cliente.setCpf("123.456.789-00");//String ###.###.###-##
            cliente.setEmail("JoãoRMI@gmail.com");//String nome@gmail.com
            cliente.setTelefone("(11) 3456-7890");//String (##) ####-####
            cliente.setCelular("(11)9 8765-4321");//String (##)# ####-####
            cliente.setCep("01234-567");//String #####-###
            cliente.setEndereco("Rua das Flores");// String 
            cliente.setNumero(123);//int ### ou ## ou ###### esse é livre
            cliente.setComplemento("Apt 45"); //String esse é livre
            cliente.setBairro("Bairro Jardim");//String esse é livre
            cliente.setCidade("São Paulo - SP");//String esse é livre
            cliente.setEstado("SP "); //SP obs aqui so pode ser UF neste formato
            service.SalvarClienteDao(cliente);
            
            System.out.println("Cliente salvo via RMI!");
            Clientes clienteEncontrado = service.BuscarClienteDao("João RMI");
            System.out.println("Cliente encontrado: " + clienteEncontrado.getNome());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
            /* try {
            System.out.println("Cliente salvo via RMI!");
            // Conecta-se ao servidor RMI
            BoletoService boletoService = (BoletoService) Naming.lookup("rmi://localhost/BoletoService");

            // Chama o método de geração de boleto
            Integer idParcela = 1876; // Exemplo de ID de parcela
            boolean sucesso = boletoService.gerarBoletoParcela(idParcela);

            if (sucesso) {
                System.out.println("Boleto gerado com sucesso para a parcela de ID: " + idParcela);
            } else {
                System.out.println("Falha ao gerar boleto para a parcela de ID: " + idParcela);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}*/
