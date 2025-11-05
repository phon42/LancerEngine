package Packages.CoreTypes;

public enum TriState {
    TRUE(1),
    FALSE(0),
    UNSET(-1);

    private int value;

    private TriState(int val) {
        this.value = val;
    }

    public boolean toBoolean() {
        if (this.value > -1) {
            return this.value == 1;
        }
        throw new IllegalStateException("Cannot convert TriState.UNSET to a"
            + " boolean");
    }
    public static TriState toTriState(boolean booleanValue) {
        if (booleanValue) {
            return TriState.TRUE;
        }

        return TriState.FALSE;
    }
}
