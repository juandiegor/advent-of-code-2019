import io.vavr.collection.List;
import utils.FileUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Day1_2 {

    public static Integer calculateFuelFromMass(Integer mass) {
        return BigDecimal.valueOf(mass)
                .divide(BigDecimal.valueOf(3), 1, RoundingMode.FLOOR)
                .subtract(BigDecimal.valueOf(2))
                .intValue();
    }

    public static Integer calculateModuleFuel(Integer moduleMass) {
        if (moduleMass < 4) return 0;
        else {
            Integer fuel = calculateFuelFromMass(moduleMass);
            return fuel + calculateModuleFuel(fuel);
        }
    }

    public Integer calculateFuel(List<String> moduleMasses) {
        return moduleMasses
                .map(Integer::new)
                .map(Day1_2::calculateModuleFuel)
                .reduce((a, b) -> a + b)
                .intValue();
    }

    public static void main(String args[]) {
        Integer totalFuell = new Day1_2().calculateFuel(FileUtils.readFile("src/main/resources/day1.txt"));
        System.out.println(totalFuell);
    }
}
