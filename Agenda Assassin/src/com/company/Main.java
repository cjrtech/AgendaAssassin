package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        String userprofile = System.getenv("USERPROFILE");
        String FileName = userprofile + "/App/login.csv";
        String FolderPath = userprofile + "/App";
        String StartFile = userprofile + "/App/startdate";
        File directory = new File(FolderPath);
        SimpleDateFormat dateformatter= new SimpleDateFormat("yyyy-MM-dd");
        Date startMonday;

        startMonday = getMonday();
        if (! directory.exists()){
            directory.mkdirs();
            // If you require it to make the entire directory path including parents,use directory.mkdirs(); here instead.
        }
        try {
            File myObj = new File(FileName);
            if (myObj.createNewFile()) {
                try {
                    File startWrite = new File(StartFile);
                    if (startWrite.createNewFile()) {
                        FileWriter tofile = null;
                        try {
                            tofile = new FileWriter(StartFile,true);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        try{
                            tofile.write(dateformatter.format(startMonday)+"¬");
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
                        LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());
                    } else {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
               //new StartRegistration();

            } else {

                IDandPasswords idandPasswords = new IDandPasswords();
                LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //IDandPasswords idandPasswords = new IDandPasswords();

        //LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo());

    }
    private static Date getMonday(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date tempDate = cal.getTime();
        //JOptionPane.showMessageDialog(null,tempDate);
        return tempDate;
    }
}
