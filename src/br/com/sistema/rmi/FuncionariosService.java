package br.com.sistema.rmi;


import br.com.sistema.model.Funcionarios;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FuncionariosService extends Remote {
    void SalvarFuncionarioDao(Funcionarios funcionario) throws RemoteException;
    void EditarFuncionarioDao(Funcionarios funcionario) throws RemoteException;
    void ExcluirFuncionarioDao(Funcionarios funcionario) throws RemoteException;
    Funcionarios BuscarFuncionariosDao(String nome) throws RemoteException;    
    List<Funcionarios> ListarFuncionarioDao() throws RemoteException;
    List<Funcionarios> FiltarFuncionarioDao(String nome) throws RemoteException;
    boolean efetuarLogin(String email, String senha) throws RemoteException;
}
