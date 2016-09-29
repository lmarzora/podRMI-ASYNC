package ar.edu.itba.client;

import ar.edu.itba.Analyzer;
import ar.edu.itba.ClientHandle;
import ar.edu.itba.Movie;
import ar.edu.itba.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client implements ClientHandle{
    private static Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        logger.info("rmi-async Client Starting ...");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        Analyzer auxi= new Analyzer(args);
        Object port = auxi.get("PORT");
        Object hostname = auxi.get("HOSTNAME");
        Object service = auxi.get("SERVICE");
        Object action = auxi.get("ACTION");
        Object movieName = auxi.get("MOVIENAME");
        Object genre = auxi.get("GENRE");
        auxi.dump();

//        String hostname = "localhost";
//        int port = 2001;
//        String service = "News";
//        String genre = "Aventura";

        Client client = new Client();
        MovieService movieService = (MovieService) Naming.lookup(String.format("//%s:%s/%s", hostname, port, service ) );
        UnicastRemoteObject.exportObject(client,0);

        if(action.equals("subscribe")) {
            movieService.subscribe(client, (String) genre);
        }
        else if(action.equals("add")) {
            movieService.addMovie(new Movie((String)movieName,(String)genre));
        }

    }





    @Override
    public void notify(Movie movie) throws RemoteException {
        logger.info("Nuevo estreno: {}",movie);
    }
}
