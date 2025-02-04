package br.com.sistema.rmi;

import br.com.sistema.dao.ClientesDAO;
import br.com.sistema.model.Clientes;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ClienteServiceImpl extends UnicastRemoteObject implements ClienteService {

    private ClientesDAO clienteDAO;

    public ClienteServiceImpl() throws RemoteException {
        super();
        this.clienteDAO = new ClientesDAO();
    }

    @Override
    public void SalvarClienteDao(Clientes cliente) throws RemoteException {
        clienteDAO.SalvarClienteDao(cliente);
    }

    @Override
    public void EditarClienteDao(Clientes cliente) throws RemoteException {
        clienteDAO.EditarClienteDao(cliente);
    }

    @Override
    public void ExcluirClienteDao(int id) throws RemoteException {
        Clientes cliente = new Clientes();
        cliente.setId(id);
        clienteDAO.ExcluirClienteDao(cliente);
    }

    @Override
    public Clientes BuscarClienteDao(String nome) throws RemoteException {
        return clienteDAO.BuscarClienteDao(nome);
    }

    @Override
    public Clientes BuscarClienteCPFDao(String cpf) throws RemoteException {
        return clienteDAO.BuscarClienteCPFDao(cpf);
    }

    @Override
    public List<Clientes> ListarClienteDao() throws RemoteException {
        return clienteDAO.ListarClienteDao();
    }

    @Override
    public List<Clientes> FiltarClienteDao(String nome) throws RemoteException {
        return clienteDAO.FiltarClienteDao(nome);
    }
}
