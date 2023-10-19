import models.Book;
import models.Library;
import models.LibraryBook;
import models.Reader;

import java.util.Comparator;


public class Main {
    public static void main(String[] args) {
        // Создание нескольких книг
        LibraryBook libraryBook1 = new LibraryBook("Book1", "Author1", 2000, 1);
        LibraryBook libraryBook2 = new LibraryBook("Book2", "Author2", 1995, 2);
        LibraryBook libraryBook3 = new LibraryBook("Book3", "Author3", 2010, 3);

        // Создание библиотеки и добавление книг
        Library library = new Library();
        library.addBook(libraryBook1);
        library.addBook(libraryBook2);
        library.addBook(libraryBook3);

        Reader reader1 = new Reader("Reader1", 18, 1);
        Reader reader2 = new Reader("Reader2", 24, 2);
        Reader reader3 = new Reader("Reader3", 44, 3);

        library.addReader(reader1);
        library.addReader(reader2);
        library.addReader(reader3);

        library.booksToString();

        // Сортировка книг по году
        library.sortBooks(Comparator.comparing(Book::getYear));

        library.booksToString();

        // Поиск книги по названию
        LibraryBook foundBook = library.findBook("Book2");

        if (foundBook != null) {
            System.out.println("Found Book: " + foundBook.toString());
        } else {
            System.out.println("Book not found.");
        }

//        Вывести информацию о наличии свободных экземпляров заданной книги.
        System.out.println("information about availability of free copies of a given book(Book1)");
        library.listAvailableCopiesInformationByTitle("Book1");
        System.out.println("copy adding Book1");
        LibraryBook libraryBook4 = new LibraryBook("Book1", "Author1", 2000, 4);
        library.addBook(libraryBook4);
        library.listAvailableCopiesInformationByTitle("Book1");

//        Вывести информацию о книгах заданного автора
        System.out.println("information about books of the given author");
        library.listBooksInformationByAuthor("Author1");

//        Выдать книгу читателю, списать экземпляр книги
        System.out.println("Handing out a book to the reader");
        library.orderBookByReader(reader2, "Book2");
        reader2.setFeesDays(20);
        library.orderBookByReader(reader3, "Book3");
        reader3.setFeesDays(35);
//      попытка аренды уже взятой книги
        System.out.println("Issuing a missing book to the reader");
        library.orderBookByReader(reader2, "Book2");

//        Вывести информацию о читателях, которые имеют задолженность более 1 месяца
        System.out.println("information on readers who are in arrears for more than 1 month");
        library.listReadersInformationByFees();
    }
}