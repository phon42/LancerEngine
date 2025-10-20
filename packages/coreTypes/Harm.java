package packages.coreTypes;

import main.HelperMethods;

/**
 * See pgs. 67 and 104.
 */
public class Harm extends Damage {
    /**
     * The Harm's type (i.e. "Blast").
     * Must be an allowed type as defined by Harm.allowedTypes. Cannot be null.
     */
    private String type;
    /**
     * Contains an array of allowed harm types.
     * Case-insensitive and stored in lowercase.
     * See pgs. 67 and 104.
     */
    public static final String[] allowedTypes = new String[] {"kinetic",
        "explosive", "energy", "variable", "burn", "heat"};
    /**
     * The amount of dice harm dealt (i.e. "1d6", representing the "1d6" in
     *     "1d6+2").
     * Can be any String that contains a valid dice expression. Can be "".
     *     Cannot be null.
     */
    private String diceValue;
    /**
     * The amount of flat harm dealt (i.e. 2, representing the "2" in
     *     "1d6+2").
     * Must be a minimum of -1.
     */
    private int flatValue;

    public Harm(String harmType, String harmDice, int harmFlatAmount) {
        super("kinetic", harmDice, harmFlatAmount);
        setType(harmType);
    }
    public Harm(Harm harm) {
        super(new Damage("kinetic", harm.diceValue, harm.flatValue));
        setType(harm.type);
    }

    @Override
    protected void setType(String type) {
        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        // TODO: create methods like this for all the checkable final arrays
        if (! Harm.isValid(type)) {
            throw new IllegalArgumentException("type value: \"" + type + "\" is"
                + " an invalid type");
        }
        this.type = type;
    }

    public static boolean isValid(String type) {
        boolean isValid = false;

        for (int i = 0; i < Harm.allowedTypes.length; i++) {
            if (type.equals(Harm.allowedTypes[i])) {
                isValid = true;
            }
        }

        return isValid;
    }
}