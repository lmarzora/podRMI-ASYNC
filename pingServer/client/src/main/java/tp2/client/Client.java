package tp2.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tp2.LibraryService;
import tp2.PingService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    private static Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        logger.info("pingServer Client Starting ...");
        String hostname = "localhost";
        String port = "10200";
        String service = "library";
        String url = String.format("//%s:%s/%s", hostname, port, service );
        try {
            LibraryService libraryService = (LibraryService) Naming.lookup(url);
            LibraryUser user = new LibraryUser(libraryService);
            user.test();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
