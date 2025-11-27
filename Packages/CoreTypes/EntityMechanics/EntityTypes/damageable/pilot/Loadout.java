package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotArmor;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotGear;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot.loadout.pilotEquipment.PilotWeapon;

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
     * The loadout's pilot armor (i.e. a PilotArmor representing the Light
     *     Hardsuit).
     * Can be any PilotArmor. Can be null.
     * 
     * See pgs. 108 - 109.
     */
    private PilotArmor pilotArmor;
    /**
     * The loadout's pilot weapons (i.e. a PilotWeapon[] containing Archaic
     *     Melee and Archaic Ranged).
     * Must be a PilotWeapon[] of length 2. Cannot be null.
     * 
     * See pg. 110.
     */
    private PilotWeapon[] pilotWeapons;
    /**
     * The loadout's pilot gear (i.e. a PilotGear[] containing Corrective, Frag
     *     Grenades, and Patch).
     * Must be a PilotGear[] of length 3. Cannot be null.
     * 
     * See pgs. 112 - 114.
     */
    private PilotGear[] pilotGear;

    /**
     * Creates an empty Loadout.
     */
    public Loadout() {
        setPilotArmor(null);
        setPilotWeapons(new PilotWeapon[2]);
        setPilotGear(new PilotGear[3]);
    }
    /**
     * Creates a non-empty Loadout using the provided pilotArmor,
     *     pilotWeapons, and pilotGear.
     */
    public Loadout(PilotArmor pilotArmor, PilotWeapon[] pilotWeapons,
        PilotGear[] pilotGear) {
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

    public PilotArmor getPilotArmor() {
        return pilotArmor;
    }
    public PilotWeapon[] getPilotWeapons() {
        return HelperMethods.copyOf(pilotWeapons);
    }
    public PilotGear[] getPilotGear() {
        return HelperMethods.copyOf(pilotGear);
    }
    public void setPilotArmor(PilotArmor pilotArmor) {
        this.pilotArmor = pilotArmor;
    }
    /**
     * Sets this.pilotWeapons to the provided value.
     * @param pilotWeapons a String[] which cannot be null and must be of length
     *     2.
     * @throws IllegalArgumentException if pilotWeapons is null or of length
     *     other than 2.
     */
    public void setPilotWeapons(PilotWeapon[] pilotWeapons) {
        HelperMethods.checkObject("New pilot weapons value",
            pilotWeapons);
        if (pilotWeapons.length != 2) {
            throw new IllegalArgumentException("New pilot weapon value is"
                + " of invalid length: " + pilotWeapons.length);
        }
        pilotWeapons = HelperMethods.copyOf(pilotWeapons);
        this.pilotWeapons = pilotWeapons;
    }
    /**
     * Sets this.pilotGear to the provided value.
     * @param pilotGear a String[] which cannot be null and must be of length 3.
     * @throws IllegalArgumentException if pilotGear is null or of length other
     *     than 3.
     */
    public void setPilotGear(PilotGear[] pilotGear) {
        HelperMethods.checkObject("New pilot gear value",
            pilotGear);
        if (pilotGear.length != 3) {
            throw new IllegalArgumentException("New pilot gear value is"
                + " of invalid length: " + pilotGear.length);
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
        PilotWeapon weapon;
        PilotWeapon[] weapons;
        PilotGear gear;
        PilotGear[] gearArray;

        if (loadout == null) {
            return false;
        }
        if (! loadout.getPilotArmor().equals(this.pilotArmor)) {
            return false;
        }
        weapons = loadout.getPilotWeapons();
        for (int i = 0; i < this.pilotWeapons.length; i++) {
            weapon = this.pilotWeapons[i];
            if (weapon == null) {
                if (weapons[i] != null) {
                    return false;
                }
            } else if (! weapon.equals(weapons[i])) {
                return false;
            }
        }
        gearArray = loadout.getPilotGear();
        for (int i = 0; i < pilotGear.length; i++) {
            gear = this.pilotGear[i];
            if (gear == null) {
                if (gearArray[i] != null) {
                    return false;
                }
            } else if (! gear.equals(gearArray[i])) {
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

        test1 = this.pilotArmor == null;
        test2 = this.pilotWeapons[0] == null && this.pilotGear[1] == null;
        test3 = this.pilotGear[0] == null && this.pilotGear[1] == null
            && this.pilotGear[2] == null;
        if (test1 && test2 && test3) {
            outputString += "  N/A\n";
            return outputString;
        }
        if (! test1) {
            outputString += this.pilotArmor + ",";
        }
        if (! test2) {
            for (int i = 0; i < 2; i++) {
                if (! (this.pilotWeapons[i] == null)) {
                    outputString += this.pilotWeapons[i] + ",";
                }
            }
        }
        if (! test3) {
            for (int i = 0; i < 3; i++) {
                if (! (this.pilotGear[i] == null)) {
                    outputString += this.pilotGear[i] + ",";
                }
            }
        }
        outputArray = outputString.split(",");
        newOutputArray = new String[0];
        for (int i = 0; i < outputArray.length; i++) {
            if (! (outputArray[i] == null)) {
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