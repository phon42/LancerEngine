/**
 * Represents a single talent of the pilot. Stores the talent's
 *     name, as well as the level at which it is held.
 */
public class Talent {
    public String name;
    public int level;

    public Talent() {
        name = "";
        level = 0;
    }
    public Talent(String newName, int newLevel) {
        name = newName;
        level = newLevel;
    }
}
