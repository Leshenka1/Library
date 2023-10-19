package models;

// Класс, представляющий сущность Книги в библиотеке
// Класс LibraryBook наследуется от класса Book
public class LibraryBook extends Book {
    private Book book;
    private int copyNumber;
    private int availableCopies;
    private int readerId = -1;


    public LibraryBook(String title, String author, int year,  int id) {
        super(title, author, year, id);
        this.availableCopies++;
        this.copyNumber++;
    }

    public LibraryBook(Book book) {
        super(book);
        this.availableCopies++;
        this.copyNumber++;
    }

    public Book getBook() {
        return book;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }
    public void decreaseAvailableCopies() {
        if (availableCopies > 0) {
            availableCopies--;
        }
    }

    public void increaseAvailableCopies() {
        availableCopies++;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }
}
