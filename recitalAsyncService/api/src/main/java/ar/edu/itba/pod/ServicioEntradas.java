package ar.edu.itba.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lumarzo on 13/10/16.
 */
public interface ServicioEntradas extends Remote {
    void solicitarEntrada(String nombreRecital, ClienteEntradas handler) throws RemoteException;
}
