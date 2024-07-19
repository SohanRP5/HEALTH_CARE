package javac;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HealthcareSystem {
    public static void main(String[] args) {


        // Create the login frame
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 250);

        // Create components for the login frame
        JPanel panel = new GradientPanel();
        loginFrame.add(panel);
        placeLoginComponents(panel, loginFrame);

        // Set frame visibility
        loginFrame.setVisible(true);
    }

    private static void placeLoginComponents(JPanel panel, JFrame loginFrame) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(50, 50, 80, 25);
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(150, 50, 165, 25);
        userText.setBackground(new Color(43, 43, 43));
        userText.setForeground(Color.WHITE);
        userText.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 90, 80, 25);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(150, 90, 165, 25);
        passwordText.setBackground(new Color(43, 43, 43));
        passwordText.setForeground(Color.WHITE);
        passwordText.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 130, 165, 35);
        loginButton.setBackground(new Color(28, 162, 243));  // Custom blue color
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(loginButton);

        // Add hover effect for the login button
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(21, 123, 184));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(28, 162, 243));
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String password = new String(passwordText.getPassword());

                // Simple authentication check
                if (user.equals("admin") && password.equals("admin")) {
                    // Close the login frame
                    loginFrame.dispose();

                    // Open the healthcare system page
                    showHealthcareSystemPage();
                } else {
                    JOptionPane.showMessageDialog(panel, "Invalid login credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private static void showHealthcareSystemPage() {
        JFrame healthcareFrame = new JFrame("Healthcare System");
        healthcareFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        healthcareFrame.setSize(800, 600);

        JPanel panel = new JPanel(new BorderLayout());
        healthcareFrame.add(panel);

        // Create the blue strip panel
        JPanel blueStripPanel = new JPanel();
        blueStripPanel.setBackground(new Color(28, 162, 243));  // Blue color for the strip
        blueStripPanel.setPreferredSize(new Dimension(800, 50));  // Set the height of the strip

        // Add text to the blue strip panel
        JLabel blueStripLabel = new JLabel("Healthcare System Management");
        blueStripLabel.setForeground(Color.WHITE);
        blueStripLabel.setFont(new Font("Arial", Font.BOLD, 20));
        blueStripLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));  // Add padding to the left
        blueStripLabel.setVerticalAlignment(JLabel.CENTER);

        // Add subtle shadow to the text
        blueStripLabel.setForeground(new Color(255, 255, 255));
        blueStripLabel.setOpaque(false);

        blueStripPanel.setLayout(new BorderLayout());
        blueStripPanel.add(blueStripLabel, BorderLayout.WEST);

        // Add the blue strip panel to the main panel
        panel.add(blueStripPanel, BorderLayout.NORTH);

        // Add the rest of the components
        placeHealthcareComponents(panel);

        healthcareFrame.setVisible(true);
    }


    private static void placeHealthcareComponents(JPanel panel) {
        // Create the tabbed pane for different sections
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels for each tab
        JPanel patientManagementPanel = new JPanel();
        patientManagementPanel.setBackground(new Color(60, 63, 65));
        JPanel appointmentSchedulingPanel = new JPanel();
        appointmentSchedulingPanel.setBackground(new Color(60, 63, 65));
        JPanel medicalRecordsPanel = new JPanel();
        medicalRecordsPanel.setBackground(new Color(60, 63, 65));

        // Add some example content to each panel
        JButton openPatientManagementButton = new JButton("Open Patient Management");
        openPatientManagementButton.setBackground(new Color(28, 162, 243));
        openPatientManagementButton.setForeground(Color.WHITE);
        openPatientManagementButton.setFont(new Font("Arial", Font.BOLD, 14));
        openPatientManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PatientManagementPage();
            }
        });
        patientManagementPanel.add(openPatientManagementButton);

        // Add hover effect for the button
        openPatientManagementButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                openPatientManagementButton.setBackground(new Color(21, 123, 184));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                openPatientManagementButton.setBackground(new Color(28, 162, 243));
            }
        });

        JButton openapp = new JButton("Open Appointment Scheduling");
        openapp.setBackground(new Color(28, 162, 243));
        openapp.setForeground(Color.WHITE);
        openapp.setFont(new Font("Arial", Font.BOLD, 14));
        openapp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DoctorAppointmentScheduler();
            }
        });
        appointmentSchedulingPanel.add(openapp);

        // Add hover effect for the button
        openapp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                openapp.setBackground(new Color(21, 123, 184));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                openapp.setBackground(new Color(28, 162, 243));
            }
        });

        // Add a new button to open the Hospital and Doctors List page
        JButton openHospitalDoctorAppButton = new JButton("Open Hospital and Doctors List");
        openHospitalDoctorAppButton.setBackground(new Color(28, 162, 243));
        openHospitalDoctorAppButton.setForeground(Color.WHITE);
        openHospitalDoctorAppButton.setFont(new Font("Arial", Font.BOLD, 14));
        openHospitalDoctorAppButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HospitalDoctorApp.main(null);
            }
        });
        medicalRecordsPanel.add(openHospitalDoctorAppButton);

        // Add hover effect for the button
        openHospitalDoctorAppButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                openHospitalDoctorAppButton.setBackground(new Color(21, 123, 184));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                openHospitalDoctorAppButton.setBackground(new Color(28, 162, 243));
            }
        });



        // Add panels to the tabbed pane
        tabbedPane.addTab("Patient Management", patientManagementPanel);
        tabbedPane.addTab("Appointment Scheduling", appointmentSchedulingPanel);
        tabbedPane.addTab("Find your doctor", medicalRecordsPanel);

        // Add the tabbed pane to the main panel
        panel.add(tabbedPane, BorderLayout.CENTER);
    }


    static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            Color color1 = new Color(60, 63, 65);
            Color color2 = new Color(28, 162, 243);

            GradientPaint gp = new GradientPaint(0, 0, color1, width, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }
}
