import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class AssistantMenu {
    public static void show() {
        // Create the Assistant Menu Frame
        JFrame frame = new JFrame("Assistant Menu");
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Header with Gradient Background
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(255, 165, 0); // Orange
                Color color2 = new Color(255, 140, 0); // Dark Orange
                GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(500, 80));

        JLabel title = new JLabel("Assistant Menu", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        headerPanel.add(title);

        // Button panel with GridBagLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20); // Padding

        JButton issueBookButton = createStyledButton("Issue Book");
        JButton viewIssuedBooksButton = createStyledButton("View Issued Books");
        JButton collectReturnBookButton = createStyledButton("Collect Returned Books");
        JButton backButton = createStyledButton("Back");

        // Add action listeners to buttons
        issueBookButton.addActionListener(e -> LendingOperations.issueBook());
        viewIssuedBooksButton.addActionListener(e -> showIssuedBooks());
        collectReturnBookButton.addActionListener(e -> LendingOperations.collectReturnBook());
        
        // Add ActionListener for the Back Button to return to the main menu
        backButton.addActionListener(e -> {
            frame.dispose(); // Close the Assistant Menu
            LibraryManagementSystem.showMainMenu(); // Show the Library Management System main menu
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(issueBookButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(viewIssuedBooksButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(collectReturnBookButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        buttonPanel.add(backButton, gbc); // Add the back button at the bottom

        // Add panels to frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Center the frame
        FrameUtils.centerFrame(frame);
        frame.setVisible(true);
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(255, 165, 0)); // Orange
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(300, 60)); // Set button size to fit text
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(false); // Transparent background for better effect
        button.setOpaque(true); // Make it opaque for color visibility

        // Rounded corners and hover effect
        button.setBorder(BorderFactory.createLineBorder(new Color(255, 140, 0), 2, true));
        button.setFocusPainted(false);

        button.addActionListener(e -> button.setBackground(new Color(255, 140, 0))); // Click effect

        return button;
    }

    private static void showIssuedBooks() {
        // Get data for issued books
        Map<Integer, LendingRecord> lendingRecords = DataHandler.loadLendingRecords();
        Map<Integer, Book> books = DataHandler.loadBooks();
        Map<Integer, Member> members = DataHandler.loadMembers();

        // Create column names and data
        String[] columns = {"Member ID", "Book Name", "Book ID", "Return Date", "Member Phone"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        // Loop through lending records and populate the table data
        for (LendingRecord record : lendingRecords.values()) {
            int bookId = record.getBookId();
            int memberId = record.getMemberId();
            Book book = books.get(bookId);
            Member member = members.get(memberId);

            Object[] row = {
                    memberId,
                    book.getTitle(),
                    bookId,
                    record.getReturnDate(),
                    member.getPhone()
            };

            tableModel.addRow(row);
        }

        // Create the table with the data
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true); // Enable sorting by column

        // Customize table appearance
        table.setRowHeight(30); // Set row height
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16)); // Set header font
        table.getTableHeader().setBackground(new Color(70, 130, 180)); // Steel blue background for header
        table.getTableHeader().setForeground(Color.WHITE); // Header text color
        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for table rows

        // Alternating row colors for better readability
        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                          boolean isSelected, boolean hasFocus,
                                                          int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(240, 240, 240)); // Light grey background for even rows
                } else {
                    c.setBackground(Color.WHITE); // White background for odd rows
                }
                return c;
            }
        });

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(450, 250)); // Increase size for proper text display

        // Create a panel to center the table in the window
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Display the table in a new window with styling
        JFrame tableFrame = new JFrame("Issued Books");
        tableFrame.setSize(600, 400);
        tableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Header with Gradient Background for table window
        JPanel tableHeaderPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(70, 130, 180); // Steel blue
                Color color2 = new Color(100, 149, 237); // Cornflower blue
                GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        tableHeaderPanel.setPreferredSize(new Dimension(600, 40));
        JLabel tableTitle = new JLabel("Issued Books", SwingConstants.CENTER);
        tableTitle.setFont(new Font("Arial", Font.BOLD, 22));
        tableTitle.setForeground(Color.WHITE);
        tableHeaderPanel.add(tableTitle);

        // Add header and table to frame
        tableFrame.add(tableHeaderPanel, BorderLayout.NORTH);
        tableFrame.add(tablePanel, BorderLayout.CENTER);
        tableFrame.setVisible(true);
    }
}
