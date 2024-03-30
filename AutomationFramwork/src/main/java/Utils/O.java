package Utils;

import org.openqa.selenium.WebElement;

//import Lib.Files.Reporter;

public class O {
	
	public String locator = "";
	public String locatorValue = "";
	public String display = "";
	public String xpath = "";
	public boolean contains = false;
	
	
	public String spanXP	= "//span[text()='{{TEXT}}']";
	public String btnXP	="//button[{{ATTR}}='{{ATTRVALUE}}']";
	public String imgXP = "//img[{{ATTR}}='{{ATTRVALUE}}']";
	public String cbXP  = "//input[{{ATTR}}='{{ATTRVALUE}}']";
	public String tbXP  = "//input[{{ATTR}}='{{ATTRVALUE}}']";	
	public String taXP  = "//textarea[{{ATTR}}='{{ATTRVALUE}}']";
	public String sbXP = "//select[{{ATTR}}='{{ATTRVALUE}}']";
	public String lnkXP = "//a[{{ATTR}}='{{ATTRVALUE}}']";
	public String divXP     = "//div[{{ATTR}}= '{{ATTRVALUE}}']";
	public String placeHolderXP = "//input[@placeholder='{{ATTRVALUE}}']";
	public String buttonXP = "//button[text()='{{ATTRVALUE}}']";
	
	
	
	public String getPatternXpath(String type)
	{
		String ret = "";
		if(type.equals("placeHolder")) {
			ret = placeHolderXP.replace("{{ATTRVALUE}}", display);
		}
		else if(type.equals("button")) {
			ret = buttonXP.replace("{{ATTRVALUE}}", display);
		}
		else if(type.equals("span")) {
			ret = spanXP.replace("{{TEXT}}", display);
		}
		else if(type.equals("lnkXP")) {
			
			ret = lnkXP.replace("{{ATTRVALUE}}", display);
			if(locator.equals("@title")) {
				ret = ret.replace("{{ATTR}}", locator);
			}else {
				ret = ret.replace("{{ATTR}}", "text()");
			}
			
		}else if(type.equals("XP")) {
			ret = xpath;
		}
		
		if(contains == true) {
			ret = ret.replace("[text()=", "[contains(text(),");
			ret = ret.replace("]", ")]");
		}
			ret = ret+"##"+display;
			
		return ret;
	}
	
	public String placeHolder()
	{	
		//this.hasAttr=true;	
		//this.locator = "@title";
	//	this.locatorValue = value;
		return getPatternXpath("placeHolder");
	}
	
	public String button()
	{	
		return getPatternXpath("button");
	}
	public String link()
	{	
		String str =  getPatternXpath("lnkXP");
		System.out.println(str);
		return str;
	}
	public String span()
	{	
		return getPatternXpath("span");
	}
	public O d(String display) {
		this.display = display;
		return this;
	}
	
	public O contains(boolean contains) {
		this.contains = contains;
		return this;
	}
	public O title(String title) {
		this.locator = "@title";
		return this;
	}

	public O xp(String xpath) {
		this.xpath = xpath;
		return this;
	}

	public String xpath() {
		return getPatternXpath("XP");
	}

}

