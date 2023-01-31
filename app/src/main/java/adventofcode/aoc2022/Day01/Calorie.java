package adventofcode.aoc2022.Day01;

public record Calorie(int calorie) {

    public Calorie(String string) {
        this(Integer.parseInt(string));
    }

}
