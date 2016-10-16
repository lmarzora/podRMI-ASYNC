package tp2.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tp2.Book;
import tp2.LibraryService;
import tp2.PingService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

public class Server {
    private static Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        logger.info("pingServer Server Starting ...");
        Collection<Book> db = Arrays.asList(
                new Book("titulo1","juan","pepe",LocalDate.of(2016,10,1),"q2k2thku3"),
                new Book("titulo2","juan","pepe",LocalDate.of(2016,10,1),"22wwsk2u3"),
                new Book("titulo3","juan","pepe",LocalDate.of(2016,10,1),"qxxx2thk2")
        );

        String hostname = "localhost";
        String service = "library";
        int port = 10200;
        try {
            Registry registry = LocateRegistry.getRegistry(hostname, port);
            LibraryService stub = new Library(db);
            //PingService stub = new Ping(new String[]{"a","b","c"});
            registry.rebind(service, stub);
            System.out.println("Service bound");
        } catch (RemoteException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
