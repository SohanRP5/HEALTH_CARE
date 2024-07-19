package javac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class
HospitalDoctorApp {

    // Hospital class definition
    static class Hospital {
        private String name;
        private List<Doctor> doctors;

        public Hospital(String name, List<Doctor> doctors) {
            this.name = name;
            this.doctors = doctors;
        }

        public String getName() {
            return name;
        }

        public List<Doctor> getDoctors() {
            return doctors;
        }

        public void addDoctor(Doctor doctor) {
            doctors.add(doctor);
        }

        public void removeDoctor(Doctor doctor) {
            doctors.remove(doctor);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    // Doctor class definition
    static class Doctor {
        private String name;

        public Doctor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Create a few hospitals and doctors
        List<Doctor> doctors1 = new ArrayList<>();
        doctors1.add(new Doctor("Dr. Smith"));
        doctors1.add(new Doctor("Dr. Johnson"));

        List<Doctor> doctors2 = new ArrayList<>();
        doctors2.add(new Doctor("Dr. Williams"));
        doctors2.add(new Doctor("Dr. Brown"));

        List<Doctor> doctors3 = new ArrayList<>();
        doctors3.add(new Doctor("Dr. Jones"));
        doctors3.add(new Doctor("Dr. Miller"));

        List<Hospital> hospitals = new ArrayList<>();
        hospitals.add(new Hospital("City Hospital", doctors1));
        hospitals.add(new Hospital("County General", doctors2));
        hospitals.add(new Hospital("St. Mary's Hospital", doctors3));

        // Create the frame
        JFrame frame = new JFrame("Hospital and Doctors List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create the list of hospitals
        JList<Hospital> hospitalList = new JList<>(hospitals.toArray(new Hospital[0]));
        JScrollPane hospitalScrollPane = new JScrollPane(hospitalList);

        // Create the list of doctors
        DefaultListModel<Doctor> doctorListModel = new DefaultListModel<>();
        JList<Doctor> doctorList = new JList<>(doctorListModel);
        JScrollPane doctorScrollPane = new JScrollPane(doctorList);

        // Create the search bar
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 30));

        // Create the search button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().toLowerCase();
            doctorListModel.clear();
            Hospital selectedHospital = hospitalList.getSelectedValue();
            if (selectedHospital != null) {
                for (Doctor doctor : selectedHospital.getDoctors()) {
                    if (doctor.getName().toLowerCase().contains(searchText)) {
                        doctorListModel.addElement(doctor);
                    }
                }
            }
        });

        // Create add and remove doctor buttons
        JButton addDoctorButton = new JButton("Add Doctor");
        addDoctorButton.addActionListener(e -> {
            Hospital selectedHospital = hospitalList.getSelectedValue();
            if (selectedHospital != null) {
                String doctorName = JOptionPane.showInputDialog(frame, "Enter doctor's name:");
                if (doctorName != null && !doctorName.trim().isEmpty()) {
                    Doctor newDoctor = new Doctor(doctorName);
                    selectedHospital.addDoctor(newDoctor);
                    doctorListModel.addElement(newDoctor);
                }
            }
        });

        JButton removeDoctorButton = new JButton("Remove Doctor");
        removeDoctorButton.addActionListener(e -> {
            Hospital selectedHospital = hospitalList.getSelectedValue();
            Doctor selectedDoctor = doctorList.getSelectedValue();
            if (selectedHospital != null && selectedDoctor != null) {
                selectedHospital.removeDoctor(selectedDoctor);
                doctorListModel.removeElement(selectedDoctor);
            }
        });

        // Add a listener to the hospital list to update the doctors list and hospital details
        JLabel hospitalDetailsLabel = new JLabel();
        hospitalList.addListSelectionListener(e -> {
            Hospital selectedHospital = hospitalList.getSelectedValue();
            doctorListModel.clear();
            if (selectedHospital != null) {
                hospitalDetailsLabel.setText("Selected Hospital: " + selectedHospital.getName());
                for (Doctor doctor : selectedHospital.getDoctors()) {
                    doctorListModel.addElement(doctor);
                }
            } else {
                hospitalDetailsLabel.setText("No Hospital Selected");
            }
        });

        // Set up the layout
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(hospitalDetailsLabel, BorderLayout.NORTH);
        topPanel.add(hospitalScrollPane, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(doctorScrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Search Doctor:"));
        bottomPanel.add(searchField);
        bottomPanel.add(searchButton);
        bottomPanel.add(addDoctorButton);
        bottomPanel.add(removeDoctorButton);

        frame.add(topPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Show the frame
        frame.setVisible(true);
    }
}
