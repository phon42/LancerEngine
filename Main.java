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
 *     - For arrays and classes, I call HelperFunctions.copyOf() (for arrays),
 *           and [Class].copyOf() (for classes). In either case, a deepest copy
 *           of the property's value is created and then returned.
 * - Second, the property is mutated as desired. (For example,
 *        Mech.getFrame().setName())
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
 *        functions are called on it, it calls a function that I define manually
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
 */
/**
 * Contains the main() function for the project. Doesn't really do anything
 *     else.
 */
public class Main {
    // if you don't know what this is from looking at the method name
    // you should not be reading this code right now
    public static void main(String[] args) {
        LancerCharacter myCharacter = new LancerCharacter(
            "Coral Nolan", "Apocalypse");
        Pilot myPilot = myCharacter.getPilot();
        Mech myMech = myCharacter.getMech();
        Loadout myLoadout;

        myPilot.setPlayer("Luna");
        myPilot.setBackground("e");
        myPilot.setBiography("e");
        myPilot.setAppearance("e");
        myPilot.setPlayerNotes("e");
        SkillTriggersList newSkillTriggers = new SkillTriggersList();
        newSkillTriggers.setSkillTriggers(new SkillTrigger[] {
            new SkillTrigger("Apply Fists to Faces", 2),
            new SkillTrigger("Assault", 2),
            new SkillTrigger("Blow Something Up", 2),
            new SkillTrigger("Survive", 2)
        });
        myPilot.setSkillTriggers(newSkillTriggers);
        myPilot.setLoadout(new Loadout());
        myPilot.setLicenseLevel(9);
        myPilot.setLicenseList(new License[] {
            new License("SSC Swallowtail", 3),
            new License("SSC Death's Head", 3),
            new License("HORUS Kobold", 3),
            new License("HORUS Lich", 1)
        });
        myPilot.setMechSkills(new int[] {4, 0, 5, 2});
        myPilot.setCoreBonuses(new String[] {
            "Neurolink Targeting",
            "Overpower Caliber",
            "Integrated Weapon"
        });
        myPilot.setTalents(new Talent[] {
            new Talent("Tactician", 3),
            new Talent("Siege Specialist", 3),
            new Talent("Spotter", 2),
            new Talent("Walking Armory", 2),
            new Talent("Leader", 2)
        });
        myLoadout = new Loadout("Mobility Hardsuit",
            new String[] {"Heavy Signature", "Archaic Melee"},
            new String[] {"Wilderness Survival Kit", "Flexsuit",
            "Personal Drone"});
        myPilot.setLoadout(myLoadout);
        myMech = new Mech("Wraith", "swallowtail_ranger");
        /*
        myMech.setMount(0, new Mount("Integrated Weapon",
            new Weapon("Nexus (Light)")));
        */
        myMech.setMount(0, new Mount(
            new Weapon("Slag Cannon")));
        myMech.setMount(1, new Mount(new Weapon("Vulture DMR"),
            "", "Overpower Caliber"));
        myMech.setSystems(new MechSystem[] {
            new MechSystem("Pattern-A Smoke Charges", 3),
            new MechSystem("Seismic Ripper"),
            new MechSystem("High-Stress Mag Clamps"),
            new MechSystem("ATHENA-Class NHP"),
            new MechSystem("Markerlight"),
            new MechSystem("IMMOLATE"),
            new MechSystem("Wandering Nightmare")
        });
        myCharacter.setPilot(myPilot);
        myCharacter.setMech(myMech);

        System.out.print(myCharacter.generateStatblock(
            "mech build"));
        // TestFunctions.runTests();
    }
}
