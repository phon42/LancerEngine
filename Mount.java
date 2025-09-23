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

  public String outputWeapon() {
    // " (BOUNDER-Class Comp/Con) // Overpower Caliber"
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