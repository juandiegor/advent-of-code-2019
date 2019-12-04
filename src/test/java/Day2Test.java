import io.vavr.collection.Array;
import io.vavr.collection.List;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Day2Test {

    @Test
    public void test1() {
        List<String> input = List.of("1,9,10,3,2,3,11,0,99,30,40,50");
        String[] codesArray = input.get(0).split(",");
        Integer[] program = Array.of(codesArray).map(Integer::new).toJavaArray(Integer[]::new);
        Day2 day2 = new Day2();

        Integer[] output = day2.runProgram(program);

        assertThat(output[0], is(3500));
    }

}
