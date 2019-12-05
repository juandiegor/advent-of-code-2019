package day3;

import io.vavr.collection.Array;
import io.vavr.collection.List;
import io.vavr.control.Option;
import utils.FileUtils;

public class Circuit {
    private Cable cable1;
    private Cable cable2;
    private final Cable.Coordinate centralPort;
    private final List<String> lines;

    public Circuit(List<String> lines) {
        this.centralPort = new Cable.Coordinate(0, 0);
        this.cable1 = new Cable(centralPort);
        this.cable2 = new Cable(centralPort);
        this.lines = lines;
    }

    private static Double manhattanDistance(Cable.Coordinate point1, Cable.Coordinate point2) {
        return Math.abs(point2.x - point1.x) + Math.abs(point2.y - point1.y);
    }

    public Double calculateClosestIntersection() {
        List<Cable.Coordinate> intersection = List.empty();
        for (int i = 1; i < cable1.trace.length(); i++) {
            for (int j = 1; j < cable2.trace.length(); j++) {
                Cable.Coordinate A = cable1.trace.get(i-1);
                Cable.Coordinate B = cable1.trace.get(i);
                Cable.Coordinate C = cable2.trace.get(j-1);
                Cable.Coordinate D = cable2.trace.get(j);

                Option<Cable.Coordinate> coordinates = lineLineIntersection(A, B, C, D);
                if(coordinates.isDefined()) {
                    intersection = intersection.append(coordinates.get());
                }
            }
        }

        return intersection
                .filter(coordinates -> !coordinates.equals(centralPort))
                .map(x -> manhattanDistance(centralPort, x))
                .sorted()
                .head();
    }

    public void putCablesInCircuit(List<String> cableOperations) {
        String cable1Operations = cableOperations.get(0);
        String cable2Operations = cableOperations.get(1);

        List<Operation> cable1Ops = getOperationsFromString(cable1Operations);
        List<Operation> cable2Ops = getOperationsFromString(cable2Operations);

        cable1Ops.forEach(
                op -> cable1.move(op.action, op.step)
        );

        cable2Ops.forEach(
                op -> cable2.move(op.action, op.step)
        );
    }

    public List<Operation> getOperationsFromString(String operationsLine) {
        Array<String> operations = Array.of(operationsLine.split(","));
        Array<Operation> map = operations.map(
                op -> new Operation(Action.valueOf(String.valueOf(op.charAt(0))), Integer.parseInt(op.substring(1)))
        );
        return map.toList();
    }

    private static Option<Cable.Coordinate> lineLineIntersection(Cable.Coordinate A, Cable.Coordinate B, Cable.Coordinate C, Cable.Coordinate D) {
        double a1 = B.y - A.y;
        double b1 = A.x - B.x;
        double c1 = a1*(A.x) + b1*(A.y);

        double a2 = D.y - C.y;
        double b2 = C.x - D.x;
        double c2 = a2*(C.x)+ b2*(C.y);

        double determinant = a1*b2 - a2*b1;

        if (determinant == 0) {
            // The lines are parallel
            return Option.none();
        } else {
            double x = (b2*c1 - b1*c2)/determinant;
            double y = (a1*c2 - a2*c1)/determinant;

            if (inSegment(x, y, A, B) && inSegment(x, y, C, D)) {
                return Option.of(new Cable.Coordinate(x, y));
            } else {
                return Option.none();
            }

        }
    }

    private static boolean inSegment(Double x, Double y, Cable.Coordinate A, Cable.Coordinate B) {
        return x >= Math.min(A.x, B.x) && x <= Math.max(A.x, B.x)
                && y >= Math.min(A.y, B.y) && y <= Math.max(A.y, B.y);
    }

    public Double run() {
        putCablesInCircuit(lines);
        return calculateClosestIntersection();
    }

    public static void main(String args[]) {
        List<String> lines = FileUtils.readFile("src/main/resources/day3.txt");
//        List<String> lines = List.of("R10,U20", "U20,R10");
//        List<String> lines = List.of("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83");
//        List<String> lines = List.of("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");
        Circuit circuit = new Circuit(lines);
        System.out.println(circuit.run());
    }
}
