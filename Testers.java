/**
 * Tests every class and every function under every class. Yes, I'm serious.
 */
public class Testers {
    private static String printBuffer = "";
    private static int maxWidth = 0;
    private static int numPassed = 0;
    private static int numTests = 0;

    /**
     * Tests every class and function in the project.
     */
    public static void runTests() {
        // run a test on every single goddamn class and function in this place
        String line = "= = = = = = = = = = = = = = =";

        printBuffer = "";
        maxWidth = 0;
        numPassed = 0;
        numTests = 0;
        printBuffer += "RUNNING TESTS\n";
        printBuffer += line + "\n";

        // ---PILOT-----------------------------------------------------------
        boolean pilotTestsPassed = runPilotTests();
        printStatement(pilotTestsPassed, "Pilot", 1);

        // ---MECH------------------------------------------------------------
        boolean mechTestsPassed = runMechTests();
        printStatement(mechTestsPassed, "Mech", 1);

        printBuffer += line + "\n";
        if (pilotTestsPassed && mechTestsPassed) {
            printBuffer += "ALL TESTS PASSED: ("
                + numPassed + "/" + numTests + " passed)\n";
        } else {
            printBuffer += "SOME TESTS FAILED: ("
                + numPassed + "/" + numTests + " passed)\n";
        }
        for (String outputLine : printBuffer.split("\n")) {
            outputBuffer(outputLine);
        }
    }
    public static void printStatement(boolean testPassed, String output,
        int level) {
        String outputString = "";

        for (int i = 0; i < level; i++) {
            outputString += "- ";
        }
        outputString += output;
        outputString += ": ";
        if (testPassed) {
            outputString += "PASS";
        } else {
            outputString += "FAIL";
        }
        printBuffer += outputString + "\n";
        maxWidth = Math.max(maxWidth, output.length() + (2 * level) + 2);
        if (testPassed) {
            numPassed++;
        }
        numTests++;
    }
    /**
     * Output the contents of printBuffer after all tests have been run.
     * @param inputString a String containing one single line of the printBuffer
     */
    private static void outputBuffer(String inputString) {
        String[] inputStringArr = {"", ""};
        String spaces = "";
        boolean specialCase = false;
        String state = "";

        if (inputString.indexOf("PASS") != -1) {
            // a success
            inputStringArr = inputString.split("PASS");
            state = "PASS";
        } else if (inputString.indexOf("FAIL") != -1
            && inputString.indexOf("FAILED") == -1) {
            // a failure
            inputStringArr = inputString.split("FAIL");
            state = "FAIL";
        } else {
            System.out.println(inputString);
            specialCase = true;
        }
        if (! specialCase) {
            for (int i = 0; i < maxWidth - inputStringArr[0].length(); i++) {
                spaces += " ";
            }
            System.out.println(inputStringArr[0] + spaces + state);
        }
    }
    public static void compareStrings(String string1, String string2) {
        char char1;
        char char2;

        if (string1.length() != string2.length()) {
            System.out.println("Error! Strings are different lengths");
        }
        for (int i = 0; i < Math.min(string1.length(), string2.length()); i++) {
            char1 = string1.charAt(i);
            char2 = string2.charAt(i);
            if (char1 != char2) {
                System.out.println("Error! Characters \"" + char1 + "\" and \""
                    + char2 + "\" at index " + i + " are not the same.");
            }
        }
    }
    /**
     * Tests the Pilot class, all its functions, and all its dependencies.
     * @return a boolean representing whether the class and its dependencies
     *     passed.
     */
    private static boolean runPilotTests() {
        boolean setNameTests = false;
        boolean setCallsignTests = false;
        boolean setPlayerTests = false;
        boolean setStatusTests = false;
        boolean setBackgroundTests = false;
        boolean setBiographyTests = false;
        boolean setAppearanceTests = false;
        boolean setPlayerNotesTests = false;
        boolean setPilotGritTests = false;
        boolean setPilotCurrentHPTests = false;
        boolean setPilotMaxHPTests = false;
        boolean setPilotArmorTests = false;
        boolean setPilotEvasionTests = false;
        boolean setPilotSpeedTests = false;
        boolean setPilotEDefenseTests = false;
        boolean setSkillTriggersTests = false;
        boolean setReservesTests = false;
        boolean setLoadoutTests = false;
        boolean setLicenseLevelTests = false;
        boolean setLicenseListTests = false;
        boolean setSpecialEquipmentTests = false;
        boolean setMechSkillsTests = false;
        boolean setCoreBonusesTests = false;
        boolean setTalentsTests = false;
        boolean generateOutputTests = false;
        boolean outputLicensesTests = false;
        boolean outputTalentsTests = false;
        boolean outputCoreBonusesTests = false;
        boolean allTests = false;
        
        // Pilot.setName()
        setNameTests = runSetPilotNameTests();
        printStatement(setNameTests, "Pilot.setName()", 2);
        // Pilot.setCallsign()
        setCallsignTests = runSetCallsignTests();
        printStatement(setCallsignTests, "Pilot.setCallsign()", 2);
        // Pilot.setPlayer()
        setPlayerTests = runSetPlayerTests();
        printStatement(setPlayerTests, "Pilot.setPlayer()", 2);
        // Pilot.setStatus()
        setStatusTests = runSetStatusTests();
        printStatement(setStatusTests, "Pilot.setStatus()", 2);
        // Pilot.setBackground()
        setBackgroundTests = runSetBackgroundTests();
        printStatement(setBackgroundTests, "Pilot.setBackground()", 2);
        // Pilot.setBiography()
        setBiographyTests = runSetBiographyTests();
        printStatement(setBiographyTests, "Pilot.setBiography()", 2);
        // Pilot.setAppearance()
        setAppearanceTests = runSetAppearanceTests();
        printStatement(setAppearanceTests, "Pilot.setAppearance()", 2);
        // Pilot.setPlayerNotes()
        setPlayerNotesTests = runSetPlayerNotesTests();
        printStatement(setPlayerNotesTests, "Pilot.setPlayerNotes()", 2);
        // Pilot.setGrit()
        setPilotGritTests = runSetPilotGritTests();
        printStatement(setPilotGritTests, "Pilot.setGrit()", 2);
        // Pilot.setCurrentHP()
        setPilotCurrentHPTests = runSetPilotCurrentHPTests();
        printStatement(setPilotCurrentHPTests, "Pilot.setCurrentHP()", 2);
        // Pilot.setMaxHP()
        setPilotMaxHPTests = runSetPilotMaxHPTests();
        printStatement(setPilotMaxHPTests, "Pilot.setMaxHP()", 2);
        // Pilot.setArmor()
        setPilotArmorTests = runSetPilotArmorTests();
        printStatement(setPilotArmorTests, "Pilot.setArmor()", 2);
        // Pilot.setEvasion()
        setPilotEvasionTests = runSetPilotEvasionTests();
        printStatement(setPilotEvasionTests, "Pilot.setEvasion()", 2);
        // Pilot.setSpeed()
        setPilotSpeedTests = runSetPilotSpeedTests();
        printStatement(setPilotSpeedTests, "Pilot.setSpeed()", 2);
        // Pilot.setEDefense()
        setPilotEDefenseTests = runSetPilotEDefenseTests();
        printStatement(setPilotEDefenseTests, "Pilot.setEDefense()", 2);
        // Pilot.setSkillTriggers()
        setSkillTriggersTests = runSetPilotSkillTriggersTests();
        printStatement(setSkillTriggersTests, "Pilot.setSkillTriggers()", 2);
        // Pilot.setReserves()
        setReservesTests = runSetReservesTests();
        printStatement(setReservesTests, "Pilot.setReserves()", 2);
        // Pilot.setLoadout()
        setLoadoutTests = runSetLoadoutTests();
        printStatement(setLoadoutTests, "Pilot.setLoadout()", 2);
        // Pilot.setLicenseLevel()
        setLicenseLevelTests = runSetLicenseLevelTests();
        printStatement(setLicenseLevelTests, "Pilot.setLicenseLevel()", 2);
        // Pilot.setLicenseList()
        setLicenseListTests = runSetLicenseListTests();
        printStatement(setLicenseListTests, "Pilot.setLicenseList()", 2);
        // Pilot.setSpecialEquipment()
        setSpecialEquipmentTests = runSetSpecialEquipmentTests();
        printStatement(setSpecialEquipmentTests, "Pilot.setSpecialEquipment()", 2);
        // Pilot.setMechSkills()
        setMechSkillsTests = runSetMechSkillsTests();
        printStatement(setMechSkillsTests, "Pilot.setMechSkills()", 2);
        // Pilot.setCoreBonuses()
        setCoreBonusesTests = runSetCoreBonusesTests();
        printStatement(setCoreBonusesTests, "Pilot.setCoreBonuses()", 2);
        // Pilot.setTalents()
        setTalentsTests = runSetTalentsTests();
        printStatement(setTalentsTests, "Pilot.setTalents()", 2);
        // Pilot.generateOutput()
        generateOutputTests = runPilotGenerateOutputTests();
        printStatement(generateOutputTests, "Pilot.generateOutput()", 2);
        // Pilot.outputLicenses()
        outputLicensesTests = runOutputLicensesTests();
        printStatement(outputLicensesTests, "Pilot.outputLicenses()", 2);
        // Pilot.outputTalents()
        outputTalentsTests = runOutputTalentsTests();
        printStatement(outputTalentsTests, "Pilot.outputTalents()", 2);
        // Pilot.outputCoreBonuses()
        outputCoreBonusesTests = runOutputCoreBonusesTests();
        printStatement(outputCoreBonusesTests, "Pilot.outputCoreBonuses()", 2);

        allTests = setNameTests && setCallsignTests && setPlayerTests
            && setStatusTests && setBackgroundTests && setBiographyTests
            && setAppearanceTests && setPlayerNotesTests && setPilotGritTests
            && setPilotCurrentHPTests && setPilotMaxHPTests
            && setPilotArmorTests && setPilotEvasionTests && setPilotSpeedTests
            && setPilotEDefenseTests && setSkillTriggersTests
            && setReservesTests && setLoadoutTests && setLicenseLevelTests
            && setLicenseListTests && setSpecialEquipmentTests
            && setMechSkillsTests && setCoreBonusesTests && setTalentsTests
            && generateOutputTests && outputLicensesTests && outputTalentsTests
            && outputCoreBonusesTests;

        return allTests;
    }
    /**
     * Tests Pilot.setPilotName().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotNameTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;
        
        // normal case
        try {
            pilot.setName(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setName("validName");
        if (pilot.getName().equals("validName")) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests Pilot.setCallsign().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetCallsignTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;
        
        // normal case
        try {
            pilot.setCallsign(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setCallsign("validCallsign");
        if (pilot.getCallsign().equals("validCallsign")) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests Pilot.setPlayer().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPlayerTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;
        
        // normal case
        try {
            pilot.setPlayer(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setPlayer("validPlayer");
        if (pilot.getPlayer().equals("validPlayer")) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests Pilot.setStatus().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetStatusTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;
        
        // normal case
        try {
            pilot.setStatus(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setStatus("not a valid status");
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        pilot.setStatus("Active");
        if (pilot.getStatus().equals("active")) {
            test3 = true;
        }

        allTests = test1 && test2 && test3;

        return allTests;
    }
    /**
     * Tests Pilot.setBackground().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetBackgroundTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;
        
        // normal case
        try {
            pilot.setBackground(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setBackground("validBackground");
        if (pilot.getBackground().equals("validBackground")) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests Pilot.setBiography().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetBiographyTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;
        
        // normal case
        try {
            pilot.setBiography(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setBiography("validBiography");
        if (pilot.getBiography().equals("validBiography")) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests Pilot.setAppearance().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetAppearanceTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;
        
        // normal case
        try {
            pilot.setAppearance(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setAppearance("validAppearance");
        if (pilot.getAppearance().equals("validAppearance")) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests Pilot.setPlayerNotes().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPlayerNotesTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;
        
        // normal case
        try {
            pilot.setPlayerNotes(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setPlayerNotes("validPlayerNotes");
        if (pilot.getPlayerNotes().equals("validPlayerNotes")) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests Pilot.setGrit() indirectly by testing Pilot.setLicenseLevel() and
     *     Pilot.getGrit().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotGritTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean allTests = false;
        
        // normal case
        pilot.setLicenseLevel(-1);
        if (pilot.getGrit() == -1) {
            test1 = true;
        }
        // normal case
        pilot.setLicenseLevel(0);
        if (pilot.getGrit() == 0) {
            test2 = true;
        }
        // normal case
        pilot.setLicenseLevel(1);
        if (pilot.getGrit() == 1) {
            test3 = true;
        }
        // normal case
        pilot.setLicenseLevel(2);
        if (pilot.getGrit() == 1) {
            test4 = true;
        }
        // normal case
        pilot.setLicenseLevel(3);
        if (pilot.getGrit() == 2) {
            test5 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5;

        return allTests;
    }
    /**
     * Tests Pilot.setCurrentHP().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotCurrentHPTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        // normal case
        try {
            pilot.setCurrentHP(-2);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setMaxHP(5);
            pilot.setCurrentHP(9);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setCurrentHP(4);
        if (pilot.getCurrentHP() == 4) {
            test3 = true;
        }

        allTests = test1 && test2 && test3;

        return allTests;
    }
    /**
     * Tests Pilot.setMaxHP().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotMaxHPTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        // normal case
        try {
            pilot.setMaxHP(-2);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setMaxHP(0);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        pilot.setMaxHP(9);
        if (pilot.getMaxHP() == 9) {
            test3 = true;
        }

        allTests = test1 && test2 && test3;

        return allTests;
    }
    /**
     * Tests Pilot.setArmor().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotArmorTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;

        // normal case
        try {
            pilot.setArmor(-2);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setArmor(8);
        if (pilot.getArmor() == 8) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests Pilot.setEvasion().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotEvasionTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;

        // normal case
        try {
            pilot.setEvasion(-2);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setEvasion(8);
        if (pilot.getEvasion() == 8) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests Pilot.setSpeed().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotSpeedTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;

        // normal case
        try {
            pilot.setSpeed(-2);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setSpeed(-1);
        if (pilot.getSpeed() == -1) {
            test2 = true;
        }
        
        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests Pilot.setEDefense().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotEDefenseTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;

        // normal case
        try {
            pilot.setEDefense(-2);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setEDefense(-1);
        if (pilot.getEDefense() == -1) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests Pilot.setSkillTriggers().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotSkillTriggersTests() {
        Pilot pilot = new Pilot();
        SkillTriggersList skillTriggersList = new SkillTriggersList();
        SkillTrigger skillTrigger;
        boolean skillTriggersListTests = false;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean allTests = false;

        // Pilot.skillTriggers // SkillTriggersList
        skillTriggersListTests = runSkillTriggersListTests();
        printStatement(skillTriggersListTests,
            "SkillTriggersList", 3);

        // normal case
        try {
            pilot.setSkillTriggers(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setSkillTriggers(new SkillTriggersList());
        if (pilot.getSkillTriggers().equals(new SkillTriggersList())) {
            test2 = true;
        }
        // normal case
        skillTrigger = new SkillTrigger("", 2);
        skillTriggersList.setSkillTriggers(new SkillTrigger[] {
            skillTrigger
        });
        try {
            pilot.setSkillTriggers(skillTriggersList);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        skillTrigger = new SkillTrigger("validSkillTrigger", -1);
        skillTriggersList.setSkillTriggers(new SkillTrigger[] {
            skillTrigger
        });
        try {
            pilot.setSkillTriggers(skillTriggersList);
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        skillTrigger = new SkillTrigger("validSkillTrigger", 2);
        skillTriggersList.setSkillTriggers(new SkillTrigger[] {
            skillTrigger
        });
        pilot.setSkillTriggers(skillTriggersList);
        if (pilot.getSkillTriggers().getSkillTriggers()[0].equals(
            skillTrigger)) {
            test5 = true;
        }

        allTests = skillTriggersListTests && test1 && test2 && test3 && test4
            && test5;

        return allTests;
    }
    /**
     * Tests the SkillTriggersList class, all its functions, and all its
     *     dependencies.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runSkillTriggersListTests() {
        boolean setSkillTriggersTests = false;
        boolean skillTriggerTests = false;
        boolean equalsTests = false;
        boolean equalsSkillTriggersListTests = false;
        boolean generateOutputTests = false;
        boolean hasPlaceholdersTests = false;
        boolean allTests = false;

        setSkillTriggersTests = runSetSkillTriggersTests();
        printStatement(setSkillTriggersTests,
            "SkillTriggersList.setSkillTriggers()", 4);
        skillTriggerTests = runSkillTriggerTests();
        printStatement(skillTriggerTests, "SkillTrigger", 4);
        equalsTests = runSkillTriggersListEqualsTests();
        printStatement(equalsTests, "SkillTriggersList.equals(Object)",
            4);
        equalsSkillTriggersListTests = 
            runSkillTriggersListEqualsSkillTriggersListTests();
        printStatement(equalsSkillTriggersListTests,
            "SkillTriggersList.equals(SkillTriggersList)", 4);
        generateOutputTests = runSkillTriggersListGenerateOutputTests();
        printStatement(generateOutputTests,
            "SkillTriggersList.generateOutput()", 4);
        hasPlaceholdersTests =
            runSkillTriggersListHasPlaceholdersTests();
        printStatement(hasPlaceholdersTests,
            "SkillTriggersList.hasPlaceholders()", 4);
        
        allTests = setSkillTriggersTests && skillTriggerTests && equalsTests
            && equalsSkillTriggersListTests && generateOutputTests
            && hasPlaceholdersTests;
        
        return allTests;
    }
    /**
     * Tests SkillTriggersList.setSkillTriggers().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetSkillTriggersTests() {
        SkillTriggersList skillTriggers = new SkillTriggersList();
        SkillTrigger skillTrigger = new SkillTrigger();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean allTests = false;

        // normal case
        try {
            skillTriggers.setSkillTriggers(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        skillTriggers.setSkillTriggers(new SkillTrigger[] {});
        if (skillTriggers.getSkillTriggers().getClass() == SkillTrigger[].class
            && skillTriggers.getSkillTriggers().length == 0) {
            test2 = true;
        }
        // normal case
        try {
            skillTriggers.setSkillTriggers(new SkillTrigger[] {null});
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            skillTriggers.setSkillTriggers(new SkillTrigger[] {
                new SkillTrigger(), null});
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        skillTrigger = new SkillTrigger("Apply Fists to Faces", 2);
        skillTriggers.setSkillTriggers(new SkillTrigger[] {
            skillTrigger
        });
        if (skillTriggers.getSkillTriggers()[0].equals(skillTrigger)) {
            test5 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5;

        return allTests;
    }
    /**
     * Tests the SkillTrigger class and all its functions.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runSkillTriggerTests() {
        SkillTrigger skillTrigger = new SkillTrigger();
        boolean equalsTests = false;
        boolean equalsSkillTriggerTests = false;
        boolean hasPlaceholdersTests = false;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean test7 = false;
        boolean test8 = false;
        boolean test9 = false;
        boolean allTests = false;

        equalsTests = runSkillTriggerEqualsTests();
        printStatement(equalsTests,
            "SkillTrigger.equals(Object)", 5);
        equalsSkillTriggerTests = runSkillTriggerEqualsSkillTriggerTests();
        printStatement(equalsSkillTriggerTests,
            "SkillTrigger.equals(SkillTrigger)", 5);
        hasPlaceholdersTests = runSkillTriggerHasPlaceholdersTests();
        printStatement(hasPlaceholdersTests,
            "SkillTrigger.hasPlaceholders()", 5);

        // normal case
        try {
            skillTrigger.setName(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        skillTrigger.setName("validName");
        if (skillTrigger.getName().equals("validName")) {
            test2 = true;
        }
        // normal case
        try {
            skillTrigger.setValue(-2);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            skillTrigger.setValue(7);
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        try {
            skillTrigger.setValue(0);
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }
        // normal case
        try {
            skillTrigger.setValue(1);
        } catch (IllegalArgumentException exception) {
            test6 = true;
        }
        // normal case
        try {
            skillTrigger.setValue(3);
        } catch (IllegalArgumentException exception) {
            test7 = true;
        }
        // normal case
        try {
            skillTrigger.setValue(5);
        } catch (IllegalArgumentException exception) {
            test8 = true;
        }
        // normal case
        skillTrigger.setValue(2);
        if (skillTrigger.getValue() == 2) {
            test9 = true;
        }

        allTests = equalsTests && equalsSkillTriggerTests
            && hasPlaceholdersTests && test1 && test2 && test3 && test4
            && test5 && test6 && test7 && test8 && test9;
        
        return allTests;
    }
    /**
     * Tests SkillTrigger.equals(Object).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSkillTriggerEqualsTests() {
        SkillTrigger skillTrigger = new SkillTrigger();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;

        // normal case
        if (! skillTrigger.equals(null)) {
            test1 = true;
        }
        // normal case
        if (! skillTrigger.equals(new Object())) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests SkillTrigger.equals(SkillTrigger).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSkillTriggerEqualsSkillTriggerTests() {
        SkillTrigger skillTrigger = new SkillTrigger();
        SkillTrigger testSkillTrigger = new SkillTrigger("validName",
            2);
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean allTests = false;

        // normal case
        if (! skillTrigger.equals(null)) {
            test1 = true;
        }
        // normal case
        skillTrigger.setValue(2);
        if (! skillTrigger.equals(testSkillTrigger)) {
            test2 = true;
        }
        // normal case
        skillTrigger = new SkillTrigger();
        skillTrigger.setName("validName");
        if (! skillTrigger.equals(testSkillTrigger)) {
            test3 = true;
        }
        // normal case
        skillTrigger = new SkillTrigger("validName", 2);
        if (skillTrigger.equals(testSkillTrigger)) {
            test4 = true;
        }

        allTests = test1 && test2 && test3 && test4;

        return allTests;
    }
    /**
     * Tests SkillTrigger.hasPlaceholders().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSkillTriggerHasPlaceholdersTests() {
        SkillTrigger skillTrigger = new SkillTrigger();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean allTests = false;

        // normal case
        if (skillTrigger.hasPlaceholders()) {
            test1 = true;
        }
        // normal case
        skillTrigger.setValue(2);
        if (skillTrigger.hasPlaceholders()) {
            test2 = true;
        }
        // normal case
        skillTrigger = new SkillTrigger();
        skillTrigger.setName("validName");
        if (skillTrigger.hasPlaceholders()) {
            test3 = true;
        }
        // normal case
        skillTrigger = new SkillTrigger("validName", 2);
        if (! skillTrigger.hasPlaceholders()) {
            test4 = true;
        }

        allTests = test1 && test2 && test3 && test4;

        return allTests;
    }
    /**
     * Tests SkillTriggersList.equals(Object).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSkillTriggersListEqualsTests() {
        SkillTriggersList skillTriggersList = new SkillTriggersList();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;

        // normal case
        if (! skillTriggersList.equals(null)) {
            test1 = true;
        }
        // normal case
        if (! skillTriggersList.equals(new Object())) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests SkillTriggersList.equals(SkillTriggersList).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSkillTriggersListEqualsSkillTriggersListTests() {
        SkillTriggersList skillTriggersList = new SkillTriggersList();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;

        // normal case
        if (! skillTriggersList.equals(null)) {
            test1 = true;
        }
        // normal case
        if (skillTriggersList.equals(new SkillTriggersList())) {
            test2 = true;
        }
        // the rest is tested by SkillTrigger.equals(Object) and
        //     SkillTrigger.equals(SkillTrigger)

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests SkillTriggersList.generateOutput().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSkillTriggersListGenerateOutputTests() {
        SkillTriggersList skillTriggers = new SkillTriggersList();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean allTests = false;

        // normal case
        skillTriggers.setSkillTriggers(new SkillTrigger[0]);
        if (skillTriggers.generateOutput().equals("  N/A\n")) {
            test1 = true;
        }
        // normal case
        skillTriggers.setSkillTriggers(new SkillTrigger[] {
            new SkillTrigger("Apply Fists to Faces", 2)
        });
        String testString1 = "  Apply Fists to Faces (+2)\n";
        if (skillTriggers.generateOutput().equals(testString1)) {
            test2 = true;
        }
        // normal case
        skillTriggers.setSkillTriggers(new SkillTrigger[] {
            new SkillTrigger("Apply Fists to Faces", 2),
            new SkillTrigger("Assault", 4)
        });
        String testString2 = "  Apply Fists to Faces (+2), Assault (+4)\n";
        if (skillTriggers.generateOutput().equals(testString2)) {
            test3 = true;
        }
        // normal case
        skillTriggers.setSkillTriggers(new SkillTrigger[] {
            new SkillTrigger("Apply Fists to Faces", 2),
            new SkillTrigger("Assault", 4),
            new SkillTrigger("Blow Something Up", 6)
        });
        String testString3 = "  Apply Fists to Faces (+2), Assault (+4),\n"
            + "  Blow Something Up (+6)\n";
        if (skillTriggers.generateOutput().equals(testString3)) {
            test4 = true;
        }

        allTests = test1 && test2 && test3 && test4;

        return allTests;
    }
    /**
     * Tests SkillTriggersList.hasPlaceholders().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSkillTriggersListHasPlaceholdersTests() {
        SkillTriggersList skillTriggersList = new SkillTriggersList();
        boolean test1 = false;
        boolean allTests = false;

        // normal case
        if (! skillTriggersList.hasPlaceholders()) {
            test1 = true;
        }
        // the rest is tested by SkillTrigger.hasPlaceholders()

        allTests = test1;

        return allTests;
    }
    /**
     * Tests Pilot.setReserves().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetReservesTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean allTests = false;

        // normal case
        try {
            pilot.setReserves(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setReserves(new String[] {});
        if (pilot.getReserves().getClass() == String[].class
            && pilot.getReserves().length == 0) {
            test2 = true;
        }
        // normal case
        try {
            pilot.setReserves(new String[] {null});
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            pilot.setReserves(new String[] {"validReserve", null});
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        try {
            pilot.setReserves(new String[] {""});
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }
        // normal case
        try {
            pilot.setReserves(new String[] {"validReserve", ""});
        } catch (IllegalArgumentException exception) {
            test6 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5 && test6;

        return allTests;
    }
    /**
     * Tests Pilot.setLoadout().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetLoadoutTests() {
        Pilot pilot = new Pilot();
        boolean loadoutTests = false;
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;
        
        // Pilot.loadout // Loadout
        loadoutTests = runLoadoutTests();
        printStatement(loadoutTests, "Loadout", 3);

        // normal case
        try {
            pilot.setLoadout(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setLoadout(new Loadout());
        if (pilot.getLoadout().equals(new Loadout())) {
            test2 = true;
        }

        allTests = loadoutTests && test1 && test2;

        return allTests;
    }
    /**
     * Tests the Loadout class.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runLoadoutTests() {
        Loadout loadout = new Loadout();
        boolean equalsTests = false;
        boolean equalsLoadoutTests = false;
        boolean generateOutputTests = false;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean test7 = false;
        boolean test8 = false;
        boolean allTests = false;

        equalsTests = runLoadoutEqualsTests();
        printStatement(equalsTests, "Loadout.equals(Object)", 4);
        equalsLoadoutTests = runLoadoutEqualsLoadoutTests();
        printStatement(equalsLoadoutTests, "Loadout.equals(Loadout)",
            4);
        generateOutputTests = runLoadoutGenerateOutputTests();
        printStatement(generateOutputTests, "Loadout.generateOutput()",
            4);

        // normal case
        try {
            loadout.setPilotArmor(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        loadout.setPilotArmor("validArmor");
        if (loadout.getPilotArmor().equals("validArmor")) {
            test2 = true;
        }
        // normal case
        try {
            loadout.setPilotWeapons(null);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            loadout.setPilotWeapons(new String[] {"validWeapon"});
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        try {
            loadout.setPilotWeapons(new String[] {"validWeapon", null});
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }
        // normal case
        try {
            loadout.setPilotGear(null);
        } catch (IllegalArgumentException exception) {
            test6 = true;
        }
        // normal case
        try {
            loadout.setPilotGear(new String[] {"validGear"});
        } catch (IllegalArgumentException exception) {
            test7 = true;
        }
        // normal case
        try {
            loadout.setPilotGear(new String[] {"validGear", null, "validGear"});
        } catch (IllegalArgumentException exception) {
            test8 = true;
        }

        allTests = equalsTests && equalsLoadoutTests && generateOutputTests
            && test1 && test2 && test3 && test4 && test5 && test6 && test7
            && test8;
        
        return allTests;
    }
    /**
     * Tests Loadout.equals(Object).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runLoadoutEqualsTests() {
        Loadout loadout = new Loadout();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;

        // normal case
        if (! loadout.equals(null)) {
            test1 = true;
        }
        // normal case
        if (! loadout.equals(new Object())) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests Loadout.equals(Loadout).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runLoadoutEqualsLoadoutTests() {
        Loadout loadout = new Loadout();
        Loadout testLoadout = new Loadout("validArmor",
            new String[] {"validWeapon", "validWeapon"}, new String[] {
            "validGear", "validGear", "validGear"});
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean allTests = false;

        // normal case
        if (! loadout.equals(null)) {
            test1 = true;
        }
        // normal case
        loadout = new Loadout("", new String[] {"validWeapon",
            "validWeapon"}, new String[] {"validGear", "validGear",
            "validGear"});
        if (! loadout.equals(testLoadout)) {
            test2 = true;
        }
        // normal case
        loadout = new Loadout("validArmor", new String[] {"", ""},
            new String[] {"validGear", "validGear", "validGear"});
        if (! loadout.equals(testLoadout)) {
            test3 = true;
        }
        // normal case
        loadout = new Loadout("validArmor", new String[] {
                "validWeapon", "validWeapon"}, new String[] {"", "", ""});
        if (! loadout.equals(testLoadout)) {
            test4 = true;
        }
        // normal case
        loadout = new Loadout("validArmor", new String[] {
            "validWeapon", "validWeapon"}, new String[] {"validGear",
            "validGear", "validGear"});
        if (loadout.equals(testLoadout)) {
            test5 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5;

        return allTests;
    }
    /**
     * Tests Loadout.generateOutput().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runLoadoutGenerateOutputTests() {
        // TODO: fill out
        Loadout loadout = new Loadout("validArmor",
            new String[] {"validWeapon", "validWeapon"}, new String[] {
            "validGear", "validGear", "validGear"});
        boolean test1 = false;
        boolean allTests = false;

        // normal case
        String testString1 = "  N/A\n";
        if (loadout.generateOutput().equals(testString1)) {
            test1 = true;
        }

        allTests = test1;

        return allTests;
    }
    /**
     * Tests Pilot.setLicenseLevel().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetLicenseLevelTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        // normal case
        try {
            pilot.setLicenseLevel(-2);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setLicenseLevel(13);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        pilot.setLicenseLevel(1);
        if (pilot.getLicenseLevel() == 1) {
            test3 = true;
        }

        allTests = test1 && test2 && test3;

        return allTests;
    }
    /**
     * Tests Pilot.setLicenseList().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetLicenseListTests() {
        Pilot pilot = new Pilot();
        License license;
        boolean licenseTests = false;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean allTests = false;

        // Pilot.licenseList[] // License
        licenseTests = runLicenseTests();
        printStatement(licenseTests, "License", 4);

        // normal case
        try {
            pilot.setLicenseList(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setLicenseList(new License[] {});
        if (pilot.getLicenseList().getClass() == License[].class
            && pilot.getLicenseList().length == 0) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setLicenseList(new License[] {
                null
            });
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        try {
            pilot.setLicenseList(new License[] {
                new License()
            });
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        license = new License();
        license.setFrame("IPS-N Blackbeard");
        try {
            pilot.setLicenseList(new License[] {
                license
            });
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        license = new License("", 2);
        try {
            pilot.setLicenseList(new License[] {
                license
            });
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }
        // normal case
        license = new License("validFrame", 2);
        pilot.setLicenseList(new License[] {
            license
        });
        if (pilot.getLicenseList()[0].equals(license)) {
            test6 = true;
        }

        allTests = licenseTests && test1 && test2 && test3 && test4 && test5
            && test6;

        return allTests;
    }
    /**
     * Tests License.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runLicenseTests() {
        License license = new License();
        boolean equalsTests = false;
        boolean equalsLicenseTests = false;
        boolean hasPlaceholdersTests = false;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean allTests = false;

        equalsTests = runLicenseEqualsTests();
        printStatement(equalsTests, "License.equals(Object)",
            5);
        equalsLicenseTests = runLicenseEqualsLicenseTests();
        printStatement(equalsLicenseTests, "License.equals(License)",
            5);
        hasPlaceholdersTests = runLicenseHasPlaceholdersTests();
        printStatement(hasPlaceholdersTests, "License.hasPlaceholders()",
            5);


        // normal case
        try {
            license.setFrame(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        license.setFrame("validFrame");
        if (license.getFrame().equals("validFrame")) {
            test2 = true;
        }
        // normal case
        license = new License();
        try {
            license.setLicenseLevel(-2);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            license.setLicenseLevel(0);
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        try {
            license.setLicenseLevel(4);
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }
        // normal case
        license.setLicenseLevel(-1);
        if (license.getLicenseLevel() == -1) {
            test6 = true;
        }

        allTests = equalsTests && equalsLicenseTests && hasPlaceholdersTests
            && test1 && test2 && test3 && test4 && test5 && test6;

        return allTests;
    }
    /**
     * Tests License.equals(Object).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runLicenseEqualsTests() {
        License license = new License();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;

        // normal case
        if (! license.equals(null)) {
            test1 = true;
        }
        // normal case
        if (! license.equals(new Object())) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests License.equals(License).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runLicenseEqualsLicenseTests() {
        License license = new License();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean allTests = false;

        // normal case
        if (! license.equals(null)) {
            test1 = true;
        }
        // normal case
        if (! license.equals(new License("validFrame", -1))) {
            test2 = true;
        }
        // normal case
        if (! license.equals(new License("", 2))) {
            test3 = true;
        }
        // normal case
        license.setFrame("validFrame");
        license.setLicenseLevel(2);
        if (license.equals(new License("validFrame", 2))) {
            test4 = true;
        }

        allTests = test1 && test2 && test3 && test4;

        return allTests;
    }
    /**
     * Tests License.hasPlaceholders().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runLicenseHasPlaceholdersTests() {
        License license = new License();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        // normal case
        license.setLicenseLevel(2);
        if (license.hasPlaceholders()) {
            test1 = true;
        }
        // normal case
        license = new License();
        license.setFrame("validFrame");
        if (license.hasPlaceholders()) {
            test2 = true;
        }
        // normal case
        license = new License("validFrame", 2);
        if (! license.hasPlaceholders()) {
            test3 = true;
        }

        allTests = test1 && test2 && test3;

        return allTests;
    }
    /**
     * Tests Pilot.setSpecialEquipment().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetSpecialEquipmentTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean allTests = false;

        // normal case
        try {
            pilot.setSpecialEquipment(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setSpecialEquipment(new String[] {});
        if (pilot.getSpecialEquipment().getClass() == String[].class
            && pilot.getSpecialEquipment().length == 0) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot();
        try {
            pilot.setSpecialEquipment(new String[] {"validEquipment", null});
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            pilot.setSpecialEquipment(new String[] {"validEquipment", ""});
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        pilot.setSpecialEquipment(new String[] {"validEquipment"});
        if (pilot.getSpecialEquipment()[0].equals("validEquipment")) {
            test5 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5;

        return allTests;
    }
    /**
     * Tests Pilot.setMechSkills().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetMechSkillsTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean allTests = false;

        // normal case
        try {
            pilot.setMechSkills(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setMechSkills(new int[] {
                0
            });
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        try {
            pilot.setMechSkills(new int[] {0, 0, -1, 0});
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            pilot.setMechSkills(new int[] {0, 0, 7, 0});
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }

        allTests = test1 && test2 && test3 && test4;

        return allTests;
    }
    /**
     * Tests Pilot.setCoreBonuses().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetCoreBonusesTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean test7 = false;
        boolean allTests = false;

        // normal case
        try {
            pilot.setCoreBonuses(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setCoreBonuses(new String[] {});
        if (pilot.getCoreBonuses().getClass() == String[].class
            && pilot.getCoreBonuses().length == 0) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot();
        try {
            pilot.setCoreBonuses(new String[] {null});
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            pilot.setCoreBonuses(new String[] {
                "validCoreBonus",
                null
            });
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        try {
            pilot.setCoreBonuses(new String[] {
                ""
            });
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }
        // normal case
        try {
            pilot.setCoreBonuses(new String[] {
                "Auto-Stabilizing Hardpoints",
                ""
            });
        } catch (IllegalArgumentException exception) {
            test6 = true;
        }
        // normal case
        pilot.setCoreBonuses(new String[] {
            "Auto-Stabilizing Hardpoints"
        });
        if (pilot.getCoreBonuses()[0].equals(
            "Auto-Stabilizing Hardpoints")) {
            test7 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5 && test6 && test7;

        return allTests;
    }
    /**
     * Tests Pilot.setTalents().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetTalentsTests() {
        Pilot pilot;
        Talent talent;
        boolean talentTests = false;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean test7 = false;
        boolean allTests = false;

        // Pilot.talents[] // Talent
        talentTests = runTalentTests();
        printStatement(talentTests, "Talent", 4);

        // normal case
        pilot = new Pilot();
        try {
            pilot.setTalents(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setTalents(new Talent[] {});
        if (pilot.getTalents().getClass() == Talent[].class
            && pilot.getTalents().length == 0) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot();
        try {
            pilot.setTalents(new Talent[] {null});
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            pilot.setTalents(new Talent[] {
                new Talent(),
                null
            });
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        talent = new Talent();
        talent.setName("");
        try {
            pilot.setTalents(new Talent[] {
                talent
            });
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }
        // normal case
        talent = new Talent();
        talent.setLevel(-1);
        try {
            pilot.setTalents(new Talent[] {
                talent
            });
        } catch (IllegalArgumentException exception) {
            test6 = true;
        }
        // normal case
        talent = new Talent();
        talent = new Talent("Ace", 1);
        pilot.setTalents(new Talent[] {
            talent
        });
        if (pilot.getTalents()[0].equals(talent)) {
            test7 = true;
        }

        allTests = talentTests && test1 && test2 && test3 && test4 && test5
            && test6 && test7;

        return allTests;
    }
    /**
     * Tests Talent.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runTalentTests() {
        Talent talent = new Talent();
        boolean equalsTests = false;
        boolean equalsTalentTests = false;
        boolean hasPlaceholdersTests = false;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean allTests = false;

        equalsTests = runTalentEqualsTests();
        printStatement(equalsTests, "Talent.equals(Object)", 5);
        equalsTalentTests = runTalentEqualsTalentTests();
        printStatement(equalsTalentTests, "Talent.equals(Talent)",
            5);
        hasPlaceholdersTests = runTalentHasPlaceholdersTests();
        printStatement(hasPlaceholdersTests, "Talent.hasPlaceholders()",
            5);

        // normal case
        try {
            talent.setName(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        talent.setName("");
        if (talent.getName().equals("")) {
            test2 = true;
        }
        // normal case
        talent = new Talent();
        try {
            talent.setLevel(-2);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            talent.setLevel(4);
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        try {
            talent.setLevel(0);
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }
        // normal case
        talent.setLevel(-1);
        if (talent.getLevel() == -1) {
            test6 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5 && test6;

        return allTests;
    }
    /**
     * Tests Talent.equals(Object).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runTalentEqualsTests() {
        Talent talent = new Talent();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;

        // normal case
        if (! talent.equals(null)) {
            test1 = true;
        }
        // normal case
        if (! talent.equals(new Object())) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests Talent.equals(Talent).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runTalentEqualsTalentTests() {
        Talent talent = new Talent();
        Talent testTalent = new Talent("validName", 1);
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean allTests = false;

        // normal case
        if (! talent.equals(null)) {
            test1 = true;
        }
        // normal case
        if (! talent.equals(testTalent)) {
            test2 = true;
        }
        // normal case
        talent.setLevel(1);
        if (! talent.equals(testTalent)) {
            test3 = true;
        }
        // normal case
        talent = new Talent();
        talent.setName("validName");
        if (! talent.equals(testTalent)) {
            test4 = true;
        }
        // normal case
        talent = new Talent("validName", 1);
        if (talent.equals(testTalent)) {
            test5 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5;

        return allTests;
    }
    /**
     * Tests Talent.hasPlaceholders().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runTalentHasPlaceholdersTests() {
        Talent talent = new Talent();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        // normal case
        talent.setLevel(1);
        if (talent.hasPlaceholders()) {
            test1 = true;
        }
        // normal case
        talent = new Talent();
        talent.setName("validName");
        if (talent.hasPlaceholders()) {
            test2 = true;
        }
        // normal case
        talent = new Talent("validName", 1);
        if (! talent.hasPlaceholders()) {
            test3 = true;
        }

        allTests = test1 && test2 && test3;

        return allTests;
    }
    /**
     * Tests Pilot.generateOutput().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runPilotGenerateOutputTests() {
        boolean pilotGenerateMechOutputTests = false;
        boolean pilotGeneratePilotOutputTests = false;
        boolean pilotGenerateFullOutputTests = false;
        boolean allTests = false;

        pilotGenerateMechOutputTests = runPilotGenerateMechOutputTests();
        printStatement(pilotGenerateMechOutputTests,
            "Pilot.generateOutput(\"mech build\")", 3);
        pilotGeneratePilotOutputTests = runPilotGeneratePilotOutputTests();
        printStatement(pilotGeneratePilotOutputTests,
            "Pilot.generateOutput(\"pilot\")", 3);
        pilotGenerateFullOutputTests = runPilotGenerateFullOutputTests();
        printStatement(pilotGenerateFullOutputTests,
            "Pilot.generateOutput(\"full\")", 3);

        allTests = pilotGenerateMechOutputTests && pilotGeneratePilotOutputTests
            && pilotGenerateFullOutputTests;


        return allTests;
    }
    /**
     * Tests Pilot.generateOutput("mech build").
     * @return a boolean representing whether the function passed.
     */
    private static boolean runPilotGenerateMechOutputTests() {
        Pilot pilot = new Pilot();
        License[] licenseList;
        String[] coreBonuses;
        Talent[] talents;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean test7 = false;
        boolean allTests = false;
        
        licenseList = new License[] {
            new License("IPS-N Blackbeard", 1),
            new License("IPS-N Drake", 2),
            new License("IPS-N Lancaster", 3)
        };
        coreBonuses = new String[] {
            "Auto-Stabilizing Hardpoints",
            "Overpower Caliber",
            "Improved Armament"
        };
        talents = new Talent[] {
            new Talent("Ace", 1),
            new Talent("Bonded", 2),
            new Talent("Brawler", 3)
        };
        // mech build
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(talents);
        String testString1 = "[ LICENSES ]\n  IPS-N Blackbeard 1,"
            + " IPS-N Drake 2, IPS-N Lancaster 3\n[ CORE BONUSES ]\n"
            + "  Auto-Stabilizing Hardpoints, Overpower Caliber, Improved"
            + " Armament\n[ TALENTS ]\n  Ace 1, Bonded 2, Brawler 3\n"
            + "[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0";
        if (pilot.generateOutput("mech build").equals(testString1)) {
            test1 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(new Talent[0]);
        String testString2 = "[ LICENSES ]\n  IPS-N Blackbeard 1,"
            + " IPS-N Drake 2, IPS-N Lancaster 3\n[ CORE BONUSES ]\n"
            + "  Auto-Stabilizing Hardpoints, Overpower Caliber, Improved"
            + " Armament\n[ TALENTS ]\n  N/A\n[ STATS ]\n  HULL:0 AGI:0 SYS:0"
            + " ENGI:0";
        if (pilot.generateOutput("mech build").equals(testString2)) {
            test2 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(talents);
        String testString3 = "[ LICENSES ]\n  IPS-N Blackbeard 1,"
            + " IPS-N Drake 2, IPS-N Lancaster 3\n[ CORE BONUSES ]\n  N/A\n"
            + "[ TALENTS ]\n  Ace 1, Bonded 2, Brawler 3\n[ STATS ]\n  HULL:0"
            + " AGI:0 SYS:0 ENGI:0";
        if (pilot.generateOutput("mech build").equals(testString3)) {
            test3 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(new Talent[0]);
        String testString4 = "[ LICENSES ]\n  IPS-N Blackbeard 1,"
            + " IPS-N Drake 2, IPS-N Lancaster 3\n[ CORE BONUSES ]\n  N/A\n"
            + "[ TALENTS ]\n  N/A\n[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0";
        if (pilot.generateOutput("mech build").equals(testString4)) {
            test4 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(talents);
        String testString5 = "[ LICENSES ]\n  N/A\n[ CORE BONUSES ]\n"
            + "  Auto-Stabilizing Hardpoints, Overpower Caliber, Improved"
            + " Armament\n[ TALENTS ]\n  Ace 1, Bonded 2, Brawler 3\n"
            + "[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0";
        if (pilot.generateOutput("mech build").equals(testString5)) {
            test5 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(new Talent[0]);
        String testString6 = "[ LICENSES ]\n  N/A\n[ CORE BONUSES ]\n"
            + "  Auto-Stabilizing Hardpoints, Overpower Caliber, Improved"
            + " Armament\n[ TALENTS ]\n  N/A\n[ STATS ]\n  HULL:0 AGI:0 SYS:0"
            + " ENGI:0";
        if (pilot.generateOutput("mech build").equals(testString6)) {
            test6 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(new Talent[0]);
        String testString7 = "[ LICENSES ]\n  N/A\n[ CORE BONUSES ]\n"
            + "  N/A\n[ TALENTS ]\n  N/A\n[ STATS ]\n  HULL:0 AGI:0 SYS:0"
            + " ENGI:0";
        if (pilot.generateOutput("mech build").equals(testString7)) {
            test7 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5 && test6 && test7;

        return allTests;
    }
    /**
     * Tests Pilot.generateOutput("pilot").
     * @return a boolean representing whether the function passed.
     */
    private static boolean runPilotGeneratePilotOutputTests() {
        Pilot pilot = new Pilot();
        License[] licenseList;
        String[] coreBonuses;
        Talent[] talents;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean test7 = false;
        boolean allTests = false;
        
        licenseList = new License[] {
            new License("IPS-N Blackbeard", 1),
            new License("IPS-N Drake", 2),
            new License("IPS-N Lancaster", 3)
        };
        coreBonuses = new String[] {
            "Auto-Stabilizing Hardpoints",
            "Overpower Caliber",
            "Improved Armament"
        };
        talents = new Talent[] {
            new Talent("Ace", 1),
            new Talent("Bonded", 2),
            new Talent("Brawler", 3)
        };
        // pilot
        pilot.setName("Name");
        pilot.setCallsign("Callsign");
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(talents);

        String testString1 = "» Name // CALLSIGN «\n  LL0\n[ SKILL TRIGGERS ]\n"
            + "  N/A\n[ GEAR ]\n  N/A\n[ MECH SKILLS ]\n  GRIT:0 // H:0 A:0 S:0"
            + " E:0\n[ TALENTS ]\n  Ace 1, Bonded 2,\n  Brawler 3\n"
            + "[ LICENSES ]\n  IPS-N Blackbeard 1, IPS-N Drake 2,\n  IPS-N"
            + " Lancaster 3\n[ CORE BONUSES ]\n  Auto-Stabilizing Hardpoints,"
            + " Overpower Caliber,\n  Improved Armament\n";
        if (pilot.generateOutput("pilot").equals(testString1)) {
            test1 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(new Talent[0]);
        String testString2 = "» Name // CALLSIGN «\n  LL0\n[ SKILL TRIGGERS ]\n"
            + "  N/A\n[ GEAR ]\n  N/A\n[ MECH SKILLS ]\n  GRIT:0 // H:0 A:0 S:0"
            + " E:0\n[ TALENTS ]\n  N/A\n[ LICENSES ]\n  IPS-N Blackbeard 1,"
            + " IPS-N Drake 2,\n  IPS-N Lancaster 3\n[ CORE BONUSES ]\n"
            + "  Auto-Stabilizing Hardpoints, Overpower Caliber,\n  Improved"
            + " Armament\n";

        if (pilot.generateOutput("pilot").equals(testString2)) {
            test2 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(talents);
        String testString3 = "» Name // CALLSIGN «\n  LL0\n[ SKILL TRIGGERS ]\n"
            + "  N/A\n[ GEAR ]\n  N/A\n[ MECH SKILLS ]\n  GRIT:0 // H:0 A:0 S:0"
            + " E:0\n[ TALENTS ]\n  Ace 1, Bonded 2,\n  Brawler 3\n"
            + "[ LICENSES ]\n  IPS-N Blackbeard 1, IPS-N Drake 2,\n  IPS-N"
            + " Lancaster 3\n[ CORE BONUSES ]\n  N/A\n";
        if (pilot.generateOutput("pilot").equals(testString3)) {
            test3 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(new Talent[0]);
        String testString4 = "» Name // CALLSIGN «\n  LL0\n[ SKILL TRIGGERS ]\n"
            + "  N/A\n[ GEAR ]\n  N/A\n[ MECH SKILLS ]\n  GRIT:0 // H:0 A:0 S:0"
            + " E:0\n[ TALENTS ]\n  N/A\n[ LICENSES ]\n  IPS-N Blackbeard 1,"
            + " IPS-N Drake 2,\n  IPS-N Lancaster 3\n[ CORE BONUSES ]\n  N/A\n";
        if (pilot.generateOutput("pilot").equals(testString4)) {
            test4 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(talents);
        String testString5 = "» Name // CALLSIGN «\n  LL0\n[ SKILL TRIGGERS ]\n"
            + "  N/A\n[ GEAR ]\n  N/A\n[ MECH SKILLS ]\n  GRIT:0 // H:0 A:0 S:0"
            + " E:0\n[ TALENTS ]\n  Ace 1, Bonded 2,\n  Brawler 3\n"
            + "[ LICENSES ]\n  N/A\n[ CORE BONUSES ]\n  Auto-Stabilizing"
            + " Hardpoints, Overpower Caliber,\n  Improved Armament\n";
        if (pilot.generateOutput("pilot").equals(testString5)) {
            test5 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(new Talent[0]);
        String testString6 = "» Name // CALLSIGN «\n  LL0\n[ SKILL TRIGGERS ]\n"
            + "  N/A\n[ GEAR ]\n  N/A\n[ MECH SKILLS ]\n  GRIT:0 // H:0 A:0 S:0"
            + " E:0\n[ TALENTS ]\n  N/A\n[ LICENSES ]\n  N/A\n"
            + "[ CORE BONUSES ]\n  Auto-Stabilizing Hardpoints, Overpower"
            + " Caliber,\n  Improved Armament\n";
        if (pilot.generateOutput("pilot").equals(testString6)) {
            test6 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(new Talent[0]);
        String testString7 = "» Name // CALLSIGN «\n  LL0\n[ SKILL TRIGGERS ]\n"
            + "  N/A\n[ GEAR ]\n  N/A\n[ MECH SKILLS ]\n  GRIT:0 // H:0 A:0 S:0"
            + " E:0\n[ TALENTS ]\n  N/A\n[ LICENSES ]\n  N/A\n"
            + "[ CORE BONUSES ]\n  N/A\n";
        if (pilot.generateOutput("pilot").equals(testString7)) {
            test7 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5 && test6 && test7;

        return allTests;
    }
    /**
     * Tests Pilot.generateOutput("full").
     * @return a boolean representing whether the function passed.
     */
    private static boolean runPilotGenerateFullOutputTests() {
        Pilot pilot = new Pilot();
        License[] licenseList;
        String[] coreBonuses;
        Talent[] talents;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean test7 = false;
        boolean allTests = false;
        
        licenseList = new License[] {
            new License("IPS-N Blackbeard", 1),
            new License("IPS-N Drake", 2),
            new License("IPS-N Lancaster", 3)
        };
        coreBonuses = new String[] {
            "Auto-Stabilizing Hardpoints",
            "Overpower Caliber",
            "Improved Armament"
        };
        talents = new Talent[] {
            new Talent("Ace", 1),
            new Talent("Bonded", 2),
            new Talent("Brawler", 3)
        };
        // full
        pilot.setName("Name");
        pilot.setCallsign("Callsign");
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(talents);

        String testString1 = "» Name // CALLSIGN «\n  LL0\n[ SKILL TRIGGERS ]\n"
            + "  N/A\n[ GEAR ]\n  N/A\n***\n[ TALENTS ]\n  Ace 1, Bonded 2,\n"
            + "  Brawler 3\n[ LICENSES ]\n  IPS-N Blackbeard 1,"
            + " IPS-N Drake 2,\n  IPS-N Lancaster 3\n[ CORE BONUSES ]\n"
            + "  Auto-Stabilizing Hardpoints, Overpower Caliber,\n  Improved"
            + " Armament\n";
        if (pilot.generateOutput("full").equals(testString1)) {
            test1 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(new Talent[0]);
        String testString2 = "» Name // CALLSIGN «\n  LL0\n[ SKILL TRIGGERS ]\n"
            + "  N/A\n[ GEAR ]\n  N/A\n***\n[ TALENTS ]\n  N/A\n[ LICENSES ]\n"
            + "  IPS-N Blackbeard 1, IPS-N Drake 2,\n  IPS-N Lancaster 3\n"
            + "[ CORE BONUSES ]\n  Auto-Stabilizing Hardpoints, Overpower"
            + " Caliber,\n  Improved Armament\n";
        if (pilot.generateOutput("full").equals(testString2)) {
            test2 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(talents);
        String testString3 = "» Name // CALLSIGN «\n  LL0\n[ SKILL TRIGGERS ]\n"
            + "  N/A\n[ GEAR ]\n  N/A\n***\n[ TALENTS ]\n  Ace 1, Bonded 2,\n"
            + "  Brawler 3\n[ LICENSES ]\n  IPS-N Blackbeard 1,"
            + " IPS-N Drake 2,\n  IPS-N Lancaster 3\n[ CORE BONUSES ]\n  N/A\n";
        if (pilot.generateOutput("full").equals(testString3)) {
            test3 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(new Talent[0]);
        String testString4 = "» Name // CALLSIGN «\n  LL0\n[ SKILL TRIGGERS ]\n"
            + "  N/A\n[ GEAR ]\n  N/A\n***\n[ TALENTS ]\n  N/A\n[ LICENSES ]\n"
            + "  IPS-N Blackbeard 1, IPS-N Drake 2,\n  IPS-N Lancaster 3\n"
            + "[ CORE BONUSES ]\n  N/A\n";
        if (pilot.generateOutput("full").equals(testString4)) {
            test4 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(talents);
        String testString5 = "» Name // CALLSIGN «\n  LL0\n[ SKILL TRIGGERS ]\n"
            + "  N/A\n[ GEAR ]\n  N/A\n***\n[ TALENTS ]\n  Ace 1, Bonded 2,\n"
            + "  Brawler 3\n[ LICENSES ]\n  N/A\n[ CORE BONUSES ]\n"
            + "  Auto-Stabilizing Hardpoints, Overpower Caliber,\n"
            + "  Improved Armament\n";
        if (pilot.generateOutput("full").equals(testString5)) {
            test5 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(new Talent[0]);
        String testString6 = "» Name // CALLSIGN «\n  LL0\n[ SKILL TRIGGERS ]\n"
            + "  N/A\n[ GEAR ]\n  N/A\n***\n[ TALENTS ]\n  N/A\n[ LICENSES ]\n"
            + "  N/A\n[ CORE BONUSES ]\n  Auto-Stabilizing Hardpoints,"
            + " Overpower Caliber,\n  Improved Armament\n";
        if (pilot.generateOutput("full").equals(testString6)) {
            test6 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(new Talent[0]);
        String testString7 = "» Name // CALLSIGN «\n  LL0\n[ SKILL TRIGGERS ]\n"
            + "  N/A\n[ GEAR ]\n  N/A\n***\n[ TALENTS ]\n  N/A\n[ LICENSES ]\n"
            + "  N/A\n[ CORE BONUSES ]\n  N/A\n";
        if (pilot.generateOutput("full").equals(testString7)) {
            test7 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5 && test6 && test7;

        return allTests;
    }
    /**
     * Tests Pilot.outputLicenses().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runOutputLicensesTests() {
        Pilot pilot = new Pilot();
        License[] licenseList;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        licenseList = new License[] {
            new License("IPS-N Blackbeard", 1),
            new License("IPS-N Drake", 2),
            new License("IPS-N Lancaster", 3)
        };
        // normal case
        pilot.setLicenseList(new License[0]);
        if (pilot.outputLicenses("mech build").equals(
            "  N/A\n")) {
            test1 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setLicenseList(licenseList);
        String testString1 = "  IPS-N Blackbeard 1, IPS-N Drake 2,"
            + " IPS-N Lancaster 3\n";
        if (pilot.outputLicenses("mech build").equals(testString1)) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setLicenseList(licenseList);
        String testString2 = "  IPS-N Blackbeard 1, IPS-N Drake 2,\n"
            + "  IPS-N Lancaster 3\n";
        if (pilot.outputLicenses("pilot").equals(testString2)) {
            test3 = true;
        }

        allTests = test1 && test2 && test3;

        return allTests;
    }
    /**
     * Tests Pilot.outputTalents().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runOutputTalentsTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean allTests = false;

        // normal case
        pilot.setTalents(new Talent[0]);
        if (pilot.outputTalents("mech build").equals(
            "  N/A\n")) {
            test1 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setTalents(new Talent[0]);
        if (pilot.outputTalents("pilot").equals(
            "  N/A\n")) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setTalents(new Talent[] {
            new Talent("Ace", 1),
            new Talent("Brawler", 1),
            new Talent("Bonded", 1)
        });
        if (pilot.outputTalents("mech build").equals(
            "  Ace 1, Brawler 1, Bonded 1\n")) {
            test3 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setTalents(new Talent[] {
            new Talent("Ace", 1),
            new Talent("Brawler", 1),
            new Talent("Bonded", 1)
        });
        if (pilot.outputTalents("pilot").equals(
            "  Ace 1, Brawler 1,\n  Bonded 1\n")) {
            test4 = true;
        }

        allTests = test1 && test2 && test3 && test4;

        return allTests;
    }
    /**
     * Tests Pilot.outputCoreBonuses().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runOutputCoreBonusesTests() {
        Pilot pilot = new Pilot();
        String[] coreBonuses;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        coreBonuses = new String[] {
            "Auto-Stabilizing Hardpoints",
            "Overpower Caliber",
            "Improved Armament"
        };
        // normal case
        pilot.setCoreBonuses(new String[0]);
        if (pilot.outputCoreBonuses("mech build").equals(
            "  N/A\n")) {
            test1 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setCoreBonuses(coreBonuses);
        String testString1 = "  Auto-Stabilizing Hardpoints, Overpower Caliber,"
            + " Improved Armament\n";
        if (pilot.outputCoreBonuses("mech build").equals(
            testString1)) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setCoreBonuses(coreBonuses);
        String testString2 = "  Auto-Stabilizing Hardpoints,"
            + " Overpower Caliber,\n  Improved Armament\n";
        if (pilot.outputCoreBonuses("pilot").equals(testString2)) {
            test3 = true;
        }

        allTests = test1 && test2 && test3;

        return allTests;
    }
    /**
     * Tests Mech.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runMechTests() {
        // TODO: fill out
        return false;
    }
}
