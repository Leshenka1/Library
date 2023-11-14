import models.Reader;
import DAO.JdbcConnector;
import DAO.ReaderDAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        Connection connection = JdbcConnector.getConnection();

//        Library library = new Library();
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
//        LibraryBook libraryBook4 = new LibraryBook.Builder("Book4", "Author4", 2019, 4)
//                .build();
//        libraryBook4.setAvailableCopies(1);
//        libraryBook4.setCopyNumber(1);
//        libraryBook4.setReaderId(4);
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

        List<Reader> readers = new ArrayList<>();

        ReaderDAO readerDAO = new ReaderDAO();

        Reader reader4 = readerDAO.getReaderById(4);

        System.out.println(reader4.toString());

        Reader newReader4 = new Reader.Builder("Reader4", 50, 4)
                .build();
        newReader4.setHasOverdueFees(false);
        newReader4.setFeesDays(20);

        readerDAO.updateReader(newReader4);

        reader4 = readerDAO.getReaderById(4);

        System.out.println(reader4.toString());


//        // Создание нескольких книг
//        LibraryBook libraryBook1 = new LibraryBook.Builder("Book1", "Author1", 1)
//                .year(2023)
//                .build();
//        LibraryBook libraryBook2 = new LibraryBook.Builder("Book2", "Author2", 2)
//                .year(2019)
//                .build();
//        LibraryBook libraryBook3 = new LibraryBook.Builder("Book3", "Author3", 3)
//                .year(2020)
//                .build();
//
//        // Создание библиотеки и добавление книг
//        Library library = new Library();
//        library.addBook(libraryBook1);
//        library.addBook(libraryBook2);
//        library.addBook(libraryBook3);
//
//        Reader reader1 = new Reader.Builder("Reader1", 1)
//                .age(18)
//                .build();
//        Reader reader2 = new Reader.Builder("Reader2", 2)
//                .age(20)
//                .build();
//        Reader reader3 = new Reader.Builder("Reader3", 3)
//                .age(44)
//                .build();
//
//        library.addReader(reader1);
//        library.addReader(reader2);
//        library.addReader(reader3);
//
//
//        library.booksToString();
//
//        // Сортировка книг по году
//        library.sortBooks(Comparator.comparing(Book::getYear));
//
//        library.booksToString();
//
//        // Поиск книги по названию
//        LibraryBook foundBook = library.findBook("Title2");
//
//        if (foundBook != null) {
//            System.out.println("Found Book: " + foundBook.toString());
//        } else {
//            System.out.println("Book not found.");
//        }
//
////        Вывести информацию о наличии свободных экземпляров заданной книги.
//        System.out.println("information about availability of free copies of a given book(Book1)");
//        library.listAvailableCopiesInformationByTitle("Book1");
//        System.out.println("copy adding Book1");
//        LibraryBook libraryBook4 = new LibraryBook.Builder("Book1", "Author1", 4)
//                .year(2023)
//                .build();
//        library.addBook(libraryBook4);
//        library.listAvailableCopiesInformationByTitle("Book1");
//
////        Вывести информацию о книгах заданного автора
//        System.out.println("information about books of the given author");
//        library.listBooksInformationByAuthor("Author1");
//
////        Выдать книгу читателю, списать экземпляр книги
//        System.out.println("Handing out a book to the reader");
//        library.orderBookByReader(reader2, "Book2");
//        reader2.setFeesDays(20);
//        library.orderBookByReader(reader3, "Book3");
//        reader3.setFeesDays(35);
////      попытка аренды уже взятой книги
//        System.out.println("Issuing a missing book to the reader");
//        library.orderBookByReader(reader2, "Book2");
//
////        Вывести информацию о читателях, которые имеют задолженность более 1 месяца
//        System.out.println("information on readers who are in arrears for more than 1 month");
//        library.listReadersInformationByFees();

        JdbcConnector.closeConnection(); // Не забудьте закрыть соединение после использования
    }
}