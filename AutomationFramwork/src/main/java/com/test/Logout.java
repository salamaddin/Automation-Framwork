package com.test;

import Lib.Files.Base;

public class Logout {
    public void executeTest(Base b) {
        // Now you can use the testData object as needed
        String user = b.getData("Username");
        String password = b.getData("Password");
        String ReportProcessName = b.getData("ReportProcessName");
        System.out.println(user);
        System.out.println(password);

    }
}
