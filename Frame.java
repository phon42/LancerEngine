public class Frame {
  // frame manufacturer (i.e. GMS)
  String manufacturer;
  // frame name (i.e. Everest)
  String frameName;
  // role (i.e. Striker)
  String[] role;
  // desc at the top of the page (i.e. "Most humans don’t...")
  String frameDescription;

  // frame attributes - size, structure, HP, etc.
  /** Size is stored as 2 * its value (Size 1/2 would be stored as int 1) */
  private int size;

  // health and structure
  private int structure;
  private int HP;
  private int armor;

  // heat and stress
  private int stress;
  private int heatCapacity;

  // evasion and speed
  private int evasion;
  private int speed;

  // e-defense and tech attack
  private int eDefense;
  private int techAttack;

  // sensors and repair capacity
  private int sensors;
  private int repairCapacity;

  // save target and system points
  private int saveTarget;
  private int systemPoints;

  // frame traits
  String[] traits;

  // weapon mounts
  Mount[] mounts;

  // core system
  // core system description
  // core system passive
  // core system active

  public Frame() {
    structure = 4;
    stress = 4;
  }

  public String getManufacturer() {
    return manufacturer;
  }
  public void setManufacturer(String newManufacturer) {
    manufacturer = newManufacturer;
  }
  public String getFrameName() {
    return frameName;
  }
  public void setFrameName(String newFrameName) {
    frameName = newFrameName;
  }
  public String[] getRole() {
    return role;
  }
  public void setRole(String[] newRole) {
    role = newRole;
  }
  public String getFrameDescription() {
    return frameDescription;
  }
  public void setFrameDescription(String newFrameDescription) {
    frameDescription = newFrameDescription;
  }
  public int getSize() {
    return size;
  }
  public void setSize(int newSize) {
    size = newSize;
  }
  public int getStructure() {
    return structure;
  }
  public void setStructure(int newStructure) {
    structure = newStructure;
  }
  public int getHP() {
    return HP;
  }
  public void setHP(int newHP) {
    HP = newHP;
  }
  public int getArmor() {
    return armor;
  }
  public void setArmor(int newArmor) {
    armor = newArmor;
  }
  public int getStress() {
    return stress;
  }
  public void setStress(int newStress) {
    stress = newStress;
  }
  public int getHeatCapacity() {
    return heatCapacity;
  }
  public void setHeatCapacity(int newHeatCapacity) {
    heatCapacity = newHeatCapacity;
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
  public int getRepairCapacity() {
    return repairCapacity;
  }
  public void setRepairCapacity(int newRepairCapacity) {
    repairCapacity = newRepairCapacity;
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
  public String[] getTraits() {
    return traits;
  }
  public void setTraits(String[] newTraits) {
    traits = newTraits;
  }
  public Mount[] getMounts() {
    return mounts;
  }
  public void setMounts(Mount[] newMounts) {
    mounts = newMounts;
  }
}