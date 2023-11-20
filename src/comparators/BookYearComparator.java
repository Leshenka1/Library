package comparators;

import models.LibraryBook;
import java.util.Comparator;

public class BookYearComparator implements Comparator<LibraryBook> {
    public int compare(LibraryBook libraryBook1, LibraryBook libraryBook2) {
        return Integer.compare(libraryBook1.getYear(), libraryBook2.getYear());
    }
}
