package javac;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HealthCampRegistration {
    public static void main(String[] args) {
        // Create the registration frame
        JFrame registrationFrame = new JFrame("Health Camp Registration");
        registrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrationFrame.setSize(400, 300);

        // Create components for the registration frame
        JPanel panel = new JPanel(null); // Use null layout for absolute positioning
        registrationFrame.add(panel);
        placeRegistrationComponents(panel, registrationFrame);

        // Center the registration frame on the screen
        registrationFrame.setLocationRelativeTo(null);

        // Set frame visibility
        registrationFrame.setVisible(true);
    }

    private static void placeRegistrationComponents(JPanel panel, JFrame registrationFrame) {
        // Labels and Text Fields
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 30, 80, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(200);
        nameText.setBounds(150, 30, 165, 25);
        panel.add(nameText);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 70, 80, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(200);
        emailText.setBounds(150, 70, 165, 25);
        panel.add(emailText);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 110, 80, 25);
        panel.add(phoneLabel);

        JTextField phoneText = new JTextField(200);
        phoneText.setBounds(150, 110, 165, 25);
        panel.add(phoneText);

        // Checkbox for T-shirt size
        JLabel tshirtLabel = new JLabel("T-shirt Size:");
        tshirtLabel.setBounds(50, 150, 80, 25);
        panel.add(tshirtLabel);

        String[] sizes = {"S", "M", "L", "XL"};
        JComboBox<String> tshirtCombo = new JComboBox<>(sizes);
        tshirtCombo.setBounds(150, 150, 80, 25);
        panel.add(tshirtCombo);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 190, 80, 30);
        panel.add(submitButton);

        // Submit action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String email = emailText.getText();
                String phone = phoneText.getText();
                String tshirtSize = (String) tshirtCombo.getSelectedItem();

                // Simple validation (you can add more complex validation as needed)
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Simulate registration submission (replace with actual logic)
                    JOptionPane.showMessageDialog(panel, "Registration Successful:\nName: " + name +
                            "\nEmail: " + email + "\nPhone: " + phone + "\nT-shirt Size: " + tshirtSize);

                    // Clear fields after submission (optional)
                    nameText.setText("");
                    emailText.setText("");
                    phoneText.setText("");
                    tshirtCombo.setSelectedIndex(0);
                }
            }
        });
    }
}
