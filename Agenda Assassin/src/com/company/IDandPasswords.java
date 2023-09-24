package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class IDandPasswords {
    String userprofile = System.getenv("USERPROFILE");
    HashMap<String,String> logininfo = new HashMap<String,String>();
    String FileName = userprofile + "/App/login.csv";

    public IDandPasswords(){
        logininfo.put("Admin","PASSWORD");
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(FileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.useDelimiter("¬");
        while(scanner.hasNext()){
            String String1 = scanner.next();
            String String2 = scanner.next();
            logininfo.put(String1,String2);
        }

        //logininfo.put("Bro","pizza");
        //logininfo.put("Brometheus","PASSWORD");
        //logininfo.put("BroCode","abc123");
    }

    public HashMap getLoginInfo(){
        return logininfo;
    }
}
