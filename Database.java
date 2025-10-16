// TODO: let's go full crazy, add the ability to read a set of .lcp files and
//     unzip those into as much Lancer data as we want:
//         https://www.w3schools.com/java/java_files_read.asp
//         https://www.baeldung.com/java-compress-and-uncompress#unzip
/**
 * Contains a set of values that can be used to reference or obtain a specific
 *     frame.
 */
enum FrameEnum {
    EVEREST,
    SAGARMATHA,
    CALIBAN,
    SWALLOWTAIL_RANGER,
    BARBAROSSA,
    SIZE_4
}
/**
 * Contains a set array of Frames to be used by Mech(String, String) and
 *     Mech(String, FrameEnum).
 * 
 * Cannot be instantiated. All its methods are static.
 * 
 * Used to create a Mech, or when information about a frame needs to be
 *     referenced.
 * 
 * Safety: N/A because this class cannot be instantiated.
 */
public final class Database {
    /**
     * Contains every frame's data for reference.
     */
    private static final Frame[] frameList = new Frame[] {
        new Frame("GMS", "Everest", "everest",
            FrameEnum.EVEREST, new String[] {"Balanced"}, "Most humans don’t"
            + " think to ask about the history of the water they drink, the"
            + " earth they walk, or the air they breathe. And yet, without"
            + " water, earth, and air, there would be nowhere for humanity to"
            + " make a home.\nJust the same, the GMS-SP1 “Everest” is often"
            + " taken for granted, its importance dismissed in favor of other,"
            + " more specialized frames. A plain and unpretentious mech,"
            + " defined by simple lines, functional grace, universal"
            + " compatibility, and sturdy bulk, the Everest is as fundamental"
            + " to the modern mechanized chassis as the natural world is to"
            + " human life. The Everest isn’t the most specialized mech, but it"
            + " is the backbone of our expansion imperative. From its"
            + " shoulders, humanity steps.\nPrior to GMS’s official adoption of"
            + " the name, “Everest” was a use-name given to the frame by its"
            + " pilots. Mount Everest – or Sagarmatha, or Chomolungma, as it"
            + " has been called in older human tongues – is the tallest"
            + " mountain on Cradle, though not the most prominent peak in known"
            + " space, nor even the greatest in Cradle’s star system, yet"
            + " pilots across the galaxy call their SP1s by that ancient name."
            + " Why?\nThe sentimental answer is that the name is a reminder of"
            + " what was once the limit of human endurance – once the height of"
            + " human achievement. To reach Everest’s summit was to defy death"
            + " and stand atop the world – the culmination of months, even"
            + " years, of training, investment, and hard work. Reaching the"
            + " peak was also a triumph of the people, systems, and"
            + " institutions behind the individual – a triumph too often left"
            + " unacknowledged, or deliberately erased.\nSagarmatha."
            + " Chomolungma.\nEven before the Fall, when the Massif vaults were"
            + " built, some names – some stories – were given priority over"
            + " others.\nThe real story behind the Everest’s name is likely"
            + " much less deliberate. Somewhere along the line, a newly"
            + " graduated pilot, frustrated by GMS’s plain naming conventions,"
            + " painted “EVEREST” across the flank of their SP1. Maybe it was a"
            + " callsign, or maybe it represented the pride they felt at"
            + " success. Either way, the name stuck: others adopted the name,"
            + " and over five centuries it grew to become the officially"
            + " unofficial designation of the SP1 chassis.\nVeteran pilots may"
            + " never return to the Everest after moving on, but they’ll always"
            + " remember reaching that first summit – the mountaintop where"
            + " they proved they could plant their own flag at the peak of the"
            + " world.\nSagarmatha. Chomolungma.\nEverest – you’ll never forget"
            + " it.", 2, 4, 10, 0, 4,
            6, 8, 4, 8,
            0, 10, 5,
            10, 6, new String[] {"Initiative",
            "Replaceable Parts"}, new Mount[] {
                new Mount("main"),
                new Mount("flex"),
                new Mount("heavy")
            }
        ),
        new Frame("GMS", "Sagarmatha", "sagarmatha",
            FrameEnum.SAGARMATHA, new String[] {"defender"}, "The SAGARMATHA is"
            + " a SIZE 2 legacy GMS chassis that all characters have access to"
            + " from LL0 onwards. This variant FRAME is not mutually exclusive"
            + " with the EVEREST.\n\nThe Sagarmatha is of an older pattern than"
            + " the Everest, sturdier and bulkier by necessity more than"
            + " design. It is seen as somewhat antiquated through the lens of"
            + " modern manufacturing standards. Nevertheless, it enjoys some"
            + " use in border colonies, in the Baronies, and with the"
            + " Albatross.", 4, 4, 8, 1, 4,
            6, 8, 4, 8,
            0, 10, 4,
            10, 6, new String[] {"Guardian", "Heroism",
            "Replaceable Parts"}, new Mount[] {
                new Mount("main"),
                new Mount("flex"),
                new Mount("heavy")
            }
        ),
        new Frame("IPS-N", "Caliban", "caliban",
            FrameEnum.CALIBAN, new String[] {"striker", "controller"},
            "The Caliban chassis is a popular new order among anti piracy and"
            + " stellar marine forces. Unlike many of IPS-N's modern frames,"
            + " the Caliban was designed from the ground up to be a military"
            + " machine.\n\nThe Caliban is IPS-N's solution to the \"Yemanova"
            + " \" Problem, more properly known as the Impact-Override Problem."
            + " Capital ships, the problem posits, are incredibly expensive,"
            + " demand a tremendous amount of time for corpros and Diasporan"
            + " states to produce and maintain, and increasingly outmatched by"
            + " anti-ship weaponry. In a conventional capital duel, a"
            + " successful kill means the death of thousands of personnel and"
            + " the loss of millions of units of manna; this makes many"
            + " commanders gun-shy, encouraging them to rely on subline vessels"
            + " and fighters to accomplish battle objectives rather than risk"
            + " their big ships. This strategy tends to prove just as"
            + " expensive: instead of one or a handful of large ships being"
            + " destroyed, signifying the end of a battle, engagements can"
            + " grind on for weeks as squadrons of smaller ships engage inside"
            + " the unpredictability gap, inching towards victory.\n\nIPS-N was"
            + " the first to crack the Yemanova Problem. The corpro's designers"
            + " identified the need for a rapidly deployable, sub-signature,"
            + " directed weapon. It needed to be well-armed and well-armored,"
            + " small enough to enter a ship and efficiently neutralize"
            + " personnel in order to achieve victory. IPS-N created the"
            + " Caliban to solve this need.\n\nCultural critics argue that"
            + " mechanized chassis venerate the form of a particular humanity;"
            + " it is an unconscious nod towards the anthrochauvinist roots of"
            + " the machine among leading designers and fabricators. The"
            + " Caliban is not that. It was never intended to be an image of"
            + " man writ large, striding across the battlefield heroically to"
            + " affect a greater purpose.\n\nUnlike many IPS-N frames, the"
            + " Caliban has no roots in early attempts at self-defense by"
            + " freighter crews and asteroid miners. It was not born from"
            + " ingenuity - there is no legacy of resilience, heroism, or the"
            + " frontier spirit to paper over the purpose of its birth. It has"
            + " no civilian applications in aid, disaster relief, construction,"
            + " or farming; it does not build, defend, or inspire – it was"
            + " designed to solve a numbers problem on a ledger.\n\nIt is a"
            + " tool designed to kill human beings very, very quickly.", 1,
            4, 6, 2, 4, 5,
            8, 3, 8, -2, 3,
            5, 11, 5, new String[]
            {"Wrecking Ball", "Pursue Prey", "Slam", "Weak Computer"},
            new Mount[] {
                new Mount("integrated mount",
                    new Weapon("HHS-075 \"Flayer\" Shotgun",
                    1, 0, new Tag[] {
                        // TODO: disentangle these two weapon profiles
                        new Tag("Inaccurate"),
                        new Tag("Knockback X", 2),
                        new Tag("Accurate"),
                        // new Tag("Knockback X", 5)
                    })
                ),
                new Mount("heavy")
            }
        ),
        new Frame("SSC", "Swallowtail (Ranger Variant)",
            "swallowtail_ranger", FrameEnum.SWALLOWTAIL_RANGER,
            new String[] {"support"}, "This variant can be taken at rank II of"
            + " the SWALLOWTAIL license instead of the base FRAME, or gained as"
            + " EXOTIC GEAR by aiding Dthall Ordo during the campaign.\n\nThis"
            + " Swallowtail variant is common among HUC ranger forces. It was"
            + " adopted and reverse-engineered from the material remains of"
            + " early, abandoned SSC survey expeditions. With no ability to"
            + " print-replicate the mech, the rangers painstakingly"
            + " manufactured and assembled each unit to high specifications."
            + " Unlike the sleek luxury of other SSC frames, this Swallowtail"
            + " is a rugged affair. Each one has a suite of marks left by its"
            + " previous owners – livery, battle paint, custom gear,"
            + " camouflage, and weathering. They have been repaired and"
            + " maintained for two generations and each one is precious to the"
            + " United Cities.", 2, 4, 10, 0, 4,
            6, 8, 4, 8,
            0, 10, 5,
            10, 6, new String[] {"Scout Battlefield",
            "Invigorating Scanners", "Weathering"}, new Mount[] {
                new Mount("flex"),
                new Mount("main")
            }
        ),
        new Frame("HA", "Barbarossa", "barbarossa",
            FrameEnum.BARBAROSSA, new String[] {"artillery"},
            "The Barbarossa is Harrison Armory’s most massive frame to date,"
            + " built, per the orders of Harrison II, to “stand as the"
            + " unstoppable image of Harrison I” and to carry the heaviest of"
            + " weapons and equipment the Armory offers. Standing nearly"
            + " thirteen meters tall, it is an unsubtle beast of a mech,"
            + " inspiring terror in enemies and awe in allies. The Barbarossa"
            + " can mount weapons suitable for engaging toe-to-toe with"
            + " gunboats and low-gross tonnage subline vessels; due to its size"
            + " and slow maneuverability, it is often employed in low-gravity"
            + " engagements where mass is less of a concern.\nThe Barbarossa is"
            + " a popular target for Purview essayists, who have been known to"
            + " remark on the drawbacks that come with its size and how, as a"
            + " result, it is a perfect stand-in for their political enemies.",
            6, 4, 10, 2, 4,
            8, 6, 2, 6, -2,
            10, 4, 10,
            5, new String[] {"Heavy Frame", "Pressure Plating",
            "Colossus", "Slow"}, new Mount[] {
                // TODO: add some kind of automatic action-to-tag translation
                new Mount("integrated mount",
                    new Weapon("Apocalypse Rail", 4,
                    7, new Tag[] {
                        new Tag("Full Action")
                    })
                ),
                new Mount("main"),
                new Mount("main"),
                new Mount("heavy")
            }
        ),
        new Frame("GMS", "Size 4", "size_4",
            FrameEnum.SIZE_4, new String[] {"Balanced"}, "",
            8, 4, 10, 0, 4,
            6, 8, 4, 8,
            0, 10, 5,
            10, 6, new String[0], new Mount[0]
        )
    };
    /**
     * Contains every pilot armor's name for reference. Case-sensitive.
     */
    private static final String[] pilotArmorNames = new String[] {
        "Light Hardsuit", "Assault Hardsuit", "Heavy Hardsuit",
        "Mobility Hardsuit", "Stealth Hardsuit", "Personal Kinetic Shielding",
        "Light Carapace Hardsuit", "Assault Carapace Hardsuit",
        "Heavy Carapace Hardsuit"
    };
    /**
     * Contains every pilot armor's stats for reference.
     * Ordered in the order of pilot stats; in other words:
     *     - HP
     *     - Armor
     *     - Evasion
     *     - Speed
     *     - E-defense
     */
    private static final int[][] pilotArmorStats = new int[][] {
        new int[] {3, 0, 0, 0, 0},
        new int[] {3, 1, -2, 0, -2},
        new int[] {3, 2, -4, -1, -2},
        new int[] {0, 0, 0, 1, 0}, // TODO: allows flight, add this
        // TODO: allows stealth, add this - it's an action
        new int[] {0, 0, -2, 0, -2},
        new int[] {0, 0, 0, 0, 0}, // TODO: has a special effect, add this
        new int[] {2, 0, 0, 0, 0}, // TODO: has a special effect, add this
        new int[] {2, 1, -2, 0, -2}, // TODO: has a special effect, add this
        new int[] {2, 2, -4, -1, -4} // TODO: has a special effect, add this
    };

    // Prevent user from instantiating this class
    private Database() {}

    /**
     * Searches for and returns a Frame matching the given search ID
     * @param searchID a String containing the frame ID of the Frame the user
     *     wants
     * @return a Frame containing the Frame the user wants
     * @throws IllegalArgumentException if no Frame is found matching the
     *     given search ID.
     */
    public static Frame getFrame(String searchID) {
        String frameID;
        String originalSearch = new String(searchID);
        
        searchID = searchID.toLowerCase();
        for (int i = 0; i < frameList.length; i++) {
            frameID = frameList[i].getID();
            if (frameID.equals(searchID)) {
                return new Frame(frameList[i]);
            }
        }
        throw new IllegalArgumentException("No frame found for frame ID: "
            + originalSearch);
    }
    /**
     * Searches for and returns a Frame matching the given search enum
     * @param searchEnum a FrameEnum containing the FrameEnum of the Frame the
     *     user wants
     * @return a Frame containing the Frame the user wants
     * @throws IllegalArgumentException if no Frame is found matching the
     *     given search enum.
     */
    public static Frame getFrame(FrameEnum searchEnum) {
        FrameEnum frameEnum;
        
        for (int i = 0; i < frameList.length; i++) {
            frameEnum = frameList[i].getFrameEnum();
            if (frameEnum == searchEnum) {
                return new Frame(frameList[i]);
            }
        }
        throw new IllegalArgumentException("No frame found for frame enum: "
            + searchEnum.toString());
    }
    /**
     * Searches for a piece of pilot armor given its name.
     * @param pilotArmorName a String containing the name of the pilot armor to
     *     be searched for.
     * @return an int[] of the requested pilot armor's stats.
     * @throws IllegalArgumentException if the requested pilot armor was not
     *     found.
     */
    public static int[] getPilotArmorStats(String pilotArmorName) {
        for (int i = 0; i < Database.pilotArmorNames.length; i++) {
            if (Database.pilotArmorNames[i].equals(pilotArmorName)) {
                return HelperMethods.copyOf(Database.pilotArmorStats[i]);
            }
        }

        throw new IllegalArgumentException("No pilot armor found for pilot"
            + " armor name: " + pilotArmorName);
    }
}
