package ar.edu.itba;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lumarzo on 28/09/16.
 */
public interface MovieService extends Remote{
    void subscribe(ClientHandle handle, String genre) throws RemoteException;
    void addMovie(Movie movie)throws RemoteException;
}
