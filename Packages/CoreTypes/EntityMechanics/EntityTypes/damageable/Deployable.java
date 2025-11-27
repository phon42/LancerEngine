package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.EntityTypes.Damageable;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.IDeployableData;
import Packages.CoreTypes.EntityMechanics.HarmSystem.Harm;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.Damage;
import Packages.CoreTypes.Size;

/**
 * See pgs. 58 and 68.
 */
public class Deployable implements Damageable {
    private IDeployableData data;
    /**
     * The deployable's current HP value.
     * Must be between 0 and this.data.HP (inclusive).
     */
    private int currentHP;

    public Deployable() {
        this(new Size(2), 0);
    }
    public Deployable(Size size, int armor) {
        setData(new IDeployableData(size, armor));
        setCurrentHP(this.data.getStatblock().getHP());
    }
    public Deployable(Deployable deployable) {
        this(deployable.data.getSize(),
            deployable.data.getStatblock().getArmor());
    }

    public IDeployableData getData() {
        return data;
    }
    public int getCurrentHP() {
        return currentHP;
    }
    private void setData(IDeployableData data) {
        HelperMethods.checkObject("data", data);
        this.data = data;
    }
    /**
     * Sets this.currentHP to the provided value.
     * @param currentHP an int which cannot be < 0 or > this.maxHP.
     * @throws IllegalArgumentException if currentHP is < 0 or > this.maxHP.
     */
    public void setCurrentHP(int currentHP) {
        if (currentHP < 0) {
            throw new IllegalArgumentException("New currentHP value: "
                + currentHP + " is < 0");
        }
        if (this.data.getStatblock().getHP() < currentHP) {
            throw new IllegalArgumentException("currentHP value provided: "
                + currentHP + " is > maxHP value: "
                + this.data.getStatblock().getHP());
        }
        this.currentHP = currentHP;
    }

    /**
     * A helper method which outputs the deployable's size, formatted properly
     *     so that it is human-readable.
     * @return a String containing the requested output.
     */
    public String outputSize() {
        return data.outputSize();
    }
    /**
     * Deals harm to this Deployable.
     * @param harm a Harm containing the harm to deal. Must have a Harm.type
     *     value that is not "variable". Must have a Harm.diceValue of something
     *     other than "" OR a Harm.flatValue that is > 0. Cannot be null.
     * @throws IllegalArgumentException if harm is null, harm's Harm.type
     *     property is "variable", or if harm's Harm.diceValue is "" AND harm's
     *     Harm.flatValue is < 1.
     */
    public void receiveHarm(Harm harm) {
        Damage damage;

        HelperMethods.checkObject("harm", harm);
        if (harm.getType().getValue().equals("variable")) {
            throw new IllegalArgumentException("harm value has a Harm.type"
                + " value of \"variable\"");
        }
        if (harm.getDiceValue() == null && harm.getFlatValue().getValue() == 0)
        {
            throw new IllegalArgumentException("harm.diceValue is \"\" and"
                + " harm.flatValue value: " + harm.getFlatValue() + " is < 1");
        }
        damage = harm.toDamage();
        if (harm.getType().getValue().equals("heat")) {
            receiveHeat(damage);
        } else if (harm.getType().getValue().equals("burn")) {
            receiveBurn(damage);
        } else {
            receiveDamage(damage);
        }
    }
    /**
     * Deals damage to this Deployable.
     * @param damage a Damage containing the damage to deal. Cannot be null.
     * @throws IllegalArgumentException if any parameters have invalid values as
     *     detailed above.
     */
    private void receiveDamage(Damage damage) {
        // TODO: fill out with damage mitigation - armor, resistance etc
        int damageAmount;
        int damageToTake;
        int newCurrentHP;

        HelperMethods.checkObject("damage", damage);
        // damage is being rolled here
        damageAmount = damage.roll();
        damageToTake = Math.min(damageAmount, this.currentHP);
        newCurrentHP = this.currentHP - damageToTake;
        setCurrentHP(newCurrentHP);
        // if the amount of damage it's taking is greater than our deployable's
        //     maximum HP, it will be destroyed no matter what its current HP is
        if (damageAmount > this.currentHP) {
            // it's about to be destroyed
            destroy();
        }
    }
    /**
     * Does nothing because Deployables don't receive heat.
     * @param heat a Damage containing the heat to deal which must have a
     *     Damage.type value of "heat". Cannot be null.
     * @throws IllegalArgumentException if any parameters have invalid values as
     *     detailed above.
     */
    private void receiveHeat(Damage heat) {
        // TODO: fill out with mitigation, resistance etc
        int heatAmount;

        HelperMethods.checkObject("heat", heat);
        if (! heat.getType().getValue().equals("heat")) {
            throw new IllegalArgumentException("heat has a Damage.type value"
                + " of: \"" + heat.getType() + "\"");
        }
        // heat is being rolled here
        heatAmount = heat.roll();
        // do nothing, according to Petrichor
    }
    /**
     * Deals burn to this Deployable.
     * @param burn a Damage containing the burn to deal which must have a
     *     Damage.type value of "burn". Cannot be null.
     * @throws IllegalArgumentException if any parameters have invalid values as
     *     detailed above.
     */
    private void receiveBurn(Damage burn) {
        // TODO: fill out with mitigation, resistance etc
        int burnAmount;

        HelperMethods.checkObject("burn", burn);
        if (! burn.getType().getValue().equals("burn")) {
            throw new IllegalArgumentException("burn has a Damage.type value"
                + " of: \"" + burn.getType() + "\"");
        }
        // burn is being rolled here
        burnAmount = burn.roll();
        // TODO: fill out - Deployables do in fact receive burn
    }
    public void destroy() {
        System.out.println("This Deployable has been destroyed");
    }
}
