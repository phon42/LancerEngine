import java.util.HashMap;
import main.Database;
import main.LancerCharacter;
import main.database.FrameEnum;
import packages.characterTypes.Mech;
import packages.characterTypes.mech.Mount;
import packages.characterTypes.mech.equipment.MechSystem;
import packages.characterTypes.pilot.Loadout;
import packages.characterTypes.pilot.SkillTriggersList;
import packages.characterTypes.pilot.Talent;
import packages.characterTypes.pilot.skillTriggersList.SkillTrigger;
import packages.coreTypes.License;

/**
 * Questions:
 * - Can pilots take burn? - yes
 * - Can the environment take heat or burn? - no and kind of (takes the damage
 *       but doesn't track burn)
 */
// TODO: implement this whole list
/**
 * What's Left To Add:
 * - Order of resolution (pg. 68)
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
 * =============================================================================
 * 
 * All methods that are not basic accessors or basic mutators (have names of the
 *     form get[Property]() or set[Property]()) have a Javadoc comment. Any
 *     set[Property]() methods within this code have a Javadoc comment if one of
 *     the following conditions is met:
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
                Database.getWeapon("Slag Cannon")
            ),
            new Mount("aux",
                Database.getWeapon("Vulture DMR"), "",
                "Overpower Caliber"
            )
        });
        mechProperties.put("systems", new MechSystem[] {
            Database.getSystem("Pattern-A Smoke Charges"),
            Database.getSystem("Seismic Ripper"),
            Database.getSystem("High-Stress Mag Clamps"),
            Database.getSystem("ATHENA-Class NHP"),
            Database.getSystem("Markerlight"),
            Database.getSystem("IMMOLATE"),
            Database.getSystem("Wandering Nightmare")
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
            new License("Swallowtail", 3),
            new License("Death's Head", 3),
            new License("Kobold", 3),
            new License("Lich", 1)
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
