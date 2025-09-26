/**
 * Represents a pilot gear loadout. Stores information about what gear items the
 *     pilot has chosen.
 */
public class Loadout {
    private String pilotArmor;
    private String[] pilotWeapons;
    private String[] pilotGear;

    public Loadout() {
        setPilotArmor("");
        setPilotWeapons(new String[] {"", ""});
        setPilotGear(new String[] {"", "", ""});
    }
    public Loadout(String pilotArmor, String[] pilotWeapons,
        String[] pilotGear) {
        setPilotArmor(pilotArmor);
        setPilotWeapons(pilotWeapons);
        setPilotGear(pilotGear);
    }

    public String getPilotArmor() {
        return pilotArmor;
    }
    public String[] getPilotWeapons() {
        return pilotWeapons;
    }
    public String[] getPilotGear() {
        return pilotGear;
    }
    /**
     * Sets this.pilotArmor to the provided value.
     * @param pilotArmor a String which cannot be null.
     */
    public void setPilotArmor(String pilotArmor) {
        if (pilotArmor == null) {
            throw new IllegalArgumentException("New pilot armor value is"
                + " null");
        }
        this.pilotArmor = pilotArmor;
    }
    /**
     * Sets this.pilotWeapons to the provided value.
     * @param pilotWeapons a String[] which cannot be null, must be of length 2,
     *     and cannot contain a null element.
     */
    public void setPilotWeapons(String[] pilotWeapons) {
        if (pilotWeapons == null) {
            throw new IllegalArgumentException("New pilot weapon value is"
                + " null");
        }
        if (pilotWeapons.length != 2) {
            throw new IllegalArgumentException("New pilot weapon value is"
                + " of invalid length");
        }
        for (String weapon : pilotWeapons) {
            if (weapon == null) {
                throw new IllegalArgumentException("New pilot weapon value"
                    + " contains a null element");
            }
        }
        this.pilotWeapons = pilotWeapons;
    }
    /**
     * Sets this.pilotGear to the provided value.
     * @param pilotGear a String[] which cannot be null, must be of length 3,
     *     and cannot contain a null element.
     */
    public void setPilotGear(String[] pilotGear) {
        if (pilotGear == null) {
            throw new IllegalArgumentException("New pilot gear value is"
                + " null");
        }
        if (pilotGear.length != 3) {
            throw new IllegalArgumentException("New pilot gear value is"
                + " of invalid length");
        }
        for (String gear : pilotGear) {
            if (gear == null) {
                throw new IllegalArgumentException("New pilot gear value"
                    + " contains a null element");
            }
        }
        this.pilotGear = pilotGear;
    }

    /**
     * Compares this Loadout object and obj. If they are the same class, returns
     *     true.
     * @param obj an Object to be compared to.
     * @return a boolean representing whether the two Objects are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        return true;
    }
    /**
     * Compares this Loadout object and loadout. If they have the same property
     *     values, returns true.
     * @param loadout a Loadout to be compared to.
     * @return a boolean representing whether the two Loadouts are the same.
     */
    public boolean equals(Loadout loadout) {
        if (loadout == null) {
            return false;
        }
        if (! loadout.getPilotArmor().equals(pilotArmor)) {
            return false;
        }
        String weapon;
        for (int i = 0; i < pilotWeapons.length; i++) {
            weapon = pilotWeapons[i];
            if (! weapon.equals(loadout.getPilotWeapons()[i])) {
                return false;
            }
        }
        String gear;
        for (int i = 0; i < pilotGear.length; i++) {
            gear = pilotGear[i];
            if (! gear.equals(loadout.getPilotGear()[i])) {
                return false;
            }
        }
        
        return true;
    }
    /**
     * Generates a String output of the items within this Loadout.
     * @return a String representing an output of the items within this Loadout.
     */
    public String generateOutput() {
        // TODO: fill out
        String outputString = "";
        
        outputString += "  N/A\n";

        return outputString;
    }
}