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
    public static void printStatement(boolean testPassed, String output, int level) {
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
        } else if (inputString.indexOf("FAIL") != -1 && inputString.indexOf("FAILED") == -1) {
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
     * @return a boolean representing whether the class and its dependencies passed.
     */
    private static boolean runPilotTests() {
        boolean skillTriggersListTests = false;
        boolean loadoutTests = false;
        boolean licenseTests = false;
        boolean setNameTests = false;
        boolean setCallsignTests = false;
        boolean setPlayerTests = false;
        boolean setStatusTests = false;
        boolean setBackgroundTests = false;
        boolean setBiographyTests = false;
        boolean setAppearanceTests = false;
        boolean setPlayerNotesTests = false;
        boolean setGritTests = false;
        boolean setPilotCurrentHPTests = false;
        boolean setPilotMaxHPTests = false;
        boolean setPilotArmorTests = false;
        boolean setPilotEvasionTests = false;
        boolean setPilotSpeedTests = false;
        boolean setPilotEDefenseTests = false;
        boolean setSkillTriggersTests = false;
        boolean setReservesTests = false;
        boolean setGearLoadoutTests = false;
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
        
        // Pilot.skillTriggers // SkillTriggersList
        skillTriggersListTests = runSkillTriggersListTests();
        printStatement(skillTriggersListTests, "SkillTriggersList", 3);
        // Pilot.gearLoadout // Loadout
        loadoutTests = runLoadoutTests();
        printStatement(loadoutTests, "Loadout", 3);
        
        // Pilot.setName()
        setNameTests = runSetPilotNameTests();
        // Pilot.setCallsign()
        setCallsignTests = runSetCallsignTests();
        // Pilot.setPlayer()
        setPlayerTests = runSetPlayerTests();
        // Pilot.setStatus()
        setStatusTests = runSetStatusTests();
        // Pilot.setBackground()
        setBackgroundTests = runSetBackgroundTests();
        // Pilot.setBiography()
        setBiographyTests = runSetBiographyTests();
        // Pilot.setAppearance()
        setAppearanceTests = runSetAppearanceTests();
        // Pilot.setPlayerNotes()
        setPlayerNotesTests = runSetPlayerNotesTests();
        // Pilot.setGrit()
        setGritTests = runSetGritTests();
        // Pilot.setCurrentHP()
        setPilotCurrentHPTests = runSetPilotCurrentHPTests();
        // Pilot.setMaxHP()
        setPilotMaxHPTests = runSetPilotMaxHPTests();
        // Pilot.setArmor()
        setPilotArmorTests = runSetPilotArmorTests();
        // Pilot.setEvasion()
        setPilotEvasionTests = runSetPilotEvasionTests();
        // Pilot.setSpeed()
        setPilotSpeedTests = runSetPilotSpeedTests();
        // Pilot.setEDefense()
        setPilotEDefenseTests = runSetPilotEDefenseTests();
        // Pilot.setSkillTriggers()
        setSkillTriggersTests = runSetPilotSkillTriggersTests();
        // Pilot.setReserves()
        setReservesTests = runSetReservesTests();
        // Pilot.setGearLoadout()
        setGearLoadoutTests = runSetLoadoutTests();
        // Pilot.setLicenseLevel()
        setLicenseLevelTests = runSetLicenseLevelTests();
        // Pilot.setLicenseList()
        setLicenseListTests = runSetLicenseListTests();
        // Pilot.setSpecialEquipment()
        setSpecialEquipmentTests = runSetSpecialEquipmentTests();
        // Pilot.setMechSkills()
        setMechSkillsTests = runSetMechSkillsTests();
        // Pilot.setCoreBonuses()
        setCoreBonusesTests = runSetCoreBonusesTests();
        // Pilot.setTalents()
        setTalentsTests = runSetTalentsTests();
        // Pilot.generateOutput()
        generateOutputTests = runPilotGenerateOutputTests();
        // Pilot.outputLicenses()
        outputLicensesTests = runOutputLicensesTests();
        // Pilot.outputTalents()
        outputTalentsTests = runOutputTalentsTests();
        // Pilot.outputCoreBonuses()
        outputCoreBonusesTests = runOutputCoreBonusesTests();

        allTests = skillTriggersListTests && loadoutTests && licenseTests
            && setNameTests && setCallsignTests && setPlayerTests
            && setStatusTests && setBackgroundTests && setBiographyTests
            && setAppearanceTests && setPlayerNotesTests && setGritTests
            && setPilotCurrentHPTests && setPilotMaxHPTests
            && setPilotArmorTests && setPilotEvasionTests && setPilotSpeedTests
            && setPilotEDefenseTests && setSkillTriggersTests
            && setReservesTests && setGearLoadoutTests && setLicenseLevelTests
            && setLicenseListTests && setSpecialEquipmentTests
            && setMechSkillsTests && setCoreBonusesTests && setTalentsTests
            && generateOutputTests && outputLicensesTests && outputTalentsTests
            && outputCoreBonusesTests;
        printStatement(setNameTests, "Pilot.setName()", 2);
        printStatement(setCallsignTests, "Pilot.setCallsign()", 2);
        printStatement(setPlayerTests, "Pilot.setPlayer()", 2);
        printStatement(setStatusTests, "Pilot.setStatus()", 2);
        printStatement(setBiographyTests, "Pilot.setBiography()", 2);
        printStatement(setAppearanceTests, "Pilot.setAppearance()", 2);
        printStatement(setPlayerNotesTests, "Pilot.setPlayerNotes()", 2);
        printStatement(setGritTests, "Pilot.setGrit()", 2);
        printStatement(setPilotCurrentHPTests, "Pilot.setCurrentHP()", 2);
        printStatement(setPilotMaxHPTests, "Pilot.setMaxHP()", 2);
        printStatement(setPilotArmorTests, "Pilot.setArmor()", 2);
        printStatement(setPilotEvasionTests, "Pilot.setEvasion()", 2);
        printStatement(setPilotSpeedTests, "Pilot.setSpeed()", 2);
        printStatement(setPilotEDefenseTests, "Pilot.setEDefense()", 2);
        printStatement(setSkillTriggersTests, "Pilot.setSkillTriggers()", 2);
        printStatement(setReservesTests, "Pilot.setReserves()", 2);
        printStatement(setGearLoadoutTests, "Pilot.setGearLoadout()", 2);
        printStatement(setLicenseLevelTests, "Pilot.setLicenseLevel()", 2);
        printStatement(setLicenseListTests, "Pilot.setLicenseList()", 2);
        printStatement(setSpecialEquipmentTests, "Pilot.setSpecialEquipment()", 2);
        printStatement(setMechSkillsTests, "Pilot.setMechSkills()", 2);
        printStatement(setCoreBonusesTests, "Pilot.setCoreBonuses()", 2);
        printStatement(setTalentsTests, "Pilot.setTalents()", 2);
        printStatement(generateOutputTests, "Pilot.generateOutput()", 2);
        printStatement(outputLicensesTests, "Pilot.outputLicenses()", 2);
        printStatement(outputTalentsTests, "Pilot.outputTalents()", 2);
        printStatement(outputCoreBonusesTests, "Pilot.outputCoreBonuses()", 2);

        return allTests;
    }
    /**
     * Tests the SkillTriggersList class, all its functions, and all its dependencies.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runSkillTriggersListTests() {
        boolean skillTriggerTests = false;                                                                                                                                                                                                                                                                           
        boolean setSkillTriggersTests = false;
        boolean generateOutputTests = false;
        boolean allTests = false;

        skillTriggerTests = runSkillTriggerTests();
        setSkillTriggersTests = runSetSkillTriggersTests();
        generateOutputTests = runSkillTriggersListGenerateOutputTests();
        allTests = skillTriggerTests && setSkillTriggersTests && generateOutputTests;
        printStatement(skillTriggerTests, "SkillTrigger", 4);
        printStatement(setSkillTriggersTests, "SkillTriggersList.setSkillTriggers()", 4);
        printStatement(generateOutputTests, "SkillTriggersList.generateOutput()", 4);
        
        return allTests;
    }
    /**
     * Tests the SkillTrigger class and all its functions.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runSkillTriggerTests() {
        SkillTrigger skillTrigger = new SkillTrigger();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean test7 = false;
        boolean test8 = false;
        boolean allTests = false;

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

        allTests = test1 && test2 && test3 && test4 && test5 && test6 && test7
            && test8;
        
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
        boolean allTests = false;

        // normal case
        skillTrigger = new SkillTrigger("Apply Fists to Faces", 2);
        skillTriggers.setSkillTriggers(new SkillTrigger[] {
            skillTrigger
        });
        if (skillTriggers.getSkillTriggers()[0].equals(skillTrigger)) {
            test1 = true;
        }
        // normal case
        try {
            skillTriggers.setSkillTriggers(new SkillTrigger[] {null});
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        try {
            skillTriggers.setSkillTriggers(new SkillTrigger[] {
                new SkillTrigger(), null});
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }

        allTests = test1 && test2 && test3;

        return allTests;
    }
    /**
     * Tests SkillTriggersList.generateOutput().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSkillTriggersListGenerateOutputTests() {
        // Output something like:
        //     "  Apply Fists to Faces (+2), Apply Fists to Faces (+2)\n"
        //     "  Apply Fists to Faces (+2)"
        SkillTriggersList skillTriggers = new SkillTriggersList();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        // normal case
        skillTriggers.setSkillTriggers(new SkillTrigger[0]);
        if (skillTriggers.generateOutput().equals("")) {
            test1 = true;
        }
        // normal case
        skillTriggers.setSkillTriggers(new SkillTrigger[] {
            new SkillTrigger("Apply Fists to Faces", 2),
            new SkillTrigger("Apply Fists to Faces", 2)
        });
        String testString1 = "  Apply Fists to Faces (+2), Apply Fists to Faces (+2)";
        if (skillTriggers.generateOutput().equals(testString1)) {
            test2 = true;
        }
        // normal case
        skillTriggers.setSkillTriggers(new SkillTrigger[] {
            new SkillTrigger("Apply Fists to Faces", 2),
            new SkillTrigger("Apply Fists to Faces", 2),
            new SkillTrigger("Apply Fists to Faces", 2)
        });
        String testString2 = "  Apply Fists to Faces (+2), Apply Fists to Faces"
            + " (+2),\n  Apply Fists to Faces (+2)";
        if (skillTriggers.generateOutput().equals(testString2)) {
            test3 = true;
        }

        allTests = test1 && test2 && test3;

        return allTests;
    }
    /**
     * Tests the Loadout class.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runLoadoutTests() {   
        // TODO: fill out
        boolean test1 = false;
        boolean allTests = false;

        allTests = test1;
        
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
        pilot.setName("validName");
        if (pilot.getName().equals("validName")) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setName(null);
        } catch (IllegalArgumentException exception) {
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
        pilot.setCallsign("validCallsign");
        if (pilot.getCallsign().equals("validCallsign")) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setCallsign(null);
        } catch (IllegalArgumentException exception) {
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
        pilot.setPlayer("validPlayer");
        if (pilot.getPlayer().equals("validPlayer")) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setPlayer(null);
        } catch (IllegalArgumentException exception) {
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
        pilot.setStatus("Active");
        if (pilot.getStatus().equals("active")) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setStatus("not a valid status");
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // edge case
        try {
            pilot.setStatus(null);
        } catch (IllegalArgumentException exception) {
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
        pilot.setBackground("validBackground");
        if (pilot.getBackground().equals("validBackground")) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setBackground(null);
        } catch (IllegalArgumentException exception) {
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
        pilot.setBiography("validBiography");
        if (pilot.getBiography().equals("validBiography")) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setBiography(null);
        } catch (IllegalArgumentException exception) {
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
        pilot.setAppearance("validAppearance");
        if (pilot.getAppearance().equals("validAppearance")) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setAppearance(null);
        } catch (IllegalArgumentException exception) {
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
        pilot.setPlayerNotes("validPlayerNotes");
        if (pilot.getPlayerNotes().equals("validPlayerNotes")) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setPlayerNotes(null);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
    /**
     * Tests Pilot.setGrit().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetGritTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        // normal case
        pilot.setGrit(0);
        if (pilot.getGrit() == 0) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setGrit(-2);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        try {
            pilot.setGrit(7);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }

        allTests = test1 && test2 && test3;

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
            pilot = new Pilot();
            pilot.setMaxHP(8);
            pilot.setCurrentHP(9);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setCurrentHP(8);
        if (pilot.getCurrentHP() == 8) {
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
            pilot = new Pilot();
            pilot.setMaxHP(0);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setMaxHP(8);
        if (pilot.getMaxHP() == 8) {
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
        pilot = new Pilot();
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
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean allTests = false;

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
        skillTriggersList = new SkillTriggersList();
        skillTriggersList.setSkillTriggers(new SkillTrigger[] {
            new SkillTrigger("", 2)
        });
        try {
            pilot.setSkillTriggers(skillTriggersList);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        skillTriggersList = new SkillTriggersList();
        skillTriggersList.setSkillTriggers(new SkillTrigger[] {
            new SkillTrigger("validName", -1)
        });
        try {
            pilot.setSkillTriggers(skillTriggersList);
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // TODO: decide whether to add these
        // // normal case
        // skillTrigger = new SkillTrigger("", 2);
        // try {
        //     skillTriggers.setSkillTriggers(new SkillTrigger[] {
        //         skillTrigger
        //     });
        // } catch (IllegalArgumentException exception) {
        //     test3 = true;
        // }
        // // normal case
        // skillTrigger = new SkillTrigger();
        // skillTrigger.setName("Apply Fists to Faces");
        // try {
        //     skillTriggers.setSkillTriggers(new SkillTrigger[] {
        //         skillTrigger
        //     });
        // } catch (IllegalArgumentException exception) {
        //     test4 = true;
        // }
        // // normal case
        // skillTrigger = new SkillTrigger();
        // skillTrigger.name = "";
        // try {
        //     skillTriggers.setSkillTriggers(new SkillTrigger[] {
        //         skillTrigger
        //     });
        // } catch (IllegalArgumentException exception) {
        //     test5 = true;
        // }
        // // normal case
        // skillTrigger = new SkillTrigger();
        // try {
        //     skillTriggers.setSkillTriggers(new SkillTrigger[] {
        //         skillTrigger
        //     });
        // } catch (IllegalArgumentException exception) {
        //     test6 = true;
        // }
        // // normal case
        // try {
        //     skillTriggers.setSkillTriggers(new SkillTrigger[] {
        //     });
        // } catch (IllegalArgumentException exception) {
        //     test7 = true;
        // }

        allTests = test1 && test2 && test3 && test4;

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
        pilot.setReserves(new String[] {});
        if (pilot.getReserves().getClass() == String[].class
            && pilot.getReserves().length == 0) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setReserves(null);
        } catch (IllegalArgumentException exception) {
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
        // TODO: fill out
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean allTests = false;

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
        boolean test4 = false;
        boolean allTests = false;

        // normal case
        pilot.setLicenseLevel(0);
        if (pilot.getGrit() == 0) {
            test1 = true;
        }
        // normal case
        pilot.setLicenseLevel(1);
        if (pilot.getGrit() == 1) {
            test2 = true;
        }
        // normal case
        pilot.setLicenseLevel(2);
        if (pilot.getGrit() == 1) {
            test3 = true;
        }
        // normal case
        pilot.setLicenseLevel(3);
        if (pilot.getGrit() == 2) {
            test4 = true;
        }

        allTests = test1 && test2 && test3 && test4;

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
        try {
            pilot.setLicenseList(new License[] {
                new License()
            });
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        license = new License();
        license.setFrame("IPS-N Blackbeard");
        try {
            pilot.setLicenseList(new License[] {
                license
            });
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        license = new License();
        license.setFrame("");
        license.setLicenseLevel(2);
        try {
            pilot.setLicenseList(new License[] {
                license
            });
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }

        allTests = licenseTests && test1 && test2 && test3 && test4;

        return allTests;
    }
    /**
     * Tests License.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runLicenseTests() {
        License license;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean allTests = false;

        // normal case
        license = new License();
        try {
            license.setLicenseLevel(-2);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        license = new License();
        try {
            license.setLicenseLevel(0);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        license = new License();
        try {
            license.setLicenseLevel(4);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        license = new License();
        license.setLicenseLevel(-1);
        if (license.getLicenseLevel() == -1) {
            test4 = true;
        }

        allTests = test1 && test2 && test3 && test4;

        return allTests;
    }
    /**
     * Tests Pilot.setSpecialEquipment().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetSpecialEquipmentTests() {
        Pilot pilot;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean allTests = false;

        // normal case
        pilot = new Pilot();
        try {
            pilot.setSpecialEquipment(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot = new Pilot();
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
        pilot = new Pilot();
        try {
            pilot.setSpecialEquipment(new String[] {"validEquipment", ""});
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        pilot = new Pilot();
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
        Pilot pilot;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        // normal case
        pilot = new Pilot();
        try {
            pilot.setMechSkills(new int[] {
                0
            });
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot = new Pilot();
        try {
            pilot.setMechSkills(new int[] {-1, 0, 0, 0});
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot();
        try {
            pilot.setMechSkills(new int[] {7, 0, 0, 0});
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }

        allTests = test1 && test2 && test3;

        return allTests;
    }
    /**
     * Tests Pilot.setCoreBonuses().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetCoreBonusesTests() {
        Pilot pilot;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean allTests = false;

        // normal case
        pilot = new Pilot();
        try {
            pilot.setCoreBonuses(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot = new Pilot();
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
        pilot = new Pilot();
        try {
            pilot.setCoreBonuses(new String[] {
                "validCoreBonus",
                null
            });
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        pilot = new Pilot();
        try {
            pilot.setCoreBonuses(new String[] {
                ""
            });
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setCoreBonuses(new String[] {
            "Auto-Stabilizing Hardpoints"
        });
        if (pilot.getCoreBonuses()[0].equals("Auto-Stabilizing Hardpoints")) {
            test6 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5 && test6;

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
        pilot = new Pilot();
        pilot.setTalents(new Talent[] {});
        if (pilot.getTalents().getClass() == Talent[].class && pilot.getTalents().length == 0) {
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
        pilot = new Pilot();
        try {
            pilot.setTalents(new Talent[] {
                new Talent(),
                null
            });
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        pilot = new Pilot();
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
        pilot = new Pilot();
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
        pilot = new Pilot();
        talent = new Talent();
        talent = new Talent("Ace", 1);
        pilot.setTalents(new Talent[] {
            talent
        });
        if (pilot.getTalents()[0].equals(talent)) {
            test7 = true;
        }

        allTests = talentTests && test1 && test2 && test3 && test4 && test5 && test6 && test7;

        return allTests;
    }
    /**
     * Tests Talent.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runTalentTests() {
        Talent talent;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean allTests = false;

        // normal case
        talent = new Talent();
        try {
            talent.setName(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        talent = new Talent();
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
        talent = new Talent();
        try {
            talent.setLevel(4);
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        talent = new Talent();
        try {
            talent.setLevel(0);
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }
        // normal case
        talent = new Talent();
        talent.setLevel(-1);
        if (talent.getLevel() == -1) {
            test6 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5 && test6;

        return allTests;
    }
    /**
     * Tests Pilot.generateOutput().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runPilotGenerateOutputTests() {
        // TODO: fill out
        Pilot pilot;
        License[] licenseList;
        String[] coreBonuses;
        Talent[] talents;
        boolean pilotGenerateMechOutputTests = false;
        boolean pilotGeneratePilotOutputTests = false;
        boolean pilotGenerateFullOutputTests = false;
        boolean test1 = false;
        boolean allTests = false;

        pilotGenerateMechOutputTests = runPilotGenerateMechOutputTests();
        pilotGeneratePilotOutputTests = runPilotGeneratePilotOutputTests();
        pilotGenerateFullOutputTests = runPilotGenerateFullOutputTests();

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
        // normal case
        pilot = new Pilot();
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(talents);
        String testString1 = "[ LICENSES ]\n  IPS-N Blackbeard 1, IPS-N Drake 2,"
            + " IPS-N Lancaster 3\n[ CORE BONUSES ]\n  Auto-Stabilizing"
            + " Hardpoints, Overpower Caliber, Improved Armament\n[ TALENTS ]\n"
            + "  Ace 1, Bonded 2, Brawler 3\n[ STATS ]\n  HULL:0 AGI:0 SYS:0"
            + " ENGI:0";
        if (pilot.generateOutput("mech build").equals(testString1)) {
            test1 = true;
        }
        
        allTests = pilotGenerateMechOutputTests && pilotGeneratePilotOutputTests
            && pilotGenerateFullOutputTests && test1;
        printStatement(pilotGenerateMechOutputTests, "Pilot.generateOutput(\"mech build\")", 3);
        printStatement(pilotGeneratePilotOutputTests, "Pilot.generateOutput(\"pilot\")", 3);
        printStatement(pilotGenerateFullOutputTests, "Pilot.generateOutput(\"full\")", 3);


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
        String testString1 = "[ LICENSES ]\n  IPS-N Blackbeard 1, IPS-N Drake 2,"
            + " IPS-N Lancaster 3\n[ CORE BONUSES ]\n  Auto-Stabilizing"
            + " Hardpoints, Overpower Caliber, Improved Armament\n[ TALENTS ]\n"
            + "  Ace 1, Bonded 2, Brawler 3\n[ STATS ]\n  HULL:0 AGI:0 SYS:0"
            + " ENGI:0";
        if (pilot.generateOutput("mech build").equals(testString1)) {
            test1 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(new Talent[0]);
        String testString2 = "[ LICENSES ]\n  IPS-N Blackbeard 1, IPS-N Drake 2,"
            + " IPS-N Lancaster 3\n[ CORE BONUSES ]\n  Auto-Stabilizing"
            + " Hardpoints, Overpower Caliber, Improved Armament\n[ TALENTS ]\n"
            + "  \n[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0";
        if (pilot.generateOutput("mech build").equals(testString2)) {
            test2 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(talents);
        String testString3 = "[ LICENSES ]\n  IPS-N Blackbeard 1, IPS-N Drake 2,"
            + " IPS-N Lancaster 3\n[ CORE BONUSES ]\n  N/A\n[ TALENTS ]\n  Ace"
            + " 1, Bonded 2, Brawler 3\n[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0";
        if (pilot.generateOutput("mech build").equals(testString3)) {
            test3 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(new Talent[0]);
        String testString4 = "[ LICENSES ]\n  IPS-N Blackbeard 1, IPS-N Drake 2,"
            + " IPS-N Lancaster 3\n[ CORE BONUSES ]\n  N/A\n[ TALENTS ]\n  \n"
            + "[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0";
        if (pilot.generateOutput("mech build").equals(testString4)) {
            test4 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(talents);
        String testString5 = "[ LICENSES ]\n  N/A\n[ CORE BONUSES ]\n"
            + "  Auto-Stabilizing Hardpoints, Overpower Caliber, Improved"
            + " Armament\n[ TALENTS ]\n  Ace 1, Bonded 2, Brawler 3\n[ STATS ]\n"
            + "  HULL:0 AGI:0 SYS:0 ENGI:0";
        if (pilot.generateOutput("mech build").equals(testString5)) {
            test5 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(new Talent[0]);
        String testString6 = "[ LICENSES ]\n  N/A\n[ CORE BONUSES ]\n"
            + "  Auto-Stabilizing Hardpoints, Overpower Caliber, Improved"
            + " Armament\n[ TALENTS ]\n  \n[ STATS ]\n  HULL:0 AGI:0 SYS:0"
            + " ENGI:0";
        if (pilot.generateOutput("mech build").equals(testString6)) {
            test6 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(new Talent[0]);
        String testString7 = "[ LICENSES ]\n  N/A\n[ CORE BONUSES ]\n"
            + "  N/A\n[ TALENTS ]\n  \n[ STATS ]\n  HULL:0 AGI:0 SYS:0"
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
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(talents);
        String testString1 = "[ TALENTS ]\n  Ace 1, Bonded 2,\n  Brawler 3\n"
            + "[ LICENSES ]\n  IPS-N Blackbeard 1, IPS-N Drake 2,\n  IPS-N"
            + " Lancaster 3\n[ CORE BONUSES ]\n  Auto-Stabilizing Hardpoints,"
            + " Overpower Caliber,\n  Improved Armament";
        if (pilot.generateOutput("pilot").equals(testString1)) {
            test1 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(new Talent[0]);
        String testString2 = "[ TALENTS ]\n  [ LICENSES ]\n  IPS-N Blackbeard 1,"
            + " IPS-N Drake 2,\n  IPS-N Lancaster 3\n[ CORE BONUSES ]\n"
            + "  Auto-Stabilizing Hardpoints, Overpower Caliber,\n  Improved"
            + " Armament";

        if (pilot.generateOutput("pilot").equals(testString2)) {
            test2 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(talents);
        String testString3 = "[ LICENSES ]\n  IPS-N Blackbeard 1, IPS-N Drake 2,"
            + " IPS-N Lancaster 3\n[ CORE BONUSES ]\n  N/A\n[ TALENTS ]\n  Ace"
            + " 1, Bonded 2, Brawler 3\n[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0";
        if (pilot.generateOutput("pilot").equals(testString3)) {
            test3 = true;
        }
        //     normal case
        pilot.setLicenseList(licenseList);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(new Talent[0]);
        String testString4 = "[ LICENSES ]\n  IPS-N Blackbeard 1, IPS-N Drake 2,"
            + " IPS-N Lancaster 3\n[ CORE BONUSES ]\n  N/A\n[ TALENTS ]\n  \n"
            + "[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0";
        if (pilot.generateOutput("pilot").equals(testString4)) {
            test4 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(talents);
        String testString5 = "[ LICENSES ]\n  N/A\n[ CORE BONUSES ]\n"
            + "  Auto-Stabilizing Hardpoints, Overpower Caliber, Improved"
            + " Armament\n[ TALENTS ]\n  Ace 1, Bonded 2, Brawler 3\n[ STATS ]\n"
            + "  HULL:0 AGI:0 SYS:0 ENGI:0";
        if (pilot.generateOutput("pilot").equals(testString5)) {
            test5 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(coreBonuses);
        pilot.setTalents(new Talent[0]);
        String testString6 = "[ LICENSES ]\n  N/A\n[ CORE BONUSES ]\n"
            + "  Auto-Stabilizing Hardpoints, Overpower Caliber, Improved"
            + " Armament\n[ TALENTS ]\n  \n[ STATS ]\n  HULL:0 AGI:0 SYS:0"
            + " ENGI:0";
        if (pilot.generateOutput("pilot").equals(testString6)) {
            test6 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[0]);
        pilot.setCoreBonuses(new String[0]);
        pilot.setTalents(new Talent[0]);
        String testString7 = "[ TALENTS ]";
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
        // TODO: fill out
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        allTests = test1 && test2 && test3;


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
        boolean test4 = false;
        boolean allTests = false;

        licenseList = new License[] {
            new License("IPS-N Blackbeard", 1),
            new License("IPS-N Drake", 2),
            new License("IPS-N Lancaster", 3)
        };
        // normal case
        pilot.setLicenseList(new License[0]);
        if (pilot.outputLicenses("mech build").equals("  N/A")) {
            test1 = true;
        }
        // normal case
        pilot.setLicenseList(new License[0]);
        if (pilot.outputLicenses("pilot").equals("")) {
            test2 = true;
        }
        // normal case
        pilot.setLicenseList(licenseList);
        String testString1 = "  IPS-N Blackbeard 1, IPS-N Drake 2, IPS-N Lancaster 3";
        if (pilot.outputLicenses("mech build").equals(testString1)) {
            test3 = true;
        }
        // normal case
        pilot.setLicenseList(licenseList);
        String testString2 = "  IPS-N Blackbeard 1, IPS-N Drake 2,\n  IPS-N Lancaster 3";
        if (pilot.outputLicenses("pilot").equals(testString2)) {
            test4 = true;
        }

        allTests = test1 && test2 && test3 && test4;

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
        pilot = new Pilot();
        pilot.setTalents(new Talent[0]);
        if (pilot.outputTalents("mech build").equals(
            "  \n")) {
            test1 = true;
        }
        // normal case
        pilot = new Pilot();
        pilot.setTalents(new Talent[0]);
        if (pilot.outputTalents("pilot").equals("")) {
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
        boolean test4 = false;
        boolean allTests = false;

        coreBonuses = new String[] {
            "Auto-Stabilizing Hardpoints",
            "Overpower Caliber",
            "Improved Armament"
        };
        // normal case
        pilot.setCoreBonuses(new String[0]);
        if (pilot.outputCoreBonuses("mech build").equals("  N/A\n")) {
            test1 = true;
        }
        // normal case
        pilot.setCoreBonuses(new String[0]);
        if (pilot.outputCoreBonuses("pilot").equals("")) {
            test2 = true;
        }
        // normal case
        pilot.setCoreBonuses(coreBonuses);
        String testString1 = "  Auto-Stabilizing Hardpoints, Overpower Caliber,"
            + " Improved Armament";
        if (pilot.outputCoreBonuses("mech build").equals(testString1)) {
            test3 = true;
        }
        // normal case
        pilot.setCoreBonuses(coreBonuses);
        String testString2 = "  Auto-Stabilizing Hardpoints, Overpower Caliber,\n"
            + "  Improved Armament";
        if (pilot.outputCoreBonuses("pilot").equals(testString2)) {
            test4 = true;
        }

        allTests = test1 && test2 && test3 && test4;

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
