package main.database;

public class ClassB {
    // some kind of method for every possible type of data, each with a method
    //     signature that includes a JSONObject parameter
    // some kind of add() or save() method for Class A to call once all
    //     JSONObjects are loaded
    // compile all JSONObjects into licenses and collections of data, for
    //     non-license content, and add everything to Database
    // deal with individual items and non-license stuff
    // maybe add some way of REMOVING licenses or items
    // =========================================================================
    // addLicense which adds:
    //     FrameLicense (duh)
    //         Frame
    //         <Equipment>
    //             MechSystem
    //             Modification
    //             Weapon
    // ----plus individual mutators for those types:
    // addFrame which adds Frame
    // addMechSystem which adds MechSystem
    // addModification which adds Modification
    // addWeapon which adds Weapon
    // ----the rest of the critical data types:
    // addAction which adds Action
    // addCondition which adds Condition
    // addDataTag which adds DataTag and thereby Tag
    // addManufacturer which adds Manufacturer
    // addNPCFeature which adds NPCFeature
    // addPilotArmor which adds PilotArmor
    // addPilotGear which adds PilotGear
    // addPilotWeapon which adds PilotWeapon
    // addReserve which adds Reserve
    // addSkill which adds Skill
    // addStatus which adds Status
    // addTalent which adds Talent
    // ----less important
    // addEnvironment which adds Environment
    // addSitrep which adds Sitrep
    // ----almost unimportant
    // addBackground which adds Background
    // addBond which adds Bond
    // ----just for reference
    // addRule which adds Rule
    // addTerm which adds Term
    // addTable which adds Table
    private static boolean open;
    private static boolean hasData;

    // Prevent user from instantiating this class
    private ClassB() {}

    public static void open() { // could also be named add() or save()
        if (ClassB.open) {
            if (ClassB.hasData) {
                // means the user had already added some data but didn't upload
                //     it
                // throw an exception if you hate your user
                // throw new IllegalArgumentException("Attempted to call"
                //     + " ClassB.open() when ClassB is already open. Either close"
                //     + " ClassB or flush the data before closing it");
            } else {
                // means the user just opened ClassB twice by accident lol
                // throw an exception if you REALLY hate your user
                // throw new IllegalArgumentException("Attempted to call"
                //     + " ClassB.open() when ClassB is already open");
            }
            ClassB.close();
        }
        ClassB.open = true;
    }
    public static void close() {
        if (! ClassB.open) {
            // throw an exception if you hate your user
            // throw new IllegalArgumentException("Attempted to call"
            //     + " ClassB.close() when ClassB is already closed");
        }
        ClassB.open = false;
        ClassB.uploadData();
    }
    private static void uploadData() { // TODO: find a better name
        // push all the data that's been collected over to Database
        // then flush it
        flushData();
    }
    public static void flushData() {
        // delete all the data that's been collected
        ClassB.hasData = false;
    }
}