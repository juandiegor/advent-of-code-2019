package day3;

public class Operation {
    public final Action action;
    public final int steps;

    public Operation(Action action, int step) {
        this.action = action;
        this.steps = step;
    }

    @Override
    public String toString() {
        return action.name() + steps;
    }
}
