package tp2.client;

import tp2.LibraryService;

import java.rmi.RemoteException;

/**
 * Created by lumarzo on 12/10/16.
 */
public class LibraryUser {
    LibraryService service;
    public LibraryUser(LibraryService service){
        this.service=service;
    }

    public void test() throws RemoteException {

        service.listBooks().stream().forEach(System.out::println);

    }
}
