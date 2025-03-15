import javax.swing.*;
import java.awt.*;

public class LibrarianMenu {
    public static void show() {
        JFrame frame = new JFrame("Librarian Menu");
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Header with Gradient Background
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(34, 139, 34); // Forest Green
                Color color2 = new Color(50, 205, 50); // Lime Green
                GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(500, 80));

        JLabel title = new JLabel("Librarian Menu", SwingConstants.CENTER);
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

        JButton addBookButton = createStyledButton("Add Book");
        JButton addMemberButton = createStyledButton("Add Member");
        JButton discardBookButton = createStyledButton("Discard Book");
        JButton viewOverdueBooksButton = createStyledButton("View Overdue Books");
        JButton backButton = createStyledButton("Back");

        // Add action listeners to buttons
        addBookButton.addActionListener(e -> BookOperations.addBook());
        addMemberButton.addActionListener(e -> MemberOperations.addMember());
        discardBookButton.addActionListener(e -> BookOperations.discardBook());
        viewOverdueBooksButton.addActionListener(e -> LendingOperations.viewOverdueBooks());
        backButton.addActionListener(e -> frame.dispose());

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(addBookButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(addMemberButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(discardBookButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        buttonPanel.add(viewOverdueBooksButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        buttonPanel.add(backButton, gbc);

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
        button.setBackground(new Color(34, 139, 34)); // Forest Green
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(300, 60)); // Set button size to fit text
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(false); // Transparent background for better effect
        button.setOpaque(true); // Make it opaque for color visibility

        // Rounded corners and hover effect
        button.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 2, true));
        button.setFocusPainted(false);

        button.addActionListener(e -> button.setBackground(new Color(50, 205, 50))); // Click effect

        return button;
    }
}
