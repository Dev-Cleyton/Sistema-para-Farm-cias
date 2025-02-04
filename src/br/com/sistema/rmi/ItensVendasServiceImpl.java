
package br.com.sistema.rmi;

import br.com.sistema.dao.ItensVendasDAO;
import br.com.sistema.model.ItensVendas;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ItensVendasServiceImpl extends UnicastRemoteObject implements ItensVendasService {
   
    private ItensVendasDAO itensVendasDAO;
   
   public ItensVendasServiceImpl()throws RemoteException{
       super();
       this.itensVendasDAO = new ItensVendasDAO();
   }

    @Override
    public void salvar(ItensVendas obj) throws RemoteException {
       itensVendasDAO.salvar(obj);
    }

    @Override
    public List<ItensVendas> listaIntens(int venda_id) throws RemoteException {
        return itensVendasDAO.listaIntens(venda_id);
    }
    
}
