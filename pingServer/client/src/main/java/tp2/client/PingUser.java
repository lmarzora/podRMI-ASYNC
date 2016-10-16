package tp2.client;

import tp2.PingService;

import java.rmi.RemoteException;

/**
 * Created by lumarzo on 12/10/16.
 */
public class PingUser {
    PingService service;
    public PingUser(PingService service) {
        this.service=service;
    }

    public void test() throws RemoteException {

        System.out.println(service.fortune());
    }

}
