package CalendarApp;

import com.company.IDandPasswords;
import com.company.ListGenerator;
import com.company.LoginPage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class App {
    static JFrame frametemp = new JFrame("Class 2999 Calendar");
    private JScrollPane MondayScroll;
    private JScrollPane WedScroll;
    private JScrollPane TuesScroll;
    private JScrollPane ThurScroll;
    private JScrollPane FriScroll;
    private JScrollPane SatScroll;
    private JScrollPane SunScroll;
    private JPanel panelMain;
    private JTextArea textMon;
    private JTextArea textTues;
    private JTextArea textWed;
    private JTextArea textThurs;
    private JTextArea textFri;
    private JTextArea textSat;
    private JTextArea textSun;
    private JButton CalcButton;
    private JButton PreviousPage;
    private JButton listButton;
    private JButton NextPage;
    private JButton MintButton;
    private JButton OceanButton;
    private JButton purpleButton;
    private JButton SunshineButton;
    private JButton BeachColorButton;
    private JButton NinjaButton;
    private JButton GrizzlyButton;
    private JButton logOutButton;
    public JButton StartRegistrationButton;
    private JFormattedTextField wednesdayFormattedTextField;
    private JFormattedTextField thursdayFormattedTextField;
    private JFormattedTextField fridayFormattedTextField;
    private JFormattedTextField saturdayFormattedTextField;
    private JFormattedTextField sundayFormattedTextField;
    private JFormattedTextField tuesdayFormattedTextField;
    private JFormattedTextField mondayFormattedTextField;
    private JComboBox<Object> comboUsers;
    private Boolean Admin = false;
    private Calendar cal = Calendar.getInstance();
    private Calendar calnow = Calendar.getInstance();
    private String stringstartDate;
    public Date startDate;
    public Date calDate;
    String userprofile = System.getenv("USERPROFILE");
    String FolderPath = userprofile + "/App";
    String FileName = userprofile + "/App/login.csv";
    String[] usersArr = new String[20];
    String varMon;
    String varTues;
    String varWed;
    String varThurs;
    String varFri;
    String varSat;
    String varSun;
    String [] TempArr = new String [364];
    String currentUser;
    String StartFile = userprofile + "/App/startdate";
    int Holder = 0;

    public App(String userID) {
        //Set start color
        ColorChange(new Color(147,208,255),new Color(255,243,221),Color.BLACK,new Color(172,247,255));

        //Read file from provided user
        currentUser = userID;
        String DataFile = userprofile + "/App/"+currentUser+".csv";
        for (int i = 0; i < TempArr.length; i++){
            TempArr[i]="";
        }

        //Read users list and set to drop down menu
        Scanner scanner = null;
            try {
                scanner = new Scanner(new File(FileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int i = 1;
            usersArr[0] = "Admin";
            scanner.useDelimiter("¬");
            while(scanner.hasNext()){
                usersArr[i++] = scanner.next();
                String String2 = scanner.next();
                String2 = "";
            }
        ArrayList<String> usersArrList = new ArrayList<>();
        for(String s : usersArr) {
            if(s != null && s.length() > 0) {
                usersArrList.add(s);
            }
        }
        comboUsers.setModel(new DefaultComboBoxModel<>(usersArrList.toArray()));

        inputarray(DataFile);

        //Read in time and find week offset
        importTime(StartFile);
        cal.setTime(startDate);
        calDate = startDate;
        //JOptionPane.showMessageDialog(null,Math.floor(Math.abs(calnow.get(Calendar.DAY_OF_YEAR)-cal.get(Calendar.DAY_OF_YEAR))/7));
        Holder = (int) (7*(Math.floor(Math.abs(calnow.get(Calendar.DAY_OF_YEAR)-cal.get(Calendar.DAY_OF_YEAR))/7)));
        setDays(calDate,Holder);
        ArrayToVariable(Holder);
        SetStuff();

        //Hide fields for non-admin users
        if (userID.equals("Admin")) {
            Admin = true;
            StartRegistrationButton.setVisible(true);
            comboUsers.setVisible(true);
            //JOptionPane.showMessageDialog(null,"Welcome Admin");
        }
        else{
            StartRegistrationButton.setVisible(false);
            comboUsers.setVisible(false);
            //JOptionPane.showMessageDialog(null,"Welcome "+userID+"");
        }
        if(Admin){
            StartRegistrationButton.setVisible(true);
            comboUsers.setVisible(true);
        }

        //Write data to file when mouse is moved within the application
        panelMain.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                String DataFile = userprofile + "/App/"+currentUser+".csv";
                //inputarray(DataFile);
                GetStuff();
                VarToArray(Holder);
                WriteToFile(DataFile);
            }
        });

        //Move between pages
        NextPage.addActionListener(e -> {
            GetStuff();
            VarToArray(Holder);
            Holder = Holder + 7;
            ArrayToVariable(Holder);
            SetStuff();
            setDays(calDate,Holder);
        });
        PreviousPage.addActionListener(e -> {
            GetStuff();
            VarToArray(Holder);
            if(Holder == 0){
                Holder = 7;
            }
            Holder = Holder - 7;
            ArrayToVariable(Holder);
            SetStuff();
            setDays(calDate,Holder);
        });

        //User edit button as admin
        StartRegistrationButton.addActionListener(e -> {
            new com.company.StartRegistration();
            frametemp.dispose();
        });
        //Open calculator
        CalcButton.addActionListener(e -> new Calculator());
        //Open List app
        listButton.addActionListener(e -> new ListGenerator.ListApp(userID));

        logOutButton.addActionListener(e -> {
            IDandPasswords idandPasswords = new IDandPasswords();
            new LoginPage(idandPasswords.getLoginInfo());
            frametemp.dispose();
        });

        //Swap user as admin
        comboUsers.addActionListener(e -> {
            currentUser = (String) comboUsers.getSelectedItem();
            String DataFile1 = userprofile + "/App/"+currentUser+".csv";
            inputarray(DataFile1);
            ArrayToVariable(Holder);
            SetStuff();
        });

        //Color Button actions
        BeachColorButton.addActionListener(e -> {
            ColorChange(new Color(147,208,255),new Color(255,243,221),Color.BLACK,new Color(172,247,255));
        });
        SunshineButton.addActionListener(e -> {
            ColorChange(new Color(246, 255, 0, 255),new Color(242, 246, 87),Color.BLACK,new Color(253, 174, 83));
        });
        purpleButton.addActionListener(e -> {
            ColorChange(new Color(119, 29, 255, 255),new Color(168, 97, 255),Color.BLACK,new Color(205, 182, 255));
        });
        OceanButton.addActionListener(e -> {
            ColorChange(new Color(14, 80, 255, 255),new Color(77, 121, 255),Color.BLACK,new Color(152, 181, 255));
        });
        MintButton.addActionListener(e -> {
            ColorChange(new Color(81, 208, 71, 255),new Color(164, 255, 134),Color.BLACK,new Color(192, 255, 195));
        });
        GrizzlyButton.addActionListener(e -> {
            ColorChange(new Color(212, 158, 0, 255),new Color(248, 231, 172),Color.BLACK,new Color(0, 0, 0));
        });
        NinjaButton.addActionListener(e -> {
            ColorChange(new Color(255,255,255),new Color(255,19,19),Color.BLACK,new Color(90,90,90));
        });
    }

    //Set the day and month on the day of the week
    private void setDays(Date calDate,int offset) {
        SimpleDateFormat Day = new SimpleDateFormat("MM/dd");
        Calendar c = Calendar.getInstance();
        c.setTime(calDate);
        c.add(Calendar.DATE,offset);
        mondayFormattedTextField.setText("Monday ("+Day.format(c.getTime())+")");
        c.add(Calendar.DATE,1);
        tuesdayFormattedTextField.setText("Tuesday ("+Day.format(c.getTime())+")");
        c.add(Calendar.DATE,1);
        wednesdayFormattedTextField.setText("Wednesday ("+Day.format(c.getTime())+")");
        c.add(Calendar.DATE,1);
        thursdayFormattedTextField.setText("Thursday ("+Day.format(c.getTime())+")");
        c.add(Calendar.DATE,1);
        fridayFormattedTextField.setText("Friday ("+Day.format(c.getTime())+")");
        c.add(Calendar.DATE,1);
        saturdayFormattedTextField.setText("Saturday ("+Day.format(c.getTime())+")");
        c.add(Calendar.DATE,1);
        sundayFormattedTextField.setText("Sunday ("+Day.format(c.getTime())+")");
    }

    //get the Monday before the first startup from file
    private void importTime(String StartFile) {
        CreateFile(StartFile);
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(StartFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i = 0;
        scanner.useDelimiter("¬");
        while(scanner.hasNext()){
            stringstartDate = scanner.next();
            //JOptionPane.showMessageDialog(null,stringstartDate);
        }

        DateFormat readDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = (Date)readDate.parse(stringstartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //JOptionPane.showMessageDialog(null,startDate);
    }

    private void CreateFile(String FilePathName){
        try {
            File myObj = new File(FilePathName);
            if (myObj.createNewFile()) {
//                    JOptionPane.showMessageDialog(null,"File created: " + myObj.getName());
            } else {
//                    JOptionPane.showMessageDialog(null,"File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void WriteToFile(String DataFile){
        CreateFile(DataFile);
        FileWriter tofile = null;
        try {
            tofile = new FileWriter(DataFile);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        int len = TempArr.length;
        for (int i = 0; i < len; i++){
            try {
                tofile.write(TempArr[i]+"¬");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        try {
            tofile.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void inputarray(String DataFile){
        File directory = new File(FolderPath);
        if (! directory.exists()){
            directory.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }
        CreateFile(DataFile);
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(DataFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i = 0;
        scanner.useDelimiter("¬");
        while(scanner.hasNext()){
            TempArr[i++] = scanner.next();
        }
    }

    private void ArrayToVariable(int Start){
        varMon = TempArr[Start];
        varTues = TempArr[Start+1];
        varWed = TempArr[Start+2];
        varThurs = TempArr[Start+3];
        varFri = TempArr[Start+4];
        varSat = TempArr[Start+5];
        varSun = TempArr[Start+6];
    }

    private void VarToArray(int Start){
        TempArr[Start] = varMon;
        TempArr[Start+1] = varTues;
        TempArr[Start+2] = varWed;
        TempArr[Start+3] = varThurs;
        TempArr[Start+4] = varFri;
        TempArr[Start+5] = varSat;
        TempArr[Start+6] = varSun;
    }

    private void GetStuff(){
        varMon = textMon.getText();
        varTues = textTues.getText();
        varWed = textWed.getText();
        varThurs = textThurs.getText();
        varFri = textFri.getText();
        varSat = textSat.getText();
        varSun = textSun.getText();
    }

    private void SetStuff(){
        textMon.setText(varMon);
        textTues.setText(varTues);
        textWed.setText(varWed);
        textThurs.setText(varThurs);
        textFri.setText(varFri);
        textSat.setText(varSat);
        textSun.setText(varSun);
    }

    private void ColorChange(Color oneColor,Color twoColor,Color threeColor,Color fourColor){
        wednesdayFormattedTextField.setBackground(oneColor);
        thursdayFormattedTextField.setBackground(oneColor);
        fridayFormattedTextField.setBackground(oneColor);
        saturdayFormattedTextField.setBackground(oneColor);
        sundayFormattedTextField.setBackground(oneColor);
        tuesdayFormattedTextField.setBackground(oneColor);
        mondayFormattedTextField.setBackground(oneColor);
        textMon.setBackground(twoColor);
        textTues.setBackground(twoColor);
        textWed.setBackground(twoColor);
        textThurs.setBackground(twoColor);
        textFri.setBackground(twoColor);
        textSat.setBackground(twoColor);
        textSun.setBackground(twoColor);
        wednesdayFormattedTextField.setForeground(threeColor);
        thursdayFormattedTextField.setForeground(threeColor);
        fridayFormattedTextField.setForeground(threeColor);
        saturdayFormattedTextField.setForeground(threeColor);
        sundayFormattedTextField.setForeground(threeColor);
        tuesdayFormattedTextField.setForeground(threeColor);
        mondayFormattedTextField.setForeground(threeColor);
        textMon.setForeground(threeColor);
        textTues.setForeground(threeColor);
        textWed.setForeground(threeColor);
        textThurs.setForeground(threeColor);
        textFri.setForeground(threeColor);
        textSat.setForeground(threeColor);
        textSun.setForeground(threeColor);
        panelMain.setBackground(fourColor);
    }

    public static class CalendarApp {

        public CalendarApp(String userID) {
            //JFrame frame = new JFrame("Class 2999 Calendar");
            ImageIcon ninja = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ninja.png")));
            JFrame frame = frametemp;
            frame.setContentPane(new App(userID).panelMain);
            frame.setIconImage(ninja.getImage());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(1280, 720));
            frame.pack();
            frame.setVisible(true);
        }
    }
}
