package br.com.sistema.rmi;

import br.com.sistema.model.Vendas;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface VendasService extends Remote  {
    void SalvarVendasDAO(Vendas vendas) throws RemoteException;
    int retonaUltimoIdVenda()throws RemoteException;
    List<Vendas> historicoVendas(LocalDate data_inicio, LocalDate data_fim)throws RemoteException;
    double posicaoDoDia(LocalDate data_veda)throws RemoteException;
    
}
