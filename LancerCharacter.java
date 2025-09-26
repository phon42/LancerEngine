/**
 * Contains the data of a single Lancer character, including the pilot and their
 *     mech
 * Stores a Pilot object and a Mech object
 */
public class LancerCharacter {
    // pilot
    private Pilot pilot;
    
    // mech
    private Mech mech;
    
    public LancerCharacter() {
        setPilot(new Pilot());
        setMech(new Mech());
    }
    
    public Pilot getPilot() {
        return this.pilot;
    }
    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }
    public Mech getMech() {
        return this.mech;
    }
    public void setMech(Mech mech) {
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
            frameName = getMech().getFrame().getFrameName();
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
                    getPilot().getMechSkills());
        }

        return outputString;
    }

    public static void main(String[] args) {
        LancerCharacter myCharacter = new LancerCharacter();
        Pilot myPilot = new Pilot();
        Mech myMech = new Mech();

        myPilot.setName("Coral Nolan");
        myPilot.setCallsign("Apocalypse");
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
        
        myCharacter.setPilot(myPilot);
        myCharacter.setMech(myMech);

        System.out.print(myCharacter.generateStatblock("full"));
        // TestFunctions.runTests();
    }
}