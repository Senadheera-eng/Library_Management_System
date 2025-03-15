import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showMainMenu());
    }

    static void showMainMenu() {
        JFrame frame = new JFrame("Library Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header with Gradient Background
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(70, 130, 180); 
                Color color2 = new Color(100, 149, 237); 
                GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(500, 80));

        JLabel title = new JLabel("Library Management System", SwingConstants.CENTER);
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

        JButton librarianButton = createStyledButton("Librarian Menu");
        JButton assistantButton = createStyledButton("Assistant Menu");
        JButton exitButton = createStyledButton("Exit");

        // Add action listeners to buttons
        librarianButton.addActionListener(e -> LibrarianMenu.show());
        assistantButton.addActionListener(e -> AssistantMenu.show());
        exitButton.addActionListener(e -> System.exit(0));

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(librarianButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(assistantButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(exitButton, gbc);

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
        button.setBackground(new Color(70, 130, 180)); // Steel blue color
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(300, 60)); // Set button size to fit text
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setContentAreaFilled(false); // Transparent background for better effect
        button.setOpaque(true); // Make it opaque for color visibility

        // Rounded corners and hover effect
        button.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true));
        button.setFocusPainted(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setBackground(new Color(100, 149, 237)); // On click effect
            }
        });

        return button;
    }
}
