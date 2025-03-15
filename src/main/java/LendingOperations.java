import javax.swing.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LendingOperations {
    private static Map<Integer, LendingRecord> lendingRecords = DataHandler.loadLendingRecords();
    private static Map<Integer, Book> books = DataHandler.loadBooks();
    private static Map<Integer, Member> members = DataHandler.loadMembers();

    public static void issueBook() {
        JTextField memberIdField = new JTextField();
        JTextField bookIdField = new JTextField();
        JTextField returnDateField = new JTextField();

        Object[] fields = {
                "Member ID:", memberIdField,
                "Book ID:", bookIdField,
                "Return Date (YYYY-MM-DD):", returnDateField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Issue Book", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int memberId = Integer.parseInt(memberIdField.getText().trim());
                int bookId = Integer.parseInt(bookIdField.getText().trim());
                String returnDateStr = returnDateField.getText().trim();

                if (!members.containsKey(memberId)) {
                    JOptionPane.showMessageDialog(null, "Member ID not found!");
                    return;
                }

                if (!books.containsKey(bookId)) {
                    JOptionPane.showMessageDialog(null, "Book ID not found!");
                    return;
                }

                long issuedCount = lendingRecords.values().stream()
                        .filter(record -> record.getMemberId() == memberId)
                        .count();

                if (issuedCount >= 4) {
                    JOptionPane.showMessageDialog(null, "Member has reached the borrowing limit (4 books)!");
                    return;
                }

                lendingRecords.put(bookId, new LendingRecord(bookId, memberId, returnDateStr));
                DataHandler.saveLendingRecords(lendingRecords);
                JOptionPane.showMessageDialog(null, "Book issued successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input!");
            }
        }
    }

    public static void collectReturnBook() {
        String bookIdStr = JOptionPane.showInputDialog("Enter Book ID to Return:");
        if (bookIdStr != null) {
            try {
                int bookId = Integer.parseInt(bookIdStr.trim());

                if (!lendingRecords.containsKey(bookId)) {
                    JOptionPane.showMessageDialog(null, "Book ID not found in lending records!");
                    return;
                }

                lendingRecords.remove(bookId);
                DataHandler.saveLendingRecords(lendingRecords);
                JOptionPane.showMessageDialog(null, "Book returned successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid Book ID!");
            }
        }
    }

    public static void viewIssuedBooks() {
        String memberIdStr = JOptionPane.showInputDialog("Enter Member ID to View Issued Books:");
        if (memberIdStr != null) {
            try {
                int memberId = Integer.parseInt(memberIdStr.trim());

                if (!members.containsKey(memberId)) {
                    JOptionPane.showMessageDialog(null, "Member ID not found!");
                    return;
                }

                StringBuilder issuedBooks = new StringBuilder("Issued Books:\n");
                lendingRecords.values().stream()
                        .filter(record -> record.getMemberId() == memberId)
                        .forEach(record -> issuedBooks.append(books.get(record.getBookId()).toString()).append("\n"));

                JOptionPane.showMessageDialog(null, issuedBooks.toString());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid Member ID!");
            }
        }
    }

    public static void viewOverdueBooks() {
        StringBuilder overdueBooks = new StringBuilder("Overdue Books:\n");
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        lendingRecords.values().stream()
                .filter(record -> LocalDate.parse(record.getReturnDate(), formatter).isBefore(today))
                .forEach(record -> overdueBooks.append(books.get(record.getBookId()).toString()).append("\n"));

        JOptionPane.showMessageDialog(null, overdueBooks.toString());
    }
}
