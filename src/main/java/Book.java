public class Book {
    private int bookId;
    private String title;
    private String author;
    private String edition;

    public Book(int bookId, String title, String author, String edition) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.edition = edition;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getEdition() {
        return edition;
    }

    @Override
    public String toString() {
        return bookId + " - " + title + " (" + edition + ")";
    }
}
