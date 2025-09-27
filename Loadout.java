/**
 * Represents a pilot gear loadout. Stores information about what gear items the
 *     pilot has chosen.
 */
public class Loadout {
    /**
     * Can be any String. Cannot be null.
     */
    private String pilotArmor;
    /**
     * Must be a String[] of length 2. Cannot be null or contain null elements.
     */
    private String[] pilotWeapons;
    /**
     * Must be a String[] of length 3. Cannot be null or contain null elements.
     */
    private String[] pilotGear;

    public Loadout() {
        setPilotArmor("");
        setPilotWeapons(new String[] {"", ""});
        setPilotGear(new String[] {"", "", ""});
    }
    // TODO: remove if unused
    public Loadout(String pilotArmor) {
        this();
        setPilotArmor(pilotArmor);
    }
    // TODO: remove if unused
    public Loadout(String pilotArmor, String[] pilotWeapons) {
        this();
        setPilotArmor(pilotArmor);
        setPilotWeapons(pilotWeapons);
    }
    // TODO: remove if unused
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
        String outputString = "";
        String[] outputArray;
        String[] newOutputArray;
        boolean test1;
        boolean test2;
        boolean test3;

        test1 = (getPilotArmor().equals(""));
        test2 = (getPilotWeapons()[0].equals("")
            && getPilotWeapons()[1].equals(""));
        test3 = (getPilotGear()[0].equals("")
            && getPilotGear()[1].equals("")
            && getPilotGear()[2].equals(""));
        if (test1 && test2 && test3) {
            outputString += "  N/A\n";
            return outputString;
        }
        if (! test1) {
            outputString += getPilotArmor() + ",";
        }
        if (! test2) {
            for (int i = 0; i < 2; i++) {
                if (! getPilotWeapons()[i].equals("")) {
                    outputString += getPilotWeapons()[i] + ",";
                }
            }
        }
        if (! test3) {
            for (int i = 0; i < 3; i++) {
                if (! getPilotGear()[i].equals("")) {
                    outputString += getPilotGear()[i] + ",";
                }
            }
        }
        outputArray = outputString.split(",");
        newOutputArray = new String[0];
        for (int i = 0; i < outputArray.length; i++) {
            if (! outputArray[i].equals("")) {
                newOutputArray = HelperFunctions.append(newOutputArray,
                    outputArray[i]);
            }
        }
        outputString = "";
        for (int i = 0; i < newOutputArray.length; i += 2) {
            outputString += "  ";
            for (int j = i; j < Math.min(i + 2, newOutputArray.length); j++) {
                outputString += newOutputArray[j];
                if (j + 1 < Math.min(i + 2, newOutputArray.length)) {
                    outputString += ", ";
                }
            }
            if (i + 2 < newOutputArray.length) {
                outputString += ",";
            }
            outputString += "\n";
        }

        return outputString;
    }
}