package Packages.CoreTypes.BattlefieldMechanics;

import MainBranch.HelperMethods;

/**
 * Represents a single sitrep. Contains information about that sitrep's id,
 *     name, win conditions for both parties, and description, among other
 *     optional properties.
 * 
 * Requires a sitrep id, name, pc victory condition, enemy victory condition,
 *     and description to be instantiated.
 * 
 * Unused at present.
 * 
 * Safety: This class does not have placeholder values and cannot be a
 *     placeholder. At least one of its properties has an allowed value of null.
 */
public class Sitrep {
    // Required properties
    /**
     * The ID of the sitrep (i.e. "sitrep_standardcombat").
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
    /**
     * The name of the sitrep (i.e. "Standard Combat").
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * A description of this sitrep (i.e. TODO: add example).
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String description;

    // Optional properties
    /**
     * The conditions for the player characters to claim victory within this
     *     sitrep (i.e. TODO: add example).
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String pcVictory;
    /**
     * The conditions for the enemy force to claim victory within this sitrep
     *     (i.e. TODO: add example).
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String enemyVictory;
    /**
     * The conditions under which no victory can be claimed by either side in
     *     this sitrep (i.e. TODO: add example).
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String noVictory;
    /**
     * The procedure for how to determine the order in which player characters
     *     and enemies should deploy (i.e. TODO: add example).
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String deployment;
    /**
     * A description of what the Objective is and its importance (i.e. TODO: add
     *     example).
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String objective;
    /**
     * The conditions under which player characters may extract from the sitrep,
     *     if extraction is possible (i.e. TODO: add example).
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String extraction;
    /**
     * The restrictions for where the control zones for this sitrep can be
     *     placed (i.e. TODO: add example).
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String controlZone;

    public Sitrep(String id, String name, String description, String pcVictory,
        String enemyVictory, String noVictory, String deployment,
        String objective, String extraction, String controlZone) {
        // Required properties
        setID(id);
        setName(name);
        setDescription(description);
        // Optional properties
        setPCVictory(pcVictory);
        setEnemyVictory(enemyVictory);
        setNoVictory(noVictory);
        setDeployment(deployment);
        setObjective(objective);
        setExtraction(extraction);
        setControlZone(controlZone);
    }
    public Sitrep(String id, String name, String description) {
        this(id, name, description, null, null,
            null, null, null, null,
            null);
    }
    public Sitrep(Sitrep sitrep) {
        // Required properties
        setID(sitrep.id);
        setName(sitrep.name);
        setDescription(sitrep.description);
        // Optional properties
        setPCVictory(sitrep.pcVictory);
        setEnemyVictory(sitrep.enemyVictory);
        setNoVictory(sitrep.noVictory);
        setDeployment(sitrep.deployment);
        setObjective(sitrep.objective);
        setExtraction(sitrep.extraction);
        setControlZone(sitrep.controlZone);
    }

    // Required properties
    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    // Optional properties
    public String getPCVictory() {
        return pcVictory;
    }
    public String getEnemyVictory() {
        return enemyVictory;
    }
    public String getNoVictory() {
        return noVictory;
    }
    public String getControlZone() {
        return controlZone;
    }
    public String getDeployment() {
        return deployment;
    }
    public String getExtraction() {
        return extraction;
    }
    public String getObjective() {
        return objective;
    }
    // Required properties
    private void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    private void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    private void setDescription(String description) {
        HelperMethods.checkString("description", description);
        this.description = description;
    }
    // Optional properties
    private void setPCVictory(String pcVictory) {
        if (pcVictory != null) {
            HelperMethods.checkString("pcVictory", pcVictory);
        }
        this.pcVictory = pcVictory;
    }
    private void setEnemyVictory(String enemyVictory) {
        if (enemyVictory != null) {
            HelperMethods.checkString("enemyVictory",
                enemyVictory);
        }
        this.enemyVictory = enemyVictory;
    }
    private void setNoVictory(String noVictory) {
        if (noVictory != null) {
            HelperMethods.checkString("noVictory", noVictory);
        }
        this.noVictory = noVictory;
    }
    private void setDeployment(String deployment) {
        if (deployment != null) {
            HelperMethods.checkString("deployment", deployment);
        }
        this.deployment = deployment;
    }
    private void setObjective(String objective) {
        if (objective != null) {
            HelperMethods.checkString("objective", objective);
        }
        this.objective = objective;
    }
    private void setExtraction(String extraction) {
        if (extraction != null) {
            HelperMethods.checkString("extraction", extraction);
        }
        this.extraction = extraction;
    }
    private void setControlZone(String controlZone) {
        if (controlZone != null) {
            HelperMethods.checkString("controlZone", controlZone);
        }
        this.controlZone = controlZone;
    }
}
