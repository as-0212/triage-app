package triage;

public class Child extends Patient {
    public Child(String name, int injuryLevel) {
        super(name, injuryLevel);
    }

    @Override
    public String getType() { return "Child"; }
}
