import javax.swing.*;
import java.util.Map;

public class MemberOperations {
    private static Map<Integer, Member> members = DataHandler.loadMembers();

    public static void addMember() {
        JTextField memberIdField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField addressField = new JTextField();

        Object[] fields = {
                "Member ID:", memberIdField,
                "First Name:", firstNameField,
                "Last Name:", lastNameField,
                "Phone Number:", phoneField,
                "Address (e.g., House No, Street, City):", addressField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Add Member", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                int memberId = Integer.parseInt(memberIdField.getText().trim());
                String firstName = firstNameField.getText().trim();
                String lastName = lastNameField.getText().trim();
                String phone = phoneField.getText().trim();
                String address = addressField.getText().trim();

                if (members.containsKey(memberId)) {
                    JOptionPane.showMessageDialog(null, "Member ID already exists!");
                    return;
                }

                members.put(memberId, new Member(memberId, firstName, lastName, phone, address));
                DataHandler.saveMembers(members);
                JOptionPane.showMessageDialog(null, "Member added successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid Member ID!");
            }
        }
    }
}
