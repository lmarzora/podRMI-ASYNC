package tp2.server;

import tp2.Book;
import tp2.LibraryService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by lumarzo on 12/10/16.
 */
public class Library implements LibraryService {
    private Map<String,Book> lib;
    private Map<Book,Integer> stock;
    private Object stockLock;

    public Library(Collection<Book> db) throws RemoteException{
        UnicastRemoteObject.exportObject(this, 0);
        lib = new HashMap<>();
        db.stream().forEach((book -> lib.put(book.getIsbn(),book)));

        stock = new HashMap<>();
        for (Book b : db) {
            stock.put(b,2);
        }

    }


    @Override
    public List<String> listBooks() throws RemoteException {
        List<String> res =  lib.values().stream().filter((book) -> stock.get(book) != 0).map((Book::toString)).collect(Collectors.toList());
        return res;
    }

    @Override
    public Book lendBook(String isbn) throws RemoteException {
        Book book = lib.get(isbn);
        synchronized (stockLock) {
            int cant = stock.get(book);
            if(cant > 0) {
                stock.put(book, cant - 1);
                return book;
            }
        }
        return null;
    }

    @Override
    public void returnBook(Book bookToReturn) throws RemoteException {
        if(lib.containsKey(bookToReturn.getIsbn())) {
            synchronized (stockLock) {
                int cant = stock.get(bookToReturn);
                stock.put(bookToReturn, cant + 1);
            }
        }
    }
}
