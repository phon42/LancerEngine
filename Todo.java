/**
 * Questions:
 * - Can pilots take burn? - yes
 * - Can the environment take heat or burn? - no and kind of (takes the damage
 *       but doesn't track burn)
 * - pg. 73 - "Unless specified otherwise, all weapons default to 1 THREAT."
 *     Does this mean ALL weapons have 1 threat automatically? Or just when they
 *     don't have a range value specified?
 */
// TODO: implement this whole list
/**
 * What's Left To Add:
 * - Order of resolution (pg. 68)
 * - IMPLEMENTING conditions and statuses (the actual things themselves, not
 *       just having them) (pgs. 77 - 78)
 * - Weapons - they have to actually attack lol
 *   - incl pilot weapons
 * - MechSystems need to do their actions/effects
 *   - incl pilot armor and gear
 * - checking validity for (having the licenses to) weapons and systems
 * - actions on your turns (everything on pgs. 69 - 73, honestly)
 *   - incl movement
 * - reactions
 * - implement reallocation of licenses on levelup (pg. 18)
 * - implement full repairs - see pgs. 33, 49, 71, 73 and 81 - 82
 * - downtime actions and reserves I guess.. - pgs. 53 - 55 for downtime
 * 
 * Probably best saved for next level of abstraction:
 * - track engagement (check page 74)? (doesn't make any sense to do until we're
 *     a level up)
 * - involuntary movement
 * - difficult/dangerous terrain
 * - adjacency
 * - lifting, dragging, jumping, climbing, flying, gravity, teleportation,
 *       hovering
 * - cover
 * - damaging the environment
 */
/**
 * Eventually thinking of hooking this up to something else for graphics. If all
 *     else fails, I will use HTML because that's what I know. In that case,
 *     see:
 * - https://stackoverflow.com/questions/10964693/java-embedding-into-html
 * - https://stackoverflow.com/questions/985754/how-to-deploy-a-java-applet-for-todays-browsers-applet-embed-object
 * - https://www.java.com/en/download/help/enable_browser.html
 * 
 * Preliminary searches show that C# is also a good language for creating graphics:
 * - https://www.reddit.com/r/learnprogramming/comments/m7x51g/comment/gre467l/
 * 
 * Can also just straight up use Java AWT:
 * - https://www.geeksforgeeks.org/java/what-is-java-awt-graphics/
 */