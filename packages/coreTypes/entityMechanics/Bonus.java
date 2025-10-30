package packages.coreTypes.entityMechanics;

import main.HelperMethods;

public class Bonus {
    // TODO: fill out
    private String id;
    private int val;
    private boolean replace;

    public Bonus(String id, int val, boolean replace) {
        setID(id);
        setVal(val);
        setReplace(replace);
    }
    public Bonus(String id, int val) {
        this(id, val, false);
    }

    public String getID() {
        return id;
    }
    public int getVal() {
        return val;
    }
    public boolean isReplace() {
        return replace;
    }
    public void setID(String id) {
        HelperMethods.checkString("id", id);
        this.id = id;
    }
    public void setVal(int val) {
        this.val = val;
    }
    public void setReplace(boolean replace) {
        this.replace = replace;
    }
}
