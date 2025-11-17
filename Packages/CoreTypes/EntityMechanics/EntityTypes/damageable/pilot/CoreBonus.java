package Packages.CoreTypes.EntityMechanics.EntityTypes.damageable.pilot;

import MainBranch.HelperMethods;
import Packages.CoreTypes.EntityMechanics.Bonus;
import Packages.CoreTypes.EntityMechanics.Manufacturer;
import Packages.CoreTypes.EntityMechanics.ISynergyData;
import Packages.CoreTypes.EntityMechanics.Actions.actionBase.IActionData;

public class CoreBonus {
    // TODO: fill out
    // Required properties
    private String id;
    private String name;
    private Manufacturer source;
    private String effect;
    private String description;
    // Optional properties
    private String mountedEffect;
    private Bonus[] bonuses;
    private ISynergyData[] synergies;
    private IActionData[] actions;

    public CoreBonus(String id, String name, Manufacturer source, String effect,
        String description, String mountedEffect, Bonus[] bonuses,
        ISynergyData[] synergies, IActionData[] actions) {
        HelperMethods.verifyConstructor();
        setID(id);
        setName(name);
        setSource(source);
        setEffect(effect);
        setDescription(description);
        // Optional properties
        setMountedEffect(mountedEffect);
        setBonuses(bonuses);
        setSynergies(synergies);
        setActions(actions);
    }
    public CoreBonus(String id, String name, Manufacturer source, String effect,
        String description) {
        this(id, name, source, effect, description, null,
            null, null, null);
    }
    public CoreBonus(CoreBonus coreBonus) {
        setID(coreBonus.id);
        setName(coreBonus.name);
        setSource(coreBonus.source);
        setEffect(coreBonus.effect);
        setDescription(coreBonus.description);
        // Optional properties
        setMountedEffect(coreBonus.mountedEffect);
        setBonuses(coreBonus.bonuses);
        setSynergies(coreBonus.synergies);
        setActions(coreBonus.actions);
    }

    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Manufacturer getSource() {
        return new Manufacturer(source);
    }
    public String getEffect() {
        return effect;
    }
    public String getDescription() {
        return description;
    }
    public String getMountedEffect() {
        return mountedEffect;
    }
    public Bonus[] getBonuses() {
        return HelperMethods.copyOf(bonuses);
    }
    public ISynergyData[] getSynergies() {
        return HelperMethods.copyOf(synergies);
    }
    public IActionData[] getActions() {
        return HelperMethods.copyOf(actions);
    }
    public void setID(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSource(Manufacturer source) {
        HelperMethods.checkObject("source", source);
        source = new Manufacturer(source);
        this.source = source;
    }
    public void setEffect(String effect) {
        this.effect = effect;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setMountedEffect(String mountedEffect) {
        this.mountedEffect = mountedEffect;
    }
    public void setBonuses(Bonus[] bonuses) {
        this.bonuses = bonuses;
    }
    public void setSynergies(ISynergyData[] synergies) {
        this.synergies = synergies;
    }
    public void setActions(IActionData[] actions) {
        this.actions = actions;
    }
}
