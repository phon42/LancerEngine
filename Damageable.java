/**
 * Represents anything that can receive damage, such as a pilot, mech,
 *     deployable, drone, or piece of terrain. Contains a list of methods such
 *     things must have in order to interact with the world and, more
 *     importantly, be interacted with.
 * 
 * Cannot be instantiated by virtue of being an interface.
 * 
 * Implemented by Deployable, Mech, Pilot, and TerrainUnit.
 * 
 * Safety: N/A because this class cannot be instantiated.
 */
public interface Damageable {
    // TODO: add a receiveAttack() method
    public void receiveHarm(int damageAmount, String damageType);
    public void destroy();
}
