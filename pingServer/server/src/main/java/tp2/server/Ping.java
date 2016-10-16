package tp2.server;

import tp2.PingService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Random;

/**
 * Created by lumarzo on 12/10/16.
 */
public class Ping implements PingService {

    String[] db;


    public Ping(String[] db) throws RemoteException{
        UnicastRemoteObject.exportObject(this, 0);
        this.db = db;
    }



    @Override
    public String ping() throws RemoteException {
        return "pong";
    }

    @Override
    public Date time() throws RemoteException {
        return new Date();
    }

    @Override
    public String echo(String s) throws RemoteException {
        return s;
    }

    @Override
    public String hello(String name) throws RemoteException {
        return "hello " + name;
    }

    @Override
    public String fortune() throws RemoteException {
        Random rnd = new Random();
        int i = rnd.nextInt(db.length);
        return db[i];
    }
}
