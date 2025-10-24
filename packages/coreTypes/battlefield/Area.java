package packages.coreTypes.battlefield;

import main.HelperMethods;

/**
 * Represents any kind of patterned area (for example, a line, cone, or
 *     blast-type pattern). Contains information about that area's size and
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
public class Area {
    /**
     * This Area's type (i.e. "line").
     * Must be a valid type as defined by Area.allowedTypes. Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String type;
    private static final String[] allowedTypes = {"line", "cone", "blast",
        "burst"};
    /**
     * The value (depending on the value of this.type, defines size in a varying
     *     way) of this Area.
     * - For lines, defines the length of the line.
     *   - Must be a minimum of 1.
     * - For cones, defines both the width of the cone at the edge, as well as
     *       the distance from the origin point to the edge.
     *   - Must be a minimum of 1.
     * - For blasts, defines the radius of the blast. Blast 1 defines an area
     *       the size of a single hex plus a ring 1 hex wide around it.
     *   - Must be a minimum of 0.
     * - For bursts, defines the additional radius of the burst beyond the
     *       entity it's centered on. Bursts add to the size of the base entity,
     *       as opposed to blasts which always define an area of the same size.
     *       For example, a Burst 1 area around a Size 1 entity is an area the
     *       size of a Size 3 entity, while a Burst 1 area around a Size 2
     *       entity is an area the size of a Size 4 entity; a Blast 1 area
     *       remains the same (the size of a Size 2 entity) no matter what.
     *   - Must be a minimum of 1.
     * Must be a valid value as defined above.
     */
    private int value;
    /**
     * This area's "control points" - a number of points that help define it.
     * - A line requires two control points which form its endpoints; the first
     *       describing the origin, and the second, the target location.
     *   - The distance between the two control points must be equal to
     *         this.value - 1.
     * - A cone requires two control points; the first describes its origin,
     *       while the second describes the center of its furthest edge. As a
     *       result, the cone will point in that direction.
     *   - The distance between the two control points must be equal to
     *         this.value - 1.
     * - A blast requires only one control point to describe its center.
     * - A burst requires two control points; the first describes its center,
     *       while the second describes the size of the entity it surrounds.
     *   - The distance between the two must be equal to a valid size's "radius"
     *         as follows:
     *     - Size of 1/2 or 1 = distance of 1
     *     - Size of 2 = distance of 2/3
     *     - Size of 3 = distance of 2
     *     - Size of 4 = distance of 5/3
     * Can be any Hex[] with the constraints defined above. Cannot be null.
     */
    private Hex[] controlPoints;
    private Hex[] spaces;
    private boolean isSpacesCurrent;

    public Area(String type, int value, Hex... controlPoints) {
        setType(type);
        setValue(value);
        setControlPoints(controlPoints);
    }

    public String getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
    public Hex[] getControlPoints() {
        return controlPoints;
    }
    private void setType(String type) {
        boolean isValid = false;

        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        for (String allowedType : Area.allowedTypes) {
            if (type.equals(allowedType)) {
                isValid = true;
                break;
            }
        }
        if (! isValid) {
            throw new IllegalArgumentException("type value: \"" + type + "\" is"
                + " not a valid value for Area.type");
        }
        this.type = type;
        this.isSpacesCurrent = false;
    }
    public void setValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("value value: " + value + " is <"
                + " 0");
        }
        if (! this.type.equals("blast")) {
            if (value == 0) {
                throw new IllegalArgumentException("value cannot be 0 when"
                    + " Area.type is \"" + this.type + "\"");
            }
        }
        this.value = value;
        this.isSpacesCurrent = false;
    }
    public void setControlPoints(Hex[] controlPoints) {
        int numRequired;

        if (controlPoints == null) {
            throw new IllegalArgumentException("controlPoints is null");
        }
        for (Hex hex : controlPoints) {
            if (hex == null) {
                throw new IllegalArgumentException("controlPoints array"
                    + " contains a null element");
            }
        }
        if (this.type.equals("line")) {
            // Two control points needed to define a line
            numRequired = 2;
        } else if (this.type.equals("cone")) {
            // Two control points needed to define a cone
            numRequired = 2;
        } else if (this.type.equals("blast")) {
            // One control point needed to define a blast
            numRequired = 1;
        } else {
            // Two control points needed to define a burst (one for the center
            //     and one for the size)
            numRequired = 2;
        }
        if (controlPoints.length != numRequired) {
            throw new IllegalArgumentException("controlPoints is of length: "
                + controlPoints.length + " when " + numRequired + " control"
                + " points are required for an Area with an Area.type of \""
                + this.type + "\"");
        }
        if (this.type.equals("line")) {
            if (controlPoints[0].getDist(controlPoints[1]) - 1 != this.value) {
                System.out.println("The distance between the two control points"
                    + " for this Line is: "
                    + controlPoints[0].getDist(controlPoints[1]) + " which is"
                    + " not equal to (its Area.value value - 1) of: "
                    + this.value);
            }
        } else if (this.type.equals("cone")) {
            if (controlPoints[0].getDist(controlPoints[1]) - 1 != this.value) {
                System.out.println("The distance between the two control points"
                    + " for this Cone is: "
                    + controlPoints[0].getDist(controlPoints[1]) + " which is"
                    + " not equal to (its Area.value value - 1) of: "
                    + this.value);
            }
        } else if (this.type.equals("burst")) {
            // TODO: add checking for "burst"
        } // no checking required for an Area.type value of "blast"
        this.controlPoints = controlPoints;
        this.isSpacesCurrent = false;
    }
    private void setSpaces(Hex[] spaces) {
        if (spaces == null) {
            throw new IllegalArgumentException("spaces is null");
        }
        this.isSpacesCurrent = true;
    }

    /**
     * Constructs and returns an array of every space covered by this Area
     *     object.
     * @return a Hex[] containing the Area's spaces.
     */
    public Hex[] getSpaces() {
        Hex[] output = null;
        int iVal = 0;
        int jVal = 0;
        int kVal;
        boolean isAxial = false;
        int[] dirArray;
        int hexIndex;

        if (this.isSpacesCurrent) {
            return this.spaces;
        }
        if (this.type.equals("line")
            || this.type.equals("cone")) {
            iVal = this.controlPoints[1].getI() - this.controlPoints[0].getI();
            jVal = this.controlPoints[1].getJ() - this.controlPoints[0].getJ();
            kVal = this.controlPoints[1].getK() - this.controlPoints[0].getK();
            isAxial = iVal == 0 || jVal == 0 || kVal == 0;
            if (isAxial) {
                // if any one axis is 0 that means that controlPoints[0] to
                //     controlPoints[1] forms a line that is perpendicular to
                //     one of a hexagon's 6 sides
                // this makes the resulting area easier to calculate
                iVal /= this.value;
                jVal /= this.value;
            }
        }
        if (this.type.equals("line")) {
            if (isAxial) {
                output = new Hex[this.value];
                output[0] = new Hex(this.controlPoints[0]);
                for (int i = 1; i < this.value; i++) {
                    output[i] = new Hex(output[i - 1]);
                    output[i].move(iVal, jVal);
                }
            } else {
                // controlPoints[1] is NOT on an axial line from
                //     controlPoints[0]. yikes. things are about to get hairy
                // TODO: add
                throw new IllegalArgumentException("haven't programmed this"
                    + " in yet sorry");
            }
        } else if (this.type.equals("cone")) {
            // there are two types of cone that lie on one of the 6 diagonal
            //     direction axes defined in Hex:
            // - edge-on, where the line passes through an edge of each
            //       hexagonal cell - something like (0, 0, 0) to (1, 1, -2).
            //       See the rightmost cone diagrammed on pg. 65.
            // - corner-on, where the line passes through a corner of each
            //       hexagonal cell - something like (0, 0, 0) to (0, 2, -2).
            //       See the leftmost cone diagrammed on pg. 65.
            // isAxial being true automatically tells us that the cone is
            //     edge-on and lies on an axial line. however, if it is false,
            //     it is still entirely possible to get an easy-to-calculate
            //     cone pattern if the cone is a corner-on cone that lies on one
            //     of the diagonal direction axes with an odd absolute value.
            // we will distinguish these cases first.
            if (isAxial) {
                // edge-on cone that lies axially
            }
            // TODO: fix
        } else if (this.type.equals("blast")) {
            // A hexagonal formula of my own making; h0 is 1, h1 is 7, h2 is 19,
            //     etc.. following the formula h_n = 3n(n - 1) + 1
            hexIndex = (3 * this.value * (this.value + 1)) + 1;
            output = new Hex[hexIndex];
            dirArray = new int[] {5, -1, -3, -5, 1, 3};
            hexIndex = 0;
            for (int i = 0; i < this.value + 1; i++) {
                // actually do the work of the i loop
                output[hexIndex] = new Hex(i, - i);
                // for a ring of size 0 you only need one space
                // so skip doing each side
                if (i == 0) {
                    hexIndex++;
                    continue;
                }
                // otherwise do each side
                for (int j = 0; j < 6; j++) {
                    for (int k = 0; k < i; k++) {
                        // the very first space of the very first side of every
                        //     ring has already been set up by the i loop
                        if (j == 0 && k == 0) {
                            hexIndex++;
                            continue;
                        }
                        output[hexIndex] = new Hex(output[hexIndex - 1]);
                        if (k == 0) {
                            // the hex being created this iteration lies
                            //     perpendicular to one of the center's sides
                            // in other words, this ring forms a giant,
                            //     "flat"-orientated hexagon, and this hex lies
                            //     at one of that hexagon's vertices
                            output[hexIndex].moveDir(dirArray[j - 1]);
                        } else {
                            // the hex being created this iteration lies between
                            //     the axes created by the lines perpendicular
                            //     to the center's sides
                            // in other words, this ring forms a giant,
                            //     "flat"-orientated hexagon, and this hex lies
                            //     along the middle of one of that hexagon's
                            //     sides
                            output[hexIndex].moveDir(dirArray[j]);
                        }
                        hexIndex++;
                    }
                }
            }
        } else { // this.type is "burst"
        }

        setSpaces(output);
        return output;
    }
    // TODO: add methods:
    // - getBorderSpaces() which performs getSpaces() if this.type is "line";
    //       otherwise, generates the array of spaces which have direct contact
    //       with the outside environment. Create an Area.borderSpaces property
    //       to save this result once it's been computed, and use
    //       isSpacesCurrent to track whether it's accurate or needs to be
    //       recomputed when called.
    // - expand(int) and a helper method expand() which increases the Area's
    //       this.value and makes adjustments accordingly.
    // - shrink(int) and a helper method shrink() which decreases the Area's
    //       this.value and makes adjustments accordingly.
    // TODO: create a class EntityArea which extends Area and uses expand() to
    //     store a representation of an entity of any given size
}