package packages.CoreTypes;

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
    // TODO: fill out
    // Required properties
    private String id;
    private String name;
    private String pcVictory;
    private String enemyVictory;
    private String description;
    // Optional properties
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
    public void setID(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPcVictory(String pcVictory) {
        this.pcVictory = pcVictory;
    }
    public void setEnemyVictory(String enemyVictory) {
        this.enemyVictory = enemyVictory;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    // Required properties
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
    // Optional properties
    public void setNoVictory(String noVictory) {
        this.noVictory = noVictory;
    }
    public void setControlZone(String controlZone) {
        this.controlZone = controlZone;
    }
    public void setDeployment(String deployment) {
        this.deployment = deployment;
    }
    public void setExtraction(String extraction) {
        this.extraction = extraction;
    }
    public void setObjective(String objective) {
        this.objective = objective;
    }
}
