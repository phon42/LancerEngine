package Packages.CoreTypes.BattlefieldMechanics;

import MainBranch.HelperMethods;
import Packages.CoreTypes.VueHTMLString.VueHTMLString;

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
     * A description of this sitrep (i.e. a VueHTMLString representing "A"
     *     " simple affair, with two sides facing off against each other until"
     *     " one of them is broken or destroyed.").
     * Can be any VueHTMLString except "". Cannot be null.
     * Case-sensitive.
     */
    private VueHTMLString description;

    // Optional properties
    /**
     * The conditions for the player characters to claim victory within this
     *     sitrep (i.e. a VueHTMLString representing "PCs defeat or route all"
     *     " enemy forces").
     * Can be any VueHTMLString except "". Can be null.
     * Case-sensitive.
     */
    private VueHTMLString pcVictory;
    /**
     * The conditions for the enemy force to claim victory within this sitrep
     *     (i.e. a VueHTMLString representing "PCs are defeated or retreat from"
     *     " battle").
     * Can be any VueHTMLString except "". Can be null.
     * Case-sensitive.
     */
    private VueHTMLString enemyVictory;
    /**
     * The conditions under which no victory can be claimed by either side in
     *     this sitrep (i.e. a VueHTMLString representing "If both sides have"
     *     " an equal score at the end of the sixth round, there is no victor"
     *     " and both sides must withdraw.").
     * Can be any VueHTMLString except "". Can be null.
     * Case-sensitive.
     */
    private VueHTMLString noVictory;
    /**
     * The procedure for how to determine the order in which player characters
     *     and enemies should deploy (i.e. a VueHTMLString representing "The GM"
     *     " and a player each roll 1d6 to determine the order of deployment."
     *     " Whoever rolls the lower result deploys first.").
     * Can be any VueHTMLString except "". Can be null.
     * Case-sensitive.
     */
    private VueHTMLString deployment;
    /**
     * A description of what the Objective is and its importance (too long to
     *     provide an example).
     * Can be any VueHTMLString except "". Can be null.
     * Case-sensitive.
     */
    private VueHTMLString objective;
    /**
     * The conditions under which player characters may extract from the sitrep,
     *     if extraction is possible (too long to provide an example).
     * Can be any VueHTMLString except "". Can be null.
     * Case-sensitive.
     */
    private VueHTMLString extraction;
    /**
     * The restrictions for where the control zones for this sitrep can be
     *     placed (too long to provide an example).
     * Can be any VueHTMLString except "". Can be null.
     * Case-sensitive.
     */
    private VueHTMLString controlZone;

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
    public VueHTMLString getDescription() {
        return description;
    }
    // Optional properties
    public VueHTMLString getPCVictory() {
        return pcVictory;
    }
    public VueHTMLString getEnemyVictory() {
        return enemyVictory;
    }
    public VueHTMLString getNoVictory() {
        return noVictory;
    }
    public VueHTMLString getControlZone() {
        return controlZone;
    }
    public VueHTMLString getDeployment() {
        return deployment;
    }
    public VueHTMLString getExtraction() {
        return extraction;
    }
    public VueHTMLString getObjective() {
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
    private void setDescription(VueHTMLString description) {
        HelperMethods.checkVueHTMLString("description",
            description);
        this.description = description;
    }
    // Optional properties
    private void setPCVictory(VueHTMLString pcVictory) {
        if (pcVictory != null) {
            HelperMethods.checkVueHTMLString("pcVictory",
                pcVictory);
        }
        this.pcVictory = pcVictory;
    }
    private void setEnemyVictory(VueHTMLString enemyVictory) {
        if (enemyVictory != null) {
            HelperMethods.checkVueHTMLString("enemyVictory",
                enemyVictory);
        }
        this.enemyVictory = enemyVictory;
    }
    private void setNoVictory(VueHTMLString noVictory) {
        if (noVictory != null) {
            HelperMethods.checkVueHTMLString("noVictory",
                noVictory);
        }
        this.noVictory = noVictory;
    }
    private void setDeployment(VueHTMLString deployment) {
        if (deployment != null) {
            HelperMethods.checkVueHTMLString("deployment",
                deployment);
        }
        this.deployment = deployment;
    }
    private void setObjective(VueHTMLString objective) {
        if (objective != null) {
            HelperMethods.checkVueHTMLString("objective",
                objective);
        }
        this.objective = objective;
    }
    private void setExtraction(VueHTMLString extraction) {
        if (extraction != null) {
            HelperMethods.checkVueHTMLString("extraction",
                extraction);
        }
        this.extraction = extraction;
    }
    private void setControlZone(VueHTMLString controlZone) {
        if (controlZone != null) {
            HelperMethods.checkVueHTMLString("controlZone",
                controlZone);
        }
        this.controlZone = controlZone;
    }

    private void setDescription(String description) {
        setDescription(new VueHTMLString(description));
    }
    private void setPCVictory(String pcVictory) {
        if (pcVictory != null) {
            setPCVictory(new VueHTMLString(pcVictory));
        }
        this.pcVictory = null;
    }
    private void setEnemyVictory(String enemyVictory) {
        if (enemyVictory != null) {
            setEnemyVictory(new VueHTMLString(enemyVictory));
        }
        this.enemyVictory = null;
    }
    private void setNoVictory(String noVictory) {
        if (noVictory != null) {
            setNoVictory(new VueHTMLString(noVictory));
        }
        this.noVictory = null;
    }
    private void setDeployment(String deployment) {
        if (deployment != null) {
            setDeployment(new VueHTMLString(deployment));
        }
        this.deployment = null;
    }
    private void setObjective(String objective) {
        if (objective != null) {
            setObjective(new VueHTMLString(objective));
        }
        this.objective = null;
    }
    private void setExtraction(String extraction) {
        if (extraction != null) {
            setExtraction(new VueHTMLString(extraction));
        }
        this.extraction = null;
    }
    private void setControlZone(String controlZone) {
        if (controlZone != null) {
            setControlZone(new VueHTMLString(controlZone));
        }
        this.controlZone = null;
    }
}
