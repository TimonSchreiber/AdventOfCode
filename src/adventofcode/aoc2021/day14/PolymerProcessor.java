package adventofcode.aoc2021.day14;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the process of extending the Polymer by keeping track
 * of the Elements, Element Pairs and Insertion Rules in separate maps.
 */
public class PolymerProcessor {

    /** the total (actual) number of Elements in the Polymer */
    private final Map<Element, Long> elementCounter;

    /** The current Polymer as a map of Element Pairs and a counter */
    private Map<Pair, Long> pairCounter;

    /** The Insertion Rules */
    private final Map<Pair, Element> insertionRules;

    /**
     * Class constructor.
     * @param elementCounter
     * @param pairCounter
     * @param insertionRules
     */
    public PolymerProcessor(Map<Element, Long> elementCounter,
                            Map<Pair, Long> pairCounter,
                            Map<Pair, Element> insertionRules) {
        this.elementCounter = elementCounter;
        this.pairCounter = pairCounter;
        this.insertionRules = insertionRules;
    }

    /**
     * Compute a specified number of process steps
     * @param n     The number of steps
     * @return      The difference between the most and least common Polymer
     *              after n steps
     */
    public long steps(int n) {
        for (int step = 0; step < n; step++) {
            this.step();
        }
        return differenceBetweenExtrema();
    }

    /**
     * Convert each Entry in the Polymer Template into the assigned number of
     * new Pairs.
     */
    private void step() {
        // temporary Map for inserting the new PolymerPairs
        final Map<Pair, Long> tmpCounter = new HashMap<>();

        // for each key in polymerTemplate find the Polymer that needs to be
        // inserted and put the two new Pairs into the tmpMap
        this.pairCounter.entrySet()
            .forEach(
                entry -> {
                    // find the insertion rule to this Pair (the new Element)
                    Element element = this.insertionRules.get(entry.getKey());

                    // check if this was a valid Entry in the Insertion Rule Map
                    if (element != null) {

                        // add the new Element to the counter as often as neccessary
                        elementCounter.merge(element, entry.getValue(), (v1,v2) -> v1+v2);

                        // split the current Pair in the middle
                        String left   = entry.getKey().pair().substring(0, 1);
                        String right  = entry.getKey().pair().substring(1, 2);

                        // convert the Element to String
                        String middle = Character.toString(element.codePoint());

                        // construct the new Pairs
                        Pair p1 = new Pair(left + middle);
                        Pair p2 = new Pair(middle + right);

                        // add the new Pairs to the tmpCounter as often as it is counted in the Pair Counter
                        tmpCounter.merge(p1, entry.getValue(), (v1,v2) ->  v1+v2);
                        tmpCounter.merge(p2, entry.getValue(), (v1,v2) ->  v1+v2);
                    }
                }
            );

        // Set the Pair Counter to the new Map
        this.pairCounter = tmpCounter;
    }

    /**
     * Calculate the difference between the most and the least common Element.
     */
    private long differenceBetweenExtrema() {
        long max = Collections.max(this.elementCounter.values());
        long min = Collections.min(this.elementCounter.values());

        return max - min;
    }

}
