package ar.edu.itba.server;

import ar.edu.itba.Analyzer;
import ar.edu.itba.ClientHandle;
import ar.edu.itba.Movie;
import ar.edu.itba.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Server implements MovieService {
    private static Logger logger = LoggerFactory.getLogger(Server.class);

    private static List<Movie> db = Arrays.asList(new Movie("Mr.Holmes", "Drama"),
            new Movie("Las sillas musicales", "Comedia"),
            new Movie("Un día perfecto", "Drama"),
            new Movie("Aloha", "Comedia"),
            new Movie("Anacleto:Agente secreto", "Aventura"),
            new Movie("Ático sin ascenso", "Drama"),
            new Movie("Everest", "Aventura"),
            new Movie("Rick y Morty", "Aventura"),
            new Movie("Una semana en Córcega", "Comedia"),
            new Movie("Eden", "Drama"));

    private static Map<String,Set<ClientHandle>> subscriptions = new HashMap<>();
    public static void main(String[] args) throws RemoteException {
        logger.info("rmi-async Server Starting ...");

        db = new ArrayList<>(db);
        Analyzer auxi= new Analyzer(args);
        int port = Integer.valueOf( auxi.get("PORT").toString() );
        String hostname = auxi.get("HOSTNAME").toString();
        String service = auxi.get("SERVICE").toString();
        auxi.dump();

//        String hostname = "localhost";
//        int port = 2001;
//        String service = "News";

        Registry registry = LocateRegistry.getRegistry(hostname, port);
        MovieService stub = new Server();
        UnicastRemoteObject.exportObject(stub, 0);
        registry.rebind(service, stub);
        System.out.println("Service bound");
    }

    @Override
    public void subscribe(ClientHandle handle, String genre) throws RemoteException {
        logger.info("received request for {}", handle);
        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getGenre().equalsIgnoreCase(genre)) {
                handle.notify(db.get(i));
            }
        }
        Set<ClientHandle> subscribers = subscriptions.get(genre);
        if (subscribers == null) {
            subscribers = new HashSet<>();
            subscribers.add(handle);
            subscriptions.put(genre,subscribers);
        } else {
            subscribers.add(handle);
        }
        try {
            Thread.sleep(1000 * 10);
        } catch (final InterruptedException e) {
            logger.debug("Interrupted while sleeping", e);
        }
    }

    @Override
    public void addMovie(Movie movie) throws RemoteException{
        logger.info("received request for {}", movie);
        if(db.contains(movie)){
            return;
        }
        db.add(movie);

        String genre = movie.getGenre();
        Set<ClientHandle> subscribers = subscriptions.get(genre);
        if(subscribers == null) {
            return;
        }

        for (ClientHandle handle: subscribers) {
            handle.notify(movie);
        }
    }
}
