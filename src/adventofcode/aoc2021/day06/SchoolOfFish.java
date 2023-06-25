package adventofcode.aoc2021.day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SchoolOfFish {

    // A List of fish grouped by the 9 stages of laternfish life.
    private List<Long> schoolOfFish;

    public SchoolOfFish(String string) {
        // create an Array of 9 zeroes for the 9 stages of laternfish life.
        Long[] array = new Long[9];
        Arrays.fill(array, 0L);

        // group the fish together by life stage
        Arrays.stream(string.split(","))
                .mapToInt(Integer::valueOf)
                .forEach(i -> array[i]++);

        // wrap the Array into a mutable ArrayList
        this.schoolOfFish = new ArrayList<>(List.of(array));
    }

    public void play(int days) {
        for (int day = 1; day <= days; day++) {

            // get the number of fish which will reproduce now
            long fish = schoolOfFish.remove(0);

            // add the new baby fish at the end of the list
            schoolOfFish.add(fish);

            // insert the fish which reproduced back into the List
            schoolOfFish.set(6, schoolOfFish.get(6)+fish);
        }
    }

    // count the total number of fish in this school
    public long countFish() {
        return this.schoolOfFish.stream()
                .mapToLong(Long::valueOf)
                .sum();
    }
}
