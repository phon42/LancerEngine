public class MechSystem {
  // TODO: fill out
  private String systemName;

  private boolean limited;
  private int limitedCharges;

  public String getSystemName() {
    return systemName;
  }
  public boolean isLimited() {
    return limited;
  }
  public int getLimitedCharges() {
    return limitedCharges;
  }
  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }
  public void setLimited(boolean limited) {
    this.limited = limited;
  }
  public void setLimitedCharges(int limitedCharges) {
    this.limitedCharges = limitedCharges;
  }

  public String outputSystem(String outputType) {
    String outputString = "";

    if (outputType == "mech build" || outputType == "full") {
      outputString += systemName;
    }

    return outputString;
  }
}