package Packages.CoreTypes.EntityMechanics.NPCs.Verified.verifiedNPCBase;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.RangeType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.TagSystem.unverifiedDataTag.dataTag.ITagData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.equipment.systemBase.systemType.WeaponType;
import Packages.CoreTypes.EntityMechanics.HarmSystem.Harm;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.HarmType;
import Packages.CoreTypes.EntityMechanics.NPCs.Verified.VerifiedNPCBase;
import Packages.CoreTypes.EntityMechanics.NPCs.Verified.verifiedNPCBase.npcFeature.NPCOrigin;
import Packages.CoreTypes.EntityMechanics.NPCs.Verified.verifiedNPCBase.npcFeature.NPCSystemType;

/**
 * Represents a single NPC feature. Contains information about the feature's
 *     origin, whether it is locked, its type, effect, and several optional
 *     properties.
 * 
 * Requires an NPC feature id, name, origin, locked boolean, type, and effect to
 *     be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: At least one of this class' properties has an allowed value of null.
 */
public class NPCFeature extends VerifiedNPCBase {
    // TODO: fill out
    // Required properties
    /**
     * Can be any NPCOrigin. Cannot be null.
     */
    private NPCOrigin origin;
    /**
     * Can be any NPCSystemType. Cannot be null.
     */
    private NPCSystemType type;
    /**
     * Can be any String. Cannot be null.
     */
    private String effect;
    private boolean locked;

    // Conditionally required properties
    /**
     * Required when this.type is a NPCSystemType representing "Weapon".
     * When required:
     *     Can be any WeaponType. Cannot be null.
     * When not required:
     *     Must be null.
     * Can be any WeaponType. Can be null.
     */
    private WeaponType weaponType;
    /**
     * Required when this.type is a NPCSystemType representing "Weapon".
     * When required:
     *     Can be any Harm[] that is of length 3 and does not contain null
     *         elements. Cannot be null.
     *     All three Harm elements must be of the same HarmType.
     * When not required:
     *     Must be null.
     * Can be any Harm[] that is of length 3 and does not contain null elements.
     *     Can be null.
     * All three Harm elements must be of the same HarmType.
     */
    private Harm[] damage;
    /**
     * Required when this.type is a NPCSystemType representing "Weapon".
     * When required:
     *     Can be any RangeTag[] that is of length 3 and does not contain null
     *         elements. Cannot be null.
     *     All three RangeTag elements must be of the same RangeType.
     * When not required:
     *     Must be null.
     * Can be any RangeTag[] that is of length 3 and does not contain null
     *     elements. Can be null.
     * All three RangeTag elements must be of the same RangeType.
     */
    private RangeTag[] range;
    /**
     * Required when this.type is a NPCSystemType representing "Weapon".
     * When required:
     *     Can be any String. Cannot be null.
     * When not required:
     *     Must be null.
     * Can be any String. Can be null.
     */
    private String onHit;

    // Conditionally semi-required properties
    /**
     * Semi-required when this.type is a NPCSystemType representing "Weapon".
     * When semi-required:
     *     Can be any int[] that is of length 3. Cannot be null.
     *     Has a default value of new int[3].
     * When not semi-required:
     *     Must be null.
     * Can be any int[] that is of length 3. Can be null.
     */
    private int[] attackBonus;
    private static final int[] attackBonusDefault = new int[3];
    /**
     * Semi-required when this.type is a NPCSystemType representing "Weapon".
     * When semi-required:
     *     Can be any int[] that is of length 3. Cannot be null.
     *     Has a default value of new int[3].
     * When not semi-required:
     *     Must be null.
     * Can be any int[] that is of length 3. Can be null.
     */
    private int[] accuracy;
    private static final int[] accuracyDefault = new int[3];

    // Optional properties
    /**
     * Can be any Bonus. Can be null.
     */
    private Bonus bonus;
    /**
     * Can be any ITagData[] that is not of length 0 and does not contain null
     *     elements. Can be null.
     * 
     * If passed an ITagData[] of length 0, sets this.tags to null.
     */
    private ITagData[] tags;

    public NPCFeature(
        // NCPBase properties
        String id, String name,
        // Required properties
        NPCOrigin origin, NPCSystemType type, String effect, boolean locked,
        // Conditionally required properties
        WeaponType weaponType, Harm[] damage, RangeTag[] range, String onHit,
        // Conditionally semi-required properties
        int[] attackBonus, int[] accuracy,
        // Optional properties
        Bonus bonus, ITagData[] tags
    ) {
        HelperMethods.verifyConstructor();
        // NCPBase properties
        super(id, name);
        // Required properties
        setOrigin(origin);
        setType(type);
        setEffect(effect);
        setLocked(locked);
        // Conditionally required properties
        setWeaponType(weaponType);
        setDamage(damage);
        setRange(range);
        setOnHit(onHit);
        // Conditionally semi-required properties
        setAttackBonus(attackBonus);
        setAccuracy(accuracy);
        // Optional properties
        setBonus(bonus);
        setTags(tags);
    }
    public NPCFeature(
        // NCPBase properties
        String id, String name,
        // Required properties
        NPCOrigin origin, NPCSystemType type, String effect, boolean locked,
        // Conditionally required properties
        WeaponType weaponType, Harm[] damage, RangeTag[] range, String onHit,
        // Conditionally semi-required properties
        int[] attackBonus, int[] accuracy
    ) {
        this(id, name, origin, type, effect, locked, weaponType, damage, range,
            onHit, attackBonus, accuracy, null, null);
    }
    public NPCFeature(
        // NCPBase properties
        String id, String name,
        // Required properties
        NPCOrigin origin, NPCSystemType type, String effect, boolean locked,
        // Conditionally required properties
        WeaponType weaponType, Harm[] damage, RangeTag[] range, String onHit
    ) {
        this(id, name, origin, type, effect, locked, weaponType, damage, range,
            onHit, null, null, null, null);
    }
    public NPCFeature(
        // NCPBase properties
        String id, String name,
        // Required properties
        NPCOrigin origin, NPCSystemType type, String effect, boolean locked
    ) {
        this(id, name, origin, type, effect, locked, null,
            null, null, null, null,
            null, null, null);
    }

    // Required properties
    public NPCOrigin getOrigin() {
        return origin;
    }
    public NPCSystemType getType() {
        return type;
    }
    public String getEffect() {
        return effect;
    }
    public boolean isLocked() {
        return locked;
    }
    // Conditionally required properties
    public WeaponType getWeaponType() {
        return weaponType;
    }
    public Harm[] getDamage() {
        if (damage != null) {
            return HelperMethods.copyOf(damage);
        }

        return damage;
    }
    public RangeTag[] getRange() {
        if (range != null) {
            return HelperMethods.copyOf(range);
        }

        return range;
    }
    public String getOnHit() {
        return onHit;
    }
    // Conditionally semi-required properties
    public int[] getAttackBonus() {
        if (attackBonus != null) {
            return HelperMethods.copyOf(attackBonus);
        }

        return attackBonus;
    }
    public int[] getAccuracy() {
        if (accuracy != null) {
            return HelperMethods.copyOf(accuracy);
        }

        return accuracy;
    }
    // Optional properties
    public Bonus getBonus() {
        return bonus;
    }
    public ITagData[] getTags() {
        if (tags != null) {
            return HelperMethods.copyOf(tags);
        }

        return tags;
    }
    // Required properties
    private void setOrigin(NPCOrigin origin) {
        HelperMethods.checkObject("origin", origin);
        this.origin = origin;
    }
    private void setType(NPCSystemType type) {
        HelperMethods.checkObject("type", type);
        this.type = type;
    }
    private void setEffect(String effect) {
        HelperMethods.checkObject("effect", effect);
        this.effect = effect;
    }
    private void setLocked(boolean locked) {
        this.locked = locked;
    }
    // Conditionally required properties
    private void setWeaponType(WeaponType weaponType) {
        if (isWeapon()) {
            HelperMethods.checkObject("weaponType", weaponType);
        } else {
            if (weaponType != null) {
                System.out.println("weaponType is not null");
            }
        }
        this.weaponType = weaponType;
    }
    private void setDamage(Harm[] damage) {
        HarmType type;

        if (isWeapon()) {
            HelperMethods.checkObjectArray("damage", damage);
            if (damage.length != 3) {
                throw new IllegalArgumentException("damage array is of"
                    + " length: " + damage.length + " which is not 3");
            }
            type = damage[0].getType();
            if (! (damage[1].getType().equals(type)
                && damage[2].getType().equals(type))) {
                throw new IllegalArgumentException("damage array contains"
                    + " elements that are of different HarmTypes");
            }
        } else {
            if (damage != null) {
                System.out.println("damage is not null");
            }
        }
        this.damage = damage;
    }
    private void setRange(RangeTag[] range) {
        RangeType type;

        if (isWeapon()) {
            HelperMethods.checkObjectArray("range", range);
            if (range.length != 3) {
                throw new IllegalArgumentException("range array is of range: "
                    + range.length + " which is not 3");
            }
            type = range[0].getType();
            if (! (range[1].getType().equals(type)
                && range[2].getType().equals(type))) {
                throw new IllegalArgumentException("range array contains"
                    + " elements that are of different RangeTypes");
            }
        } else {
            if (damage != null) {
                System.out.println("damage is not null");
            }
        }
        this.range = range;
    }
    private void setOnHit(String onHit) {
        if (isWeapon()) {
            HelperMethods.checkObject("onHit", onHit);
        } else {
            if (onHit != null) {
                System.out.println("onHit is not null");
            }
        }
        this.onHit = onHit;
    }
    // Conditionally semi-required properties
    private void setAttackBonus(int[] attackBonus) {
        if (isWeapon()) {
            if (attackBonus == null) {
                attackBonus = NPCFeature.attackBonusDefault;
            }
            if (attackBonus.length != 3) {
                throw new IllegalArgumentException("attackBonus array is of"
                    + " length: " + attackBonus.length + " which is not 3");
            }
        } else {
                System.out.println("attackBonus is not null");
        }
        this.attackBonus = attackBonus;
    }
    private void setAccuracy(int[] accuracy) {
        if (isWeapon()) {
            if (accuracy == null) {
                accuracy = NPCFeature.accuracyDefault;
            }
            if (accuracy.length != 3) {
                throw new IllegalArgumentException("accuracy array is of"
                    + " length: " + accuracy.length + " which is not 3");
            }
        } else {
                System.out.println("accuracy is not null");
        }
        this.accuracy = accuracy;
    }
    // Optional properties
    private void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }
    private void setTags(ITagData[] tags) {
        if (tags != null) {
            if (tags.length == 0) {
                tags = null;
            }
            HelperMethods.checkObjectArray("tags", tags);
            tags = HelperMethods.copyOf(tags);
        }
        this.tags = tags;
    }

    private boolean isWeapon() {
        return type.getValue().equals("Weapon");
    }
}
