package client;

import br.com.sistema.model.Clientes;
import br.com.sistema.rmi.ClienteService;
import java.rmi.Naming;
//import server.BoletoService;
//import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
//import server.BoletoService;

public class ClienteTesteRMI {
    public static void main(String[] args) {
        try {
            // Conectando ao serviço RMI
            ClienteService clienteService = (ClienteService) Naming.lookup("rmi://localhost/ClienteService");
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
            clienteService.SalvarClienteDao(cliente);
            System.out.println("clientes Salvo : " + cliente);

            // Teste: Listar clientes
            List<Clientes> clientes = clienteService.ListarClienteDao();
            System.out.println("Lista de clientes: " + clientes);

            // Teste: Buscar cliente pelo nome
            Clientes clienteEncontrado = clienteService.BuscarClienteDao("João Silva");
            System.out.println("Cliente encontrado: " + clienteEncontrado);

            // Teste: Buscar cliente pelo CPF
            Clientes clienteCPF = clienteService.BuscarClienteCPFDao("12345678900");
            System.out.println("Cliente encontrado pelo CPF: " + clienteCPF);

            // Teste: Editar cliente
            if (clienteEncontrado != null) {
                clienteEncontrado.setNome("João Souza");
                clienteService.EditarClienteDao(clienteEncontrado);
                System.out.println("Cliente editado com sucesso!");
            }
            // Teste: Filtrar clientes pelo nome
            List<Clientes> clientesFiltrados = clienteService.FiltarClienteDao("João");
            System.out.println("Clientes filtrados: " + clientesFiltrados);

            // Teste: Excluir cliente
            if (clienteEncontrado != null) {
                clienteService.ExcluirClienteDao(clienteEncontrado.getId());
                System.out.println("Cliente excluído com sucesso!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
        
