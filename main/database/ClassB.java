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

    // Prevent user from instantiating this class
    private ClassB() {}
}