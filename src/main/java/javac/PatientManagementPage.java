package javac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class PatientManagementPage {

    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField addressField;
    private JTextField searchField;
    private JTextArea patientListArea;
    private ArrayList<String> patients;
    private int patientIdCounter = 1;

    public PatientManagementPage() {
        patients = new ArrayList<>();
        initialize();
        loadPatientsFromFile();
    }

    private void initialize() {
        frame = new JFrame("Patient Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 500);

        panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(10, 20, 80, 25);
        panel.add(lblName);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 165, 25);
        panel.add(nameField);
        nameField.setColumns(10);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setBounds(10, 60, 80, 25);
        panel.add(lblAge);

        ageField = new JTextField();
        ageField.setBounds(100, 60, 165, 25);
        panel.add(ageField);
        ageField.setColumns(10);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(10, 100, 80, 25);
        panel.add(lblAddress);

        addressField = new JTextField();
        addressField.setBounds(100, 100, 165, 25);
        panel.add(addressField);
        addressField.setColumns(10);

        JButton addButton = new JButton("Add Patient");
        addButton.setBounds(10, 140, 150, 25);
        panel.add(addButton);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(10, 180, 150, 25);
        panel.add(searchButton);

        JButton deleteButton = new JButton("Delete Patient");
        deleteButton.setBounds(180, 140, 150, 25);
        panel.add(deleteButton);

        JButton updateButton = new JButton("Update Patient");
        updateButton.setBounds(180, 180, 150, 25);
        panel.add(updateButton);

        JLabel lblSearch = new JLabel("Search by Name/ID:");
        lblSearch.setBounds(10, 220, 150, 25);
        panel.add(lblSearch);

        searchField = new JTextField();
        searchField.setBounds(160, 220, 165, 25);
        panel.add(searchField);
        searchField.setColumns(10);

        patientListArea = new JTextArea();
        patientListArea.setBounds(10, 260, 560, 170);
        panel.add(patientListArea);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addPatient();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchPatient();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletePatient();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePatient();
            }
        });

        frame.setVisible(true);
    }

    private void addPatient() {
        String name = nameField.getText();
        String age = ageField.getText();
        String address = addressField.getText();

        if (!name.isEmpty() && !age.isEmpty() && !address.isEmpty()) {
            String patientInfo = "ID: " + patientIdCounter + ", Name: " + name + ", Age: " + age + ", Address: " + address;
            patients.add(patientInfo);
            patientIdCounter++;
            updatePatientList();
            clearFields();
            savePatientsToFile();
        } else {
            JOptionPane.showMessageDialog(frame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchPatient() {
        String searchTerm = searchField.getText().toLowerCase();
        StringBuilder searchResults = new StringBuilder();

        for (String patient : patients) {
            if (patient.toLowerCase().contains(searchTerm)) {
                searchResults.append(patient).append("\n");
            }
        }

        if (searchResults.length() == 0) {
            patientListArea.setText("No patients found.");
        } else {
            patientListArea.setText(searchResults.toString());
        }
    }

    private void deletePatient() {
        String searchTerm = searchField.getText().toLowerCase();
        boolean patientFound = false;

        for (int i = 0; i < patients.size(); i++) {
            String patient = patients.get(i);
            if (patient.toLowerCase().contains(searchTerm)) {
                patients.remove(i);
                patientFound = true;
                break;
            }
        }

        if (patientFound) {
            updatePatientList();
            savePatientsToFile();
        } else {
            JOptionPane.showMessageDialog(frame, "No matching patient found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePatient() {
        String searchTerm = searchField.getText().toLowerCase();
        boolean patientFound = false;

        for (int i = 0; i < patients.size(); i++) {
            String patient = patients.get(i);
            if (patient.toLowerCase().contains(searchTerm)) {
                String name = nameField.getText();
                String age = ageField.getText();
                String address = addressField.getText();

                if (!name.isEmpty() && !age.isEmpty() && !address.isEmpty()) {
                    String patientId = patient.split(",")[0]; // Extract the ID
                    String updatedPatientInfo = patientId + ", Name: " + name + ", Age: " + age + ", Address: " + address;
                    patients.set(i, updatedPatientInfo);
                    patientFound = true;
                    break;
                } else {
                    JOptionPane.showMessageDialog(frame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

        if (patientFound) {
            updatePatientList();
            savePatientsToFile();
        } else {
            JOptionPane.showMessageDialog(frame, "No matching patient found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePatientList() {
        StringBuilder listContent = new StringBuilder();
        for (String patient : patients) {
            listContent.append(patient).append("\n");
        }
        patientListArea.setText(listContent.toString());
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        addressField.setText("");
        searchField.setText("");
    }

    private void savePatientsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("patients.txt"))) {
            for (String patient : patients) {
                writer.println(patient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPatientsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("patients.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                patients.add(line);
                String[] parts = line.split(", ");
                int id = Integer.parseInt(parts[0].split(": ")[1]);
                if (id >= patientIdCounter) {
                    patientIdCounter = id + 1;
                }
            }
            updatePatientList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PatientManagementPage window = new PatientManagementPage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
