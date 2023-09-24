package com.company;

import CalendarApp.App.CalendarApp;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

public class LoginPage implements ActionListener{

    JFrame Layout = new JFrame();
    JButton AlphaLoginButton = new JButton("Login");
    JButton BetaResetButton = new JButton("Reset");
    JTextField UsernameTextBox = new JTextField();
    JPasswordField PasswordTextBox = new JPasswordField();
    JLabel UserLabel = new JLabel("User ID:");
    JLabel PasswordLabel = new JLabel("Password:");
    JLabel Csi2999 = new JLabel("CSI 2999 Project");
    JLabel TeamName = new JLabel("Team Name : ");
    JLabel NightKnight = new JLabel("Night Knights");
    JLabel TeamLeader = new JLabel("Team Leader:");
    JLabel EW = new JLabel("Eric Wong");
    JLabel Members = new JLabel("Group Members:");
    JLabel Group = new JLabel("Travis Bauman,Mamadou Jallow, Cedric Rivera");
    JLabel Title = new JLabel("Agenda Assassin");
    JLabel MessageLabel = new JLabel();


    //ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Eric\\ECL.jpg");
    //ImageIcon image1 = new ImageIcon("src/ninja.png");
    ImageIcon image1 = new ImageIcon(getClass().getClassLoader().getResource("ninja.png"));
    JLabel picture = new JLabel(image1);
    Border border = BorderFactory.createLineBorder( Color.BLACK,1);
    Border border2 = BorderFactory.createLineBorder(Color.red,2);

    JPanel Panel1 = new JPanel();
//    Image enemy;
//    Image backgroundImage;

//    Timer timer;
//    int xVelocity = 3;
//    int yVelocity = 3;
//    int x = 0;
//    int y = 0;


    HashMap<String,String> logininfo = new HashMap<String,String>();

    public LoginPage(HashMap<String, String> loginInfoOriginal){

        logininfo = loginInfoOriginal;



//        enemy = new ImageIcon("C:\\Users\\Eric\\Bee.jpg").getImage();
//        backgroundImage = new ImageIcon("C:\\Users\\Eric\\ECL.jpg").getImage();
        //Image ninja = new ImageIcon("src/ninja.png").getImage();

        ImageIcon ninja = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ninja.png")));
//
//
//        timer = new Timer(10, this);
//        timer.start();
        Panel1.setBackground(Color.white);
        Panel1.setBounds(0,0,600,750);
        Panel1.setLayout(new BorderLayout());
        Panel1.add(picture);
        //picture.setBounds(300,800,50,50);

        Title.setBounds(80,45,500,80);
        Title.setForeground(Color.red);
        Title.setFont(new Font("PMingLiU-ExtB",Font.ITALIC,70));

        Csi2999.setBounds(610,00,500,50);
        Csi2999.setForeground(Color.red);
        Csi2999.setFont(new Font("Comic Sans MS",Font.BOLD,30));

        TeamName.setBounds(630,45,500,30);
        TeamName.setFont(new Font("Comic Sans MS",Font.ITALIC,20));
        TeamName.setForeground(Color.red);

        NightKnight.setBounds(760,45,500,30);
        NightKnight.setFont(new Font("Comic Sans MS",Font.ITALIC,20));
        NightKnight.setForeground(Color.white);

        TeamLeader.setBounds(630,75,500,30);
        TeamLeader.setFont(new Font("Comic Sans MS",Font.ITALIC,20));
        TeamLeader.setForeground(Color.red);

        EW.setBounds(765,75,500,30);
        EW.setFont(new Font("Comic Sans MS",Font.ITALIC,20));
        EW.setForeground(Color.white);

        Members.setBounds(630,105,700,30);
        Members.setFont(new Font("Comic Sans MS",Font.ITALIC,20));
        Members.setForeground(Color.red);

        Group.setBounds(790,105,700,30);
        Group.setFont(new Font("Comic Sans MS",Font.ITALIC,20));
        Group.setForeground(Color.white);


        UserLabel.setBounds(650,200,500,100);
        UserLabel.setForeground(Color.red);
        UserLabel.setFont(new Font("PMingLiU-ExtB",Font.PLAIN,40));

        PasswordLabel.setBounds(650,300,500,100);
        PasswordLabel.setForeground(Color.red);
        PasswordLabel.setFont(new Font("PMingLiU-ExtB",Font.PLAIN,40));


        MessageLabel.setBounds(725,500,500,100);
        MessageLabel.setFont(new Font("PMingLiU-ExtB",Font.ITALIC,40));


        UsernameTextBox.setBounds(850,225,300,45);
        UsernameTextBox.setFont(new Font("PMingLiU-ExtB",Font.PLAIN,40));
        UsernameTextBox.setForeground(Color.red);
        UsernameTextBox.setBorder(border);


        PasswordTextBox.setBounds(850,325,300,45);
        PasswordTextBox.setFont(new Font("PMingLiU-ExtB",Font.PLAIN,40));
        PasswordTextBox.setForeground(Color.red);
        PasswordTextBox.setBorder(border);

        AlphaLoginButton.setBounds(675,425,200,50);
        AlphaLoginButton.setFocusable(false);
        AlphaLoginButton.addActionListener(this);
        AlphaLoginButton.setBackground(Color.red);
        AlphaLoginButton.setFont(new Font("PMingLiU-ExtB",Font.BOLD,35));
        AlphaLoginButton.setForeground(Color.WHITE);
        AlphaLoginButton.setBorder(border2);

        BetaResetButton.setBounds(925,425,200,50);
        BetaResetButton.setFocusable(false);
        BetaResetButton.addActionListener(this);
        BetaResetButton.setBackground(Color.red);
        BetaResetButton.setFont(new Font("PMingLiU-ExtB",Font.BOLD,35));
        BetaResetButton.setForeground(Color.WHITE);
        BetaResetButton.setBorder(border2);

        Layout.add(Title);
        Layout.add(Csi2999);
        Layout.add(TeamName);
        Layout.add(NightKnight);
        Layout.add(TeamLeader);
        Layout.add(EW);
        Layout.add(Members);
        Layout.add(Group);
        Layout.add(UserLabel);
        Layout.add(PasswordLabel);
        Layout.add(MessageLabel);
        Layout.add(UsernameTextBox);
        Layout.add(PasswordTextBox);
        Layout.add(AlphaLoginButton);
        Layout.add(BetaResetButton);
        Layout.setLocation(70,50);
        Layout.add(Panel1);
        Layout.setIconImage(ninja.getImage());

        Layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Layout.setSize(1250,750);
        Layout.setTitle("CSI 2999 Project Team Night Knights");
        Layout.getContentPane().setBackground(new Color (0,0,0));
        Layout.setLayout(null);
        Layout.setVisible(true);




    }

//    public void paint(Graphics g) {
//        Graphics2D g2D = (Graphics2D) g;
//
//
//        g2D.drawImage(backgroundImage, 0, 0, null);
//        g2D.drawImage(enemy, x, y, null);
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()== BetaResetButton) {
            UsernameTextBox.setText("");
            PasswordTextBox.setText("");
        }

        if(e.getSource()== AlphaLoginButton) {
            String userID = UsernameTextBox.getText();
            String password = String.valueOf(PasswordTextBox.getPassword());

            if(logininfo.containsKey(userID)) {
                if(logininfo.get(userID).equals(password)) {
                    MessageLabel.setForeground(Color.green);
                    MessageLabel.setText("Login successful");
                    Layout.dispose();
                    CalendarApp calendarApp = new CalendarApp(userID);
                    //WelcomePage welcomePage = new WelcomePage(userID);
                }
                else {
                    MessageLabel.setForeground(Color.red);
                    MessageLabel.setText("Wrong password");
                }

            }
            else {
                MessageLabel.setForeground(Color.red);
                MessageLabel.setText("username not found");
            }
        }


    }
}