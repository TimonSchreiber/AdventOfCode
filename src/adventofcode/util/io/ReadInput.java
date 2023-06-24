package adventofcode.util.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Factory class for reading files.
 */
public class ReadInput {

    /**
     *
     */
    private static final String RESOURCES = "resources/";
    private static final String LINE_BREAK = "\n";

    public static List<String> toListOfStringsFrom(String filePath) {

        return ReadInput.lines(Path.of(RESOURCES, filePath))
                .toList();
    }

    public static String toSingleStringFrom(String filePath) {

        return ReadInput.lines(Path.of(RESOURCES, filePath))
                .collect(Collectors.joining(LINE_BREAK));
    }

    private static Stream<String> lines(Path filePath) {

        try {
            return Files.lines(filePath);

        } catch (IOException ioe) {
            ioe.printStackTrace();
            return Stream.empty();
        }
    }
}
