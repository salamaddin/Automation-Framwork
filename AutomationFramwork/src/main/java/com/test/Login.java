package com.test;

import Lib.Files.Base;

public class Login extends Base {
    public void executeTest(Base b) {
        // Now you can use the testData object as needed
        String user = b.getData("Username");
        String password = b.getData("Password");
        System.out.println(user);
        System.out.println(password);
    }
}
