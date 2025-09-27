enum TestStatus {
    PASS,
    MIXED,
    FAIL,
    ERROR
}
/**
 * A class representing a single test. May have an empty Test[] as its tests
 *     property, or a battery of tests which might themselves have their own
 *     tests. The result property can be set either as a constant boolean, or by
 *     feeding the result of a boolean test method directly into the "result"
 *     parameter. Alternatively, the run() method can be overridden to call
 *     run(that boolean test method) instead.
 */
public class Test {
    public String name;
    public Test[] tests;
    public TestStatus result;

    public Test(String name, boolean result) {
        this(name, result, new Test[0]);
    }
    public Test(String name, Test[] tests) {
        this(name, true, tests);
    }
    public Test(String name, boolean result, Test[] tests) {
        this.name = name;
        this.result = Test.run(result);
        this.tests = tests;
    }

    /**
     * Converts a boolean to its TestStatus equivalent.
     * @param result a boolean to be converted
     * @return a TestStatus containing the TestStatus equivalent of the given
     *     boolean.
     */
    private static TestStatus run(boolean result) {
        if (result) {
            return TestStatus.PASS;
        }
        return TestStatus.FAIL;
    }
    /**
     * For a single test, returns the result value assigned to it. For a battery
     *     of tests or a test with a battery of tests under it, returns the
     *     result value assigned to it combined with the results of any tests
     *     underneath it.
     * @return a TestStatus containing the result of whatever was run.
     */
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
    /**
     * A helper method appending a given TestStatus to a TestStatus[].
     * @param testArray a TestStatus[] which cannot be null.
     * @param result a TestStatus which cannot be null.
     * @return a TestStatus[] consisting of testArray with result appended to
     *     the end of it.
     * @throws IllegalArgumentException if testArray or result is null.
     */
    private TestStatus[] append(TestStatus[] testArray, TestStatus result) {
        if (testArray == null) {
            throw new IllegalArgumentException("Called"
                + " Test.append(TestStatus[], TestStatus) with null in the"
                + " place of the TestStatus[]");
        }
        if (result == null)  {
            throw new IllegalArgumentException("Called"
                + " Test.append(TestStatus[], TestStatus) with null in the"
                + " place of the TestStatus");
        }
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
    /**
     * Helper function allowing output(0) to be called as output() instead.
     * @return a String containing the output of this test and any nested tests.
     */
    public String output() {
        return output(0);
    }
    /**
     * A recursive function returning the output of this test and the output of
     *     any tests nested one level beneath it.
     * @return a String containing the output of this test and any tests nested
     *     one level beneath it.
     */
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
        TestFunctions.lineWidths = HelperFunctions.append(
            TestFunctions.lineWidths, indent.length() + this.name.length());
        output = indent + "- " + this.name + ": %spaces%" + result;
        for (Test test : this.tests) {
            output += "\n" + test.output(level + 1);
        }

        return output;
    }
}