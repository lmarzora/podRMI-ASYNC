package tp2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by lumarzo on 12/10/16.
 */
public interface LibraryService extends Remote {
    List<String> listBooks() throws RemoteException;
    Book lendBook(String isbn) throws RemoteException;
    void returnBook(Book bookToReturn) throws RemoteException;
}
