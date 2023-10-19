package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Класс библиотеки, реализующий паттерн Singleton
public class Library {
    private static Library instance;
    private List<LibraryBook> libraryBooks;
    private List<Reader> readers;

    public Library() {
        libraryBooks = new ArrayList<>();
        readers = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(LibraryBook libraryBook) {
        for (LibraryBook existLibraryBook : libraryBooks) {
            if (existLibraryBook.getTitle().equals(libraryBook.getTitle())) {
                existLibraryBook.increaseAvailableCopies();
                libraryBook.increaseAvailableCopies();
                if(libraryBook.getCopyNumber() <= existLibraryBook.getCopyNumber()){
                    libraryBook.setCopyNumber(existLibraryBook.getId() + 1);
                }
            }
        }
        libraryBooks.add(libraryBook);
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public List<LibraryBook> getLibraryBooks() {
        return libraryBooks;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    // Метод для сортировки книг в библиотеке по заданному критерию
    public void sortBooks(Comparator<LibraryBook> comparator) {
        Collections.sort(libraryBooks, comparator);
    }

    // Метод для поиска книги в библиотеке
    public LibraryBook findBook(String title) {
        for (LibraryBook libraryBook : libraryBooks) {
            if (libraryBook.getTitle().equals(title)) {
                return libraryBook;
            }
        }
        return null;
    }

    // наличие свободных экземпляров заданной книги.
    public void listAvailableCopiesInformationByTitle(String title) {
        LibraryBook libraryBook = findBook(title);
        System.out.println(libraryBook.toString() +
                ", Copies Available: " + libraryBook.getAvailableCopies());

    }

    //информация о читателях, которые имеют задолженность более 1 месяца
    public void listReadersInformationByFees() {
        for (Reader reader : readers) {
            if (reader.getFeesDays() > 30) {
                System.out.println("Reader with overdue fees: " + reader.toString());
            }
        }
    }

    public void listBooksInformationByAuthor(String author) {
        for (LibraryBook libraryBook : libraryBooks) {
            if (libraryBook.getAuthor().equals(author)) {
                System.out.println(libraryBook.toString() +
                        ", Copies Available: " + libraryBook.getAvailableCopies());
            }
        }
    }

    public void orderBookByReader(Reader reader, String title) {
        for (LibraryBook libraryBook : libraryBooks) {
            if (libraryBook.getTitle().equals(title) && libraryBook.getAvailableCopies() > 0 && libraryBook.getReaderId() < 0) {
                libraryBook.decreaseAvailableCopies();
                libraryBook.setReaderId(reader.getId());
                reader.setOverdueFees(true);            //просто для вида
                System.out.println(reader.getName() + " has borrowed the book: " + title);
                return;
            }
        }
        System.out.println("Book not available for borrowing: " + title);
    }

    public void returnBook(Reader reader, String title) {
        for (LibraryBook libraryBook : libraryBooks) {
            if (libraryBook.getTitle().equals(title)) {
                libraryBook.increaseAvailableCopies();
                libraryBook.setReaderId(-1);
                System.out.println(reader.getName() + " has returned the book: " + title);
                return;
            }
        }
        System.out.println("Book not found in the library: " + title);
    }

    public String booksToString() {
        StringBuilder allBooks = new StringBuilder();
        for (LibraryBook libraryBook : libraryBooks) {
            allBooks.append(libraryBook.toString() + "\n");
        }
        return allBooks.toString();
    }

    public String readersToString() {
        StringBuilder allReaders = new StringBuilder();
        for (Reader reader : readers) {
            allReaders.append(reader.toString() + "\n");
        }
        return allReaders.toString();
    }
}
