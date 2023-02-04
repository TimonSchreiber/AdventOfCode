package adventofcode.aoc2022.Day07;

import java.util.Arrays;
import java.util.List;

import adventofcode.util.ReadInput;

/**
 * https://adventofcode.com/2022/day/7
 */
public class NoSpaceLeftOnDevice {

    private static final int DISC_SPACE = 70_000_000;
    private static final int NEEDED_SPACE = 30_000_000;

    private final static String COMMAND          = "$";
    private final static String CHANGE_DIRECTORY = "cd";
    private final static String BACK_UP          = "..";
    private final static String ROOT             = "/";
    private final static String LIST             = "ls";
    private final static String DIRECTORY        = "dir";

    public static void main(String[] args) {
        System.out.println("\n### Day 7: No Space Left On Device ###\n");

        // file path as String
        final String filePath = "aoc2022/Day07/test";

        List<String> input = ReadInput.toListofStringsFrom(filePath);

        FileSystem fileSystem = processInput(input);

        // fileSystem.print();

        System.out.println("\nTotal size: " + fileSystem.root.totalSize());

        long requiredSpace = NEEDED_SPACE - (DISC_SPACE - fileSystem.root.totalSize());

        System.out.println("\nRequired Space: " + requiredSpace + "\n");

        fileSystem.print();

        long part1 = fileSystem.root.sumOfDirectoriesWithSizeBelow(100000L);
        System.out.println("-> Part1: " + part1);
        
        long part2 = fileSystem.root.sizeOfSmallestDirectorieWithSizeBiggerThan(requiredSpace);
        System.out.println("-> Part2: " + part2);

    }

    private static FileSystem processInput(List<String> input) {

        FileSystem fileSystem = new FileSystem();

        // a line can be one of 3 categories:
        //  1. command startring with '$' like 'cd xx' (cd x, cd .., cd /) or 'ls'
        //  2. directory starting with 'dir'
        //  3. file satring with a number (the file size)

        // TODO: extract the nested if statements into separate methods
        input.stream().forEachOrdered(
            str -> {
                // split string along spaces
                List<String> line = Arrays.stream(str.split("[ ]")).toList();

                // check if first String is '$'
                if (line.get(0).equals(COMMAND)) {

                    // check if second String is 'cd'
                    if (line.get(1).equals(CHANGE_DIRECTORY)) {

                        // the argument behind 'cd' -> 'x', '..', '/'
                        final String argument = line.get(2);
                        switch (argument) {
                            case BACK_UP: fileSystem.cdUp(); break;
                            case ROOT: fileSystem.cdRoot(); break;
                            default: fileSystem.cdDown(argument); break;
                        }

                    // check if second String is ls
                    } else if (line.get(1).equals(LIST)) {
                        // Do nothing

                    } else {
                        // invalid command
                        throw new IllegalArgumentException("Invalid command: " + line.get(1));
                    }

                // check if first String is 'dir'
                } else if (line.get(0).equals(DIRECTORY)) {
                    fileSystem.addDirectory(line.get(1));

                // else the first String is a number then the second String is the fileName
                } else {
                    // starts with a number
                    long fileSize = Long.parseLong(line.get(0));
                    String fileName = line.get(1);
                    File file = new File(fileSize, fileName);
                    fileSystem.addFile(file);
                }
            }
        );

        return fileSystem;
    }

}
