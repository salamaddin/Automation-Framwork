package Lib.Files;

import java.lang.reflect.Method;
import java.util.HashMap;

import Utils.Reporter;
import Utils.Web;

public class Scripts {
    private HashMap<Integer, Script> scripts;
    private int autoInc = 0;
    private int currScriptIndex = 1;
    private Script curr;
    private String cmpName = "";
    private int index;
    private FilloXL xl;
    private TestData testData;
    private Web web;
    private Reporter reporter;
    private String masterDriverPath ="C:/Users/arka.sarkar/Desktop/Data/MasterDriver.xlsx";
    private String reportPath ="C:/Users/arka.sarkar/Desktop/Data/";

    public Scripts() throws Exception {
        loadScripts();
    }

    public void loadScripts() throws Exception {
        xl = new FilloXL();
        scripts = new HashMap<Integer, Script>();
        xl.getRecords(masterDriverPath, "Scripts", " Run='Y'");
        while (xl.next()) {
            Script temp = new Script();
            temp.setTestID(xl.get("TestID"));
            temp.setTestTitle(xl.get("Test Title"));
            temp.setTestDescription(xl.get("Test Description"));
            autoInc++;
            scripts.put(autoInc, temp);
        }
    }

    public void setNext() {
        curr = scripts.get(currScriptIndex);
        currScriptIndex++;
    }

    public boolean hasNext() {
        return currScriptIndex <= scripts.size();
    }

    public void executeTest() throws Exception {
        setNext();
        testData = new TestData(curr);
        web = new Web("chrome");
        reporter = new Reporter(reportPath +curr.getTestID()+ "_"+ curr.getTestDescription());
        Reporter.createTest(curr.getTestTitle(), curr.getTestDescription());
        Base base = new Base();
        base.setTestData(testData);
        base.setWeb(web);
        base.setReporter(reporter);
        
        index = 1;
        System.out.println("Current Test ID: " + curr.getTestID());
        xl.getRecords(masterDriverPath, "Components"," TestID ='" + curr.getTestID() + "'");

        while (xl.next()) {
            getComponentName();
            while (!"".contentEquals(cmpName)) {              
                System.out.println("Component: " + cmpName);
                try {
                    Class<?> testClass = Class.forName(cmpName);
                    Object testInstance = testClass.getDeclaredConstructor().newInstance();

                    Method executeTestMethod = testClass.getMethod("executeTest", Base.class);
                    executeTestMethod.invoke(testInstance, base);
                } catch (Exception e) {
                    e.printStackTrace();
                }
	              getComponentName();
            }
        }
        Reporter.closeReport();
        web.quit();
        
    }

    public void getComponentName() throws Exception {
        cmpName = xl.get(index);
        index++;
    }
}
