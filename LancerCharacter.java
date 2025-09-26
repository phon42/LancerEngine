/**
 * Contains the data of a single Lancer character, including the pilot and their
 *     mech.
 * Stores a Pilot object and a Mech object.
 */
public class LancerCharacter {
    /**
     * Holds a Pilot object. Cannot be a placeholder or have placeholders in
     *     the following fields:
     *     - Pilot Name
     *     - Pilot Callsign
     */
    private Pilot pilot;
    
    /**
     * Holds a Mech object. Can be a placeholder, but must either be a
     *     placeholder or not have placeholders in the following fields:
     *     - Mech/Chassis Name
     *     - Frame
     *     - Mounts
     *     - Size
     *     - Current HP
     *     - Max HP
     *     - Armor
     *     - Current Heat Capacity
     *     - Max Heat Capacity
     *     - Evasion
     *     - Speed
     *     - E-Defense
     *     - Sensors
     *     - Current Repair Capacity
     *     - Max Repair Capacity
     *     - Save Target
     *     - System Points
     *     - Limited Systems Bonus
     */
    private Mech mech;
    
    public LancerCharacter(String pilotName, String pilotCallsign) {
        setPilot(new Pilot(pilotName, pilotCallsign));
        setMech(new Mech());
    }
    
    public Pilot getPilot() {
        return this.pilot;
    }
    public void setPilot(Pilot pilot) {
        if (pilot == null) {
            throw new IllegalArgumentException("New pilot value is null");
        }
        if (pilot.hasPlaceholders()) {
            throw new IllegalArgumentException("New pilot value has"
                + " placeholders");
        }
        this.pilot = pilot;
    }
    public Mech getMech() {
        return this.mech;
    }
    public void setMech(Mech mech) {
        if (mech == null) {
            throw new IllegalArgumentException("New mech is null");
        }
        if (! mech.isPlaceholder()) {
            if (mech.hasPlaceholders()) {
                throw new IllegalArgumentException("New mech value has"
                    + " placeholders");
            }
            mech.calculateAttributes(getPilot().getMechSkills());
        }
        this.mech = mech;
    }

    /**
     * Performs the "Generate Statblock" function from COMP/CON, printing out
     *     details of a pilot and their mech depending on what output is
     *     demanded
     * @param outputType a String controlling which details to include in the
     *     printout
     * @return a String of the mech/pilot's statblock
     */
    public String generateStatblock(String outputType) {
        String outputString = "";
        
        boolean hasMech = true;
        if (getMech().getFrame() == null) {
            hasMech = false;
        }
        
        String manufacturer = "";
        String frameName = "";
        int licenseLevel = getPilot().getLicenseLevel();
        if (hasMech) {
            frameName = getMech().getFrame().getName();
            manufacturer = getMech().getFrame().getManufacturer();
        }

        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            if (! hasMech) {
                outputString += ">> NO MECH SELECTED <<";
                return outputString;
            }
            outputString += "-- " + manufacturer + " " + frameName + " @ LL"
                + licenseLevel + " --\n";
            outputString += getPilot().generateOutput(outputType);
            outputString += getMech().generateOutput(outputType);
            } else if (outputType.equals("pilot")) {
                outputString += getPilot().generateOutput(outputType);
            } else if (outputType.equals("full")) {
                outputString += getPilot().generateOutput(outputType);
                if (! hasMech) {
                    outputString += ">> NO MECH SELECTED <<";
                    return outputString;
                }
                outputString += getMech().generateOutput(outputType,
                    getPilot().getMechSkills(), getPilot().getGrit());
        }

        return outputString;
    }

    public static void main(String[] args) {
        LancerCharacter myCharacter = new LancerCharacter(
            "Coral Nolan", "Apocalypse");
        Pilot myPilot = myCharacter.getPilot();
        Mech myMech = myCharacter.getMech();
        Frame myFrame;
        Loadout myLoadout = new Loadout();

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
        myLoadout.setPilotArmor("Mobility Hardsuit");
        myLoadout.setPilotWeapons(new String[] {"Heavy Signature",
            "Archaic Melee"});
        myLoadout.setPilotGear(new String[] {"Wilderness Survival Kit",
            "Flexsuit", "Personal Drone"});
        myPilot.setLoadout(myLoadout);

        myFrame = FrameDatabase.getFrame("Swallowtail (Ranger Variant)");
        myMech.setFrame(myFrame);
        myMech.setName("Wraith");
        // TODO: add a way for mountType to be omitted and automatically filled
        //     in by the correct mount type as long as the index is valid
        // TODO: make this possible again
        /*
        myMech.setMount(0, new Mount("Integrated Weapon",
            new Weapon("Nexus (Light)")));
        myMech.setMount(1, new Mount("Flex",
            new Weapon("Slag Cannon")));
        myMech.setMount(2, new Mount("Main",
            new Weapon("Vulture DMR"), "",
            "Overpower Caliber"));
        */
        myMech.setMount(0, new Mount("Flex",
            new Weapon("Slag Cannon")));
        myMech.setMount(1, new Mount("Main",
            new Weapon("Vulture DMR"), "",
            "Overpower Caliber"));
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

        System.out.print(myCharacter.generateStatblock("mech build"));
        // TestFunctions.runTests();
    }
}