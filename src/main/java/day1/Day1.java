package day1;

import io.vavr.collection.List;
import utils.FileUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public static void main(String args[]) {
        Integer totalFuell = new Day1().calculateFuel(FileUtils.readFile("src/main/resources/day1.txt"));
        System.out.println(totalFuell);
    }
}
