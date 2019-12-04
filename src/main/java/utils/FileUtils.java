package utils;

import io.vavr.collection.List;
import io.vavr.control.Try;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {

    public static List<String> readFile(String fileName) {
        java.util.List<String> list = Try.of(() -> Files.readAllLines(Path.of(fileName)))
                .fold(
                        t -> {
                            throw new IllegalArgumentException(String.format("missing file %s", fileName));
                        },
                        s -> s
                );

        return List.ofAll(list);
    }
}
