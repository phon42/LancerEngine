package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable;

public class Size {
    private int value;

    public Size(int value) {
        setValue(value);
    }
    public Size(Size size) {
        setValue(size.value);
    }

    public int getValue() {
        return value;
    }
    private void setValue(int value) {
        this.value = value;
    }
}
