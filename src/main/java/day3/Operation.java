package day3;

public class Operation {
    public final Action action;
    public final int step;

    public Operation(Action action, int step) {
        this.action = action;
        this.step = step;
    }

    @Override
    public String toString() {
        return action.name() + step;
    }
}
