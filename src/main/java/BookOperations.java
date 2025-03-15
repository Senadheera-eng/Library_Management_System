import javax.swing.*;
import java.util.Map;

public class BookOperations {
    private static Map<Integer, Book> books = DataHandler.loadBooks();

    public static void addBook() {
        JTextField bookIdField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField editionField = new JTextField();

        Object[] fields = {
                "Book ID:", bookIdField,
                "Title:", titleField,
                "Author:", authorField,
                "Edition:", editionField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Add Book", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int bookId = Integer.parseInt(bookIdField.getText().trim());
                String title = titleField.getText().trim();
                String author = authorField.getText().trim();
                String edition = editionField.getText().trim();

                if (books.containsKey(bookId)) {
                    JOptionPane.showMessageDialog(null, "Book ID already exists!");
                    return;
                }

                books.put(bookId, new Book(bookId, title, author, edition));
                DataHandler.saveBooks(books);
                JOptionPane.showMessageDialog(null, "Book added successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid Book ID!");
            }
        }
    }

    public static void discardBook() {
        String bookIdStr = JOptionPane.showInputDialog("Enter Book ID to Discard:");
        if (bookIdStr != null) {
            try {
                int bookId = Integer.parseInt(bookIdStr.trim());

                if (!books.containsKey(bookId)) {
                    JOptionPane.showMessageDialog(null, "Book ID not found!");
                    return;
                }

                books.remove(bookId);
                DataHandler.saveBooks(books);
                JOptionPane.showMessageDialog(null, "Book discarded successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid Book ID!");
            }
        }
    }
}
