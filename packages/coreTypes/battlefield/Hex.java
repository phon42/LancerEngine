package packages.coreTypes.battlefield;

/**
 * Hexes in this project are "pointy" as defined by
 *     https://www.redblobgames.com/grids/hexagons/#coordinates-cube; in other
 *     words, they are aligned such that two vertices face along the vertical
 *     axis; two of their sides lie parallel to the vertical axis, as opposed to
 *     the "flat" alignment in which two vertices face the horizontal axis and
 *     two sides lie parallel to the horizontal axis.
 * Uses a combination of the cube/axial coordinate system from
 *     https://www.redblobgames.com/grids/hexagons/#coordinates-cube and the
 *     coordinate system specified on
 *     https://h3geo.org/docs/core-library/coordsystems/, specifically within
 *     this image: https://h3geo.org/images/ijkp.png.
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
        iVal -= this.i;
        jVal -= this.j;
        iVal = Math.abs(iVal);
        jVal = Math.abs(jVal);

        return Math.max(iVal, jVal);
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
        return this.getDist(hex.getI(), hex.getJ());
    }
}
