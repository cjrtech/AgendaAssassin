package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.util.Objects.isNull;

public class StartRegistration {

    JFrame Layout = new JFrame();
    JButton NewUserButton = new JButton("Create User");
    JTextField NewUserIDField = new JTextField();
    JPasswordField NewUserPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("New User:");
    JLabel userPasswordLabel = new JLabel("New Password:");
    JLabel messageLabel = new JLabel();
    String[] usersArr = new String[50];
    String[] passArr = new String[50];
    String userprofile = System.getenv("USERPROFILE");
    String DataFile = userprofile + "/App/login.csv";
    JComboBox comboUsers = new JComboBox();
    private JButton RemoveUserButton = new JButton("Remove User");

    public StartRegistration(){
        ImageIcon ninja = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ninja.png")));
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(DataFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.useDelimiter("¬");
        int i5 = 0;
        while(scanner.hasNext()){
            usersArr[i5++] = scanner.next();
            passArr[i5++]= scanner.next();
        }

        ArrayList<String> usersArrList = new ArrayList<>();
        for(String s2 : usersArr) {
            if(s2 != null && s2.length() > 0) {
                usersArrList.add(s2);
            }
        }
        ArrayList<String> passArrList = new ArrayList<>();
        for(String s2 : passArr) {
            if(s2 != null && s2.length() > 0) {
                passArrList.add(s2);
            }
        }

        comboUsers.setModel(new DefaultComboBoxModel(usersArrList.toArray()));
            userIDLabel.setBounds(30,100,95,25);
            userPasswordLabel.setBounds(30,150,95,25);

            comboUsers.setBounds(140,300,250,35);
            RemoveUserButton.setBounds(400,300,250,35);

            messageLabel.setBounds(140,250,250,35);
            messageLabel.setFont(new Font(null,Font.ITALIC,25));

            NewUserIDField.setBounds(140,100,200,25);
            NewUserPasswordField.setBounds(140,150,200,25);

            NewUserButton.setBounds(140,200,150,25);
            NewUserButton.setFocusable(false);

            Layout.setAlwaysOnTop(true);
            Layout.add(userIDLabel);
            Layout.add(userPasswordLabel);
            Layout.add(messageLabel);
            Layout.add(NewUserIDField);
            Layout.add(NewUserPasswordField);
            Layout.add(NewUserButton);
            Layout.add(comboUsers);
            Layout.add(RemoveUserButton);
            Layout.setIconImage(ninja.getImage());
            Layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Layout.setSize(900,600);
            Layout.setLayout(null);
            Layout.setVisible(true);

            RemoveUserButton.addActionListener(e -> {
                ArrayList<String> newUsersArr = new ArrayList<>();
                int i6 = usersArrList.size();
                for(int i3 = 0; i3 < i6; i3++){
                    if (usersArrList.get(i3).equals((String) comboUsers.getSelectedItem())) {
                        JOptionPane.showMessageDialog(null, (String) comboUsers.getSelectedItem()+" Removed");
                    } else {
                        newUsersArr.add(usersArrList.get(i3));
                        newUsersArr.add(passArrList.get(i3));
                    }
                }

                FileWriter tofile = null;
                try {
                    tofile = new FileWriter(DataFile);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                int len = (newUsersArr.size());
                for (int i2 = 0; i2 < len; i2++){
                    try {
                        if(isNull(newUsersArr.get(i2))) {

                        }else{
                            tofile.write(newUsersArr.get(i2) + "¬");
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                try {
                    tofile.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                IDandPasswords idandPasswords = new IDandPasswords();
                new LoginPage(idandPasswords.getLoginInfo());
                Layout.dispose();
            });

            NewUserButton.addActionListener(e -> {
                if (e.getSource() == NewUserButton) {
                    String userID = NewUserIDField.getText();
                    String password = String.valueOf(NewUserPasswordField.getPassword());

                    if (userID != "") {
                        FileWriter tofile = null;
                        try {
                            tofile = new FileWriter(DataFile,true);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        try{
                            tofile.write(userID+"¬"+password+"¬");
                        }
                        catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        try {
                            tofile.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        IDandPasswords idandPasswords = new IDandPasswords();
                        new LoginPage(idandPasswords.getLoginInfo());
                        Layout.dispose();
                        //JOptionPane.showMessageDialog(null,"Please Restart App to use the new user");
                    } else {
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("Please Enter User");
                    }
                }
            });
    }
}