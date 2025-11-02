package Packages.CoreTypes.Battlefield;

import MainBranch.HelperMethods;

public class BaseArea {
    /**
     * This BaseArea's type (i.e. "line").
     * Must be a valid type as defined by BaseArea.allowedTypes. Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    protected String type;
    /**
     * Contains an array of allowed values for BaseArea.type.
     * Case-insensitive and stored in lowercase.
     */
    protected static final String[] allowedTypes = {"line", "cone", "blast",
        "burst", "size"};
    /**
     * The value (depending on the value of this.type, defines size in a varying
     *     way) of this BaseArea.
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
     * - For entity areas, defines the size of the entity it represents.
     *   - Must be a minimum of 1.
     * Must be a valid value as defined above.
     */
    protected int value;
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
     * - An entity area requires only one control point to describe its center.
     * Can be any Hex[] with the constraints defined above. Cannot be null.
     */
    protected Hex[] controlPoints;
    protected Hex[] spaces;
    protected boolean isSpacesCurrent;

    protected BaseArea() {}

    public String getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
    public Hex[] getControlPoints() {
        return controlPoints;
    }
    protected void setType(String type) {
        HelperMethods.checkString("type", type);
        type = type.toLowerCase();
        for (String allowedType : BaseArea.allowedTypes) {
            if (type.equals(allowedType)) {
                this.type = type;
                this.isSpacesCurrent = false;
                return;
            }
        }
        throw new IllegalArgumentException("type value: \"" + type + "\" is"
            + " not a valid value for BaseArea.type");
    }
    protected void setValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("value value: " + value + " is <"
                + " 0");
        }
        if (! this.type.equals("blast")) {
            if (value == 0) {
                throw new IllegalArgumentException("value cannot be 0 when"
                    + " BaseArea.type is \"" + this.type + "\"");
            }
        }
        this.value = value;
        this.isSpacesCurrent = false;
    }
    public void setControlPoints(Hex[] controlPoints) {
        int numRequired;

        HelperMethods.checkObjectArray("controlPoints",
            controlPoints);
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
                + " points are required for a BaseArea with a BaseArea.type of"
                + " \"" + this.type + "\"");
        }
        if (this.type.equals("line")) {
            if (controlPoints[0].getDist(controlPoints[1]) - 1 != this.value) {
                System.out.println("The distance between the two control points"
                    + " for this Line is: "
                    + controlPoints[0].getDist(controlPoints[1]) + " which is"
                    + " not equal to (its BaseArea.value value - 1) of: "
                    + this.value);
            }
        } else if (this.type.equals("cone")) {
            if (controlPoints[0].getDist(controlPoints[1]) - 1 != this.value) {
                System.out.println("The distance between the two control points"
                    + " for this Cone is: "
                    + controlPoints[0].getDist(controlPoints[1]) + " which is"
                    + " not equal to (its BaseArea.value value - 1) of: "
                    + this.value);
            }
        } else if (this.type.equals("burst")) {
            // TODO: add checking for "burst"
        } // no checking required for a BaseArea.type value of "blast"
        this.controlPoints = controlPoints;
        this.isSpacesCurrent = false;
    }
    protected void setSpaces(Hex[] spaces) {
        HelperMethods.checkObject("spaces", spaces);
        this.isSpacesCurrent = true;
    }

    /**
     * Generates a String representation of this BaseArea.
     * @return a String containing a representation of this BaseArea.
     */
    @Override
    public String toString() {
        // Generate something of the form "Line 3\n"
        //                              + "Control Points:\n"
        //                              + "  (0, 0), (0, 3)"
        String output;

        // "Line 3"
        output = outputType();
        output += "\nControl Points:\n  ";
        // "(0, 0), (0, 3)"
        for (int i = 0; i < this.controlPoints.length; i++) {
            output += this.controlPoints[i].toString();
            if (i < this.controlPoints.length - 1) {
                output += ", ";
            }
        }

        return output;
    }
    /**
     * Generates a String representation of this BaseArea at varying detail
     *     levels.
     * @param detailLevel an int which must be between 0 and 2 (inclusive).
     *     Controls the level of detail of the generated String.
     * @return a String containing a representation of this BaseArea at varying
     *     detail levels.
     * @throws IllegalArgumentException if detailLevel is not between 0 and 2
     *     (inclusive).
     */
    public String toString(int detailLevel) {
        String output;

        if (detailLevel < 0) {
            throw new IllegalArgumentException("detailLevel value: "
                + detailLevel + " is < 0");
        }
        if (detailLevel > 2) {
            throw new IllegalArgumentException("detailLevel value: "
                + detailLevel + " is > 2");
        }
        if (detailLevel == 0) {
            return outputType();
        } else if (detailLevel == 1) {
            return toString();
        } else {
            output = toString();
            output += "\nSpaces Covered:\n  ";
            // "(0, 0), (0, 1), (0, 2), (0, 3)"
            if (! this.isSpacesCurrent) {
                // if this.spaces isn't current, update it
                getSpaces();
            }
            for (int i = 0; i < this.spaces.length; i++) {
                output += this.spaces[i].toString();
                if (i < this.spaces.length - 1) {
                    output += ", ";
                }
            }

            return output;
        }
    }
    /**
     * Constructs and returns an array of every space covered by this BaseArea
     *     object.
     * @return a Hex[] containing the BaseArea's spaces.
     */
    public Hex[] getSpaces() {
        Hex[] output = null;
        int iVal = 0;
        int jVal = 0;
        int kVal;
        // For Lines and Cones, represents whether the two control points lie in
        //     a manner such that a line can be drawn between them that is
        //     perpendicular to one of a "pointy"-orientated hexagon's six
        //     sides.
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
                //     one of a "pointy"-orientated hexagon's 6 sides
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
                // TODO: add calculation for weird Line patterns
                throw new IllegalArgumentException("haven't programmed this"
                    + " in yet sorry");
            }
        } else if (this.type.equals("cone")) {
            // There are two types of cone that lie on one of the 6 diagonal
            //     direction axes defined in Hex:
            // - edge-on, where the line drawn between the cone's control points
            //       passes through an edge of each hexagonal cell - something
            //       like (0, 0, 0) to (0, 2, -2). See the leftmost cone
            //       diagrammed on pg. 65.
            // - corner-on, where the line drawn between the cone's control
            //       points passes through a vertex of each hexagonal cell -
            //       something like (0, 0, 0) to (1, 1, -2). See the rightmost
            //       cone diagrammed on pg. 65.
            // However, cones exist whose line drawn between control points does
            //     NOT lie directly along any of the 6 diagonal axes. For
            //     example, a cone from (0, 0, 0) to (1, 2, -3). In this case,
            //     it is much more difficult to calculate the spaces occupied.
            // isAxial being true automatically tells us that the cone is
            //     edge-on. however, if it is false, it is still entirely
            //     possible to get an easy-to-calculate cone pattern if the cone
            //     is a corner-on cone, and therefore lies on one of the
            //     diagonal direction axes with an odd absolute value.
            // We will distinguish these cases first.
            // See https://www.reddit.com/r/LancerRPG/comments/1cci18l/comment/l15kdil/,
            //     https://www.reddit.com/r/LancerRPG/comments/1cci18l/comment/l15qoa2/,
            //     and https://www.reddit.com/r/LancerRPG/comments/15vtvrp/lancer_area_of_effect_tokens_for_vtt_files_in/
            if (isAxial) {
                // edge-on cone
                throw new IllegalArgumentException("haven't programmed this"
                    + " in yet sorry");
            } else {
                // could be a corner-on cone or a regular old cone
                hexIndex = new Hex(this.controlPoints[1].getI()
                    - this.controlPoints[0].getI(), this.controlPoints[1].getJ()
                    - this.controlPoints[0].getJ()).getDirection();
                if (hexIndex % 2 == 0) {
                    // this is a corner-on cone
                    throw new IllegalArgumentException("haven't programmed this"
                        + " in yet sorry");
                } else {
                    // this is just a regular old cone. much more difficult to
                    //     calculate
                    throw new IllegalArgumentException("haven't programmed this"
                        + " in yet sorry");
                }
            }
        } else if (this.type.equals("blast")) {
            // A hexagonal formula of my own making; h0 is 1, h1 is 7, h2 is 19,
            //     etc.. following the formula h_n = 3n(n - 1) + 1
            hexIndex = (3 * this.value * (this.value + 1)) + 1;
            output = new Hex[hexIndex];
            dirArray = new int[] {5, -1, -3, -5, 1, 3};
            hexIndex = 0;
            // we will iteratively create larger and larger rings around the
            //     center which we then add to the array
            for (int i = 0; i < this.value + 1; i++) {
                // actually do the work of the i loop
                output[hexIndex] = new Hex(this.controlPoints[0].getI() + i,
                    this.controlPoints[0].getJ() - i);
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
                            //     perpendicular to one of the
                            //     ("pointy"-orientated) center's sides
                            // in other words, this ring forms a giant,
                            //     "flat"-orientated hexagon, and this hex lies
                            //     at one of that hexagon's vertices
                            output[hexIndex].moveDir(dirArray[j - 1]);
                        } else {
                            // the hex being created this iteration lies between
                            //     the axes created by the lines perpendicular
                            //     to the ("pointy"-orientated) center's sides
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
            throw new IllegalArgumentException("haven't programmed this in"
                + " yet sorry");
        }

        setSpaces(output);
        return output;
    }
    // TODO: add the following methods:
    // - getBorderSpaces() which performs getSpaces() if this.type is "line";
    //       otherwise, generates the array of spaces which have direct contact
    //       with the outside environment. Create a BaseArea.borderSpaces
    //       property to save this result once it's been computed, and use
    //       isSpacesCurrent to track whether it's accurate or needs to be
    //       recomputed when called.
    // - expand(int) and a helper method expand() which increases the BaseArea's
    //       this.value and makes adjustments accordingly.
    // - shrink(int) and a helper method shrink() which decreases the BaseArea's
    //       this.value and makes adjustments accordingly.
    /**
     * Generates a very simple String representation of this BaseArea.
     * @return a String containing a very simple representation of this
     *     BaseArea.
     */
    public String outputType() {
        // Generate something of the form "Blast 1"
        return HelperMethods.capitalizeFirst(this.type) + " " + this.value;
    }
}
