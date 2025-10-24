package packages.coreTypes.battlefield;

/**
 * See pg. 65.
 */
/**
 * Hexes in this project are "pointy" as defined by
 *     https://www.redblobgames.com/grids/hexagons/#coordinates-cube; in other
 *     words, they are aligned such that two vertices face along the vertical
 *     axis; two of their sides lie parallel to the vertical axis, as opposed to
 *     the "flat" alignment in which two vertices face the horizontal axis and
 *     two sides lie parallel to the horizontal axis.
 * This decision was made based on pg. 65, in which a battlefield area is
 *     depicted using hexes that are in the "pointy" orientation.
 * 
 * This class uses a combination of the cube/axial coordinate system from
 *     https://www.redblobgames.com/grids/hexagons/#coordinates-cube and the
 *     coordinate system specified on
 *     https://h3geo.org/docs/core-library/coordsystems/, specifically within
 *     this image: https://h3geo.org/images/ijkp.png.
 * 
 * The two coordinates stored by the Hex, i and j, respectively represent the q
 *     and r coordinates defined on
 *     https://www.redblobgames.com/grids/hexagons/#coordinates-cube. Therefore,
 *     i and j can each be any int, and i + j + k = 0 must always be true. As a
 *     result, the k coordinate can be calculated at any time given some simple
 *     algebra:
 *         (1)  i      +  j      + k         = 0
 *         (2)  i      +  j      + k - i - j = 0 - i - j
 *         (3) (i - i) + (j - j) + k         =   - i - j
 *         (4) (0)     + (0)     + k         =   - i - j
 *         (5)                     k         =   - i - j
 *     Thus, we find:
 *         (6) k = - i - j
 * =============================================================================
 * The following diagram illustrates how each coordinate determines a cell's
 *     position:
 *                                       * Point A
 *                                  ---/   \---
 *                              --/             \--
 *                         ---/                     \---
 *                    ---/                               \---
 *               ---/                                         \---
 *           --/                                                   \--
 *      ---/                                                           \---
 * ---/                                                                     \---
 * * Point F                                                           Point B *
 * |                                                                           |
 * |                                                                           |
 * |                                                                           |
 * |                                                                           |
 * |                                                                           |
 * |                                                                           |
 * |                                       The point that would                |
 * |                                     * theoretically be midway             |
 * |                                     * between these two                   |
 * |                                       asterisks is Point G                |
 * |                                                                           |
 * |                                                                           |
 * |                                                                           |
 * |                                                                           |
 * |                                                                           |
 * |                                                                           |
 * * Point E                                                           Point C *
 * ---\                                                                     /---
 *      ---\                                                           /---
 *           --\                                                   /--
 *               ---\                                         /---
 *                    ---\                               /---
 *                         ---\                     /---
 *                              --\             /--
 *                                  ---\   /---
 *                                       * Point D
 *         Figure 1. A hexagon with its vertices labeled alphabetically.
 * =============================================================================
 * The i coordinate stores a cell's bottom-left-to-top-right position; it
 *     corresponds to the q coordinate defined on
 *     https://www.redblobgames.com/grids/hexagons/#coordinates-cube. A positive
 *     i coordinate indicates that a cell with that position lies in the
 *     direction, on Figure 1, specified by a ray GB drawn from G to B. A
 *     negative i coordinate indicates the opposite; from G, in the direction of
 *     E.
 * The j coordinate stores a cell's vertical position; it corresponds to the r
 *     coordinate defined on
 *     https://www.redblobgames.com/grids/hexagons/#coordinates-cube. A positive
 *     j coordinate indicates that a cell with that position lies in the
 *     direction, on Figure 1, specified by a ray GD drawn from G to D. A
 *     negative j coordinate indicates the opposite; from G, in the direction of
 *     A.
 * The k coordinate is not stored but can be calculated, and represents a cell's
 *     bottom-right-to-top-left position; it corresponds to the s coordinate
 *     defined on https://www.redblobgames.com/grids/hexagons/#coordinates-cube.
 *     A positive k coordinate indicates that a cell with that position lies in
 *     the direction, on Figure 1, specified by a ray GF drawn from G to F. A
 *     negative i coordinate indicates the opposite; from G, in the direction of
 *     C.
 * =============================================================================
 * Occasionally (for example, in Hex.move(int, int, int)), a three-coordinate
 *     system will be used. In these situations, i refers to the i coordinate
 *     defined within https://h3geo.org/images/ijkp.png instead of the q
 *     coordinate defined on
 *     https://www.redblobgames.com/grids/hexagons/#coordinates-cube. Similarly,
 *     j and k refer to the j and k coordinates defined within the same image.
 *     In other words, i refers to the cell's distance on Figure 1 from G
 *     towards the line BC, j refers to the distance from G towards the line AF,
 *     and k refers to the distance from G towards the line CD. In these
 *     situations, similar methods (such as Hex.move(int, int)) will use q, r,
 *     and s as parameter names instead of i, j, and k to avoid confusion. All
 *     computation will be done in the q, r, s or cube/axial system as much as
 *     possible.
 * =============================================================================
 * It is possible to calculate a "direction" for a Hex's position based on two
 *     different methods, detailed at
 *     https://www.redblobgames.com/grids/hexagons/directions.html.
 * 
 * The second of these methods calculates the Hex's "diagonal direction"; in
 *     other words, the axis along which the Hex's position lies with respect to
 *     the origin.
 * 
 * The absolute value of the direction varies with the relative values of |i|,
 *     |j|, and |k|.
 * - For a |direction| of 2, in which |i| is the maximum of the three, the Hex's
 *       position lies directly along the i axis.
 * - For a |direction| of 4, in which |j| is the maximum of the three, the Hex's
 *       position lies directly along the j axis.
 * - For a |direction| of 6, in which |k| is the maximum of the three, the Hex's
 *       position lies directly along the k axis.
 * The above values are used when the Hex's coordinates consist of one value
 *     whose absolute value is unequivocally larger than those of the others,
 *     and two values that are exactly the same.
 * - More specifically, the sign of the direction value is dependant on the sign
 *       of the coordinate involved. For i and j, the sign of the direction
 *       value is the same as the sign of the coordinate value. For example, a
 *       positive i value leads to a direction value of +2, while a negative i
 *       value leads to a direction value of -2. However, for k (in other words,
 *       for directions whose absolute value is 6), the sign of the direction
 *       value is the opposite of the sign of the coordinate value.
 * 
 * However, it is also possible for two coordinate values to tie for the spot of
 *     maximum, or for all three coordinates to have completely different
 *     absolute values. This is true whenever the Hex lies between two axes
 *     instead of directly along one. We will denote this situation by using a
 *     value that is halfway between the two directions as follows:
 * - Between diagonal directions 2 and 4, direction is 3, adopting the sign
 *       that is common to the two direction values. In this case, the Hex lies
 *       between the i and k axes; whether in the positive or negative direction
 *       of the i axis is communicated by the sign of the direction value.
 * - Between diagonal directions 4 and 6, direction is 5, adopting the sign that
 *       is common to the two direction values. In this case, the Hex lies
 *       between the j and k axes; whether in the positive or negative direction
 *       of the j axis is communicated by the sign of the direction value.
 * - Between diagonal directions 6 and 2, direction is 1, adopting the sign of
 *       the direction with an absolute value of 2. In this case, the Hex lies
 *       between the i and j axes; whether in the positive or negative direction
 *       of the i axis is communicated by the sign of the direction value.
 * 
 * Finally, all three values are equal if the Hex's coordinates are (0, 0, 0);
 *     in other words, at the origin. This will be represented by a diagonal
 *     direction value of 0.
 * 
 * This diagonal direction value is calculated by the method Hex.getDirection().
 */
/**
 * Represents a single hex cell on a grid. Contains coordinates specifying a
 *     canonical position for that cell.
 * 
 * Requires an i and j coordinate to be instantiated.
 * 
 * Used in myriad other classes.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. None of its properties have allowed values of null.
 */
public class Hex {
    private int i;
    private int j;
    // k is not stored but can be calculated from the other two

    /**
     * Creates a Hex from the provided i and j positions.
     * @param i an int which can be any int.
     * @param j an int which can be any int.
     */
    public Hex(int i, int j) {
        setI(i);
        setJ(j);
    }
    /**
     * Creates a deep copy of the provided Hex.
     * @param hex a Hex to be copied.
     */
    public Hex(Hex hex) {
        setI(hex.i);
        setJ(hex.j);
    }

    public int getI() {
        return i;
    }
    public int getJ() {
        return j;
    }
    /**
     * Calculates k from i and j. Given that i + j + k = 0 must always be true,
     *     it follows that k = - i - j.
     * @return an int containing the value of k.
     */
    public int getK() {
        return - this.i - this.j;
    }
    private void setI(int i) {
        this.i = i;
    }
    private void setJ(int j) {
        this.j = j;
    }

    /**
     * Generates a String output representing this Hex object, consisting of its
     *     i and j coordinate values.
     * @return a String containing a representation of this Hex object.
     */
    @Override
    public String toString() {
        return String.format("(%d, %d)", this.i, this.j);
    }
    /**
     * Generates a String output representing this Hex object, consisting of its
     *     i, j, and optionally, k, coordinate values.
     * @param includeDetail a boolean representing whether or not to include the
     *     k coordinate in the output.
     * @return a String containing a representation of this Hex object.
     */
    public String toString(boolean includeDetail) {
        if (includeDetail) {
            return String.format("(%d, %d, %d)", this.i, this.j, getK());
        }
        return toString();
    }
    /**
     * Moves this Hex some number of spaces dQ along the q or i axis, and some
     *     number of spaces dR along the r or j axis.
     * @param dQ an int which can be any int.
     * @param dR an int which can be any int.
     */
    public void move(int dQ, int dR) {
        setI(this.i + dQ);
        setJ(this.j + dR);
    }
    /**
     * Moves this Hex some number of spaces in the 3-COORDINATE SPACE defined
     *     within https://h3geo.org/images/ijkp.png. dI, dJ, and dK are not
     *     cube/axial coordinates; they represent a different coordinate system
     *     here. The operation is equivalent to moving (dI - dK) spaces along
     *     the q axis and (dK - dJ) spaces along the r axis.
     * @param dI an int which can be any int.
     * @param dJ an int which can be any int.
     * @param dK an int which can be any int.
     */
    public void move(int dI, int dJ, int dK) {
        move(dI - dK, dK - dJ);
    }
    /**
     * Moves this Hex (distance) spaces in the provided diagonal direction.
     * @param direction an int which must be between -6 and +6 (inclusive).
     * @param distance an int which must be >= 0.
     * @throws IllegalArgumentException if direction is not between -6 and +6
     *     (inclusive), or if distance is < 0.
     */
    public void moveDir(int direction, int distance) {
        int newI;
        int newJ;
        int absD;

        if (direction < -6) {
            throw new IllegalArgumentException("direction value: " + direction
                + " is < -6");
        }
        if (direction > 6) {
            throw new IllegalArgumentException("direction value: " + direction
                + " is > 6");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("distance value: " + distance
                + " is < 0");
        }
        if (direction == 0 || distance == 0) {
            return;
        }
        absD = Math.abs(direction);
        if (absD == 1) {
            newI = 1;
            newJ = -1;
        } else if (absD == 2) {
            newI = 2;
            newJ = -1;
        } else if (absD == 3) {
            newI = 1;
            newJ = 0;
        } else if (absD == 4) {
            newI = 1;
            newJ = 1;
        } else if (absD == 5) {
            newI = 0;
            newJ = 1;
        } else {
            newI = -1;
            newJ = 2;
        }
        if (direction < 0) {
            newI *= -1;
            newJ *= -1;
        }
        move(newI, newJ);

    }
    /**
     * Helper method for Hex.moveDir(int, int). Allows it to be called with a
     *     default value of 1 for the second int.
     * @param direction an int which must be between -6 and +6 (inclusive).
     */
    public void moveDir(int direction) {
        moveDir(direction, 1);
    }
    /**
     * Calculates the distance between two Hexes given the coordinates that
     *     define them.
     * @param i1 the i coordinate of the first Hex.
     * @param j1 the j coordinate of the first Hex.
     * @param i2 the i coordinate of the second Hex.
     * @param j2 the j coordinate of the second Hex.
     * @return an int containing the distance between the two provided Hexes.
     */
    public static int getDist(int i1, int j1, int i2, int j2) {
        return new Hex(i1, j1).getDist(i2, j2);
    }
    /**
     * Helper method for Hex.getDist(int, int, int, int), allowing it to be
     *     called with two Hex objects instead of their four int coordinate
     *     values.
     * @param hex1 a Hex which can be any Hex. Cannot be null.
     * @param hex2 a Hex which can be any Hex. Cannot be null.
     * @return an int containing the distance between the two provided Hexes.
     * @throws IllegalArgumentException if hex1 or hex2 is null.
     */
    public static int getDist(Hex hex1, Hex hex2) {
        if (hex1 == null) {
            throw new IllegalArgumentException("hex1 is null");
        }
        if (hex2 == null) {
            throw new IllegalArgumentException("hex2 is null");
        }

        return hex1.getDist(hex2);
    }
    /**
     * Calculates the distance between a new Hex and this Hex given the
     *     coordinates that define it.
     * @param iVal the i coordinate of the new Hex.
     * @param jVal the j coordinate of the new Hex.
     * @return an int containing the distance between this Hex and the provided
     *     Hex.
     */
    public int getDist(int iVal, int jVal) {
        return new Hex(iVal - this.i,  jVal - this.j).getDist();
    }
    /**
     * Helper method for Hex.getDist(int, int), allowing it to be called with a
     *     Hex object instead of its two int coordinate values.
     * @param hex a Hex which can be any Hex. Cannot be null.
     * @return an int containing the distance between this Hex and the provided
     *     Hex.
     * @throws IllegalArgumentException if hex is null.
     */
    public int getDist(Hex hex) {
        if (hex == null) {
            throw new IllegalArgumentException("hex is null");
        }

        return new Hex(hex.getI() - this.i, hex.getJ() - this.j).getDist();
    }
    /**
     * Calculates the distance between this Hex and the origin.
     * @return an int containing the distance between this Hex and the origin.
     */
    public int getDist() {
        return Math.max(this.i, this.j);
    }
    /**
     * Returns the "diagonal direction" the Hex lies in, represented as an int
     *     from -6 to 6 (inclusive). See the header documentation for Hex for
     *     more information.
     * @return an int from -6 to 6 (inclusive) containing the diagonal direction
     *     of this Hex.
     */
    public int getDirection() {
        int aI;
        int aJ;
        int aK;
        int result;

        if (this.i == 0 && this.j == 0) {
            // only happens in one place - at the origin
            return 0;
        }
        aI = Math.abs(this.i);
        aJ = Math.abs(this.j);
        aK = Math.abs(this.getK());
        // Possible values for |direction| and their meanings:
        // 1: i > j > k OR j > i > k OR i = j > k
        // 2: i > j = k
        // 3: i > k > j OR k > i > j OR i = k > j
        // 4: k > i = j
        // 5: j > k > i OR k > j > i OR j = k > i
        // 6: j > i = k
        if (aI > aJ) {
            // |direction| is 1, 2, or 3
            if (aJ > aK) {
                result = 1;
            } else if (aK > aJ) {
                result = 3;
            } else {
                result = 2;
            }
        } else if (aJ > aI) {
            // |direction| is 1, 5, or 6
            if (aI > aK) {
                result = 1;
            } else if (aK > aI) {
                result = 5;
            } else {
                result = 6;
            }
        } else if (aK > aI) {
            // |direction| is 4 or 5
            if (aI == aJ) {
                result = 4;
            } else {
                result = 5;
            }
        } else if (aK > aJ) {
            // |direction| is 3
            result = 3;
        } else {
            // |direction| is 1
            result = 1;
        }
        if (result < 4) {
            // |direction| is 1, 2, or 3
            result *= this.i > 0 ? 1 : -1;
        } else if (result < 5) {
            // |direction| is 4
            result *= this.getK() > 0 ? -1 : 1;
        } else {
            // |direction| is 5 or 6
            result *= this.j > 0 ? 1 : -1;
        }

        return result;
    }
}
