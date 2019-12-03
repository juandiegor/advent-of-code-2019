import io.vavr.collection.List;
import io.vavr.control.Try;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day1 {

    public static Integer calculateFuelFromMass(Integer mass) {
        return BigDecimal.valueOf(mass)
                .divide(BigDecimal.valueOf(3), 1, RoundingMode.FLOOR)
                .subtract(BigDecimal.valueOf(2))
                .intValue();
    }

    public Integer calculateFuel(List<String> moduleMasess) {
        return moduleMasess
                .map(Integer::new)
                .map(Day1::calculateFuelFromMass)
                .reduce((a, b) -> a + b)
                .intValue();
    }

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

    public static void main(String args[]) {
        Integer totalFuell = new Day1().calculateFuel(readFile("src/main/resources/day1.txt"));
        System.out.println(totalFuell);
    }
}
