import java.util.HashMap;
// TODO: implement this whole list
/**
 * Questions:
 * - Can pilots take burn? - yes
 * - Can the environment take heat or burn? - no and kind of (takes the damage
 *       but doesn't track burn)
 */
/**
 * What's Left To Add:
 * - Order of resolution (pg. 68)
 * - mechs taking burn
 * - pilots taking burn
 * - IMPLEMENTING conditions and statuses (the actual things themselves, not
 *       just having them)
 * - Weapons - they have to actually attack lol
 *   - incl pilot weapons
 * - MechSystems need to do their actions/effects
 *   - incl pilot armor and gear
 * - checking validity for (having the licenses to) weapons and systems
 * - actions on your turns (everything on pg 61 honestly)
 *   - incl movement
 * - reactions
 * - downtime actions and reserves I guess..
 * 
 * Probably best saved for next level of abstraction:
 * - track engagement (check page 74)? (doesn't make any sense to do until we're
 *     a level up)
 * - involuntary movement
 * - difficult/dangerous terrain
 * - adjacency
 * - lifting, dragging, jumping, climbing, flying, gravity, teleportation, hovering
 * - cover
 * - damaging the environment
 */
/**
 * Eventually thinking of hooking this up to something else for graphics. If all
 *     else fails, I will use HTML because that's what I know. In that case,
 *     see:
 * - https://stackoverflow.com/questions/10964693/java-embedding-into-html
 * - https://stackoverflow.com/questions/985754/how-to-deploy-a-java-applet-for-todays-browsers-applet-embed-object
 * - https://www.java.com/en/download/help/enable_browser.html
 * 
 * Preliminary searches show that C# is also a good language for creating graphics:
 * - https://www.reddit.com/r/learnprogramming/comments/m7x51g/comment/gre467l/
 * 
 * Can also just straight up use Java AWT:
 * - https://www.geeksforgeeks.org/java/what-is-java-awt-graphics/
 */
/**
 * This program is intended to simulate one (or more) characters within the
 *     tabletop roleplaying game Lancer.
 * A helper site for this game, COMP/CON, which allows one to reference many of
 *     the game's items and equipment, is available at https://compcon.app/
 * Reference pages for the game, including the full core rulebook, are available
 *     at https://lancer-rules.carrd.co/ and
 *     https://massif-press.itch.io/corebook-pdf-free
 * A Lancer character consists of a pilot and (optionally) their mech. Thus, I
 *     have constructed a LancerCharacter class as well as Pilot and Mech
 *     classes which it utilizes.
 * 
 * Some terms:
 * - A "frame" is a template from which mechanized chassis (mechs) can be
 *        created. For example, the GMS Everest is a frame. It can be thought of
 *        as a species; all Everest mechs may not be exactly alike, but they are
 *        all hewn from the same original statblock of the GMS Everest frame.
 * - A "mech" is an instance of that species; every mech is a copy of some
 *       original frame which is then modified by its pilot's stats and
 *       abilities and other such things.
 * 
 * Mechs are able to be modified in several key ways:
 * - They are affected by their pilot's stats (their "mech stats" and their
 *       "grit").
 * - They are affected by special abilities called "core bonuses" and "talents"
 *       belonging to their pilot.
 * - They can be given equipment, such as weapons and systems, which change
 *       their stats or give them access to new abilities.
 * 
 * A Mech is an empty shell which does not itself have any stats, so I have
 *     created Frame classes for each different possible frame and I pass them
 *     to a Mech object to create it.
 * 
 * My current process for accessing and mutating a property from any of the
 *     classes I have created is as follows:
 * - First, I call [Class].get[Property]().
 *     - For primitives such as int, and the String class, the value is returned
 *           directly.
 *     - For arrays and classes, I call HelperMethods.copyOf() (for arrays),
 *           and [Class]([Class instance]) (for classes). In either case, a
 *           deepest copy of the property's value is created and then returned.
 * - Second, the property is mutated as desired. For example:
 *        Mech.getFrame().setName()
 * - Finally, [Class].set[Property]() is called with the new, mutated value.
 *     - Internally, a deepest copy is once again created when necessary.
 *     - Then, the property is set to the mutated value.
 * 
 * When mutating LancerCharacter.mech, in particular, I always call
 *     Mech.calculateAttributes() on the passed Mech when
 *     LancerCharacter.setMech() is called, for the reason that, again, Mechs do
 *     not inherently know what their stats should be; that information must be
 *     fetched from attached Frame and Pilot objects.
 * This, however, has led to a difficult problem that is currently blocking my
 *     progress. When LancerCharacter.setMech() is called, there are two
 *     possible cases.
 * - The first is the case where LancerCharacter is receiving an entirely new
 *       mech when it previously did not have one, or had a fully functional
 *       mech that is now being replaced.
 * - The second is the case where the incoming Mech object is the "same" mech as
 *       the Mech object currently living in LancerCharacter's .mech property,
 *       but something about it has been changed or updated.
 * In the first case, recalculation of the mech's stats from its Frame object is
 *     necessary. In the second, some partial recalculation based on the added
 *     system is possibly needed. However, it is impossible for me to
 *     distinguish between these cases, because a user could create an entirely
 *     new Mech with the wrong stats that just so happens to match the existing
 *     LancerCharacter.mech's stats. Therefore, I always call
 *     Mech.calculateAttributes().
 * When called, Mech.calculateAttributes() essentially resets the Mech object to
 *     the values predicted by its Frame and the Pilot object attached to it.
 *     However, this also erases any changes made in the process.
 * I have successfully implemented encapsulation so well that it is now
 *     impossible for me to change anything about my objects' properties.
 * 
 * Here are some solutions I have thought of, tested and/or rejected within the
 *     concept phase:
 * 1. Change LancerCharacter.pilot and .mech from private to public final
 *        Why: One issue with LancerCharacter is that I can't track when .mech
 *            is changed. If I could do that, maybe I could know when I'm making
 *            a new mech and when I'm just changing a part of it? Also, this way
 *            it's possible to call methods on .mech directly instead of calling
 *            getMech() like this: LancerCharacter.mech.setName("blah"). And
 *            when I use setMech(), I could just copy over all the properties
 *            instead of setting the property itself to the new Mech object.
 *        What happened: I tried this. It didn't work. Now, I STILL don't know
 *            if the Mech I've just been passed is a new one or the same one,
 *            just changed a bit. On top of that, my encapsulation is ruined,
 *            because that .mech is public I can get the Mech object by calling
 *            X = LancerCharacter.mech. Why did I think this was a good idea?
 * 2. Change LancerCharacter.pilot and .mech from private to public final and
 *        create some kind of mirror object that, when any of Mech's instance
 *        methods are called on it, it calls a method that I define manually
 *        within LancerCharacter with the same name that calls that method with
 *        the same parameters on a private (and thus hidden) Mech object.
 *        Why: Now the LancerCharacter's Mech object isn't actually exposed.
 *        What happened: I did NOT try this because it would be a lot of work
 *            but although it would address the problem with changing the
 *            properties to be public to some extent, it still doesn't address
 *            the core issue.
 * 
 * Ideally, I would like to solve this problem without adding additional
 *     properties, systems, or classes, just editing existing methods. However,
 *     one final solution that I can think of that violates this rule is to
 *     create some kind of Object[] where each element contains a name (the
 *     property of the Mech object that could have been changed) and a boolean
 *     (whether or not that property was modified). This solution does fix the
 *     core issue, although more information other than a simple boolean would
 *     be required to know to what EXTENT the property has been modified, but it
 *     requires the addition of multiple methods to support it.
 * 
 * Some questions:
 * - Should I hold an unset "frame" value (Mech.frame) as null or some kind of
 *       special placeholder Frame value?
 *     - Same question for LancerCharacter.mech?
 *     - Same question for Mount.weapon?
 * 
 * The Mech.generateOutput("mech build") method may be of use to you for the
 *     purposes of testing. It generates the section of text beginning with
 *     "STRUCTURE:" and ending with the "[ SYSTEMS ]" section.
 * Additionally, Mech.outputWeapons("mech build") is the method specifically
 *     responsible for generating the line of output directly under the
 *     "[ WEAPONS ]" line.
 */
/**
 * All non-set[Property]() methods have a Javadoc comment. Any set[Property]()
 *     methods within this code have a Javadoc comment if one of the following
 *     conditions are met:
 *     - For properties of a primitive type (such as int), the property must be
 *           governed by a rule that is not as simple as a single bound (i.e.
 *           "[Property] cannot be < 0").
 *         - For properties of type String specifically, the property must be
 *               governed by a rule that is not as simple as '[Property] cannot
 *               be null or ""'.
 *     - For properties of type Array or any other class (other than String),
 *           all set[Property]() methods have a Javadoc comment.
 */
/**
 * Represents absolutely nothing. Contains the main() method for the project.
 *     Doesn't really do anything else.
 * 
 * Cannot be instantiated.
 * 
 * Used for its main() method only.
 * 
 * Safety: N/A because this class cannot be instantiated.
 */
public final class Main {
    // prevent user from instantiating
    private Main() {}

    // if you don't know what this is from looking at the method name
    // you should not be reading this code right now
    public static void main(String[] args) {
        LancerCharacter myCharacter = new LancerCharacter(
            "Coral Nolan", "Apocalypse",
            new Mech("Wraith", FrameEnum.SWALLOWTAIL_RANGER));
        HashMap<String, Object> mechProperties;
        HashMap<String, Object> pilotProperties;

        mechProperties = myCharacter.getMechProperties();
        mechProperties.put("mounts", new Mount[] {
            new Mount("aux",
                new Weapon("Slag Cannon", 1,
                    1)
            ),
            new Mount("aux",
                new Weapon("Vulture DMR", 1,
                    5),
                "", "Overpower Caliber"
            )
        });
        mechProperties.put("systems", new MechSystem[] {
            new MechSystem("Pattern-A Smoke Charges",
                new EquipmentTag[] {
                    new EquipmentTag("Limited X", 3)
                }
            ),
            new MechSystem("Seismic Ripper"),
            new MechSystem("High-Stress Mag Clamps"),
            new MechSystem("ATHENA-Class NHP"),
            new MechSystem("Markerlight"),
            new MechSystem("IMMOLATE"),
            new MechSystem("Wandering Nightmare")
        });
        myCharacter.setMechProperties(mechProperties);

        pilotProperties = myCharacter.getPilotProperties();
        pilotProperties.put("player", "Luna");
        pilotProperties.put("background", "e");
        pilotProperties.put("biography", "e");
        pilotProperties.put("appearance", "e");
        pilotProperties.put("playerNotes", "e");
        pilotProperties.put("skillTriggers", new SkillTriggersList(
            new SkillTrigger[] {
                new SkillTrigger("Apply Fists to Faces",
                    2),
                new SkillTrigger("Assault", 2),
                new SkillTrigger("Blow Something Up",
                    2),
                new SkillTrigger("Survive", 2)
            }
        ));
        pilotProperties.put("loadout", new Loadout());
        pilotProperties.put("licenseLevel", 9);
        pilotProperties.put("licenseList", new License[] {
            new License("SSC Swallowtail", 3),
            new License("SSC Death's Head", 3),
            new License("HORUS Kobold", 3),
            new License("HORUS Lich", 1)
        });
        pilotProperties.put("mechSkills", new int[] {4, 0, 5, 2});
        pilotProperties.put("coreBonuses", new String[] {
            "Neurolink Targeting",
            "Overpower Caliber"
        });
        pilotProperties.put("talents", new Talent[] {
            new Talent("Tactician", 3),
            new Talent("Siege Specialist", 3),
            new Talent("Spotter", 2),
            new Talent("Walking Armory", 2),
            new Talent("Leader", 2)
        });
        pilotProperties.put("loadout", new Loadout(
            "Mobility Hardsuit", new String[] {"Heavy Signature",
                "Archaic Melee"}, new String[] {"Wilderness Survival Kit",
                    "Flexsuit", "Personal Drone"
                }
        ));
        myCharacter.setPilotProperties(pilotProperties);

        System.out.print(myCharacter.generateStatblock(
            "mech build"));
    }
}
