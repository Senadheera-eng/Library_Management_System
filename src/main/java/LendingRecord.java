public class LendingRecord {
    private int bookId;
    private int memberId;
    private String returnDate;

    public LendingRecord(int bookId, int memberId, String returnDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.returnDate = returnDate;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getReturnDate() {
        return returnDate;
    }
}
