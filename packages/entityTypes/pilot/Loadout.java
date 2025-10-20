package packages.entityTypes.pilot;

import main.Database;
import main.HelperMethods;

/**
 * See pgs. 108 - 114.
 */
/**
 * Represents a pilot gear loadout. Contains information about what gear items
 *     the pilot has chosen.
 * 
 * Requires nothing to be instantiated.
 * 
 * Used in Pilot.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public final class Loadout {
    /**
     * The loadout's pilot armor (i.e. "Light Hardsuit").
     * Can be any String. Cannot be null.
     * 
     * See pgs. 108 - 109.
     */
    private String pilotArmor;
    /**
     * The loadout's pilot weapons (i.e. {"Archaic Melee", "Archaic Ranged"}).
     * Must be a String[] of length 2. Cannot be null or contain null elements.
     * 
     * See pg. 110.
     */
    private String[] pilotWeapons;
    /**
     * The loadout's pilot gear (i.e. {"Corrective", "Frag Grenades", "Patch"}).
     * Must be a String[] of length 3. Cannot be null or contain null elements.
     * 
     * See pgs. 112 - 114.
     */
    private String[] pilotGear;

    /**
     * Creates an empty Loadout.
     */
    public Loadout() {
        setPilotArmor("");
        setPilotWeapons(new String[] {"", ""});
        setPilotGear(new String[] {"", "", ""});
    }
    /**
     * Creates a non-empty Loadout using the provided pilotArmor,
     *     pilotWeapons, and pilotGear.
     */
    public Loadout(String pilotArmor, String[] pilotWeapons,
        String[] pilotGear) {
        setPilotArmor(pilotArmor);
        setPilotWeapons(pilotWeapons);
        setPilotGear(pilotGear);
    }
    /**
     * Creates a deepest copy of the provided Loadout.
     * @param loadout a Loadout to be copied.
     * @return a Loadout deepest copy of the provided Loadout.
     */
    public Loadout(Loadout loadout) {
        // don't need to make copies of these because the mutators already do so
        this(loadout.pilotArmor, loadout.pilotWeapons, loadout.pilotGear);
    }

    public String getPilotArmor() {
        return pilotArmor;
    }
    public String[] getPilotWeapons() {
        return HelperMethods.copyOf(pilotWeapons);
    }
    public String[] getPilotGear() {
        return HelperMethods.copyOf(pilotGear);
    }
    public void setPilotArmor(String pilotArmor) {
        if (pilotArmor == null) {
            throw new IllegalArgumentException("New pilot armor value is"
                + " null");
        }
        if (! Database.isValidPilotArmor(pilotArmor)) {
            throw new IllegalArgumentException("Pilot armor value: \""
                + pilotArmor + "\" is an invalid value for Loadout.pilotArmor");
        }
        this.pilotArmor = pilotArmor;
    }
    /**
     * Sets this.pilotWeapons to the provided value.
     * @param pilotWeapons a String[] which cannot be null, must be of length 2,
     *     and cannot contain a null element.
     * @throws IllegalArgumentException if pilotWeapons is null, of length other
     *     than 2, or contains a null element.
     */
    public void setPilotWeapons(String[] pilotWeapons) {
        if (pilotWeapons == null) {
            throw new IllegalArgumentException("New pilot weapon value is"
                + " null");
        }
        if (pilotWeapons.length != 2) {
            throw new IllegalArgumentException("New pilot weapon value is"
                + " of invalid length: " + pilotWeapons.length);
        }
        for (String weapon : pilotWeapons) {
            if (weapon == null) {
                throw new IllegalArgumentException("New pilot weapon value"
                    + " contains a null element");
            }
        }
        pilotWeapons = HelperMethods.copyOf(pilotWeapons);
        this.pilotWeapons = pilotWeapons;
    }
    /**
     * Sets this.pilotGear to the provided value.
     * @param pilotGear a String[] which cannot be null, must be of length 3,
     *     and cannot contain a null element.
     * @throws IllegalArgumentException if pilotGear is null, of length other
     *     than 3, or contains a null element.
     */
    public void setPilotGear(String[] pilotGear) {
        if (pilotGear == null) {
            throw new IllegalArgumentException("New pilot gear value is"
                + " null");
        }
        if (pilotGear.length != 3) {
            throw new IllegalArgumentException("New pilot gear value is"
                + " of invalid length: " + pilotGear.length);
        }
        for (String gear : pilotGear) {
            if (gear == null) {
                throw new IllegalArgumentException("New pilot gear value"
                    + " contains a null element");
            }
        }
        pilotGear = HelperMethods.copyOf(pilotGear);
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
        if (! loadout.getPilotArmor().equals(this.pilotArmor)) {
            return false;
        }
        String weapon;
        for (int i = 0; i < this.pilotWeapons.length; i++) {
            weapon = this.pilotWeapons[i];
            if (! weapon.equals(loadout.getPilotWeapons()[i])) {
                return false;
            }
        }
        String gear;
        for (int i = 0; i < pilotGear.length; i++) {
            gear = this.pilotGear[i];
            if (! gear.equals(loadout.getPilotGear()[i])) {
                return false;
            }
        }
        
        return true;
    }
    /**
     * Generates a String output of the items within this Loadout.
     * @return a String containing an output of the items within this Loadout.
     */
    public String generateOutput() {
        // Generate something along the lines of:
        // "  validArmor, validWeapon,\n"
        // "  validWeapon, validGear,\n"
        // "  validGear, validGear\n"
        String outputString = "";
        String[] outputArray;
        String[] newOutputArray;
        boolean test1;
        boolean test2;
        boolean test3;

        test1 = (this.pilotArmor.equals(""));
        test2 = (this.pilotWeapons[0].equals("")
            && this.pilotGear[1].equals(""));
        test3 = (this.pilotGear[0].equals("")
            && this.pilotGear[1].equals("")
            && this.pilotGear[2].equals(""));
        if (test1 && test2 && test3) {
            outputString += "  N/A\n";
            return outputString;
        }
        if (! test1) {
            outputString += this.pilotArmor + ",";
        }
        if (! test2) {
            for (int i = 0; i < 2; i++) {
                if (! this.pilotWeapons[i].equals("")) {
                    outputString += this.pilotWeapons[i] + ",";
                }
            }
        }
        if (! test3) {
            for (int i = 0; i < 3; i++) {
                if (! this.pilotGear[i].equals("")) {
                    outputString += this.pilotGear[i] + ",";
                }
            }
        }
        outputArray = outputString.split(",");
        newOutputArray = new String[0];
        for (int i = 0; i < outputArray.length; i++) {
            if (! outputArray[i].equals("")) {
                newOutputArray = HelperMethods.append(newOutputArray,
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