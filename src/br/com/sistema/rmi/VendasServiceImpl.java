package br.com.sistema.rmi;

import br.com.sistema.dao.VendasDAO;
import br.com.sistema.model.Vendas;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class VendasServiceImpl extends UnicastRemoteObject implements VendasService{
    
    private VendasDAO vendasDAO;
    
    public VendasServiceImpl() throws RemoteException{
        super();
        this.vendasDAO = new VendasDAO();
    }

    @Override
    public void SalvarVendasDAO(Vendas vendas) throws RemoteException {
       vendasDAO.SalvarVendasDAO(vendas);
    }

    @Override
    public int retonaUltimoIdVenda() throws RemoteException {
     return vendasDAO.retonaUltimoIdVenda();
    }

    @Override
    public List<Vendas> historicoVendas(LocalDate data_inicio, LocalDate data_fim) throws RemoteException {
       return vendasDAO.historicoVendas(data_inicio, data_fim);
    }

    @Override
    public double posicaoDoDia(LocalDate data_veda) throws RemoteException {
      return vendasDAO.posicaoDoDia(data_veda);
    }
  
}
