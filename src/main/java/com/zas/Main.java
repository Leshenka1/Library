package com.zas;


import com.zas.DAO.LibraryBookDAOJPA;
import com.zas.DAO.ReaderDAOJPA;
import com.zas.JPA.LibraryBookJPA;
import com.zas.JPA.ReaderJPA;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ReaderDAOJPA readerDAO = new ReaderDAOJPA();
        List<ReaderJPA> overdueReaders = readerDAO.findReadersWithOverdueFees();
        System.out.println("Readers with overdue fees: " + overdueReaders);

        LibraryBookDAOJPA libraryBookDAO = new LibraryBookDAOJPA();
        List<LibraryBookJPA> booksByAuthor = libraryBookDAO.findBooksByAuthor("Author4");
        System.out.println("Books by Some Author: " + booksByAuthor);

        List<Integer> booksByTitle = libraryBookDAO.findAvailableCopies("Book5");
        System.out.println("Book5 have  " + booksByTitle + " copies");

        libraryBookDAO.issueBookToReader(4, 5, "Book5");
        System.out.println("Issue Book5 by reader4");
//        libraryBookDAO.decreaseAvailableCopies("Book5");

        List<Integer> booksByTitle1 = libraryBookDAO.findAvailableCopies("Book5");
        System.out.println("Book5 have  " + booksByTitle1 + " copies");


        // Other operations...


//        Connection connection = JdbcConnector.getConnection();

//        Library library = new Library();
//
//        System.out.println("Books:");
//        System.out.println(library.booksToString());
//
//        LibraryBook libraryBook5 = new LibraryBook.Builder("Book5", "Author5", 1955, 5)
//                .build();
//        libraryBook5.setAvailableCopies(1);
//        libraryBook5.setCopyNumber(1);
//        library.addBook(libraryBook5);
//        System.out.println("add Book5");
//        System.out.println(library.booksToString());
//
//        LibraryBook libraryBook6 = new LibraryBook.Builder("Book5", "Author5", 1955, 6)
//                .build();
//        libraryBook6.setAvailableCopies(1);
//        libraryBook6.setCopyNumber(1);
//        library.addBook(libraryBook6);
//        System.out.println("add another Book5");
//        System.out.println(library.booksToString());
//
//        System.out.println("Readers:");
//        System.out.println(library.readersToString());
//
//        Reader reader4 = new Reader.Builder("Reader4", 50, 4)
//                .build();
//        reader4.setHasOverdueFees(false);
//        reader4.setFeesDays(0);
//        library.addReader(reader4);
//        System.out.println("add Reader4");
//        System.out.println(library.readersToString());
//
//
////        Сортировка книг по году
//        System.out.println("sorting books by year");
//        List<LibraryBook> sortedBooks = library.sortBooks();
//        StringBuilder allBooks = new StringBuilder();
//        for (LibraryBook libraryBook : sortedBooks) {
//            allBooks.append(libraryBook.toString() + "\n");
//        }
//        System.out.println(allBooks);
//
////        Поиск книги по названию
//        System.out.println("find book by title(Book 1)");
//        LibraryBook foundBook = library.findBook("Book 1");
//        System.out.println(foundBook.toString());
//
////        Вывести информацию о наличии свободных экземпляров заданной книги.
//        System.out.println("information about availability of free copies of a given book(Book5)");
//        library.listAvailableCopiesInformationByTitle("Book5");
//
////        Вывести информацию о книгах заданного автора
//        System.out.println("information about books of the given author(Author 1)");
//        library.listBooksInformationByAuthor("Author 1");
//
////        Выдать книгу читателю, списать экземпляр книги
//        System.out.println("Issuing a book to the reader");
//        library.orderBookByReader(reader4, "Book5");
//        reader4.setFeesDays(50);
//        System.out.println(library.booksToString());
//
////        попытка аренды уже взятой книги
//        System.out.println("Issuing a missing book to the reader");
//        library.orderBookByReader(reader4, "Book5");
//        System.out.println(library.booksToString());
//        library.orderBookByReader(reader4, "Book5");
//
//
////        Вывести информацию о читателях, которые имеют задолженность более 1 месяца
//        System.out.println("information on readers who are in fees for more than 1 month");
//        library.listReadersInformationByFees();
    }
}