import java.util.HashMap;
import MainBranch.Database;
import Packages.CoreTypes.EntityMechanics.LancerCharacter;
import Packages.CoreTypes.EntityMechanics.License;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.Mech;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Mount;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase.MechSystem;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.frameBase.frame.FrameEnum;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Loadout;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.SkillTriggersList;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.Talent;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.pilotEquipment.PilotGear;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.Verified.pilotEquipment.PilotWeapon;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.skillTriggersList.Skill;

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

    public static void main(String[] args) {
        LancerCharacter myCharacter;
        HashMap<String, Object> mechProperties;
        HashMap<String, Object> pilotProperties;

        Database.initialize();

        // compile a character
        myCharacter = new LancerCharacter("Coral Nolan",
            "Apocalypse", new Mech("Wraith",
                FrameEnum.SWALLOWTAIL_RANGER)
        );
        mechProperties = myCharacter.getMechProperties();
        mechProperties.put("mounts", new Mount[] {
            new Mount(
                Database.getMountType("Aux"),
                Database.getWeapon("Slag Cannon")
            ),
            new Mount(
                Database.getMountType("Aux"),
                Database.getWeapon("Vulture DMR"), null,
                "Overpower Caliber"
            )
        });
        mechProperties.put("systems", new MechSystem[] {
            Database.getMechSystem("ms_pattern_a_smoke_charges"),
            Database.getMechSystem("ms_seismic_pulse"),
            Database.getMechSystem("ms_high_stress_mag_clamps"),
            Database.getMechSystem("ms_athena_class_nhp"),
            Database.getMechSystem("ms_markerlight"),
            Database.getMechSystem("ms_immolate"),
            Database.getMechSystem("ms_tear_firmament")
        });
        myCharacter.setMechProperties(mechProperties);

        pilotProperties = myCharacter.getPilotProperties();
        pilotProperties.put("player", "Luna");
        pilotProperties.put(
            "background", "sample background text here"
        );
        pilotProperties.put(
            "biography", "sample biography text here"
        );
        pilotProperties.put(
            "appearance", "sample appearance description here"
        );
        pilotProperties.put(
            "playerNotes", "sample player notes here"
        );
        pilotProperties.put("skills", new SkillTriggersList(
            new Skill[] {
                new Skill("Apply Fists to Faces",
                    2),
                new Skill("Assault", 2),
                new Skill("Blow Something Up", 2),
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
            new Talent(Database.getTalent("t_tactician"),
                3),
            new Talent(Database.getTalent("t_siege_specialist"),
                3),
            new Talent(Database.getTalent("t_spotter"), 2),
            new Talent(Database.getTalent("t_walking_armory"),
                2),
            new Talent(Database.getTalent("t_leader"), 2)
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
