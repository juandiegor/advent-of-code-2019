package day3;

import io.vavr.collection.List;

public class Cable {

    public static class State {
        public final Coordinate coordinate;
        public final int steps;

        public State(Coordinate coordinate, int steps) {
            this.coordinate = coordinate;
            this.steps = steps;
        }

        @Override
        public boolean equals(Object obj) {
            return coordinate.x == ((Coordinate)obj).x && coordinate.y == ((Coordinate)obj).y;
        }
    }

    public static class Coordinate {
        public final double x;
        public final double y;

        public Coordinate(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + "," + y;
        }

        @Override
        public boolean equals(Object obj) {
            return x == ((Coordinate)obj).x && y == ((Coordinate)obj).y;
        }
    }


    public Coordinate currentPosition;
    public List<State> trace;
    public int totalSteps = 0;

    public Cable(Coordinate initialPosition) {
        currentPosition = initialPosition;
        this.trace = List.of(new State(currentPosition, 0));
    }

    public void move(Action action, int steps) {
        totalSteps+=steps;
        switch (action) {
            case U:
                this.currentPosition = new Coordinate(currentPosition.x, currentPosition.y+steps);
                trace = trace.append(new State(currentPosition, totalSteps));
                break;
            case D:
                this.currentPosition = new Coordinate(currentPosition.x, currentPosition.y-steps);
                trace = trace.append(new State(currentPosition, totalSteps));
                break;
            case R:
                this.currentPosition = new Coordinate(currentPosition.x+steps, currentPosition.y);
                trace = trace.append(new State(currentPosition, totalSteps));
                break;
            case L:
                this.currentPosition = new Coordinate(currentPosition.x-steps, currentPosition.y);
                trace = trace.append(new State(currentPosition, totalSteps));
                break;
            default:
                throw new IllegalStateException("say what?");
        }
    }
}
