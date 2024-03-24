package Lib.Files;

import java.util.HashMap;

public class TestData {
	Script script;
	FilloXL xl;
	String filePath;
	private String testDataPath ="C:/Users/arka.sarkar/Desktop/Data/";
	protected static HashMap<String, User> env;

	public TestData(Script script) throws Exception
	{
		this.script   = script;
		xl = new FilloXL();
		loadTestData();
	}
		
	public void loadTestData() throws Exception {
		try {
			String testDataFilePath = testDataPath + script.getTestID()+".xlsx";
			xl.getRecords(testDataFilePath, "TestData");
			
		} catch (Exception e) {
			System.out.print("Test data is not found");
			e.printStackTrace();
		}
	}
	
	public void display() throws Exception {
		while(xl.next()) {
		System.out.println("print "+xl.get("Severity"));
		}
	}
	
	public String get(String colName)
	{
		String ret  = "";
		try
		{
			xl.next();
			ret  = xl.get(colName);
		}
		catch(Exception e)
		{

		}

		return ret;
	}

}
