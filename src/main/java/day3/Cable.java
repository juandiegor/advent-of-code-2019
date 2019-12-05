package day3;

import io.vavr.collection.List;

public class Cable {

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

    private Coordinate currentPosition;
    public List<Coordinate> trace;

    public Cable(Coordinate initialPosition) {
        currentPosition = initialPosition;
        this.trace = List.of(currentPosition);
    }

    public void move(Action action, int steps) {
        switch (action) {
            case U:
                this.currentPosition = new Coordinate(currentPosition.x, currentPosition.y+steps);
                trace = trace.append(currentPosition);
                break;
            case D:
                this.currentPosition = new Coordinate(currentPosition.x, currentPosition.y-steps);
                trace = trace.append(currentPosition);
                break;
            case R:
                this.currentPosition = new Coordinate(currentPosition.x+steps, currentPosition.y);
                trace = trace.append(currentPosition);
                break;
            case L:
                this.currentPosition = new Coordinate(currentPosition.x-steps, currentPosition.y);
                trace = trace.append(currentPosition);
                break;
            default:
                throw new IllegalStateException("say what?");
        }
    }
}
