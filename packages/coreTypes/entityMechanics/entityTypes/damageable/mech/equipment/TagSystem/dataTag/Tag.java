package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.dataTag;

import Packages.CoreTypes.EntityMechanics.EntityTypes.mech.equipment.TagSystem.DataTag;
import main.HelperMethods;

/**
 * See pgs. 104 - 106.
 */
/**
 * Represents a tag for a mech system or weapon, like the tags "AI" or "Limited
 *     X". Contains information about the tag's name and value (if it has one).
 * 
 * Requires a tag name to be instantiated.
 * 
 * Used in Equipment, MechSystem, and Weapon.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public final class Tag extends DataTag {
    // TODO: find a way to override DataTag's documentation
    /**
     * The id for this tag (i.e. "tg_ai").
     * Must be a valid value for this.id (as defined by
     *     Tag.allowedTags). Cannot be null.
     */
    // protected String id;
    // TODO: find a way to override DataTag's documentation
    /**
     * The name for this tag (i.e. "AI").
     * Must be a valid value (as defined by Tag.allowedTags). Cannot be null.
     */
    // protected String name;
    // TODO: find a way to override DataTag's documentation
    /**
     * The value for this equipment tag if it has one (i.e. the "X" in
     *     "Limited X").
     * Must be a minimum of 0. If this.name is one of Tag.valueNames, must be a
     *     minimum of 1. Set to 0 on construction.
     */
    // private int value;
    /**
     * Contains an array of values for this.id for which having a value other
     *     than 0 for this.value makes sense. Case-insensitive and stored in
     *     lowercase.
     */
    private static final String[] valueIDs = new String[] {
        // Shared tags
        "tg_heat_self", "tg_limited",
        // Mech system tags
        "tg_round", "tg_turn",
        // Weapon tags
        "tg_knockback", "tg_reliable", "tg_thrown",
        // Currently unused
        "tg_burn", "tg_heat_target", "tg_line", "tg_cone", "tg_blast",
        "tg_burst", "tg_range", "tg_recharge", "tg_threat"
    };
    /**
     * Contains an array of allowed Tag values.
     */
    private static final Object[][] allowedTags = new Object[][] {
        // Shared tags
        new Object[] {"tg_heat_self", "Heat X (Self)", "Immediately after using"
            + " this weapon or system, the user takes X Heat.", false, false},
        new Object[] {"tg_inaccurate", "Inaccurate", "Attacks made with this"
            + " weapon receive +1 Difficulty.", false, false},
        new Object[] {"tg_limited", "Limited X", "This weapon or system can"
            + " only be used X times before it requires a Full Repair. Some"
            + " Limited systems, like Grenades, describe these uses as"
            + " “charges”. To use the system, the user expends a charge.",
            false, false},
        new Object[] {"tg_loading", "Loading", "This weapon must be reloaded"
            + " after each use. Mechs can reload with Stabilize and some"
            + " systems.", false, false},
        new Object[] {"tg_ordnance", "Ordnance", "This weapon can only be fired"
            + " before the user moves or takes any other actions on their turn,"
            + " excepting Protocols. The user can still act and move normally"
            + " after attacking. Additionally, because of its size, this weapon"
            + " can’t be used against targets in engagement with the user’s"
            + " mech, and cannot be used for Overwatch.", false, false},
        new Object[] {"tg_resistall", "Resistance (All)", "This unit has"
            + " Resistance to all damage types", true, false},
        new Object[] {"tg_smart", "Smart", "This weapon has self-guidance"
            + " systems, self-propelled projectiles, or even nanorobotic"
            + " ammunition. These systems are effective enough that its attacks"
            + " can’t simply be dodged – they must be scrambled or jammed."
            + " Because of this, all attacks with this weapon – including melee"
            + " and ranged attacks – use the target’s E-Defense instead of"
            + " Evasion. Targets with no E-Defense count as having 8"
            + " E-Defense.", false, false},
        // Mech system tags
        new Object[] {"tg_ai", "AI", "A mech can only have one system with this"
            + " tag installed at a time. Some AI systems grant the AI tag to"
            + " the mech. A mech with the AI tag has an NHP or COMP/CON unit"
            + " installed that can act somewhat autonomously. A pilot can"
            + " choose to hand over the controls to their AI or take control"
            + " back as a protocol. Their mech gains its own set of actions and"
            + " reactions when controlled by an AI, but the pilot can’t take"
            + " actions or reactions with it until the start of their next"
            + " turn. AIs can’t benefit from talents, and have a small chance"
            + " of cascading when they take structure damage or stress damage.",
            false, false},
        new Object[] {"tg_danger_zone", "Danger Zone", "This system, talent, or"
            + " weapon can only be used if the user is in the Danger Zone (Heat"
            + " equal to at least half of their Heat Cap).", false, false},
        new Object[] {"tg_exotic", "Exotic Gear", "EXOTIC GEAR is a general tag"
            + " for equipment that exists outside the traditional licensing"
            + " system.", true, false},
        new Object[] {"tg_free_action", "Free Action", "Characters can take"
            + " free actions at any point during their turn, and they don’t"
            + " count toward the number of quick or full actions they take."
            + " They can also be used to take actions more than once per turn.",
            true, false},
        new Object[] {"tg_grenade", "Grenade", "As a quick action, this"
            + " explosive or other device can be thrown to a space within line"
            + " of sight and the specified Range.", false, false},
        new Object[] {"tg_indestructible", "Indestructible", "This equipment"
            + " cannot be marked as Destroyed", true, false},
        new Object[] {"tg_invisible", "Invisible", "This unit is Invisible",
            true, false},
        new Object[] {"tg_invulnerable", "Invulnerable", "This equipment is"
            + " immune to all damage.", true, false},
        new Object[] {"tg_overshield", "Overshield", "This system provides HP"
            + " that disappears at the end of the scene or when a specified"
            + " condition is met. The user only retains the highest value of"
            + " Overshield applied – it does not stack. For example, if a"
            + " system provides Overshield 5 and the user gains another effect"
            + " that provides Overshield 7, they would gain Overshield 7."
            + " Damage is dealt to Overshield first, then HP. Overshield can"
            + " push a character past their maximum HP. It can’t benefit from"
            + " healing but otherwise benefits normally from anything that"
            + " would affect HP and damage (i.e., reduction, armor, etc).",
            false, false},
        new Object[] {"tg_quick_action", "Quick Action", "This system requires"
            + " a quick action to Activate.", false, false},
        new Object[] {"tg_quick_tech", "Quick Tech", "This tech can be used as"
            + " a Quick Tech Action", false, false},
        new Object[] {"tg_reaction", "Reaction", "This system can be activated"
            + " as a reaction.", false, false},
        new Object[] {"tg_round", "X/Round", "This system, trait or reaction"
            + " can be used X number of times between the start of the user’s"
            + " turn and the start of their next turn.", false, false},
        new Object[] {"tg_shield", "Shield", "This system is an energy shield"
            + " of some kind.", false, false},
        new Object[] {"tg_turn", "X/Turn", "This system, trait, or reaction can"
            + " be used X number of times in any given turn.", false, false},
        new Object[] {"tg_unique", "Unique", "This weapon or system cannot be"
            + " duplicated – each character can only have one copy of it"
            + " installed at a time.", false, false},
        // Deployable gear tags
        // All of Deployable's gear tags are shared by other classes
        // Pilot gear tags
        new Object[] {"tg_archaic", "Archaic", "This weapon is old-fashioned"
            + " and can’t harm mechs.", false, false},
        new Object[] {"tg_gear", "Gear", "This is a tool, piece of equipment,"
            + " or another item. Pilots can have up to three of these at a"
            + " time.", false},
        new Object[] {"tg_personal_armor", "Personal Armor", "This gear offers"
            + " protection in combat, but it is obvious to observers and"
            + " usually can’t be hidden. Only one piece of Personal Armor can"
            + " be worn at a time. Putting on Personal Armor takes 10–20"
            + " minutes, and while wearing it, pilots have restricted mobility"
            + " and dexterity. Nobody wears armor unless they’re expecting to"
            + " go into a warzone.", false, false},
        new Object[] {"tg_pilot_weapon", "Pilot Weapon", "On missions, pilots"
            + " can take up to two weapons. All pilot weapons are pilot-scale"
            + " and can’t be used by mechs.", false, false},
        new Object[] {"tg_sidearm", "Sidearm", "This weapon can be used to"
            + " Fight as a quick action instead of a full action.", false,
            false},
        // Weapon tags
        new Object[] {"tg_accurate", "Accurate", "Attacks made with this weapon"
            + " receive +1 Accuracy.", false, false},
        new Object[] {"tg_ap", "Armor-Piercing (AP)", "Damage dealt by this"
            + " weapon ignores Armor.", false, false},
        new Object[] {"tg_arcing", "Arcing", "This weapon can be fired over"
            + " obstacles, usually by lobbing a projectile in an arc. Attacks"
            + " made with this weapon don’t require line of sight, as long as"
            + " it’s possible to trace a path to the target; however, they are"
            + " still affected by cover.", false, false},
        new Object[] {"tg_knockback", "Knockback X", "On a hit, the user may"
            + " choose to knock their target X spaces in a straight line"
            + " directly away from the point of origin (e.g., the attacking"
            + " mech or the center of a Blast). Multiple Knockback effects"
            + " stack with each other. This means that an attack made with a"
            + " Knockback 1 weapon and a talent that grants Knockback 1 counts"
            + " as having Knockback 2.", false, false},
        new Object[] {"tg_overkill", "Overkill", "When rolling for damage with"
            + " this weapon, any damage dice that land on a 1 cause the"
            + " attacker to take 1 Heat, and are then rerolled. Additional 1s"
            + " continue to trigger this effect.", false, false},
        new Object[] {"tg_reliable", "Reliable X", "This weapon has some degree"
            + " of self-correction or is simply powerful enough to cause damage"
            + " even with a glancing blow. It always does X damage, even if it"
            + " misses its target or rolls less damage. Reliable damage"
            + " inherits other tags (such as AP) and base damage type but not"
            + " tags that require a hit, such as Knockback.", false, false},
        new Object[] {"tg_seeking", "Seeking", "This weapon has a limited form"
            + " of self-guidance and internal propulsion, allowing it to follow"
            + " complicated paths to its targets. As long as it’s possible to"
            + " draw a path to its target, this weapon ignores cover and"
            + " doesn’t require line of sight.", false, false},
        new Object[] {"tg_thrown", "Thrown X", "This melee weapon can be thrown"
            + " at targets within X spaces. Thrown attacks follow the rules for"
            + " melee attacks but are affected by cover; additionally, a thrown"
            + " weapon comes to rest in an adjacent space to its target and"
            + " must be retrieved as a free action while adjacent to that"
            + " weapon before it can be used again.", false, false},
        // Currently unused
        new Object[] {"tg_burn", "Burn X", "On a hit, this weapon deals X Burn"
            + " to its target. They immediately take that much burn damage,"
            + " ignoring Armor, then mark X Burn on their sheet, adding it to"
            + " any existing Burn. At the end of their turn, characters with"
            + " marked burn make an Engineering check. On a success, they clear"
            + " all marked burn; on a failure, they take damage equal to their"
            + " total marked burn.", false, false},
        new Object[] {"tg_heat_target", "Heat X (Target)", "On a hit, this"
            + " weapon or system deals X Heat to its target.", false, false},
        new Object[] {"tg_line", "Line X", "Attacks made with this weapon"
            + " affect characters in a straight line, X spaces long.", true,
            false},
        new Object[] {"tg_cone", "Cone X", "Attacks made with this weapon"
            + " affect characters within a cone, X spaces long and X spaces"
            + " wide at its furthest point. The cone begins 1 space wide.",
            true, false},
        new Object[] {"tg_blast", "Blast X", "Attacks made with this weapon"
            + " affect characters within a radius of X spaces, drawn from a"
            + " point within Range and line of sight. Cover and line of sight"
            + " are calculated based on the center of the blast, rather than"
            + " the attacker’s position.", true, false},
        new Object[] {"tg_burst", "Burst X", "Attacks made with this weapon"
            + " affect characters within a radius of X spaces, centered on and"
            + " including the space occupied by the user (or target). If the"
            + " Burst is an attack, the user or target is not affected by the"
            + " attack unless specified. Cover and line of sight are calculated"
            + " from the character. If a Burst effect is ongoing, it moves with"
            + " the character at its center.", true, false},
        new Object[] {"tg_deployable", "Deployable", "This system is an object"
            + " that can be deployed on the field. Unless otherwise specified,"
            + " it can be deployed in an adjacent, free and valid space as a"
            + " quick action, and has 5 Evasion and 10 HP per Size.", false,
            false},
        new Object[] {"tg_drone", "Drone", "This is a self-propelled,"
            + " semi-autonomous unit with rudimentary intelligence. Unless"
            + " otherwise specified, Drones are Size 1/2 characters that are"
            + " allied to the user and have 10 Evasion, 5 HP, and 0 Armor. To"
            + " be used they must be deployed to a free, valid space within"
            + " Sensors and line of sight, typically as a quick action. Once"
            + " deployed, they can be recalled with the same action used to"
            + " deploy them (quick action or full action, etc.), rejoining with"
            + " your mech. By default, Drones can’t take actions or move; if"
            + " they do have actions or movement, they act on their user’s"
            + " turn. They benefit from cover and other defenses as usual, and"
            + " make all mech skill checks and saves at +0. If a Drone reaches"
            + " 0 HP, it is destroyed and must be repaired before it can be"
            + " used again – like any system. As long as a Drone hasn’t been"
            + " destroyed, it is restored to full HP when the user rests or"
            + " performs a Full Repair. Deployed Drones persist for the rest of"
            + " the scene, until destroyed, or until otherwise specified.",
            false},
        new Object[] {"tg_full_action", "Full Action", "This system requires a"
            + " full action to Activate.", false, false},
        new Object[] {"tg_mine", "Mine", "As a quick action, this device can be"
            + " planted in an adjacent, free and valid space on any surface,"
            + " but not adjacent to any other mines. Upon deployment, it arms"
            + " at the end of the deploying character’s turn and – unless"
            + " otherwise specified – is triggered when any character enters an"
            + " adjacent space. Characters leaving an adjacent space will not"
            + " trigger a mine. Once triggered, a mine creates a Burst attack"
            + " starting from the space in which it was placed. Mines within a"
            + " character’s Sensors can be detected by making a successful"
            + " Systems check as a quick action, otherwise they are Hidden and"
            + " can’t be targeted. Detected mines can be disarmed from adjacent"
            + " spaces by making a successful Systems check as a quick action;"
            + " the attempt takes place before the mine detonates, and on a"
            + " failure, the mine detonates as normal.", false, false},
        new Object[] {"tg_mod", "Mod", "This modification can be applied to a"
            + " weapon. Each weapon can only have one Mod, and cannot have more"
            + " than one of the same Mod. Mods are applied when the user builds"
            + " their mech or during a Full Repair.", false, false},
        new Object[] {"tg_protocol", "Protocol", "This system can be activated"
            + " as a free action, but only at the start of the user’s turn."
            + " Another action might be needed to deactivate it.", false,
            false},
        new Object[] {"tg_invade", "Invade", "This system provides additional"
            + " options for the Invade Quick Tech Action.", false, false},
        new Object[] {"tg_full_tech", "Full Tech", "This tech can be used as a"
            + " Full Tech Action", false, false},
        new Object[] {"tg_range", "Range (X)", "This system can be activated at"
            + " a range of X spaces.", true, false},
        new Object[] {"tg_modded", "Modded", "This weapon has been equipped"
            + " with a weapon mod.", false, false},
        new Object[] {"tg_resistance", "Resistance", "Reduce all damage from a"
            + " source you have resistance to by half", false, false},
        new Object[] {"tg_recharge", "Recharge X+", "Once this system or weapon"
            + " has been used, it can’t be used again until it is recharged. At"
            + " the start of this NPC's turn, roll 1d6: if the result is equal"
            + " to or greater X, the equipment can be used again. Only one roll"
            + " is required for each NPC, even if an NPC has multiple Recharge"
            + " systems or weapons. If this NPC has two Recharge systems with"
            + " target numbers of 4+ and 5+, a roll of 5 will recharge both.",
            true, false},
        new Object[] {"tg_unlimited", "Unlimited", "This ability can be used"
            + " any number of times per round.", true, false},
        new Object[] {"tg_set_damage_value", "Set Damage Value", "Allow player"
            + " to set the damage value for this equipment (HIDDEN TAG)", false,
            true},
        new Object[] {"tg_efficient", "Efficient", "At the end of any scene in"
            + " which this system is used, you regain 1 Core Power.", false},
        new Object[] {"tg_threat", "Threat X", "This weapon can be used to make"
            + " Overwatch attacks within X spaces. If it’s a melee weapon, it"
            + " can be used to make melee attacks within X spaces."}
    };

    /**
     * Creates a new Tag given an equipment tag name.
     * @param tagName a String which cannot be null or an invalid value, as
     *     defined by Tag.allowedTags.
     */
    public Tag(String tagID, String tagName, String tagDescription,
        boolean filterIgnore) {
        super("tg_inaccurate");
        setID(tagID);
        setName(tagName);
        setDescription(tagDescription);
        this.value = 0;
        setFilterIgnore(filterIgnore);
        setHidden(false);
    }
    /**
     * Creates a new Tag such as the "Limited X" tag, given an equipment tag
     *     name for which having a value other than 0 for this.value makes
     *     sense as defined by Tag.valueIDs, and a tag value.
     * @param tagName a String which must be a value for which having a value
     *     other than 0 makes sense as defined by Tag.valueIDs. Cannot be null.
     * @param tagValue an int which must be > 1.
     */
    public Tag(String tagID, String tagName, String tagDescription,
        int tagValue, boolean filterIgnore) {
        super("tg_inaccurate");
        setID(tagID);
        setName(tagName);
        setDescription(tagDescription);
        setValue(tagValue);
        setFilterIgnore(filterIgnore);
        setHidden(false);
    }
    /**
     * Creates a copy of the provided Tag.
     * @param tag a Tag to be copied.
     * @return a Tag copy of the provided Tag.
     */
    public Tag(Tag tag) {
        super("tg_inaccurate");
        HelperMethods.checkObject("tag", tag);
        setID(tag.id);
        setName(tag.name);
        setDescription(tag.description);
        setValue(tag.value);
        setFilterIgnore(tag.filterIgnore);
        setHidden(tag.hidden);
    }

    @Override
    protected void setID(String id) {
        HelperMethods.checkString("New ID", id);
        id = id.toLowerCase();
        this.id = id;
    }
    /**
     * Sets this.id to the provided value.
     * @param id a String which must be a valid value (as defined by
     *     Tag.allowedTags). Cannot be null.
     * @throws IllegalArgumentException if id is null or an invalid value, as
     *     defined by Tag.allowedTags.
     */
    @Override
    protected void setName(String name) {
        HelperMethods.checkString("New name", name);
        for (Object[] tag : Tag.allowedTags) {
            if (name.equals((String) tag[1])) {
                this.name = name;
                setID((String) tag[0]);
                setDescription((String) tag[2]);
                setFilterIgnore((boolean) tag[3]);
                setHidden((boolean) tag[4]);
                return;
            }
        }
        throw new IllegalArgumentException("New name: \"" + name + "\" is not a"
            + " valid tag name");
    }
    /**
     * Sets this.value to the provided value. If this.id is a tag for which
     *     having a value makes sense as defined by Tag.valueIDs, checks whether
     *     value is < 1.
     * @param value an int containing the new value.
     * @throws IllegalArgumentException if this.id is not a tag for which having
     *     a value makes sense, or if it is and value is < 1.
     */
    @Override
    protected void setValue(int value) {
        for (String valueID : Tag.valueIDs) {
            if (this.id.equals(valueID)) {
                if (value < 1) {
                    throw new IllegalArgumentException("Name is: \"" + this.name
                    + "\" and new value: " + value + " is < 1");
                }
                this.value = value;
                return;
            }
        }
        if (value != 0) {
            throw new IllegalArgumentException("Attempted to change a Tag's"
                + " value when its Tag.name value is: \"" + this.name + "\"");
        }
    }
}
