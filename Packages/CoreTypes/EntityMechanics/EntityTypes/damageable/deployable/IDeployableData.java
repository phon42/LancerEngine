package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable;

import MainBranch.HelperMethods;
import Packages.CoreTypes.Size;

/**
 * See pgs. 58 and 68.
 */
public class IDeployableData {
    // Optional properties
    /**
     * The deployable's size.
     * Can be any Size. Can be null.
     * Use IDeployableData.getSize() to get the raw value and
     *     IDeployableData.outputSize() to obtain it properly formatted.
     */
    private Size size;
    /**
     * The statblock associated with this IDeployableData (example unhelpful).
     * Can be any DeployableStatblock. Can be null.
     */
    private DeployableStatblock statblock;

    public IDeployableData() {
        this(new Size(2), 0);
    }
    public IDeployableData(Size size, int armor) {
        // default values from pg. 68
        // max always before current
        setSize(size);
        setStatblock(new DeployableStatblock(size, armor));
    }

    // Optional properties
    public Size getSize() {
        return size;
    }
    public DeployableStatblock getStatblock() {
        return statblock;
    }
    // Optional properties
    /**
     * Sets this.size to the provided value.
     * @param size a Size which cannot be null.
     * @throws IllegalArgumentException if size is null.
     */
    private void setSize(Size size) {
        this.size = size;
    }
    private void setStatblock(DeployableStatblock statblock) {
        this.statblock = statblock;
    }

    /**
     * A helper method which outputs the deployable's size, formatted properly
     *     so that it is human-readable.
     * @return a String containing the requested output.
     */
    public String outputSize() {
        return size.output();
    }
}
