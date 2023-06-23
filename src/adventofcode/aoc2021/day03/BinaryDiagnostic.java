package adventofcode.aoc2021.day03;

import java.util.List;

import adventofcode.util.io.ReadInput;

/**
 * https://adventofcode.com/2021/day/3
 */
public class BinaryDiagnostic {
    public static void main(String[] args) {
        System.out.println("\n--- Day 3: Binary Diagnostic ---\n");

        final String filePath = "aoc2021/day03/input";

        final List<String> input = ReadInput.toListofStringsFrom(filePath);

        final int width = input.get(0).length();

        final List<Integer> binaries = BinaryParser.parse(input);

        long part1 = calcPowerConsumption(binaries, width);
        System.out.println("-> Part1: " + part1);   // 741950

        long part2 = findLifeSupportRating(binaries, width);
        System.out.println("-> Part2: " + part2);   // 903810
    }

    private static int calcPowerConsumption(List<Integer> list, int width) {
        int gammaRate = 0;
        int epsilonRate = 0;

        for (int j = width-1; j >= 0; j--) {
            int mask = (int) Math.pow(2, j);
            int bit = mostCommonBit(list, mask);

            gammaRate   += (bit == 1) ? mask : 0;
            epsilonRate += (bit == 0) ? mask : 0;
        }

        return gammaRate * epsilonRate;
    }

    private static int findLifeSupportRating(List<Integer> list, int width) {
        List<Integer> oxygen = List.copyOf(list);
        List<Integer> co2 = List.copyOf(list);

        int oxygenRating = 0;
        int co2Rating = 0;

        // oxygen
        for (int j = width-1; j >= 0; j--) {
            int mask = (int) Math.pow(2, j);
            int bit = mostCommonBit(oxygen, mask);

            oxygen = oxygen.stream().filter(i -> Integer.signum(i & mask) == bit).toList();

            if (oxygen.size() == 1) {
                oxygenRating = oxygen.get(0);
                break;
            }
        }

        // co2
        for (int j = width-1; j >= 0; j--) {
            int mask = (int) Math.pow(2, j);
            int bit = mostCommonBit(co2, mask);

            co2 = co2.stream().filter(i -> Integer.signum(i & mask) != bit).toList();

            if (co2.size() == 1) {
                co2Rating = co2.get(0);
                break;
            }
        }

        return oxygenRating * co2Rating;
    }

    private static int mostCommonBit(List<Integer> list, int mask) {
        int sum = list.stream()
                .mapToInt(i -> i & mask)
                .map(Integer::signum)
                .sum();

        return (sum >= (list.size()-sum)) ? 1 : 0;
    }

}
