public class Mech {
  // frame that this mech is (i.e. Swallowtail)
  Frame frame;
  
  // mounts/weapons
  Mount[] mounts;

  // systems
  MechSystem[] systems;

  // frame attributes - size, structure, HP, etc.
  /** Size is stored as 2 * its value (Size 1/2 would be stored as int 1) */
  private int size;

  // health and structure
  private int currentStructure;
  private int maxStructure;
  private int currentHP;
  private int maxHP;
  private int armor;

  // heat and stress
  private int currentStress;
  private int maxStress;
  private int currentHeatCapacity;
  private int maxHeatCapacity;

  // evasion and speed
  private int evasion;
  private int speed;

  // e-defense and tech attack
  private int eDefense;
  private int techAttack;

  // sensors and repair capacity
  private int sensors;
  private int currentRepairCapacity;
  private int maxRepairCapacity;

  // save target and system points
  private int saveTarget;
  private int systemPoints;

  // limited systems bonus
  private int limitedSystemsBonus;

  public Mech() {
    currentStructure = 4;
    maxStructure = 4;
    currentStress = 4;
    maxStress = 4;
  }

  public Frame getFrame() {
    return frame;
  }
  public void setFrame(Frame newFrame) {
    frame = newFrame;
  }
  public Mount[] getMounts() {
    return mounts;
  }
  public void setMounts(Mount[] newMounts) {
    mounts = newMounts;
  }
  public MechSystem[] getSystems() {
    return systems;
  }
  public void setSystems(MechSystem[] newSystems) {
    systems = newSystems;
  }
  public int getSize() {
    return size;
  }
  public void setSize(int newSize) {
    size = newSize;
  }
  public int getCurrentStructure() {
    return currentStructure;
  }
  public void setCurrentStructure(int newCurrentStructure) {
    currentStructure = newCurrentStructure;
  }
  public int getMaxStructure() {
    return maxStructure;
  }
  public void setMaxStructure(int newMaxStructure) {
    maxStructure = newMaxStructure;
  }
  public int getCurrentHP() {
    return currentHP;
  }
  public void setCurrentHP(int newCurrentHP) {
    if (currentHP < 0) {
      throw new IllegalArgumentException(
        "Error: currentHP value provided: " + newCurrentHP + " is less than 0"
      );
    } else if (currentHP > getMaxHP()) {
      throw new IllegalArgumentException(
        "Error: currentHP value provided: " + newCurrentHP + " is greater than maxHP value " + getMaxHP()
      );
    } else {
      currentHP = newCurrentHP;
    }
  }
  public int getMaxHP() {
    return maxHP;
  }
  public void setMaxHP(int newMaxHP) {
    maxHP = newMaxHP;
  }
  public int getArmor() {
    return armor;
  }
  public void setArmor(int newArmor) {
    armor = newArmor;
  }
  public int getCurrentStress() {
    return currentStress;
  }
  public void setCurrentStress(int newCurrentStress) {
    currentStress = newCurrentStress;
  }
  public int getMaxStress() {
    return maxStress;
  }
  public void setMaxStress(int newMaxStress) {
    maxStress = newMaxStress;
  }
  public int getCurrentHeatCapacity() {
    return currentHeatCapacity;
  }
  public void setCurrentHeatCapacity(int newCurrentHeatCapacity) {
    currentHeatCapacity = newCurrentHeatCapacity;
  }
  public int getMaxHeatCapacity() {
    return maxHeatCapacity;
  }
  public void setMaxHeatCapacity(int newMaxHeatCapacity) {
    maxHeatCapacity = newMaxHeatCapacity;
  }
  public int getEvasion() {
    return evasion;
  }
  public void setEvasion(int newEvasion) {
    evasion = newEvasion;
  }
  public int getSpeed() {
    return speed;
  }
  public void setSpeed(int newSpeed) {
    speed = newSpeed;
  }
  public int getEDefense() {
    return eDefense;
  }
  public void setEDefense(int newEDefense) {
    eDefense = newEDefense;
  }
  public int getTechAttack() {
    return techAttack;
  }
  public void setTechAttack(int newTechAttack) {
    techAttack = newTechAttack;
  }
  public int getSensors() {
    return sensors;
  }
  public void setSensors(int newSensors) {
    sensors = newSensors;
  }
  public int getCurrentRepairCapacity() {
    return currentRepairCapacity;
  }
  public void setCurrentRepairCapacity(int newCurrentRepairCapacity) {
    currentRepairCapacity = newCurrentRepairCapacity;
  }
  public int getMaxRepairCapacity() {
    return maxRepairCapacity;
  }
  public void setMaxRepairCapacity(int newMaxRepairCapacity) {
    maxRepairCapacity = newMaxRepairCapacity;
  }
  public int getSaveTarget() {
    return saveTarget;
  }
  public void setSaveTarget(int newSaveTarget) {
    saveTarget = newSaveTarget;
  }
  public int getSystemPoints() {
    return systemPoints;
  }
  public void setSystemPoints(int newSystemPoints) {
    systemPoints = newSystemPoints;
  }
  public int getLimitedSystemsBonus() {
    return limitedSystemsBonus;
  }
  public void setLimitedSystemsBonus(int newLimitedSystemsBonus) {
    limitedSystemsBonus = newLimitedSystemsBonus;
  }

  public void calculateAttributes(int[] mechSkills) throws Exception {
    // TODO: fill out
    // Calculate this mech's attributes based off of the frame template provided
    if (frame == null) {
      throw new Exception("calculateAttributes() was called while frame was set to null");
    }
    // Hull
    maxHP = frame.getHP() + (mechSkills[0] * 2);
    currentHP = maxHP;
    maxRepairCapacity = frame.getRepairCapacity() + (mechSkills[0] / 2);

    // Agility
    evasion = frame.getEvasion() + mechSkills[1];
    speed = frame.getSpeed() + (mechSkills[1] / 2);

    // Systems
    eDefense = frame.getEDefense() + mechSkills[2];
    techAttack = frame.getTechAttack() + mechSkills[2];
    systemPoints = frame.getSystemPoints() + (mechSkills[2] / 2);

    // Engineering
    maxHeatCapacity = frame.getHeatCapacity() + mechSkills[3];
    currentHeatCapacity = 0;
    limitedSystemsBonus = mechSkills[3] / 2;
  }
  public String outputStats(String outputType) {
    String outputString = "";

    outputType = outputType.toLowerCase();
    if (outputType == "mech build") {
      outputString += "  STRUCTURE:4 HP:19 ARMOR:0\n";
      outputString += "  STRESS:4 HEATCAP:6 REPAIR:7\n";
      outputString += "  TECH ATK:+6 LIMITED:+1\n";
      outputString += "  SPD:6 EVA:10 EDEF:13 SENSE:20 SAVE:15\n";
    } else if (outputType == "full") {
      outputString += " SIZE:1\n";
      outputString += "  STRUCTURE:2/4 HP:19/19 ARMOR:0\n";
      outputString += "  STRESS:3/4 HEAT:0/6 REPAIR:7/7\n";
      outputString += "  ATK BONUS:5 TECH ATK:6 LTD BONUS:1\n";
      outputString += "  SPD:6 EVA:10 EDEF:13 SENS:20 SAVE:15\n";
    }

    return outputString;
  }
  public String outputWeapons(String outputType) {
    // output something along the lines of
    // "  MAIN MOUNT: Vulture DMR // Overpower Caliber\n"
    // or "  INTEGRATED WEAPON: Nexus (Light)\n"
    String outputString = "";

    if (mounts == null || mounts.length == 0) {
      return outputString;
    }
    outputType = outputType.toLowerCase();
    if (outputType == "mech build" || outputType == "full") {
      for (int i = 0; i < mounts.length; i++) {
        outputString += "  ";
        outputString += mounts[i].outputWeapon();
        outputString += "\n";
      }
    }

    return outputString;
  }
  public String outputSystems(String outputType) {
    String outputString = "";

    if (systems == null || systems.length == 0) {
      return outputString;
    }
    outputType = outputType.toLowerCase();
    if (outputType == "mech build") {
      // output something like "  Pattern-A Smoke Charges x4, Seismic Ripper, High-Stress Mag Clamps, ATHENA-Class NHP"
      for (int i = 0; i < systems.length; i++) {
        if (i == 0) {
          outputString += "  ";
        }
        outputString += outputSystem(outputType, systems[i]);
        if (i != systems.length - 1) {
          outputString += ", ";
        }
      }
    } else if (outputType == "full") {
      // output something along the lines of "  Ammo Case II, Pattern-A Smoke Charges,\n"
      //     + "  Seismic Ripper, High-Stress Mag Clamps"
      for (int i = 0; i < systems.length; i += 2) {
        outputString += "  ";
        for (int ii = i; ii < Math.min(systems.length, i + 2); ii++) {
          outputString += outputSystem(outputType, systems[ii]);
          if (ii == i && ii + 2 < systems.length) {
            outputString += ", ";
          }
        }
        if (i + 2 < systems.length) {
          outputString += ",\n";
        }
      }
    }

    return outputString;
  }
  public String outputSystem(String outputType, MechSystem system) {
    String outputString = "";

    outputType = outputType.toLowerCase();
    if (outputType == "full") {
      // output something like "Pattern-A Smoke Charges"
      outputString += system.outputSystem(outputType);
    }
    if (outputType == "mech build" && system.isLimited()) {
      // add something like " x4"
      outputString += " x" + (system.getLimitedCharges() + limitedSystemsBonus);
    }

    return outputString;
  }
}