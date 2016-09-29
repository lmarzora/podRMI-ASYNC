package ar.edu.itba;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lumarzo on 28/09/16.
 */
public interface ClientHandle extends Remote{
    void notify(Movie movieName) throws RemoteException;
}
