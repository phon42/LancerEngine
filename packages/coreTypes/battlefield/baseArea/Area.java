package Packages.CoreTypes.Battlefield.baseArea;

import Packages.CoreTypes.Battlefield.BaseArea;
import Packages.CoreTypes.Battlefield.Hex;
import main.HelperMethods;

/**
 * Represents a line, cone, blast, or burst-type pattern and the spaces on the
 *     battlefield it covers. Contains information about that area's size and
 *     which spaces it occupies.
 * 
 * Requires a type, a value, and an array of control points to be instantiated.
 * 
 * Used in myriad other classes.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its private properties has a value of null,
 *     but this class is not capable of outputting null through any of its
 *     methods.
 */
public class Area extends BaseArea {
    /**
     * This Area's type (i.e. "line").
     * Must be a valid type as defined by Area.allowedTypes. Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    // TODO: figure out how to override BaseArea's documentation
    // private String type;
    /**
     * Contains an array of allowed values for Area.type.
     * Case-insensitive and stored in lowercase.
     */
    private static final String[] allowedTypes = {"line", "cone", "blast",
        "burst"};

    public Area(String type, int value, Hex... controlPoints) {
        super();
        setType(type);
        setValue(value);
        setControlPoints(controlPoints);
    }

    @Override
    protected void setType(String type) {
        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        for (String allowedType : Area.allowedTypes) {
            if (type.equals(allowedType)) {
                this.type = type;
                this.isSpacesCurrent = false;
                return;
            }
        }
        throw new IllegalArgumentException("type value: \"" + type + "\" is"
            + " not a valid value for Area.type");
    }
}