package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase;

import MainBranch.Database;
import MainBranch.HelperMethods;
import Packages.CoreTypes.TriState;
import Packages.CoreTypes.VueHTMLString;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.RangeTag;
import Packages.CoreTypes.EntityMechanics.WeaponSize;
import Packages.CoreTypes.EntityMechanics.Actions.Verified.actionBase.IActionData;
import Packages.CoreTypes.EntityMechanics.ActivationTypeSystem.Verified.ActivationType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.deployable.iDeployableDataBase.IDeployableData;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.SystemBase;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.TagSystem.unverifiedDataTag.DataTag;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.systemType.WeaponType;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.Equipment.Verified.equipment.systemBase.weapon.IWeaponProfile;
import Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.mech.mount.MountType;
import Packages.CoreTypes.EntityMechanics.HarmSystem.Harm;
import Packages.CoreTypes.counterBase.CounterData;

/**
 * See pgs. 33 and 104.
 */
/**
 * Represents a single weapon to be mounted on a mount. Contains information
 *     about that weapon such as its name, size, and data tags.
 * 
 * Requires a weapon name and weapon size to be instantiated.
 * 
 * Used in Mount.
 * 
 * Safety: This class has placeholder values and can be a placeholder. At least
 *     one of its properties has an allowed value of null.
 */
public final class Weapon extends SystemBase {
    // TODO: fill out with some kind of way to attack
    // TODO: fucking deal with Ushabti Omnigun somehow
    // Required properties
    private MountType mount;
    /**
     * The weapon's type (i.e. a WeaponType representing a CQB weapon).
     * Can be any WeaponType. Cannot be null.
     */
    private WeaponType weaponType;

    // Semi-required (optional but has a specific default value other than null
    //     when not provided) properties
    /**
     * Can be any int.
     * Default value: 0.
     */
    private int cost;
    public static final int costDefault = 0;
    /**
     * Default value: true.
     */
    private boolean barrage;
    private static final boolean barrageDefault = true;
    /**
     * Default value is automatically determined based on this.activation type.
     * If this.activation is a quick action, the default value is false.
     * If this.activation is a full action, the default value is true.
     */
    private boolean skirmish;
    private boolean noAttack;
    private static final boolean noAttackDefault = false;
    private boolean noMods;
    private static final boolean noModsDefault = false;
    private boolean noCoreBonus;
    private static final boolean noCoreBonusDefault = false;
    private boolean noBonus;
    private static final boolean noBonusDefault = false;
    private boolean noSynergy;
    private static final boolean noSynergyDefault = false;
    /**
     * The weapon's range (i.e. a RangeTag[] containing a RangeTag representing
     *     Range 10).
     * Can be any RangeTag[] that is not of length 0 or contains null elements.
     *     Cannot be null.
     * Default value: a RangeTag[] containing a single RangeTag representing
     *     Threat 1.
     * 
     * If passed a RangeTag[] that is null or of length 0, sets this.range to
     *     the default value.
     */
    private RangeTag[] range;
    // rangeDefault removed because the relevant value only becomes accessible
    //     at runtime

    // Optional properties
    /**
     * The weapon's damage, including heat and burn (i.e. a Harm[] containing
     *     a Harm representing 10 Kinetic, 2 Heat, or 3 Burn).
     * Can be any Harm[] that is not of length 0 or contains null elements. Can
     *     be null.
     */
    private Harm[] damage;
    private VueHTMLString effect;
    private VueHTMLString onAttack;
    private VueHTMLString onHit;
    private VueHTMLString onCrit;
    /**
     * Can be any IActionData[] that is not of length 0 or contains null
     *     elements. Can be null.
     */
    private IActionData[] actions;
    /**
     * Can be any Bonus[] that is not of length 0 or contains null elements. Can
     *     be null.
     */
    private Bonus[] bonuses;
    /**
     * Can be any ISynergyData[] that is not of length 0 or contains null
     *     elements. Can be null.
     */
    private ISynergyData[] synergies;
    /**
     * Can be any IDeployableData[] that is not of length 0 or contains null
     *     elements. Can be null.
     */
    private IDeployableData[] deployables;
    /**
     * Can be any CounterData[] that is not of length 0 or contains null
     *     elements. Can be null.
     */
    private CounterData[] counters;
    /**
     * Can be any String[] that is not of length 0 or contains null elements or
     *     elements that are "". Can be null.
     */
    private String[] integrated;
    /**
     * Can be any String[] that is not of length 0 or contains null elements or
     *     elements that are "". Can be null.
     */
    private String[] specialEquipment;
    /**
     * Can be any IWeaponProfile[] that is not of length 0 or contains null
     *     elements. Can be null.
     */
    private IWeaponProfile[] profiles;

    // Helper properties
    /**
     * The weapon's size (i.e. a WeaponSize representing a main weapon).
     * Can be any WeaponSize. Cannot be null.
     */
    private WeaponSize size;
    /**
     * The action required to activate this weapon (i.e. an ActivationType
     *     representing a quick action).
     * Can be either a quick action or a full action. Cannot be null.
     */
    private ActivationType activation;

    // Reference properties
    /**
     * Contains an array of allowed values for Weapon.dataTags' DataTag.name
     *     values.
     * Case-sensitive.
     */
    public static final String[] allowedNames = new String[] {"Accurate",
        "Armor-Piercing (AP)", "Loading", "Ordnance", "Reliable X",
        "Inaccurate", "Arcing", "Smart", "Overkill", "Thrown X",
        "Heat X (Self)", "Knockback X", "Limited X", "Seeking", "Unique",
        "Reaction", "Drone", "Protocol", "Full Action", "Deployable",
        "Quick Action", "X/Round", "Danger Zone", "AI", "Free Action"};

    /**
     * Verbose constructor for non-GMS content.
     * Takes in as a parameter the semi-required property SystemBase.spCost.
     * Takes in as parameters Weapon's semi-required properties.
     * Takes in as parameters Weapon's optional properties.
     */
    public Weapon(
        // SystemBase properties
        String id, String name, Manufacturer manufacturer, String licenseID,
        String licenseName, int licenseLevel, String description,
        DataTag[] dataTags, int spCost,
        // Required properties
        MountType mount, WeaponType weaponType,
        // Semi-required properties
        int cost, TriState barrage, TriState skirmish, TriState noAttack,
        TriState noMods, TriState noCoreBonus, TriState noBonus,
        TriState noSynergy, RangeTag[] range,
        // Optional properties
        Harm[] damage, String effect, String onAttack, String onHit,
        String onCrit, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, IDeployableData[] deployables,
        CounterData[] counters, String[] integrated, String[] specialEquipment,
        IWeaponProfile[] profiles
    ) {
        // SystemBase properties
        super(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description, dataTags, null, spCost);
        // Required properties
        setMount(mount);
        setWeaponType(weaponType);
        // Semi-required properties
        setCost(cost);
        setBarrage(barrage);
        // setSkirmish moved below the helper properties section because it is
        //     dependent on this.activation
        setNoAttack(noAttack);
        setNoMods(noMods);
        setNoCoreBonus(noCoreBonus);
        setNoBonus(noBonus);
        setNoSynergy(noSynergy);
        setRange(range);
        // Optional properties
        setDamage(damage);
        setEffect(effect);
        setOnAttack(onAttack);
        setOnHit(onHit);
        setOnCrit(onCrit);
        setActions(actions);
        setBonuses(bonuses);
        setSynergies(synergies);
        setDeployables(deployables);
        setCounters(counters);
        setIntegrated(integrated);
        setSpecialEquipment(specialEquipment);
        setProfiles(profiles);
        // Helper properties
        calculateHelperProperties();

        setSkirmish(skirmish);
    }
    /**
     * Abbreviated constructor for GMS content.
     * Takes in as a parameter the semi-required property SystemBase.spCost.
     * Takes in as parameters Weapon's semi-required properties.
     * Takes in as parameters Weapon's optional properties.
     */
    public Weapon(
        // SystemBase properties
        String id, String name, String licenseID, String licenseName,
        String description, DataTag[] dataTags, int spCost,
        // Required properties
        MountType mount, WeaponType weaponType,
        // Semi-required properties
        int cost, TriState barrage, TriState skirmish, TriState noAttack,
        TriState noMods, TriState noCoreBonus, TriState noBonus,
        TriState noSynergy, RangeTag[] range,
        // Optional properties
        Harm[] damage, String effect, String onAttack, String onHit,
        String onCrit, IActionData[] actions, Bonus[] bonuses,
        ISynergyData[] synergies, IDeployableData[] deployables,
        CounterData[] counters, String[] integrated, String[] specialEquipment,
        IWeaponProfile[] profiles
    ) {
        // SystemBase properties
        super(id, name, licenseID, licenseName, description, dataTags,
            null, spCost);
        // Required properties
        setMount(mount);
        setWeaponType(weaponType);
        // Semi-required properties
        setCost(cost);
        setBarrage(barrage);
        // setSkirmish moved below the helper properties section because it is
        //     dependent on this.activation
        setNoAttack(noAttack);
        setNoMods(noMods);
        setNoCoreBonus(noCoreBonus);
        setNoBonus(noBonus);
        setNoSynergy(noSynergy);
        setRange(range);
        // Optional properties
        setDamage(damage);
        setEffect(effect);
        setOnAttack(onAttack);
        setOnHit(onHit);
        setOnCrit(onCrit);
        setActions(actions);
        setBonuses(bonuses);
        setSynergies(synergies);
        setDeployables(deployables);
        setCounters(counters);
        setIntegrated(integrated);
        setSpecialEquipment(specialEquipment);
        setProfiles(profiles);
        // Helper properties
        calculateHelperProperties();

        setSkirmish(skirmish);
    }
    /**
     * Verbose constructor for non-GMS content.
     * Takes in as a parameter the semi-required property SystemBase.spCost.
     * Takes in as parameters Weapon's semi-required properties.
     * Does not take in any of Weapon's optional properties as parameters.
     */
    public Weapon(
        // SystemBase properties
        String id, String name, Manufacturer manufacturer, String licenseID,
        String licenseName, int licenseLevel, String description,
        DataTag[] dataTags, int spCost,
        // Required properties
        MountType mount, WeaponType weaponType,
        // Semi-required properties
        int cost, TriState barrage, TriState skirmish, TriState noAttack,
        TriState noMods, TriState noCoreBonus, TriState noBonus,
        TriState noSynergy, RangeTag[] range
    ) {
        this(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description, dataTags, spCost, mount, weaponType,
            Weapon.costDefault, TriState.UNSET, TriState.UNSET, TriState.UNSET,
            TriState.UNSET, TriState.UNSET, TriState.UNSET, TriState.UNSET,
            null, null, null, null, null,
            null, null, null, null,
            null, null, null,
            null, null);
    }
    /**
     * Abbreviated constructor for GMS content.
     * Takes in as a parameter the semi-required property SystemBase.spCost.
     * Takes in as parameters Weapon's semi-required properties.
     * Does not take in any of Weapon's optional properties as parameters.
     */
    public Weapon(
        // SystemBase properties
        String id, String name, String licenseID, String licenseName,
        String description, DataTag[] dataTags, int spCost,
        // Required properties
        MountType mount, WeaponType weaponType,
        // Semi-required properties
        int cost, TriState barrage, TriState skirmish, TriState noAttack,
        TriState noMods, TriState noCoreBonus, TriState noBonus,
        TriState noSynergy, RangeTag[] range
    ) {
        this(id, name, licenseID, licenseName, description, dataTags, spCost,
            mount, weaponType, Weapon.costDefault, TriState.UNSET,
            TriState.UNSET, TriState.UNSET, TriState.UNSET, TriState.UNSET,
            TriState.UNSET, TriState.UNSET, null, null,
            null, null, null, null,
            null, null, null, null,
            null, null, null,
            null);
    }
    /**
     * Verbose constructor for non-GMS content.
     * Takes in as a parameter the semi-required property SystemBase.spCost.
     * Does not take in any of Weapon's semi-required properties as parameters.
     */
    public Weapon(
        // SystemBase properties
        String id, String name, Manufacturer manufacturer, String licenseID,
        String licenseName, int licenseLevel, String description,
        DataTag[] dataTags, int spCost,
        // Required properties
        MountType mount, WeaponType weaponType
    ) {
        this(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description, dataTags, spCost, mount, weaponType,
            Weapon.costDefault, TriState.UNSET, TriState.UNSET, TriState.UNSET,
            TriState.UNSET, TriState.UNSET, TriState.UNSET, TriState.UNSET,
            null);
    }
    /**
     * Abbreviated constructor for GMS content.
     * Takes in as a parameter the semi-required property SystemBase.spCost.
     * Does not take in any of Weapon's semi-required properties as parameters.
     */
    public Weapon(
        // SystemBase properties
        String id, String name, String licenseID, String licenseName,
        String description, DataTag[] dataTags, int spCost,
        // Required properties
        MountType mount, WeaponType weaponType
    ) {
        this(id, name, licenseID, licenseName, description, dataTags, spCost,
            mount, weaponType, Weapon.costDefault, TriState.UNSET,
            TriState.UNSET, TriState.UNSET, TriState.UNSET, TriState.UNSET,
            TriState.UNSET, TriState.UNSET, null);
    }
    /**
     * Verbose constructor for non-GMS content.
     * Does not take in any semi-required properties as parameters.
     */
    public Weapon(
        // SystemBase properties
        String id, String name, Manufacturer manufacturer, String licenseID,
        String licenseName, int licenseLevel, String description,
        DataTag[] dataTags,
        // Required properties
        MountType mount, WeaponType weaponType
    ) {
        this(id, name, manufacturer, licenseID, licenseName, licenseLevel,
            description, dataTags, Weapon.spCostDefault, mount, weaponType);
    }
    /**
     * Abbreviated constructor for GMS content.
     * Does not take in any semi-required properties as parameters.
     */
    public Weapon(
        // SystemBase properties
        String id, String name, String licenseID, String licenseName,
        String description, DataTag[] dataTags,
        // Required properties
        MountType mount, WeaponType weaponType
    ) {
        this(id, name, licenseID, licenseName, description, dataTags,
            Weapon.spCostDefault, mount, weaponType);
    }

    // Required properties
    public MountType getMount() {
        return mount;
    }
    public WeaponType getWeaponType() {
        return weaponType;
    }
    // Semi-required properties
    public int getCost() {
        return cost;
    }
    public boolean isBarrage() {
        return barrage;
    }
    public boolean isSkirmish() {
        return skirmish;
    }
    public boolean isNoAttack() {
        return noAttack;
    }
    public boolean isNoMods() {
        return noMods;
    }
    public boolean isNoCoreBonus() {
        return noCoreBonus;
    }
    public boolean isNoBonus() {
        return noBonus;
    }
    public boolean isNoSynergy() {
        return noSynergy;
    }
    public RangeTag[] getRange() {
        return HelperMethods.copyOf(range);
    }
    // Optional properties
    public Harm[] getDamage() {
        if (damage == null) {
            return damage;
        }

        return HelperMethods.copyOf(damage);
    }
    public VueHTMLString getEffect() {
        return effect;
    }
    public VueHTMLString getOnAttack() {
        return onAttack;
    }
    public VueHTMLString getOnHit() {
        return onHit;
    }
    public VueHTMLString getOnCrit() {
        return onCrit;
    }
    public IActionData[] getActions() {
        if (actions == null) {
            return actions;
        }

        return HelperMethods.copyOf(actions);
    }
    public Bonus[] getBonuses() {
        if (bonuses == null) {
            return bonuses;
        }

        return HelperMethods.copyOf(bonuses);
    }
    public ISynergyData[] getSynergies() {
        if (synergies == null) {
            return synergies;
        }

        return HelperMethods.copyOf(synergies);
    }
    public IDeployableData[] getDeployables() {
        if (deployables == null) {
            return deployables;
        }

        return HelperMethods.copyOf(deployables);
    }
    public CounterData[] getCounters() {
        if (counters == null) {
            return counters;
        }

        return HelperMethods.copyOf(counters);
    }
    public String[] getIntegrated() {
        if (integrated == null) {
            return integrated;
        }

        return HelperMethods.copyOf(integrated);
    }
    public String[] getSpecialEquipment() {
        if (specialEquipment == null) {
            return specialEquipment;
        }

        return HelperMethods.copyOf(specialEquipment);
    }
    public IWeaponProfile[] getProfiles() {
        if (profiles == null) {
            return profiles;
        }

        return HelperMethods.copyOf(profiles);
    }
    // Helper properties
    public WeaponSize getSize() {
        return size;
    }
    public ActivationType getActivation() {
        return activation;
    }
    // Inherited properties
    /**
     * Sets this.dataTags to the provided value.
     * @param dataTags a DataTag[] which cannot be null, contain null elements,
     *     or contain DataTags with invalid DataTag.name values as defined by
     *     Weapon.allowedNames.
     * @throws IllegalArgumentException if dataTags includes an element with an
     *     invalid DataTag.name value for a Weapon, as defined by
     *     Weapon.allowedNames.
     */
    @Override
    protected void setDataTags(DataTag[] dataTags) {
        boolean isValid;
        String dataTagName;

        HelperMethods.checkObjectArray("dataTags", dataTags);
        for (DataTag tag : dataTags) {
            isValid = false;
            dataTagName = tag.getData().getName();
            for (String allowedTag : Weapon.allowedNames) {
                if (dataTagName.equals(allowedTag)) {
                    isValid = true;
                    break;
                }
            }
            if (! isValid) {
                throw new IllegalArgumentException("New tags array includes a"
                    + " Tag with an invalid tag name for a Weapon: \""
                    + dataTagName + "\"");
            }
        }
        dataTags = HelperMethods.copyOf(dataTags);
        this.dataTags = dataTags;
    }
    @Override
    protected void setType(SystemType type) {
        if (type == null) {
            type = Database.getSystemType("Weapon");
        }
        this.type = type;
    }
    // Required properties
    private void setMount(MountType mount) {
        HelperMethods.checkObject("mount", mount);
        this.mount = mount;
    }
    private void setWeaponType(WeaponType weaponType) {
        HelperMethods.checkObject("weaponType", weaponType);
        this.weaponType = weaponType;
    }
    // Semi-required properties
    private void setCost(int cost) {
        this.cost = cost;
    }
    private void setBarrage(boolean barrage) {
        this.barrage = barrage;
    }
    private void setSkirmish(boolean skirmish) {
        this.skirmish = skirmish;
    }
    private void setNoAttack(boolean noAttack) {
        this.noAttack = noAttack;
    }
    private void setNoMods(boolean noMods) {
        this.noMods = noMods;
    }
    private void setNoCoreBonus(boolean noCoreBonus) {
        this.noCoreBonus = noCoreBonus;
    }
    private void setNoBonus(boolean noBonus) {
        this.noBonus = noBonus;
    }
    private void setNoSynergy(boolean noSynergy) {
        this.noSynergy = noSynergy;
    }
    private void setRange(RangeTag[] range) {
        HelperMethods.checkObjectArray("range", range);
        if (range.length < 1) {
            throw new IllegalArgumentException("range.length value: "
                + range.length + " is < 1");
        }
        range = HelperMethods.copyOf(range);
        this.range = range;
    }
    // Optional properties
    private void setDamage(Harm[] damage) {
        HelperMethods.checkObjectArrayAlt("damage", damage);
        if (damage != null) {
            damage = HelperMethods.copyOf(damage);
        }
        this.damage = damage;
    }
    private void setEffect(VueHTMLString effect) {
        this.effect = effect;
    }
    private void setOnAttack(VueHTMLString onAttack) {
        this.onAttack = onAttack;
    }
    private void setOnHit(VueHTMLString onHit) {
        this.onHit = onHit;
    }
    private void setOnCrit(VueHTMLString onCrit) {
        this.onCrit = onCrit;
    }
    private void setActions(IActionData[] actions) {
        HelperMethods.checkObjectArrayAlt("actions", actions);
        if (actions != null) {
            actions = HelperMethods.copyOf(actions);
        }
        this.actions = actions;
    }
    private void setBonuses(Bonus[] bonuses) {
        HelperMethods.checkObjectArrayAlt("bonuses", bonuses);
        if (bonuses != null) {
            bonuses = HelperMethods.copyOf(bonuses);
        }
        this.bonuses = bonuses;
    }
    private void setSynergies(ISynergyData[] synergies) {
        HelperMethods.checkObjectArrayAlt("synergies", synergies);
        if (synergies != null) {
            synergies = HelperMethods.copyOf(synergies);
        }
        this.synergies = synergies;
    }
    private void setDeployables(IDeployableData[] deployables) {
        HelperMethods.checkObjectArrayAlt("deployables",
            deployables);
        if (deployables != null) {
            deployables = HelperMethods.copyOf(deployables);
        }
        this.deployables = deployables;
    }
    private void setCounters(CounterData[] counters) {
        HelperMethods.checkObjectArrayAlt("counters", counters);
        if (counters != null) {
            counters = HelperMethods.copyOf(counters);
        }
        this.counters = counters;
    }
    private void setIntegrated(String[] integrated) {
        HelperMethods.checkStringArrayAlt("integrated",
            integrated);
        if (integrated != null) {
            integrated = HelperMethods.copyOf(integrated);
        }
        this.integrated = integrated;
    }
    private void setSpecialEquipment(String[] specialEquipment) {
        HelperMethods.checkStringArrayAlt("specialEquipment",
            specialEquipment);
        if (specialEquipment != null) {
            specialEquipment = HelperMethods.copyOf(specialEquipment);
        }
        this.specialEquipment = specialEquipment;
    }
    private void setProfiles(IWeaponProfile[] profiles) {
        HelperMethods.checkObjectArrayAlt("profiles", profiles);
        if (profiles != null) {
            profiles = HelperMethods.copyOf(profiles);
        }
        this.profiles = profiles;
    }
    // Helper properties
    private void setSize(WeaponSize size) {
        HelperMethods.checkObject("size", size);
        this.size = size;
    }
    private void setActivation(ActivationType activation) {
        HelperMethods.checkObject("activation", activation);
        if (! (activation.getType().equals("quick")
            || activation.getType().equals("full"))) {
            throw new IllegalArgumentException("activation.type is: \""
                + activation.getType() + "\" which is neither \"quick\" nor"
                + " \"full\"");
        }
        this.activation = activation;
    }

    private void setBarrage(TriState barrage) {
        if (barrage == TriState.UNSET) {
            this.barrage = Weapon.barrageDefault;
        } else {
            setBarrage(barrage.toBoolean());
        }
    }
    private void setSkirmish(TriState skirmish) {
        if (skirmish == TriState.UNSET) {
            setSkirmish(calculateSkirmishDefault());
        } else {
            setSkirmish(skirmish.toBoolean());
        }
    }
    private void setNoAttack(TriState noAttack) {
        if (noAttack == TriState.UNSET) {
            setNoAttack(Weapon.noAttackDefault);
        } else {
            setNoAttack(noAttack.toBoolean());
        }
    }
    private void setNoMods(TriState noMods) {
        if (noMods == TriState.UNSET) {
            setNoMods(Weapon.noModsDefault);
        } else {
            setNoMods(noMods.toBoolean());
        }
    }
    private void setNoCoreBonus(TriState noCoreBonus) {
        if (noCoreBonus == TriState.UNSET) {
            setNoCoreBonus(Weapon.noCoreBonusDefault);
        } else {
            setNoCoreBonus(noCoreBonus.toBoolean());
        }
    }
    private void setNoBonus(TriState noBonus) {
        if (noBonus == TriState.UNSET) {
            setNoBonus(Weapon.noBonusDefault);
        } else {
            setNoBonus(noBonus.toBoolean());
        }
    }
    private void setNoSynergy(TriState noSynergy) {
        if (noSynergy == TriState.UNSET) {
            setNoSynergy(Weapon.noSynergyDefault);
        } else {
            setNoSynergy(noSynergy.toBoolean());
        }
    }
    private void setEffect(String effect) {
        if (effect == null) {
            this.effect = null;
        } else {
            setEffect(new VueHTMLString(effect));
        }
    }
    private void setOnAttack(String onAttack) {
        if (onAttack == null) {
            this.onAttack = null;
        } else {
            setOnAttack(new VueHTMLString(onAttack));
        }
    }
    private void setOnHit(String onHit) {
        if (onHit == null) {
            this.onHit = null;
        } else {
            setOnHit(new VueHTMLString(onHit));
        }
    }
    private void setOnCrit(String onCrit) {
        if (onCrit == null) {
            this.onCrit = null;
        } else {
            setOnCrit(new VueHTMLString(onCrit));
        }
    }
    private boolean calculateSkirmishDefault() {
        // TODO: fill out
        return true;
    }
    private void calculateHelperProperties() {
        // TODO: fill out
    }
}