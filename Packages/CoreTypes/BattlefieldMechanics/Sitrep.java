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
     * The ID of the sitrep (i.e. TODO: add example).
     * Can be any String except "". Cannot be null.
     * Case-insensitive and stored in lowercase.
     */
    private String id;
    /**
     * The name of the sitrep (i.e. TODO: add example).
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String name;
    /**
     * The conditions for the player characters to claim victory within this
     *     sitrep (i.e. TODO: add example).
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String pcVictory;
    /**
     * The conditions for the enemy force to claim victory within this sitrep
     *     (i.e. TODO: add example).
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String enemyVictory;
    /**
     * A description of this sitrep (i.e. TODO: add example).
     * Can be any String except "". Cannot be null.
     * Case-sensitive.
     */
    private String description;

    // Optional properties
    /**
     * The conditions under which no victory can be claimed by either side in
     *     this sitrep (i.e. TODO: add example).
     * Can be any String except "". Can be null.
     * Case-sensitive.
     */
    private String noVictory;
    private String controlZone;
    private String deployment;
    private String extraction;
    private String objective;

    public Sitrep(String id, String name, String pcVictory, String enemyVictory,
        String description, String noVictory, String controlZone,
        String deployment, String extraction, String objective) {
        // Required properties
        setID(id);
        setName(name);
        setPcVictory(pcVictory);
        setEnemyVictory(enemyVictory);
        setDescription(description);
        // Optional properties
        setNoVictory(noVictory);
        setControlZone(controlZone);
        setDeployment(deployment);
        setExtraction(extraction);
        setObjective(objective);
    }
    public Sitrep(String id, String name, String pcVictory, String enemyVictory,
        String description) {
        this(id, name, pcVictory, enemyVictory, description, null,
            null, null, null,
            null);
    }
    public Sitrep(Sitrep sitrep) {
        this(sitrep.id, sitrep.name, sitrep.pcVictory, sitrep.enemyVictory,
            sitrep.description, sitrep.noVictory, sitrep.controlZone,
            sitrep.deployment, sitrep.extraction, sitrep.objective);
    }

    // Required properties
    public String getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPcVictory() {
        return pcVictory;
    }
    public String getEnemyVictory() {
        return enemyVictory;
    }
    public String getDescription() {
        return description;
    }
    // Optional properties
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
    public void setID(String id) {
        HelperMethods.checkString("id", id);
        id = id.toLowerCase();
        this.id = id;
    }
    public void setName(String name) {
        HelperMethods.checkString("name", name);
        this.name = name;
    }
    public void setPcVictory(String pcVictory) {
        HelperMethods.checkString("pcVictory", pcVictory);
        this.pcVictory = pcVictory;
    }
    public void setEnemyVictory(String enemyVictory) {
        HelperMethods.checkString("enemyVictory", enemyVictory);
        this.enemyVictory = enemyVictory;
    }
    public void setDescription(String description) {
        HelperMethods.checkString("description", description);
        this.description = description;
    }
    // Optional properties
    public void setNoVictory(String noVictory) {
        if (noVictory != null) {
            HelperMethods.checkString("noVictory", noVictory);
        }
        this.noVictory = noVictory;
    }
    public void setControlZone(String controlZone) {
        if (controlZone != null) {
            HelperMethods.checkString("controlZone", controlZone);
        }
        this.controlZone = controlZone;
    }
    public void setDeployment(String deployment) {
        if (deployment != null) {
            HelperMethods.checkString("deployment", deployment);
        }
        this.deployment = deployment;
    }
    public void setExtraction(String extraction) {
        if (extraction != null) {
            HelperMethods.checkString("extraction", extraction);
        }
        this.extraction = extraction;
    }
    public void setObjective(String objective) {
        if (objective != null) {
            HelperMethods.checkString("objective", objective);
        }
        this.objective = objective;
    }
}
