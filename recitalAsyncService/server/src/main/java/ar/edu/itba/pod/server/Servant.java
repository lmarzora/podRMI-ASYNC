package ar.edu.itba.pod.server;

import ar.edu.itba.pod.ClienteEntradas;
import ar.edu.itba.pod.ServicioEntradas;
import ar.edu.itba.pod.ServicioRecitales;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lumarzo on 13/10/16.
 */
public class Servant implements ServicioEntradas, ServicioRecitales {

    private static Logger logger = LoggerFactory.getLogger(Servant.class);
    private Map<String,Recital> recitales;

    public Servant() throws RemoteException{
        UnicastRemoteObject.exportObject(this,0);
        this.recitales = new HashMap<>();
    }

    @Override
    public void solicitarEntrada(String nombreRecital, ClienteEntradas handler) throws RemoteException {

        if(!recitales.containsKey(nombreRecital)) {
            throw new RemoteException("El recital no existe");
        }

        Recital recital = recitales.get(nombreRecital);
        if (recital.getState() == Recital.State.CANCELADO) {
            handler.recitalCancelado(recital.getName());
        } else if (recital.getState() == Recital.State.AGOTADO) {
            handler.recitalCancelado(recital.getName());
        }

        recital.reservarEntrada(handler);
    }

    @Override
    public void crear(String nombre, int cantidadParaConfirmar, int entradasVip, int capacidadMaxima) throws RemoteException {
        if (recitales.containsKey(nombre)) {
            logger.warn("se intento crear un recital de nombre {} pero ya existe un recital con ese nombre",nombre);
            return;
        }
        recitales.put(nombre,new Recital(nombre,cantidadParaConfirmar,capacidadMaxima,entradasVip));
        logger.info("Recital {} creado",nombre);
    }

    @Override
    public void cancelar(String nombre) throws RemoteException {
        if (recitales.containsKey(nombre)) {
            Recital recital = recitales.remove(nombre);
            recital.cancelar();
            logger.info("Recital {} cancelado", recital.getName());
            return;
        }
        logger.warn("Se intento cancelar un recital de nombre {} pero no existe un recital con ese nombre",nombre);
    }

}
