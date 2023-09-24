package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ListGenerator {
    private JPanel panelMain;
    private JTextArea listText1;
    private JTextArea listText2;
    private JTextArea listText3;
    private JTextArea listText4;
    String userprofile = System.getenv("USERPROFILE");
    String FolderPath = userprofile + "/App";
    String [] TempArr = new String [4];
    String currentUser;
    String listData1 = "";
    String listData2 = "";
    String listData3 = "";
    String listData4 = "";

    public ListGenerator(String userID){
        currentUser = userID;
        String listName = userprofile + "/App/"+currentUser+"list.csv";
        for (int i = 0; i < 4; i++){
            TempArr[i]="";
        }
        inputarray(listName);
        listText1.setText(TempArr[0]);
        listText2.setText(TempArr[1]);
        listText3.setText(TempArr[2]);
        listText4.setText(TempArr[3]);
        panelMain.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                listData1 = listText1.getText();
                listData2 = listText2.getText();
                listData3 = listText3.getText();
                listData4 = listText4.getText();
                VarToArray(0);
                WriteToFile(listName);
            }
        });
    }

    private void VarToArray(int Start){
        TempArr[Start] = listData1;
        TempArr[Start+1] = listData2;
        TempArr[Start+2] = listData3;
        TempArr[Start+3] = listData4;
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
    public static class ListApp {
        public ListApp(String userID) {
            JFrame frame = new JFrame("ListGenerator");
            ImageIcon ninja = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ninja.png")));
            frame.setIconImage(ninja.getImage());
            frame.setContentPane(new ListGenerator(userID).panelMain);
            frame.setPreferredSize(new Dimension(1400, 720));
            frame.pack();
            frame.setVisible(true);
            frame.setAlwaysOnTop(true);
        }
    }
}
