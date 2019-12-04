import io.vavr.collection.Array;
import io.vavr.collection.List;
import utils.FileUtils;

import java.util.Arrays;

public class Day2 {

    public Integer[] runProgram(List<String> fileLines) {
        String[] codesArray = fileLines.get(0).split(",");

        Integer[] program = Array.of(codesArray).map(Integer::new).toJavaArray(Integer[]::new);

        for (int i = 0; i < program.length; i+=4) {
            System.out.println(String.format("indexes: %s %s %s %s", i, i+1, i+2, i+3));
            Integer opcode = program[i];
            if (opcode == 99) break;
            if (opcode == 1) {
                System.out.println(String.format("Adding %s plus %s and storing %s in index %s", program[program[i+1]], program[program[i+2]], program[program[i+1]] + program[program[i+2]], program[program[i+3]]));
                program[program[i+3]] = program[program[i+1]] + program[program[i+2]];
            } else if (opcode == 2) {
                program[program[i+3]] = program[program[i+1]] * program[program[i+2]];
            }
        }

        return program;
    }

    public static void main(String args[]) {
        List<String> fileLines = FileUtils.readFile("src/main/resources/day2.txt");
        Integer[] output = new Day2().runProgram(fileLines);
        System.out.println(Arrays.asList(output));
    }
}
