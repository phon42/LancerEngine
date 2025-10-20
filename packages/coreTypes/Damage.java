package packages.coreTypes;

import main.HelperMethods;

public class Damage {
    /**
     * The Damage's type (i.e. "Blast").
     * Must be an allowed type as defined by Damage.allowedTypes. Cannot be
     *     null.
     */
    private String type;
    /**
     * Contains an array of allowed damage types.
     * Case-insensitive and stored in lowercase.
     */
    public static final String[] allowedTypes = new String[] {"kinetic",
        "explosive", "energy", "variable", "heat", "burn"};
    /**
     * The amount of dice damage dealt (i.e. "1d6", representing the "1d6" in
     *     "1d6+2").
     * Can be any String that contains a valid dice expression. Can be "".
     *     Cannot be null.
     */
    private String diceValue;
    /**
     * The amount of flat damage dealt (i.e. 2, representing the "2" in
     *     "1d6+2").
     * Must be a minimum of -1.
     */
    private int flatValue;

    public Damage(String damageType, String damageDice, int damageFlatAmount) {
        setType(damageType);
        setDiceValue(damageDice);
        setFlatValue(damageFlatAmount);
    }
    public Damage(Damage damage) {
        setType(damage.type);
        setDiceValue(damage.diceValue);
        setFlatValue(damage.flatValue);
    }
    
    public String getType() {
        return type;
    }
    public String getDiceValue() {
        return diceValue;
    }
    public int getFlatValue() {
        return flatValue;
    }
    private void setType(String type) {
        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        // TODO: create methods like this for all the checkable final arrays
        if (! Damage.isValid(type)) {
            throw new IllegalArgumentException("type value: \"" + type + "\" is"
                + " an invalid type");
        }
        this.type = type;
    }
    private void setDiceValue(String diceValue) {
        if (diceValue == null) {
            throw new IllegalArgumentException("diceValue is null");
        }
        diceValue = diceValue.toLowerCase();
        // TODO: add some way of making sure diceValue is a valid dice
        //     expression
        if (diceValue.equals("") || true) {
            this.diceValue = diceValue;
        }
    }
    private void setFlatValue(int flatValue) {
        if (flatValue < -1) {
            throw new IllegalArgumentException("flat value value: " + flatValue
                + " is < -1");
        }
        this.flatValue = flatValue;
    }

    public static boolean isValid(String type) {
        boolean isValid = false;

        for (int i = 0; i < Damage.allowedTypes.length; i++) {
            if (type.equals(Damage.allowedTypes[i])) {
                isValid = true;
            }
        }

        return isValid;
    }
}