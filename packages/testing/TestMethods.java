package packages.testing;

import main.Database;
import main.database.FrameEnum;
import packages.coreTypes.entityMechanics.License;
import packages.coreTypes.entityMechanics.entityTypes.Mech;
import packages.coreTypes.entityMechanics.entityTypes.Pilot;
import packages.coreTypes.entityMechanics.entityTypes.mech.Frame;
import packages.coreTypes.entityMechanics.entityTypes.mech.Mount;
import packages.coreTypes.entityMechanics.entityTypes.mech.equipment.MechSystem;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Loadout;
import packages.coreTypes.entityMechanics.entityTypes.pilot.SkillTriggersList;
import packages.coreTypes.entityMechanics.entityTypes.pilot.Talent;
import packages.coreTypes.entityMechanics.entityTypes.pilot.skillTriggersList.Skill;
import packages.testing.testMethods.Test;

/**
 * Tests every class and every method under every class. Yes, I'm serious.
 */
/**
 * Represents absolutely nothing. Contains the runTests() method, which tests
 *     every class and method in the project and prints the results.
 * 
 * Cannot be instantiated. All its methods are static.
 * 
 * Used in Main.
 * 
 * Safety: N/A because this class cannot be instantiated.
 */
public final class TestMethods {
    public static int maxWidth = 0;
    public static int[] lineWidths;
    public static int numPassed = 0;
    public static int numTests = 0;

    // Prevent user from instantiating this class
    private TestMethods() {}
    
    /**
     * Tests every class and method in the project and prints the results.
     */
    public static void runTests() {
        // run a test on every single goddamn class and method in this place
        final String line = "= = = = = = = = = = = = = = =";
        String spaces;

        maxWidth = 0;
        lineWidths = new int[0];
        numPassed = 0;
        numTests = 0;

        System.out.println("RUNNING TESTS");
        System.out.println(line);

        Test allTests = new Test("ALL TESTS", new Test[] {
            new Test("Pilot", new Test[] {
                new Test("Pilot.setName()", runSetPilotNameTests()),
                new Test("Pilot.setCallsign()", runSetCallsignTests()),
                new Test("Pilot.setPlayer()", runSetPlayerTests()),
                new Test("Pilot.setStatus()", runSetStatusTests()),
                new Test("Pilot.setBackground()", runSetBackgroundTests()),
                new Test("Pilot.setBiography()", runSetBiographyTests()),
                new Test("Pilot.setAppearance()", runSetAppearanceTests()),
                new Test("Pilot.setPlayerNotes()", runSetPlayerNotesTests()),
                new Test("Pilot.setCurrentHP()", runSetPilotCurrentHPTests()),
                new Test("Pilot.setSkillTriggers()", runSetPilotSkillTriggersTests(), new Test[] {
                    new Test("SkillTriggersList", new Test[] {
                        new Test("SkillTriggersList.hasSkillTrigger()", runHasSkillTriggerTests()),
                        new Test("SkillTriggersList.getSkillTrigger()", runGetSkillTriggerTests()),
                        new Test("SkillTriggersList.setSkillTriggers()", runSetSkillTriggersTests(), new Test[] {
                            new Test("SkillTrigger", runSkillTriggerTests(), new Test[] {
                                new Test("SkillTrigger.equals(Object)", runSkillTriggerEqualsTests()),
                                new Test("SkillTrigger.equals(SkillTrigger)", runSkillTriggerEqualsSkillTriggerTests()),
                                new Test("SkillTrigger.copyOf()", runSkillTriggerCopyOfTests())
                            })
                        }),    
                        new Test("SkillTriggersList.equals(Object)", runSkillTriggersListEqualsTests()),
                        new Test("SkillTriggersList.equals(SkillTriggersList)", runSkillTriggersListEqualsSkillTriggersListTests()),
                        new Test("SkillTriggersList.copyOf()", runSkillTriggersListCopyOfTests()),
                        new Test("SkillTriggersList.generateOutput()", runSkillTriggersListGenerateOutputTests())
                    })
                }),
                new Test("Pilot.setReserves()", runSetReservesTests()),
                new Test("Pilot.setLoadout()", runSetLoadoutTests(), new Test[] {
                    new Test("Loadout", new Test[] {
                        new Test("Loadout.setPilotArmor()", runLoadoutSetPilotArmorTests()),
                        new Test("Loadout.setPilotWeapons()", runLoadoutSetPilotWeaponsTests()),
                        new Test("Loadout.setPilotGear()", runLoadoutSetPilotGearTests()),
                        new Test("Loadout.equals(Object)", runLoadoutEqualsTests()),
                        new Test("Loadout.equals(Loadout)", runLoadoutEqualsLoadoutTests()),
                        new Test("Loadout.copyOf()", runLoadoutCopyOfTests()),
                        new Test("Loadout.generateOutput()", runLoadoutGenerateOutputTests())
                    })
                }),
                new Test("Pilot.setLicenseLevel()", runSetLicenseLevelTests()),
                new Test("Pilot.setLicenseList()", runSetLicenseListTests(), new Test[] {
                    new Test("License", new Test[] {
                        new Test("License.equals(Object)", runLicenseEqualsTests()),
                        new Test("License.equals(License)", runLicenseEqualsLicenseTests()),
                        new Test("License.copyOf()", runLicenseCopyOfTests()),
                        new Test("License.outputName()", runLicenseOutputNameTests()),
                        new Test("License.outputLicense()", runLicenseOutputLicenseTests())
                    })
                }),
                new Test("Pilot.setSpecialEquipment()", runSetSpecialEquipmentTests()),
                new Test("Pilot.setMechSkills()", runSetMechSkillsTests()),
                new Test("Pilot.setCoreBonuses()", runSetCoreBonusesTests()),
                new Test("Pilot.setTalents()", runSetTalentsTests(), new Test[] {
                    new Test("Talent", new Test[] {
                        new Test("Talent.equals(Object)", runTalentEqualsTests()),
                        new Test("Talent.equals(Talent)", runTalentEqualsTalentTests()),
                        new Test("Talent.copyOf()", runTalentCopyOfTests()),
                        new Test("Talent.outputName()", runTalentOutputNameTests())
                    })
                }),
                new Test("Pilot.copyOf()", runPilotCopyOfTests()),
                new Test("Pilot.generateOutput()", new Test[] {
                    new Test("Pilot.generateOutput(\"mech build\")", runPilotGenerateMechOutputTests()),
                    new Test("Pilot.generateOutput(\"pilot\")", runPilotGeneratePilotOutputTests()),
                    new Test("Pilot.generateOutput(\"full\")", runPilotGenerateFullOutputTests())
                }),
                new Test("Pilot.outputLicenses()", runOutputLicensesTests()),
                new Test("Pilot.outputTalents()", runOutputTalentsTests()),
                new Test("Pilot.outputCoreBonuses()", runOutputCoreBonusesTests())
            }),
            new Test("Mech", new Test[] {
                new Test("Mech.setName()", runMechSetNameTests()),
                new Test("Mech.setFrame()", runMechSetFrameTests(), new Test[] {
                    new Test("Frame", new Test[] {
                        new Test("Frame.setManufacturer()", runFrameSetManufacturerTests()),
                        new Test("Frame.setName()", runFrameSetNameTests()),
                        new Test("Frame.setID()", runFrameSetIDTests()),
                        new Test("Frame.setFrameEnum()", runFrameSetFrameEnumTests()),
                        new Test("Frame.setRole()", runFrameSetRoleTests()),
                        new Test("Frame.setFrameDescription()", runFrameSetFrameDescriptionTests()),
                        new Test("Frame.setSize()", runFrameSetSizeTests()),
                        new Test("Frame.setStructure()", runFrameSetStructureTests()),
                        new Test("Frame.setHP()", runFrameSetHPTests()),
                        new Test("Frame.setArmor()", runFrameSetArmorTests()),
                        new Test("Frame.setStress()", runFrameSetStressTests()),
                        new Test("Frame.setHeatCapacity()", runFrameSetHeatCapacityTests()),
                        new Test("Frame.setEvasion()", runFrameSetEvasionTests()),
                        new Test("Frame.setSpeed()", runFrameSetSpeedTests()),
                        new Test("Frame.setEDefense()", runFrameSetEDefenseTests()),
                        new Test("Frame.setSensors()", runFrameSetSensorsTests()),
                        new Test("Frame.setRepairCapacity()", runFrameSetRepairCapacityTests()),
                        new Test("Frame.setSaveTarget()", runFrameSetSaveTargetTests()),
                        new Test("Frame.setSystemPoints()", runFrameSetSystemPointsTests()),
                        new Test("Frame.setTraits()", runFrameSetTraitsTests()),
                        new Test("Frame.setMounts()", runFrameSetMountsTests(), new Test[] {
                            new Test("Mount", new Test[] {
                                new Test("Mount.setMountType()", runMountSetMountTypeTests()),
                                new Test("Mount.setWeapon()", runMountSetWeaponTests(), new Test[] {
                                    new Test("Equipment", new Test[] {
                                        new Test("Equipment.hasTag()", runEquipmentHasTagTests()),
                                        new Test("Equipment.getTag()", runEquipmentGetTagTests()),
                                        new Test("Equipment.setName()", runEquipmentSetNameTests()),
                                        new Test("Equipment.setTags()", runEquipmentSetTagsTests()),
                                        new Test("Equipment.checkTagsArray()", runEquipmentCheckTagsArrayTests()),
                                        new Test("Equipment.copyOf()", runEquipmentCopyOfTests()),
                                        new Test("Weapon", new Test[] {
                                            new Test("Weapon.setSize()", runWeaponSetSizeTests()),
                                            new Test("Weapon.setType()", runWeaponSetTypeTests()),
                                            new Test("Weapon.setTags()", runWeaponSetTagsTests()),
                                            new Test("Weapon.copyOf()", runWeaponCopyOfTests()),
                                        })
                                    })
                                }),
                                new Test("Mount.setModification()", runMountSetModificationTests()),
                                new Test("Mount.setCoreBonus()", runMountSetCoreBonusTests()),
                                new Test("Mount.setTalent()", runMountSetTalentTests()),
                                new Test("Mount.isUnmodified()", runMountIsUnmodifiedTests()),
                                new Test("Mount.copyOf()", runMountCopyOfTests()),
                                new Test("Mount.outputWeapon()", runMountOutputWeaponTests())
                            })
                        }),
                        new Test("Frame.copyOf()", runFrameCopyOfTests()),
                        new Test("Frame.outputName()", runFrameOutputNameTests())
                    }),
                }),
                new Test("Mech.setOperatorNotes()", runMechSetOperatorNotesTests()),
                new Test("Mech.setCurrentStructure()", runMechSetCurrentStructureTests()),
                new Test("Mech.setCurrentHP()", runMechSetCurrentHPTests()),
                new Test("Mech.setCurrentStress()", runMechSetCurrentStressTests()),
                new Test("Mech.setCurrentHeat()", runMechSetCurrentHeatTests()),
                new Test("Mech.setCurrentRepairs()", runMechSetCurrentRepairsTests()),
                new Test("Mech.setMount()", runMechSetMountTests()),
                new Test("Mech.setSystems()", runMechSetSystemsTests(), new Test[] {
                    new Test("MechSystem", new Test[] {
                        new Test("MechSystem.setTags()", runMechSystemSetTagsTests()),
                        new Test("MechSystem.copyOf()", runMechSystemCopyOfTests()),
                        new Test("MechSystem.outputSystem()", runMechSystemOutputSystemTests())
                    })
                }),
                new Test("Mech.copyOf()", runMechCopyOfTests()),
                new Test("Mech.outputSize()", runMechOutputSizeTests()),
                new Test("Mech.calculateAttributes()", runMechCalculateAttributesTests()),
                new Test("Mech.generateOutput()", runMechGenerateOutputTests()),
                new Test("Mech.outputStats()", runMechOutputStatsTests()),
                new Test("Mech.outputWeapons()", runMechOutputWeaponsTests()),
                new Test("Mech.outputSystems()", runMechOutputSystemsTests()),
                new Test("Mech.outputSystem()", runMechOutputSystemTests()),
            })
        });
        String result = allTests.toString();
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
    /**
     * Compares two Strings and prints information about each one.
     * @param string1
     * @param string2
     */
    public static void compareStrings(String string1, String string2) {
        char char1;
        char char2;

        if (string1.equals(string2)) {
            System.out.println("The two Strings are the same.");
        } else {
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
    }
    /**
     * Tests Pilot.setPilotName().
     * @return a boolean representing whether the method passed.
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
     * @return a boolean representing whether the method passed.
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
     * @return a boolean representing whether the method passed.
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
     * @return a boolean representing whether the method passed.
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
     * @return a boolean representing whether the method passed.
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
        pilot.setBackground("validbackground");
        if (pilot.getBackground().equals("validbackground")) {
            test2 = true;
        }

        return test1 && test2;
    }
    /**
     * Tests Pilot.setBiography().
     * @return a boolean representing whether the method passed.
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
     * @return a boolean representing whether the method passed.
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
     * @return a boolean representing whether the method passed.
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
     * Tests Pilot.setCurrentHP().
     * @return a boolean representing whether the method passed.
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
            pilot.setCurrentHP(15);
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
     * Tests Pilot.setSkillTriggers().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runSetPilotSkillTriggersTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        SkillTriggersList skillTriggersList = new SkillTriggersList();
        Skill skillTrigger;
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
        skillTrigger = new Skill("validSkillTrigger", 2);
        skillTriggersList.setSkillTriggers(new Skill[] {
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
     * Tests SkillTriggersList.hasSkillTrigger().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runHasSkillTriggerTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests SkillTriggersList.getSkillTrigger().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runGetSkillTriggerTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests SkillTriggersList.setSkillTriggers().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runSetSkillTriggersTests() {
        SkillTriggersList skillTriggers = new SkillTriggersList();
        Skill skillTrigger;
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
        skillTriggers.setSkillTriggers(new Skill[0]);
        if (skillTriggers.getSkillTriggers().getClass() == Skill[].class
            && skillTriggers.getSkillTriggers().length == 0) {
            test2 = true;
        }
        // normal case
        try {
            // = SkillTrigger[] {null}
            skillTriggers.setSkillTriggers(new Skill[1]);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        skillTrigger = new Skill("Apply Fists to Faces", 2);
        skillTriggers.setSkillTriggers(new Skill[] {
            skillTrigger
        });
        if (skillTriggers.getSkillTriggers()[0].equals(skillTrigger)) {
            test4 = true;
        }

        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests the SkillTrigger class and all its methods.
     * @return a boolean representing whether the class passed.
     */
    private static boolean runSkillTriggerTests() {
        Skill skillTrigger = new Skill(
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
            skillTrigger = new Skill(null, 2);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        skillTrigger = new Skill("validName", 2);
        if (skillTrigger.getName().equals("validName")) {
            test2 = true;
        }
        // normal case
        try {
            skillTrigger = new Skill("Apply Fists to Faces", -2);
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            skillTrigger = new Skill("Apply Fists to Faces",
                7);
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        try {
            skillTrigger = new Skill("Apply Fists to Faces",
                0);
        } catch (IllegalArgumentException exception) {
            test5 = true;
        }
        // normal case
        try {
            skillTrigger = new Skill("Apply Fists to Faces",
                1);
        } catch (IllegalArgumentException exception) {
            test6 = true;
        }
        // normal case
        try {
            skillTrigger = new Skill("Apply Fists to Faces",
                3);
        } catch (IllegalArgumentException exception) {
            test7 = true;
        }
        // normal case
        try {
            skillTrigger = new Skill("Apply Fists to Faces",
                5);
        } catch (IllegalArgumentException exception) {
            test8 = true;
        }
        // normal case
            skillTrigger = new Skill("Apply Fists to Faces",
                2);
        if (skillTrigger.getLevel() == 2) {
            test9 = true;
        }
        
        return test1 && test2 && test3 && test4 && test5 && test6 && test7
            && test8 && test9;
    }
    /**
     * Tests SkillTrigger.equals(Object).
     * @return a boolean representing whether the method passed.
     */
    private static boolean runSkillTriggerEqualsTests() {
        Skill skillTrigger = new Skill(
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
     * @return a boolean representing whether the method passed.
     */
    private static boolean runSkillTriggerEqualsSkillTriggerTests() {
        Skill skillTrigger = new Skill(
            "Apply Fists to Faces", 4);
        Skill testSkillTrigger = new Skill("validName",
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
        skillTrigger = new Skill("Apply Fists to Faces", 2);
        if (! skillTrigger.equals(testSkillTrigger)) {
            test2 = true;
        }
        // normal case
        skillTrigger = new Skill("validName", 4);
        if (! skillTrigger.equals(testSkillTrigger)) {
            test3 = true;
        }
        // normal case
        skillTrigger = new Skill("validName", 2);
        if (skillTrigger.equals(testSkillTrigger)) {
            test4 = true;
        }

        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests SkillTrigger.copyOf().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runSkillTriggerCopyOfTests() {
        Skill original = new Skill("validName", 2);
        Skill copy;
        boolean test1 = false;
        boolean test2 = false;

        // normal case
        copy = new Skill(original);
        if (original != copy) {
            test1 = true;
        }
        if (original.equals(copy)) {
            test2 = true;
        }

        return test1 && test2;
    }
    /**
     * Tests SkillTriggersList.equals(Object).
     * @return a boolean representing whether the method passed.
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
     * @return a boolean representing whether the method passed.
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
     * Tests SkillTriggersList.copyOf().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runSkillTriggersListCopyOfTests() {
        Skill skillTrigger = new Skill("validName", 2);
        SkillTriggersList original = new SkillTriggersList(new Skill[] {
            skillTrigger
        });
        SkillTriggersList copy;
        boolean test1 = false;
        boolean test2 = false;

        // normal case
        copy = new SkillTriggersList(original);
        if (original != copy) {
            test1 = true;
        }
        if (original.equals(copy)) {
            test2 = true;
        }

        return test1 && test2;
    }
    /**
     * Tests SkillTriggersList.generateOutput().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runSkillTriggersListGenerateOutputTests() {
        SkillTriggersList skillTriggers = new SkillTriggersList();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

        // normal case
        skillTriggers.setSkillTriggers(new Skill[0]);
        if (skillTriggers.toString().equals("  N/A\n")) {
            test1 = true;
        }
        // normal case
        skillTriggers.setSkillTriggers(new Skill[] {
            new Skill("Apply Fists to Faces", 2)
        });
        String testString1 = "  Apply Fists to Faces (+2)\n";
        if (skillTriggers.toString().equals(testString1)) {
            test2 = true;
        }
        // normal case
        skillTriggers.setSkillTriggers(new Skill[] {
            new Skill("Apply Fists to Faces", 2),
            new Skill("Assault", 4)
        });
        String testString2 = "  Apply Fists to Faces (+2), Assault (+4)\n";
        if (skillTriggers.toString().equals(testString2)) {
            test3 = true;
        }
        // normal case
        skillTriggers.setSkillTriggers(new Skill[] {
            new Skill("Apply Fists to Faces", 2),
            new Skill("Assault", 4),
            new Skill("Blow Something Up", 6)
        });
        String testString3 = "  Apply Fists to Faces (+2), Assault (+4),\n"
            + "  Blow Something Up (+6)\n";
        if (skillTriggers.toString().equals(testString3)) {
            test4 = true;
        }

        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests Pilot.setReserves().
     * @return a boolean representing whether the method passed.
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
     * @return a boolean representing whether the method passed.
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
     * Tests Loadout.setPilotArmor().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runLoadoutSetPilotArmorTests() {
        Loadout loadout = new Loadout();
        boolean test1 = false;
        boolean test2 = false;

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
        
        return test1 && test2;
    }
    /**
     * Tests Loadout.setPilotWeapons().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runLoadoutSetPilotWeaponsTests() {
        Loadout loadout = new Loadout();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

        // normal case
        try {
            loadout.setPilotWeapons(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        try {
            loadout.setPilotWeapons(new String[] {"validWeapon"});
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        try {
            loadout.setPilotWeapons(new String[] {"validWeapon", null});
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        loadout.setPilotWeapons(new String[] {"validWeapon", "validWeapon"});
        if (loadout.getPilotWeapons().getClass() == String[].class
            && loadout.getPilotWeapons().length == 2
            && loadout.getPilotWeapons()[0].equals("validWeapon")
            && loadout.getPilotWeapons()[1].equals("validWeapon")) {
            test4 = true;
        }
        
        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests Loadout.setPilotGear().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runLoadoutSetPilotGearTests() {
        Loadout loadout = new Loadout();
        Loadout testLoadout = new Loadout();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;

        // normal case
        try {
            loadout.setPilotGear(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        try {
            loadout.setPilotGear(new String[] {"validGear"});
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        try {
            loadout.setPilotGear(new String[] {"validGear", null, "validGear"});
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        testLoadout.setPilotGear(new String[] {"validGear", "", "validGear"});
        loadout.setPilotGear(new String[] {"validGear", "", "validGear"});
        if (loadout.equals(testLoadout)) {
            test4 = true;
        }
        // normal case
        testLoadout.setPilotGear(new String[] {"validGear", "validGear",
            "validGear"});
        loadout.setPilotGear(new String[] {"validGear", "validGear",
            "validGear"});
        if (loadout.getPilotGear().getClass() == String[].class
            && loadout.getPilotGear().length == 3
            && loadout.getPilotGear()[0].equals("validGear")
            && loadout.getPilotGear()[1].equals("validGear")
            && loadout.getPilotGear()[2].equals("validGear")) {
            test5 = true;
        }
        
        return test1 && test2 && test3 && test4 && test5;
    }
    /**
     * Tests Loadout.equals(Object).
     * @return a boolean representing whether the method passed.
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
     * @return a boolean representing whether the method passed.
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

        return test1 && test2 && test3 && test4 && test5;
    }
    /**
     * Tests Loadout.copyOf().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runLoadoutCopyOfTests() {
        Loadout original = new Loadout();
        Loadout copy;
        boolean test1 = false;
        boolean test2 = false;

        original.setPilotArmor("validArmor");
        original.setPilotWeapons(new String[] {"validWeapon", "validWeapon"});
        original.setPilotGear(new String[] {"validGear", "validGear",
            "validGear"});
        copy = new Loadout(original);
        if (original != copy) {
            test1 = true;
        }
        if (original.equals(copy)) {
            test2 = true;
        }

        return test1 && test2;
    }
    /**
     * Tests Loadout.generateOutput().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runLoadoutGenerateOutputTests() {
        Loadout loadout = new Loadout();
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = false;
        boolean test7 = false;
        boolean test8 = false;
        boolean test9 = false;
        boolean test10 = false;
        boolean test11 = false;

        // normal case
        String testString1 = "  N/A\n";
        if (loadout.toString().equals(testString1)) {
            test1 = true;
        }
        // normal case
        String testString2 = "  validArmor\n";
        loadout = new Loadout("validArmor", new String[] {"", ""},
            new String[] {"", "", ""});
        if (loadout.toString().equals(testString2)) {
            test2 = true;
        }
        // normal case
        String testString3 = "  validWeapon\n";
        loadout = new Loadout("", new String[] {"validWeapon", ""},
            new String[] {"", "", ""});
        if (loadout.toString().equals(testString3)) {
            test3 = true;
        }
        // normal case
        String testString4 = "  validWeapon, validWeapon\n";
        loadout = new Loadout("", new String[] {"validWeapon",
            "validWeapon"}, new String[] {"", "", ""});
        if (loadout.toString().equals(testString4)) {
            test4 = true;
        }
        // normal case
        String testString5 = "  validArmor, validWeapon,\n  validWeapon\n";
        loadout = new Loadout("validArmor",
            new String[] {"validWeapon", "validWeapon"}, new String[] {"", "",
            ""});
        if (loadout.toString().equals(testString5)) {
            test5 = true;
        }
        // normal case
        String testString6 = "  validGear\n";
        loadout = new Loadout("", new String[] {"", ""},
            new String[] {"validGear", "", ""});
        if (loadout.toString().equals(testString6)) {
            test6 = true;
        }
        // normal case
        String testString7 = "  validGear, validGear\n";
        loadout = new Loadout("", new String[] {"", ""},
            new String[] {"validGear", "validGear", ""});
        if (loadout.toString().equals(testString7)) {
            test7 = true;
        }
        // normal case
        String testString8 = "  validArmor, validGear,\n  validGear\n";
        loadout = new Loadout("validArmor", new String[] {"", ""},
            new String[] {"validGear", "validGear", ""});
        if (loadout.toString().equals(testString8)) {
            test8 = true;
        }
        // normal case
        String testString9 = "  validWeapon, validGear,\n  validGear\n";
        loadout = new Loadout("", new String[] {"validWeapon", ""},
            new String[] {"validGear", "validGear", ""});
        if (loadout.toString().equals(testString9)) {
            test9 = true;
        }
        // normal case
        String testString10 = "  validWeapon, validWeapon,\n  validGear,"
            + " validGear\n";
        loadout = new Loadout("",
            new String[] {"validWeapon", "validWeapon"},
            new String[] {"validGear", "validGear", ""});
        if (loadout.toString().equals(testString10)) {
            test10 = true;
        }
        // normal case
        String testString11 = "  validArmor, validWeapon,\n  validWeapon,"
            + " validGear,\n  validGear\n";
        loadout = new Loadout("validArmor",
            new String[] {"validWeapon", "validWeapon"}, new String[] {
            "validGear", "validGear", ""});
        if (loadout.toString().equals(testString11)) {
            test11 = true;
        }

        return test1 && test2 && test3 && test4 && test5 && test6 && test7
            && test8 && test9 && test10 && test11;
    }
    /**
     * Tests Pilot.setLicenseLevel().
     * @return a boolean representing whether the method passed.
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
     * @return a boolean representing whether the method passed.
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
     * Tests License.equals(Object).
     * @return a boolean representing whether the method passed.
     */
    private static boolean runLicenseEqualsTests() {
        License license = new License("Blackbeard", 1);
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
     * @return a boolean representing whether the method passed.
     */
    private static boolean runLicenseEqualsLicenseTests() {
        License license = new License("Blackbeard", 1);
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
        if (! license.equals(new License("Blackbeard",
            2))) {
            test3 = true;
        }
        // normal case
        license = new License("validFrame", 2);
        if (license.equals(new License("validFrame",
            2))) {
            test4 = true;
        }

        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests License.copyOf().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runLicenseCopyOfTests() {
        License original = new License("validFrame", 1);
        License copy;
        boolean test1 = false;
        boolean test2 = false;

        copy = new License(original);
        if (original != copy) {
            test1 = true;
        }
        if (original.equals(copy)) {
            test2 = true;
        }

        return test1 && test2;
    }
    /**
     * Tests License.outputName().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runLicenseOutputNameTests() {
        License license;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;

        license = new License("blackbeard", 1);
        if (license.outputName().equals("Blackbeard")) {
            test1 = true;
        }
        license = new License("death's head", 1);
        if (license.outputName().equals("Death's Head")) {
            test2 = true;
        }
        license = new License("swallowtail (ranger variant)",
            1);
        if (license.outputName().equals(
            "Swallowtail (Ranger Variant)")) {
            test3 = true;
        }
        
        return test1 && test2 && test3;
    }
    /**
     * Tests License.outputLicense().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runLicenseOutputLicenseTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Pilot.setSpecialEquipment().
     * @return a boolean representing whether the method passed.
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
            pilot.setSpecialEquipment(new String[] {"validequipment", null});
        } catch (IllegalArgumentException exception) {
            test3 = true;
        }
        // normal case
        try {
            pilot.setSpecialEquipment(new String[] {"validequipment", ""});
        } catch (IllegalArgumentException exception) {
            test4 = true;
        }
        // normal case
        pilot.setSpecialEquipment(new String[] {"validequipment"});
        if (pilot.getSpecialEquipment().getClass() == String[].class
            && pilot.getSpecialEquipment().length == 1
            && pilot.getSpecialEquipment()[0].equals(
            "validequipment")) {
            test5 = true;
        }

        return test1 && test2 && test3 && test4 && test5;
    }
    /**
     * Tests Pilot.setMechSkills().
     * @return a boolean representing whether the method passed.
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
     * @return a boolean representing whether the method passed.
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
                "validcorebonus",
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
                "auto-stabilizing hardpoints",
                ""
            });
        } catch (IllegalArgumentException exception) {
            test6 = true;
        }
        // normal case
        pilot.setCoreBonuses(new String[] {
            "auto-stabilizing hardpoints"
        });
        if (pilot.getCoreBonuses()[0].equals(
            "auto-stabilizing hardpoints")) {
            test7 = true;
        }

        return test1 && test2 && test3 && test4 && test5 && test6 && test7;
    }
    /**
     * Tests Pilot.setTalents().
     * @return a boolean representing whether the method passed.
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
     * Tests Talent.equals(Object).
     * @return a boolean representing whether the method passed.
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
     * @return a boolean representing whether the method passed.
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
        talent = new Talent("Ace", 1);
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
     * Tests Talent.copyOf().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runTalentCopyOfTests() {
        Talent original = new Talent("validTalent", 1);
        Talent copy;
        boolean test1 = false;
        boolean test2 = false;

        copy = new Talent(original);
        if (original != copy) {
            test1 = true;
        }
        if (original.equals(copy)) {
            test2 = true;
        }

        return test1 && test2;
    }
    /**
     * Tests Talent.outputName().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runTalentOutputNameTests() {
        Talent talent;
        boolean test1 = false;
        boolean test2 = false;

        talent = new Talent("ace", 1);
        if (talent.outputName().equals("Ace")) {
            test1 = true;
        }
        talent = new Talent("combined arms", 1);
        if (talent.outputName().equals("Combined Arms")) {
            test2 = true;
        }
        
        return test1 && test2;
    }
    /**
     * Tests Pilot.copyOf().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runPilotCopyOfTests() {
        Pilot original = new Pilot("validName",
            "validCallsign");
        Pilot copy;
        boolean test1 = false;
        boolean test2 = false;

        copy = new Pilot(original);
        if (original != copy) {
            test1 = true;
        }
        if (original.equals(copy)) {
            test2 = true;
        }

        return test1 && test2;
    }
    /**
     * Tests Pilot.generateOutput("mech build").
     * @return a boolean representing whether the method passed.
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
            new License("Blackbeard", 1),
            new License("Drake", 2),
            new License("Lancaster", 3)
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
     * @return a boolean representing whether the method passed.
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
            new License("Blackbeard", 1),
            new License("Drake", 2),
            new License("Lancaster", 3)
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
     * @return a boolean representing whether the method passed.
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
            new License("Blackbeard", 1),
            new License("Drake", 2),
            new License("Lancaster", 3)
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
     * @return a boolean representing whether the method passed.
     */
    private static boolean runOutputLicensesTests() {
        Pilot pilot = new Pilot("validName",
            "validCallsign");
        License[] licenseList;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;

        licenseList = new License[] {
            new License("Blackbeard", 1),
            new License("Drake", 2),
            new License("Lancaster", 3)
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
     * @return a boolean representing whether the method passed.
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
     * @return a boolean representing whether the method passed.
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
     * Tests Mech.setName().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechSetNameTests() {
        Mech mech = new Mech("validName", FrameEnum.EVEREST);
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;

        // normal case
        try {
            mech.setName(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        try {
            mech.setName("");
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        // normal case
        mech.setName("validName");
        if (mech.getName().equals("validName")) {
            test3 = true;
        }
        
        return test1 && test2 && test3;
    }
    /**
     * Tests Mech.setFrame().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechSetFrameTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setManufacturer().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetManufacturerTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setName().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetNameTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setID().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetIDTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setFrameEnum().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetFrameEnumTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setRole().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetRoleTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setFrameDescription().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetFrameDescriptionTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setSize().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetSizeTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setStructure().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetStructureTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setHP().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetHPTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setArmor().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetArmorTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setStress().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetStressTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setHeatCapacity().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetHeatCapacityTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setEvasion().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetEvasionTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setSpeed().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetSpeedTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setEDefense().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetEDefenseTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setSensors().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetSensorsTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setRepairCapacity().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetRepairCapacityTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setSaveTarget().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetSaveTargetTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setSystemPoints().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetSystemPointsTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setTraits().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetTraitsTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.setMounts().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameSetMountsTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mount.setMountType().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMountSetMountTypeTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mount.setWeapon().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMountSetWeaponTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Equipment.hasTag().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runEquipmentHasTagTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Equipment.getTag().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runEquipmentGetTagTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Equipment.setName().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runEquipmentSetNameTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Equipment.setTags().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runEquipmentSetTagsTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Equipment.checkTagsArray().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runEquipmentCheckTagsArrayTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Equipment.copyOf().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runEquipmentCopyOfTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Weapon.setSize().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runWeaponSetSizeTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Weapon.setType().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runWeaponSetTypeTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Weapon.setTags().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runWeaponSetTagsTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Weapon.copyOf().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runWeaponCopyOfTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mount.setModification().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMountSetModificationTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mount.setCoreBonus().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMountSetCoreBonusTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mount.setTalent().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMountSetTalentTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mount.isUnmodified().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMountIsUnmodifiedTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mount.copyOf().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMountCopyOfTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mount.outputWeapon().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMountOutputWeaponTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.copyOf().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameCopyOfTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Frame.outputWeapon().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runFrameOutputNameTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mech.setOperatorNotes().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechSetOperatorNotesTests() {
        Mech mech = new Mech("validName", FrameEnum.EVEREST);
        boolean test1 = false;
        boolean test2 = false;

        // normal case
        try {
            mech.setOperatorNotes(null);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        // normal case
        mech.setOperatorNotes("validOperatorNotes");
        if (mech.getOperatorNotes().equals("validOperatorNotes")) {
            test2 = true;
        }
        
        return test1 && test2;
    }
    /**
     * Tests Mech.setCurrentStructure().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechSetCurrentStructureTests() {
        Mech mech = new Mech("validName", FrameEnum.EVEREST);
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

        try {
            mech.setCurrentStructure(-1);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        try {
            mech.setCurrentStructure(5);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        mech.setCurrentStructure(0);
        if (mech.getCurrentStructure() == 0) {
            test3 = true;
        }
        mech.setCurrentStructure(4);
        if (mech.getCurrentStructure() == 4) {
            test4 = true;
        }
        
        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests Mech.setCurrentHP().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechSetCurrentHPTests() {
        Mech mech = new Mech("validName", FrameEnum.EVEREST);
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

        try {
            mech.setCurrentHP(-1);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        try {
            mech.setCurrentHP(11);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        mech.setCurrentHP(0);
        if (mech.getCurrentHP() == 0) {
            test3 = true;
        }
        mech.setCurrentHP(10);
        if (mech.getCurrentHP() == 10) {
            test4 = true;
        }
        
        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests Mech.setCurrentStress().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechSetCurrentStressTests() {
        Mech mech = new Mech("validName", FrameEnum.EVEREST);
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

        try {
            mech.setCurrentStress(-1);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        try {
            mech.setCurrentStress(5);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        mech.setCurrentStress(0);
        if (mech.getCurrentStress() == 0) {
            test3 = true;
        }
        mech.setCurrentStress(4);
        if (mech.getCurrentStress() == 4) {
            test4 = true;
        }
        
        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests Mech.setCurrentHeat().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechSetCurrentHeatTests() {
        Mech mech = new Mech("validName", FrameEnum.EVEREST);
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

        try {
            mech.setCurrentHeat(-1);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        try {
            mech.setCurrentHeat(7);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        mech.setCurrentHeat(0);
        if (mech.getCurrentHeat() == 0) {
            test3 = true;
        }
        mech.setCurrentHeat(6);
        if (mech.getCurrentHeat() == 6) {
            test4 = true;
        }
        
        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests Mech.setCurrentRepairs().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechSetCurrentRepairsTests() {
        Mech mech = new Mech("validName", FrameEnum.EVEREST);
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;

        try {
            mech.setCurrentRepairs(-1);
        } catch (IllegalArgumentException exception) {
            test1 = true;
        }
        try {
            mech.setCurrentRepairs(6);
        } catch (IllegalArgumentException exception) {
            test2 = true;
        }
        mech.setCurrentRepairs(0);
        if (mech.getCurrentRepairs() == 0) {
            test3 = true;
        }
        mech.setCurrentRepairs(5);
        if (mech.getCurrentRepairs() == 5) {
            test4 = true;
        }
        
        return test1 && test2 && test3 && test4;
    }
    /**
     * Tests Mech.setMount().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechSetMountTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mech.setSystems().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechSetSystemsTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests MechSystem.setTags().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechSystemSetTagsTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests MechSystem.copyOf().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechSystemCopyOfTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests MechSystem.outputSystem().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechSystemOutputSystemTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mech.copyOf().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechCopyOfTests() {
        Mech original;
        Mech copy;
        Frame frame1;
        Frame frame2;
        Mount mount1;
        Mount mount2;
        MechSystem system1;
        MechSystem system2;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = true;
        boolean test4 = false;
        boolean test5 = false;
        boolean test6 = true;
        boolean test7 = false;
        boolean test8 = false;
        boolean test9 = false;
        boolean test10 = false;
        boolean test11 = false;
        boolean test12 = false;
        boolean test13 = false;
        boolean test14 = false;
        boolean test15 = false;
        boolean test16 = false;
        boolean test17 = false;
        boolean test18 = false;
        boolean test19 = false;
        boolean test20 = false;
        boolean test21 = false;
        boolean test22 = false;
        boolean test23 = false;
        boolean test24 = false;
        boolean test25 = false;
        boolean test26 = false;
        boolean test27 = false;
        boolean test28 = false;
        boolean test29 = true;
        boolean test30 = true;
        boolean test31 = true;

        original = new Mech("Wraith", FrameEnum.SWALLOWTAIL_RANGER);
        original.setOperatorNotes("validOperatorNotes");
        original.setMount(0, new Mount("aux",
            Database.getWeapon("Slag Cannon")));
        original.setMount(1, new Mount("aux",
            Database.getWeapon("Vulture DMR"),
            "", "Overpower Caliber"));
        original.setSystems(new MechSystem[] {
            Database.getSystem("Pattern-A Smoke Charges"),
            Database.getSystem("Seismic Ripper"),
            Database.getSystem("High-Stress Mag Clamps"),
            Database.getSystem("ATHENA-Class NHP"),
            Database.getSystem("Markerlight"),
            Database.getSystem("IMMOLATE"),
            Database.getSystem("Wandering Nightmare")
        });
        copy = new Mech(original);
        if (original != copy) {
            test1 = true;
        }
        if (original.getName().equals(copy.getName())) {
            test2 = true;
        }
        frame1 = original.getFrame();
        frame2 = copy.getFrame();
        if (! frame1.getManufacturer().equals(frame2.getManufacturer())) {
            test3 = false;
        } else if (! frame1.getName().equals(frame2.getName())) {
            test3 = false;
        } else if (! frame1.getID().equals(frame2.getID())) {
            test3 = false;
        } else if (frame1.getFrameEnum() != frame2.getFrameEnum()) {
            test3 = false;
        } else if (frame1.getRole().getClass() == String[].class // TODO: FIX
            && frame1.getRole().getClass() == frame2.getRole().getClass()
            && frame1.getRole().length == frame2.getRole().length) {
            for (int i = 0; i < frame1.getRole().length; i++) {
                if (! frame1.getRole()[i].equals(frame2.getRole()[i])) {
                    test3 = false;
                    break;
                }
            }
        } else if (! frame1.getFrameDescription().equals(
            frame2.getFrameDescription())) {
            test3 = false;
        } else if (frame1.getSize() != frame2.getSize()) {
            test3 = false;
        } else if (frame1.getStructure() != frame2.getStructure()) {
            test3 = false;
        } else if (frame1.getHP() != frame2.getHP()) {
            test3 = false;
        } else if (frame1.getArmor() != frame2.getArmor()) {
            test3 = false;
        } else if (frame1.getStress() != frame2.getStress()) {
            test3 = false;
        } else if (frame1.getHeatCapacity() != frame2.getHeatCapacity()) {
            test3 = false;
        } else if (frame1.getEvasion() != frame2.getEvasion()) {
            test3 = false;
        } else if (frame1.getSpeed() != frame2.getSpeed()) {
            test3 = false;
        } else if (frame1.getEDefense() != frame2.getEDefense()) {
            test3 = false;
        } else if (frame1.getTechAttack() != frame2.getTechAttack()) {
            test3 = false;
        } else if (frame1.getSensors() != frame2.getSensors()) {
            test3 = false;
        } else if (frame1.getRepairCapacity() !=
            frame2.getRepairCapacity()) {
            test3 = false;
        } else if (frame1.getSaveTarget() != frame2.getSaveTarget()) {
            test3 = false;
        } else if (frame1.getSystemPoints() != frame2.getSystemPoints()) {
            test3 = false;
        // TODO: FIX - this doesn't catch if any of these conditions are false
        } else if (frame1.getTraits().getClass() == String[].class
            && frame1.getTraits().getClass() ==
                frame2.getTraits().getClass()
            && frame1.getTraits().length == frame2.getTraits().length) {
            for (int i = 0; i < frame1.getRole().length; i++) {
                if (! frame1.getRole()[i].equals(frame2.getRole()[i])) {
                    test3 = false;
                    break;
                }
            }
        } else if (frame1.getMounts().getClass() == Mount[].class
            && frame1.getMounts().getClass() == frame2.getMounts().getClass()
            && frame1.getMounts().length == frame2.getMounts().length) { // TODO: FIX
            for (int i = 0; i < frame1.getMounts().length; i++) {
                mount1 = frame1.getMounts()[i];
                mount2 = frame2.getMounts()[i];
                if ((! mount1.getMountType().equals(mount2.getMountType()))
                    || (! mount1.getWeapon().getName().equals(mount2.getWeapon().getName()))
                    || mount1.hasModification() != mount2.hasModification()
                    || (! mount1.getModification().equals(mount2.getModification()))
                    || mount1.hasCoreBonus() != mount2.hasCoreBonus()) { // TODO: finish
                    test3 = false;
                    break;
                }
            }
            test3 = false;
        }
        if (original.getManufacturer().equals(copy.getManufacturer())) {
            test4 = true;
        }
        if (original.getFrameName().equals(copy.getFrameName())) {
            test5 = true;
        }
        if (original.getRole().getClass() == String[].class
            && original.getRole().getClass() == copy.getRole().getClass()
            && original.getRole().length == copy.getRole().length) {
            for (int i = 0; i < original.getRole().length; i++) {
                if (! original.getRole()[i].equals(copy.getRole()[i])) {
                    test6 = false;
                    break;
                }
            }
        }
        if (original.getFrameDescription().equals(copy.getFrameDescription())) {
            test7 = true;
        }
        if (original.getOperatorNotes().equals(copy.getOperatorNotes())) {
            test8 = true;
        }
        if (original.getSize() == copy.getSize()) {
            test9 = true;
        }
        if (original.getCurrentStructure() == copy.getCurrentStructure()) {
            test10 = true;
        }
        if (original.getMaxStructure() == copy.getMaxStructure()) {
            test11 = true;
        }
        if (original.getCurrentHP() == copy.getCurrentHP()) {
            test12 = true;
        }
        if (original.getMaxHP() == copy.getMaxHP()) {
            test13 = true;
        }
        if (original.getArmor() == copy.getArmor()) {
            test14 = true;
        }
        if (original.getCurrentStress() == copy.getCurrentStress()) {
            test15 = true;
        }
        if (original.getMaxStress() == copy.getMaxStress()) {
            test16 = true;
        }
        if (original.getCurrentHeat() == copy.getCurrentHeat()) {
            test17 = true;
        }
        if (original.getMaxHeatCapacity() == copy.getMaxHeatCapacity()) {
            test18 = true;
        }
        if (original.getEvasion() == copy.getEvasion()) {
            test19 = true;
        }
        if (original.getSpeed() == copy.getSpeed()) {
            test20 = true;
        }
        if (original.getEDefense() == copy.getEDefense()) {
            test21 = true;
        }
        if (original.getTechAttack() == copy.getTechAttack()) {
            test22 = true;
        }
        if (original.getSensors() == copy.getSensors()) {
            test23 = true;
        }
        if (original.getCurrentRepairs() == copy.getCurrentRepairs()) {
            test24 = true;
        }
        if (original.getMaxRepairCapacity() == copy.getMaxRepairCapacity()) {
            test25 = true;
        }
        if (original.getSaveTarget() == copy.getSaveTarget()) {
            test26 = true;
        }
        if (original.getSystemPoints() == copy.getSystemPoints()) {
            test27 = true;
        }
        if (original.getLimitedSystemsBonus() ==
            copy.getLimitedSystemsBonus()) {
            test28 = true;
        }
        if (original.getTraits().getClass() == String[].class
            && original.getTraits().getClass() == copy.getTraits().getClass()
            && original.getTraits().length == copy.getTraits().length) {
            for (int i = 0; i < original.getTraits().length; i++) {
                if (! original.getTraits()[i].equals(copy.getTraits()[i])) {
                    test29 = false;
                    break;
                }
            }
        }
        if (original.getMounts().getClass() == Mount[].class
            && original.getMounts().getClass() == copy.getMounts().getClass()
            && original.getMounts().length == copy.getMounts().length) {
            for (int i = 0; i < original.getMounts().length; i++) {
                mount1 = original.getMounts()[i];
                mount2 = copy.getMounts()[i];
                if (! mount1.getMountType().equals(mount2.getMountType())) {
                    test30 = false;
                    break;
                }
                if (! mount1.getWeapon().getName().equals(
                    mount2.getWeapon().getName())) {
                    test30 = false;
                    break;
                }
                if (mount1.hasModification() != mount2.hasModification()) {
                    test30 = false;
                    break;
                }
                if (! mount1.getModification().equals(
                    mount2.getModification())) {
                    test30 = false;
                    break;
                }
                if (mount1.hasCoreBonus() != mount2.hasCoreBonus()) {
                    test30 = false;
                    break;
                }
                if (! mount1.getCoreBonus().equals(
                    mount2.getCoreBonus())) {
                    test30 = false;
                    break;
                }
                if (mount1.hasTalent() != mount2.hasTalent()) {
                    test30 = false;
                    break;
                }
                if (mount1.getTalent() == null) {
                    if (mount1.getTalent() != mount2.getTalent()) {
                        test30 = false;
                        break;
                    }
                } else {
                    if (! mount1.getTalent().equals(mount2.getTalent())) {
                        test30 = false;
                        break;
                    }
                }
            }
        }
        if (original.getSystems().getClass() == MechSystem[].class
            && original.getSystems().getClass() == copy.getSystems().getClass()
            && original.getSystems().length == copy.getSystems().length) {
            for (int i = 0; i < original.getSystems().length; i++) {
                system1 = original.getSystems()[i];
                system2 = copy.getSystems()[i];
                if (! system1.getName().equals(system2.getName())) {
                    test31 = false;
                    break;
                }
                if (system1.hasTag("Limited X") !=
                    system2.hasTag("Limited X")) {
                    test31 = false;
                    break;
                }
                if (system1.hasTag("Limited X")) {
                    if (system1.getTag("Limited X") !=
                        system2.getTag("Limited X")) {
                        test31 = false;
                        break;
                    }
                }
            }
        }

        return test1 && test2 && test3 && test4 && test5 && test6 && test7
            && test8 && test9 && test10 && test11 && test12 && test13 && test14
            && test15 && test16 && test17 && test18 && test19 && test20
            && test21 && test22 && test23 && test24 && test25 && test26
            && test27 && test28 && test29 && test30 && test31;
    }
    /**
     * Tests Mech.outputSize().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechOutputSizeTests() {
        // TODO: un-comment
        Mech mech;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        boolean test5 = false;
        
        // mech = new Mech("validName", FrameEnum.CALIBAN);
        // if (mech.outputSize().equals("1/2")) {
        //     test1 = true;
        // }
        mech = new Mech("validName", FrameEnum.EVEREST);
        if (mech.outputSize().equals("1")) {
            test2 = true;
        }
        // mech = new Mech("validName", FrameEnum.SAGARMATHA);
        // if (mech.outputSize().equals("2")) {
        //     test3 = true;
        // }
        // mech = new Mech("validName", FrameEnum.BARBAROSSA);
        // if (mech.outputSize().equals("3")) {
        //     test4 = true;
        // }
        // mech = new Mech("validName", FrameEnum.SIZE_4);
        // if (mech.outputSize().equals("4")) {
        //     test5 = true;
        // }
        
        return test1 && test2 && test3 && test4 && test5;
    }
    /**
     * Tests Mech.calculateAttributes().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechCalculateAttributesTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mech.generateOutput().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechGenerateOutputTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mech.outputStats().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechOutputStatsTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mech.outputWeapons().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechOutputWeaponsTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mech.outputSystems().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechOutputSystemsTests() {
        // TODO: fill out
        return false;
    }
    /**
     * Tests Mech.outputSystem().
     * @return a boolean representing whether the method passed.
     */
    private static boolean runMechOutputSystemTests() {
        // TODO: fill out
        return false;
    }
    /*
    might be useful for when the Roll tests are made
    public static void main(String[] args) {
        int X;
        int Y;
        int Z;
        String keep;
        final int one = 1;
        final int low = 3;
        final int mid = 6;
        final int high = 20;
        
        X = high;
        Y = mid;
        Z = high;
        keep = "kh";
        for (int i = 0; i < 4; i++) {
            switch(i) {
                case 0:
                    X = one;
                    break;
                case 1:
                    X = low;
                    break;
                case 2:
                    X = mid;
                    break;
                case 3:
                    X = high;
                    break;
            }
            for (int j = 0; j < 4; j++) {
                switch(j) {
                    case 0:
                        Y = one;
                        break;
                    case 1:
                        Y = low;
                        break;
                    case 2:
                        Y = mid;
                        break;
                    case 3:
                        Y = high;
                        break;
                }
                for (int k = 0; k < 4; k++) {
                    switch(j) {
                        case 0:
                            Z = one;
                            break;
                        case 1:
                            Z = low;
                            break;
                        case 2:
                            Z = mid;
                            break;
                        case 3:
                            Z = high;
                            break;
                    }
                    for (int l = 0; l < 2; l++) {
                        switch(j) {
                            case 0:
                                keep = "kh";
                                break;
                            case 1:
                                keep = "kl";
                                break;
                        }
                        test(X, Y, Z, keep);
                        System.out.println();
                    }
                }
            }
        }
    }
    public static void test(int X, int Y, int Z, String keep) {
        if (Y != 1) {
            System.out.println("X = " + X + " | Y = " + Y + " | Z = " + Z);
            System.out.println("-----------------------------");

            if (X > Z) {
                System.out.print("Rolling XdYkhZ: ");
                Roll.roll("" + X + "d" + Y + keep + Z);

                keep = "kl";
                System.out.print("Rolling XdYklZ: ");
                Roll.roll("" + X + "d" + Y + keep + Z);
            }

            if (Z == 1) {
                keep = "kh";
                System.out.print("Rolling dYkhZ: ");
                Roll.roll("d" + Y + keep + Z);

                keep = "kl";
                System.out.print("Rolling dYklZ: ");
                Roll.roll("d" + Y + keep + Z);
            }

            keep = "kh";
            System.out.print("Rolling dYkh: ");
            Roll.roll("d" + Y + keep);

            keep = "kl";
            System.out.print("Rolling dYkl: ");
            Roll.roll("d" + Y + keep);

            if (X > Z) {
                keep = "kh";
                System.out.print("Rolling XdYkh: ");
                Roll.roll("" + X + "d" + Y + keep);

                keep = "kl";
                System.out.print("Rolling XdYkl: ");
                Roll.roll("" + X + "d" + Y + keep);
            }

            System.out.print("Rolling XdY: ");
            Roll.roll("" + X + "d" + Y);

            System.out.print("Rolling dY: ");
            Roll.roll("d" + Y);

            System.out.print("Rolling X: ");
            Roll.roll("" + X);
        }
    }
    */
}