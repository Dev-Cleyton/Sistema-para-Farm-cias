package br.com.sistema.rmi;

import br.com.sistema.dao.FuncionariosDAO;
import br.com.sistema.model.Funcionarios;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class FuncionariosServiceImpl extends UnicastRemoteObject implements FuncionariosService {
    
    private FuncionariosDAO funcionariosDAO;
    
    public FuncionariosServiceImpl() throws RemoteException{
        super();
        this.funcionariosDAO = new FuncionariosDAO();
    }
    @Override
    public void SalvarFuncionarioDao(Funcionarios funcionario) throws RemoteException{
        System.err.println("Funcionario Salvo ConSucesso");
        funcionariosDAO.SalvarFuncionarioDao(null);
    }
    
    @Override
    public void EditarFuncionarioDao(Funcionarios funcionario) throws RemoteException{
        funcionariosDAO.EditarFuncionarioDao(null);
    }
    
    @Override
    public void ExcluirFuncionarioDao(Funcionarios funcionario) throws RemoteException{
        funcionariosDAO.ExcluirFuncionarioDao(null);
    }
    
    @Override
    public Funcionarios BuscarFuncionariosDao(String nome) throws RemoteException{
       return funcionariosDAO.BuscarFuncionariosDao(nome);
    } 
    
    @Override
    public List<Funcionarios> ListarFuncionarioDao() throws RemoteException{
        return funcionariosDAO.ListarFuncionarioDao();
    }
    
    @Override
    public List<Funcionarios> FiltarFuncionarioDao(String nome) throws RemoteException{
        return funcionariosDAO.FiltarFuncionarioDao(nome);
    }
    
    @Override
    public boolean efetuarLogin(String email, String senha) throws RemoteException{
        return funcionariosDAO.efetuarLogin(email, senha);
    }
    
}
