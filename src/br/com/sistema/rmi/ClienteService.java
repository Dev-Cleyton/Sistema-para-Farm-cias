package br.com.sistema.rmi;

import br.com.sistema.model.Clientes;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClienteService extends Remote {
    void SalvarClienteDao(Clientes cliente) throws RemoteException;
    void EditarClienteDao(Clientes cliente) throws RemoteException;
    void ExcluirClienteDao(int id) throws RemoteException;
    Clientes BuscarClienteDao(String nome) throws RemoteException;
    Clientes BuscarClienteCPFDao(String cpf) throws RemoteException;
    List<Clientes> ListarClienteDao() throws RemoteException;
    List<Clientes> FiltarClienteDao(String nome)throws RemoteException;
   
}
