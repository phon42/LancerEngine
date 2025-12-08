package Packages.CoreTypes;

public enum TriState {
    FALSE(0),
    TRUE(1),
    UNSET(-1);

    private int value;

    private TriState(int val) {
        this.value = val;
    }

    public boolean toBoolean() {
        if (this.value != -1) {
            return this.value == 1;
        }
        throw new IllegalStateException("Cannot convert TriState.UNSET to a"
            + " boolean");
    }
    public static TriState toTriState(boolean booleanValue) {
        return booleanValue ? TriState.TRUE : TriState.FALSE;
    }
}
