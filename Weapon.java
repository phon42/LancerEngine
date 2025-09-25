/**
 * Represents a single weapon to be mounted on a mount.
 */
public class Weapon {
    // TODO: fill out
    private String weaponName;

    public String getWeaponName() {
        return weaponName;
    }
    public void setWeaponName(String weaponName) {
        if (weaponName == null) {
            throw new IllegalArgumentException("New weapon name is null");
        }
        this.weaponName = weaponName;
    }
}
