package tp2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * Created by lumarzo on 12/10/16.
 */
public interface PingService extends Remote {
    String ping() throws RemoteException;
    Date time() throws RemoteException;
    String echo(final String s) throws RemoteException;
    String hello(final String name) throws RemoteException;
    String fortune() throws RemoteException;

}
