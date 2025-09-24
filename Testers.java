public class Testers {
    public static void runTests() {
        // run a test on every single goddamn class and function in this place
        String line = "= = = = = = = = = = = = = = =";

        System.out.println("RUNNING TESTS");
        System.out.println(line);

        // ---PILOT-----------------------------------------------------------
        boolean pilotTestsPassed = runPilotTests();
        printStatement(pilotTestsPassed, "Pilot class", 1);

        // ---MECH------------------------------------------------------------
        boolean mechTestsPassed = runMechTests();
        printStatement(mechTestsPassed, "Mech class", 1);

        System.out.println(line);
        if (pilotTestsPassed && mechTestsPassed) {
            System.out.println("ALL TESTS PASSED");
        } else {
            System.out.println("SOME TESTS FAILED");
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
        System.out.println(outputString);
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
                System.out.println("Error! Characters \"" + char1 + "\" and \"" + char2 + "\" at index " + i + " are not the same.");
            }
        }
    }
    private static boolean runPilotTests() {
        boolean skillTriggersListTests = false;
        boolean setNameTests = false;
        boolean setCallsignTests = false;
        boolean setPlayerTests = false;
        boolean setStatusTests = false;
        boolean setBackgroundTests = false;
        boolean setBiographyTests = false;
        boolean setAppearanceTests = false;
        boolean setPlayerNotesTests = false;
        boolean setCurrentHPTests = false;
        boolean setReservesTests = false;
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
        // Pilot.setCurrentHP()
        setCurrentHPTests = runSetCurrentHPTests();
        // Pilot.setReserves()
        setReservesTests = runSetReservesTests();
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

        allTests = skillTriggersListTests && setNameTests && setCallsignTests
            && setPlayerTests && setStatusTests && setBackgroundTests
            && setBiographyTests && setAppearanceTests && setPlayerNotesTests
            && setCurrentHPTests && setReservesTests && setLicenseLevelTests
            && setLicenseListTests && setSpecialEquipmentTests && setMechSkillsTests
            && setCoreBonusesTests && setTalentsTests && generateOutputTests
            && outputLicensesTests && outputTalentsTests && outputCoreBonusesTests;
        printStatement(setNameTests, "Pilot.setName()", 2);
        printStatement(setCallsignTests, "Pilot.setCallsign()", 2);
        printStatement(setPlayerTests, "Pilot.setPlayer()", 2);
        printStatement(setStatusTests, "Pilot.setStatus()", 2);
        printStatement(setBackgroundTests, "Pilot.setBackground()", 2);
        printStatement(setBiographyTests, "Pilot.setBiography()", 2);
        printStatement(setAppearanceTests, "Pilot.setAppearance()", 2);
        printStatement(setPlayerNotesTests, "Pilot.setPlayerNotes()", 2);
        printStatement(setCurrentHPTests, "Pilot.setCurrentHP()", 2);
        printStatement(setReservesTests, "Pilot.setReserves()", 2);
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
    private static boolean runSkillTriggersListTests() {                                                                                                                                                                                                                                                                              
        boolean setSkillTriggersTests = false;
        boolean generateOutputTests = false;
        boolean allTests = false;

        setSkillTriggersTests = runSetSkillTriggersTests();
        generateOutputTests = runSkillTriggersListGenerateOutputTests();
        allTests = setSkillTriggersTests && generateOutputTests;
        printStatement(setSkillTriggersTests, "SkillTriggersList.setSkillTriggers()", 4);
        printStatement(generateOutputTests, "SkillTriggersList.generateOutput()", 4);
        
        return allTests;
    }
    private static boolean runSetSkillTriggersTests() {
        SkillTriggersList skillTriggers = new SkillTriggersList();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean test7 = false;
        boolean allTests = false;

        // normal case
        skillTriggers.setSkillTriggers(new SkillTrigger[] {
            new SkillTrigger("Apply Fists to Faces", 2)
        });
        if (skillTriggers.generateOutput().equals("  Apply Fists to Faces (+2)\n")) {
            test1 = true;
        }
        // normal case
        SkillTrigger skillTrigger1 = new SkillTrigger();
        skillTrigger1.name = "";
        skillTrigger1.value = 2;
        try {
            skillTriggers.setSkillTriggers(new SkillTrigger[] {
                skillTrigger1
            });
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        skillTrigger1 = new SkillTrigger();
        skillTrigger1.name = "Apply Fists to Faces";
        try {
            skillTriggers.setSkillTriggers(new SkillTrigger[] {
                skillTrigger1
            });
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        skillTrigger1 = new SkillTrigger();
        skillTrigger1.name = "";
        try {
            skillTriggers.setSkillTriggers(new SkillTrigger[] {
                skillTrigger1
            });
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }
        // normal case
        skillTrigger1 = new SkillTrigger();
        try {
            skillTriggers.setSkillTriggers(new SkillTrigger[] {
                skillTrigger1
            });
        } catch (IllegalArgumentException exception) {
            test6 = true;
        }
        // normal case
        try {
        skillTriggers.setSkillTriggers(new SkillTrigger[] {
        });
        } catch (IllegalArgumentException exception) {
            test7 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5 && test6 && test7;

        return allTests;
    }
    private static boolean runSkillTriggersListGenerateOutputTests() {
        SkillTriggersList skillTriggers = new SkillTriggersList();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        // normal case
        skillTriggers.setSkillTriggers(new SkillTrigger[] {
            new SkillTrigger("Apply Fists to Faces", 2),
            new SkillTrigger("Apply Fists to Faces", 2)
        });
        if (skillTriggers.generateOutput().equals("  Apply Fists to Faces (+2), Apply Fists to Faces (+2)\n")) {
            test1 = true;
        }
        // normal case
        skillTriggers.setSkillTriggers(new SkillTrigger[] {
            new SkillTrigger("Apply Fists to Faces", 2),
            new SkillTrigger("Apply Fists to Faces", 2),
            new SkillTrigger("Apply Fists to Faces", 2)
        });
        if (skillTriggers.generateOutput().equals(
            "  Apply Fists to Faces (+2), Apply Fists to Faces (+2)\n  Apply Fists to Faces (+2)"
        )) {
            test2 = true;
        }
        // normal case
        skillTriggers.setSkillTriggers(null);
        if (skillTriggers.generateOutput().equals("")) {
            test3 = true;
        }

        allTests = test1 && test2 && test3;

        return allTests;
    }
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
    private static boolean runSetCurrentHPTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        // normal case
        try {
            pilot.setCurrentHP(-1);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setMaxHP(8);
            pilot.setCurrentHP(9);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        pilot.setMaxHP(8);
        pilot.setCurrentHP(8);
        if (pilot.getCurrentHP() == 8) {
            test3 = true;
        }

        allTests = test1 && test2 && test3;

        return allTests;
    }
    private static boolean runSetReservesTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean allTests = false;

        // normal case
        pilot.setReserves(null);
        if (pilot.getReserves() == null) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setReserves(new String[] {});
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }

        allTests = test1 && test2;

        return allTests;
    }
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
    private static boolean runSetLicenseListTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean allTests = false;

        // normal case
        pilot.setLicenseList(null);
        if (pilot.getLicenseList() == null) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setLicenseList(new String[][] {{}});
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        try {
            pilot.setLicenseList(new String[][] {
                {"IPS-N Blackbeard"}
            });
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            pilot.setLicenseList(new String[][] {
                {"IPS-N Blackbeard", ""}
            });
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        try {
            pilot.setLicenseList(new String[][] {
                {"", "2"}
            });
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5;

        return allTests;
    }
    private static boolean runSetSpecialEquipmentTests() {
        // TODO: fill out
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean allTests = false;

        allTests = test1;

        return allTests;
    }
    private static boolean runSetMechSkillsTests() {
        // TODO: fill out
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean allTests = false;

        allTests = test1;

        return allTests;
    }
    private static boolean runSetCoreBonusesTests() {
        // TODO: fill out
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean allTests = false;

        allTests = test1;

        return allTests;
    }
    private static boolean runSetTalentsTests() {
        // TODO: fill out
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean allTests = false;

        allTests = test1;

        return allTests;
    }
    private static boolean runPilotGenerateOutputTests() {
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        test1 = runPilotGenerateMechOutputTests();
        test2 = runPilotGeneratePilotOutputTests();
        test3 = runPilotGenerateFullOutputTests();

        allTests = test1 && test2 && test3;
        printStatement(test1, "Pilot.generateOutput(\"mech build\")", 3);
        printStatement(test2, "Pilot.generateOutput(\"pilot\")", 3);
        printStatement(test3, "Pilot.generateOutput(\"full\")", 3);


        return allTests;
    }
    private static boolean runPilotGenerateMechOutputTests() {
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean test7 = false;
        boolean allTests = false;
        
        // mech build
        //     normal case
        pilot.setLicenseList(new String[][] {
            {"IPS-N Blackbeard", "1"}
        });
        pilot.setCoreBonuses(new String[] {
            "Overpower Caliber"
        });
        pilot.setTalents(new Talent[] {
            new Talent("Ace", 1)
        });
        String testString1 = "[ LICENSES ]\n  IPS-N Blackbeard 1\n[ CORE BONUSES ]\n  Overpower Caliber\n[ TALENTS ]\n  Ace 1\n[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0\n";
        if (pilot.generateOutput("mech build").equals(testString1)) {
            test1 = true;
        }
        //     normal case
        pilot.setLicenseList(new String[][] {
            {"IPS-N Blackbeard", "1"}
        });
        pilot.setCoreBonuses(new String[] {
            "Overpower Caliber"
        });
        pilot.setTalents(null);
        String testString2 = "[ LICENSES ]\n  IPS-N Blackbeard 1\n[ CORE BONUSES ]\n  Overpower Caliber\n[ TALENTS ]\n  \n[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0\n";
        if (pilot.generateOutput("mech build").equals(testString2)) {
            test2 = true;
        }
        //     normal case
        pilot.setLicenseList(new String[][] {
            {"IPS-N Blackbeard", "1"}
        });
        pilot.setCoreBonuses(null);
        pilot.setTalents(new Talent[] {
            new Talent("Ace", 1)
        });
        String testString3 = "[ LICENSES ]\n  IPS-N Blackbeard 1\n[ CORE BONUSES ]\n  N/A\n[ TALENTS ]\n  Ace 1\n[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0\n";
        if (pilot.generateOutput("mech build").equals(testString3)) {
            test3 = true;
        }
        //     normal case
        pilot.setLicenseList(new License[] {
            new License("IPS-N Blackbeard", 1)
        });
        pilot.setCoreBonuses(null);
        pilot.setTalents(null);
        String testString4 = "[ LICENSES ]\n  IPS-N Blackbeard 1\n[ CORE BONUSES ]\n  N/A\n[ TALENTS ]\n  \n[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0\n";
        if (pilot.generateOutput("mech build").equals(testString4)) {
            test4 = true;
        }
        //     normal case
        pilot.setLicenseList(null);
        pilot.setCoreBonuses(new String[] {
            "Overpower Caliber"
        });
        pilot.setTalents(new Talent[] {
            new Talent("Ace", 1)
        });
        String testString5 = "[ LICENSES ]\n  N/A\n[ CORE BONUSES ]\n  Overpower Caliber\n[ TALENTS ]\n  Ace 1\n[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0\n";
        if (pilot.generateOutput("mech build").equals(testString5)) {
            test5 = true;
        }
        //     normal case
        pilot.setLicenseList(null);
        pilot.setCoreBonuses(new String[] {
            "Overpower Caliber"
        });
        pilot.setTalents(null);
        String testString6 = "[ LICENSES ]\n  N/A\n[ CORE BONUSES ]\n  Overpower Caliber\n[ TALENTS ]\n  \n[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0\n";
        if (pilot.generateOutput("mech build").equals(testString6)) {
            test6 = true;
        }
        //     normal case
        pilot.setLicenseList(null);
        pilot.setCoreBonuses(null);
        pilot.setTalents(null);
        String testString7 = "[ LICENSES ]\n  N/A\n[ CORE BONUSES ]\n  N/A\n[ TALENTS ]\n  \n[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0\n";
        if (pilot.generateOutput("mech build").equals(testString7)) {
            test7 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5 && test6 && test7;

        return allTests;
    }
    private static boolean runPilotGeneratePilotOutputTests() {
        // TODO: fill out
        Pilot pilot = new Pilot();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        // pilot
        //     normal case
        pilot.setLicenseList(new String[][] {
            {"IPS-N Blackbeard", "1"}
        });
        pilot.setCoreBonuses(new String[] {
            "Overpower Caliber"
        });
        pilot.setTalents(new Talent[] {
            new Talent("Ace", 1)
        });
        String testString1 = "[ LICENSES ]\n  IPS-N Blackbeard 1\n[ CORE BONUSES ]"
            + "\n  Overpower Caliber\n[ TALENTS ]\n  Ace 1\n[ STATS ]\n  HULL:0 AGI:0 SYS:0 ENGI:0\n";
        if (pilot.generateOutput("pilot").equals(testString1)) {
            test1 = true;
        }

        allTests = test1 && test2 && test3;


        return allTests;
    }
    private static boolean runPilotGenerateFullOutputTests() {
        // TODO: fill out
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean allTests = false;

        allTests = test1 && test2 && test3;


        return allTests;
    }
    private static boolean runOutputLicensesTests() {
        Pilot pilot = new Pilot();
        License[] licenseList;
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

        // normal case
        licenseList = new License[] {
            new License("Blackbeard", 1),
            new License("Drake", 2),
            new License("Lancaster", 3)
        };
        pilot.setLicenseList(licenseList);
        if (pilot.outputLicenses("full") == "  Blackbeard 1, Drake 2, Lancaster 3\n") {
            test1 = true;
        }
        // normal case
        try {
            licenseList = new String[][] {
                {"", "1"}
            };
            pilot.setLicenseList(licenseList);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        try {
            licenseList = new String[][] {
                {null, "1"}
            };
            pilot.setLicenseList(licenseList);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            licenseList = new String[][] {
                {"Blackbeard", ""}
            };
            pilot.setLicenseList(licenseList);
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        try {
            licenseList = new String[][] {
                {"Blackbeard", null}
            };
            pilot.setLicenseList(licenseList);
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }
        // normal case
        try {
            licenseList = new String[][] {
                {"Blackbeard"}
            };
            pilot.setLicenseList(licenseList);
        } catch (IllegalArgumentException exception) {
            test6 = true;
        }
        // normal case
        try {
            licenseList = new String[][] {
                {}
            };
            pilot.setLicenseList(licenseList);
        } catch (IllegalArgumentException exception) {
            test7 = true;
        }
        // normal case
        try {
            licenseList = new String[][] {
            };
            pilot.setLicenseList(licenseList);
        } catch (IllegalArgumentException exception) {
            test8 = true;
        }
        // normal case
        try {
            licenseList = null;
            pilot.setLicenseList(licenseList);
        } catch (IllegalArgumentException exception) {
            test9 = true;
        }

        allTests = test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9;

        return allTests;
    }
    private static boolean runOutputTalentsTests() {
        // TODO: fill out

        return false;
    }
    private static boolean runOutputCoreBonusesTests() {
        // TODO: fill out

        return false;
    }
    private static boolean runMechTests() {
        // TODO: fill out
        return false;
    }
}
