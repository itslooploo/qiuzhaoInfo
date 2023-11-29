package TestExample.LibraryManager.Pojo;

/**
 * @author loop
 * @version 1.0
 */
public class Reader {
    String LibraryCardNumber, ReaderName, Gender, JobTitle, Department, PhoneNumber;
    double  ReaderBorrowableNumber, ReaderBorrowedNumber;

    public void setLibraryCardNumber(String libraryCardNumber) {
        LibraryCardNumber = libraryCardNumber;
    }

    public void setReaderName(String readerName) {
        ReaderName = readerName;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setReaderBorrowableNumber(double readerBorrowableNumber) {
        ReaderBorrowableNumber = readerBorrowableNumber;
    }

    public void setReaderBorrowedNumber(double readerBorrowedNumber) {
        ReaderBorrowedNumber = readerBorrowedNumber;
    }

    public String getLibraryCardNumber() {
        return LibraryCardNumber;
    }

    public String getReaderName() {
        return ReaderName;
    }

    public String getGender() {
        return Gender;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public String getDepartment() {
        return Department;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "LibraryCardNumber='" + LibraryCardNumber + '\'' +
                ", ReaderName='" + ReaderName + '\'' +
                ", Gender='" + Gender + '\'' +
                ", JobTitle='" + JobTitle + '\'' +
                ", Department='" + Department + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", ReaderBorrowableNumber=" + ReaderBorrowableNumber +
                ", ReaderBorrowedNumber=" + ReaderBorrowedNumber +
                '}';
    }

    public double getReaderBorrowableNumber() {
        return ReaderBorrowableNumber;
    }

    public double getReaderBorrowedNumber() {
        return ReaderBorrowedNumber;
    }
}
