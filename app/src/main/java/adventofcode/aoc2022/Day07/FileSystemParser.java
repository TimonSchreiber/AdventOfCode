package adventofcode.aoc2022.Day07;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileSystemParser {

    private static FileSystem fileSystem;

    private final static String COMMAND          = "$";
    private final static String CHANGE_DIRECTORY = "cd";
    private final static String BACK_UP          = "..";
    private final static String ROOT             = "/";
    private final static String LIST             = "ls";
    private final static String DIRECTORY        = "dir";
    
    public static Directory parse(List<String> list) {
        fileSystem = new FileSystem();

        list.stream()
            .forEachOrdered(FileSystemParser::parse);

        return fileSystem.root;
    }

    private static void parse(String string) {
        // split string along spaces
        final List<String> line = Arrays.stream(string.split(" ")).toList();

        switch (line.get(0)) {
            case COMMAND   -> parseCommand(line);
            case DIRECTORY -> parseDirectory(line);
            default        -> parseFile(line);
            // default -> the first String is a number
        }
    }

    private static void parseCommand(final List<String> line) {
        // the String behind '$' -> 'cs' or 'ls'
        switch (line.get(1)) {
            case CHANGE_DIRECTORY -> parseChangeDirectory(line);
            case LIST             -> { /* do nothing */ }
            default               -> invalidCommand(line);
        }
    }

    private static void parseDirectory(final List<String> line) {
        // the String behind 'dir' is the directory name
        String name = line.get(1);

        Directory dir = new Directory(name, fileSystem.current);
        fileSystem.add(dir);
    }

    private static void parseFile(final List<String> line) {
        // the line starts with a number -> fileSize and fileName
        long fileSize = Long.parseLong(line.get(0));
        String fileName = line.get(1);
    
        File file = new File(fileSize, fileName);
        fileSystem.add(file);
    }

    private static void parseChangeDirectory(final List<String> line) {
        // the String behind 'cd' -> '..', '/', 'x'
        switch (line.get(2)) {
            case BACK_UP ->  fileSystem.cdUp();
            case ROOT    ->  fileSystem.cdRoot();
            default      ->  fileSystem.cdDown(line.get(2));
        }
    }

    private static void invalidCommand(final List<String> line) {
        throw new IllegalArgumentException("Invalid command: " + line.stream().collect(Collectors.joining(" ")));
    }
    
}
