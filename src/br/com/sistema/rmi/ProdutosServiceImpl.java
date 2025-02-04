
package br.com.sistema.rmi;

import br.com.sistema.dao.ProdutosDAO;
import br.com.sistema.model.Produtos;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


public class ProdutosServiceImpl extends UnicastRemoteObject implements ProdutosService {
    
    private ProdutosDAO produtosDAO;
    
    public ProdutosServiceImpl() throws RemoteException{
        super();
        this.produtosDAO = new ProdutosDAO();
    }

    @Override
    public void SalvarProdutoDao(Produtos produtos) throws RemoteException {
        produtosDAO.SalvarProdutoDao(produtos);
    }

    @Override
    public void EditarProdutoDao(Produtos produtos) throws RemoteException {
        produtosDAO.EditarProdutoDao(produtos);
    }

    @Override
    public void ExcluirProdutoDao(Produtos produtos) throws RemoteException {
        produtosDAO.ExcluirProdutoDao(produtos);
    }

    @Override
    public Produtos BuscarProdutoDao(String nome) throws RemoteException {
       return produtosDAO.BuscarProdutoDao(nome);
    }

    @Override
    public Produtos BuscarProdutoCodDao(int id) throws RemoteException {
       return produtosDAO.BuscarProdutoCodDao(id);
    }

    @Override
    public List<Produtos> ListarProdutoDao() throws RemoteException {
        return produtosDAO.ListarProdutoDao();
    }

    @Override
    public List<Produtos> FiltarProdutoDao(String nome) throws RemoteException {
        return produtosDAO.FiltarProdutoDao(nome);
    }

    @Override
    public void adicionarEstoque(int id, int qtd_nova) throws RemoteException {
       produtosDAO.adicionarEstoque(id, qtd_nova);
    }

    @Override
    public void baixaEstoque(int id, int qtd_nova) throws RemoteException {
      produtosDAO.baixaEstoque(id, qtd_nova);
    }

    @Override
    public int retornaQtdAtualEstoque(int id) throws RemoteException {
        return produtosDAO.retornaQtdAtualEstoque(id);
    }
    
}
