package com.test;

import com.pages.LoginPg;

import Lib.Files.Base;

public class Login{
    public void executeTest(Base b) {
    	b.web.launchUrl("http://tutorialsninja.com/demo");
    	
    	LoginPg login = new LoginPg(b);
    	login.login();
//        String user = b.getData("Username");
//        String password = b.getData("Password");    	
    
    }
}
