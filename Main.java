import java.util.HashMap;

import MainBranch.Database;
import Packages.CoreTypes.EntityMechanics.LancerCharacter;
import Packages.CoreTypes.EntityMechanics.License;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Mech;
import Packages.CoreTypes.EntityMechanics.EntityTypes.mech.Mount;
import Packages.CoreTypes.EntityMechanics.EntityTypes.mech.equipment.MechSystem;
import Packages.CoreTypes.EntityMechanics.EntityTypes.mech.frame.FrameEnum;
import Packages.CoreTypes.EntityMechanics.EntityTypes.pilot.Loadout;
import Packages.CoreTypes.EntityMechanics.EntityTypes.pilot.SkillTriggersList;
import Packages.CoreTypes.EntityMechanics.EntityTypes.pilot.Talent;
import Packages.CoreTypes.EntityMechanics.EntityTypes.pilot.loadout.pilotEquipment.PilotGear;
import Packages.CoreTypes.EntityMechanics.EntityTypes.pilot.loadout.pilotEquipment.PilotWeapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.pilot.skillTriggersList.skillTrigger.Skill;

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
            new Skill[] {
                new Skill("Apply Fists to Faces",
                    2),
                new Skill("Assault", 2),
                new Skill("Blow Something Up",
                    2),
                new Skill("Survive", 2)
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
            Database.getPilotArmor("pg_mobility_hardsuit"),
            new PilotWeapon[] {
                Database.getPilotWeapon("pg_heavy_signature"),
                Database.getPilotWeapon("pg_archaic_melee")
            }, new PilotGear[] {
                Database.getPilotGear("pg_wilderness_survival_kit"),
                Database.getPilotGear("pg_flexsuit"),
                Database.getPilotGear("pg_personal_drone")
            }
        ));
        myCharacter.setPilotProperties(pilotProperties);

        System.out.print(myCharacter.generateStatblock(
            "mech build"));
    }
}
