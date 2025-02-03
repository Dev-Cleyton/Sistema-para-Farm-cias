/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BoletoService extends Remote{
    boolean gerarBoletoParcela(Integer idParcela) throws RemoteException;
}
