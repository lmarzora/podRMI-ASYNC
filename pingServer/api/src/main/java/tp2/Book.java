package tp2;

import java.io.Serializable;
import java.rmi.Remote;
import java.time.LocalDate;

/**
 * Created by lumarzo on 12/10/16.
 */
public class Book implements Serializable{
    final private String title;
    final private String authorName;
    final private String authorLastname;
    final private LocalDate publishDate;
    final private String isbn;

    public Book(String title, String authorName, String authorLastname, LocalDate publishDate, String isbn) {
        this.title = title;
        this.authorName = authorName;
        this.authorLastname = authorLastname;
        this.publishDate = publishDate;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorLastname() {
        return authorLastname;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return isbn + "-" + title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        return getIsbn().equals(book.getIsbn());

    }

    @Override
    public int hashCode() {
        return getIsbn().hashCode();
    }
}
