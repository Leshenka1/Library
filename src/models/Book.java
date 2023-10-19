package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Класс, представляющий сущность Книги
public class Book {
    private String title;
    private String author;
    private int year;
    private int id;

    public Book(String title, String author, int year, int id) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.id = id;
    }

    public Book(Book book) {
        this.title = book.title;
        this.author = book.author;
        this.year = book.year;
        this.id++;
    }

    // Геттеры и сеттеры

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getId() {
        return id;
    }


    // Переопределение метода toString() для вывода информации о книге
    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Year: " + year + ", Id: " + id;
    }
}