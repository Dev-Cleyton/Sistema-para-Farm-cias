/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import br.com.sistema.model.Funcionarios;
import br.com.sistema.rmi.FuncionariosService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FuncionariosTesteRMI {
    
    public static void main(String[] args) {
        try {
            // Obter o registro RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // Procurar o serviço remoto
            FuncionariosService service = (FuncionariosService) registry.lookup("FuncionariosService");
            
            // Criar um cliente e salvar no banco remoto
            Funcionarios funcionarios= new Funcionarios();

            funcionarios.setNome("Carlos Silva");
            funcionarios.setRg("22.334.556-7");
            funcionarios.setCpf("234.567.890-12");
            funcionarios.setEmail("carlos.silva@gmail.com");
            funcionarios.setSenha("000");
            funcionarios.setCargo("entregador");
            funcionarios.setNivel_acesso("USUARIO");
            funcionarios.setTelefone("(21) 2345-6789");
            funcionarios.setCelular("(21)9 8765-4321");
            funcionarios.setCep("21000-345");
            funcionarios.setEndereco("Avenida Central");
            funcionarios.setNumero(456);
            funcionarios.setComplemento("Bloco B, Apt 302");
            funcionarios.setBairro("Centro");
            funcionarios.setCidade("Rio de Janeiro - RJ");
            funcionarios.setEstado("RJ");

            service.SalvarFuncionarioDao(funcionarios);
            Funcionarios funcionariosEncontrado = service.BuscarFuncionariosDao("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    