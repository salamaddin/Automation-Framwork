package com.pages;

import Lib.Files.Base;
import Utils.O;

public class LoginPg {
	public Base b;
	public String emailTextXP = "//input[@placeholder='Email']";
	public String passwordXP = "//input[@placeholder='Password']";
	public String loginBtnXP = "//button[.='Login']";
	public String authenticationXP ="//h2[contains(text(),'Hi,')]";
	
	public LoginPg(Base b){
		this.b = b;
		
	}

	O myAccount = new O().d("My Account");
	O login = new O().d("Login");
	O User = new O().d("E-Mail Address"); 
	O Password = new O().d("Password");
	O phSignIn = new O().xp("//input[@type='submit']").d("Login");
	
	public void login() {
		b.web.getObject(myAccount.span()).click();
		b.web.getObject(login.link()).click();
		b.web.getObject(User.placeHolder()).sendKeys(b.getData("Username"));
		b.web.getObject(Password.placeHolder()).sendKeys(b.getData("Password"));
		b.web.getObject(phSignIn.xpath()).click();
	}

}
