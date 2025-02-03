package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BoletoServiceImpl extends UnicastRemoteObject implements BoletoService {

    public BoletoServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean gerarBoletoParcela(Integer idParcela) throws RemoteException {
        // Aqui você pode implementar a lógica real para gerar um boleto
        // Exemplo fictício de geração de boleto para a parcela
        System.out.println("Gerando boleto para a parcela de ID: " + idParcela);
        // Simula a geração de boleto com sucesso
        return true;
    }
}
