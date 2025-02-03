package client;

import server.BoletoService;

import java.rmi.Naming;
import server.BoletoService;

public class Cliente {
    public static void main(String[] args) {
        try {
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
}
