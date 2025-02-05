package br.com.sistema.rmi;

import br.com.sistema.dao.FornecedoresDAO;
import br.com.sistema.model.Fornecedores;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
/**
 *
 * @author cleyton
 */
public class FornecedoresServiceImpl extends UnicastRemoteObject implements FornecedoresService{
    
    private FornecedoresDAO fornecedoresDAO;
    
    public FornecedoresServiceImpl() throws RemoteException{
        super();
        this.fornecedoresDAO = new FornecedoresDAO();
    }
    
    @Override
    public void SalvarFornecedoresDao(Fornecedores fornecedor)throws RemoteException{
        System.out.println("Fonecedor salvo com Sucesso");
        fornecedoresDAO.SalvarFornecedoresDao(fornecedor);
    }
    
    @Override
    public    void EditarFornecedoreDao(Fornecedores fornecedor) throws RemoteException{
        fornecedoresDAO.EditarFornecedoreDao(fornecedor);
    }
    @Override
    public void ExcluirFornecedoresDao(Fornecedores fornecedor) throws RemoteException{
        fornecedoresDAO.ExcluirFornecedoresDao(fornecedor);
    }
    
    @Override
    public  Fornecedores BuscarFornecedoresDao(String nome)throws RemoteException {
        return fornecedoresDAO.BuscarFornecedoresDao(nome);
    }
    
    @Override
    public List<Fornecedores> ListarFornecedoreDao() throws RemoteException{
        try{
        return fornecedoresDAO.ListarFornecedoreDao();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Erro ao listar fornecedores", e);
        }
    }
    
    @Override
    public List<Fornecedores> FiltarFornecedoresDao() throws RemoteException{
      return fornecedoresDAO.FiltarFornecedoresDao(null);
    }
}

