package ar.edu.itba.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lumarzo on 13/10/16.
 */
public interface ServicioRecitales extends Remote {
    void crear(String nombre, int cantidadParaConfirmar, int entradasVip, int capacidadMaxima) throws RemoteException;
    void cancelar(String nombre) throws RemoteException;
}
