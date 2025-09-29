/**
 * Contains the data of a single Lancer character, including the pilot and their
 *     mech.
 * Stores a Pilot object and a Mech object.
 * To be instantiated, must be provided at minimum pilotName and a pilotCallsign
 *     or a Pilot object with those values.
 */
public class LancerCharacter {
    /**
     * The pilot associated with this character.
     * Holds a Pilot object. Cannot be a placeholder or have placeholders in
     *     the following fields:
     *     - Pilot Name
     *     - Pilot Callsign
     */
    private Pilot pilot;
    
    /**
     * The mech associated with this character (if there is one).
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
    public LancerCharacter(String pilotName, String pilotCallsign, Mech mech) {
        setPilot(new Pilot(pilotName, pilotCallsign));
        setMech(mech);
    }
    // TODO: remove if unused
    public LancerCharacter(Pilot pilot) {
        setPilot(pilot);
        setMech(new Mech());
    }
    // TODO: remove if unused
    public LancerCharacter(Pilot pilot, Mech mech) {
        setPilot(pilot);
        setMech(mech);
    }
    
    public Pilot getPilot() {
        return this.pilot.copyOf();
    }
    /**
     * Sets this.pilot to the value provided.
     * @param pilot a Pilot that cannot be null or have placeholders.
     */
    public void setPilot(Pilot pilot) {
        if (pilot == null) {
            throw new IllegalArgumentException("New pilot value is null");
        }
        if (pilot.hasPlaceholders()) {
            throw new IllegalArgumentException("New pilot value has"
                + " placeholders");
        }
        pilot = pilot.copyOf();
        this.pilot = pilot;
    }
    public Mech getMech() {
        return this.mech.copyOf();
    }
    /**
     * Sets this.mech to the value provided.
     * @param mech a Mech that cannot be null and must EITHER be a placeholder
     *     or not have any placeholders.
     */
    public void setMech(Mech mech) {
        if (mech == null) {
            throw new IllegalArgumentException("New mech is null");
        }
        if (! mech.isPlaceholder()) {
            if (mech.hasPlaceholders()) {
                throw new IllegalArgumentException("New mech value is not a"
                    + " placeholder but has some properties set to placeholder"
                    + " values");
            }
            Pilot pilot = getPilot();
            mech.calculateAttributes(pilot.getMechSkills(),
                pilot.getCoreBonuses(), pilot.getTalents());
        }
        mech = mech.copyOf();
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
            if (getMech().isPlaceholder()) {
                manufacturer = "N/A";
                frameName = "N/A";
            } else {
                manufacturer = getMech().getFrame().getManufacturer();
                frameName = getMech().getFrame().outputName();
            }
        }

        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            if (! hasMech) {
                outputString += ">> NO MECH SELECTED <<";
                return outputString;
            }
            if (getMech().isPlaceholder()) {
                System.out.println(" [ WARNING ]"
                    + " LancerCharacter.generateStats() has been called even"
                    + " though the object's mech value is a placeholder Mech");
            }
            outputString += "-- " + manufacturer + " " + frameName + " @ LL"
                + licenseLevel + " --\n";
            outputString += getPilot().generateOutput(outputType);
            outputString += getMech().generateOutput(outputType);
        } else if (outputType.equals("pilot")) {
            outputString += getPilot().generateOutput(outputType);
        } else if (outputType.equals("full")) {
            if (getMech().isPlaceholder()) {
                System.out.println(" [ WARNING ]"
                    + " LancerCharacter.generateStats() has been called even"
                    + " though the object's mech value is a placeholder Mech");
            }
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
}