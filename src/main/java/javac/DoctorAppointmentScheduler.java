package javac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DoctorAppointmentScheduler extends JFrame {

    private List<Appointment> appointments;

    private JLabel titleLabel, nameLabel, timeLabel, dateLabel, searchLabel;
    private JTextField nameField, timeField, dateField, searchField;
    private JButton addButton, viewButton, cancelButton, searchButton, sortButton;
    private JTextArea displayArea;

    public DoctorAppointmentScheduler() {
        super("Doctor Appointment Scheduler");
        appointments = new ArrayList<>();

        // GUI Components
        titleLabel = new JLabel("Doctor Appointment Scheduler");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        nameLabel = new JLabel("Patient Name:");
        timeLabel = new JLabel("Appointment Time:");
        dateLabel = new JLabel("Appointment Date:");
        searchLabel = new JLabel("Search by Name:");

        nameField = new JTextField(15);
        timeField = new JTextField(15);
        dateField = new JTextField(15);
        searchField = new JTextField(15);

        addButton = new JButton("Add Appointment");
        viewButton = new JButton("View Appointments");
        cancelButton = new JButton("Cancel Appointment");
        searchButton = new JButton("Search Appointment");
        sortButton = new JButton("Sort Appointments");

        displayArea = new JTextArea(15, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Layout setup
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(timeLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(timeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(dateLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(searchLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(searchField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(sortButton);

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createTitledBorder("Appointments"));
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(displayPanel, BorderLayout.EAST);

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAppointment();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAppointments();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelAppointment();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAppointment();
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortAppointments();
            }
        });

        // JFrame setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        add(mainPanel);
        setVisible(true);
    }

    private void addAppointment() {
        String name = nameField.getText();
        String time = timeField.getText();
        String date = dateField.getText();

        if (!name.isEmpty() && !time.isEmpty() && !date.isEmpty()) {
            Appointment appointment = new Appointment(name, time, date);
            appointments.add(appointment);

            displayArea.append("Appointment added: " + appointment + "\n");

            // Clear fields after adding
            nameField.setText("");
            timeField.setText("");
            dateField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewAppointments() {
        displayArea.setText("Appointments:\n");
        for (Appointment appointment : appointments) {
            displayArea.append(appointment + "\n");
        }
    }

    private void cancelAppointment() {
        String name = nameField.getText();
        String time = timeField.getText();
        String date = dateField.getText();

        Appointment appointmentToRemove = new Appointment(name, time, date);
        if (appointments.remove(appointmentToRemove)) {
            displayArea.append("Appointment cancelled: " + appointmentToRemove + "\n");
        } else {
            displayArea.append("Appointment not found: " + appointmentToRemove + "\n");
        }

        // Clear fields after cancelling
        nameField.setText("");
        timeField.setText("");
        dateField.setText("");
    }

    private void searchAppointment() {
        String searchName = searchField.getText().toLowerCase();
        displayArea.setText("Search Results:\n");
        for (Appointment appointment : appointments) {
            if (appointment.getName().toLowerCase().contains(searchName)) {
                displayArea.append(appointment + "\n");
            }
        }
    }

    private void sortAppointments() {
        appointments.sort(Comparator.comparing(Appointment::getDate).thenComparing(Appointment::getTime));
        viewAppointments();
    }

    private class Appointment {
        private String name;
        private String time;
        private String date;

        public Appointment(String name, String time, String date) {
            this.name = name;
            this.time = time;
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public String getTime() {
            return time;
        }

        public String getDate() {
            return date;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Time: " + time + ", Date: " + date;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Appointment that = (Appointment) o;

            if (!name.equals(that.name)) return false;
            if (!time.equals(that.time)) return false;
            return date.equals(that.date);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + time.hashCode();
            result = 31 * result + date.hashCode();
            return result;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DoctorAppointmentScheduler();
            }
        });
    }
}
