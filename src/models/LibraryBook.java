package models;

public class LibraryBook extends Book {
    private int copyNumber;
    private int availableCopies;
    private int readerId = -1;

    private LibraryBook(Builder builder) {
        super(builder.title, builder.author, builder.year, builder.id);
        this.availableCopies = 1;
        this.copyNumber = 1;
    }

    public int getAvailableCopies() {
        return this.availableCopies;
    }

    public int getCopyNumber() {
        return this.copyNumber;
    }

    public int getReaderId() {
        return this.readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public void decreaseAvailableCopies() {
        if (this.availableCopies > 0) {
            this.availableCopies--;
        }
    }

    public void increaseAvailableCopies() {
        this.availableCopies++;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public static class Builder {
        private String title;
        private String author;
        private int year;
        private int id;

        public Builder(String title, String author, int year, int id) {
            this.title = title;
            this.author = author;
            this.year = year;
            this.id = id;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public LibraryBook build() {
            return new LibraryBook(this);
        }
    }
}