package br.com.sistema.rmi;

import br.com.sistema.model.ItensVendas;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ItensVendasService extends Remote{
    void salvar(ItensVendas obj)throws RemoteException;
    List<ItensVendas> listaIntens(int venda_id)throws RemoteException;
}
