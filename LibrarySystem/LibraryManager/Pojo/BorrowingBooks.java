package TestExample.LibraryManager.Pojo;

import java.util.Date;

/**
 * @author loop
 * @version 1.0
 */
public class BorrowingBooks {
    String LibraryCardNumber, ISBN;
    int BorrowingPeriod;
    double Penalty;
    Date BorrowedDate, ReturnDate;

    @Override
    public String toString() {
        return "BorrowingBooks{" +
                "LibraryCardNumber='" + LibraryCardNumber + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", BorrowingPeriod=" + BorrowingPeriod +
                ", Penalty=" + Penalty +
                ", BorrowedDate=" + BorrowedDate +
                ", ReturnDate=" + ReturnDate +
                '}';
    }

    public void setLibraryCardNumber(String libraryCardNumber) {
        LibraryCardNumber = libraryCardNumber;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setBorrowingPeriod(int borrowingPeriod) {
        BorrowingPeriod = borrowingPeriod;
    }

    public void setPenalty(double penalty) {
        Penalty = penalty;
    }

    public void setBorrowedDate(Date borrowedDate) {
        BorrowedDate = borrowedDate;
    }

    public void setReturnDate(Date returnDate) {
        ReturnDate = returnDate;
    }

}
