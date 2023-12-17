package com.zas.JPA;


import jakarta.persistence.*;

@Entity
@Table(name = "librarybooks")
@NamedQueries({
        @NamedQuery(name = "LibraryBook.findAvailableCopies",
                query = "SELECT b.availableCopies FROM LibraryBookJPA b WHERE b.title = :title"),
        @NamedQuery(name = "LibraryBook.findBooksByAuthor",
                query = "SELECT b FROM LibraryBookJPA b WHERE b.author = :author"),
        @NamedQuery(name = "LibraryBook.issueBookToReader",
                query = "UPDATE LibraryBookJPA b SET b.readerID = :readerID WHERE b.bookID = :bookID AND b.availableCopies > 0 AND b.readerID <= 0"),
        @NamedQuery(name = "LibraryBook.decreaseAvailableCopies",
                query = "UPDATE LibraryBookJPA b SET b.availableCopies = b.availableCopies - 1 WHERE b.title = :title AND b.availableCopies > 0")

})
public class LibraryBookJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookID;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "copyNumber")
    private int copyNumber;

    @Column(name = "availableCopies")
    private int availableCopies;

    @Column(name = "readerID")
    private int readerID;

    @Column(name = "year")
    private int year;

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public int getReaderID() {
        return readerID;
    }

    public void setReaderID(int readerID) {
        this.readerID = readerID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "LibraryBook{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", copyNumber=" + copyNumber +
                ", availableCopies=" + availableCopies +
                ", readerID=" + readerID +
                ", year=" + year +
                '}';
    }
}
