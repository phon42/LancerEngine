public class Main {
    // if you don't know what this is from looking at the method name
    // you should not be reading this code right now
    public static void main(String[] args) {
        LancerCharacter myCharacter = new LancerCharacter(
            "Coral Nolan", "Apocalypse");
        Pilot myPilot = myCharacter.getPilot();
        Mech myMech = myCharacter.getMech();
        Loadout myLoadout;

        myPilot.setPlayer("Luna");
        myPilot.setBackground("e");
        myPilot.setBiography("e");
        myPilot.setAppearance("e");
        myPilot.setPlayerNotes("e");
        SkillTriggersList newSkillTriggers = new SkillTriggersList();
        newSkillTriggers.setSkillTriggers(new SkillTrigger[] {
            new SkillTrigger("Apply Fists to Faces", 2),
            new SkillTrigger("Assault", 2),
            new SkillTrigger("Blow Something Up", 2),
            new SkillTrigger("Survive", 2)
        });
        myPilot.setSkillTriggers(newSkillTriggers);
        myPilot.setLoadout(new Loadout());
        myPilot.setLicenseLevel(9);
        myPilot.setLicenseList(new License[] {
            new License("SSC Swallowtail", 3),
            new License("SSC Death's Head", 3),
            new License("HORUS Kobold", 3),
            new License("HORUS Lich", 1)
        });
        myPilot.setMechSkills(new int[] {4, 0, 5, 2});
        myPilot.setCoreBonuses(new String[] {
            "Neurolink Targeting",
            "Overpower Caliber",
            "Integrated Weapon"
        });
        myPilot.setTalents(new Talent[] {
            new Talent("Tactician", 3),
            new Talent("Siege Specialist", 3),
            new Talent("Spotter", 2),
            new Talent("Walking Armory", 2),
            new Talent("Leader", 2)
        });
        myLoadout = new Loadout("Mobility Hardsuit",
            new String[] {"Heavy Signature", "Archaic Melee"},
            new String[] {"Wilderness Survival Kit", "Flexsuit",
            "Personal Drone"});
        myPilot.setLoadout(myLoadout);
        myMech = new Mech("Wraith", "swallowtail_ranger");
        /*
        myMech.setMount(0, new Mount("Integrated Weapon",
            new Weapon("Nexus (Light)")));
        */
        myMech.setMount(0, new Mount(
            new Weapon("Slag Cannon")));
        myMech.setMount(1, new Mount(new Weapon("Vulture DMR"),
            "", "Overpower Caliber"));
        myMech.setSystems(new MechSystem[] {
            new MechSystem("Pattern-A Smoke Charges", 3),
            new MechSystem("Seismic Ripper"),
            new MechSystem("High-Stress Mag Clamps"),
            new MechSystem("ATHENA-Class NHP"),
            new MechSystem("Markerlight"),
            new MechSystem("IMMOLATE"),
            new MechSystem("Wandering Nightmare")
        });
        myCharacter.setPilot(myPilot);
        myCharacter.setMech(myMech);

        System.out.print(myCharacter.generateStatblock(
            "mech build"));
        // TestFunctions.runTests();
    }
}
