package Packages.CoreTypes.EntityMechanics;

import MainBranch.HelperMethods;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.SystemType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.systemType.WeaponType;

public class ISynergyData {
    // TODO: fill out
    // Required properties
    private SynergyLocation[] locations;
    private VueHTMLString detail;

    // Optional properties
    private WeaponType[] weaponTypes;
    private SystemType[] systemTypes;
    private WeaponSize[] weaponSizes;

    public ISynergyData(SynergyLocation[] locations, String detail,
        WeaponType[] weaponTypes, SystemType[] systemTypes,
        WeaponSize[] weaponSizes) {
        // Required properties
        setLocations(locations);
        setDetail(detail);
        // Optional properties
        setWeaponTypes(weaponTypes);
        setSystemTypes(systemTypes);
        setWeaponSizes(weaponSizes);
    }
    public ISynergyData(SynergyLocation[] locations, String detail) {
        this(locations, detail, null, null,
            null);
    }

    // Required properties
    public SynergyLocation[] getLocations() {
        return locations;
    }
    public VueHTMLString getDetail() {
        return detail;
    }
    // Optional properties
    public WeaponType[] getWeaponTypes() {
        return weaponTypes;
    }
    public SystemType[] getSystemTypes() {
        return systemTypes;
    }
    public WeaponSize[] getWeaponSizes() {
        return weaponSizes;
    }
    // Required properties
    private void setLocations(SynergyLocation[] locations) {
        HelperMethods.checkObjectArray("locations", locations);
        this.locations = locations;
    }
    private void setDetail(VueHTMLString detail) {
        HelperMethods.checkVueHTMLString("detail", detail);
        this.detail = detail;
    }
    // Optional properties
    private void setWeaponTypes(WeaponType[] weaponTypes) {
        HelperMethods.checkObjectArrayAlt("weaponTypes",
            weaponTypes);
        this.weaponTypes = weaponTypes;
    }
    private void setSystemTypes(SystemType[] systemTypes) {
        HelperMethods.checkObjectArrayAlt("systemTypes",
            systemTypes);
        this.systemTypes = systemTypes;
    }
    public void setWeaponSizes(WeaponSize[] weaponSizes) {
        HelperMethods.checkObjectArrayAlt("weaponSizes",
            weaponSizes);
        this.weaponSizes = weaponSizes;
    }

    private void setDetail(String detail) {
        HelperMethods.checkObject("detail", detail);
        setDetail(new VueHTMLString(detail));
    }
}
