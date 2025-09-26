enum TestStatus {
    PASS,
    MIXED,
    FAIL,
    ERROR
}
public class Test {
    public String name;
    public Test[] tests;
    public TestStatus result;

    public Test(String name, boolean result) {
        this(name, result, new Test[] {});
    }
    public Test(String name, Test[] tests) {
        this(name, true, tests);
    }
    public Test(String name, boolean result, Test[] tests) {
        this.name = name;
        this.result = Test.run(result);
        this.tests = tests;
    }

    private TestStatus run() {
        boolean containsPass = false;
        boolean containsFail = false;

        if (this.tests.length == 0) {
            return this.result;
        }
        TestStatus[] testResults = new TestStatus[0];
        for (Test test : this.tests) {
            TestStatus result = test.run();
            testResults = append(testResults, result);
        }
        for (TestStatus testStatus : testResults) {
            if (testStatus == TestStatus.MIXED) {
                return TestStatus.MIXED;
            }
            if (testStatus == TestStatus.PASS) {
                containsPass = true;
            }
            if (testStatus == TestStatus.FAIL) {
                containsFail = true;
            }
            if (containsPass && containsFail) {
                return TestStatus.MIXED;
            }
        }
        if (containsPass) {
            return TestStatus.PASS;
        }
        if (containsFail) {
            return TestStatus.FAIL;
        }

        return TestStatus.ERROR;
    }
    private static TestStatus run(boolean result) {
        if (result) {
            return TestStatus.PASS;
        }
        return TestStatus.FAIL;
    }
    private TestStatus[] append(TestStatus[] testArray, TestStatus result) {
        TestStatus[] newTestArray = new TestStatus[testArray.length + 1];

        for (int i = 0; i < newTestArray.length; i++) {
            if (i < testArray.length) {
                newTestArray[i] = testArray[i];
                continue;
            }
            newTestArray[i] = result;
        }

        return newTestArray;
    }
    private static int[] append(int[] intArray, int newElement) {
        int[] newIntArray = new int[intArray.length + 1];

        for (int i = 0; i < newIntArray.length; i++) {
            if (i < intArray.length) {
                newIntArray[i] = intArray[i];
                continue;
            }
            newIntArray[i] = newElement;
        }
        
        return newIntArray;
    }
    public String output() {
        return output(0);
    }
    private String output(int level) {
        TestStatus status = this.run();
        String result;
        String indent = "";
        String output;

        TestFunctions.numTests++;
        if (status == TestStatus.PASS) {
            result = "PASS";
            TestFunctions.numPassed++;
        } else if (status == TestStatus.FAIL) {
            result = "FAIL";
        } else if (status == TestStatus.MIXED) {
            result = "SOME PASSING";
        } else {
            throw new IllegalArgumentException("Result property was ERROR.");
        }

        for (int i = 0; i < level; i++) {
            indent += "- ";
        }

        TestFunctions.maxWidth = Math.max(TestFunctions.maxWidth,
            indent.length() + this.name.length());
        TestFunctions.lineWidths = append(TestFunctions.lineWidths,
            indent.length() + this.name.length());
        output = indent + "- " + this.name + ": %spaces%" + result;
        for (Test test : this.tests) {
            output += "\n" + test.output(level + 1);
        }

        return output;
    }
}