package Packages.CoreTypes.EntityMechanics;

import MainBranch.HelperMethods;
import Packages.CoreTypes.JSONTypeTree;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.EntityMechanics.HarmSystem.harm.harmType.DamageType;

/**
 * See https://github.com/massif-press/lancer-data/blob/master/README.md#bonuses.
 */
public class Bonus {
    // TODO: fill out
    // Required properties
    private String id;
    private Object value;
    private JSONTypeTree valueType;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    private boolean overwrite;
    private static final boolean overwriteDefault = false;
    private boolean replace;
    private static final boolean replaceDefault = false;

    // Optional properties
    private DamageType[] damageTypes;
    private RangeType[] rangeTypes;
    private WeaponType[] weaponTypes;
    private WeaponSize[] weaponSizes;

    public Bonus(
        // Required properties
        String id, Object value, JSONTypeTree valueType,
        // Semi-required properties
        TriState overwrite, TriState replace,
        // Optional properties
        DamageType[] damageTypes, RangeType[] rangeTypes,
        WeaponType[] weaponTypes, WeaponSize[] weaponSizes
    ) {
        // Required properties
        setID(id);
        setValueType(valueType);
        setValue(value);
        // setValueType moved up so that setValue can work properly
        // Semi-required properties
        setOverwrite(overwrite);
        setReplace(replace);
        // Optional properties
        setDamageTypes(damageTypes);
        setRangeTypes(rangeTypes);
        setWeaponTypes(weaponTypes);
    }
    public Bonus(String id, Object value, JSONTypeTree valueType) {
        this(id, value, valueType, TriState.UNSET, TriState.UNSET,
            null, null, null,
            null);
    }

    // Required properties
    public String getID() {
        return id;
    }
    public Object getValue() {
        return JSONTypeTree.copy(value, valueType);
    }
    public JSONTypeTree getValueType() {
        return valueType;
    }
    // Semi-required properties
    public boolean isOverwrite() {
        return overwrite;
    }
    public boolean isReplace() {
        return replace;
    }
    // Optional properties
    public DamageType[] getDamageTypes() {
        if (damageTypes == null) {
            return damageTypes;
        }

        return HelperMethods.copyOf(damageTypes);
    }
    public RangeType[] getRangeTypes() {
        if (rangeTypes == null) {
            return rangeTypes;
        }

        return HelperMethods.copyOf(rangeTypes);
    }
    public WeaponType[] getWeaponTypes() {
        if (weaponTypes == null) {
            return weaponTypes;
        }

        return HelperMethods.copyOf(weaponTypes);
    }
    public WeaponSize[] getWeaponSizes() {
        if (weaponSizes == null) {
            return weaponSizes;
        }

        return HelperMethods.copyOf(weaponSizes);
    }
    // Required properties
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    private void setValue(Object value) {
        HelperMethods.checkObject("value", value);
        value = JSONTypeTree.copy(value, valueType);
        this.value = value;
    }
    private void setValueType(JSONTypeTree valueType) {
        HelperMethods.checkObject("valueType", valueType);
        this.valueType = valueType;
    }
    // Semi-required properties
    private void setOverwrite(TriState overwrite) {
        if (overwrite == TriState.UNSET) {
            this.overwrite = Bonus.overwriteDefault;
        } else {
            this.overwrite = overwrite.toBoolean();
        }
    }
    private void setReplace(TriState replace) {
        if (replace == TriState.UNSET) {
            this.replace = Bonus.replaceDefault;
        } else {
            this.replace = replace.toBoolean();
        }
    }
    // Optional properties
    private void setDamageTypes(DamageType[] damageTypes) {
        if (damageTypes != null) {
            HelperMethods.checkObjectArray("damageTypes",
                damageTypes);
            damageTypes = HelperMethods.copyOf(damageTypes);
        }
        this.damageTypes = damageTypes;
    }
    private void setRangeTypes(RangeType[] rangeTypes) {
        if (rangeTypes != null) {
            HelperMethods.checkObjectArray("rangeTypes",
                rangeTypes);
            rangeTypes = HelperMethods.copyOf(rangeTypes);
        }
        this.rangeTypes = rangeTypes;
    }
    private void setWeaponTypes(WeaponType[] weaponTypes) {
        if (weaponTypes != null) {
            HelperMethods.checkObjectArray("weaponTypes",
                weaponTypes);
            weaponTypes = HelperMethods.copyOf(weaponTypes);
        }
        this.weaponTypes = weaponTypes;
    }
    private void setWeaponSizes(WeaponSize[] weaponSizes) {
        if (weaponSizes != null) {
            HelperMethods.checkObjectArray("weaponSizes",
                weaponSizes);
            weaponSizes = HelperMethods.copyOf(weaponSizes);
        }
        this.weaponSizes = weaponSizes;
    }
}
