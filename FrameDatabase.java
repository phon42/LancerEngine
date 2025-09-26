public class FrameDatabase {
    private static final Frame[] frameList = new Frame[] {
        new Frame("GMS", "Everest", new String[] {"Balanced"},
            "Most humans don’t think to ask about the history of the water they"
            + " drink, the earth they walk, or the air they breathe. And yet,"
            + " without water, earth, and air, there would be nowhere for"
            + " humanity to make a home.\nJust the same, the GMS-SP1 “Everest”"
            + " is often taken for granted, its importance dismissed in favor"
            + " of other, more specialized frames. A plain and unpretentious"
            + " mech, defined by simple lines, functional grace, universal"
            + " compatibility, and sturdy bulk, the Everest is as fundamental"
            + " to the modern mechanized chassis as the natural world is to"
            + " human life. The Everest isn’t the most specialized mech, but it"
            + " is the backbone of our expansion imperative. From its"
            + " shoulders, humanity steps.\nPrior to GMS’s official adoption of"
            + " the name, “Everest” was a use-name given to the frame by its"
            + " pilots. Mount Everest – or Sagarmatha, or Chomolungma, as it"
            + " has been called in older human tongues – is the tallest"
            + " mountain on Cradle, though not the most prominent peak in known"
            + " space, nor even the greatest in Cradle’s star system, yet"
            + " pilots across the galaxy call their SP1s by that ancient name."
            + " Why?\nThe sentimental answer is that the name is a reminder of"
            + " what was once the limit of human endurance – once the height of"
            + " human achievement. To reach Everest’s summit was to defy death"
            + " and stand atop the world – the culmination of months, even"
            + " years, of training, investment, and hard work. Reaching the"
            + " peak was also a triumph of the people, systems, and"
            + " institutions behind the individual – a triumph too often left"
            + " unacknowledged, or deliberately erased.\nSagarmatha."
            + " Chomolungma.\nEven before the Fall, when the Massif vaults were"
            + " built, some names – some stories – were given priority over"
            + " others.\nThe real story behind the Everest’s name is likely"
            + " much less deliberate. Somewhere along the line, a newly"
            + " graduated pilot, frustrated by GMS’s plain naming conventions,"
            + " painted “EVEREST” across the flank of their SP1. Maybe it was a"
            + " callsign, or maybe it represented the pride they felt at"
            + " success. Either way, the name stuck: others adopted the name,"
            + " and over five centuries it grew to become the officially"
            + " unofficial designation of the SP1 chassis.\nVeteran pilots may"
            + " never return to the Everest after moving on, but they’ll always"
            + " remember reaching that first summit – the mountaintop where"
            + " they proved they could plant their own flag at the peak of the"
            + " world.\nSagarmatha. Chomolungma.\nEverest – you’ll never forget"
            + " it.", 2, 4, 10, 0, 4,
            6, 8, 4, 8, 0,
            10, 5, 10, 6,
            new String[] {"Initiative", "Replaceable Parts"}, new Mount[] {
                new Mount("main", new Weapon()),
                new Mount("flex", new Weapon()),
                new Mount("heavy", new Weapon())})
    };

    public static Frame getFrame(String frameName) {
        for (int i = 0; i < frameList.length; i++) {
            if (frameList[i].getName().equals(frameName)) {
                return frameList[i];
            }
        }
        throw new IllegalArgumentException("No frame found for frame name: "
            + frameName);
    }
}
