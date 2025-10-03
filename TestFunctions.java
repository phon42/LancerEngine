/**
 * Tests every class and every function under every class. Yes, I'm serious.
 * Cannot be instantiated. All its methods are static.
 * Safety: N/A because this class cannot be instantiated.
 */
public class TestFunctions {
    public static int maxWidth = 0;
    public static int[] lineWidths;
    public static int numPassed = 0;
    public static int numTests = 0;

    // Prevent user from instantiating this class
    private TestFunctions() {}
    
    /**
     * Tests every class and function in the project.
     */
    public static void runTests() {
        // run a test on every single goddamn class and function in this place
        String line = "= = = = = = = = = = = = = = =";
        String spaces;

        maxWidth = 0;
        lineWidths = new int[0];
        numPassed = 0;
        numTests = 0;

        System.out.println("RUNNING TESTS");
        System.out.println(line);

        Test allTests = new Test("ALL TESTS", new Test[] {
            // ---PILOT-----------------------------------------------------------
            new Test("Pilot", new Test[] {
                new Test("Pilot.setName()", runSetPilotNameTests()),
                new Test("Pilot.setCallsign()", runSetCallsignTests()),
                new Test("Pilot.setPlayer()", runSetPlayerTests()),
                new Test("Pilot.setStatus()", runSetStatusTests()),
                new Test("Pilot.setBackground()", runSetBackgroundTests()),
                new Test("Pilot.setBiography()", runSetBiographyTests()),
                new Test("Pilot.setAppearance()", runSetAppearanceTests()),
                new Test("Pilot.setPlayerNotes()", runSetPlayerNotesTests()),
                new Test("Pilot.setGrit()", runSetPilotGritTests()),
                new Test("Pilot.setCurrentHP()", runSetPilotCurrentHPTests()),
                new Test("Pilot.setMaxHP()", runSetPilotMaxHPTests()),
                new Test("Pilot.setArmor()", runSetPilotArmorTests()),
                new Test("Pilot.setEvasion()", runSetPilotEvasionTests()),
                new Test("Pilot.setSpeed()", runSetPilotSpeedTests()),
                new Test("Pilot.setEDefense()", runSetPilotEDefenseTests()),
                new Test("Pilot.setSkillTriggers()", runSetPilotSkillTriggersTests(), new Test[] {
                    new Test("SkillTriggersList", new Test[] {
                        new Test("SkillTriggersList.setSkillTriggers()", runSetSkillTriggersTests()),
                        new Test("SkillTrigger", runSkillTriggerTests(), new Test[] {
                            new Test("SkillTrigger.equals(Object)", runSkillTriggerEqualsTests()),
                            new Test("SkillTrigger.equals(SkillTrigger)", runSkillTriggerEqualsSkillTriggerTests())
                        }),
                        new Test("SkillTriggersList.equals(Object)", runSkillTriggersListEqualsTests()),
                        new Test("SkillTriggersList.equals(SkillTriggersList)", runSkillTriggersListEqualsSkillTriggersListTests()),
                        new Test("SkillTriggersList.generateOutput()", runSkillTriggersListGenerateOutputTests())
                    })
                }),
                new Test("Pilot.setReserves()", runSetReservesTests()),
                new Test("Pilot.setLoadout()", runSetLoadoutTests(), new Test[] {
                    new Test("Loadout", runLoadoutTests(), new Test[] {
                        new Test("Loadout.equals(Object)", runLoadoutEqualsTests()),
                        new Test("Loadout.equals(Loadout)", runLoadoutEqualsLoadoutTests()),
                        new Test("Loadout.generateOutput()", runLoadoutGenerateOutputTests())
                    })
                }),
                new Test("Pilot.setLicenseLevel()", runSetLicenseLevelTests()),
                new Test("Pilot.setLicenseList()", runSetLicenseListTests(), new Test[] {
                    new Test("License", runLicenseTests(), new Test[] {
                        new Test("License.equals(Object)", runLicenseEqualsTests()),
                        new Test("License.equals(License)", runLicenseEqualsLicenseTests())
                    })
                }),
                new Test("Pilot.setSpecialEquipment()", runSetSpecialEquipmentTests()),
                new Test("Pilot.setMechSkills()", runSetMechSkillsTests()),
                new Test("Pilot.setCoreBonuses()", runSetCoreBonusesTests()),
                new Test("Pilot.setTalents()", runSetTalentsTests(), new Test[] {
                    new Test("Talent", runTalentTests(), new Test[] {
                        new Test("Talent.equals(Object)", runTalentEqualsTests()),
                        new Test("Talent.equals(Talent)", runTalentEqualsTalentTests())
                    })
                }),
                new Test("Pilot.generateOutput()", new Test[] {
                    new Test("Pilot.generateOutput(\"mech build\")", runPilotGenerateMechOutputTests()),
                    new Test("Pilot.generateOutput(\"pilot\")", runPilotGeneratePilotOutputTests()),
                    new Test("Pilot.generateOutput(\"full\")", runPilotGenerateFullOutputTests())
                }),
                new Test("Pilot.outputLicenses()", runOutputLicensesTests()),
                new Test("Pilot.outputTalents()", runOutputTalentsTests()),
                new Test("Pilot.outputCoreBonuses()", runOutputCoreBonusesTests())
            }),
            // ---MECH------------------------------------------------------------
            new Test("Mech", runMechTests())
        });
        String result = allTests.output();
        for (int i = 0; i < lineWidths.length; i++) {
            spaces = "";
            for (int j = 0; j < maxWidth - lineWidths[i]; j++) {
                spaces += " ";
            }
            result = result.replaceFirst("%spaces%", spaces);
        }
        System.out.println(result);
        System.out.println(line);
        if (numPassed == numTests) {
            System.out.println("ALL TESTS PASSED: ("
                + numPassed + "/" + numTests + " passed)");
        } else {
            System.out.println("SOME TESTS FAILED: ("
                + numPassed + "/" + numTests + " passed)");
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
     * Tests Pilot.setPilotName().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotNameTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        
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

        return test1 && test2;
    }
    /**
     * Tests Pilot.setCallsign().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetCallsignTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        
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

        return test1 && test2;
    }
    /**
     * Tests Pilot.setPlayer().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPlayerTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        
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

        return test1 && test2;
    }
    /**
     * Tests Pilot.setStatus().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetStatusTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        
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

        return test1 && test2 && test3;
    }
    /**
     * Tests Pilot.setBackground().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetBackgroundTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        
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

        return test1 && test2;
    }
    /**
     * Tests Pilot.setBiography().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetBiographyTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        
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

        return test1 && test2;
    }
    /**
     * Tests Pilot.setAppearance().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetAppearanceTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        
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

        return test1 && test2;
    }
    /**
     * Tests Pilot.setPlayerNotes().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPlayerNotesTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        
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

        return test1 && test2;
    }
    /**
     * Tests Pilot.setGrit() indirectly by testing Pilot.setLicenseLevel() and
     *     Pilot.getGrit().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotGritTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

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

        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests Pilot.setCurrentHP().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotCurrentHPTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;

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
        pilot = new Pilot("validName", "validCallsign");
        pilot.setCurrentHP(4);
        if (pilot.getCurrentHP() == 4) {
            test3 = true;
        }

        return test1 && test2 && test3;
    }
    /**
     * Tests Pilot.setMaxHP().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotMaxHPTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;

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

        return test1 && test2 && test3;
    }
    /**
     * Tests Pilot.setArmor().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotArmorTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;

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

        return test1 && test2;
    }
    /**
     * Tests Pilot.setEvasion().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotEvasionTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;

        // normal case
        try {
            pilot.setEvasion(-2);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot = new Pilot("validName", "validCallsign");
        pilot.setEvasion(8);
        if (pilot.getEvasion() == 8) {
            test2 = true;
        }

        return test1 && test2;
    }
    /**
     * Tests Pilot.setSpeed().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotSpeedTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;

        // normal case
        try {
            pilot.setSpeed(-1);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot = new Pilot("validName", "validCallsign");
        pilot.setSpeed(0);
        if (pilot.getSpeed() == 0) {
            test2 = true;
        }

        return test1 && test2;
    }
    /**
     * Tests Pilot.setEDefense().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotEDefenseTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;

        // normal case
        try {
            pilot.setEDefense(-1);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot = new Pilot("validName", "validCallsign");
        pilot.setEDefense(0);
        if (pilot.getEDefense() == 0) {
            test2 = true;
        }

        return test1 && test2;
    }
    /**
     * Tests Pilot.setSkillTriggers().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetPilotSkillTriggersTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        SkillTriggersList skillTriggersList = new SkillTriggersList();
        SkillTrigger skillTrigger;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;

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
        skillTrigger = new SkillTrigger("validSkillTrigger", 2);
        skillTriggersList.setSkillTriggers(new SkillTrigger[] {
            skillTrigger
        });
        pilot.setSkillTriggers(skillTriggersList);
        if (pilot.getSkillTriggers().getSkillTriggers()[0].equals(
            skillTrigger)) {
            test3 = true;
        }

        return test1 && test2 && test3;
    }
    /**
     * Tests SkillTriggersList.setSkillTriggers().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetSkillTriggersTests() {
        SkillTriggersList skillTriggers = new SkillTriggersList();
        SkillTrigger skillTrigger;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

        // normal case
        try {
            skillTriggers.setSkillTriggers(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        skillTriggers.setSkillTriggers(new SkillTrigger[0]);
        if (skillTriggers.getSkillTriggers().getClass() == SkillTrigger[].class
            && skillTriggers.getSkillTriggers().length == 0) {
            test2 = true;
        }
        // normal case
        try {
            // = SkillTrigger[] {null}
            skillTriggers.setSkillTriggers(new SkillTrigger[1]);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        skillTrigger = new SkillTrigger("Apply Fists to Faces", 2);
        skillTriggers.setSkillTriggers(new SkillTrigger[] {
            skillTrigger
        });
        if (skillTriggers.getSkillTriggers()[0].equals(skillTrigger)) {
            test4 = true;
        }

        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests the SkillTrigger class and all its functions.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runSkillTriggerTests() {
        SkillTrigger skillTrigger = new SkillTrigger(
            "Apply Fists to Faces", 2);
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean test7 = false;
        boolean test8 = false;
        boolean test9 = false;

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
            skillTrigger.setLevel(-2);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            skillTrigger.setLevel(7);
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        try {
            skillTrigger.setLevel(0);
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }
        // normal case
        try {
            skillTrigger.setLevel(1);
        } catch (IllegalArgumentException exception) {
            test6 = true;
        }
        // normal case
        try {
            skillTrigger.setLevel(3);
        } catch (IllegalArgumentException exception) {
            test7 = true;
        }
        // normal case
        try {
            skillTrigger.setLevel(5);
        } catch (IllegalArgumentException exception) {
            test8 = true;
        }
        // normal case
        skillTrigger.setLevel(2);
        if (skillTrigger.getLevel() == 2) {
            test9 = true;
        }
        
        return test1 && test2 && test3 && test4 && test5 && test6 && test7
            && test8 && test9;
    }
    /**
     * Tests SkillTrigger.equals(Object).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSkillTriggerEqualsTests() {
        SkillTrigger skillTrigger = new SkillTrigger(
            "Apply Fists to Faces", 2);
        boolean test1 = false;
        boolean test2 = false;

        // normal case
        if (! skillTrigger.equals(null)) {
            test1 = true;
        }
        // normal case
        if (! skillTrigger.equals(new Object())) {
            test2 = true;
        }

        return test1 && test2;
    }
    /**
     * Tests SkillTrigger.equals(SkillTrigger).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSkillTriggerEqualsSkillTriggerTests() {
        SkillTrigger skillTrigger = new SkillTrigger(
            "Apply Fists to Faces", 4);
        SkillTrigger testSkillTrigger = new SkillTrigger("validName",
            2);
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

        // normal case
        if (! skillTrigger.equals(null)) {
            test1 = true;
        }
        // normal case
        skillTrigger.setLevel(2);
        if (! skillTrigger.equals(testSkillTrigger)) {
            test2 = true;
        }
        // normal case
        skillTrigger = new SkillTrigger("Apply Fists to Faces", 4);
        skillTrigger.setName("validName");
        if (! skillTrigger.equals(testSkillTrigger)) {
            test3 = true;
        }
        // normal case
        skillTrigger = new SkillTrigger("validName", 2);
        if (skillTrigger.equals(testSkillTrigger)) {
            test4 = true;
        }

        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests SkillTriggersList.equals(Object).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSkillTriggersListEqualsTests() {
        SkillTriggersList skillTriggersList = new SkillTriggersList();
        boolean test1 = false;
        boolean test2 = false;

        // normal case
        if (! skillTriggersList.equals(null)) {
            test1 = true;
        }
        // normal case
        if (! skillTriggersList.equals(new Object())) {
            test2 = true;
        }

        return test1 && test2;
    }
    /**
     * Tests SkillTriggersList.equals(SkillTriggersList).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSkillTriggersListEqualsSkillTriggersListTests() {
        SkillTriggersList skillTriggersList = new SkillTriggersList();
        boolean test1 = false;
        boolean test2 = false;

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

        return test1 && test2;
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

        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests Pilot.setReserves().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetReservesTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;

        // normal case
        try {
            pilot.setReserves(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setReserves(new String[0]);
        if (pilot.getReserves().getClass() == String[].class
            && pilot.getReserves().length == 0) {
            test2 = true;
        }
        // normal case
        try {
            // = String[] {null}
            pilot.setReserves(new String[1]);
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

        return test1 && test2 && test3 && test4 && test5 && test6;
    }
    /**
     * Tests Pilot.setLoadout().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetLoadoutTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;

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

        return test1 && test2;
    }
    /**
     * Tests the Loadout class.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runLoadoutTests() {
        Loadout loadout = new Loadout();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean test7 = false;
        boolean test8 = false;

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
        
        return test1 && test2 && test3 && test4 && test5 && test6 && test7
            && test8;
    }
    /**
     * Tests Loadout.equals(Object).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runLoadoutEqualsTests() {
        Loadout loadout = new Loadout();
        boolean test1 = false;
        boolean test2 = false;

        // normal case
        if (! loadout.equals(null)) {
            test1 = true;
        }
        // normal case
        if (! loadout.equals(new Object())) {
            test2 = true;
        }

        return test1 && test2;
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
                "validWeapon", "validWeapon"});
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

        return test1 && test2 && test3 && test4 && test5;
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

        // normal case
        String testString1 = "  N/A\n";
        if (loadout.generateOutput().equals(testString1)) {
            test1 = true;
        }

        return test1;
    }
    /**
     * Tests Pilot.setLicenseLevel().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetLicenseLevelTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;

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

        return test1 && test2 && test3;
    }
    /**
     * Tests Pilot.setLicenseList().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetLicenseListTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        License license;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

        // normal case
        try {
            pilot.setLicenseList(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setLicenseList(new License[0]);
        if (pilot.getLicenseList().getClass() == License[].class
            && pilot.getLicenseList().length == 0) {
            test2 = true;
        }
        // normal case
        try {
            // = License[] {null}
            pilot.setLicenseList(new License[1]);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        license = new License("validFrame", 2);
        pilot.setLicenseList(new License[] {
            license
        });
        if (pilot.getLicenseList()[0].equals(license)) {
            test4 = true;
        }

        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests License.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runLicenseTests() {
        License license = new License("IPS-N Blackbeard",
            1);
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;

        // normal case
        try {
            license.setName(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        license.setName("validFrame");
        if (license.getName().equals("validFrame")) {
            test2 = true;
        }
        // normal case
        try {
            license.setLevel(0);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            license.setLevel(4);
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        license.setLevel(3);
        if (license.getLevel() == 3) {
            test5 = true;
        }

        return test1 && test2 && test3 && test4 && test5;
    }
    /**
     * Tests License.equals(Object).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runLicenseEqualsTests() {
        License license = new License("IPS-N Blackbeard",
            1);
        boolean test1 = false;
        boolean test2 = false;

        // normal case
        if (! license.equals(null)) {
            test1 = true;
        }
        // normal case
        if (! license.equals(new Object())) {
            test2 = true;
        }

        return test1 && test2;
    }
    /**
     * Tests License.equals(License).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runLicenseEqualsLicenseTests() {
        License license = new License("IPS-N Blackbeard",
            1);
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

        // normal case
        if (! license.equals(null)) {
            test1 = true;
        }
        // normal case
        if (! license.equals(new License("validFrame",
            1))) {
            test2 = true;
        }
        // normal case
        if (! license.equals(new License("IPS-N Blackbeard",
            2))) {
            test3 = true;
        }
        // normal case
        license.setName("validFrame");
        license.setLevel(2);
        if (license.equals(new License("validFrame",
            2))) {
            test4 = true;
        }

        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests Pilot.setSpecialEquipment().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetSpecialEquipmentTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;

        // normal case
        try {
            pilot.setSpecialEquipment(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setSpecialEquipment(new String[0]);
        if (pilot.getSpecialEquipment().getClass() == String[].class
            && pilot.getSpecialEquipment().length == 0) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot("validName", "validCallsign");
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

        return test1 && test2 && test3 && test4 && test5;
    }
    /**
     * Tests Pilot.setMechSkills().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetMechSkillsTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

        // normal case
        try {
            pilot.setMechSkills(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        try {
            pilot.setMechSkills(new int[1]);
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

        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests Pilot.setCoreBonuses().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetCoreBonusesTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean test7 = false;

        // normal case
        try {
            pilot.setCoreBonuses(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setCoreBonuses(new String[0]);
        if (pilot.getCoreBonuses().getClass() == String[].class
            && pilot.getCoreBonuses().length == 0) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot("validName", "validCallsign");
        try {
            // = String[] {null}
            pilot.setCoreBonuses(new String[1]);
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

        return test1 && test2 && test3 && test4 && test5 && test6 && test7;
    }
    /**
     * Tests Pilot.setTalents().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runSetTalentsTests() {
        Pilot pilot;
        Talent talent;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;

        // normal case
        pilot = new Pilot("validName", "validCallsign");
        try {
            pilot.setTalents(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        pilot.setTalents(new Talent[0]);
        if (pilot.getTalents().getClass() == Talent[].class
            && pilot.getTalents().length == 0) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot("validName", "validCallsign");
        try {
            // = Talent[] {null}
            pilot.setTalents(new Talent[1]);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            pilot.setTalents(new Talent[] {
                new Talent("Ace", 1),
                null
            });
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        talent = new Talent("Ace", 1);
        pilot.setTalents(new Talent[] {
            talent
        });
        if (pilot.getTalents()[0].equals(talent)) {
            test5 = true;
        }

        return test1 && test2 && test3 && test4 && test5;
    }
    /**
     * Tests Talent.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runTalentTests() {
        Talent talent = new Talent("Ace", 1);
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;

        // normal case
        try {
            talent.setName(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        talent.setName("validName");
        if (talent.getName().equals("validName")) {
            test2 = true;
        }
        // normal case
        talent = new Talent("Ace", 1);
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
        talent.setLevel(3);
        if (talent.getLevel() == 3) {
            test6 = true;
        }

        return test1 && test2 && test3 && test4 && test5 && test6;
    }
    /**
     * Tests Talent.equals(Object).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runTalentEqualsTests() {
        Talent talent = new Talent("Ace", 1);
        boolean test1 = false;
        boolean test2 = false;

        // normal case
        if (! talent.equals(null)) {
            test1 = true;
        }
        // normal case
        if (! talent.equals(new Object())) {
            test2 = true;
        }

        return test1 && test2;
    }
    /**
     * Tests Talent.equals(Talent).
     * @return a boolean representing whether the function passed.
     */
    private static boolean runTalentEqualsTalentTests() {
        Talent talent = new Talent("Ace", 1);
        Talent testTalent = new Talent("validName", 1);
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;

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
        talent = new Talent("validName", 2);
        if (! talent.equals(testTalent)) {
            test4 = true;
        }
        // normal case
        talent = new Talent("validName", 1);
        if (talent.equals(testTalent)) {
            test5 = true;
        }

        return test1 && test2 && test3 && test4 && test5;
    }
    /**
     * Tests Pilot.generateOutput("mech build").
     * @return a boolean representing whether the function passed.
     */
    private static boolean runPilotGenerateMechOutputTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
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

        return test1 && test2 && test3 && test4 && test5 && test6 && test7;
    }
    /**
     * Tests Pilot.generateOutput("pilot").
     * @return a boolean representing whether the function passed.
     */
    private static boolean runPilotGeneratePilotOutputTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
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

        return test1 && test2 && test3 && test4 && test5 && test6 && test7;
    }
    /**
     * Tests Pilot.generateOutput("full").
     * @return a boolean representing whether the function passed.
     */
    private static boolean runPilotGenerateFullOutputTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
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

        return test1 && test2 && test3 && test4 && test5 && test6 && test7;
    }
    /**
     * Tests Pilot.outputLicenses().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runOutputLicensesTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        License[] licenseList;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;

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
        pilot = new Pilot("validName", "validCallsign");
        pilot.setLicenseList(licenseList);
        String testString1 = "  IPS-N Blackbeard 1, IPS-N Drake 2,"
            + " IPS-N Lancaster 3\n";
        if (pilot.outputLicenses("mech build").equals(testString1)) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot("validName", "validCallsign");
        pilot.setLicenseList(licenseList);
        String testString2 = "  IPS-N Blackbeard 1, IPS-N Drake 2,\n"
            + "  IPS-N Lancaster 3\n";
        if (pilot.outputLicenses("pilot").equals(testString2)) {
            test3 = true;
        }

        return test1 && test2 && test3;
    }
    /**
     * Tests Pilot.outputTalents().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runOutputTalentsTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

        // normal case
        pilot.setTalents(new Talent[0]);
        if (pilot.outputTalents("mech build").equals(
            "  N/A\n")) {
            test1 = true;
        }
        // normal case
        pilot = new Pilot("validName", "validCallsign");
        pilot.setTalents(new Talent[0]);
        if (pilot.outputTalents("pilot").equals(
            "  N/A\n")) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot("validName", "validCallsign");
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
        pilot = new Pilot("validName", "validCallsign");
        pilot.setTalents(new Talent[] {
            new Talent("Ace", 1),
            new Talent("Brawler", 1),
            new Talent("Bonded", 1)
        });
        if (pilot.outputTalents("pilot").equals(
            "  Ace 1, Brawler 1,\n  Bonded 1\n")) {
            test4 = true;
        }

        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests Pilot.outputCoreBonuses().
     * @return a boolean representing whether the function passed.
     */
    private static boolean runOutputCoreBonusesTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        String[] coreBonuses;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;

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
        pilot = new Pilot("validName", "validCallsign");
        pilot.setCoreBonuses(coreBonuses);
        String testString1 = "  Auto-Stabilizing Hardpoints, Overpower Caliber,"
            + " Improved Armament\n";
        if (pilot.outputCoreBonuses("mech build").equals(
            testString1)) {
            test2 = true;
        }
        // normal case
        pilot = new Pilot("validName", "validCallsign");
        pilot.setCoreBonuses(coreBonuses);
        String testString2 = "  Auto-Stabilizing Hardpoints,"
            + " Overpower Caliber,\n  Improved Armament\n";
        if (pilot.outputCoreBonuses("pilot").equals(testString2)) {
            test3 = true;
        }

        return test1 && test2 && test3;
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