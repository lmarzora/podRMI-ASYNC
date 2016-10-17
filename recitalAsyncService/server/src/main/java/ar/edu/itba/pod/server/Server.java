package ar.edu.itba.pod.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    private static Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        logger.info("recitalAsyncService Server Starting ...");
        String hostname = "localhost";
        int port = 10200;
        String serviceName = "recitales";
        try {
            Servant servant = new Servant();
            Registry registry = LocateRegistry.getRegistry(hostname,port);
            registry.rebind(serviceName,servant);
            logger.info("Service Bound");
        } catch (RemoteException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
