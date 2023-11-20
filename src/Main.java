import models.LibraryBook;
import models.Reader;
import DAO.JdbcConnector;
import DAO.ReaderDAO;
import services.Library;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Main {
    public static void main(String[] args) {

//        Connection connection = JdbcConnector.getConnection();

        Library library = new Library();

        System.out.println("Books:");
        System.out.println(library.booksToString());

        LibraryBook libraryBook5 = new LibraryBook.Builder("Book5", "Author5", 1955, 5)
                .build();
        libraryBook5.setAvailableCopies(1);
        libraryBook5.setCopyNumber(1);
        library.addBook(libraryBook5);
        System.out.println("add Book5");
        System.out.println(library.booksToString());

        LibraryBook libraryBook6 = new LibraryBook.Builder("Book5", "Author5", 1955, 6)
                .build();
        libraryBook6.setAvailableCopies(1);
        libraryBook6.setCopyNumber(1);
        library.addBook(libraryBook6);
        System.out.println("add another Book5");
        System.out.println(library.booksToString());

        System.out.println("Readers:");
        System.out.println(library.readersToString());

        Reader reader4 = new Reader.Builder("Reader4", 50, 4)
                .build();
        reader4.setHasOverdueFees(false);
        reader4.setFeesDays(0);
        library.addReader(reader4);
        System.out.println("add Reader4");
        System.out.println(library.readersToString());


//        Сортировка книг по году
        System.out.println("sorting books by year");
        List<LibraryBook> sortedBooks = library.sortBooks();
        StringBuilder allBooks = new StringBuilder();
        for (LibraryBook libraryBook : sortedBooks) {
            allBooks.append(libraryBook.toString() + "\n");
        }
        System.out.println(allBooks);

//        Поиск книги по названию
        System.out.println("find book by title(Book 1)");
        LibraryBook foundBook = library.findBook("Book 1");
        System.out.println(foundBook.toString());

//        Вывести информацию о наличии свободных экземпляров заданной книги.
        System.out.println("information about availability of free copies of a given book(Book5)");
        library.listAvailableCopiesInformationByTitle("Book5");

//        Вывести информацию о книгах заданного автора
        System.out.println("information about books of the given author(Author 1)");
        library.listBooksInformationByAuthor("Author 1");

//        Выдать книгу читателю, списать экземпляр книги
        System.out.println("Issuing a book to the reader");
        library.orderBookByReader(reader4, "Book5");
        reader4.setFeesDays(50);
        System.out.println(library.booksToString());

//        попытка аренды уже взятой книги
        System.out.println("Issuing a missing book to the reader");
        library.orderBookByReader(reader4, "Book5");
        System.out.println(library.booksToString());
        library.orderBookByReader(reader4, "Book5");


//        Вывести информацию о читателях, которые имеют задолженность более 1 месяца
        System.out.println("information on readers who are in fees for more than 1 month");
        library.listReadersInformationByFees();

//        JdbcConnector.closeConnection();


        //
//        //работа с libraryBook
//
//        List<LibraryBook> libraryBooks = new ArrayList<>();
//
//        LibraryBookDAO libraryBookDAO = new LibraryBookDAO();
//
//        libraryBooks = libraryBookDAO.getAllBooks();
//
//        StringBuilder allBooks = new StringBuilder();
//        for (LibraryBook libraryBook : libraryBooks) {
//            allBooks.append(libraryBook.toString() + "\n");
//        }
//
//        System.out.println(allBooks.toString());
//

//
//        libraryBookDAO.updateBook(libraryBook4);
//
//        libraryBooks = libraryBookDAO.getAllBooks();
//
//        allBooks = new StringBuilder();
//        for (LibraryBook libraryBook : libraryBooks) {
//            allBooks.append(libraryBook.toString() + "\n");
//        }
//
//        System.out.println(allBooks.toString());

        //работа с reader

//        List<Reader> readers = new ArrayList<>();
//
//        ReaderDAO readerDAO = new ReaderDAO();
//
//        Reader reader4 = readerDAO.getReaderById(4);
//
//        System.out.println(reader4.toString());
//
//        Reader newReader4 = new Reader.Builder("Reader4", 50, 4)
//                .build();
//        newReader4.setHasOverdueFees(false);
//        newReader4.setFeesDays(20);
//
//        readerDAO.updateReader(newReader4);
//
//        reader4 = readerDAO.getReaderById(4);
//
//        System.out.println(reader4.toString());
    }
}