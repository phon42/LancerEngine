/**
 * Holds a set of possible test statuses that a Test can return or contain.
 */
enum TestStatus {
    PASS,
    MIXED,
    FAIL,
    ERROR
}
/**
 * A class representing a single test. May have an empty Test[] as its tests
 *     property, or a battery of tests which might themselves have their own
 *     tests.
 * The result property can be set either as a constant boolean, or by
 *     feeding the result of a boolean test method directly into the "result"
 *     parameter. Alternatively, the run() method can be overridden to call
 *     run(that boolean test method) instead.
 * When output() is called, this test's result is combined with the results of
 *     all tests underneath it.
 */
/**
 * Represents a single test. Contains a test name, an array of tests underneath
 *     it, and a result for this specific test.
 * 
 * Requires a test name and a test result to be instantiated.
 * 
 * Used in TestMethods.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. All of its properties can be set to null.
 */
public class Test {
    /**
     * The test's name (i.e. "Pilot.setCallsign()").
     * Can be any String except "". Cannot be null.
     */
    public String name;
    /**
     * The set of tests underneath this test.
     * Can be any Test[] that is not null or contains null elements.
     */
    public Test[] tests;
    /**
     * This test's result. Can be any TestStatus. Cannot be null.
     */
    public TestStatus result;

    /**
     * Creates a new Test with the provided test name and test result.
     * @param testName a String which cannot be null or "".
     * @param testResult a boolean.
     */
    public Test(String testName, boolean testResult) {
        this(testName, testResult, new Test[0]);
    }
    /**
     * Creates a new Test with the provided test name and array of tests.
     * @param testName a String which cannot be null or "".
     * @param testArray a Test[] which cannot be null or contain null elements.
     */
    public Test(String testName, Test[] testArray) {
        this(testName, true, testArray);
    }
    /**
     * Creates a new Test with the provided test name, test result, and array of
     *     tests.
     * @param testName a String which cannot be null or "".
     * @param testResult a boolean.
     * @param testArray a Test[] which cannot be null or contain null elements.
     */
    public Test(String testName, boolean testResult, Test[] testArray) {
        this.name = testName;
        this.result = Test.run(testResult);
        this.tests = testArray;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("New test name is null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("New test name is \"\"");
        }
        this.name = name;
    }
    public void setTests(Test[] tests) {
        if (tests == null) {
            throw new IllegalArgumentException("New tests array is null");
        }
        for (Test test : tests) {
            if (test == null) {
                throw new IllegalArgumentException("New tests array contains"
                    + " a null element");
            }
        }
        this.tests = tests;
    }
    public void setResult(TestStatus result) {
        if (result == null) {
            throw new IllegalArgumentException("New test result is null");
        }
        this.result = result;
    }

    /**
     * Converts a boolean to its TestStatus equivalent.
     * @param result a boolean to be converted.
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
     * Helper method allowing output(0) to be called as output() instead.
     * @return a String containing the output of this test and any nested tests.
     */
    public String output() {
        return output(0);
    }
    /**
     * A recursive method returning the output of this test and the output of
     *     any tests nested one level beneath it.
     * @return a String containing the output of this test and any tests nested
     *     one level beneath it.
     * @throws IllegalArgumentException if this.run() evaluates to
     *     TestStatus.ERROR.
     */
    private String output(int level) {
        TestStatus status = this.run();
        String result;
        String indent = "";
        String output;

        TestMethods.numTests++;
        if (status == TestStatus.PASS) {
            result = "PASS";
            TestMethods.numPassed++;
        } else if (status == TestStatus.FAIL) {
            result = "FAIL";
        } else if (status == TestStatus.MIXED) {
            result = "SOME PASSING";
        } else {
            throw new IllegalArgumentException("this.run() evaluated to"
                + " TestStatus.ERROR");
        }

        for (int i = 0; i < level; i++) {
            indent += "- ";
        }

        TestMethods.maxWidth = Math.max(TestMethods.maxWidth,
            indent.length() + this.name.length());
        TestMethods.lineWidths = HelperMethods.append(
            TestMethods.lineWidths, indent.length() + this.name.length());
        output = indent + "- " + this.name + ": %spaces%" + result;
        for (Test test : this.tests) {
            output += "\n" + test.output(level + 1);
        }

        return output;
    }
    /**
     * Helper method allowing output() or output(0) to be called as toString()
     *     instead.
     * @return a String containing the output of this test and any nested tests.
     */
    @Override
    public String toString() {
        return output(0);
    }
}