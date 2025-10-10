// TODO: add some kind of way for Pilot and Mech's actions to bubble up
/**
 * Represents a single Lancer character, including the pilot and their mech.
 *     Contains a Pilot object and a Mech object.
 * 
 * Requires at minimum a pilot name and a pilot callsign or a Pilot object with
 *     those values.
 * 
 * Used in Main.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class LancerCharacter {
    /**
     * The pilot associated with this character.
     * Can be any Pilot that does not have placeholders in the following fields:
     *     - Pilot Name
     *     - Pilot Callsign
     */
    private Pilot pilot;
    
    /**
     * The mech associated with this character (if there is one).
     * Can be any Mech. Can be null.
     */
    private Mech mech;
    
    /**
     * Creates a new LancerCharacter from a pilotName and a pilotCallsign. Sets
     *     this.mech to null.
     */
    public LancerCharacter(String pilotName, String pilotCallsign) {
        setPilot(new Pilot(pilotName, pilotCallsign));
        setMech(null);
    }
    /**
     * Creates a new LancerCharacter from a pilotName, a pilotCallsign, and a
     *     mech.
     */
    public LancerCharacter(String pilotName, String pilotCallsign, Mech mech) {
        setPilot(new Pilot(pilotName, pilotCallsign));
        setMech(mech);
    }
    
    public Pilot getPilot() {
        return pilot.copyOf();
    }
    public Mech getMech() {
        if (mech == null) {
            return mech;
        }
        return mech.copyOf();
    }
    /**
     * Sets this.pilot to the value provided.
     * @param pilot a Pilot that cannot be null or have placeholders.
     * @throws IllegalArgumentException if pilot is null.
     */
    public void setPilot(Pilot pilot) {
        if (pilot == null) {
            throw new IllegalArgumentException("New pilot value is null");
        }
        pilot = pilot.copyOf();
        this.pilot = pilot;
    }
    /**
     * Sets this.mech to the value provided. Calls Mech.calculateAttributes()
     *     before setting this.mech to it.
     * @param mech a Mech.
     */
    public void setMech(Mech mech) {
        if (mech != null) {
            mech.calculateAttributes(this.pilot.getMechSkills(),
                this.pilot.getCoreBonuses(), this.pilot.getTalents());
            mech = mech.copyOf();
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
        if (this.mech == null) {
            hasMech = false;
        }
        
        String manufacturer = "";
        String frameName = "";
        int licenseLevel = this.pilot.getLicenseLevel();
        if (hasMech) {
            manufacturer = this.mech.getFrame().getManufacturer();
            frameName = this.mech.getFrame().outputName();
        }

        outputType = outputType.toLowerCase();
        if (outputType.equals("mech build")) {
            if (! hasMech) {
                outputString += ">> NO MECH SELECTED <<";
                return outputString;
            }
            outputString += String.format(
                "-- %s %s @ LL%d --\n",
                manufacturer, frameName, licenseLevel
            );
            outputString += this.pilot.generateOutput(outputType);
            outputString += this.mech.generateOutput(outputType,
                this.mech.getLimitedSystemsBonus());
        } else if (outputType.equals("pilot")) {
            outputString += this.pilot.generateOutput(outputType);
        } else if (outputType.equals("full")) {
            outputString += this.pilot.generateOutput(outputType);
            if (! hasMech) {
                outputString += ">> NO MECH SELECTED <<";
                return outputString;
            }
            outputString += this.mech.generateOutput(outputType,
                this.pilot.getMechSkills(), this.pilot.getGrit());
        }

        return outputString;
    }
}