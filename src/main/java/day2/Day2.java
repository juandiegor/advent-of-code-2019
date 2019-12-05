package day2;

import io.vavr.collection.Array;
import io.vavr.collection.List;
import io.vavr.control.Option;
import utils.FileUtils;

import java.util.Arrays;

public class Day2 {

    public Integer[] runProgram(Integer[] program) {
        for (int i = 0; i < program.length; i+=4) {
            System.out.println(String.format("indexes: %s %s %s %s", i, i+1, i+2, i+3));
            Integer opcode = program[i];
            if (opcode == 99) break;
            if (opcode == 1) {
                System.out.println(String.format("Adding %s plus %s and storing %s in index %s", program[program[i+1]], program[program[i+2]], program[program[i+1]] + program[program[i+2]], program[program[i+3]]));
                program[program[i+3]] = program[program[i+1]] + program[program[i+2]];
            } else if (opcode == 2) {
                System.out.println(String.format("Multiplying %s times %s and storing %s in index %s", program[program[i+1]], program[program[i+2]], program[program[i+1]] + program[program[i+2]], program[program[i+3]]));
                program[program[i+3]] = program[program[i+1]] * program[program[i+2]];
            }
        }

        return program;
    }

    public Option<Integer> controlIterations(Array<Integer> program2) {
        for(int noun = 0; noun < 100; noun++) {
            for(int verb = 0; verb < 100; verb++) {
                Integer[] program = program2.toJavaArray(Integer[]::new);
                program[1] = noun;
                program[2] = verb;
                System.out.println(String.format("using program %s", Arrays.asList(program)));
                Integer[] output = new Day2().runProgram(program);
                if (output[0] == 19690720) {
                    return Option.of(100*noun + verb);
                }
            }
        }
        return Option.none();
    }

    public static void main(String args[]) {
        List<String> fileLines = FileUtils.readFile("src/main/resources/day2.txt");
        String[] codesArray = fileLines.get(0).split(",");
        Array<Integer> program = Array.of(codesArray).map(Integer::new);
        Option<Integer> result = new Day2().controlIterations(program);
        System.out.println(result.get());
    }
}
