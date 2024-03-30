package com.test;

import com.pages.LoginPg;

import Lib.Files.Base;

public class Login{
    public void executeTest(Base b) {
    	b.web.launchUrl("http://tutorialsninja.com/demo");
    	
    	LoginPg loginPg = new LoginPg(b);
    	loginPg.login();  
    	loginPg.authenticationTest();
    
    }
}
