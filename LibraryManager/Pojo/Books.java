package TestExample.LibraryManager.Pojo;

/**
 * @author loop
 * @version 1.0
 */
public class Books {
    String ISBN, LibraryName, PublishingHouse, Author, Borrowable;
    double CollectionNumber, BorrowableNumber;

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setLibraryName(String libraryName) {
        LibraryName = libraryName;
    }

    public void setPublishingHouse(String publishingHouse) {
        PublishingHouse = publishingHouse;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setBorrowable(String borrowable) {
        Borrowable = borrowable;
    }

    public void setCollectionNumber(double collectionNumber) {
        CollectionNumber = collectionNumber;
    }

    public void setBorrowableNumber(double borrowableNumber) {
        BorrowableNumber = borrowableNumber;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getLibraryName() {
        return LibraryName;
    }

    public String getPublishingHouse() {
        return PublishingHouse;
    }

    public String getAuthor() {
        return Author;
    }

    public String getBorrowable() {
        return Borrowable;
    }

    public double getCollectionNumber() {
        return CollectionNumber;
    }

    public double getBorrowableNumber() {
        return BorrowableNumber;
    }

    @Override
    public String toString() {
        return "Books{" +
                "ISBN='" + ISBN + '\'' +
                ", LibraryName='" + LibraryName + '\'' +
                ", PublishingHouse='" + PublishingHouse + '\'' +
                ", Author='" + Author + '\'' +
                ", Borrowable='" + Borrowable + '\'' +
                ", CollectionNumber=" + CollectionNumber +
                ", BorrowableNumber=" + BorrowableNumber +
                '}';
    }
}
