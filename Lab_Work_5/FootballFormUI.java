package com.iitdu.ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.iitdu.util.FormToFile;
import java.io.File;

public class FootballFormUI {
    private JFrame frame;
    private JTextField nameField, phoneField, emailField, addressField, dobField;
    private JComboBox<String> genderBox, degreeBox, positionBox;
    private JCheckBox interDeptCheck;
    private JTextArea experienceArea;
    private JLabel pictureLabel;
    private String picturePath = "";

    public FootballFormUI() {
        frame = new JFrame("IITDU Football League Registration");
        frame.setSize(500, 700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 20, 100, 25);
        frame.add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(150, 20, 300, 25);
        frame.add(nameField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(30, 60, 100, 25);
        frame.add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBounds(150, 60, 300, 25);
        frame.add(phoneField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 100, 100, 25);
        frame.add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(150, 100, 300, 25);
        frame.add(emailField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(30, 140, 100, 25);
        frame.add(addressLabel);
        addressField = new JTextField();
        addressField.setBounds(150, 140, 300, 25);
        frame.add(addressField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(30, 180, 100, 25);
        frame.add(genderLabel);
        genderBox = new JComboBox<>(new String[]{"Select", "Male", "Female", "Other"});
        genderBox.setBounds(150, 180, 300, 25);
        frame.add(genderBox);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(30, 220, 100, 25);
        frame.add(dobLabel);
        dobField = new JTextField("YYYY-MM-DD");
        dobField.setBounds(150, 220, 300, 25);
        frame.add(dobField);

        JLabel degreeLabel = new JLabel("Degree:");
        degreeLabel.setBounds(30, 260, 100, 25);
        frame.add(degreeLabel);
        degreeBox = new JComboBox<>(new String[]{"Select", "BSc in CS", "MSc in CS", "PhD in CS"});
        degreeBox.setBounds(150, 260, 300, 25);
        frame.add(degreeBox);

        JLabel pictureText = new JLabel("Picture:");
        pictureText.setBounds(30, 300, 100, 25);
        frame.add(pictureText);
        JButton pictureButton = new JButton("Choose File");
        pictureButton.setBounds(150, 300, 150, 25);
        frame.add(pictureButton);

        pictureLabel = new JLabel("No file chosen");
        pictureLabel.setBounds(310, 300, 200, 25);
        frame.add(pictureLabel);

        pictureButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                picturePath = selectedFile.getAbsolutePath();
                pictureLabel.setText(selectedFile.getName());
            }
        });

        JLabel positionLabel = new JLabel("Preferred Position:");
        positionLabel.setBounds(30, 340, 120, 25);
        frame.add(positionLabel);
        positionBox = new JComboBox<>(new String[]{"Select", "Goalkeeper", "Defender", "Midfielder", "Forward"});
        positionBox.setBounds(150, 340, 300, 25);
        frame.add(positionBox);

        interDeptCheck = new JCheckBox("Played Inter-departmental Competition");
        interDeptCheck.setBounds(150, 380, 300, 25);
        frame.add(interDeptCheck);

        JLabel expLabel = new JLabel("Football Experience:");
        expLabel.setBounds(30, 420, 120, 25);
        frame.add(expLabel);
        experienceArea = new JTextArea();
        experienceArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(experienceArea);
        scrollPane.setBounds(150, 420, 300, 100);
        frame.add(scrollPane);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(180, 550, 120, 40);
        frame.add(submitButton);

        submitButton.addActionListener(e -> handleSubmit());

        frame.setVisible(true);
    }

    private void handleSubmit() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        String address = addressField.getText().trim();
        String gender = (String) genderBox.getSelectedItem();
        String dob = dobField.getText().trim();
        String degree = (String) degreeBox.getSelectedItem();
        String position = (String) positionBox.getSelectedItem();
        String experience = experienceArea.getText().trim();
        boolean playedInterDept = interDeptCheck.isSelected();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty() ||
                gender.equals("Select") || dob.isEmpty() || degree.equals("Select") || position.equals("Select") ||
                experience.isEmpty() || picturePath.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields!", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String data = String.format(
                "Name: %s\nPhone: %s\nEmail: %s\nAddress: %s\nGender: %s\nDOB: %s\nDegree: %s\nPicture: %s\n" +
                        "Preferred Position: %s\nPlayed Inter-Dept: %s\nExperience: %s\n----------------------\n",
                name, phone, email, address, gender, dob, degree, picturePath,
                position, playedInterDept ? "Yes" : "No", experience
        );

        boolean saved = FormToFile.saveData(data);

        if (saved) {
            JOptionPane.showMessageDialog(frame, "Registration successful!");
            frame.dispose();  // Close form after success
        } else {
            JOptionPane.showMessageDialog(frame, "Error saving data. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new FootballFormUI();
    }
}
