/**
 * Represents a single mount on a mech. Contains information such as what
 *     type this mount is, what weapon is mounted on it (if there is one),
 *     whether it has any modifications and whether there is a core bonus
 *     attached to it.
 */
public class Mount {
    private final String[] allowedMountTypes = {"aux", "aux/aux", "flex", "heavy", "integrated weapon", "main", "main/aux"};
    // allowed values: "aux", "aux/aux", "flex", "heavy", "integrated weapon", "main", "main/aux"
    private String mountType;
    private Weapon weapon;
    private boolean hasModification;
    private String modification;
    private boolean hasCoreBonus;
    private String coreBonus;

    public Mount() {
        hasModification = false;
        hasCoreBonus = false;
    }

    public String getMountType() {
        return mountType;
    }
    public void setMountType(String mountType) throws IllegalArgumentException {
        boolean isValidType = false;
        String exceptionMessage = "Given mount type is not one of the allowed mount types:\n\"Aux\", \"Aux/Aux\", \"Flex\", \"Heavy\", \"Integrated Weapon\", \"Main\", \"Main/Aux\"";

        mountType = mountType.toLowerCase();
        for (int i = 0; i < allowedMountTypes.length; i++) {
            if (allowedMountTypes[i].equals(mountType)) {
                isValidType = true;
                break;
            }
        }
        if (! isValidType) {
            throw new IllegalArgumentException(exceptionMessage);
        }
        this.mountType = mountType;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public boolean isHasModification() {
        return hasModification;
    }
    public String getModification() {
        return modification;
    }
    public boolean isHasCoreBonus() {
        return hasCoreBonus;
    }
    public String getCoreBonus() {
        return coreBonus;
    }
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    // Setters for hasModification and hasCoreBonus removed
    public void setModification(String modification) {
        if (modification == null) {
            hasModification = false;
        } else {
            hasModification = true;
        }
        this.modification = modification;
    }
    public void setCoreBonus(String coreBonus) {
        if (coreBonus == null) {
            hasCoreBonus = false;
        } else {
            hasCoreBonus = true;
        }
        this.coreBonus = coreBonus;
    }

    /**
     * Outputs a short snippet of text in the style of:
     *     "MAIN MOUNT: Assault Rifle (BOUNDER-Class Comp/Con) // Overpower Caliber"
     * @return a String representing the mount and any weapons mounted on it
     */
    public String outputWeapon() {
        String outputString = "";

        outputString += mountType.toUpperCase();
        if (mountType != "integrated weapon") {
            outputString += " MOUNT";
        }
        outputString += ": ";
        if (weapon != null) {
            outputString += weapon.getWeaponName();
            if (hasModification) {
                outputString += " (" + modification + ")";
            }
            if (hasCoreBonus) {
                outputString += " // " + coreBonus;
            }
        }

        return outputString;
    }
}
