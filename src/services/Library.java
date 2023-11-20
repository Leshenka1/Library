package services;

import DAO.LibraryBookDAO;
import DAO.ReaderDAO;
import comparators.BookYearComparator;
import models.LibraryBook;
import models.Reader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Класс библиотеки, реализующий паттерн Singleton
public class Library {
    private static Library instance;
    private List<LibraryBook> libraryBooks;
    private List<Reader> readers;

    private ReaderDAO readerDAO;

    private LibraryBookDAO libraryBookDAO;


    public Library() {
        libraryBooks = new ArrayList<>();
        readers = new ArrayList<>();
        readerDAO = new ReaderDAO();
        libraryBookDAO = new LibraryBookDAO();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(LibraryBook libraryBook) {
        libraryBooks = libraryBookDAO.getAllBooks();
        for (LibraryBook existLibraryBook : libraryBooks) {
            if (existLibraryBook.getTitle().equals(libraryBook.getTitle())) {
                existLibraryBook.increaseAvailableCopies();
                libraryBook.increaseAvailableCopies();
                libraryBookDAO.updateBook(existLibraryBook);
                if(libraryBook.getCopyNumber() <= existLibraryBook.getCopyNumber()){
                    libraryBook.setCopyNumber(existLibraryBook.getCopyNumber() + 1);
                }
            }
        }
        libraryBookDAO.addBook(libraryBook);
    }

    public void addReader(Reader reader) {
        readerDAO.addReader(reader);
    }

    public List<LibraryBook> getLibraryBooks() {
        return libraryBookDAO.getAllBooks();
    }

    public List<Reader> getReaders() {
        return readerDAO.getAllReaders();
    }

    // Метод для сортировки книг в библиотеке по заданному критерию
    public List<LibraryBook> sortBooks() {
        return getLibraryBooks().stream().sorted(new BookYearComparator()).toList();
    }

    // Метод для поиска книги в библиотеке
    public LibraryBook findBook(String title) {
        libraryBooks = libraryBookDAO.getAllBooks();
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
        System.out.println(libraryBook.getTitle() +
                ", Copies Available: " + libraryBook.getAvailableCopies());

    }

    //информация о читателях, которые имеют задолженность более 1 месяца
    public void listReadersInformationByFees() {
        readers = readerDAO.getAllReaders();
        for (Reader reader : readers) {
            if (reader.getFeesDays() > 30) {
                System.out.println("Reader with overdue fees: " + reader.toString());
            }
        }
    }

    //Вывести информацию о книгах заданного автора.
    public void listBooksInformationByAuthor(String author) {
        libraryBooks = libraryBookDAO.getAllBooks();
        for (LibraryBook libraryBook : libraryBooks) {
            if (libraryBook.getAuthor().equals(author)) {
                System.out.println(libraryBook.toString());
            }
        }
    }

    //Выдать книгу читателю, списать экземпляр книги.
    public void orderBookByReader(Reader reader, String title) {
        libraryBooks = libraryBookDAO.getAllBooks();
        for (LibraryBook libraryBook : libraryBooks) {
            if (libraryBook.getTitle().equals(title) && libraryBook.getAvailableCopies() > 0 && libraryBook.getReaderId() < 0) {
                libraryBook.setReaderId(reader.getId());
                for (LibraryBook libraryBookDecrease : libraryBooks) {
                    if (libraryBookDecrease.getTitle().equals(title)){
                        libraryBookDecrease.decreaseAvailableCopies();
                        libraryBookDAO.updateBook(libraryBookDecrease);
                    }
                }
                reader.setHasOverdueFees(true);            //просто для вида
                readerDAO.updateReader(reader);
                System.out.println(reader.getName() + " has borrowed the book: " + title);
                return;
            }
        }
        System.out.println("Book not available for borrowing: " + title);
    }
//TODO: make method to enable readers return their books
//    public void returnBook(Reader reader, String title) {
//        for (LibraryBook libraryBook : libraryBooks) {
//            if (libraryBook.getTitle().equals(title)) {
//                libraryBook.increaseAvailableCopies();
//                libraryBook.setReaderId(-1);
//                System.out.println(reader.getName() + " has returned the book: " + title);
//                return;
//            }
//        }
//        System.out.println("Book not found in the library: " + title);
//    }

    public String booksToString() {
        libraryBooks = libraryBookDAO.getAllBooks();
        StringBuilder allBooks = new StringBuilder();
        for (LibraryBook libraryBook : libraryBooks) {
            allBooks.append(libraryBook.toString() + "\n");
        }
        return allBooks.toString();
    }

    public String readersToString() {
        readers = readerDAO.getAllReaders();
        StringBuilder allReaders = new StringBuilder();
        for (Reader reader : readers) {
            allReaders.append(reader.toString() + "\n");
        }
        return allReaders.toString();
    }
}
