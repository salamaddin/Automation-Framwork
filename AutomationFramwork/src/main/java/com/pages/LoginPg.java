package com.pages;

import Lib.Files.Base;
import Utils.O;

public class LoginPg {
	public Base b;
	public LoginPg(Base b){
		this.b = b;
	}

	O myAccount = new O().d("My Account");
	O login = new O().d("Login");
	O User = new O().d("E-Mail Address"); 
	O Password = new O().d("Password");
	O phSignIn = new O().xp("//input[@type='submit']").d("Submit");
	O authenticationXP = new O().xp("//h2[contains(text(),'My Account')]").d("My Account");
	
	public void login() {
		b.web.getXP(myAccount.span()).click();
		b.web.getXP(login.link()).click();
		b.web.getXP(User.placeHolder()).setText(b.getData("Username"));
		b.web.getXP(Password.placeHolder()).setText(b.getData("Password"));
		b.web.getXP(phSignIn.xpath()).click();
	}

	public void authenticationTest() {
		if (b.web.isExist(authenticationXP.xpath())) {
			b.reporter.pass("Successfuly logged in");
		} else {
			b.reporter.fail("Login failed");
		}
	}

}