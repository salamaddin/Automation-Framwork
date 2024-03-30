package execution;

import Lib.Files.FilloXL;
import Lib.Files.Scripts;

public class TestExecutor {

    public static void executeTests() {
        try {
            Scripts scripts = new Scripts();

            while (scripts.hasNext()) {
            	scripts.executeTest();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        executeTests();
    }
}
